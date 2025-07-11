# 💸 Wallet Application

A secure wallet service built with Spring Boot (v2.7) and Java 8, using H2 file-based storage. This project provides a simple system where users can create wallets, check balances, recharge, and withdraw — all protected with Basic Authentication.

---

## 🧩 Features

- ✅ User Registration (with secure password encryption)
- 🔐 Basic Authentication for accessing wallet operations
- 💰 Wallet operations: Check Balance, Recharge, Withdraw
- 🛡️ Handles Race Conditions using `@Version` + `@Transactional`
- ❌ Prevents Overdrafts and Negative Recharges/Withdrawals
- 📜 Transaction History Support (Pluggable)
- 🧪 Postman Collection Included for Testing

---

## 🚀 Tech Stack

- **Spring Boot 2.7**
- **Java 8**
- **Spring Data JPA**
- **Spring Security (Basic Auth)**
- **H2 File-based Database**
- **Maven**

---

## 📁 Project Structure

```
wallet-application/
├── src/
│   ├── controller/
│   ├── dto/
│   ├── entity/
│   ├── repository/
│   └── service/
├── postman/
│   └── wallet-collection.json
├── application.properties
├── README.md
└── pom.xml
```

---

## 🧪 API Testing (Postman)

A Postman Collection is available in the `postman/` directory:

📥 [Download Wallet Postman Collection](postman/wallet-collection.json)

To test:
1. Import the collection in Postman.
2. Use **Basic Auth** → Username & Password of registered user.
3. Test `/wallet/balance`, `/wallet/recharge`, and `/wallet/withdraw` endpoints.

---

## 🔐 Authentication

- Basic Auth is used to protect wallet endpoints.
- Every request requires valid `username:password`.
- Only the owner of a wallet can operate on it.

---

## 🧮 Race Condition Handling

- Optimistic Locking via `@Version` field in `Wallet` entity.
- `@Transactional` ensures atomicity for concurrent operations.
- Concurrency tested with parallel threads in JUnit.

---

## 🧾 Sample API Usage

### 🔐 Register User

```http
POST /user/register
Content-Type: application/json

{
  "username": "Priyanshu",
  "password": "khungar123#"
}
```

### 💵 Recharge Wallet

```http
POST /wallet/recharge?amount=100.00
Authorization: Basic Priyanshu:khungar123#
```

### 🏧 Withdraw

```http
POST /wallet/withdraw?amount=50.00
Authorization: Basic Priyanshu:khungar123#
```

### 📊 Check Balance

```http
GET /wallet/balance
Authorization: Basic Priyanshu:khungar123#
```

---

## 🛠 Run the App

```bash
# From project root
mvn clean install
mvn spring-boot:run
```

App runs at: `http://localhost:8080`

---

## 🗃️ H2 File Database

Stored in your local filesystem, configured via `application.properties`:

```properties
spring.datasource.url=jdbc:h2:file:./wallet-db
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
```

---

## 📌 TODO / Future Enhancements

- Add JWT authentication
- Add transaction history APIs
- Add email notifications
- Add admin dashboard

---

## 📃 License

This project is for educational and technical assessment purposes only.