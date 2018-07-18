/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietest;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 *
 * @author J.S
 */
public class MovieTest extends JFrame{
    
    //This is a program that sets up a movie ticket website or app that lets 
    //you buy tickets for movies.
    //This does NOT look at actual databases, so it does not save anything.
    //It reads two movie files and gets their showtimes.
    //This does NOT use a calender to check times for other days or a specific day.
    
    //Ints used in loops and other functions. Helps me keep track of number setting.   
    int ZERO = 0;
    int MOVIE_1_TOTAL = ZERO;
    int MOVIE_2_TOTAL = ZERO;
    int MAX_NUM = 5; //Max number of shows in 1 day (does NOT have to be 5)
    int TEMP_NUM = ZERO;
    int NUMBER_OF_ROWS = ZERO;   
    int Q = ZERO; //Temp Variable 
    double TICKET_PRICE = 8.00;
    double PRICE = 0.00;
    double TAX = 0.07;
    double TOTAL_PRICE = 0.00; 
    
    
    //Strings
    String MOVIE_FILE_1 = ("src/MovieTest/Movie1.txt");
    String MOVIE_FILE_2 = ("src/MovieTest/Movie2.txt");
    List<String> MTB1_TEMP = new ArrayList<String>();
    List<String> MTB2_TEMP = new ArrayList<String>();
    String[] MTB1_ARRAY = new String[MAX_NUM];
    String[] MTB2_ARRAY = new String[MAX_NUM];
    String MOVIE_NAME = (null);
    String MOVIE_TIME = (null);
    
   
    //Error List setting to help me see what each button is doing.
    DefaultListModel<String> MODEL = new DefaultListModel<>();
    DefaultListModel<String> MODEL2 = new DefaultListModel<>();
    JList<String> ERROR_LIST = new JList<>(MODEL);
    JList<String> PURCHASE_LIST = new JList<>(MODEL2);


    
    public MovieTest() throws IOException{
       
      
        //Data Files of Movies
        String purchaseListHeader = ("Your Tickets For:");
        String emptySpace = ("");//Showtimes Header
    
    
        //Theatre Varibles
        String[] seatingTest = new String[9];
        String[] rowLetter = {"A", "B", "C"};
    
    
        //Labels   
        JLabel movieTitle1 = new JLabel();
        JLabel movieTitle2 = new JLabel();  
        JLabel movie1Name = new JLabel();
        JLabel movie1Time = new JLabel();
        JLabel checkoutMessage = new JLabel("THANK YOU! WE'LL SEE YOU AT THE MOVIES!");

    
        //JPanels 
        JPanel p1 = new JPanel(); //Main Menu
        JPanel p2 = new JPanel(); //Movie 1 Times (without picture)
        JPanel p3 = new JPanel(); //Movie 2 Times (without picture)
        JPanel p4 = new JPanel(); //Theatre Seating Screen
        JPanel backt1 = new JPanel(); //Back Button and Screen Picture
        JPanel backt2 = new JPanel(); //Back Button
        JPanel p1Test = new JPanel(); //Movie 1 Slot (with picture)
        JPanel p2Test = new JPanel(); //Movie 2 Slot (with picture)
        JPanel p4Test = new JPanel(); //Theatre Seating Slot
        JPanel p5Test = new JPanel(); //Movie header for p4
        JPanel p5 = new JPanel(); //ErrorArt & Message panel
        JPanel p6 = new JPanel();//Purchase List Page
        JPanel p7 = new JPanel();//submit button in p4
        JPanel p8 = new JPanel();//Border for BackTab2 in p6
        JPanel p9 = new JPanel(); //Checkout Button for p6
        JPanel p10 = new JPanel(); //Thank You Page
        JPanel p11 = new JPanel(); //Home Button for p10
     
    
        //Images
        ImageIcon MovieImage1 = new ImageIcon("src/MovieTest/Thor-Ragnarok-Poster.jpg");
        ImageIcon MovieImage2 = new ImageIcon("src/MovieTest/Spider-Man.jpg");
        ImageIcon movieLogo = new ImageIcon("src/MovieTest/MovieLogo.jpg");
        ImageIcon errorArt = new ImageIcon("src/MovieTest/errorArtTest.jpg"); 
        ImageIcon theatreArt = new ImageIcon("src/MovieTest/theatre.jpg");
    
        
        //Fonts
        Font titleFont = new Font("courier", Font.BOLD, 20);
        Font movieFont = new Font("courier", Font.BOLD, 14);
        Font defaultFont = new Font("courier",Font.PLAIN, 12);

        
        
            
        //Frame setup
        this.setTitle("Movie Theatre");       
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLayout(new BorderLayout());
        
        
        //Main Panel size      
        JScrollPane movieScrollPane = new JScrollPane(p1);      
        movieScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        movieScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        movieScrollPane.setPreferredSize(new Dimension (0, 0));       
        movieScrollPane.setViewportBorder(new LineBorder(Color.RED));
        
        this.add(movieScrollPane, BorderLayout.CENTER);
        
        
        //Designs for the pictures used
        JLabel errorPic = new JLabel(errorArt);
        JLabel logo = new JLabel(movieLogo);
        JLabel theatrePic = new JLabel(theatreArt);      
        this.add(logo, BorderLayout.NORTH);     
        
        //Design for Error List
        p5.setLayout(new BorderLayout());
        p5.add(errorPic, BorderLayout.NORTH);
        p5.add(ERROR_LIST, BorderLayout.CENTER);
        JScrollPane errorScrollPane = new JScrollPane(p5);
        this.add(errorScrollPane, BorderLayout.WEST);
       
        //Design for Back Button
        backt1.setLayout(new BorderLayout());
        backt1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        backt2.setLayout(new BorderLayout());
        backt2.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        
        
        //Design for Main Menu Screen
        //p0.setLayout(new BorderLayout()); //Overall Menu
        
        p1.setLayout(new GridLayout (2, 1)); //Overall Menu
        p2.setLayout(new GridLayout (1,6)); //Movie 1 Menu
        p3.setLayout(new GridLayout (1,6)); //Movie 2 Menu
        p4Test.setLayout(new GridLayout(3,3));       
        p4.setLayout(new BorderLayout());
        p4.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        p4Test.setBorder(BorderFactory.createEmptyBorder(10,90,10,100));     
        p4.add(p4Test, BorderLayout.CENTER); //SOUTH
        p4.setVisible(false);
        p1Test.setLayout(new BorderLayout());
        p2Test.setLayout(new BorderLayout()); //Movie 2 
           
        p1.setBorder(BorderFactory.createEmptyBorder(25,25,25,-13));
        
           
        //Movie 1 Setup
        JLabel movieLabelOne = new JLabel(MovieImage1);
        p1Test.add(movieLabelOne, BorderLayout.WEST);
        movieTitle1.setFont(movieFont);
        p2.add(movieTitle1); 
        
        
        //Calls Function to read Data from Movie 1
        this.MovieReadingOne(); 

        
               
        //Set Movie 1 Buttons
        
        JButton[] mtb1 = new JButton[MOVIE_1_TOTAL];
        
        //Finds Movie Title & Showtimes
        for (int i = 0; i < MOVIE_1_TOTAL ; i++)
        {       
            int Q = i;
            if (i == 0)//First of per document SHOULD b the Movie Title
            {
                //Finds Movie Title
                movieTitle1.setText(MTB1_ARRAY[i]);            
                p2.add(movieTitle1);                               
            }
            else //Everything else SHOULD be the showtimes & makes them buttons
            {
                mtb1[i] = new JButton(MTB1_ARRAY[i]);
                
                //Each Showtime Button clicked takes you to the Theatre
                //Sitting setup so you can choose seats.
                mtb1[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {                                      
                        
                        p1.add(p4, BorderLayout.CENTER);
                        p4.setVisible(true);
                        p1.remove(p1Test);
                        p1.remove(p2Test);
                                     
                        p1.setLayout(new GridLayout (1, 1));
                        
                        MODEL.removeAllElements(); //Error Test Output
                        
                        //Set Movie Name in Theatre Screen
                        MOVIE_NAME = (null);
                        MOVIE_NAME = MTB1_ARRAY[ZERO];
                        movie1Name.setText(null);
                        movie1Name.setText(MTB1_ARRAY[ZERO] + "  -  ");
                        
                        //Sets up Time Label in Theatre Screen
                        MOVIE_TIME = (null);
                        MOVIE_TIME = MTB1_ARRAY[Q];
                        movie1Time.setText(null);
                        movie1Time.setText(MTB1_ARRAY[Q]);
                    }
                }); 
                p2.add(mtb1[i]); 
            }                     
        }
        p2.setAlignmentY(50);
         
