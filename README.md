# 📚 Library Management REST API

A simple RESTful API built with Java 17 and Spring Boot to manage a basic library system. This API allows registering borrowers and books, listing books, and borrowing/returning books.

---

## 🚀 Features

- Register new borrowers and books
- Borrow and return books
- Prevent duplicate borrowing of the same book copy
- Multiple copies of the same ISBN allowed
- Data validation and error handling
- Environment configuration support (`dev`, `prod`, etc.)
- Unit tests for core business logic

---

## 🛠️ Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 (Dev) / PostgreSQL (Prod)
- Maven
- JUnit 5 + Mockito
- Docker (optional)
- RESTful architecture

---

## 📦 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven 3.8+
- (Optional) Docker

### 📥 Clone the Project


```bash
git clone https://github.com/pubudu008/library-api.git
cd library-api
````

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

```bash
mvn clean install
java -jar target/library-api-0.0.1-SNAPSHOT.jar
```

```bash
mvn test
```
➕ Register a Borrower

POST /api/borrowers
Content-Type: application/json

Request Body:
{
"name": "Alice",
"email": "alice@example.com"
}

➕ Register a Book
POST /api/books
Content-Type: application/json

Request Body:
{
"isbn": "9780134685991",
"title": "Effective Java",
"author": "Joshua Bloch"
}

📚 Get All Books
GET /api/books

📖 Borrow a Book
POST /api/borrowers/{borrowerId}/borrow/{bookId}

📤 Return a Book

POST /api/borrowers/{borrowerId}/return/{bookId}


🧾 Assumptions
Each book copy is uniquely identified by its bookId

Multiple books with the same ISBN can be added (representing physical copies)

ISBN must always match the same title/author combination

A book can only be borrowed by one user at a time


🐳 Docker Support

Build the Docker image:

```bash
docker build -t library-api .
```

Run the container:
```bash
docker run -p 8080:8080 library-api
```

📌 Future Improvements
Add pagination to book listings
Add due dates and overdue tracking
Add authentication (JWT or Basic Auth)
Add Swagger/OpenAPI documentation

👨‍💻 Author
Pubudu Perera

Email: pubudusandaruwan008@gmail.com
GitHub: github.com/pubudu008
