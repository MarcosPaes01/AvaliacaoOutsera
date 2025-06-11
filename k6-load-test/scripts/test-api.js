import http from 'k6/http';
import { check, sleep } from 'k6';
import { htmlReport } from "https://raw.githubusercontent.com/benc-uk/k6-reporter/main/dist/bundle.js";

export const options = {
  vus: 10,
  duration: '300s',
  thresholds: {
    http_req_duration: ['p(95)<800'],
    http_req_failed: ['rate<0.05'],
  },
};

export default function () {
  // 1. GET positivo
  let getRes = http.get('https://reqres.in/api/users/2');
  check(getRes, {
    'GET /users/2 - status 200': (r) => r.status === 200,
    'GET /users/2 - retorna JSON': (r) => r.headers['Content-Type'].includes('application/json'),
    'GET /users/2 - contém id': (r) => {
      try {
        return JSON.parse(r.body).data.id === 2;
      } catch (e) {
        return false;
      }
    },
  });

  // 2. GET negativo (usuário inexistente)
  let getNotFound = http.get('https://reqres.in/api/users/23');
  check(getNotFound, {
    'GET /users/23 - status 404': (r) => r.status === 404,
  });

  // 3. POST com dados randômicos
  const randomName = `User_${Math.random().toString(36).substring(7)}`;
  const randomJob = `Job_${Math.random().toString(36).substring(7)}`;
  let postRes = http.post('https://reqres.in/api/users', JSON.stringify({
    name: randomName,
    job: randomJob,
  }), { headers: { 'Content-Type': 'application/json' } });

  check(postRes, {
    'POST /users - status 201': (r) => r.status === 201,
    'POST /users - JSON válido': (r) => {
      try {
        const body = JSON.parse(r.body);
        return body.name === randomName && body.job === randomJob;
      } catch {
        return false;
      }
    },
  });

  // 4. PUT com atualização
  const updatedJob = `Updated_${Math.random().toString(36).substring(7)}`;
  let putRes = http.put('https://reqres.in/api/users/2', JSON.stringify({
    name: randomName,
    job: updatedJob,
  }), { headers: { 'Content-Type': 'application/json' } });

  check(putRes, {
    'PUT /users/2 - status 200': (r) => r.status === 200,
    'PUT /users/2 - JSON válido': (r) => {
      try {
        return JSON.parse(r.body).job === updatedJob;
      } catch {
        return false;
      }
    },
  });

  // 5. DELETE (não retorna corpo)
  let delRes = http.del('https://reqres.in/api/users/2');
  check(delRes, {
    'DELETE /users/2 - status 204': (r) => r.status === 204,
  });

  sleep(1);
}

// Gera relatório HTML
export function handleSummary(data) {
  return {
    "summary.html": htmlReport(data),
  };
}
