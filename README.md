# Journal App (Spring Boot)

A simple **Spring Boot Journal Application** built with **Java 17** and **MongoDB**. This project provides REST APIs for managing users and their journal entries. It is designed as a backend service and follows a clean layered architecture (Controller → Service → Repository).

---

## 🚀 Features

* Spring Boot 3.x
* RESTful APIs
* MongoDB integration (Spring Data MongoDB)
* User management
* Journal entry management
* Health check endpoint
* Maven-based project
* Lombok for boilerplate reduction

---

## 🛠 Tech Stack

* **Java:** 17
* **Framework:** Spring Boot
* **Database:** MongoDB
* **Build Tool:** Maven
* **Dependencies:**

  * spring-boot-starter-web
  * spring-boot-starter-data-mongodb
  * spring-boot-starter-actuator
  * Lombok

---

## 📂 Project Structure

```
journalApp
├── src/main/java/com/nazrulislam/journalApp
│   ├── Controller
│   │   ├── HealthCheck.java
│   │   ├── JournalEntryController.java
│   │   └── UserController.java
│   ├── Service
│   ├── Repository
│   │   ├── JournalRepository.java
│   │   └── UserRepository.java
│   ├── entity
│   │   ├── JournalEntry.java
│   │   └── User.java
│   └── JournalApplication.java
├── pom.xml
└── README.md
```

---

## ⚙️ Prerequisites

Make sure you have the following installed:

* Java 17+
* Maven
* MongoDB (running locally or via cloud, e.g. MongoDB Atlas)

---

## ▶️ Running the Application

1. **Clone the repository**

   ```bash
   git clone <repository-url>
   cd journalApp
   ```

2. **Configure MongoDB**

   Update your `application.properties` or `application.yml` with MongoDB configuration:

   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/journaldb
   ```

3. **Run the application**

   ```bash
   mvn spring-boot:run
   ```

4. **Application will start at**

   ```
   http://localhost:8080
   ```

---

## 🔗 API Endpoints (Overview)

### Health Check

* `GET /health`

### Users

* `POST /users`
* `GET /users`
* `GET /users/{id}`

### Journal Entries

* `POST /journals`
* `GET /journals`
* `GET /journals/{id}`
* `DELETE /journals/{id}`

*(Exact paths may vary based on controller mappings)*

---

## 🧪 Testing

Run tests using:

```bash
mvn test
```

---

## 📌 Notes

* This project is intended for learning and backend practice.
* Security and authentication are not implemented yet.
* Can be extended with Spring Security, JWT, and encryption (E2EE).

---

## 👤 Author

**Nazrul Islam**
Java & Spring Boot Learner

---

## 📄 License

This project is open-source and available for educational purposes.
