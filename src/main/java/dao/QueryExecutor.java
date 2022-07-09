package dao;

import java.sql.*;

public class QueryExecutor {

    private static QueryExecutor INSTANCE;
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/lecturing";
    static final String USER = "root";
    static final String PASSWORD = "111111";

    Connection connection;

    private QueryExecutor() {}

    public static QueryExecutor getInstance(){
        if (INSTANCE == null){
            INSTANCE = new QueryExecutor();
        }
        return INSTANCE;
    }

    public Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        return connection;
    }

    public ResultSet getResultSet(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        for (int i = 0; i < parameters.length; i++){
            statement.setObject(i + 1, parameters[i]);
        }
        return statement.executeQuery();
    }

    public void updateDatabase(String query, Object... parameters) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(query);
        for (int i = 0; i < parameters.length; i++){
            statement.setObject(i + 1, parameters[i]);
        }
        statement.executeUpdate();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}