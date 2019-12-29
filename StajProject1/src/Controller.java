

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Action;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField; 
import javax.swing.table.DefaultTableModel;


public class Controller {
    View view=new View();
    Sign_up sign_up=new Sign_up();
    Model model;
    //Buttons
    JButton Insert_button;
    JButton Update_button;
    JButton Delete_button;
    JButton Insert_sig_Button;
    JButton Back_Sig_Button;
    JButton Update_sig_Button;
    
    DefaultTableModel tableModel;
    JTable table_list;
    JLabel text_message;
    JTextField search_field_state;
    //ComboBoxs
    JComboBox<String> Faculty_coBox;
    JComboBox<String> Department_coBox;
    JComboBox<String> Country_coBox;
    JComboBox<String> City_coBox;
    //other fields
   
    String name;
    String last_name;
    String father_name;
    String Student_id;
    String phone_number;
    String email_address;
    String search_field;
    String sex;
    
    String faclulty;
    String depart;
    String country;
    String City;
    String studentIdentity;
    String job="Student";
    Integer id; 
    Integer year;
    Integer student_no;
    
    //MenuItems
    JMenuItem open_file;
    JMenuItem save_file;
    JMenuItem close_file;
        
    public Controller(){
          //buttons or other fields in view file
          this.Insert_button=this.view.getInsert_Button();
          this.Update_button=this.view.getUpdate_button();
          this.Delete_button=this.view.getDelete_Button();
          this.text_message=this.view.getMessage_text();
          this.search_field=this.view.getSearching_field().toString();
          
          //menus in view File
          this.open_file=(JMenuItem)this.view.getOpenFile_item();
          this.save_file=(JMenuItem)this.view.getSavetofile_item();
          this.close_file=(JMenuItem)this.view.getClose_item();
          
          //buttons and other fields in sign_in file 
          this.Insert_sig_Button=this.sign_up.getInsert_button2();
          this.Back_Sig_Button=this.sign_up.getBack_button();
          this.Update_sig_Button=this.sign_up.getUpdate_button();
          JRadioButton rdb1=this.sign_up.getFemale_radioButton();
          JRadioButton rdb2=this.sign_up.getMale_radioButton();
          this.sex="Male";
    
          //tables in view file and alse insert data to this table
          this.tableModel=(DefaultTableModel)this.view.getTable_list().getModel();
          this.table_list=this.view.getTable_list();
          this.model=new Model();
          InsertInToTable();// that is for showing data in view file's table
       
          this.view.setVisible(true);   
          
          //Insert button in view file
          this.Insert_button.addActionListener((ActionEvent ae)->{
              
              this.text_message.setText("Insert Button");
              this.sign_up.setVisible(true);
              Set_data_for_defualt();
              Sign_Insert_Button();
             
              
          });
          
          // update button in view file
          this.Update_button.addActionListener((ActionEvent ae)->{
              this.text_message.setText("Update Button");
              UpdateData();
              
              
          });
          
          // Delete Button in view file
          this.Delete_button.addActionListener((ActionEvent ae)->{
               DeleteData();
              
          });
          
          // file menu items in view file
          this.open_file.addActionListener((ActionEvent ae)->{
              JFileChooser fc=new JFileChooser();
              int i=fc.showOpenDialog(this.view);
              
          });
 

        }
          
    
    public void UpdateData(){
        int selectedRow=this.table_list.getSelectedRow();
        if(selectedRow==-1){
            if(this.tableModel.getRowCount()==0){
                this.text_message.setText("Your table is empty");
            }
            else{
                this.text_message.setText("Please Select any row:");
            }
        }else{
            String row=this.tableModel.getValueAt(selectedRow,0).toString();
            SelectedRowSetTo_Sign_up(row);
            this.sign_up.setVisible(true);
            
            this.Update_sig_Button.addActionListener((ActionEvent ae)->{
                
                GetData();
                System.out.println("this.identity is:::"+this.studentIdentity);
                this.model.Update_Data(this.id,this.name, this.last_name, this.father_name, this.year, this.student_no, this.sex, this.birth_data, this.phone_number,
                        this.email_address, this.country, this.City, this.faclulty, this.depart, this.studentIdentity, this.job);
                
                
                InsertInToTable();// that is for showing data in view file's table
                this.text_message.setText("The Worker was Updated");
            });
        }

     }
    
