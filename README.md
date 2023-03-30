<img align="right" src="https://user-images.githubusercontent.com/47650058/182009174-f8138d3b-2099-4f72-bc25-29528298abd2.png" alt="DiscordDB" height="200" width="200">

# DiscordDB
> A simple database library for JDA Bot Developers

DiscordDB is perfect for developers who want a quick solution to storing data for their users and don't want to use any cloud-based databases. Make developing discord bots extremely easy with DiscordDB and store data without a problem!<br>

### Disclaimer
DiscordDB is not responsible for any damage done to your database files and will not be able to recover lost data unless already backed up previously. `SQLDatabase` has a functioning backup system, however, `DatabaseManager` with the JSON Databases don't have any options for database file recovery yet. Please be careful while using DiscordDB to store your data.

----

## Database Models
DiscordDB actually has wrappers for various different database models. JSON databases with simple key-value systems and is very convenient, and also SQL Databases with row-column systems and is much more optimized.

### JSON Database Model
While the JSON Database Model is very simple to use and very convenient, it can be very inefficient and unoptimized. For larger scale projects, I don't recommend using the JSON Database Model. However, for a project with a smaller scope, this is perfect for storing data.
<br>
They key-value system allows for data caching and makes data retrieval much faster than any other database model. However, updating and inserting data with the JSON Database Model can become very bloated and eat up a lot of resources. The JSON Database Model uses threads to update the files so it is updating the database in the background while the project still runs, but these threads can become heavy when they are loaded with too many requests and eat up all your resources making it extremely inefficient. The key-value system also drastically limits searching capabilities unlike other database models.

----

## Documentation
Learn more about the Library Usage from the [documentation page](https://booleancube.github.io/projects/discorddb/main).

You can also visit the [wiki page](https://github.com/BooleanCube/DiscordDB/wiki) to get more specific materials, in-depth information and tools to learn about DiscordDB.

----

## Inner Workings
DiscordDB is a database library for JDA Bot Developers and is fast because it caches all the elements and retrieves data fast from the cache and then stores all of the data in a file. So, upon application termination all of the data will be stored in JSON files using JDA's JSON utilities and upon application boot the stored data will be stored in the cache which will then be updated and used.

DiscordDB is very simple to use and easy to understand because of the minimal code required to be written. Visit the documentation to learn more about the API.

JDA Github Link: https://github.com/DV8FromTheWorld/JDA

----

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

----

*Created by BooleanCube :]*
