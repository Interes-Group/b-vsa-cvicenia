package sk.stuba.fei.uim.vsa.cv1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Cvicenie1 {

    public static final int DB_H2 = 0;
    public static final int DB_MYSQL = 1;

    public static final String DB = "VSA_CV1";
    public static final String USERNAME = "vsa";
    public static final String PASSWORD = "vsa";


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int db = DB_H2;

        Connection connection = null;
        if (db == DB_MYSQL) {
            connection = connectToMySQL();
        } else {
            connection = connectToH2();
        }

        runSQLStatement(connection, "CREATE TABLE person (id INTEGER NOT NULL, name VARCHAR(255), PRIMARY KEY(id))");
        runSQLStatement(connection, "INSERT INTO person VALUES (1, 'Milan')");

        connection.close();
    }

    public static Connection connectToMySQL() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB, USERNAME, PASSWORD);
    }

    public static Connection connectToH2() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:./" + DB, USERNAME, PASSWORD);
    }

    public static void runSQLStatement(Connection con, String sql) {
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
