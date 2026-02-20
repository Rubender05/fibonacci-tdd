# ARQUITECTURA Y FLUJO DE LA APLICACIÓN

## 🏗️ Arquitectura de Capas

```
┌─────────────────────────────────────────┐
│          REST API Controllers           │
│  (UserController, CategoryController,   │
│     FibonacciController)                │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│        Servicios de Negocio             │
│  (UserService, CategoryService,         │
│     FibonacciRecordService)             │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│      Repositorios (Data Access)         │
│  (UserRepository, CategoryRepository,   │
│     FibonacciRecordRepository)          │
└──────────────────┬──────────────────────┘
                   │
┌──────────────────▼──────────────────────┐
│     Base de Datos PostgreSQL            │
│  (users, categories, fibonacci_records) │
└─────────────────────────────────────────┘
```

## 📊 Diagrama de Entidades

```
┌─────────────────────┐
│       User          │
├─────────────────────┤
│ id (PK)             │
│ name                │
│ email               │
└─────────────────────┘

┌─────────────────────┐
│     Category        │
├─────────────────────┤
│ id (PK)             │
│ category_name       │
└─────────────────────┘

┌─────────────────────┐
│  FibonacciRecord    │
├─────────────────────┤
│ id (PK)             │
│ input_n             │
│ result              │
└─────────────────────┘
```

## 🔄 Flujo de Solicitud HTTP

### Ejemplo: Crear un Usuario

```
1. CLIENT REQUEST
   POST /api/users
   Content-Type: application/json
   {
     "name": "John Doe",
     "email": "john@example.com"
   }

2. SPRING ROUTING
   ↓
   UserController.createUser(User user)

3. SERVICE LAYER
   ↓
   UserService.createUser(User user)
   - Validar name no vacío
   - Validar email no vacío
   - Llamar repository.save()

4. DATA ACCESS
   ↓
   UserRepository.save(User user)
   - INSERT INTO users (name, email) VALUES (...)

5. DATABASE
   ↓
   PostgreSQL crea registro

6. RESPONSE
   ↓
   201 Created
   {
     "id": 1,
     "name": "John Doe",
     "email": "john@example.com"
   }
```

## 🧪 Estrategia de Testing

### Unit Tests (Tests Unitarios)
- **Objetivo:** Probar métodos individuales
- **Alcance:** Servicios sin BD
- **Mocking:** Mock de repositorios
- **Ejemplo:** UserServiceUnitTest.shouldCreateUserSuccessfully()

### Integration Tests (Tests de Integración)
- **Objetivo:** Probar end-to-end
- **Alcance:** Controller + Service + Mock Repo
- **Base de datos:** H2 en memoria
- **Ejemplo:** UserControllerIntegrationTest.shouldCreateUserViaAPI()

### Acceptance Tests (Tests de Aceptación)
- **Objetivo:** Probar escenarios completos
- **Alcance:** API completa funcionando
- **Base de datos:** PostgreSQL real
- **Ejemplo:** APIAcceptanceTest.shouldCreateAndRetrieveUser()

## 🌍 Endpoints Disponibles

### Users (Gestión de Usuarios)
```
GET    /api/users              - Obtener todos
GET    /api/users/{id}         - Obtener por ID
POST   /api/users              - Crear (JSON)
PUT    /api/users/{id}         - Actualizar
DELETE /api/users/{id}         - Eliminar
```

### Categories (Gestión de Categorías)
```
GET    /api/categories         - Obtener todos
GET    /api/categories/{id}    - Obtener por ID
POST   /api/categories         - Crear (JSON)
PUT    /api/categories/{id}    - Actualizar
DELETE /api/categories/{id}    - Eliminar
```

### Fibonacci (Cálculos)
```
GET    /api/fibonacci/calculate/{n}     - Calcular Fibonacci(n)
GET    /api/fibonacci/records           - Obtener registros
GET    /api/fibonacci/records/{id}      - Obtener registro por ID
POST   /api/fibonacci/records           - Crear registro
DELETE /api/fibonacci/records/{id}      - Eliminar registro
```

## 📦 Estructura de Directorios

