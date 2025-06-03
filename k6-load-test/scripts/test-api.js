import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    vus: 500,               // Número fixo de usuários virtuais
    duration: '5m',         // Duração total do teste: 5 minutos
    thresholds: {
      http_req_duration: ['p(95)<500'], // 95% das requisições abaixo de 500ms
      http_req_failed: ['rate<0.01'],   // menos de 1% de falhas
    },
  };

export default function () {
  const res = http.get('https://dog.ceo/api/breeds/image/random');

  check(res, {
    'status is 200': (r) => r.status === 200,
    'body is not empty': (r) => r.body.length > 0,
  });

  sleep(1); // simula tempo de navegação entre requisições
}