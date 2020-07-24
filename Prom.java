/*
 * Prom
 * Version: 1.0
 * Author: Theo & Dennis
 * Date: 02-16-2020
 * Description: Launches either the ticketing system or the floor plan system
 */

//import statements
//arraylists and arrays
import java.util.ArrayList;
import java.util.Arrays;
//frames,panels,buttons
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
//graphics
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
//Event Listeners
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//IO Stuff
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

//Main Class
class Prom extends JFrame {
    
    //Global Variables
    // main panels
    private TicketingSystem ticketingPanel;
    private FloorPlanSystem floorPlanPanel; 
    //main student and table arraylist
    private ArrayList<Student> students;
    private ArrayList<Table> tables;
    //graphics
    private JFrame thisFrame;
    //allows user to input # of tables and max students per table
    private JLabel labelTableNum;    
    private JLabel labelMaxStudentsPerTable;
    private JTextField tableNumField;
    private JTextField maxStudentsPerTableField;
    
    //Constructor
    /*
     * Prom()
     * Description: creates the prom interface when launched from the PromSemiLauncher
     * @param: null
     * @return: null
     */
    Prom() {
        super("Prom");
        this.thisFrame = this; 
        //create students and tables arraylist
        students = new ArrayList<Student>();
        tables = new ArrayList<Table>();
        
        //use IO methods to read from file and update arraylists.
        try {
            readFile();
            readPartnersFile();
        } catch (Exception E){}
    
        //configure the window
        this.setSize(900,900);    
        this.setLocation(0, 0); 
        this.setResizable (false);
    
        //Checks for closing the window and updates StudentsInfo and PartnersInfo textfile
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                createNewInfoFile();
                exitProcedure();
            }
        });
         
        //Create a Panel for stuff
        JPanel decPanel = new DecoratedPanel();
        decPanel.setBorder(new EmptyBorder(288, 68, 68, 68));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(0, 0, 0, 0));
        mainPanel.setPreferredSize(new Dimension(640, 400));  
    
        //listens for keyboard clicks
        MyKeyListener keyListener = new MyKeyListener();
        this.addKeyListener(keyListener);
    
        //Create JLabels and Textfields for finding venue info (#of tables, max students per table)
        JLabel labelTableNum = new JLabel("Insert # of tables for venue:");
        JLabel labelMaxStudentsPerTable = new JLabel("Insert max students per table:");
        labelTableNum.setForeground(Color.YELLOW);
        labelMaxStudentsPerTable.setForeground(Color.YELLOW);
        tableNumField = new JTextField(8);
        maxStudentsPerTableField = new JTextField(8);
    
        //JButtons
        //studentbutton that launches ticketing system interface
        ImageIcon sb = new ImageIcon("Photos/TicketingButton.png");
        JButton studentButton = new JButton(sb);
        studentButton.setBackground(Color.black);
        studentButton.setBorder(BorderFactory.createEmptyBorder());
        studentButton.setFocusPainted(false);
        
        //adminbutton that launches the seating assignment system and floorplan system
        ImageIcon ab = new ImageIcon("Photos/SeatingButton.png");
        JButton adminButton = new JButton(ab);
        adminButton.setBackground(Color.black);
        adminButton.setBorder(BorderFactory.createEmptyBorder());
        adminButton.setFocusPainted(false);
        
        //add action listener's to buttons
        studentButton.addActionListener(new StudentButtonListener());
        adminButton.addActionListener(new AdminButtonListener());
        
        //add buttons,jlabels,and jtextfields to panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setPreferredSize(new Dimension(200,400));
        bottomPanel.setBackground(Color.black);
        bottomPanel.add(studentButton);
        bottomPanel.add(adminButton);
        bottomPanel.add(labelTableNum);
        bottomPanel.add(tableNumField);
        bottomPanel.add(labelMaxStudentsPerTable);
        bottomPanel.add(maxStudentsPerTableField);
        
        //Add all panels to the mainPanel according to border layout
        mainPanel.add(bottomPanel,BorderLayout.EAST);
        decPanel.add(mainPanel);
        
        //add the main panel to the frame
        this.add(decPanel);
        
        //Start the app
        this.setVisible(true);
    }
    //End of Constructor
  
    //INNER CLASSES
    
    /**
     * DecoratedPanel
     * This inner class draws some of the graphics for the Prom interface & Overides Paint Component for JPANEL
     * @param: for constructor none
     * @return: nothing
     */
    private class DecoratedPanel extends JPanel {
        //Constructor
        DecoratedPanel() {
            this.setBackground(new Color(0,0,0,0));
        }
        /**
         * paintComponent()
         * Description: draws graphics and adds pictures for the panels
         * @param: Graphics g
         * @return: null
         */
        public void paintComponent(Graphics g) { 
            super.paintComponent(g);     
            g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            g.setColor(Color.yellow);
        
            //draw strings
            g.drawString("Welcome to the RHHS Prom/Semi-Formal Ticketing and Seating System!",550,200);
            g.drawString("Please click on Ticketing System if you are a student",550,230);
            g.drawString("Please click on Seating Assignment if you want to see the floor plan system",550,260);
        
            //add pictures and draw them
            thisFrame.setBackground(new Color(0,0,0));
            Image promLogo = new ImageIcon( "Photos/Logo.jpg" ).getImage();
            Image rhhsLogo = new ImageIcon( "Photos/RHHSLogo.jpg" ).getImage();
            g.drawImage(promLogo, 10, 10, null);
            g.drawImage(rhhsLogo, 625,10,null);
        } 
    } // End of INNER CLASS - Overide Paint Component for JPANEL
  
    /**
     * MyKeyListener
     * Checks for key presses
     * @param: null 
     * @return: null
     */
    private class MyKeyListener implements KeyListener {
        public void keyTyped(KeyEvent e) {  
        } 
        public void keyReleased(KeyEvent e) {
        } 
        public void keyPressed(KeyEvent e) { 
            if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                //If ESC is pressed create new file, exit
                createNewInfoFile();  
                System.exit(0);
            } else {
                thisFrame.dispose();
            }    
        }  
    } // end of INNER CLASS - checks for key presses
  
    /**
     * StudentButtonListener
     * checks if the student button is clicked to launch the ticketing system
     * @param: null
     * @return: null
     */
    class StudentButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            // launches ticketing panel
            ticketingPanel = new TicketingSystem(students); 
        }
    } // end of INNER CLASS - checks for ticketing system button click
  
    /**
     * AdminButtonListener
     * checks if the admin button is clicked to launch the seating assignment system
     * @param: null
     * @return: null
     */
    class AdminButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event){
            //get students that have paid
            ArrayList<Student> paidStudents = new ArrayList<Student>();
            for (int i = 0; i < students.size(); i++){
                if ( (students.get(i)).hasPaid() == true ){
                    paidStudents.add(students.get(i));
                }
            }
            //get inputted venue info (table # and table size)
            String tableNumString = tableNumField.getText();
            String studentsPerTableString = maxStudentsPerTableField.getText();
      
            //create tables
            //make sure user filled out both prompts
            if (tableNumString != null && studentsPerTableString != null){
                int tableNum = Integer.parseInt(tableNumString);
                int studentsPerTable = Integer.parseInt(studentsPerTableString);
                //run algorithm (Put Andy's part here)
                tables = SeatingAssignmentSystem.assignTables(paidStudents,tableNum,studentsPerTable ); 
                for (int i = 0; i < tables.size(); i++){
                    ArrayList<Student> assignedTables = (tables.get(i)).getStudents();
                    for (int j = 0; j < assignedTables.size(); j++){
                        System.out.println( (assignedTables.get(j)).getName() );
                    }
                    System.out.println();
                }
                //create floorplansystem (Put Jerry/Sunny's part here)
                floorPlanPanel = new FloorPlanSystem(tables);
            }
        }
    } // END OF INNER CLASS - CHECK FOR SEATING ASSIGNMENT BUTTON CLICK
 
    //Methods
    
    /**
     * readFile()
     * Description: reads the StudentsInfo.txt file and creates students
     *              based on already registered student after reopening the Prom System
     * @param: null
     * @return: null
     */
    public void readFile () {
        try{
            //create reader
            BufferedReader input1 = new BufferedReader(new FileReader("Files/StudentsInfo.txt"));
            String currentLine;
            // for every line in the text file read all 5 pieces of info
            while ((currentLine = input1.readLine()) != null){
                String name;
                String id;
                String username;
                String password;
                String paid;
                
                //create array for positions of white spaces
                //note: we used white spaces in case the username/password has commas
                //      this avoids any messed up file io
                int[] whiteSpacePosition = new int[5];
                int position = 0;
                for (int i = 0; i < currentLine.length()-1; i++){
                    if ((currentLine.substring(i,i+1)).equals(" ")){
                         whiteSpacePosition[position] = i;
                         position++;
                    }
                }
                //use substrings to split all required info
                name = currentLine.substring(0,whiteSpacePosition[1]);
                id = currentLine.substring(whiteSpacePosition[1]+1,whiteSpacePosition[2]);
                username = currentLine.substring(whiteSpacePosition[2]+1,whiteSpacePosition[3]);
                password = currentLine.substring(whiteSpacePosition[3]+1, whiteSpacePosition[4]);
                paid = currentLine.substring(whiteSpacePosition[4]+1, currentLine.length());
                boolean paidBoolean = Boolean.parseBoolean(paid);
                
                //add the student to the arraylist
                Student s = new Student(name,id,username,password,paidBoolean);
                students.add(s);
            }
            input1.close(); //close reader
        } catch (Exception E){}
    }
    //End of readFile() Method
  
    /**
     * readPartnersFile()
     * Description: reads the partner file and adds partners to each
     *              student's partner arraylist
     * @param: null
     * @return: null
     */
    public void readPartnersFile() throws Exception {
        //create reader
        BufferedReader input2 = new BufferedReader(new FileReader("Files/PartnersInfo.txt"));
        String currentLine2;
        for (int count = 0; count < students.size(); count++){
            currentLine2 = input2.readLine();
            //if the line says null that means student has no partners, if it doesn't the reader will check for partners
            if (!currentLine2.equals("null")){ 
                ArrayList<Student> temp = new ArrayList<Student>();
                //splits string by commas into an arraylist
                ArrayList<String> partners = new ArrayList<>(Arrays.asList(currentLine2.split(","))); 
              
                for (int i = 0; i < partners.size(); i++){
                    for (int j = 0; j < students.size(); j++){
                        //add the student to the arraylist if they exist
                        if ( ( (students.get(j) ).getName() ).equals( partners.get(i) ) ){
                            temp.add(students.get(j)); 
                        }
                    }
                }
                //add the partners arraylist to the student
                students.get(count).setPartners(temp); 
            }
        }
        input2.close(); // close reader
    }
    //end of readPartnersFile() method
  
    /**
     * createNewInfoFile()
     * Description: upon closing the prom system, this method updates the info
     *              for both the StudentsInfo.txt and PartnersInfo.txt
     * @param: null
     * @return: null
     */
    public void createNewInfoFile(){
        try{
            //clear all previous info by using printing a blank "" using a printwriter in both the students and partners textfiles.
            PrintWriter pw = new PrintWriter("Files/StudentsInfo.txt");
            pw.print("");
            pw.close();
            PrintWriter pw2 = new PrintWriter("Files/PartnersInfo.txt");
            pw2.print("");
            pw2.close();
      
            //create new output stream for both files
            FileWriter outputStudentInfo = new FileWriter("Files/StudentsInfo.txt");
            FileWriter outputPartnerInfo = new FileWriter("Files/PartnersInfo.txt");
            
            //output student info into the "StudentsInfo.txt"
            for (int i = 0; i < students.size(); i++){
                Student placeholder = students.get(i);
                 outputStudentInfo.write(placeholder.getName()+" "+placeholder.getId()+" "
                                         +placeholder.getUserName()+" "+placeholder.getPassword()+" "+Boolean.toString(placeholder.hasPaid())+"\n");
        
                 if (placeholder.getPartners().size() < 1){
                     //if no partners write 'null'
                     outputPartnerInfo.write("null\n"); 
                 } else {
                     for (int j = 0; j < (placeholder.getPartners()).size(); j++){
                         //if there are partners, write out names seperated by commas
                         outputPartnerInfo.write(placeholder.getPartners().get(j).getName()+","); 
                     }
                     //seperate each student's partners with a new line
                     outputPartnerInfo.write("\n");
                 } 
             }
             outputStudentInfo.close();  //close writers
             outputPartnerInfo.close();
         } catch (Exception E){}
     }
    // end of createNewInfoFile Method
    
    /**
     * exitProcedure()
     * Description: disposes of the frame and exits the system
     * @param: null
     * @return: null
     */
    public void exitProcedure(){
        thisFrame.dispose();
        System.exit(0);
     }
    //end of exitProcedure method
}
//end of Prom Class