# Product Catalog Microservice

A RESTful microservice built with Spring Boot and MongoDB for managing a product catalog. It supports CRUD operations, filtering, validation, logging, exception handling, and unit/integration tests.

## Features
- **CRUD Operations**: Create, read, update, and delete products.
- **Filtering**: Filter products by category, price range, and stock status.
- **Validation**: Input validation for product fields (e.g., name, price).
- **Logging**: Logs API requests and execution time using AOP.
- **Exception Handling**: Custom error responses for not-found and validation errors.
- **Testing**: Unit tests for service layer and integration tests for API endpoints.
- **Docker Support**: Containerized application for easy deployment.

## Tech Stack
- **Spring Boot**: 3.5.3
- **Java**: 17
- **MongoDB**: NoSQL database
- **MapStruct**: DTO mapping
- **Lombok**: Reduces boilerplate code
- **Mockito**: Unit testing
- **Maven**: Build tool
- **Docker**: Containerization

## Prerequisites
- **Java 17**
- **Maven**
- **MongoDB** (running locally on port 27017)
- **Docker** (for containerized deployment)
- **Postman** (optional, for API testing)

## Setup Instructions (Local)
1. **Clone the Repository**:
   ```bash
   git clone https://github.com/felixxplore/product-catalog.git
   cd product-catalog
   ```
2. **Build the Project**:
   ```bash
   mvn clean install
   ```
3. **Start MongoDB**:
   Ensure MongoDB is running locally:
   ```bash
   mongod
   ```
4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
   The app runs on `http://localhost:8080`.

## Setup Instructions (Docker)
1. **Build and run app + MongoDB together**:
   ```bash
   docker-compose up --build
   ```
2. **Access application**

| Service                  | URL                        |
| ------------------------ |----------------------------|
| Spring Boot App          | [http://localhost:8080]    |
| MongoDB (via Compass)    | `mongodb://localhost:27017` |
---

## API Endpoints
- ### **Note : For API Testing use POSTMAN for better user experience**


- **Create Product**:
  ```bash
  curl -X POST http://localhost:8080/products 
  -H "Content-Type: application/json" 
  -d '{"name":"Laptop","price":1000.0,"category":"Electronics","inStock":true}'
  ```
  Response: 201 Created, JSON with product details.


- **Get All Products (with Filters)**:
  ```bash
  curl http://localhost:8080/products?category=Electronics&minPrice=500.0&maxPrice=1500.0&inStock=true
  ```
  Response: 200 OK, JSON array of products.


- **Get Product by ID**:
  ```bash
  curl http://localhost:8080/products/<product-id>
  ```
  Response: 200 OK, JSON with product details.


- **Update Product**:
  ```bash
  curl -X PUT http://localhost:8080/products/<product-id> 
  -H "Content-Type: application/json" 
  -d '{"name":"Updated Laptop","price":1200.0,"category":"Electronics","inStock":false}'
  ```
  Response: 200 OK, updated JSON.


- **Delete Product**:
  ```bash
  curl -X DELETE http://localhost:8080/products/<product-id>
  ```
  Response: 204 No Content.



## MongoDB Configuration
- **Database**: `catalogdb`
- **Collection**: `products`
- **URI**: `mongodb://localhost:27017/catalogdb`

## Testing
- **Unit Tests**: Run with:
  ```bash
  mvn test
  ```
  Tests cover the service layer using Mockito.
- **Integration Tests**: Test API endpoints with a running MongoDB instance.
- **Postman**: Import the collection from the repository or manually test endpoints.

## Project Structure
```
com.example.catalog
├── controller        # REST controllers
├── service           # Business logic
├── repository        # MongoDB repository
├── dto               # Data Transfer Objects
├── model             # MongoDB entities
├── config            # AOP logging
├── exception         # Custom exceptions
```
 