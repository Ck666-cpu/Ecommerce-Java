# 🛒 Spring Boot E-commerce Backend

A robust backend REST API for an e-commerce platform built with **Java 17** and **Spring Boot**. This application handles user management, product inventory, and order processing, secured with **JWT (JSON Web Token)** authentication.

## 🚀 Features

* **User Management:** Registration and Login with Role-Based Access Control (Customer/Admin).
* **Product Catalog:** CRUD operations for managing products and inventory.
* **Order Processing:** Secure order placement with automatic stock deduction.
* **Security:** Stateless authentication using JWT and BCrypt password hashing.
* **Database:** H2 In-Memory database for rapid development and testing.
* **Error Handling:** Global exception handling for standardized API responses.

## 🛠️ Tech Stack

* **Language:** Java 17
* **Framework:** Spring Boot 3.5.9
* **Build Tool:** Maven (Wrapper included)
* **Database:** H2 (In-Memory)
* **Security:** Spring Security, JJWT (0.11.5)
* **Persistence:** Spring Data JPA, Hibernate

## 📂 Project Structure

```text
src/main/java/com/example/Ecommerce/
├── controller/       # REST API Endpoints (Product, User, Order)
├── dto/              # Data Transfer Objects (LoginRequest, OrderRequest)
├── exception/        # Global Exception Handlers
├── model/            # JPA Entities (User, Product, Order, OrderItem)
├── repository/       # Database Access Interfaces
├── security/         # JWT Filters, Utils, and Security Configuration
└── service/          # Business Logic

```

## ⚙️ Configuration & Setup

### Prerequisites

* JDK 17 or higher
* Maven (optional, as `mvnw` is provided)

### 1. Clone & Build

```bash
git clone [https://github.com/Ck666-cpu/Ecommerce-Java]
cd ecommerce-java
./mvnw clean install

```

### 2. Run the Application

The application is configured to run on port **8081** to avoid conflicts with other common services.

```bash
./mvnw spring-boot:run

```

### 3. Database Access (H2 Console)

Since the app uses an in-memory database, you can access the raw data console while the app is running:

* **URL:** `http://localhost:8081/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **Username:** `sa`
* **Password:** *(leave blank)*

## 🔌 API Endpoints

### 👤 User Authentication

| Method | Endpoint | Description | Auth Required |
| --- | --- | --- | --- |
| `POST` | `/api/users/register` | Register a new user | ❌ No |
| `POST` | `/api/users/login` | Login to receive a JWT Token | ❌ No |
| `GET` | `/api/users/{id}` | Get user details | ✅ Yes |

### 📦 Products

| Method | Endpoint | Description | Auth Required |
| --- | --- | --- | --- |
| `GET` | `/api/products` | List all products | ✅ Yes |
| `GET` | `/api/products/{id}` | Get specific product details | ✅ Yes |
| `POST` | `/api/products` | Create a new product | ✅ Yes |

### 🛒 Orders

| Method | Endpoint | Description | Auth Required |
| --- | --- | --- | --- |
| `POST` | `/api/orders` | Place a new order | ✅ Yes |
| `GET` | `/api/orders/myorders` | Get current user's history | ✅ Yes |

> **Note:** For endpoints requiring Auth, add the header: `Authorization: Bearer <your_jwt_token>`

## 🛡️ Security Details

* **JWT Secret:** Currently hardcoded in `JwtUtil.java` for development purposes. For production, move this to an environment variable.
* **Password Encryption:** User passwords are hashed using `BCryptPasswordEncoder` before storage.
* **Stock Management:** The `OrderService` automatically checks and decrements product stock quantity upon successful order placement (`@Transactional`).

## 🧪 Running Tests

The project includes JUnit 5 tests. Run them using:

```bash
./mvnw test

```

## 📝 License

This project is open-source and available for educational and development purposes.