    public void SelectedRowSetTo_Sign_up(String row){
        ArrayList<Worker> worker=new ArrayList<Worker>();
        worker=this.model.getOneRow(row);
        
        if(worker!=null){
               String sex="";
               ButtonModel buttonModel;

               for(Worker w:worker){
                   //Object[]ekelenecek={calisan.getIndentity_cord_No(),calisan.getName(),calisan.getLast_name(),calisan.getJob()};
                   this.id=w.getId();
                   this.sign_up.getName_field().setText(w.getName());
                   this.sign_up.getLastN_field().setText(w.getLast_name());
                   this.sign_up.getFather_field().setText(w.getF_name());
                   this.sign_up.getYear_field().setText(String.valueOf(w.getYear()));
                   this.sign_up.getStudent_num_field().setText(String.valueOf(w.getStu_no_year()));
                   
                   if(w.getSex().equals(this.sign_up.getMale_radioButton().toString())){
                       this.sign_up.getMale_radioButton().setSelected(true);
                   }
                   else if(w.getSex().equals(this.sign_up.getFemale_radioButton().toString())){
                       this.sign_up.getFemale_radioButton().setSelected(true);
                   }
                   this.sign_up.getBith_date_field().setText(w.getDate());
                   this.sign_up.getPhone_field().setText(w.getPhone_No());
                   this.sign_up.getEmail_field().setText(w.getEmail());
                   this.sign_up.getCountry_combobox().setSelectedItem(w.getCountry());
                   this.sign_up.getCity_comboBox().setSelectedItem(w.getCity());
                   this.sign_up.getFaculty_comboBox().setSelectedItem(w.getFaculty());
                   this.sign_up.getDepartment_comboBox().setSelectedItem(w.getDepartment());

               }
         }
        else{
            System.out.println("The worker is null");
        }
    }
    
    public void DeleteData(){
        int selectedRow=this.table_list.getSelectedRow();
        if(selectedRow==-1){
            if(this.tableModel.getRowCount()==0){
                this.text_message.setText("Your table is empty");
            }
            else{
                this.text_message.setText("Please Select any row:");
            }
        }else{
            String row=this.tableModel.getValueAt(selectedRow,0).toString();
           
            this.model.DeleteData(row);
            InsertInToTable();// that is for showing data in view file's table
            this.text_message.setText("The Worker was deleted");

        }

    }
    
    public void InsertInToTable(){
        this.tableModel.setRowCount(0);

        ArrayList<Worker> worker=new ArrayList<Worker>();
        worker=this.model.InsertDataIntoTable();
        if(worker!=null){

            for(Worker calisan:worker){
                Object[]ekelenecek={calisan.getIndentity_cord_No(),calisan.getName(),calisan.getLast_name(),calisan.getJob()};
//                System.out.println("Student id in InsertInoTable function:"+calisan.getIndentity_cord_No());
                this.tableModel.addRow(ekelenecek);
            }
        }
        
     }
    
    public void Set_data_for_defualt(){
        this.sign_up.getFaculty_comboBox().setSelectedIndex(0);
        this.sign_up.getFaculty_comboBox().setSelectedIndex(0);
        this.sign_up.getDepartment_comboBox().setSelectedIndex(0);
        this.sign_up.getCountry_combobox().setSelectedIndex(0);
        this.sign_up.getCity_comboBox().setSelectedIndex(0);
        this.sign_up.getName_field().setText("");
        this.sign_up.getLastN_field().setText("");
        this.sign_up.getFather_field().setText("");
        this.sign_up.getBith_date_field().setText("");
        this.sign_up.getYear_field().setText("");
        this.sign_up.getStudent_num_field().setText("");
        this.sign_up.getPhone_field().setText("");
        this.sign_up.getEmail_field().setText("");
    }
    
    public void GetData(){
         try {
                 this.faclulty=(String)this.sign_up.getFaculty_comboBox().getSelectedItem();
                 this.depart=(String)this.sign_up.getDepartment_comboBox().getSelectedItem();
                 this.country=(String)this.sign_up.getCountry_combobox().getSelectedItem();
                 this.City=(String)this.sign_up.getCity_comboBox().getSelectedItem();
                 this.name=this.sign_up.getName_field().getText();
                 this.last_name=this.sign_up.getLastN_field().getText();
                 this.father_name=this.sign_up.getFather_field().getText();
                 this.birth_data=this.sign_up.getBith_date_field().getText();
                 this.year=Integer.valueOf(this.sign_up.getYear_field().getText());
                 this.student_no=Integer.valueOf(this.sign_up.getStudent_num_field().getText());
                 this.phone_number=this.sign_up.getPhone_field().getText();
                 this.email_address=this.sign_up.getEmail_field().getText();

                 selectedFaculty();
                 selectedDepartment();
                 Student_no();
                 this.studentIdentity=Student_Id();
                 Worker worker=new Worker(this.name,this.last_name,this.father_name,this.year, this.student_no,this.sex,
                         this.birth_data,this.phone_number, this.email_address, 
                         this.country,this.City,this.faclulty,this.depart,this.studentIdentity,this.job);

              } catch (NumberFormatException e) {
                   //this.sign_up.getMessage_text().setText("Number format is not correct");
                    e.printStackTrace();
              }
    }
    
