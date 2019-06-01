
[![Build Status](https://travis-ci.org/tatitati/oauth_play.svg?branch=master)](https://travis-ci.org/tatitati/oauth_play)


# Todo:

### with Docker:
- [x] ~add redis and mysql in container (this will define better the environment, versions...)~
- [x] ~Spin up a docker with mysql and a database~
- [x] ~Figure out how to run still travis with docker in order to pass all the test in CI~
- [x] ~Link containers Scala-db-redis~
- [x] ~Set environmental variables for configuration~ 
- [ ] :fire: Put database in volume-host (bind volume)
- [x] ~Investigate docker logs & events: sbt-docker-compose, testcontainers-scala, docker-it-scala~
 

### Fix:
 

### with scala
- [ ] Investigate default values in case classes. Behaviour is not as I was expecting

### With Play


### With Slick
- [x] ~Find out how to execute plain sql queries~
- [ ] Own pool of connections
- [ ] Understand thread pools vs connection pools difference


### with code
- [x] ~Investigate how to hash a password with sha-256 + salt~
- [x] ~Add unique keys for domain ids~
- [x] ~Add registered date (also needed for hashing+salt in password~
- [ ] Simplify builders and isolation of them, unnaply() might help?
- [x] ~Create transformer Code->Auth (this will be a domain service)~
- [ ] Add foreign keys (by the last steps)
- [ ] Add db encryption
- [ ] :fire: Design Register/login of a user (infrastructure layer) :fire:
- [ ] :fire: Design Register/login of a third (infrastructure layer) :fire:
- [ ] Start Application layer
- [x] ~BuildDate, BuildUUID, Faker, duplicated in domain and root project, they should be only in root (if possible)~

### with sbt
- [x] ~Track dependencies: https://www.scala-sbt.org/0.13/docs/Organizing-Build.html~
- [x] ~Use common settings~
- [x] ~Create subprojects for Domain and infrastructure~
- [x] ~Create custom taks in SBT to run tests in specific app-layers~
- [x] ~How to set environment variables like Hosts, port for Redis?, for each environment...scala-env~
- [x] ~Execute test in infrastructure subproject in serie instead of in parallel~

### with project
- [ ] Add license file




# Purpose

Is a project that try to implement some concepts about oauth.

# Problem statement


# Overview


# Pre-requisites

```
docker-compose up
```

# Instructions

```
docker-compose run service_scala sbt test
```

Run specific suite test:

```
docker-compose exec -it service_sbt_scala /bin/bash
        > sbt domain/test
        > sbt infrastructure/test
        > sbt learning/test
```

Visit: http://localhost:8080/ping

# Preview


# Credits

# License

The MIT License (MIT). Please see License File for more information.
 



