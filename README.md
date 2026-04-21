# Banking Transaction Aggregation API using Spring Boot

## Aggregates and stores csv and json data in an H2 in memory database with a Swagger rest API for retrieving and using the data

This project runs as a Docker container and is built to aggregate and store mock bank transactions temporally for use and analysis
* It uses mock data sources in the form of a CSV and json file I found online
* The transactions are then parsed into an array of transaction objects
* Transaction objects are then stored in a H2 in memory database for fast data access
* Stored transactions can then be retrieved/sorted with swagger API calls

How to run/build:
* Clone project using git CLI or download GitHub zip
* Open project in intellij and allow gradle to sync
* Make sure docker desktop is installed and running
* Then run **docker build -t transaction-aggregation-api:latest .** to build the Docker Image
* Once the image is built run **docker run -p 8080:8080 transaction-aggregation-api:latest** to start the container
* You can then access the Swagger API UI at http://localhost:8080/swagger-ui/

Frameworks (And why I made the decision to use them):
* Spring boot
  * For easier development with reduction of code needed for microservices and boilerplate code
* Swagger
  * To allow easy creation and use of my API for accessing and using the aggregated data
* H2 in memory database
  * Used for its small footprint, easy setup and fast data access speeds

API Calls:



My aims with this project: 
I wanted to create a program that completes the assigned task in an efficient, simple and scalable manner. For example 
the code is modular and easy to upgrade any function/framework without an entire codebase refactor. I wanted to focus on 
coding fundamentals and allow space for easy upgrades if wanted. For example adding an ML data pipeline that can input data 
for many formats instead of being restricted to one.

Mock data sources:

CSV: https://datafactory.gomask.ai/dashboard/community/transaction-categorization-dataset

Json: https://jsonlint.com/datasets/mock-transactions

