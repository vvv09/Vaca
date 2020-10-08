# Vaco
## the vacabulaty coach
### Features

- CRUD for foreign words you want to remember


**Table of Contents**
###### will be soon

### Application.yml

```
server:
  port: 8156
spring:
  freemarker:
    expose-request-attributes: true
    suffix: .ftl
  datasource:
    platform: postgres
    driverClassName: org.postgresql.Driver
    url: <url>
    username: <username>
    password: <password>
    initialization-mode: always
  jpa:
    generate-ddl: true
```