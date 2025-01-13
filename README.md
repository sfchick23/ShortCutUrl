# URL Shortener API

Welcome to the URL Shortener API! This API allows you to create, retrieve, update, and delete shortened URLs. Below are the available endpoints and their descriptions.

## API Endpoints

### Create a Shortened URL

**POST** `/shorten`

**Request:**
- **Method:** POST
- **Content-Type:** application/json
- **Body:**
  ```json
  {
    "url": "http://example.com"
  }
**Response:**
- Status Code: 201 Created
- Body:
  ```json
  {
    "id": 1,
    "url": "http://example.com",
    "shortCode": "abc123",
    "createdAt": "2024-08-21T12:00:00Z",
    "updated": "2024-08-21T12:00:00Z"
  }

- Description: Generates a unique shortened URL for the provided original URL.


### Retrieve Original URL ###
**GET** `/shorten/<short_code>`

**Request:**
- **Method:** GET
- **URL Parameter:** `short_code` (the unique shortened code)
**Response:**
- Status Code: 200 OK
- Body:
  ```json
  {
    "id": 1,
    "url": "http://example.com",
    "shortCode": "abc123",
    "createdAt": "2024-08-21T12:00:00Z",
    "updated": "2024-08-21T12:00:00Z",
    "accessCount": 1
  }
  
- Description: Retrieves the original URL associated with the given shortened code and increments the access count.


### Update Original URL ###
**PUT** `/shorten/<short_code>`

**Request:**
- **Method:** PUT
- **Content-Type:** `application/json`
- **URL Parameter:** `short_code` (the unique shortened code)
- **Body:**
  ```json
  {
    "url": "http://newexample.com"
  }
**Response:**
- Status Code: 200 OK
- Body:
  ```json
  {
    "id": 1,
    "url": "http://newexample.com",
    "shortCode": "abc123",
    "createdAt": "2024-08-21T12:00:00Z",
    "updated": "2024-08-21T12:00:00Z"
  }
- Description: Updates the original URL associated with the given shortened code.


### Delete a Shortened URL ###
**DELETE** `/shorten/<short_code>`

**Request:**
- **Method:** DELETE
- **URL Parameter:** short_code (the unique shortened code)
- **Response:**
  - **Status Code:** 204 No Content
- Description: Deletes the shortened URL associated with the given shortened code.


### Get Usage Statistics ###
**GET** `/shorten/<short_code>/stats`

**Request:**
- **Method:** GET
- **URL Parameter:** `short_code` (the unique shortened code)
**Response:**
- **Status Code:** 200 OK
- **Body:**
  ```json
  {
    "id": 1,
    "url": "http://example.com",
    "shortCode": "abc123",
    "createdAt": "2024-08-21T12:00:00Z",
    "updated": "2024-08-21T12:00:00Z",
    "accessCount": 1
  }
- Description: Retrieves the usage statistics, including the access count, for the given shortened URL.
