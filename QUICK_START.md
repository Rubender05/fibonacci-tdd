# GUÍA RÁPIDA - Fibonacci TDD CI/CD

## 🚀 INICIO RÁPIDO

### 1. Iniciar la aplicación con Docker
```bash
cd c:\Users\ruben\OneDrive\Documents\Projects\fibonacci-tdd
docker-compose up -d
```

### 2. Acceder a la aplicación
- **API REST:** http://localhost:8080
- **Base de Datos:** localhost:5432

### 3. Probar un endpoint
```bash
# Calcular Fibonacci(10)
curl http://localhost:8080/api/fibonacci/calculate/10

# Crear usuario
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Test User", "email":"test@example.com"}'

# Listar todos los usuarios
curl http://localhost:8080/api/users
```

## 🧪 EJECUTAR TESTS

### Todos los tests
```bash
mvn clean test
```

### Solo unitarios
```bash
mvn test -Dtest=*UnitTest
```

### Solo integración
```bash
mvn test -Dtest=*IntegrationTest
```

### Solo aceptación
```bash
mvn test -Dtest=*AcceptanceTest
```

### Con reporte HTML
```bash
mvn surefire-report:report
# Ver en: target/site/surefire-report.html
```

## 📊 VALIDACIÓN DE CÓDIGO

### Checkstyle
```bash
mvn checkstyle:check
mvn checkstyle:checkstyle
# Ver en: target/site/checkstyle.html
```

## 🐳 DOCKER

### Construir imagen
```bash
docker build -t fibonacci-tdd:latest .
```

### Ver logs
```bash
docker-compose logs app
docker-compose logs db
```

### Detener y limpiar
```bash
docker-compose down          # Detener
docker-compose down -v       # Detener y eliminar volúmenes
```

## 📝 POSTMAN

### Importar colección
1. Abrir Postman
2. Clic en "Import"
3. Seleccionar `postman_collection.json`
4. Hacer clic en "Import"

### Usar la colección
- Todos los endpoints preconfigurados
- Base URL: http://localhost:8080
- Ejemplos de payloads listos

## 🔄 GITHUB ACTIONS

### Ver pipeline
1. Ir a GitHub: https://github.com/usuario/fibonacci-tdd
2. Hacer clic en "Actions"
3. Ver los resultados de los commits

### Pipeline ejecuta:
- ✅ Checkstyle (validación de código)
- ✅ Unit Tests
- ✅ Integration Tests
- ✅ Build Docker
- ✅ Acceptance Tests

## 📋 ESTRUCTURA DE TESTS

### Unit Tests (13 tests)
- `FibonacciUnitTest.java` - Tests de la lógica Fibonacci
- `EntityModelUnitTest.java` - Tests de modelos
- `*ServiceUnitTest.java` - Tests de servicios

### Integration Tests (7 tests)
- `*ControllerIntegrationTest.java` - Tests REST

### Acceptance Tests (5 tests)
- `APIAcceptanceTest.java` - Scenarios completos

## 🛠️ TROUBLESHOOTING

### Error: Puerto 5432 en uso
```bash
docker-compose down -v
# O cambiar puerto en .env:
# POSTGRES_PORT=5433
```

### Error: Conexión a BD fallida
```bash
docker-compose ps
docker-compose logs db
```

### Error: Tests fallando
```bash
mvn clean test -X  # Modo verbose
```

### Error: Docker build fallando
```bash
docker build --no-cache -t fibonacci-tdd:latest .
```

## 📚 RECURSOS

- README.md - Documentación completa
- pom.xml - Dependencias y configuración
- .github/workflows/ci.yml - Pipeline CI/CD
- checkstyle.xml - Reglas de calidad
- postman_collection.json - Colección de endpoints

## ✨ PUNTOS CLAVE

✅ **3 tablas:** User, Category, FibonacciRecord  
✅ **~30 tests:** Unitarios + Integración + Aceptación  
✅ **API REST:** Endpoints JSON  
✅ **Docker:** Compose con PostgreSQL  
✅ **CI/CD:** GitHub Actions automatizado  
✅ **Código:** Validado con Checkstyle  
✅ **Fixtures:** JUnit automáticos  

---

**Última actualización:** Febrero 2026
