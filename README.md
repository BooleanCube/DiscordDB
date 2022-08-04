<img align="right" src="https://user-images.githubusercontent.com/47650058/182009174-f8138d3b-2099-4f72-bc25-29528298abd2.png" alt="DiscordDB" height="200" width="200">

# DiscordDB
> A simple database library for JDA Bot Developers

Discord DB is a database wrapper library for JDA Bot Developers. Currently the library is very limited in terms of what it can do because it is a **Work In Progress**.

## Limitations
Currently, you can only create database tables with 2 String fields and nothing else which makes it very limited. <br>
An Example of where this kind of database can be used may be to keep track of currency like such:
```json
{
  "data": {
    "12345678901234567890": "69",
    "23456789012345678901": "420"
  }
}
```

----

## Documentation
`DatabaseManager` is the main database manager class which will give you access to every database. <br>
`DatabaseObject` is the database object class with all the methods to access the data within the database.

Learn more about the Library Usage from the [documentation page](https://booleancube.github.io/projects/discorddb/1-0-7/discorddb/package-summary.html).

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