        p2.setBorder(BorderFactory.createEmptyBorder(100,25,100,25)); 
        
        p1Test.add(p2, BorderLayout.CENTER);             
        
        
        
         
        
        //Movie 2 Setup
        JLabel movieLabelTwo = new JLabel(MovieImage2);
        
        p2Test.add(movieLabelTwo, BorderLayout.WEST);
        
        this.MovieReadingTwo();
        
        movieTitle2.setFont(movieFont);
        p3.add(movieTitle2); 
        
        
           
        //Set Movie 1 Buttons
        JButton[] mtb2 = new JButton[MOVIE_2_TOTAL];
        
        for (int i = 0; i < MOVIE_2_TOTAL ; i++)
        {        
            int Q = i;
            if (i == 0)
            {
                movieTitle2.setText(MTB2_ARRAY[i]);
                p3.add(movieTitle2);                               
            }
            else
            {
                mtb2[i] = new JButton(MTB2_ARRAY[i]);
                mtb2[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                
                        p1.add(p4, BorderLayout.CENTER);
                        p4.setVisible(true);
                        p1.remove(p1Test);
                        p1.remove(p2Test);
                        
                        p1.setLayout(new GridLayout (1, 1));
                       
                        MODEL.removeAllElements();//Error Testing Output
                        
                        //Set Movie Title in theatre Screen                       
                        MOVIE_NAME = (null);
                        MOVIE_NAME = MTB2_ARRAY[ZERO];
                        movie1Name.setText(null);
                        movie1Name.setText(MTB2_ARRAY[ZERO] + "  -  ");
                        
                        
                        //Sets up Time Label in theatre Screen
                        MOVIE_TIME = (null);
                        MOVIE_TIME = MTB2_ARRAY[Q];
                        movie1Time.setText(null);          
                        movie1Time.setText(MTB2_ARRAY[Q]);
      
                    }
                }); 
                p3.add(mtb2[i]); 
            }                     
        }
        p3.setBorder(BorderFactory.createEmptyBorder(100,25,100,25));
        p2Test.add(p3, BorderLayout.CENTER);
        
        
          
        //Add Movie Panels to main panel
        p1.add(p1Test);
        p1.add(p2Test);      
 
        //Theatre Setup (uses p4 & p4Test)
        JToggleButton[] rowSeatArray = new JToggleButton[9];
        //A Loop to create the number of buttons per the number of seats
        //At this point, the max number is 3
        int w = 0;
        for (NUMBER_OF_ROWS = 0 ; NUMBER_OF_ROWS < 3 ; NUMBER_OF_ROWS++)
        {           
            for( TEMP_NUM = 0 ; TEMP_NUM <3 ; TEMP_NUM++)
            {

                rowSeatArray[w] = new JToggleButton(rowLetter[NUMBER_OF_ROWS]+ (TEMP_NUM + 1)); 
                seatingTest[w] = rowLetter[NUMBER_OF_ROWS] + (TEMP_NUM + 1);
            
                rowSeatArray[w].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        MODEL.removeAllElements();                       
                        MODEL2.removeAllElements();                        
                        MODEL2.addElement(purchaseListHeader);
                       for(int i = 0; i < 5; i++)
                        {
                            MODEL2.addElement(emptySpace);
                        }                      
                        MODEL2.addElement(MOVIE_NAME);;
                        MODEL2.addElement(emptySpace); 
                        MODEL2.addElement(MOVIE_TIME);
                        for(int i = 0; i < 8; i++)
                        {
                            MODEL2.addElement(emptySpace);
                        } 
                        MODEL2.addElement("Seats");
                        PURCHASE_LIST.setFont(titleFont);
                        for(int i = 0; i < 3; i++)
                        {
                            MODEL2.addElement(emptySpace);
                        }  
                        PURCHASE_LIST.setFont(defaultFont);
                        PRICE = ZERO;
                      
                        for (int q = 0 ; q < 9 ; q++)
                        {                      
                            if (rowSeatArray[q].isSelected())
                                {
                                    MODEL.addElement(seatingTest[q]);
                                    MODEL2.addElement(seatingTest[q]);
                                    PRICE = PRICE + TICKET_PRICE;
                                }
                            else //if(rowSeatArray[q].isSelected())
                                {
                                    MODEL.removeElement(seatingTest[q]);
                                    MODEL2.removeElement(seatingTest[q]);                                  
                                }                              
                        };
                    }
                });                            
                p4Test.add(rowSeatArray[w]);
                w = w + 1;
            }  
        }
                

        //Back Tabs takes you back to the main menu
        JButton backTab = new JButton("<"); 
        backTab.setPreferredSize(new Dimension(70, 40));
        backTab.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                      
                p4.setVisible(false);
                //p1Test.setVisible(true);
                p1.add(p1Test);
                p1.add(p2Test);
                p1.remove(p4);
                
                p1.setLayout(new GridLayout (2, 1));
                      
                MODEL.removeAllElements();
                PRICE = ZERO;
                        
                //Reloads Movie Info in Error Message 
                try (BufferedReader brTest = new BufferedReader(new FileReader(MOVIE_FILE_1))) {
                    String line = null;                       
            
                    while ((line = brTest.readLine())!= null)
                    {
                        MODEL.addElement(line);
                    }
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                try (BufferedReader brTest = new BufferedReader(new FileReader(MOVIE_FILE_2))) {
                    String line = null;                       
            
                    while ((line = brTest.readLine())!= null)
                    {
                        MODEL.addElement(line);
                    }
                    }catch (FileNotFoundException ex) {
                        Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                //Resets Toggle Buttons to default (seats)
                for (int q = 0 ; q < 9 ; q++)
                {                      
                    if (rowSeatArray[q].isSelected())
                    {
                        rowSeatArray[q].setSelected(false);
                                                           
                    };
                }
                        
            }
                              
        });
         
        
        
        //Setting Title and Showtime Front on Theatre Screen
        p5Test.setLayout(new BorderLayout());
        movie1Name.setFont(titleFont);
        movie1Time.setFont(titleFont);
        p5Test.add(movie1Name, BorderLayout.WEST);
        p5Test.add(movie1Time, BorderLayout.CENTER);
     
        //Setting Back Tab and Title Movie & Showtime on Theatre Screen
        backt2.add(backTab, BorderLayout.WEST);
        backt2.add(p5Test, BorderLayout.CENTER);
        backt1.add(backt2, BorderLayout.CENTER);
        backt1.add(theatrePic, BorderLayout.SOUTH);
        p4.add(backt1, BorderLayout.NORTH);
        
        //Submit Button for Theatre Screen
        JButton submitButton = new JButton("Submit");
        submitButton.setPreferredSize(new Dimension(70, 70));
        submitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {               
                p1.add(p6, BorderLayout.CENTER);               
                p6.setVisible(true); 
                p4.setVisible(false);
                p1.remove(p4);
                                        
                BigDecimal bD = new BigDecimal(TICKET_PRICE);
                bD = bD.setScale(2 , RoundingMode.HALF_UP);
                for(int i = 0; i < 7; i++)
                {
                    MODEL2.addElement(emptySpace);
                } 
                MODEL2.addElement("Price per Seat:   $" + bD);
                double priceTemp = PRICE * TAX;
                TOTAL_PRICE = PRICE + priceTemp;
                BigDecimal bD2 = new BigDecimal(TOTAL_PRICE);
                bD2 = bD2.setScale(2 , RoundingMode.HALF_UP);
                for(int i = 0; i < 10; i++)
                {
                    MODEL2.addElement(emptySpace);
                } 
                MODEL2.addElement("Your final price is:   $" + bD2);
                //MODEL2.addElement("$" + bD2);  
                for(int i = 0; i < 15; i++)
                {
                    MODEL2.addElement(emptySpace);
                }               
                MODEL2.addElement("Are you ready to checkout?");
                  
            }

        });   
   
        //Submit Layout for Theatre Screen
        p7.setLayout(new BorderLayout()); //Submit in p4
        p7.setBorder(BorderFactory.createEmptyBorder(10,300,10,310));//Submit in p4
        p7.setPreferredSize(new Dimension(10, 50));//Submit in p4
        p7.add(submitButton, BorderLayout.CENTER);
        p4.add(p7, BorderLayout.SOUTH);
        
        
        
        //Purchase Screen Layout
        JScrollPane purchaseListScroll = new JScrollPane(PURCHASE_LIST);
        p6.setLayout(new BorderLayout());
        p6.setBorder(BorderFactory.createEmptyBorder(0,0,0,-30));
        p6.add(purchaseListScroll, BorderLayout.CENTER);
        purchaseListScroll.setPreferredSize(new Dimension(0,300));     
        
              
        //Back Tab for Purchase Screen       
        JButton backTab2 = new JButton("<"); 
        backTab2.setPreferredSize(new Dimension(70, 40));
        backTab2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                                     
                p1.add(p4);
                p1.remove(p6);
                p4.setVisible(true);
                p6.setVisible(false);
                        
                MODEL.removeAllElements();
                PRICE = ZERO;
                                      
                
                //Resets Toggle Buttons to default (seats)
                for (int q = 0 ; q < 9 ; q++)
                {                      
                    if (rowSeatArray[q].isSelected())
                    {
                        {
                            rowSeatArray[q].setSelected(false);
                        }                                   
                    };
                }
                        
            }
                              
        });
        
        //Adds Back Tab to Purchase Screen
        p8.setLayout(new FlowLayout(FlowLayout.LEFT));
        //p8.setBorder(BorderFactory.createEmptyBorder(0,0,10,1020));
        p8.add(backTab2, BorderLayout.CENTER);
        p6.add(p8, BorderLayout.NORTH);
        
        //Sets up Thank You Screen
        p10.setLayout(new BorderLayout());
        checkoutMessage.setFont(titleFont);
        p10.add(checkoutMessage, BorderLayout.CENTER);
        
        JButton checkoutButton = new JButton("CHECKOUT"); 
        checkoutButton.setPreferredSize(new Dimension(150, 40));
        checkoutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                                     
                p1.add(p10, BorderLayout.CENTER);               
                p10.setVisible(true); 
                p6.setVisible(false);
                p1.remove(p6);
                        
            }
                              
        });
        
        //Adds Checkout Button to Purchase Screen
        p9.setLayout(new BorderLayout());
        p9.setBorder(BorderFactory.createEmptyBorder(10,500,0,500));
        p9.add(checkoutButton, BorderLayout.CENTER);
        p6.add(p9, BorderLayout.SOUTH);
        
        
        JButton homeButton = new JButton("HOME"); 
        homeButton.setPreferredSize(new Dimension(150, 40));
        homeButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                                                    
                p1.add(p1Test);
                p1.add(p2Test);
                p10.setVisible(false);
                p1.remove(p10);
                
                p1.setLayout(new GridLayout (2, 1));
                        
                MODEL.removeAllElements();
                PRICE = ZERO;
                        
                //Reloads Movie Info in Error Message 
                try (BufferedReader brTest = new BufferedReader(new FileReader(MOVIE_FILE_1))) {
                    String line = null;                       
            
                    while ((line = brTest.readLine())!= null)
                    {
                        MODEL.addElement(line);
                    }
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                try (BufferedReader brTest = new BufferedReader(new FileReader(MOVIE_FILE_2))) {
                    String line = null;                       
            
                    while ((line = brTest.readLine())!= null)
                    {
                        MODEL.addElement(line);
                    }
                    }catch (FileNotFoundException ex) {
                        Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                //Resets Toggle Buttons to default (seats)
                for (int q = 0 ; q < 9 ; q++)
                {                      
                    if (rowSeatArray[q].isSelected())
                    {
                        rowSeatArray[q].setSelected(false);
                                                           
                    };
                }
                        
            }                            
        });
        
        p11.setLayout(new BorderLayout());
        p11.setBorder(BorderFactory.createEmptyBorder(10,500,0,500));
        p11.add(homeButton, BorderLayout.CENTER);
        p10.add(p11, BorderLayout.SOUTH);
        
        
        
        
        this.setVisible(true);       
    }
    
    
    //Function to read Movie File 1 & put it into an Array so the data can be
    //made into Showtime Buttons 
    void MovieReadingOne() throws FileNotFoundException, IOException{
        
        try (BufferedReader brTest = new BufferedReader(new FileReader(MOVIE_FILE_1))) {
            String line = null;
                     
            while ((line = brTest.readLine())!= null /*&& i < maxNum*/)
            {
                MTB1_TEMP.add(line);
                MODEL.addElement(line);
                MOVIE_1_TOTAL = MOVIE_1_TOTAL + 1;
            }
        }
        MTB1_ARRAY = MTB1_TEMP.toArray(new String[MTB1_TEMP.size()]);
    }
    
    
    
    
    //Function to read Movie File 2 & put it into an Array so the data can be
    //made into Showtime Buttons 
    void MovieReadingTwo() throws FileNotFoundException, IOException{     
        
        try (BufferedReader brTest = new BufferedReader(new FileReader(MOVIE_FILE_2))) {
            String line = null;
                       
            while ((line = brTest.readLine())!= null)
            {
                MTB2_TEMP.add(line);
                MODEL.addElement(line);
                MOVIE_2_TOTAL = MOVIE_2_TOTAL + 1;
            }
        }
        MTB2_ARRAY = MTB2_TEMP.toArray(new String[MTB2_TEMP.size()]);        
    }
    
    
    
    /*
    void MovieShowing1 (){
          
    }
    
    void MovieShowing2 (){
              
    }*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       JFrame.setDefaultLookAndFeelDecorated(true);
        MovieTest frame = new MovieTest();       
    }  
    
}
