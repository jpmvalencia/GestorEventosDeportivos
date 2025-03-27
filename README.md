# Sports Event Manager Project Documentation

## Summary
https://drive.google.com/file/d/1_WZFtfGB-8UI1Ndd-yl_O2bFXqdFiJHF/view?usp=sharing

## Project Structure Overview

The **Sports Event Manager** project is organized in a modular fashion, following a clean architecture to facilitate maintainability and scalability. Below is an overview of the directory structure and its key components.

---

### **1. `src`**
This is the main source directory that contains all of the project’s code and resources. It’s organized into multiple subdirectories for the core application, configuration, and testing.

#### **`src/main/java`**
Contains the Java source files, organized by package to separate concerns. The primary packages are:

- **`com.sportsevent.sportseventmanager`**: The root package for the application’s logic.
  
  - **`common`**: Contains shared components across the application.
    - **`adapters`**: This package includes classes that adapt external libraries or frameworks for internal use.
    - **`exception`**: Handles custom exceptions across the application.
    - **`pagination`**: Includes pagination utilities and related DTOs for managing large datasets.
    - **`response`**: Contains standard response formats for the application’s APIs.
    - **`utils`**: Houses utility classes used throughout the application.
    - **`validation`**: Contains validation logic for inputs and models.
  
  - **`config`**: Contains the application's configuration classes, such as Spring configuration or other frameworks’ configurations.
  
  - **`events`**: This module handles all event-related functionality.
    - **`dao`**: Contains the data access objects (DAOs) for events.
    - **`dto`**: Defines the data transfer objects (DTOs) for events.
    - **`model`**: Contains the event models/entities.
  
  - **`players`**: This module handles all player-related functionality.
    - **`dao`**: Contains the data access objects (DAOs) for players.
    - **`dto`**: Defines the data transfer objects (DTOs) for players.
    - **`model`**: Contains the player models/entities.
  
  - **`teams`**: This module handles all team-related functionality.
    - **`dao`**: Contains the data access objects (DAOs) for teams.
    - **`dto`**: Defines the data transfer objects (DTOs) for teams.
    - **`model`**: Contains the team models/entities.

#### **`src/main/webapp`**
Contains web-related files, including the web application configuration.

- **`WEB-INF`**: Contains web application configuration, including **`web.xml`** or other servlet configurations.
  
---

### **2. `src/test`**
Contains all the test files and related resources.

- **`java`**: Contains the test source files for the application.
- **`resources`**: Contains test-related resources like configuration files for testing environments.

---

### **3. `target`**
The target directory is where the build output is placed. This includes compiled classes, resources, and packaged files (like `.jar` or `.war` files). The subdirectories within **`target`** are:

- **`classes`**: Contains the compiled class files, organized in the same structure as the source code.
- **`generated-sources`**: Contains any automatically generated sources, like annotations or code generation.
- **`sports-event-manager-1.0-SNAPSHOT`**: The final packaged application, including the compiled classes and resources.
- **`lib`**: Contains any external libraries or dependencies bundled with the application.

---

### **4. Key Packages and Modules**

#### **`common` module**
The **`common`** module contains utilities and shared components that are used across the entire project, such as pagination utilities, exception handling, response formatting, and validation.

#### **`events` module**
This module is responsible for managing events, including defining event data models, their persistence (via DAOs), and the data transfer objects (DTOs) used for communication. It also handles any business logic related to events.

#### **`players` module**
Similar to the events module, this module handles player data. It includes DAOs for interacting with the database, DTOs for data exchange, and models representing players.

#### **`teams` module**
The **`teams`** module manages the teams involved in the sports events. It defines team-related models, data access objects, and DTOs.

---

### **5. Configuration and Utilities**

- **`config`**: Centralizes all application-related configurations, ensuring separation of concerns and easier management of application settings.
  
- **`pagination`**: Manages pagination logic for large datasets, allowing efficient data retrieval with page-based queries.

- **`validation`**: Ensures that the incoming data is correctly validated before processing, making sure that invalid data does not cause issues during runtime.

- **`exception`**: Defines custom exceptions and error-handling logic across the application, ensuring that proper error messages are returned to the users when necessary.

---

# API Documentation

## Base URL

`{{base_url}}`

---

## Endpoints

### 1. Retrieve Teams

**Endpoint:**

`GET /teams?page=1&size=10`

**Response:**

```json
{
    "message": "teams retrieved successfully",
    "status": 200,
    "data": [
        {
            "id": 1,
            "name": "Natus Vincere",
            "sport": "Counter Strike",
            "city": "Berlin",
            "foundationDate": "2013",
            "logo": "https://upload.wikimedia.org/wikipedia/commons/4/47/Na_Vi_Logo.png",
            "players": []
        }
    ],
    "totalRecords": 1
}
```

---

### 2. Create Team

**Endpoint:**

`POST /teams`

**Request Body:**

```json
{
    "name": "Natus Vincere",
    "sport": "Counter Strike",
    "city": "Berlin",
    "foundationDate": 2013,
    "logo": "https://upload.wikimedia.org/wikipedia/commons/4/47/Na_Vi_Logo.png"
}
```

**Response:**

