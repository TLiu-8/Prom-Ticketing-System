/*
 * Student
 * Version: 1.0
 * Author: Theo Liu, Dennis Chizhov
 * Date: 2020-02-13
 * Description: Template that creates student objects
 */

//import statements
import java.util.ArrayList;

public class Student {
    //global variables
    private String name;
    private String id;
    private ArrayList<Student> partners;
    private boolean paid;
    //variables that allow student to login and edit their account
    private String userName;
    private String password;
  
    //Constructors
  
    /**
     * Student Constructor 1
     * Description: Creates a student with partners
     * @param: name, id and arraylist of partners
     * @return: null
     */
    public Student(String name, String id, ArrayList<Student> partners){
        this.name = name;
        this.id = id;
        this.partners = partners;
    }
    
    /**
     * Student Constructor 2
     * Description: Creates a student without partners
     * @param: the name and id of the person in two Strings
     * @return: null
     */
    public Student(String name, String id){
        this.name = name;
        this.id = id;
        partners = new ArrayList<Student>();
    }
    
    //Additional constructors for implementing username and password
    /**
     * Student Constructor 3
     * Description: Creates a student without partners but with a username and password and status of payment
     * @param: the name,id, new username and password, and whether or not they paid of the person in Strings
     * @return: null
     */
    public Student(String name, String id,String userName, String password, boolean paid){
        this.name = name;
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.paid = paid;
        partners = new ArrayList<Student>();
    }
  
    /**
     * Student Constructor 4
     * Description: Creates a student without partners but with a username and password and status of payment
     * @param: the name,id, new username and password, and whether or not they paid of the person in Strings
     * @return: null
     */
    public Student(String name, String id,String userName, String password){
        this.name = name;
        this.id = id;
        this.userName = userName;
        this.password = password;
        partners = new ArrayList<Student>();
    }
  
    //Methods
    
    /**
     * getName()
     * Description: gets the name of the student
     * @param: null
     * @return: String of the student's name
     */
    public String getName(){
        return name;
    }
  
    /**
     * getId()
     * Description: gets the id of the student
     * @param: null
     * @return: String of the student's id
     */
    public String getId(){
        return id;
    }
  
    /**
     * getPartners()
     * Description: gets the arraylist of the partners of the student
     * @param: null
     * @return: an arraylist of different refrences to other students that represent the student's partners
     */
    public ArrayList<Student> getPartners(){
        return partners;
    }
  
    /**
     * setPartners()
     * Description: sets the partners of the student
     * @param: ArrayList<Student> for partners
     * @return: null
     */
    public void setPartners(ArrayList<Student> partners){
        this.partners = partners;
    } 
  
    /**
     * hasPaid()
     * Description: returns true if the student has paid or false if not
     * @param: null
     * @return: boolean paid
     */
    public boolean hasPaid(){
        return paid;
    }
  
    /**
     * setPaid()
     * Description: sets if the student has paid or not
     * @param: boolean paid
     * @return: null
     */
    public void setPaid(boolean hasPaid){
         paid = hasPaid;
    }
  
    /**
     * equals()
     * Description: checks if the student has the same attributes (id and string) as the given student
     * @param: The given student object
     * @return: boolean on whether the student is the same (true) or not (false)
     */
    public boolean equals(Student s){
        if ( (s.getName()).equals(name) && ( (s.getId()).equals(id)) ) {
            return true;
        } else {
            return false;
        }
    }
  
    /**
     * getUserName()
     * Description: gets the userName of the student
     * @param: null
     * @return: the userName String
     */
    public String getUserName(){
        return userName;
    }
  
    /**
     * getPassword()
     * Description: gets the password of the student
     * @param: null
     * @return: the password String
     */
    public String getPassword(){
        return password;
    }
    
    /**
    * toString()
     * Description: returns a string of the formatted info of the student
     * @param: null
     * @return: the new formatted string
     */
    @Override
    public String toString() {
        return String.format("(%s, ID: %s)", this.name, this.id);
    }  
}
// end of Student Class