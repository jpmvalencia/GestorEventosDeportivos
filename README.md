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

`POST /events`

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
