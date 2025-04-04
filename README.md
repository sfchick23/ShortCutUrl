# URL Shortener API

This is a solution to the URL shortener project on roadmap.sh : [https://roadmap.sh/projects/url-shortening-service](https://roadmap.sh/projects/url-shortening-service)

This project is a URL Shortener service that enables users to create shortened URLs, retrieve the original URLs using shortened codes, and monitor statistics like access counts. It is developed using Spring Boot and JPA for data persistence, offering RESTful APIs for seamless interaction with the service.

## Features

- [Shorten URLs](#create-a-shortened-url): Generate a unique, shortened version of any URL.
- [Retrieve URLs](#retrieve-original-url): Look up the original URL using the shortened code.
- [Update URLs](#update-original-url): Modify the original URL associated with a shortened code.
- [Delete URLs](#delete-a-shortened-url): Remove shortened URLs from the system.
- [View Stats](#get-usage-statistics): Track how many times a shortened URL has been accessed.


## Setup Instructions

### Prerequisites
- Java 21
- Maven 3.9.9

### Steps to Run

  - Clone the repository:

     ```bash
     git clone https://github.com/sfchick23/ShortCutUrl.git
     cd ShortCutUrl

  - Build the project using Maven:

     ```bash
     mvn clean install

  - Database Configuration

     - Change the portNo and dbName
     - Change the username and password
   
    ```properties
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.datasource.url=jdbc:postgresql://localhost:portNo/dbname
    spring.datasource.username=postgres
    spring.datasource.password=postgres

  - Run the application:
    ```bash
    mvn spring-boot:run
  
## Technologies Used
- **Java 21**
- **Spring boot(REST, JPA, Transaction)**
- **Hibernate**
- **Postgresql**
- **Maven**
- **ModelMapper**

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
