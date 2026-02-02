---

# Microservices E-Commerce Backend (Spring Boot + API Gateway + Keycloak)

A production-style microservices backend built using **Spring Boot**, **Spring Cloud**, and **Docker**, featuring centralized authentication with **Keycloak**, API routing through **Spring Cloud Gateway (MVC)**, inter-service communication using **OpenFeign**, database migrations with **Flyway**, and API documentation using **OpenAPI (Swagger)**.

---

## Architecture Overview

```
                        Client
                          ↓
  API Gateway (Spring Cloud Gateway MVC + Keycloak Security)
                          ↓
-----------------------------------------------------------
  | Product Service  | Order Service | Inventory Service |
-----------------------------------------------------------
        ↓                  ↓                  ↓
     MongoDB           MySQL DB           MySQL DB
```

---

## Services

| Service               | Description                                             |
| --------------------- | ------------------------------------------------------- |
| **API Gateway**       | Central entry point, routing, JWT validation (Keycloak) |
| **Product Service**   | Product management APIs                                 |
| **Order Service**     | Order creation & processing                             |
| **Inventory Service** | Stock availability & updates                            |

---

## Tech Stack

### Backend

* Java 21
* Spring Boot 3
* Spring Cloud Gateway (WebMvc.fn)
* Spring Security OAuth2 Resource Server
* OpenFeign (sync service communication)
* JPA + Hibernate

### Database

* MySQL
* Flyway Migration
* MongoDB

### DevOps

* Docker & Docker Compose

### Auth

* Keycloak (JWT based OAuth2)

### Documentation

* OpenAPI / Swagger UI

---

##  Security Flow (Keycloak)

1. User authenticates with Keycloak
2. Receives JWT token
3. Sends token to API Gateway
4. Gateway validates token
5. Requests forwarded to services

All endpoints are protected by default.

---

##  Inter-Service Communication

Order Service → Inventory Service uses **Spring Cloud OpenFeign** for synchronous REST calls.

No manual HTTP handling required.

---

##  API Documentation

Each service exposes Swagger UI:

```
http://localhost:<service-port>/swagger-ui.html
```
---

##  Database Migration (Flyway)

Each service manages schema using versioned SQL files:

```
db/migration/V1__init.sql
db/migration/V2__add_inventory.sql
```

---

##  Running with Docker

Each service contains its own `docker-compose.yml` with MySQL.

### Example:

```bash
docker compose up -d
```

Run for:

* inventory-service
* order-service
* product-service

Then start API Gateway.

---

##  Typical Ports (example)

| Service           | Port |
| ----------------- | ---- |
| API Gateway       | 9000 |
| Product Service   | 8080 |
| Order Service     | 8081 |
| Inventory Service | 8082 |
| Keycloak          | 8181 |
| MySQL             | 3306 |

---

##  Project Structure (per service)

```
service-name/
 ┣ controller/
 ┣ service/
 ┣ repository/
 ┣ model/
 ┣ dto/
 ┣ config/
 ┣ db/migration/
 ┣ docker-compose.yml
```

---
