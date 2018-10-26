package com.lgp.postgresql.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @AUTHOR lgp
 * @DATE 2018/10/26 17:58
 * @DESCRIPTION
 **/
public class PostgresqlConnect {
    static String url = "jdbc:postgresql://127.0.0.1:5432/test";
    static String usr = "postgres";
    static String psd = "123456";


    public static void main(String args[]) {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
                    .getConnection(url,
                            usr, psd);
            System.out.println("Opened database test");
            String sql = "";
//            stmt = c.createStatement();
//             sql = "CREATE TABLE COMPANY " +
//                    "(ID INT PRIMARY KEY     NOT NULL," +
//                    " NAME           TEXT    NOT NULL, " +
//                    " AGE            INT     NOT NULL, " +
//                    " ADDRESS        CHAR(50), " +
//                    " SALARY         REAL)";
//            stmt.executeUpdate(sql);
//            System.out.println("CREATE TABLE COMPANY");

//            stmt = c.createStatement();
//             sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                    + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                    + "VALUES (2, 'Allen', 25, 'Texas', 15000.00 );";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                    + "VALUES (3, 'Teddy', 23, 'Norway', 20000.00 );";
//            stmt.executeUpdate(sql);
//
//            sql = "INSERT INTO COMPANY (ID,NAME,AGE,ADDRESS,SALARY) "
//                    + "VALUES (4, 'Mark', 25, 'Rich-Mond ', 65000.00 );";
//            stmt.executeUpdate(sql);
//            System.out.println("insert data end");


//            stmt = c.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM COMPANY;");
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int age = rs.getInt("age");
//                String address = rs.getString("address");
//                float salary = rs.getFloat("salary");
//                System.out.println("ID = " + id);
//                System.out.println("NAME = " + name);
//                System.out.println("AGE = " + age);
//                System.out.println("ADDRESS = " + address);
//                System.out.println("SALARY = " + salary);
//                System.out.println();
//            }
//            System.out.println("select data end");

            stmt = c.createStatement();
            sql = "UPDATE COMPANY set SALARY = 21000.00 where ID=1;";
            stmt.executeUpdate(sql);
//            c.commit();

            rs = stmt.executeQuery("SELECT * FROM COMPANY where ID=1;");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println("ID = " + id);
                System.out.println("NAME = " + name);
                System.out.println("AGE = " + age);
                System.out.println("ADDRESS = " + address);
                System.out.println("SALARY = " + salary);
                System.out.println();
            }
            System.out.println("update data end");

            stmt = c.createStatement();
             sql = "DELETE from COMPANY where ID=2;";
            stmt.executeUpdate(sql);


             rs = stmt.executeQuery( "SELECT * FROM COMPANY;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  name = rs.getString("name");
                int age  = rs.getInt("age");
                String  address = rs.getString("address");
                float salary = rs.getFloat("salary");
                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + name );
                System.out.println( "AGE = " + age );
                System.out.println( "ADDRESS = " + address );
                System.out.println( "SALARY = " + salary );
                System.out.println();
            }
            System.out.println("delete data end");

            stmt.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
