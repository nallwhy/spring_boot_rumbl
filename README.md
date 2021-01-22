[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=nallwhy_spring_boot_rumbl&metric=alert_status)](https://sonarcloud.io/dashboard?id=nallwhy_spring_boot_rumbl)

# Spring Boot Rumbl

- [x] Spring Boot 2.3
- [x] Kotlin 1.3.7
- [x] Postgres 12
- [x] Multi-module
- [x] Multi-env properties
- [x] Logging
- [x] DB schema version control
- [ ] JPA
- [ ] NO JPA
- [x] JDBC
- [ ] R2DBC
- [ ] Testing
- [x] MVC
- [ ] WebFlux
- [x] RSocket
- [ ] Caching
- [ ] i18n

## Architecture

TBD

## References

- [Spring Guides](https://spring.io/guides)
- Spring in Action, 5th
- [Getting Started With RSocket - SpringOne 2020](https://www.youtube.com/watch?v=dp1lGH2OCUs)

# How to run

## Run DB in docker

```shell
$ docker-compose up -d
```

## Run DB migration

```shell
$ gradle rumbl-domain:jarmonicaUp
```

## Run Spring Boot Application

```shell
$ gradle rumbl-domain:bootRun
```
