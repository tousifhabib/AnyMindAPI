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