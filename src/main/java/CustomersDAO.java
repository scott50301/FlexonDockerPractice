import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class CustomersDAO {
    final private String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    final private String DB_URL = "jdbc:mysql://192.168.99.100:3306/dockerDB1";
    //final private String DB_URL = "jdbc:mysql://localhost:3306/dockerDB1";
    final private String USER = "root";
    final private String PASS = "mypassword";

    private Connection conn = null;
    private Statement stmt;
    ResultSet rs;
    String sql;
    List<Customers> list = new ArrayList<Customers>();
    public CustomersDAO() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Connecting to database...");
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement();
    }


    public List<Customers> getData(String sql) throws SQLException {
        rs = stmt.executeQuery(sql);
        list = new ArrayList<Customers>();
        while (rs.next()) {
              Customers customers = new Customers(rs.getInt("contactID"),
                      rs.getString("contact_name"),
                      rs.getString("contact_lastname"),
                      rs.getBigDecimal("contact_phoneNume"),
                      rs.getString("contact_email"));
              list.add(customers);
        }
        return list;
    }


    public void insertData(String firstName, String lastName, BigDecimal phone, String email) throws SQLException {
        sql = "INSERT INTO contacts_tbl ( contact_name, contact_lastname, contact_phoneNume, contact_email )\n" +
                "   VALUES ('"+firstName+"','"+lastName+"',"+phone+",'"+email+"' )";

        stmt.executeUpdate(sql);
        System.out.println("new contact info has been added");
    }

    public void deleteById(int id) throws SQLException {
        sql = "DELETE FROM `contacts_tbl` WHERE `contactID` = "+id;
        stmt.executeUpdate(sql);
        System.out.println("User "+id + " has been deleted");
    }

    public void updateData(int id, String firstName, String lastName, BigDecimal phone, String email) throws SQLException {
        sql= "UPDATE `contacts_tbl` " +
                "SET contact_name = '"+firstName +
                "', contact_lastname = '"+lastName+
                "', contact_phoneNume ="+phone+
                ", contact_email ='"+  email+
                "' WHERE contactID = "+id;
        System.out.println(sql);
        stmt.executeUpdate(sql);
        System.out.println("The contact info has been updated");
    }
}
