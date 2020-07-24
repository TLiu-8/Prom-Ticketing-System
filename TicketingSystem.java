/*
 * TicketingSystem.java
 * Version: 1.0
 * Author: Theo & Dennis
 * Date: 02-16-2020
 * Description: Launches the method and graphical user interface for students to buy tickets and choose their partners
 */

//imports
//arrays and arraylists
import java.util.ArrayList;
import java.util.Arrays;
//JFrame, Panels, Labels, etc
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
//Graphics
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
//ActionEvents and KeyListeners
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//IO
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

//Main Class
class TicketingSystem extends JFrame {
  
    //Global Variables
    private ArrayList<Student> students;
    private ArrayList<Table> tables;
    //when a student logs in, this variable holds that student
    private Student studentReference; 
  
    //cost of a ticket
    private static final double COST_TICKET = 82.99;
    private double profit = 0;
    //for the graphical window
    private static final int WIDTH = 300; 
    private static final int HEIGHT = 650;
    private JFrame thisFrame;
    //all the panels
    private JPanel mainPanel; 
    private JPanel registerPanel;
    private JPanel signInPanel;
    private JPanel partnerSelectionPanel;
    //all the labels
    private JLabel labelGuest1; 
    private JLabel labelGuest2; 
    private JLabel labelGuest3; 
    private JLabel labelCostOfTicket;
    private JLabel labelTotalSales;
    private JLabel labelUser;
    private JLabel labelN;
    private JLabel labelId;
    private JLabel labelCreateUserName;
    private JLabel labelCreatePassword;  
    private JLabel labelEnterUserName;
    private JLabel labelEnterPassword;
    private JLabel labelPNum;
    private JLabel labelPreferredPartnerName;  
    private JLabel labelPreferredPartnerId;
    private JLabel labelPreferredPartnerNameInstructions;
    private JLabel labelWrongLogin;
    private JLabel labelPreferredPartnerNameExample;
    private JLabel labelAddOnePartner;
    private JLabel labelRemoveOnePartner;
    //all the textfields
    private JTextField nameField; 
    private JTextField idField;
    private JTextField userNameCreateField;
    private JTextField passwordCreateField;
    private JTextField userNameEnterField;
    private JTextField passwordEnterField;
    private JTextField partnerN;
    private JTextField preferredPartnerNameField; 
    private JTextField addOnePartnerField;
    private JTextField removeOnePartnerField;
    //all the jbuttons
    private JButton registerButton; 
    private JButton signInButton;
    private JButton signOutButton;
    private JButton toPartnerSelectionButton;
    private JButton submitRegisterButton; 
    private JButton submitSignInButton;
    private JButton submitPartnerSelectionButton;
    private JButton addOnePartnerButton;
    private JButton removeOnePartnerButton;
    private JButton registerCancelButton;
    private JButton signInCancelButton;
    private JButton partnerSelectionCancelButton;
    private JButton exitButton;
    private JButton buyButton;
    private JButton refundButton;
    private JButton removeButton;
  
