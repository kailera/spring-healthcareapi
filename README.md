### SISTEMA DE INTEGRAÇÃO DE SAÚDE PÚBLICA
## Objetivo

Permitir que administradores de estabelecimentos de saúde pública acessem e contratem serviços médicos com maior velocidade para atender a demanda por 
profissionais de diversas especialidades. 

Sistema deve: 
- Cadastrar médicos pessoa física ou jurídica como prestadores de serviços;
- Cadastrar instituições de saúde como UBSs, Santas-Casas e clínicas;
- Exibir profissionais disponíveis para serviços
- Permitir que se visualize informações adicionais e enviar uma proposta de trabalho com valor (proposta rápida)
- handShake: profissional aceita e executa o trabalho.
- Pagamento: CC, Nota Fiscal, PIX

## Stack de Desenvolvimento

API: Spring Boot 
Persistência de dados: MYSQL
Front-end: React
Hospedagem: Heroku


### API

endpoints:
/organization: CRUD de instituições médicas públicas
/profissional: CRUD de profissionais
/jobs: CRUD de trabalhos (todo job é emitido por uma organization)



