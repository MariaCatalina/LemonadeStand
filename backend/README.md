Dropwizard-Demo
=========================
This a sample REST application written in Java. It's purpose is to create a generic project that can be used as a starting point for a new project.

- built on [Dropwizard](https://dropwizard.github.io/dropwizard/) version 0.7.0
- dependency injection with [Google Guice](https://code.google.com/p/google-guice/) (no Spring dependencies!)
- [Hibernate](http://hibernate.org/) / JPA 2.1 as database access framework
- PostgreSQL as database
- read database configuration from Dropwizard yaml config file, persistence.xml is not used
- "Session-per-HTTP-request" with Guice [PersistentFilter](https://code.google.com/p/google-guice/wiki/JPA)
- suport for [cross-origin resource sharing](http://en.wikipedia.org/wiki/Cross-origin_resource_sharing)
- JPA entities 
- a pattern for accessing and manipulation entities with HTTP REST calls (Resource => Service => DAO => entity)

# Database Setup

- Create a local PostgreSQL database named "language".
- Run conf/db_schema.sql script to generate the database schema.
- Run conf/db_demo_data.sql to insert demo data.
- (Configure lemonade.yml with the correct url, username and password.) 



# Usage

* Start the application with the class "LemonadeApplication" with the parameters "server lemonade.yml".

* List all languages with:

        GET => http://localhost:8080/language

