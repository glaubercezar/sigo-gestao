# SIGO serviço de gestão

* Este projeto é uma aplicação Spring Boot em Java 11 configurado para o Google Cloud App Engine Standard.

## Configurações

A aplicação possui 3 arquivos de configurações principais na pasta de recursos:

* application.yaml (configurações comuns para ambientes de desenvolvimento ou produção)
* application-dev.yaml (configurações para execução do ambiente local)
* application-prod.yaml (configurações para o ambiente de produção)

As configurações são para banco de dados, api keys, etc.

### Executar em localhost

* atribua o valor "dev" nas configurações de application.yaml (spring profiles active)
* Inicie a aplicação utilizando o maven:

```bash
mvn spring-boot:run
```

## Deploy

Com o SDK gcloud instalado e configurado é possível solicitar o deploy para o ambiente de produção:

```bash
gcloud app deploy
```

Para visualizar o app:
```
gcloud app browse
```
A aplicação passa a ser acessível em `https://<your-project-id>.appspot.com`.
