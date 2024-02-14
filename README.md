# Spring CDC

A Spring API that uses kafka connect to synchronize databases data between Mysql and Postgresql


### Used Technologies

* [Java](https://www.java.com/en/)
* [Docker](https://www.docker.com/)
* [MySQL](https://www.mysql.com/)
* [PostgreSQL](https://www.postgresql.org/)
* [Kafka](https://kafka.apache.org/)
* [Kafka Connect](https://docs.confluent.io/platform/current/connect/index.html)

## Necessary versions and dependencies

* Docker - Version: 25.0.3
* Java - Version: 17 or higher

### Getting started

```
docker compose up -d
```

After that:

```
cd /post-service
./mvnw spring-boot:run
```
and:
```
cd /spring-comment-service
./mvnw spring-boot:run
```

then you run:

**OBS:** make sure that you are at the project root

```
curl -X POST -H "Content-Type:application/json" http://0.0.0.0:8083/connectors -d @mysql.json
```

```
curl -X POST -H "Content-Type:application/json" http://0.0.0.0:8083/connectors -d @postgres.json
```

Then you will can use the project at [localhost:8080](htpp://localhost:8080) and [localhost:8081](htpp://localhost:8081)

## Routes

```
Create post

POST :8080/posts

{
    "content": "this is the way, mandalore way"
}
```
Return:

```
{
    "id": 1,
    "content": "this is the way, mandalore way"
}
```

```
Create comment

POST :8081/comments

{
    "text": "this is the way 3", 
    "postId": 1
}
```

Return:

```
{
    "id": 1,
    "text": "this is the way 3", 
    "postId": 1
}
```

after that you can see the comments saved at post database:

```
GET :8081/posts
```

Return:

```
{
    "id": 1,
    "content": "this is the way, mandalore way",
    "comments": [
        {
            "id": 1,
            "text": "this is the way 3", 
            "postId": 1,
        }
    ]
}

```

## Faced problems


### Problem 1:
Error on execute ```curl -X POST -H "Content-Type:application/json" http://0.0.0.0:8083/connectors -d @mysql.json```
* Make sure that all database are created, you can do it running both APIs

### Problem 2:
The command ```curl -X POST -H "Content-Type:application/json" http://0.0.0.0:8083/connectors -d @mysql.json``` doesn't return anything
* Wait while docker is starting all services, if necessary restart the container

