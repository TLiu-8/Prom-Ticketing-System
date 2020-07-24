/*
 * Table
 * Version: 1.0
 * Author: Dennis and Theo
 * Date: 14/02/2020
 * Description: An instance of a table serves as an entity wherein multiple students will congregate at. 
 */

//import statements
import java.util.ArrayList;
import java.util.Iterator;

public class Table {
    //global variables
    private int size;
    private ArrayList<Student> students;
    private int x;
    private int y;
  
    //Constructor
    /**
     * Table Constructor
     * Description: creates the table with its size
     * @param: the max # of students that can fit on a table
     * @return: null
     */
    public Table(int size) {
        this.size = size;
        this.students = new ArrayList<Student>();
        this.x = 0;
        this.y = 0;
    }
    
    //Methods
    
    /**
     * getSize()
     * Description: gets the # of students that can fit at a table
     * @param: null
     * @return: the int of the size of the table
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * addStudent()
     * Description: adds a student to the arraylist of students at the table
     * @param: the student to be added
     * @return: null
     */
    public void addStudent(Student s){
        students.add(s);
    }
    
    /**
     * removeStudent()
     * Description: removes a student from the arraylist of students at the table
     * @param: the student to be removed
     * @return: null
     */
    public void removeStudent(Student s){
        students.remove(s);
    }
    
    /**
     * getStudents()
     * Description: gets the whole arraylist of students seated at the table
     * @param: null
     * @return: the arraylist of students
     */
    public ArrayList<Student> getStudents() {
        return this.students;
    }
    
    /**
     * setStudents()
     * Description: sets the people to be seated at the table
     * @param: the arraylist of students to be added to th etable
     * @return: null
     */
    public void setStudents(ArrayList<Student> students){
        this.students = students;
    }
    
    /**
     * getX()
     * Description: gets the x position of the table
     * @param: null
     * @return: integer value of x position.
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * setX()
     * Description: sets the x position of the table
     * @param: the new x value to be set
     * @return: null
     */
    public void setX(int x){
        this.x = x;
    }
    
    /**
     * getY()
     * Description: sets the y position of the table
     * @param: null
     * @return: integer value of the y position
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * setY()
     * Description: sets the y position of the table
     * @param: the new y value to be set
     * @return: null
     */
    public void setY(int y){
        this.y = y;
    }
    
    /**
     * isFull()
     * Description: determines if the table is full or not
     * @param: null
     * @return: boolean value, true if the size is equal to the arraylist size
     */
    public boolean isFull() { 
        return this.getStudents().size() == this.getSize();
    } 
    
    /**
     * containsStudent()
     * Description: checks if the table has a certain student or not
     * @param: the student that the method looks for
     * @return: boolean true or false if he/she was found or not
     */
    public boolean containsStudent(Student s) {
        return this.students.contains(s);
    }
    
    /**
     * toString()
     * Description: returns a string of the formatted info of the table
     * @param: null
     * @return: formatted string of students at table
     */
    @Override
    public String toString() {
        String out = "{";
        int i = 0;
        for (final Student s : this.students) {
            out += s.toString();
            if (i < this.size - 1) {
                out += ", ";
            }
            ++i;
        }
        for (int j = i; j < this.size; ++j) {
            out += "empty";
            if (i < this.size - 1) {
                out += ", ";
            }
        }
        out += "}";
        return out;
    }
}
// end of Table Class