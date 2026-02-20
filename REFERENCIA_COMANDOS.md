# 📚 REFERENCIA RÁPIDA DE COMANDOS

## 🚀 INICIO RÁPIDO

```bash
# Navegar al proyecto
cd c:\Users\ruben\OneDrive\Documents\Projects\fibonacci-tdd

# Iniciar Docker Compose
docker-compose up -d

# Esperar ~30 segundos a que levante

# Probar la aplicación
curl http://localhost:8080/api/users
```

## 🧪 TESTING

```bash
# Ejecutar todos los tests
mvn clean test

# Tests unitarios
mvn test -Dtest=*UnitTest

# Tests de integración
mvn test -Dtest=*IntegrationTest

# Tests de aceptación
mvn test -Dtest=*AcceptanceTest

# Test específico
mvn test -Dtest=FibonacciUnitTest

# Con output verbose
mvn test -X

# Test específico
mvn test -Dtest=FibonacciUnitTest#shouldCalculateFibonacciOfZero
```

## 📊 VALIDACIÓN DE CÓDIGO

```bash
# Checkstyle
mvn checkstyle:check

# Checkstyle con reporte
mvn checkstyle:checkstyle

# Ver reporte HTML
start target/site/checkstyle.html
```

## 📈 REPORTES

```bash
# Surefire Report
mvn surefire-report:report

# Ver reporte
start target/site/surefire-report.html

# Coverage (si está configurado)
mvn test jacoco:report
start target/site/jacoco/index.html
```

## 🏗️ BUILD

```bash
# Build local
mvn clean install

# Build saltando tests
mvn clean install -DskipTests

# Package
mvn clean package

# Package con tests
mvn clean package -DskipTests

# Ver dependencias
mvn dependency:tree
```

## 🐳 DOCKER

```bash
# Build imagen
docker build -t fibonacci-tdd:latest .

# Build sin cache
docker build --no-cache -t fibonacci-tdd:latest .

# Ver imágenes
docker images

# Run container
docker run -p 8080:8080 fibonacci-tdd:latest

# Ver contenedores
docker ps

# Ver logs
docker logs fibo_app
docker logs fibo_db

# Logs en tiempo real
docker logs -f fibo_app

# Entrar a contenedor
docker exec -it fibo_app bash
docker exec -it fibo_db psql -U admin -d fibodb

# Detener todo
docker-compose down

# Detener y limpiar
docker-compose down -v

# Reiniciar
docker-compose restart
docker-compose restart app
```

## 🔧 CONFIGURACIÓN

```bash
# Ver variables de entorno
cat .env

# Cambiar configuración
# Editar .env y luego:
docker-compose down -v
docker-compose up -d
```

## 💾 BASE DE DATOS

```bash
# Conectarse a PostgreSQL
psql -h localhost -U admin -d fibodb

# Dentro de psql:
\dt                           # Ver tablas
\d users                      # Ver esquema de users
SELECT * FROM users;          # Ver registros
SELECT COUNT(*) FROM users;   # Contar registros
\q                            # Salir

# Con docker
docker exec -it fibo_db psql -U admin -d fibodb

# Limpiar base de datos
docker exec -it fibo_db psql -U admin -d fibodb -c "DROP TABLE IF EXISTS fibonacci_records CASCADE;"
docker exec -it fibo_db psql -U admin -d fibodb -c "DROP TABLE IF EXISTS categories CASCADE;"
docker exec -it fibo_db psql -U admin -d fibodb -c "DROP TABLE IF EXISTS users CASCADE;"
```

## 🔗 ENDPOINTS CURL

```bash
# ===== USERS =====

# Listar todos
curl http://localhost:8080/api/users

# Obtener por ID
curl http://localhost:8080/api/users/1

# Crear
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John Doe","email":"john@example.com"}'

# Actualizar
curl -X PUT http://localhost:8080/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane Doe","email":"jane@example.com"}'

# Eliminar
curl -X DELETE http://localhost:8080/api/users/1

# ===== CATEGORIES =====

# Listar todos
curl http://localhost:8080/api/categories

# Crear
curl -X POST http://localhost:8080/api/categories \
  -H "Content-Type: application/json" \
  -d '{"categoryName":"Mathematics"}'

# ===== FIBONACCI =====

# Calcular Fibonacci
curl http://localhost:8080/api/fibonacci/calculate/10

# Listar registros
curl http://localhost:8080/api/fibonacci/records

# Crear registro
curl -X POST http://localhost:8080/api/fibonacci/records \
  -H "Content-Type: application/json" \
  -d '{"n":12}'
```

