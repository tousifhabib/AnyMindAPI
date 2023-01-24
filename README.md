#Introduction

Hi there. This is the GraphQL API developed for AnyMind for the coding challenge.
There are a few prerequisites to be able to use this API.
I am using a Windows environment and as such these instructions are for a Windows environment.

## Installing Java
I am using JDK 19 to run this project, as such it should be downloaded
from here: https://www.oracle.com/java/technologies/javase/jdk19-archive-downloads.html

## Setting up the database
I  am using postgresQL inside a docker container.
To implement this do the following:

# Get the latest docker image for PostgreSQL
```
docker pull postgres
```

# Create the PostgreSQL container on port 4444
```
docker run --name anymindDB -p 4444:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=root -d postgres
```

The database should now be set up

##Setting up the code
#Installing Maven Packages
In the terminal run:
```
mvn clean install
```

#Running Spring Boot:
```
mvn spring-boot:run
```

That's it, the API is set up.

##Using GraphQL

GraphQL queries can be run on:
```
localhost:9000/graphiql
```

The following queries are possible:

# Creating a transaction:
```
mutation {
  addTransaction(transaction:{
    price: 100.00,
    paymentMethod: "MASTERCARD",
    priceModifier: 0.96
    dateTime: "2022-09-04T06:09:00Z"
  }){
    finalPrice
    points
  }
}
```

# Displaying a list of transactions:
```
query {
  transactions{
    price
    paymentMethod
    dateTime
    priceModifier
  }
}
```

# Sales broken down by hour:
```
query{
  sales(startDateTime:"2022-09-01T00:00:00Z", 
    endDateTime: "2022-09-05T19:00:00Z"){
    dateTime
    sales
    points
  }
}
```