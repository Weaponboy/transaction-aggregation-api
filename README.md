# Banking Transaction Aggregation API using Spring Boot

## Aggregates and stores csv and json data in an H2 in memory database with a Swagger rest API for retrieving and using the data

This project runs as a Docker container and is built to aggregate and store mock bank transactions temporally for use and analysis
* It uses mock data sources in the form of a csv and json file I found online
* The transactions are then parsed into an array of transaction objects
* Transaction objects are then stored in a H2 in memory database for fast data access
* Stored transactions can then be retrieved/sorted with Swagger API calls

How to run/build:
* Clone project using git CLI or download GitHub zip
* Open project in intellij and allow gradle to sync
* Make sure docker desktop is installed and running
* Then run this command to build the Docker Image
  * **docker build -t transaction-aggregation-api:latest .** 
* Once the image is built run the command below to start the container
  * **docker run -p 8080:8080 transaction-aggregation-api:latest** 
* You can then access the Swagger API UI at http://localhost:8080/swagger-ui/

Frameworks (And why I made the decision to use them):
* Spring boot
  * For easier development with reduction of code needed for microservices and boilerplate code
* Swagger
  * To allow easy creation and use of my API for accessing and using the aggregated data
* H2 in memory database
  * Used for its small footprint, easy setup and fast data access speeds

## API Calls:

### `GET /transactions`
Returns **all transactions** in the database.

### `GET /transactions/paged`
Returns transactions in **paginated format**.

#### Query Parameters
- `page` → Page number (default `0`)
- `size` → Items per page (default `20`)
- `sortBy` → Field to sort by (default `date`)
- `direction` → `asc` or `desc` (default `desc`)

### `GET /transactions/account`
Returns transactions for a specific **account name/number**.

### `GET /transactions/merchant`
Returns transactions for a specific **merchant**.

### `GET /transactions/bank`
Returns transactions for a specific **bank**.

### `GET /transactions/amount/greater-than`
Returns transactions where the amount is **greater than the provided value**.

#### Query Parameters
- `amount`

### `GET /transactions/date/range`
Returns transactions between two dates.

#### Query Parameters
- `startDate`
- `endDate`

### `GET /transactions/searchDescription`
Searches transactions where the description contains a keyword.

#### Query Parameters
- `keyword`

### `GET /transactions/filter`
Flexible filtering endpoint. Returns transactions based on the **first provided parameter**.

#### Optional Query Parameters
- `account`
- `bank`
- `merchant`
- `minAmount`

## Features
- Retrieve all transactions
- Pagination and sorting support
- Search by merchant, bank, account, description
- Filter by amount or date range

## My aims with this project: 
I wanted to create a program that completes the assigned task in an efficient, simple and scalable manner. For example 
the code is modular making it easy to upgrade any function/framework without an entire codebase refactor. I wanted to focus on 
coding fundamentals and allow space for easy upgrades if wanted. For example adding an ML data pipeline that can input data 
for many formats instead of being restricted to one hardcoded per pipeline.

## Mock data sources:

CSV: https://datafactory.gomask.ai/dashboard/community/transaction-categorization-dataset

Json: https://jsonlint.com/datasets/mock-transactions

