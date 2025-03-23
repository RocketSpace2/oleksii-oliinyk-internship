# Airline System

## Technologies, Concepts, and Skills

The whole project was made using **Spring Boot** and I applied **Domain-Driven Design** principles and implemented a **microservices architecture** throughout the project. Overall, this system was made to demonstrate my knowledge in creating and managing **RESTful APIs** and working with relational databases using **Spring JPA**. Also, during development I learned how to implement the **Eureka service** and how to make a **gateway** to the whole system, so requests will be filtered and redirected to the designated service.


## Project Overview

This microservices-based project represents a simplified airline system designed for booking tickets on selected flights. It is developed using Spring Boot and follows Domain-Driven Design (DDD) principles. The system has multiple services:

- ### User Service:
  Handles user registration, authentication, user login, and JWT token creation.

- ### Flight-Booking Service:
  Handles CRUD operations for flights, seats, and tickets. Users can book tickets, view their own tickets, and cancel them. Workers have permissions to create, delete, and update flights, as well as confirm selected tickets. The service is organized into distinct booking and flight packages within each architectural layer.

- ### API Gateway:
  Provides a single entry point to the microservices, handling routing and security.

- ### Eureka Service (Service Discovery):
  Implements service registry and discovery using Eureka Service.