    /**
     * Constructor
     * TicketingSystem()
     * Description: creates the ticketing system interface when launched from the Prom interface
     * @param: the arraylist of students read from the text file
     * @return: null
     */
    public TicketingSystem(ArrayList<Student> students) {
        thisFrame = this;
        this.students = students;
        //gets profit based on # of students who have paid
        for (int i = 0; i < students.size(); i++){
            if (students.get(i).hasPaid())  {
                profit = profit+COST_TICKET;
            }
        }
    
        //set window settings
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setUndecorated (true);
        this.setLocation(950, 10);
        // Change border to brownish-gold colour
        this.getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.YELLOW)); 
        this.setFocusable (true);
        this.requestFocus (true);
    
        //create the new JPanels
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        registerPanel = new JPanel();
        registerPanel.setBackground(Color.BLACK);
        signInPanel = new JPanel();
        signInPanel.setBackground(Color.BLACK);
        partnerSelectionPanel = new JPanel();
        partnerSelectionPanel.setBackground(Color.BLACK);

        //create the labels
        labelGuest1 = new JLabel("Welcome Guest!"); 
        labelGuest2 = new JLabel("Sign in with your Prom account or register");
        labelGuest3 = new JLabel("now! You may buy a ticket once logged in.");
        labelCostOfTicket = new JLabel("The cost is: "+COST_TICKET);
        labelTotalSales = new JLabel("The total # of sales so far is: "+profit/COST_TICKET);
        labelUser = new JLabel("Welcome " + "User" + "!");
        labelN = new JLabel("Enter in your Full Name: ");
        labelId = new JLabel("Enter in your Student ID: ");
        labelCreateUserName = new JLabel("Enter a username: ");
        labelCreatePassword = new JLabel("Enter a password: ");
        labelEnterUserName = new JLabel("Enter a username: ");
        labelEnterPassword = new JLabel("Enter a password: ");
        labelPreferredPartnerName = new JLabel("Enter your new list of partners: ");
        labelPreferredPartnerNameInstructions = new JLabel("Seperated by commas, no spaces");
        labelPreferredPartnerNameExample = new JLabel("Example:John Smith,Mary Doe,Steven Smith");
        labelAddOnePartner = new JLabel("Enter the name of ONE new partner:");
        labelRemoveOnePartner = new JLabel("Remove the name of ONE old partner:");
 
        //set colours for labels
        labelGuest1.setForeground(Color.YELLOW);
        labelGuest2.setForeground(Color.YELLOW);
        labelGuest3.setForeground(Color.YELLOW);
        labelCostOfTicket.setForeground(Color.YELLOW);
        labelTotalSales.setForeground(Color.YELLOW);
        labelUser.setForeground(Color.YELLOW);
        labelN.setForeground(Color.YELLOW);
        labelId.setForeground(Color.YELLOW);
        labelCreateUserName.setForeground(Color.YELLOW);
        labelCreatePassword.setForeground(Color.YELLOW);
        labelEnterUserName.setForeground(Color.YELLOW);
        labelEnterPassword.setForeground(Color.YELLOW);
        labelPreferredPartnerName.setForeground(Color.YELLOW);
        labelPreferredPartnerNameInstructions.setForeground(Color.YELLOW);
        labelPreferredPartnerNameExample.setForeground(Color.YELLOW);
        labelAddOnePartner.setForeground(Color.YELLOW);
        labelRemoveOnePartner.setForeground(Color.YELLOW);

        //create the textfields
        nameField = new JTextField(25);
        idField = new JTextField(25);
        userNameCreateField = new JTextField(25);
        passwordCreateField = new JTextField(25);
        userNameEnterField = new JTextField(25);
        passwordEnterField = new JTextField(25);
        preferredPartnerNameField = new JTextField(25);
        addOnePartnerField = new JTextField(25);
        removeOnePartnerField = new JTextField(25);
    
        //Create the JButtons and add action listeners
        
        //registerButton (switches to register panel)
        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                // switches to register panel
                switchPanel(mainPanel, registerPanel); 
            }
        });
        //End of Register Button
    
        //signInButton (switches to sign in panel)
        signInButton = new JButton("Sign In");
        signInButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //switches to sign in panel
                switchPanel(mainPanel, signInPanel);  
            }
        });
        //End of SignInButton
    
        //signOutButton (allows user to sign out)
        signOutButton = new JButton("Sign Out");
        signOutButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //'logs out' by resetting the studentReference variable
                studentReference = null;
                refreshLabelUser();    
                refreshMainPanel();
                //refreshs and changes back to main panel
                switchPanel(mainPanel, mainPanel); 
            }
        });
        //end of signOutButton
    
        //toPartnerSelectionButton (allows student to go to the partner selection panel)
        toPartnerSelectionButton = new JButton("Partner Selection");
        toPartnerSelectionButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //switches from main panel to PartnerSelection Panel
                switchPanel(mainPanel, partnerSelectionPanel); 
            }
        });
        //end of toPartnerSelectionButton
    
        //submitRegisterButton (allows student to register)
        submitRegisterButton = new JButton("Submit");
        submitRegisterButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //gets inputted info
                String inputName = nameField.getText();
                String inputId = idField.getText();               
                String inputUserName = userNameCreateField.getText(); 
                String inputPassword = passwordCreateField.getText();
                //creates student
                Student createdStudent = new Student(inputName, inputId, inputUserName, inputPassword,false); 
                try {
                    addStudent(createdStudent);
                } catch (Exception E){};
                //switches back to main panel and refreshes
                switchPanel(registerPanel, mainPanel);        
                refreshMainPanel();
                //resets textfields
                nameField.setText("");       
                idField.setText("");
                userNameCreateField.setText("");
                passwordCreateField.setText("");
            }
        });
        //End of submitRegisterButton
    
        //submitSignInButton (allows student to sign in)
        submitSignInButton = new JButton("Submit");
        submitSignInButton.addActionListener(new ActionListener() {
              @Override public void actionPerformed(ActionEvent e) {
                   //get info
                   String inputUserName = userNameEnterField.getText(); 
                   String inputPassword = passwordEnterField.getText();
            
                   boolean passwordCheck = false;
                   String foundUsername, foundPassword;
            
                   for (int i = 0; i < students.size(); i++){
                       foundUsername = ( students.get(i) ).getUserName();
                       foundPassword = ( students.get(i) ).getPassword();
                       //checks if username and passwords are equal to the inputted
                       if ( (foundUsername).equals(inputUserName) && (foundPassword).equals(inputPassword) ){
                           //if equal, set the student reference.
                           studentReference = students.get(i);  
                           passwordCheck = true;
                           i = students.size();
                       }
                   }
                   if (passwordCheck == true){
                       //switch back to main panel
                       refreshLabelUser();            
                       refreshMainPanel();
                       switchPanel(signInPanel, mainPanel);
                   } 
                   userNameEnterField.setText(""); //reset textfields
                   passwordEnterField.setText("");
            }
        });
        //end of submitSignInButton
    
        //submitPartnerSelectionButton (allows user to access submit new partner list)
        submitPartnerSelectionButton = new JButton("Submit");
        submitPartnerSelectionButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //gets the partner list seperated by commas.
                String inputNames = preferredPartnerNameField.getText();  
                ArrayList<Student> temp = new ArrayList<Student>();
                boolean check = false;
            
                //first checks if there are commas, if not it'll add one partner only.
                for(int i = 0; i < inputNames.length(); i++){
                    if (inputNames.substring(i,i+1).equals(",")){
                        check = true;
                    }
                }
            
                if (check == true){
                    //splits string into arraylist of each partner's name
                    ArrayList<String> partners = new ArrayList<>(Arrays.asList(inputNames.split(",")));
                    for (int i = 0; i < partners.size(); i++){
                         for (int j = 0; j < students.size(); j++){
                             //checks if the partner is already existing or not (based on name), and adds them to a temporary arraylist
                             if ( ( ( students.get(j) ) .getName() ).equals( partners.get(i) ) ){  
                                 temp.add(students.get(j));                                          
                             }
                         }
                     }
                 } else {
                     //if no commas, add one student if they exist
                     for (int i = 0; i < students.size(); i++){
                         if ( inputNames.equals( (students.get(i)).getName() ) ){
                             temp.add(students.get(i));
                         }
                     }
                 }
                 //sets the new partner list
                 studentReference.setPartners(temp);
                 //reset textfield
                 preferredPartnerNameField.setText(""); 
                 refreshLabelUser();
                 refreshMainPanel();
                 //update and switch mainpanel with signinpanel
                 switchPanel(signInPanel, mainPanel); 
          }
        });
        //end of submitPartnerSelectionButton
    
        //addOnePartnerButton (allows user to add one partner)
        addOnePartnerButton = new JButton("Add One New Partner");
        addOnePartnerButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e){
                String partnerName = addOnePartnerField.getText();
                //check if partner is already there or not
                boolean check = true;
                for (int i = 0; i < (studentReference.getPartners()).size(); i++ ){
                    if ( ( ((studentReference.getPartners()).get(i)).getName()).equals(partnerName) ){
                        //if student is already in their partners, it won't add
                        check = false; 
                        i = (studentReference.getPartners()).size();
                    }
                }
                //finds if student exists in the main arraylist
                if (check == true){
                    for (int i = 0; i < students.size(); i++){
                        if (( students.get(i).getName()).equals(partnerName) && !(studentReference.getName()).equals(partnerName) ){
                            //adds student to their partners arraylist also checks to make sure student doesn't input their own name
                            studentReference.getPartners().add(students.get(i)); 
                            //exit loop
                            i = students.size(); 
                        }
                    }
                }
                //reset textfield
                addOnePartnerField.setText(""); 
                refreshLabelUser();
                refreshMainPanel();
                //switch and update panels
                switchPanel(signInPanel, mainPanel); 
          }
        });
        //end of addOnePartnerButton
    
        //removeOnePartnerButton (allows user to remove one partner)
        removeOnePartnerButton = new JButton("Remove One Old Partner");
        removeOnePartnerButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e){
                String partnerName = removeOnePartnerField.getText();
                for (int i = 0; i < studentReference.getPartners().size(); i++ ){
                    if ( ( studentReference.getPartners().get(i).getName() ).equals( partnerName ) ){
                        //if partner found in the partner arraylist, remove
                        studentReference.getPartners().remove(i);       
                        i = studentReference.getPartners().size();
                    }
                }
                //reset textfield
                removeOnePartnerField.setText("");  
                refreshLabelUser();
                refreshMainPanel();
                //switch and update panels
                switchPanel(signInPanel, mainPanel);  
            }
        });
        //end of removeOnePartnerButton
                                          
        //registerCancelButton (cancels registration, switches back to main)
        registerCancelButton = new JButton("Cancel");
        registerCancelButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //switches back frames
                switchPanel(registerPanel, mainPanel);  
            }
        });
        //end of registerCancelButton
        
        //signInCancelButton (switches out of sign in panel back to main)
        signInCancelButton = new JButton("Cancel");
        signInCancelButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //switches back frames after refreshing
                refreshLabelUser();
                refreshMainPanel();
                switchPanel(signInPanel, mainPanel);  
            }
        });
        //end of signInCanceButton
    
        //partnerSelectionCancelButton (leaves partner selection panel)
        partnerSelectionCancelButton = new JButton("Exit Partner Selection");
        partnerSelectionCancelButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                refreshLabelUser();
                refreshMainPanel();
                //switches back panels
                switchPanel(partnerSelectionPanel, mainPanel); 
            }
        });
        //end of partnerSelectionCancelButton
    
        //exitButton (exits ticketing system)
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //closes ticketing system
                thisFrame.dispose();  
            }
        });
        //end of exitButton
    
        //buyButton (allows a student who hasn't paid to buy)
        buyButton = new JButton("Buy");
        buyButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                //calls buy function
                if (!studentReference.hasPaid()){
                    buy(studentReference); 
                    profit = profit + COST_TICKET;
                    //logs user out, updates sales label
                    studentReference = null;
                    labelTotalSales.setText("The total # of sales so far is: "+profit/COST_TICKET);
                    refreshLabelUser();
                    refreshMainPanel();
                    switchPanel(mainPanel, mainPanel);
                }
            }
        });
        //end of buyButton
    
        //refundButton (allows student to refund a previously bought ticket)
        refundButton = new JButton("Refund");
        refundButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                if (studentReference.hasPaid()){
                    refund(studentReference);
                    profit = profit - COST_TICKET;
                    //logs user back out, updates sales label
                    studentReference = null;
                    labelTotalSales.setText("The total # of sales so far is: "+profit/COST_TICKET);
                    refreshLabelUser();
                    refreshMainPanel();
                    switchPanel(mainPanel, mainPanel);
                }
            }
        });
        //end of refundButton
    
        //removeButton (removes/deletes student account from arraylist)
        removeButton = new JButton("Remove");
        removeButton.addActionListener( new ActionListener() {
            @Override public void actionPerformed (ActionEvent e){
                //calls removeStudent method
                removeStudent(studentReference); 
                studentReference = null;
                refreshLabelUser();
                refreshMainPanel();
                //switches back panels
                switchPanel(mainPanel, mainPanel); 
            }
        });
        //end of removeButton

        //Refresh to keep track of logged in status
        refreshMainPanel();
    
        //Add labels, fields, buttons to registerPanel 
        registerPanel.add(labelN);
        registerPanel.add(nameField);
        registerPanel.add(labelId);
        registerPanel.add(idField);
        registerPanel.add(labelCreateUserName);
        registerPanel.add(userNameCreateField);
        registerPanel.add(labelCreatePassword);
        registerPanel.add(passwordCreateField);
        registerPanel.add(submitRegisterButton);
        registerPanel.add(registerCancelButton);
      
        //Add labels, fields, buttons to signInPanel
        signInPanel.add(labelEnterUserName);
        signInPanel.add(userNameEnterField);
        signInPanel.add(labelEnterPassword);
        signInPanel.add(passwordEnterField);
        signInPanel.add(submitSignInButton);
        signInPanel.add(signInCancelButton);
    
        //Add labels, fields, buttons to partnerSelectionPanel
        partnerSelectionPanel.add(labelPreferredPartnerName);
        partnerSelectionPanel.add(labelPreferredPartnerNameInstructions);
        partnerSelectionPanel.add(labelPreferredPartnerNameExample);                                              
        partnerSelectionPanel.add(preferredPartnerNameField);
        partnerSelectionPanel.add(submitPartnerSelectionButton);
        partnerSelectionPanel.add(labelAddOnePartner);
        partnerSelectionPanel.add(addOnePartnerField);
        partnerSelectionPanel.add(addOnePartnerButton);
        partnerSelectionPanel.add(labelRemoveOnePartner);
        partnerSelectionPanel.add(removeOnePartnerField);
        partnerSelectionPanel.add(removeOnePartnerButton);
        partnerSelectionPanel.add(partnerSelectionCancelButton);
      
        this.add(mainPanel);  
        this.setVisible(true); 
    }
     //End of Constructor
  
    // METHODS
    /**
     * refreshLabelUser
     * Description: refreshes the label when the student logs in or logs out
     * @param: null
     * @return: null
     */
    public void refreshLabelUser() {
        if (studentReference==null) {
            labelUser = new JLabel("Welcome " + "User" + "!");
        } else {
            labelUser = new JLabel("Welcome " + studentReference.getName() + "!");
        }
        labelUser.setForeground(Color.YELLOW);
    }
  
    /**
     * refreshMainPanel
     * Description: This method refreshes the mainpanel to either the logged in panel or the opening panel
     * @param: null
     * @return: null
     */
    public void refreshMainPanel() {
        if (studentReference==null) {
            //shows logged out screen
            mainPanel.removeAll();
            mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10)); // temporary for readability 
            mainPanel.add(labelGuest1);
            mainPanel.add(labelGuest2);
            mainPanel.add(labelGuest3);
            mainPanel.add(signInButton);
            mainPanel.add(registerButton);
            mainPanel.add(exitButton);
            mainPanel.add(labelCostOfTicket);
            mainPanel.add(labelTotalSales);
        } else {
            //shows logged in screen
            mainPanel.removeAll();
            mainPanel.add(labelUser);
            mainPanel.add(signOutButton);
            mainPanel.add(toPartnerSelectionButton);
            mainPanel.add(buyButton);
            mainPanel.add(refundButton);
            mainPanel.add(removeButton);
        }
    }
  
    /**
     * switchPanel
     * Description: This method switches two panels
     * @param: the inital panel and the new panel
     * @return: null
     */
    public void switchPanel(JPanel panel1, JPanel panel2) {
        this.getContentPane().removeAll();
        this.remove(panel1);
        this.add(panel2);
        panel2.updateUI();
    }
  
    /**
     * addStudent
     * Description: This method adds a new student to the arraylist
     * @param: the student to be added
     * @return: null
     */
    private void addStudent(Student student) {
        students.add(student);
    }
  
    /**
     * removeStudent
     * Description: This method removes a student from the arraylist
     * @param: the student that is to be removeed
     * @return: null
     */
    private void removeStudent(Student student) {
        students.remove(student);
    }  
  
    /**
     * buy
     * Description: This method allows the student to buy tickets.
     * @param: the student buying the ticket and the cost
     * @return: null
     */
    private void buy(Student student) {
        student.setPaid(true);
    }
  
    /**
     * refund
     * Description: This method allows the student to refund their ticket.
     * @param: the student refunding the ticket and the cost
     * @return: null
     */
    private void refund(Student student) {
        student.setPaid(false);
    }
  
    /**
    * paintComponent(Graphics g) from UML not necessary to make graphics for our ticketing system
    */
}
//end of Ticketing System