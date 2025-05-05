# Website Generator

## Description

Set of scripts that generate backend and frontend for a website that interact with a database.

The frontend is based on [React-Router](https://reactrouter.com/) using TypeScript and the backend is based on [Springboot](https://spring.io/) using Java.

## Table of Content

- [Prerequis](#prerequis)
- [Backend](#backend)
  - [Usage](#b-usage)
  - [Use Case](#b-usecase)
  - [Configuration](#b-config)
  - [Examples](#b-examples)
- [Frontend](#frontend)
  - [Usage](#f-usage)
  - [Use Case](#f-usecase)
  - [Configuration](#f-config)
  - [Examples](#f-examples)

## Prerequis

You must have a python script that have a function `extract_class_and_fields(self, map: dict)` that return a model of this kind:

```{code}
[(MyTableName1,
  [(column_ref, column_type, column_name),
  (time, LocalDateTime, Time)]),
(MyTableName2,
  ...
)]
```

Note that the `column_type` must be set to the type of the backend, so in Java.
The `map` parameter required allow to easily define types for java, or for react, depending on the files generated.

Example: `{"int": "Integer", "str": "String", "datetime": "LocalDateTime"}` for springboot

## Backend

This is the part of the application that is used to interact with the database. For more details, [see](#b-usecase).

The structure of the generated code is the following:

```{code}
.
├── build.gradle.kts
├── .env # You must add this or modify application.yml to have a functionnal project !
└── src
    ├── main
    │   ├── java
    │   │   ├── ems
    │   │   │   ├── Application.java
    │   │   │   ├── controllers
    │   │   │   │   └── PhonyController.java
    │   │   │   ├── models
    │   │   │   │   └── Phony.java
    │   │   │   ├── repositories
    │   │   │   │   └── PhonyRepository.java
    │   │   │   ├── services
    │   │   │   │   └── PhonyService.java
    │   │   │   └── WebConfig.java
    │   │   └── myapp
    │   │       ├── Application.java
    │   │       ├── controllers
    │   │       │   └── PhonyController.java
    │   │       ├── models
    │   │       │   └── Phony.java
    │   │       ├── repositories
    │   │       │   └── PhonyRepository.java
    │   │       ├── services
    │   │       │   └── PhonyService.java
    │   │       └── WebConfig.java
    │   └── resources
    │       └── application.yml
    └── test
        └── java
            ├── ems
            │   ├── controllers
            │   │   └── PhonyControllerTest.java
            │   ├── models
            │   │   └── PhonyTest.java
            │   ├── repositories
            │   │   └── PhonyRepositoryTest.java
            │   └── services
            │       └── PhonyServiceTest.java
            └── myapp
                ├── controllers
                │   └── PhonyControllerTest.java
                ├── models
                │   └── PhonyTest.java
                ├── repositories
                │   └── PhonyRepositoryTest.java
                └── services
                    └── PhonyServiceTest.java
```

<a name="b-usage"></a>

### Usage

Go into a folder and run the two scripts:

```{sh}
cd mybackend
python springboot_generator.y
python springboot_test_generator.py
```

Then, ensure that [Gradle](https://gradle.org/install/) is installed on your computer:

```{sh}
gradle --version
```

Create a `.env` files that store secrets from the database, or simply modify the `application.yml` into [`springboot_generator.py`](./springboot_generator.py#L272-L289) or directly into your project.

(Note that if you modify `application.yml` directly in the project, the configuration will be override when running again [`springboot_generator.py`](./springboot_generator.py#L272-L289))

You can find an example of the `application.yml` generated in the section [Examples](#b-examples)

Then, to start your backend, run the following command. (Please note that if the backend run until 75% and then wait, it means that all's right)

```{sh}
gradle bootRun # or simply run "gradle tasks" to see available tasks
```

<a name="b-usecase"></a>

### Use Case

The diagram bellow represent the structure of the backend with the data transfers.

![Backend Use Case](./diagrams/b-usecase.svg)

<a name="b-config"></a>

### Configuration

The configuration of the database is stored into the generated `application.yml` with the secrets stored inside `.env` file.

The configuration of the REST server is stored inside `WebConfig.java` file located at `src/main/java/yourapplication/WebConfig.java`.

Make sure the http address stored inside `allowedOrigins` is EXACTLY the same that the address of your frontend (don't forget the port !).

Else, your backend could refuse the REST request.

The configuration of gradle is stored into `build.gradle.kts` and is written in Koltin.

You can setup a sonarcloud analyze by configuring the following lines:

```{kts}
sonar {
  properties {
    property("sonar.projectKey", "myProjectKey")
    property("sonar.organization", "myOrganization")
    property("sonar.host.url", "https://sonarcloud.io")
    property("sonar.projectName", "myProjectName")
    property("sonar.exclude", "**/*.py, **/test/**")
    property("sonar.java.source", "21")
    property("sonar.coverage.jacoco.xmlReportPaths", "./build/reports/jacoco/test/jacocoTestReport.xml")
    property("sonar.junit.reportPaths", "./build/test-results/test")
  }
}
```

You can setup a github action like this:

```{yaml}

name: test-project

on:
  push:
    branches:
      - frontend

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository to get access to the code
        uses: actions/checkout@v4
      - name: cache npm dependencies
        uses: actions/setup-node@v4
        with:
          cache: "npm"
          cache-dependency-path: "**/package-lock.json"
      - name: build dependencies
        run: npm clean-install
  test:
    runs-on: ubuntu-latest
    needs: build
    steps:
      - name: Checkout repository
        uses: actions/checkout@v4
      - name: cache npm dependencies
        uses: actions/setup-node@v4
        with:
          cache: "npm"
          cache-dependency-path: "**/package-lock.json"
      - name: build dependencies
        run: |
          npm ci
      - name: cache coverage report
        uses: actions/cache@v4
        with:
          path: ./coverage/lcov.info
          key: ${{ runner.os }}-coverage-${{ hashFiles('**/lcov.info') }}
          restore-keys: |
            ${{ runner.os }}-coverage-${{ hashFiles('**/lcov.info') }}
            ${{ runner.os }}-coverage-
            ${{ runner.os }}-
      - name: run tests and coverage
        run: |
          npm run coverage

  sonarcloud:
    name: SonarCloud
    needs: test
    runs-on: ubuntu-latest
    steps:
      - name: Checkout repository
        uses: actions/checkout@v4
        with:
          fetch-depth: 0 # Shallow clones should be disabled for a better relevancy of analysis
      - name: cache coverage report
        uses: actions/cache@v4
        with:
          path: coverage/lcov.info
          key: ${{ runner.os }}-coverage-${{ hashFiles('**/lcov.info') }}
          restore-keys: |
            ${{ runner.os }}-coverage-${{ hashFiles('**/lcov.info') }}
            ${{ runner.os }}-coverage-
            ${{ runner.os }}-
      - name: SonarCloud scan
        uses: SonarSource/sonarqube-scan-action@v5
        env:
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN }}
  publish:
    name: publish to github container
    needs: sonarcloud
    runs-on: ubuntu-latest
    permissions:
      packages: write
    steps:
      - name: create image name
        run: echo "IMAGE_NAME=ghcr.io/${GITHUB_REPOSITORY,,}:${{ github.ref_name }}" > $GITHUB_ENV
      - uses: actions/checkout@v4
      - uses: docker/setup-buildx-action@v3
      - name: Docker login
        uses: docker/login-action@v3
        with:
          registry: ghcr.io
          username: ${{ github.actor }}
          password: ${{ github.token }}
      - name: publish docker image
        uses: docker/build-push-action@v6
        with:
          context: .
          push: true
          tags: ${{ env.IMAGE_NAME }}
```

<a name="b-examples"></a>

### Examples

An example project is stored inside `phonyBackend` folder with the structure described in [Backend](#backend).

## Frontend

The frontend is used for data visualization.

For instance, the frontend can have this following structure:

```{text}
.
├── app
│   ├── components
│   │   ├── chartUtils.tsx
│   │   ├── colorUtils.tsx
│   │   ├── filter.tsx
│   │   ├── layout.tsx
│   │   ├── navigation.tsx
│   │   └── utils.tsx
│   ├── root.tsx
│   ├── routes
│   │   ├── home.tsx
│   │   └── phony.tsx
│   ├── routes.ts
│   └── tests
│       ├── chartUtils.test.ts
│       ├── colorUtils.test.ts
│       ├── home.test.tsx
│       ├── layout.test.tsx
│       ├── navigation.test.tsx
│       ├── phony.test.tsx
│       ├── root.test.tsx
│       ├── routes.test.tsx
│       ├── server.ts
│       └── setup.ts
├── Dockerfile
├── package.json
├── package-lock.json # Generated after running npm install
├── react-router.config.ts
├── tsconfig.json
├── vite.config.ts
└── vitest.config.ts
```

<a name="f-usage"></a>

### Usage

Go into a folder and run the two scripts:

```{sh}
cd myfrontend
python react_generator.y
python react_test_generator.py
```

Then, ensure that [npm](https://docs.npmjs.com/downloading-and-installing-node-js-and-npm) is installed on your computer:

```{sh}
npm --version
```

Finally, you can use this following commands:

```{sh}
npm install      # Install all dependencies
npm run build    # Build the application
npm run start    # Start the application
npm run dev      # Start the application in dev mode: developer can see in real time the modifications
npm run coverage # Start test coverage
```

<a name="f-usecase"></a>

### Use Case

The diagram bellow show the structure of the frontend

![Frontend Use Case](./diagrams/f-usecase.svg)

<a name="f-config"></a>

### Configuration

Please verify that `API_BASE` match the `mybackend:8080/api` url !

You can setup sonarcloud by adding a file `sonar-project.properties` with the following content:

```{text}
sonar.projectKey=myProjectKey
sonar.organization=myOrganization
sonar.javascript.lcov.reportPaths=coverage/lcov.info
sonar.typescript.lcov.reportPaths=coverage/lcov.info
sonar.exclusions=**/*.py,**/tests/**
sonar.branch.name=mybranchname
```

You can setup a github action like this:

```{yaml}
name: SonarQube
on:
  push:
    branches:
      - backend
  pull_request:
    types: [opened, synchronize, reopened]
jobs:
  build:
    name: Build and analyze
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0 # Shallow clones should be disabled for a better relevancy of analysis
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: 21
          distribution: temurin # Alternative distribution options are available
          cache: gradle
      - name: Check dependencies
        run: ./gradlew --write-verification-metadata sha256 help --refresh-keys
      - name: Build
        run: ./gradlew build
  sonar:
    name: SonarCloud analysis
    needs: build
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
        with:
          fetch-depth: 0 # Shallow clones should be disabled for a better relevancy of analysis
      - name: Set up JDK 21
        uses: actions/setup-java@v4
        with:
          java-version: 21
          distribution: temurin # Alternative distribution options are available
          cache: gradle
      - name: Cache SonarQube packages
        uses: actions/cache@v4
        with:
          path: ~/.sonar/cache
          key: ${{ runner.os }}-sonar
          restore-keys: ${{ runner.os }}-sonar
      - name: Analyze
        env:
          DB_HOST: ${{ secrets.DB_HOST }}
          DB_NAME: ${{ secrets.DB_NAME }}
          DB_USER: ${{ secrets.DB_USER }}
          DB_PWD: ${{ secrets.DB_PWD }}
          DB_DRIVER: ${{ secrets.DB_DRIVER }}
          SONAR_TOKEN: ${{ secrets.SONAR_TOKEN_BACKEND }}
        run: ./gradlew jacocoTestReport sonar --info
  publish:
    name: Publish backend
    needs: sonar
    runs-on: ubuntu-latest
    permissions:
      packages: write
    steps:
      - uses: actions/checkout@v4
      - name: setup java JDK
        uses: actions/setup-java@v4
        with:
          java-version: 21
          distribution: temurin # Alternative distribution options are available
          cache: gradle
      - name: publish image
        run: |
          ./gradlew jib \
          -Djib.to.image="ghcr.io/$(echo '${{ github.repository }}' | tr '[:upper:]' '[:lower:]'):${{ github.ref_name }}" \
          -Djib.to.auth.username=${{ github.actor }} \
          -Djib.to.auth.password=${{ github.token }} \
```

<a name="f-examples"></a>

### Examples

You can see examples of generated file in the folder `phonyFrontend`.

The file structure is the same than [Frontend](#frontend).
