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

## Installation
[![](https://jitpack.io/v/BooleanCube/DiscordDB.svg)](https://jitpack.io/#BooleanCube/DiscordDB)

Latest Release: [Github Releases](https://github.com/BooleanCube/DiscordDB/releases)



## Documentation
`DatabaseManager` is the main database manager class which will give you access to every database. <br>
`DatabaseObject` is the database object class with all the methods to access the data within the database.

Learn more about the Library Usage from the [documentation page](https://booleancube.github.io/projects/discorddb/discorddb/package-summary.html).

----

*Created by BooleanCube :]*
