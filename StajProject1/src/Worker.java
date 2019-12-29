 
public class Worker {
     private Integer id;
     private String name;
     private String last_name;
     private String F_name;
     private int year;
     private int Stu_no_year;
     private String indentity_cord_No;
     private String sex;
     private String date;
     private String phone_No;
     private String email;
     private String Country;
     private String City;
     private String Faculty;
     private String Department;
     private String job;
    
    public Worker(String indentity_cord_No,String name,String last_name,String job){
        this.name=name;
        this.last_name=last_name;
        this.indentity_cord_No=indentity_cord_No;
        this.job=job;
    } 
   

    Worker(String name, String last_name, String father_name, int year, int student_no,
            String sex, String birth_data, String phone_number, String email_address, String country, String city, String faculty, String depart, String studentIdentity, String job) {
        this.name = name;
        this.last_name = last_name;
        this.F_name = father_name;
        this.year = year;
        this.Stu_no_year = student_no;
        this.sex = sex;
        this.date = birth_data;
        this.phone_No = phone_number;
        this.email = email_address;
        this.Country = country;
        this.City = city;
        this.Faculty = faculty;
        this.Department = depart;
        this.indentity_cord_No=studentIdentity;
        this.job=job;
    }
    Worker(Integer id,String name, String last_name, String father_name, int year, int student_no,
            String sex, String birth_data, String phone_number, String email_address, String country, String city, String faculty, String depart, String studentIdentity, String job) {
        this.id=id;
        this.name = name;
        this.last_name = last_name;
        this.F_name = father_name;
        this.year = year;
        this.Stu_no_year = student_no;
        this.sex = sex;
        this.date = birth_data;
        this.phone_No = phone_number;
        this.email = email_address;
        this.Country = country;
        this.City = city;
        this.Faculty = faculty;
        this.Department = depart;
        this.indentity_cord_No=studentIdentity;
        this.job=job;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
 
   
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getF_name() {
        return F_name;
    }

    public void setF_name(String F_name) {
        this.F_name = F_name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getStu_no_year() {
        return Stu_no_year;
    }

    public void setStu_no_year(int Stu_no_year) {
        this.Stu_no_year = Stu_no_year;
    }

    

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPhone_No() {
        return phone_No;
    }

    public void setPhone_No(String phone_No) {
        this.phone_No = phone_No;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public String getFaculty() {
        return Faculty;
    }

    public void setFaculty(String Faculty) {
        this.Faculty = Faculty;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String Department) {
        this.Department = Department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getIndentity_cord_No() {
        return indentity_cord_No;
    }

    public void setIndentity_cord_No(String indentity_cord_No) {
        this.indentity_cord_No = indentity_cord_No;
    }


}
