
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class DataBase {
    public static final String user="root";
    public static final String parola="";
    public static final String db_name="demo";
    public static final String host="localhost";
    public static final int port=3306;
}

 
public class Model{
    protected Connection con=null;
    protected Statement statement=null;
    protected PreparedStatement preparedStatement=null;
    
    
    public Model(){
        
        String Url="jdbc:mysql://"+DataBase.host+":"+DataBase.port+"/"+DataBase.db_name;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Driver bulamadi");
        }
        try {
            con=(Connection) DriverManager.getConnection(Url, DataBase.user, DataBase.parola);
            System.out.println("Baglanti basarli oldu");
        }
        catch (SQLException ex) {
            ex.printStackTrace();
           // System.out.println("Baglanti basirsiz oldu");
            }
    }
    
    public void Update_Data(Integer id,String name, String last_name, String father_name, int year, int student_no,
            String sex, String birth_data, String phone_number, String email_address, String country, String city,
            String faculty, String depart, String studentIdentity, String job){
            String sorgu="Update student Set  name=?,last_name=?,father_name=?,year=?,sign_no=?,Sex=?,date=?,phone_number=?,email_address=?,"
                + "country=?,city=?,faculty=?,department=?,Job=?,identity_cord_No=? where id=?";
           
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, father_name);
            preparedStatement.setInt(4, year);
            preparedStatement.setInt(5, student_no);
            preparedStatement.setString(6, sex);
            preparedStatement.setString(7, birth_data);
            preparedStatement.setString(8, phone_number);
            preparedStatement.setString(9, email_address);
            preparedStatement.setString(10, country);
            preparedStatement.setString(11, city);
            preparedStatement.setString(12, faculty);
            preparedStatement.setString(13, depart);
            preparedStatement.setString(14, job);
            preparedStatement.setString(15, studentIdentity);
            
            preparedStatement.setInt(16, id);
            new Worker(id,name, last_name, father_name, year, student_no, sex, birth_data, phone_number, email_address, country, city, faculty, depart, studentIdentity, job);
            new Worker(studentIdentity, name, last_name, job);
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //the  username and passowrd database function
    public boolean Sign_in(String user_name,String password){
        String Sorgu="SELECT * FROM `adminler` WHERE `username`=? and `password`=?";
        try {
            preparedStatement=con.prepareStatement(Sorgu);
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2,password);
            
            ResultSet rs=preparedStatement.executeQuery();
            
            return rs.next();// if rs.next is true it return true and if false it return false
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
           // return false;
        }
        return false; // we can use that in catch block
    }
     //there we can get elements of only one row
    public ArrayList<Worker> getOneRow(String ident){
        String sorgu="Select * From student where identity_cord_No=?";
       
        ArrayList<Worker>output=new ArrayList<>();
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, ident);
            
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                Integer id=rs.getInt("id");
                String name=rs.getString("name");
                String last_name=rs.getString("last_name");
                String father_name=rs.getString("father_name");
                int year=rs.getInt("year");
                int sign_no=rs.getInt("sign_no");
                String sex=rs.getString("Sex");
                String date=rs.getString("date");
                String phone_number=rs.getString("phone_number");
                String email_address=rs.getString("email_address");
                String country=rs.getString("country");
                String city=rs.getString("city");
                String faculty=rs.getString("faculty");
                String department=rs.getString("department");
                String Identity=rs.getString("identity_cord_No");
                String job=rs.getString("Job");
                output.add(new Worker(id,name, last_name, father_name, year, sign_no, sex, date, phone_number, email_address, country, city, faculty, department, Identity, job));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public void DeleteData(String id){
        String sorgu="Delete From student where identity_cord_No=?";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
            
            System.out.println("id is:"+id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   // that function for Data which show in table in main view file 
    public ArrayList<Worker> InsertDataIntoTable(){
        ArrayList<Worker> output=new ArrayList<>();
        String sorgu="Select name,last_name,identity_cord_No,Job From student";
        
        try {
            preparedStatement=con.prepareStatement(sorgu);
            ResultSet rs=preparedStatement.executeQuery();
            while(rs.next()){
                String name=rs.getString("name");
                String last_name=rs.getString("last_name");
                String Id=rs.getString("identity_cord_No");
                String job=rs.getString("Job");
                
                output.add(new Worker(Id,name, last_name, job));
                
            }
           return output;
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    
    
    public void InsertWorker(String name, String last_name, String father_name, int year, int student_no,
            String sex, String birth_data, String phone_number, String email_address, String country, String city, String faculty, 
            String depart, String studentIdentity, String job){
        
        String Sorgu="Insert Into student (name,last_name,father_name,year,sign_no,Sex,date,phone_number,email_address,"
                + "country,city,faculty,department,identity_cord_No,Job) Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            preparedStatement=con.prepareStatement(Sorgu);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, father_name);
            preparedStatement.setInt(4, year);
            preparedStatement.setInt(5, student_no);
            preparedStatement.setString(6, sex);
            preparedStatement.setString(7, birth_data);
            preparedStatement.setString(8, phone_number);
            preparedStatement.setString(9, email_address);
            preparedStatement.setString(10, country);
            preparedStatement.setString(11, city);
            preparedStatement.setString(12, faculty);
            preparedStatement.setString(13, depart);
            preparedStatement.setString(14, studentIdentity);
            preparedStatement.setString(15, job);
            
            preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }
    
    //there we can read our data from extra file txt or other format file
    public  void  Data_in_file(){
        
        try(Scanner scanner=new Scanner(new BufferedReader(new FileReader("yes.txt")))){
           
            String name,last_name,father_name,sex,indentity_no,country,city,birth_date,phone_num,email,job;
            while (scanner.hasNextLine()) {
                String next = scanner.nextLine();
                String information[]=next.split(",");
                name=information[0];
                last_name=information[1];
                father_name=information[2];
                sex=information[3];
                indentity_no=information[4];
                country=information[5];
                city=information[6];
                birth_date=information[7];
                phone_num=information[8];
                email=information[9];
                job=information[10];
            }
             
        } catch (FileNotFoundException ex) {
            System.out.println(" file not found");
        }
       
    }
    
 

    
   
    
}