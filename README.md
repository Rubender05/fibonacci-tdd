# Projecte Fibonacci - Fase 3 CI/CD

Aplicació Java amb Spring Boot que implementa un API REST per calcular Fibonacci i gestionar usuaris i categories, amb pipeline CI/CD automàtica a GitHub Actions.

## Estructura Bàsica

**Entitats:** User, Category, FibonacciRecord  
**Base de Dades:** PostgreSQL 15  
**Endpoints:** 15 endpoints REST que retornen JSON  
**Tests:** ~55 tests (unitaris, integració, aceptació)

## Quickstart

### Setup
```bash
git clone <repo-url>
cd fibonacci-tdd
docker-compose up -d
```

### Tests
```bash
mvn clean test
```

### API
```bash
# Usuaris
curl http://localhost:8080/api/users
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com"}'

# Categories
curl http://localhost:8080/api/categories

# Fibonacci
curl http://localhost:8080/api/fibonacci/calculate/10
```

## Estructura del Projecte

```
src/
├── main/java/eu/cifpfbmoll/
│   ├── App.java
│   ├── Fibonacci.java
│   ├── controller/ (UserController, CategoryController, FibonacciController)
│   ├── service/ (UserService, CategoryService, FibonacciRecordService)
│   ├── repository/ (UserRepository, CategoryRepository, FibonacciRecordRepository)
│   └── model/ (User, Category, FibonacciRecord)
└── test/java/eu/cifpfbmoll/
    ├── FibonacciUnitTest
    ├── model/EntityModelUnitTest
    ├── service/*ServiceUnitTest
    ├── controller/*IntegrationTest
    ├── acceptance/APIAcceptanceTest
    └── util/TestFixtures
```

## Configuració

- **.env** - Variables de entorn (PostgreSQL, Spring Boot)
- **pom.xml** - Dependències Maven (Spring Boot, JUnit, Mockito, etc)
- **docker-compose.yml** - PostgreSQL + Spring Boot
- **Dockerfile** - Multi-stage build
- **checkstyle.xml** - Validació de codi

## Tests

| Tipus | Count |
|-------|-------|
| Unitaris | 13 |
| Integració | 11 |
| Aceptació | 5 |
| **Total** | **~55** |

## GitHub Actions

Pipeline automàtica que executa:
- Checkstyle (validació de codi)
- Unit Tests
- Integration Tests
- Docker Build
- Acceptance Tests

S'activa en: push/PR a main, master, develop

## Comandos Útils

```bash
# Tests
mvn clean test

# Build
mvn clean install

# Checkstyle
mvn checkstyle:check

# Docker
docker-compose up -d
docker-compose down -v

# Logs
docker logs fibo_app
docker logs fibo_db
```

## Endpoints REST

**Users:** GET/POST/PUT/DELETE /api/users  
**Categories:** GET/POST/PUT/DELETE /api/categories  
**Fibonacci:** GET /api/fibonacci/calculate/{n}, GET/POST/DELETE /api/fibonacci/records

## Requisits

- Java 17+
- Maven 3.8+
- Docker & Docker Compose

## Documentació Adicional

- `QUICK_START.md` - Guia ràpida
- `ARQUITECTURA.md` - Detalls tècnics
- `REFERENCIA_COMANDOS.md` - Comandos
- `postman_collection.json` - Endpoints en Postman

---

**Status:** ✅ Completat | **Versió:** 1.0 | **Data:** Febrer 2026

