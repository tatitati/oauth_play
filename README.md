
[![Build Status](https://travis-ci.org/tatitati/oauth_play.svg?branch=master)](https://travis-ci.org/tatitati/oauth_play)


# Todo:

### Fix:
- [ ] Infrastructure layer for Third. Broken because of changes in domain. :fire::fire:

### with Docker: 

### with project
- [ ] Add license file

### with sbt


### with scala

### With Play


### With Slick
- [ ] Own pool of connections


### with application
- [ ] Try another flavor of builders. Too duplication, ripling changes. :fire:
- [x] ~Define/model concept of Credentials(HASH) for user and third~
- [x] ~Define/model concept of Hash~
- [x] ~Investigate how to hash a password with sha-256 + salt~
- [x] ~Add registered date (also needed for hashing+salt in password)~
- [ ] Add foreign keys (by the last steps) :fire:
- [ ] Add db encryption
- [ ] Design Register/login of a user (infrastructure layer) :fire:
- [ ] Design Register/login of a third (infrastructure layer) :fire:
- [x] ~Start application level: Time for use cases~


----



# Purpose

Is a project that try to implement some concepts about oauth.

# Problem statement


# Overview


# Pre-requisites

```
docker-compose up
```

# Instructions

Run specific suite test:


```
// [docker exec container_scala_sbt sbt test] just create another sbt process, which create .lock files
docker-compose run service_scala /bin/bash 
   bash:> sbt
        sbt:> domain/test
        sbt:> infrastructure/test
        sbt:> learning/test
```
Visit: http://localhost:8080/ping

# Preview


# Credits

# License

The MIT License (MIT). Please see License File for more information.
 



