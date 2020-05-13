## Starter project with Spring boot.

This is my take on https://www.baeldung.com/spring-boot-start

## How to run the project.
    - I use jdk 11 to run the project. (use sdkman.io to get it).  
    - Clone the repo.
    - Then using IntelliJ open the project ( where the pom file is).
    
    - Enable auto-import.
    - Wait a while
    - Then navigate to the tests (BookIntegrationTest) and try to run them :) 
    
    - If you have any problems or questions - ask me :)
    
## Database migrations & setup

Important - Before starting the app, run this command: 
   
    docker-compose up -d 
    
to start the local postgres instance (listening on localhost:5432)

We have flyway DB migration for the persistent DB in the dockerfile. 
For the H2 DB for the tests we don't have the flyway enabled -  we use the JPA to autogenerate the database there for now.
    
    - Articles used 
        A good explanation of what DB migrations are and how to turn them off:
        https://www.baeldung.com/database-migrations-with-flyway
     -  Also this one for how to execute Flyway DB migrations on app startup - https://docs.spring.io/spring-boot/docs/1.1.1.RELEASE/reference/html/howto-database-initialization.html
     
     
## Swagger 2

Swagger is enabled and available in the url http://localhost:8081/swagger-ui.html