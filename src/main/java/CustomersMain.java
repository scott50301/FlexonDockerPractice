import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomersMain {
    public static void main(String[] args)  {
        // TODO Auto-generated method stub

//        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//        final String DB_URL = "jdbc:mysql://192.168.99.100:3306/dockerDB1";
//        final String USER = "root";
//        final String PASS = "mypassword";

        Connection conn = null;

        try {
            boolean exit = false;
            int choice;
            Scanner scanner = new Scanner(System.in);
            CustomersDAO dao = new CustomersDAO();
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("Connecting to database...");
//
//            conn = DriverManager.getConnection(DB_URL,USER,PASS);
//
//            System.out.println("Creating table in given database...");
//            Statement stmt = conn.createStatement();

            String sql = "";
            while (!exit){
                System.out.println("What do you want to do today? (1)Add (2)update (3)delete (4)export information (5)exit (please enter the number)");
                if (!scanner.hasNextInt()){
                    System.out.println("Please enter valid operation code");
                    scanner = new Scanner(System.in);
                    continue;
                }
                choice = Integer.parseInt(scanner.nextLine());
                ResultSet rs;
                String firstName = null;
                String lastName = null;
                BigDecimal phone = null;
                String email = null;
                List<Customers> result = new ArrayList<Customers>();
                switch (choice){
                    case 1:
                        System.out.println("Please enter the first name");
                        firstName  = scanner.nextLine();
                        System.out.println("Please enter the last name");
                        lastName  = scanner.nextLine();
                        System.out.println("Please enter the phone number");
                        phone =  new BigDecimal(scanner.nextLine());
                        System.out.println("Please enter the email");
                        email = scanner.nextLine();

                        dao.insertData(firstName,lastName,phone,email);

                        break;
                    case 2:
                        sql = "SELECT * FROM `contacts_tbl`";
                        result = dao.getData(sql);
                        showInfo(result);
                        System.out.println("Which data do you want to update? (please enter the contactID)");
                        if (!scanner.hasNextInt()){
                            System.out.println("Please enter valid contactID");
                            scanner = new Scanner(System.in);
                            continue;
                        }
                        int updateId  = Integer.parseInt(scanner.nextLine());
                        sql = "SELECT * FROM `contacts_tbl` WHERE  `contactID` ="+updateId;
                        result = dao.getData(sql);

                        firstName = result.get(0).getFirstNema();
                        lastName = result.get(0).getLastNema();
                        phone = result.get(0).getPhone();
                        email = result.get(0).getEmail();

                        System.out.println("Current firstName ："+firstName+" (fill the previous value if you don't want to change this)");
                        firstName = scanner.nextLine();

                        System.out.println("Current lastName ："+lastName+" (fill the previous value if you don't want to change this)");
                        lastName = scanner.nextLine();


                        System.out.println("Current phone number ："+phone+" (fill the previous value if you don't want to change this)");
                        phone = new BigDecimal(scanner.nextLine());


                        System.out.println("Current email ："+email+" (fill the previous value if you don't want to change this)");
                        email = scanner.nextLine();

                        dao.updateData(updateId, firstName, lastName, phone, email);

                        break;
                    case 3:
                        sql = "SELECT * FROM `contacts_tbl`";
                        result = dao.getData(sql);
                        showInfo(result);
                        System.out.println("Which data do you want to delete? (please enter the contactID)");
                        if (!scanner.hasNextInt()){
                            System.out.println("Please enter valid contactID");
                            scanner = new Scanner(System.in);
                            continue;
                        }
                        int deleteId  = Integer.parseInt(scanner.nextLine());
                        dao.deleteById(deleteId);
                        break;
                    case 4:
                        sql = "SELECT * FROM `contacts_tbl`";
                        result = dao.getData(sql);

                        FileWriter myWriter = new FileWriter("contactInfo.txt");
                        for (Customers res : result) {
                            int id = res.getId();
                            firstName = res.getFirstNema();
                            lastName = res.getLastNema();
                            phone = res.getPhone();
                            email = res.getEmail();
                            myWriter.write(id +"\t"+ firstName+"\t" + lastName+"\t"+phone+"\t"+email+"\n");

                            //System.out.println(id + firstName + lastName+phone+email);
                        }
                        myWriter.close();
                        System.out.println("Successfully wrote to the file.");
                        break;
                    case 5:
                        exit = true;
                        break;
                }
            }

            System.out.println("Created table in given database...");

        } catch (SQLException  e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void showInfo(List<Customers> result) {
        for (Customers res : result){
            System.out.println(res.getId() +"\t"+ res.getFirstNema()+"\t" + res.getLastNema()+"\t"+res.getPhone()+"\t"+res.getEmail());
        }


    }
}
