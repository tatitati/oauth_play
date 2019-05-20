
[![Build Status](https://travis-ci.org/tatitati/HTTP_AKKA_project.svg?branch=master)](https://travis-ci.org/tatitati/HTTP_AKKA_project)


# Todo:

### with Docker:
- [x] ~add redis and mysql in container (this will define better the environment, versions...)~
- [x] ~Spin up a docker with mysql and a database~
- [x] ~Figure out how to run still travis with docker in order to pass all the test in CI ~
- [x] ~Link containers Scala-db-redis~
- [x] ~Set environmental variables for configuration~
- [ ] Manage to switch database-container when running tests (this will define better the environment, versions...) :fire:
- [ ] Put database in volume-host :fire:
- [ ] Investigate docker logs & events

### Fix: 
- [x] ~updateCallbackUrl() is used in Third. It should belong to thirdadpp.~

### with scala
- [ ] Investigate default values in case classes. Behaviour is not as I was expecting

### with code
- [x] ~Investigate how to hash a password with sha-256 + salt~
- [x] ~Add unique keys for domain ids~
- [x] ~Add registered date (also needed for hashing+salt in password~
- [ ] Simplify builders and isolation of them, unnaply() might help?
- [x] ~Create transformer Code->Auth (this will be a domain service)~
- [ ] Add foreign keys (by the last steps)
- [ ] Add db encryption
- [ ] Design Register/login of a user :fire:
- [ ] Design Register/login of a third :fire:
- [ ] Start Application layer
- [ ] Investigate how to handle user-session
- [ ] BuildDate, BuildUUID, Faker, duplicated in domain and root project, they should be only in root (if possible)

### with sbt
- [x] ~Track dependencies: https://www.scala-sbt.org/0.13/docs/Organizing-Build.html~
- [x] ~Use common settings~
- [x] ~Create subprojects for Domain and infrastructure~
- [ ] How to tag tests? (and filter them in the CLI)
- [x] ~Create custom taks in SBT to run tests in specific app-layers~
- [ ] How to set environment variables like Hosts, port for Redis?, for each environment...scala-env :fire:
- [x] ~Execute test in infrastructure subproject in serie instead of in parallel~

### With general coding concepts:
- [ ] Investigate if I should refactor to use Actors :fire:

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
docker exec container_scala_sbt sbt test
        ...                     sbt 'testOnly *app.domain*'
        ...                     sbt 'testOnly *app.persistence*'
        ...                     sbt run
```

Visit: http://localhost:8080/ping

# Preview


# Credits

# License

The MIT License (MIT). Please see License File for more information.
 



