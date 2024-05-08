# Loyalty App

This service manages a loyalty program where customers earn points based on their transactions and can later use those points to redeem rewards. It provides functionalities for tracking loyalty points, querying balances, and managing point status.

## Table of Contents

- [Specifications](#specifications)
- [Implementation Overview](#implementation-overview)
- [Endpoints](#endpoints)
- [Getting Started](#getting-started)
- [Bonus](#bonus)

## Specifications

The loyalty program works as follows:

### Earning Points:

Customers earn points based on the value of their transactions:
- 1 pending point for every euro spent until €5000
- 2 pending points for every euro spent from €5001 to €7500
- 3 pending points for every euro spent beyond €7500

Points become available for use at the end of each week if the customer has spent at least €500 that week and has at least one transaction every day of the week. Points expire if no transaction is made within the last 5 weeks.

### Using Points:

Each point is worth 1 eurocent. Customers can use some or all of their available points.

### Querying Balance:

Customers can query for their pending and available points balance, as well as the history of points allocation and usage.

## Implementation Overview

The Loyalty Points Service is implemented in Java using Spring Boot. It consists of several components:

- **LoyaltyPoints**: Represents a loyalty points transaction, including customer ID, transaction ID, amount, date, points earned, and status.
- **PointStatus**: Enum defining the status of loyalty points (PENDING, AVAILABLE, EXPIRED, SPENT).
- **LoyaltyRepository**: JPA repository for interacting with the loyalty points database.
- **LoyaltyService**: Service layer implementing the business logic for earning, using, and querying loyalty points. It also includes a scheduled task to update points status weekly.
- **LoyaltyController**: REST controller defining endpoints for retrieving loyalty points and customer summaries.
- **Summary**: Representing a customer's loyalty points summary, including pending, available, and spent points, along with points history.
- **DatabaseInitializer**: Initializes the database with sample loyalty points data.

## Endpoints

The Loyalty Points Service exposes the following endpoints:

- `GET /loyalty-points`: Retrieves all loyalty points transactions.
- `GET /customers/{customerId}/summary`: Retrieves the summary of loyalty points for a specific customer.

## Getting Started

To run the Loyalty app locally, follow these steps:

1. Ensure you have Java 8 or higher installed on your system.
2. Clone this repository or download the source code.
3. Open the project in your preferred IDE.
4. Run the application by executing the `LoyaltyApplication` class.
5. Once the application is running, you can access the endpoints using a tool like Postman or cURL.

### Database Configuration

The Loyalty app uses an H2 in-memory database for data storage. Sample data is included to facilitate testing and evaluation of the service.

## Bonus

- **In-Memory Persistence**: Loyalty points data is persisted in memory for simplicity. To scale the application, you can replace this with a database implementation.
- **HTTP Exposed Service Logic**: The Loyalty Service logic is exposed via HTTP endpoints for easy integration with other services.