## 📝 POSTMAN

```bash
# Importar colección
1. Abrir Postman
2. Click en "Import"
3. Seleccionar postman_collection.json
4. Click en "Import"

# O via CLI:
postman runtime cli postman_collection.json
```

## 🔍 DEBUGGING

```bash
# Maven debug
mvn -e clean test

# Java debug port 5005
mvn clean test -Dmaven.surefire.debug

# Spring Boot debug
mvn spring-boot:run -Dspring-boot.run.arguments="--debug"

# Ver propiedades activas
curl http://localhost:8080/actuator/env 2>/dev/null | jq
```

## 📋 INFORMACIÓN ÚTIL

```bash
# Ver versiones
mvn -v
docker --version
docker-compose --version
java -version

# Ubicación del proyecto
pwd
echo %cd%

# Variables de entorno
echo $JAVA_HOME
echo %JAVA_HOME%

# Puertos en uso
netstat -ano | findstr :8080
netstat -ano | findstr :5432

# Procesos Java
jps
jps -l
```

## 🔄 FLUJO TÍPICO DE DESARROLLO

```bash
# 1. Editar código
code src/main/java/...

# 2. Ejecutar tests
mvn clean test

# 3. Validar código
mvn checkstyle:check

# 4. Build
mvn clean package

# 5. Build Docker
docker build -t fibonacci-tdd:latest .

# 6. Test Docker
docker run -p 8080:8080 fibonacci-tdd:latest

# 7. Git (si aplica)
git add .
git commit -m "Descripción del cambio"
git push origin main
```

## 🎯 VERIFICACIÓN RÁPIDA

```bash
# ¿Está Docker corriendo?
docker ps

# ¿Está la API respondiendo?
curl -s http://localhost:8080/api/users | jq

# ¿Está la BD conectada?
curl -s http://localhost:8080/api/users | jq length

# ¿Pasan los tests?
mvn test -q

# ¿Está todo bien?
mvn clean verify

# Estado del proyecto
echo "=== Docker ===" && docker-compose ps && \
echo "=== Tests ===" && mvn test -q && \
echo "=== API ===" && curl -s http://localhost:8080/api/users | jq length
```

## 📚 DOCUMENTOS IMPORTANTES

```bash
# Leer documentación
README.md                    # Documentación completa
QUICK_START.md              # Guía rápida
ARQUITECTURA.md             # Detalles técnicos
CAMBIOS_REALIZADOS.md       # Cambios implementados
VERIFICACION_COMPLETA.md    # Checklist
REFERENCIA_COMANDOS.md      # Este archivo
```

## 🔑 CREDENCIALES

```
PostgreSQL:
  Host: localhost
  Port: 5432
  Usuario: admin
  Contraseña: password123
  Base de datos: fibodb

Spring Boot:
  URL: http://localhost:8080
  Profile: default (development)

Para tests:
  Base de datos: H2 (en memoria)
  Profile: test
```

## 🆘 TROUBLESHOOTING RÁPIDO

```bash
# Problema: Port 5432 en uso
# Solución:
docker-compose down -v
# Editar .env cambiar POSTGRES_PORT
docker-compose up -d

# Problema: Tests fallando
# Solución:
mvn clean test -X

# Problema: Docker no build
# Solución:
docker build --no-cache -t fibonacci-tdd:latest .

# Problema: BD corrupta
# Solución:
docker-compose down -v
docker-compose up -d

# Problema: API no responde
# Solución:
docker logs fibo_app
docker-compose restart app
```

---

**Última actualización:** Febrero 2026  
**Proyecto:** fibonacci-tdd  
**Versión:** 1.0
