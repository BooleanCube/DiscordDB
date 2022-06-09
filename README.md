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
1. Install JDA through maven, gradle, or manually downloading the library.
2. Download the source code and add it to your project. You can manually download and drag it into your project or you can download using git (`git clone https://github.com/BooleanCube/DiscordDB`). This project currently does not exist as a maven repository so manually installing the latest stable build will be the only way of using the library.

## Documentation
`DatabaseManager` is the main database manager class which will give you access to every database. <br>
`DatabaseObject` is the database object class with all the methods to access the data within the database.

```java

/**
 * Creates a database file and adds it to the cache
 * @param dbName database name
 * @return boolean indicating success or failure
 * @throws LimitExceededException 10 databases at max
 * @throws FileAlreadyExistsException no duplicate databases can be created
 * @throws FileNotFoundException could not find directory
 */
DatabaseManager.createDatabase("database_name");

/**
 * Returns the database object based on the database name
 * @param dbName database name
 * @return {@link DatabaseObject} representing a database
 */
DatabaseObject database = DatabaseManager.getDatabase("database_name");

/**
 * Get database name
 * @return String with database name
 */
database.getName();

/**
 * Get value given key from the cache
 * @param key String key
 * @return String value corresponding to key
 */
database.getValue("key");

/**
 * Get value given key as integer from the cache
 * @param key String key
 * @return Integer value corresponding to key
 */
database.getValueInt("key");

/**
 * Get value given key as a long from the cache
 * @param key String key
 * @return Long value corresponding to key
 */
database.getValueLong("key");

/**
 * Update value at key, Add if key does not exist
 * (both database and cache)
 * @param key key String to new value
 * @param value new value String
 */
database.updateValue("key", "value");

/**
 * Add key to value relationship
 * (both database and cache)
 * @param key key String of value
 * @param value value String corresponding to key
 */
database.addKey("key", "value");

/**
 * Remove key
 * both database and cache
 * @param key key String to be removed
 */
database.removeKey("key");

```

----

*Created by BooleanCube :]*
