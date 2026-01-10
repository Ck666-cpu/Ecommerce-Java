# 🛒 Java Spring Boot E-Commerce Platform

A full-stack ready E-commerce backend built with **Spring Boot**, featuring secure authentication, product management, shopping cart functionality, and order processing.

## 🚀 Key Features

### 👤 User Management
* **Authentication:** Secure Login & Registration using **JWT (JSON Web Tokens)**.
* **Role-Based Access:** Distinct permissions for **ADMIN** (Inventory) vs **CUSTOMER** (Shopping).

### 📦 Product & Inventory
* **Catalog:** Browse products with **Pagination** and **Sorting**.
* **Search:** Filter products by name.
* **Categories:** Organize products into specific categories (e.g., Electronics, Books).
* **Reviews:** Customers can leave star ratings and comments on products.

### 🛒 Shopping Experience
* **Persistent Cart:** Users have a dedicated shopping cart managed in the database.
* **Inventory Checks:** Automatic stock validation prevents overselling.
* **Checkout:** Seamless conversion of Cart items into a finalized Order.
* **Order History:** Customers can view their past purchases.

---

## 🛠️ Tech Stack

* **Core:** Java 17+, Spring Boot 3.x
* **Database:** H2 In-Memory (Dev) / PostgreSQL (Prod ready)
* **Security:** Spring Security, BCrypt, JJWT
* **Persistence:** Spring Data JPA, Hibernate
* **Build Tool:** Maven

---

## ⚙️ Setup & Installation

### 1. Prerequisites
* Java JDK 17 or higher
* Maven (Optional, wrapper included)
* (Optional) Node.js if running the frontend

### 2. Clone the Repository
```bash
git clone [https://github.com/your-username/ecommerce-java.git](https://github.com/your-username/ecommerce-java.git)
cd ecommerce-java

```

### 3. Database Configuration

The project is currently configured to use the **H2 In-Memory Database** for instant setup.

* **File:** `src/main/resources/application.properties`
* **Console URL:** `http://localhost:8081/h2-console`
* **JDBC URL:** `jdbc:h2:mem:testdb`
* **User/Pass:** `sa` / *(blank)*

*(To switch to PostgreSQL, update the `spring.datasource` properties in `application.properties`)*.

### 4. Run the Application

```bash
# Windows
./mvnw.cmd spring-boot:run

# Mac/Linux
./mvnw spring-boot:run

```

The API will start at: `http://localhost:8081`

---

## 🔌 API Reference

### 🔓 Public Endpoints

| Method | URL | Description |
| --- | --- | --- |
| `POST` | `/api/users/register` | Register a new account |
| `POST` | `/api/users/login` | Login to receive JWT |
| `GET` | `/api/products` | List products (Supports `?page=0&size=10&sortBy=price`) |
| `GET` | `/api/products/search` | Search (`?query=Laptop`) |
| `GET` | `/api/products/{id}` | Get product details |
| `GET` | `/api/categories` | List all categories |
| `GET` | `/api/reviews/{pid}` | View reviews for a product |

### 🔐 Customer Endpoints (Headers: `Authorization: Bearer <token>`)

| Method | URL | Description |
| --- | --- | --- |
| `GET` | `/api/cart` | View current shopping cart |
| `POST` | `/api/cart/add` | Add item (`?productId=1&quantity=1`) |
| `DELETE` | `/api/cart/remove/{id}` | Remove item from cart |
| `POST` | `/api/orders/checkout` | Place order from cart |
| `GET` | `/api/orders/myorders` | View order history |
| `POST` | `/api/reviews/add` | Add a review for a product |

### 🛡️ Admin Endpoints

| Method | URL | Description |
| --- | --- | --- |
| `POST` | `/api/products` | Create new product |
| `PUT` | `/api/products/{id}` | Update product |
| `DELETE` | `/api/products/{id}` | Delete product |
| `POST` | `/api/categories` | Create new category |

---

## 🧪 Testing

### Postman

1. **Login** as a user to get a Token.
2. Set the token in the **Authorization** tab (Type: Bearer Token).
3. Test endpoints like `GET /api/cart` or `POST /api/orders/checkout`.

### JUnit

Run the automated test suite to verify the logic:

```bash
./mvnw test

```

---

## 🔮 Future Roadmap

* [ ] React.js Frontend Integration
* [ ] Image Uploads (AWS S3 / Local Storage)
* [ ] Payment Gateway Integration (Stripe)
* [ ] Email Notifications for Orders
