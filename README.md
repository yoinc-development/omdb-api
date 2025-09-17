# omdb-api

[![GitHub release](https://img.shields.io/github/v/release/yoinc-development/omdb-api)](https://github.com/yoinc-development/omdb-api)

A Java library that provides a simple integration of the OMDB API (https://www.omdbapi.com/) to your Java application.

## Installation

### As a Maven Dependency

```xml
<dependency>
    <groupId>ch.yoinc</groupId>
    <artifactId>omdb-api</artifactId>
    <version>1.0</version>
</dependency>
```

### Building from Source

1. Clone this repository
2. Run `mvn clean install` to build and install to your local Maven repository. To install a local Maven repository, run
   `mvn org.apache.maven.plugins:maven-install-plugin:2.5.2:install-file -Dfile=<path-to-file>`
3. Add the dependency to your main bot project's `pom.xml`:

## Usage

To use the omdb-api library, you need to create an instance of the `OmdbClient` class.

```java
OmdbClient omdbClient = new OmdbClientBuilder()
.apiKey("your-api-key")
.build();
```

Once you have an instance of the `OmdbClient` class, you can use it to make requests to the OMDB API.

```java
OmdbMovie omdbMovie = omdbClient.getMovieByTitle("The Matrix", Plot.SHORT);
```

## FAQ

### How do I get an API key?

You can get an API key on the OMDB API website (http://www.omdbapi.com/apikey.aspx).

### What can I do if something doesn't work?

Create a bug ticket.

### What about the Poster API?

Support for the Poster API is currently not planned.
