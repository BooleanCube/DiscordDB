package discorddb.sqlitedb;

import java.sql.*;

/**
 * {@link DatabaseTable} represents a table in a SQLite database and provides methods for
 * performing various SQL queries on the table, such as inserting data, searching for data,
 * updating data, and deleting data.
 *
 * @see #insertQuery(String[] columns)
 * @see #searchQuery(String keyName, String location)
 */
public class DatabaseTable {

    private final Statement statement;
    private final String tableName;
    private int columnCount;

    protected DatabaseTable(Statement stat, String name, int columnCount) {
        this.statement = stat;
        this.tableName = name;
        this.columnCount = columnCount;
    }

    /**
     * Gets the name of hte table
     * @return table name represented in a String
     */
    public String getName() {
        return this.tableName;
    }

    /**
     * Inserts a new row of data into the table with the specified values
     * @param values array of column values that you want to set in the correct order
     * @return boolean indicating whether the sql command execution worked properly (didn't return a {@link ResultSet})
     * @throws SQLException for statement sql command execution
     */
    public boolean insertQuery(String... values) throws SQLException {
        return !statement.execute(
                String.format("INSERT INTO %s VALUES (%s)",
                        this.tableName,
                        String.join(", ", values)
                )
        );
    }

    /**
     * Searches for rows in the table that matches the specified
     * key value, and returns the values of the specified parameters
     * for those rows.
     * @param parameterNames specified String array of parameter names to return the parameter values of
     * @param keyName name of the key parameter that you want to search for the row by
     * @param location the value you want to match for the key
     * @return 2D Object array of all the column values in all selected rows.
     * @throws SQLException for the sql command execution
     */
    public Object[][] searchQuery(String[] parameterNames, String keyName, String location) throws SQLException {
        ResultSet result = statement.executeQuery(
                String.format("SELECT %s FROM %s WHERE %s=%s",
                        String.join(", ", parameterNames),
                        this.tableName,
                        keyName,
                        location
                )
        );

        result.last();
        int rows = result.getRow();
        result.beforeFirst();
        Object[][] values = new Object[rows][parameterNames.length];

        for(int r=1; r<=rows; r++) {
            result.next();
            for(int i=1; i<=parameterNames.length; i++) {
                values[r-1][i-1] = result.getObject(i);
            }
        }

        return values;
    }

    /**
     * Searches for rows in the table that match the specified
     * key value and returns all of the column values for those
     * rows.
     * @param keyName specified String key parameter name
     * @param location specified String key parameter value
     * @return 2D Object array of all the column values in all selected rows.
     * @throws SQLException for sql command execution
     */
    public Object[][] searchQuery(String keyName, String location) throws SQLException {
        ResultSet result = statement.executeQuery(
                String.format("SELECT * FROM %s WHERE %s=%s",
                        this.tableName,
                        keyName,
                        location
                )
        );

        result.last();
        int rows = result.getRow();
        result.beforeFirst();
        Object[][] values = new Object[rows][this.columnCount];

        for(int r=1; r<=rows; r++) {
            result.next();
            for(int i=1; i<=this.columnCount; i++) {
                values[r-1][i-1] = result.getObject(i);
            }
        }

        return values;
    }

    /**
     * Updates the value of a specified column for the row
     * that matches the specified key value.
     * @param columnName String array of all column parameter names
     * @param columnValue String array of all column parameter values
     * @param keyName specified String containing the key parameter name
     * @param location specified String containing the key parameter value to match
     * @return boolean indicating whether the sql command execution worked properly (didn't return a {@link ResultSet})
     * @throws SQLException for sql command execution
     */
    public boolean updateQuery(String[] columnName, String[] columnValue, String keyName, String location) throws SQLException {
        StringBuilder columns = new StringBuilder();
        for(int i=0; i<columnName.length; i++) {
            columns.append(columnName[i]).append("=").append(columnValue[i]).append(",");
        }

        return !statement.execute(
                String.format("UPDATE %s SET %s WHERE %s=%s",
                        this.tableName,
                        columns.substring(0, columns.length()-1),
                        keyName,
                        location
                )
        );
    }

    /**
     * Deletes multiple specified rows from the table that matches
     * the specified key value.
     * @param keyName specified String key parameter name
     * @param location specified String key parameter value to match
     * @return boolean indicating whether the sql command execution worked properly (didn't return a {@link ResultSet})
     * @throws SQLException for sql command execution
     */
    public boolean deleteQuery(String keyName, String location) throws SQLException {
        return !statement.execute(
                String.format("DELETE FROM %s WHERE %s=%s",
                        this.tableName,
                        keyName,
                        location
                )
        );
    }

}
