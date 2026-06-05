# MiniKintsugi

MiniKintsugi is a cloud-hosted transaction risk assessment platform that simulates a simplified fintech workflow. The application allows users to create, review, approve, reject, and delete transactions while automatically calculating a risk score based on predefined business rules.

## Live Demo

https://minikintsugi-production.up.railway.app

## Features

* Create Transactions
* View Transactions
* Approve Transactions
* Reject Transactions
* Delete Transactions
* Automated Risk Scoring
* PostgreSQL Persistence
* Cloud Deployment
* RESTful API Architecture

## Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate

### Database

* PostgreSQL
* Neon

### Frontend

* HTML
* CSS
* JavaScript

### Deployment

* Railway
* GitHub

## Architecture

Frontend (HTML/CSS/JavaScript)

↓

Spring Boot REST API

↓

Service Layer

↓

JPA / Hibernate

↓

PostgreSQL (Neon)

## API Endpoints

### Create Transaction

POST /transactions

### Get All Transactions

GET /transactions

### Get Transaction By ID

GET /transactions/{id}

### Update Transaction

PUT /transactions/{id}

### Approve Transaction

PUT /transactions/approve/{id}

### Reject Transaction

PUT /transactions/reject/{id}

### Delete Transaction

DELETE /transactions/{id}

## Risk Scoring Logic

Transactions are evaluated using a simple rule-based risk engine.

* High-risk transactions are marked as UNDER_REVIEW
* Low-risk transactions are automatically APPROVED

## Local Setup

Clone the repository:

```bash
git clone https://github.com/akamreez/MiniKintsugi.git
```

Move into the project:

```bash
cd MiniKintsugi
```

Configure database properties in `application.properties`.

Run the application:

```bash
./mvnw spring-boot:run
```

Open:

```text
http://localhost:8080
```

## Software Engineering Practices

This project was developed using industry-standard Git workflows:

* Forking
* Feature Branches
* Pull Requests
* Code Reviews
* Merge Workflows

## Future Enhancements

* Transaction Search and Filtering
* Dashboard Metrics
* Spring Security Authentication
* Docker Containerization
* GitHub Actions CI/CD Pipeline

## Author

Amreez Khan
