# Arrowstack-Internship
# B2B Order Management System

A full-stack B2B Order Management Platform developed for the Arrowstack Full Stack Development Internship.

## Technology Stack
- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA
- **Frontend:** Angular (Standalone), TypeScript, HTML5, CSS3
- **Database:** Relational Database (MySQL / PostgreSQL)
- **API Testing:** Postman

## Architecture Decisions
- **Transactional Safety:** Used Spring's `@Transactional` in the order processing workflow so that inventory deduction, order creation, cart clearing, and invoice generation execute atomically or roll back completely on failure.
- **Role-Based Security:** Implemented Spring Security with Basic Authentication and CORS configuration to separate `ADMIN` and `CUSTOMER` endpoints and dashboards.
- **Type-Safe Frontend:** Built with Angular Standalone components and TypeScript interfaces to ensure predictable data binding and real-time UI updates.

## Core Features
- **Admin Dashboard:** Add new products, define pricing, and manage available inventory stock.
- **Customer Dashboard:** Browse available products, add items to cart, place orders, and view order and invoice history.
- **Automated Invoice Pipeline:** Automatically generates an invoice upon successful order placement and clears the user's cart.
- **Validation and Error Handling:** Handles insufficient stock and duplicate invoice attempts with structured `400 Bad Request` error responses.

## Repository Structure
```text
Arrowstack-B2B-Order-Management/
├── Backend/b2b-order-management/
├── Frontend/order-app-ui/
├── Documentation/
├── postman/
└── README.md
```

## Setup Instructions

### Backend
1. Navigate to `Backend/b2b-order-management`.
2. Configure database credentials in `src/main/resources/application.properties`.
3. Run the Spring Boot application on `http://localhost:8080`.

### Frontend
1. Navigate to `Frontend/order-app-ui`.
2. Run `npm install` to install dependencies.
3. Run `ng serve` to start the application on `http://localhost:4200`.

## Validation Scenarios
- Role-based login redirection for Admin and Customer users.
- Real-time stock reduction upon order placement.
- Automatic invoice creation linked to placed orders.
- Error handling when ordered quantity exceeds available stock.