    public void Sign_Insert_Button(){

       this.Insert_sig_Button.addActionListener((ActionEvent ae)->{ 
                  GetData();

                  this.model.InsertWorker(name,last_name,father_name,year, student_no,this.sex, birth_data,phone_number, email_address, 
                         country,City,faclulty,depart,studentIdentity,job);
                         InsertInToTable();// that is for showing data in view file's table

        });

       this.Back_Sig_Button.addActionListener((ActionEvent ae)->{
           this.sign_up.setVisible(false);
       });
    }

   public void student_number(String s){
        
        String year=String.valueOf(s.charAt(0))+String.valueOf(s.charAt(1));
        String faculty=String.valueOf(s.charAt(2))+String.valueOf(s.charAt(3));
        String department=String.valueOf(s.charAt(5))+String.valueOf(s.charAt(6));
        String student_no=String.valueOf(s.charAt(7))+String.valueOf(s.charAt(8)+String.valueOf(s.charAt(9)));
        
        
//        faculty_file(Integer.valueOf(faculty));
//        department_file(Integer.valueOf(department));
//        
  
    }
   
   
   public String selectedFaculty(){
          String faculty_number="";
          String selectedFacult=this.sign_up.getFaculty_comboBox().getSelectedItem().toString();
          
          Map< ArrayList<String>,String> map = new HashMap<ArrayList<String>,String>();
          
          List<String> list1=Arrays.asList("Latter");
          ArrayList<String> otherList1=new ArrayList<>();
          otherList1.addAll(list1);
          
          List<String> list2=Arrays.asList("Economics and Administrative Sciences");
          ArrayList<String> otherList2=new ArrayList<>();
          otherList2.addAll(list2);
          
          List<String> list3=Arrays.asList("Communication");
          ArrayList<String> otherList3=new ArrayList<>();
          otherList3.addAll(list3);
          
          List<String> list4=Arrays.asList("Engineering");
          ArrayList<String> otherList4=new ArrayList<>();
          otherList4.addAll(list4);
          
          List<String> list5=Arrays.asList("Language");
          ArrayList<String> otherList5=new ArrayList<>();
          otherList5.addAll(list5);
          
          List<String> list6=Arrays.asList("Medicine");
          ArrayList<String> otherList6=new ArrayList<>();
          otherList6.addAll(list6);
          
          List<String> list7=Arrays.asList("SCHOOL OF TOURISM AND HOTELING");
          ArrayList<String> otherList7=new ArrayList<>();
          otherList7.addAll(list7);
          
          List<String> list8=Arrays.asList("Agriculture");
          ArrayList<String> otherList8=new ArrayList<>();
          otherList8.addAll(list8);
          
          List<String> list9=Arrays.asList("Veterinary");
          ArrayList<String> otherList9=new ArrayList<>();
          otherList9.addAll(list9);
          
          List<String> list10=Arrays.asList("Fine Arts");
          ArrayList<String> otherList10=new ArrayList<>();
          otherList10.addAll(list10);
          
          List<String> list11=Arrays.asList("HIGH SCHOOL OF PHYSICAL EDUCATION AND SPORTS");
          ArrayList<String> otherList11=new ArrayList<>();
          otherList11.addAll(list11);
          
          List<String> list12=Arrays.asList("Sciences");
          ArrayList<String> otherList12=new ArrayList<>();
          otherList12.addAll(list12);
          
          List<String> list13=Arrays.asList("Fine Arts Resim");
          ArrayList<String> otherList13=new ArrayList<>();
          otherList13.addAll(list13);
          
          List<String> list14=Arrays.asList("Theology");
          ArrayList<String> otherList14=new ArrayList<>();
          otherList14.addAll(list14);
          
          map.put(otherList1,"01");
          map.put(otherList2,"02");
          map.put(otherList3,"03");
          map.put(otherList4,"04");
          map.put(otherList5,"05");
          map.put(otherList6,"06");
          map.put(otherList7,"07");
          map.put(otherList8,"08");
          map.put(otherList9,"09");
          map.put(otherList10,"10");
          map.put(otherList11,"11");
          map.put(otherList12,"12");
          map.put(otherList13,"13");
          map.put(otherList14,"14");
          
        for(Map.Entry<ArrayList<String>,String> entry:map.entrySet()){
             if(selectedFacult.equals(entry.getKey().get(0))){
                 faculty_number=entry.getValue();
             }
        }
        String faculty_name="";
        for(Map.Entry<ArrayList<String>,String> entry:map.entrySet()){
            
            if(selectedFacult.equals(entry.getKey().get(0))){
                 faculty_name=entry.getKey().get(0);
             }
        }
        
       // setFaculty_name(faculty_name);
        System.out.println("faculty numer::"+faculty_number);
        return faculty_number;                     
    }
       
    
   public String selectedDepartment(){
        String selectedItem=this.sign_up.getDepartment_comboBox().getSelectedItem().toString();
        String selecDept="";
        Map< ArrayList<String>,String> map = new HashMap<ArrayList<String>,String>();
        
        List<String> list1=Arrays.asList("Department of Horticulture and Agronomy","Department of Journalism","Department of Economy",
               "Department of Computer Engineering","Department of Painting","Department of Western Languages","Department of Mathematics",
               "Department of Islamic Studies");
        ArrayList<String> otherList1=new ArrayList<>();
        otherList1.addAll(list1);
        
        List<String> list2=Arrays.asList("Department of Plant Protection","Department of Public Relations and Advertising",
                "Department of Management","Department of Chemical Engineering","Department of Performing Arts","Department of Eastern Languages",
                "Department of Applied Mathematics and Informatics","Department of Religious Studies");
        ArrayList<String> otherList2=new ArrayList<>();
        otherList2.addAll(list2);
        
        List<String> list3=Arrays.asList("Department of Animal Science","Department of Radio, Television and Cinema","Department of Finance","Department of Food Engineering",
            "Department of Educational Science","Department of Biology");
        ArrayList<String> otherList3=new ArrayList<>();
        otherList3.addAll(list3);
        
        List<String> list4=Arrays.asList("Department of International Relations","Department of Ecological Engineering","Department of Philosophy");
        ArrayList<String> otherList4=new ArrayList<>();
        otherList4.addAll(list4);
        
        List<String> list5=Arrays.asList("Department of Finance and Banking","Department of Industry Engineering","Department of Sociology");
        ArrayList<String> otherList5=new ArrayList<>();
        otherList5.addAll(list5);
        
        List<String> list6=Arrays.asList("Department of Electrical Engineering","Department of History");
        ArrayList<String> otherList6=new ArrayList<>();
        otherList6.addAll(list6);

        List<String> list7=Arrays.asList("Department of Turkology");
        ArrayList<String> otherList7=new ArrayList<>();
        otherList7.addAll(list7);
        
        List<String> list8=Arrays.asList("Department of Translations");
        ArrayList<String> otherList8=new ArrayList<>();
        otherList8.addAll(list8);
        
        map.put(otherList1,"01");
        map.put(otherList2,"02");
        map.put(otherList3,"03");
        map.put(otherList4,"04");
        map.put(otherList5,"05");
        map.put(otherList6,"06");
        map.put(otherList7,"07");
        map.put(otherList8,"09");
        
         
        for(Map.Entry<ArrayList<String>,String> entry:map.entrySet()){
            for(int i=0;i<entry.getKey().size();i++){
                if(selectedItem.equals(entry.getKey().get(i))){
                    selecDept=entry.getValue();
                }
            }
        }
       
        String dept_name="";
        for(Map.Entry<ArrayList<String>,String> entry:map.entrySet()){
            
            for(int i=0;i<entry.getKey().size();i++){
                if(selectedItem.equals(entry.getKey().get(i)));
                {
                    dept_name=entry.getKey().get(i);
                }break;
            }
        }
       // setDepartment_name(department_name);
     
        return selecDept;
    }
    
   public String Student_no(){
        String stu="";        
        Integer stu_no=Integer.valueOf(this.sign_up.getStudent_num_field().getText());
        if(stu_no<10){
            stu="00"+String.valueOf(stu_no);
        }
        else if(stu_no<100){
            stu="0"+String.valueOf(stu_no);
        }
        else if(stu_no<1000){
            stu=String.valueOf(stu_no);
        }
       return stu;
    }
    
   public String Student_Id(){
         String student_Id="";
         student_Id=String.valueOf(Integer.valueOf(this.sign_up.getYear_field().getText())%100)+selectedFaculty()+"."+selectedDepartment()+Student_no();
        return student_Id;
    }

public static void main(String args[]) {
    Controller controller=new Controller();
}
    
}
    

