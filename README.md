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

## Usage
1. Install [JDA](https://github.com/DV8FromTheWorld/JDA#download) through maven, gradle, or manually downloading the library.
2. Download the source code and add it to your project. You can manually download and drag it into your project or you can download using git (`git clone https://github.com/BooleanCube/DiscordDB`). This project currently does not exist as a maven repository so manually installing the latest stable build will be the only way of using the library.

## Documentation
`DatabaseManager` is the main database manager class which will give you access to every database. <br>
`DatabaseObject` is the database object class with all the methods to access the data within the database.

Learn more about the Library Usage from the [documentation page](https://booleancube.github.io/projects/discorddb/discorddb/package-summary.html).

----

*Created by BooleanCube :]*
