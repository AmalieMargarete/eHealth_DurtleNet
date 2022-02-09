package DB_Driver;//Driver class using JDCB to connect MySQL database eHealth_db to our Java application
//Author: Amalie Wilke

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class driver {

    public static void main(String[] args){

        try{
            //1. connection to db
            Connection myCon =DriverManager.getConnection("jdbc:mysql://localhost:3306/eHealth_db","root", "Bionicle1!");
            //2. create a statement
            Statement myStat =myCon.createStatement();
            //3. execute a sql query
            ResultSet myRes=myStat.executeQuery("select *from users");

            //4. processing the result set
            while (myRes.next()){
                System.out.println(myRes.getString("FirstName")+", "+myRes.getString("LastName")+"." +" User created: "+myRes.getString("CreationDate"));
            }
        }

        catch(Exception exc){
            exc.printStackTrace();  //prints throwable along with line number and class where exception happened
        }
    }
}
