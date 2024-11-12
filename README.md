# Projeto de Cadastro de Carros com Spring Boot 🚗

Este projeto é uma aplicação RESTful para o gerenciamento de carros, implementada em **Spring Boot**. Inclui funcionalidades para **cadastro em lote**, **listagem com paginação** e **exclusão paralela** de carros, com documentação automática via **Swagger**.

## Funcionalidades

- **Cadastro de Carros em Lote**: Permite o cadastro de uma lista de carros em uma única requisição.
- **Listagem com Paginação**: Consulta os carros cadastrados de maneira paginada.
- **Exclusão Paralela**: Exclui múltiplos carros de maneira assíncrona e paralela para maior performance.
- **Validação de Dados**: Valida campos obrigatórios e regras de negócio para cadastro.
- **Documentação da API com Swagger**: Documentação interativa disponível para testar e visualizar os endpoints.

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot 2.7.5**
- **Spring Data JPA**
- **H2 Database** (banco de dados em memória para testes)
- **Swagger com springdoc-openapi**
- **Guava** para manipulação de dados em lote

## Configuração do Projeto

### Pré-requisitos

- **Java 11** ou superior
- **Maven** para gerenciamento de dependências

### Configuração do Banco de Dados

O projeto está configurado para usar o banco de dados **H2** em memória, ideal para testes rápidos. As configurações do H2 estão no arquivo `application.properties`.

**Arquivo de Configuração (`src/main/resources/application.properties`):**

```properties
spring.datasource.url=jdbc:h2:mem:carrosdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update

