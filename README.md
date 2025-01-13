# URL Shortener API

Welcome to the URL Shortener API! This API allows you to create, retrieve, update, and delete shortened URLs. Below are the available endpoints and their descriptions.

## API Endpoints

### 1. Create a Shortened URL

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

- Description: Retrieves the original URL associated with the given shortened code and increments the access count.
