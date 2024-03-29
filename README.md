<img align="right" src="https://user-images.githubusercontent.com/47650058/182009174-f8138d3b-2099-4f72-bc25-29528298abd2.png" alt="DiscordDB" height="200" width="200">

# DiscordDB
> A simple database library for JDA Bot Developers

DiscordDB is perfect for developers who want a quick solution to storing data for their users and don't want to use any cloud-based databases. Make developing discord bots extremely easy with DiscordDB and store data without a problem!<br>

### Disclaimer
DiscordDB is not responsible for any damage done to your database files and will not be able to recover lost data unless already backed up previously. `SQLDatabase` has a functioning backup system, however, `DatabaseManager` with the JSON Databases don't have any options for database file recovery yet. Please be careful while using DiscordDB to store your data.

## Features
- Customizable and configurable (Both)
- Simple to use and convenient (JSON)
- Multi-threading for seamless database updating (JSON)
- JDA JSON Parsing Library (JSON)
- Fast and Reliable (SQL)
- H2 Database Java SQL Library (SQL)

## Database Models
DiscordDB actually has wrappers for various different database models. JSON databases with simple key-value systems and is very convenient, and also SQL Databases with row-column systems and is much more optimized. I recommend using a database model that is based on your needs as they both have their pros and cons.

### JSON Database Model
While the JSON Database Model is very simple to use and very convenient, it can be very inefficient and unoptimized. For larger scale projects, I don't recommend using the JSON Database Model. However, for a project with a smaller scope, this is perfect for storing data. JSON Databases are also limited upto a count of `15` to keep storage low.
<br><br>
They JSON system with keys and corresponding values, allows for data caching and makes data retrieval much faster than any other database model. However, updating and inserting data with the JSON Database Model can become very bloated and eat up a lot of resources. The JSON Database Model uses threads to update the files so it is updating the database in the background while the project still runs, but these threads can become heavy when they are loaded with too many requests and eat up all your resources making it extremely inefficient. The key-value system also drastically limits searching capabilities unlike other database models.

### SQL Database Model
The SQL Database Model requires minimal knowledge of the SQL Language. It can be used as a wrapper library to perform SQL Commands without knowing any SQL. The main purpose of this database model is to be fast and reliant which makes it more suitable to store large amounts of data with ease. Using the SQL Database model, however, isn't as easy because it pre-requisites some knowledge about the SQL Language and experience with SQL-based databases.
<br><br>
The table system with rows and columns, removes caching from being a possibility. This makes retrieval of data from databases much slower but it is also better at extensive searching for multiple keys and multiple values which can come very handy in discord bot development. Even though data retrieval might be slower, database updating is significantly faster than JSON databases and doesn't require as much resources. This enables a user to store a lot of data in a single SQL Database. The DiscordDB Library also only utilizes one database file which can home multiple tables that isn't capped at a limit.

## Documentation
Learn more about the Library Usage from the [documentation page](https://booleancube.github.io/projects/discorddb/main).

You can also visit the [wiki page](https://github.com/BooleanCube/DiscordDB/wiki) to get more specific materials, in-depth information and tools to learn about DiscordDB.

## Installation
[![](https://jitpack.io/v/BooleanCube/DiscordDB.svg)](https://jitpack.io/#BooleanCube/DiscordDB)

Latest Release: [Github Releases](https://github.com/BooleanCube/DiscordDB/releases)

Replace `VERSION` with the latest release in Jitpack. If you want to use SNAPSHOTS, please follow the instructions on the [Jitpack page](https://jitpack.io/#BooleanCube/DiscordDB)

### Maven
#### Dependency
```xml
<dependencies>
    <dependency>
        <groupId>com.github.BooleanCube</groupId>
        <artifactId>DiscordDB</artifactId>
        <version>VERSION</version>
    </dependency>
</dependencies>
```
#### Repository
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

### Gradle
#### Dependency
```gradle
dependencies {
    implementation 'com.github.BooleanCube:DiscordDB:VERSION'
}
```
#### Repository
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

## Project Roadmap
The [DiscordDB Roadmap](https://github.com/users/BooleanCube/projects/2) is open and will actively track the progress with DiscordDB development. All issues that are created will either be resolved or added to the Roadmap for later. If you have a feature request, suggestion, bug report, please create a new issue addressing your needs using the templates properly.

----

*Created by BooleanCube :]*
