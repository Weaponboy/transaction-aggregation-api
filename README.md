# Banking Transaction Aggregation API using Spring Boot

## Aggregates and stores csv and json data in an H2 in memory database with a Swagger rest API for retrieving and using the data

This project runs as a Docker container as is built to aggregate and store mock bank transactions temporally for use and analysis
* This project uses mock data sources in the form of a CSV and json file I found online
* The data is then parsed into an array of transaction objects and stored in a H2 in memory database
* The data can then be retrieved with the swagger API

How to run/build:
* Clone project using git CLI or download GitHub zip
* Open project in intellij and allow gradle to sync
* Make sure docker desktop is installed and running
* Then run **docker build -t transaction-aggregation-api:latest .** to build the Docker Image
* Once the image is built run **docker run -p 8080:8080 transaction-aggregation-api:latest** to start the container
* You can then access the Swagger API UI at http://localhost:8080/swagger-ui/

Mock data sources:

CSV: https://datafactory.gomask.ai/dashboard/community/transaction-categorization-dataset

Json: https://jsonlint.com/datasets/mock-transactions