```
src/
├── main/
│   ├── java/eu/cifpfbmoll/
│   │   ├── App.java                    # Punto de entrada Spring Boot
│   │   ├── Fibonacci.java              # Lógica de algoritmo
│   │   ├── controller/                 # Layer de presentación
│   │   │   ├── UserController.java
│   │   │   ├── CategoryController.java
│   │   │   └── FibonacciController.java
│   │   ├── service/                    # Layer de lógica de negocio
│   │   │   ├── UserService.java
│   │   │   ├── CategoryService.java
│   │   │   └── FibonacciRecordService.java
│   │   ├── repository/                 # Layer de acceso a datos
│   │   │   ├── UserRepository.java
│   │   │   ├── CategoryRepository.java
│   │   │   └── FibonacciRecordRepository.java
│   │   └── model/                      # Entidades JPA
│   │       ├── User.java
│   │       ├── Category.java
│   │       └── FibonacciRecord.java
│   └── resources/
│       ├── application.properties      # Config por defecto
│       └── application-test.properties # Config para tests
└── test/
    └── java/eu/cifpfbmoll/
        ├── FibonacciUnitTest.java
        ├── model/
        │   └── EntityModelUnitTest.java
        ├── service/
        │   ├── UserServiceUnitTest.java
        │   ├── CategoryServiceUnitTest.java
        │   └── FibonacciRecordServiceUnitTest.java
        ├── controller/
        │   ├── UserControllerIntegrationTest.java
        │   └── FibonacciControllerIntegrationTest.java
        ├── acceptance/
        │   └── APIAcceptanceTest.java
        └── util/
            └── TestFixtures.java
```

## 🔐 Validaciones Implementadas

### UserService
- ✅ Name no puede estar vacío
- ✅ Email no puede estar vacío
- ✅ ID debe existir para actualizar/eliminar

### CategoryService
- ✅ CategoryName no puede estar vacío
- ✅ ID debe existir para actualizar/eliminar

### FibonacciRecordService
- ✅ InputN no puede ser negativo
- ✅ ID debe existir para eliminar

### Controllers
- ✅ Retornan 201 Created para POST
- ✅ Retornan 200 OK para GET/PUT
- ✅ Retornan 204 No Content para DELETE
- ✅ Retornan 400 Bad Request para validaciones
- ✅ Retornan 404 Not Found para recursos inexistentes

## 🚀 Pipeline CI/CD Completa

```
GitHub Push
    ↓
1. Checkout Code
    ↓
2. Setup Java 17
    ↓
3. Checkstyle (Validación de código)
    ↓
4. Unit Tests (Con H2 en memoria)
    ↓
5. Integration Tests (Con PostgreSQL)
    ↓
6. All Tests (Completo)
    ↓
7. Build Docker Image
    ↓
8. Acceptance Tests (En Docker)
    ↓
9. Upload Resultados
    ↓
✅ DONE / ❌ FAILED
```

## 🐳 Docker Compose Arquitectura

```
┌─────────────────────────────────────────┐
│        Docker Network: fibo-network     │
├─────────────────────────────────────────┤
│                                         │
│  ┌──────────────────────────────────┐  │
│  │  fibo_db (PostgreSQL 15)         │  │
│  │  Port: 5432                      │  │
│  │  Healthcheck: pg_isready         │  │
│  └──────────────────────────────────┘  │
│                                         │
│  ┌──────────────────────────────────┐  │
│  │  fibo_app (Spring Boot)          │  │
│  │  Port: 8080                      │  │
│  │  Depends on: db                  │  │
│  │  Connects to: fibo_db:5432       │  │
│  └──────────────────────────────────┘  │
│                                         │
└─────────────────────────────────────────┘
```

## 📝 Configuración de Profiles

### Development (default)
```
spring.datasource.url=jdbc:postgresql://localhost:5432/fibodb
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

### Test
```
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## 🔄 Flujo de Desarrollo

1. **Escribir test** → Fallará (Test-Driven)
2. **Implementar código** → Test pasará
3. **Refactorizar** → Mejorar código
4. **Validación de código** → Checkstyle
5. **Commit a Git** → Push a GitHub
6. **GitHub Actions** → Ejecuta tests automáticos
7. **Build Docker** → Crea imagen
8. **Deploy** → Sube a producción (opcional)

## 📊 Conteo de Tests

| Tipo | Count | Clases |
|------|-------|--------|
| Unit | 13 | FibonacciUnitTest, EntityModelUnitTest, UserServiceUnitTest, CategoryServiceUnitTest, FibonacciRecordServiceUnitTest |
| Integration | 7 | UserControllerIntegrationTest, FibonacciControllerIntegrationTest |
| Acceptance | 5 | APIAcceptanceTest |
| **TOTAL** | **~32** | **8 clases** |

---

**Arquitectura versión:** v1.0  
**Última actualización:** Febrero 2026