```json
{
    "message": "team created successfully",
    "status": 201,
    "data": [
        {
            "id": 1,
            "name": "Natus Vincere",
            "sport": "Counter Strike",
            "city": "Berlin",
            "foundationDate": "2013",
            "logo": "https://upload.wikimedia.org/wikipedia/commons/4/47/Na_Vi_Logo.png",
            "players": []
        }
    ]
}
```

---

### 3. Create Player

**Endpoint:**

`POST /players`

**Request Body:**

```json
{
    "firstName": "John",
    "lastName": "Doe",
    "birthDate": "1990-05-15T00:00:00Z",
    "nationality": "Colombian",
    "number": 1,
    "teamId": 1
}
```

**Response:**

```json
{
    "message": "player created successfully",
    "status": 201,
    "data": [
        {
            "id": 1,
            "firstName": "John",
            "lastName": "Doe",
            "birthDate": "1990-05-15T00:00:00Z",
            "nationality": "Colombian",
            "number": 1,
            "teamId": 1,
            "isActive": true
        }
    ]
}
```

---

### 4. Retrieve Players

**Endpoint:**

`GET /players?page=1&size=10`

**Response:**

```json
{
    "message": "players retrieved successfully",
    "status": 200,
    "data": [
        {
            "playerId": 1,
            "firstName": "John",
            "lastName": "Doe",
            "birthDate": "1990-05-15T00:00:00Z",
            "nationality": "Colombian",
            "number": 1,
            "teamId": 1,
            "teamName": "Natus Vincere"
        }
    ],
    "totalRecords": 1
}
```

---

### 5. Create Event

**Endpoint:**

`POST /events`

**Request Body:**

```json
{
    "name": "Football Championship",
    "date": "2025-03-30T14:30:00Z",
    "location": "Stadium XYZ",
    "capacity": 50000,
    "sport": "Football"
}
```

**Response:**

```json
{
    "message": "event created successfully",
    "status": 201,
    "data": [
        {
            "id": 1,
            "name": "Football Championship",
            "date": "2025-03-30T14:30:00Z",
            "location": "Stadium XYZ",
            "sport": "Football",
            "participatingTeams": [],
            "capacity": 50000,
            "ticketsSold": 0,
            "status": "Programado"
        }
    ]
}
```

---

### 6. Retrieve Events

**Endpoint:**

`GET /events?page=1&size=10`

**Response:**

```json
{
    "message": "events retrieved successfully",
    "status": 200,
    "data": [
        {
            "id": 1,
            "name": "Football Championship",
            "date": "2025-03-30T14:30:00Z",
            "location": "Stadium XYZ",
            "sport": "Football",
            "capacity": 50000,
            "ticketsSold": 0,
            "status": "Programado",
            "teamNames": []
        }
    ],
    "totalRecords": 1
}
```

---

### 7. Add Team to Event

**Endpoint:**

`PUT /events`

**Request Body:**

```json
{
    "eventId": 1,
    "teamId": 1
}
```

**Response:**

```json
{
    "message": "team added to event successfully",
    "status": 200,
    "data": [
        {
            "id": 1,
            "name": "Football Championship",
            "date": "2025-03-30T14:30:00Z",
            "location": "Stadium XYZ",
            "sport": "Football",
            "participatingTeams": [
                1
            ],
            "capacity": 50000,
            "ticketsSold": 0,
            "status": "Programado"
        }
    ]
}
```

---

### 8. Update Event Status

**Endpoint:**  
`PUT /events/{eventId}/status`

#### Request Body:

```json
{
    "status": "Cancelado"
}
```

#### Response:

```json
{
    "message": "event status updated successfully",
    "status": 200,
    "data": [
        {
            "id": 1,
            "name": "Football Championship",
            "date": "2025-03-30T14:30:00Z",
            "location": "Stadium XYZ",
            "sport": "Football",
            "participatingTeams": [],
            "capacity": 50000,
            "ticketsSold": 0,
            "status": "Cancelado"
        }
    ]
}
```

---

### 9. Sell Tickets for Event

**Endpoint:**  
`PUT /events/{eventId}/sell-ticket`

#### Request Body:

```json
{
    "quantity": 1000000
}
```

#### Response:

```json
{
    "message": "tickets sold successfully",
    "status": 200,
    "data": [
        {
            "id": 1,
            "name": "Football Championship",
            "date": "2025-03-30T14:30:00Z",
            "location": "Stadium XYZ",
            "sport": "Football",
            "participatingTeams": [],
            "capacity": 50000,
            "ticketsSold": 100,
            "status": "Cancelado"
        }
    ]
}
```

--- 

**Endpoint:**  
`GET /statistics`

#### Response:

```json
{
    "message": "Statistics retrieved successfully",
    "status": 200,
    "data": [
        {
            "averagePlayersPerTeam": 0.0,
            "eventsBySport": {
                "Basquetball": 1,
                "Football": 3
            },
            "teamsWithMostEvents": {
                "1": 3,
                "2": 1
            },
            "occupancyPercentages": {
                "1": 0.2,
                "2": 0.0,
                "3": 0.0,
                "4": 100.0
            }
        }
    ]
}
```
