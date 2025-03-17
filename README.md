# Sistema de Gerenciamento de Carros 🚗

Sistema RESTful desenvolvido com Spring Boot para gerenciamento de carros, com operações de cadastro em lote, listagem paginada e exclusão paralela.

## 📋 Funcionalidades

- **Cadastro em Lote**: Insere múltiplos carros em uma única requisição, com processamento em lotes de 50 registros
- **Listagem Paginada**: Consulta os carros com paginação automática e cache
- **Exclusão Paralela**: Remove múltiplos registros simultaneamente com processamento assíncrono
- **Validação de Dados**: Validação automática de campos obrigatórios e regras de negócio
- **Documentação API**: Interface Swagger/OpenAPI para teste e visualização dos endpoints

## 🛠️ Tecnologias

- **Java 11+**
- **Spring Boot 3.0.0**
  - Spring Web (API REST)
  - Spring Data JPA
  - Spring Validation
  - Spring Cache
- **H2 Database** (banco de dados em memória)
- **Springdoc OpenAPI** (documentação)
- **Google Guava** (processamento em lote)

## ⚙️ Arquitetura

### Estrutura de Pacotes

```
com.example.carros/
├── CarroApplication.java         # Classe principal da aplicação
├── controller/
│   └── CarroController.java      # Endpoints REST
├── exception/
│   └── GlobalExceptionHandler.java  # Tratamento de exceções
├── model/
│   └── Carro.java                # Entidade JPA
├── repository/
│   └── CarroRepository.java      # Interface de persistência
└── service/
    └── CarroService.java         # Lógica de negócios
```

### Modelo de Dados

O sistema gerencia objetos do tipo `Carro` com os seguintes atributos:

- **id**: Identificador único (gerado automaticamente)
- **marca**: Marca do carro (obrigatório)
- **modelo**: Modelo do carro (obrigatório)
- **ano**: Ano de fabricação (entre 1886 e 2100)
- **cor**: Cor do carro (obrigatório)

## 🔌 API REST

### Endpoints

| Método | URL | Descrição |
|--------|-----|-----------|
| POST | `/api/carros/batch` | Cadastra múltiplos carros |
| GET | `/api/carros` | Lista carros com paginação |
| DELETE | `/api/carros/batch` | Exclui múltiplos carros por ID |

### Exemplos de Uso

#### Cadastro em Lote

```http
POST /api/carros/batch
Content-Type: application/json

[
  {
    "marca": "Volkswagen",
    "modelo": "Golf",
    "ano": 2022,
    "cor": "Branco"
  },
  {
    "marca": "Toyota",
    "modelo": "Corolla",
    "ano": 2023,
    "cor": "Prata"
  }
]
```

#### Listagem com Paginação

```http
GET /api/carros?page=0&size=10&sort=marca,asc
```

#### Exclusão Paralela

```http
DELETE /api/carros/batch
Content-Type: application/json

[1, 2, 3, 4, 5]
```

## 🚀 Executando o Projeto

### Pré-requisitos

- Java 11 ou superior
- Maven 3.6 ou superior

### Passos para Execução

1. Clone o repositório:
   ```
   git clone https://github.com/seu-usuario/sistema-carros.git
   cd sistema-carros
   ```

2. Compile o projeto:
   ```
   mvn clean package
   ```

3. Execute a aplicação:
   ```
   java -jar target/carros-1.0.0.jar
   ```

4. Acesse a API:
   - API REST: http://localhost:8080/api/carros
   - Console H2: http://localhost:8080/h2-console
   - Documentação Swagger: http://localhost:8080/swagger-ui/index.html

## 📝 Características Técnicas Destacadas

### Processamento em Lote

O sistema utiliza a biblioteca Google Guava para dividir grandes conjuntos de dados em lotes menores, otimizando o processamento e reduzindo o impacto na memória e no banco de dados.

```java
List<List<Carro>> batches = Lists.partition(carros, 50);
```

### Exclusão Paralela

A exclusão em lote é executada de forma paralela e assíncrona utilizando CompletableFuture e streams paralelos do Java:

```java
CompletableFuture.runAsync(() -> {
    ids.parallelStream().forEach(id -> {
        carroRepository.deleteById(id);
    });
})
```

### Caching

O sistema implementa cache para a listagem de carros, melhorando o desempenho para consultas repetidas:

```java
@Cacheable("carros")
public Page<Carro> findAll(Pageable pageable) {
    return carroRepository.findAll(pageable);
}
```

### Tratamento de Exceções

Possui um tratamento global de exceções que padroniza as respostas de erro da API:

- Validação de campos obrigatórios
- Timeout para operações assíncronas
- Tratamento de erros gerais

## 📊 Considerações sobre Performance

- **Cadastro em Lote**: Reduz o número de transações no banco de dados
- **Exclusão Paralela**: Utiliza threads para acelerar operações em lote
- **Cache**: Reduz consultas repetidas ao banco de dados
- **Paginação**: Limita o volume de dados transferidos por requisição
