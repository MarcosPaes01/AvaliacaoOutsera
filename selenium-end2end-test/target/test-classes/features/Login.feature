Feature: Login e navegação para o formulário

  Scenario: Usuário faz login e navega até o formulário
    Given que o usuário está na página de login
    When ele preenche as credenciais válidas e entra
    Then ele deve ser redirecionado para a página de formulário

  Scenario: Usuário tenta login com senha inválida
    Given que o usuário está na página de login
    When ele preenche o usuário correto e senha incorreta
    Then ele deve ver uma mensagem de erro