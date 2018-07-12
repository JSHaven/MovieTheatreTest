/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietest;

import java.io.*;
import java.io.BufferedReader.*;
import java.io.FileReader.*;
import java.io.IOException.*;
import java.io.File.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

/**
 *
 * @author J.S
 */
public class MovieTest extends JFrame{
 
    
    //ALL TEMP Varibles until I can get the project running. This just helps
    //me keep track of all my important varibles and items used.
    
    //Ints used in loops and other functions. Helps me keep track of number setting.
    int maxNum = 5;
    int movie1Total = 0;
    int movie2Total = 0;
    int tempNum = 0;
    int numberOfRows = 0;
    int w = 0;      
    
    //Data Files of Movies
    String movieFile1 = ("src/MovieTest/Movie1.txt");
    String movieFile2 = ("src/MovieTest/Movie2.txt");
    List<String> mtb1Temp = new ArrayList<String>();
    List<String> mtb2Temp = new ArrayList<String>();
    String[] mtb1Array = new String[maxNum];
    String[] mtb2Array = new String[maxNum];
     
    //Theatre Varibles
    String[] seatingTest = new String[9];
    String[] rowLetter = {"A", "B", "C"};
    JToggleButton[] tgbtn = new JToggleButton[3];
    
    //Labels
    JLabel movieLabel1 = new JLabel();
    JLabel movieLabel2 = new JLabel();
    JLabel movieTitle1 = new JLabel();
    JLabel movieTitle2 = new JLabel();
   
    //Error List setting to help me see what each button is doing.
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> errorList = new JList<>(model);
    
    //JPanel panelOne = new JPanel();
    JPanel p1 = new JPanel(); //Main Menu
    JPanel p2 = new JPanel(); //Movie 1 Times (without picture)
    JPanel p3 = new JPanel(); //Movie 2 Times (without picture)
    JPanel p4 = new JPanel(); //Theatre Seating Screen
    JPanel backt1 = new JPanel(); //Back Button and Screen Picture
    JPanel backt2 = new JPanel(); //Back Button
    JPanel p1Test = new JPanel(); //Movie 1 Slot (with picture)
    JPanel p2Test = new JPanel(); //Movie 2 Slot (with picture)
    JPanel p4Test = new JPanel(); //Theatre Seating Slot
    JPanel p5 = new JPanel(); //ErrorArt & Message panel
 
    //Images
    ImageIcon MovieImage1 = new ImageIcon("src/MovieTest/Thor-Ragnarok-Poster.jpg");
    ImageIcon MovieImage2 = new ImageIcon("src/MovieTest/Spider-Man.jpg");
    ImageIcon movieLogo = new ImageIcon("src/MovieTest/MovieLogo.jpg");
    ImageIcon errorArt = new ImageIcon("src/MovieTest/errorArtTest.jpg"); 
    ImageIcon theatreArt = new ImageIcon("src/MovieTest/theatre.jpg");
    
    public MovieTest() throws IOException{
       
        //Frame setup
        this.setTitle("Movie Theatre");
        //this.setSize(600, 650);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setLayout(new BorderLayout());
        
        
        //Main Panel size
        JScrollPane movieScrollPane = new JScrollPane(p1);
        movieScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        movieScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        movieScrollPane.setPreferredSize(new Dimension (500, 50));
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
        p5.add(errorList, BorderLayout.CENTER);
        JScrollPane errorScrollPane = new JScrollPane(p5);
        this.add(errorScrollPane, BorderLayout.WEST);
       
        //Design for Back Button
        backt1.setLayout(new BorderLayout());
        backt1.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        backt2.setLayout(new BorderLayout());
        backt2.setBorder(BorderFactory.createEmptyBorder(0,100,0,0));
        
        
        //Design for Main Menu Screen
        p1.setLayout(new GridLayout (2, 1)); //Overall Menu
        p2.setLayout(new GridLayout (1,6)); //Movie 1 Menu
        p3.setLayout(new GridLayout (1,6)); //Movie 2 Menu
        p4Test.setLayout(new GridLayout(3,3));       
        p4.setLayout(new BorderLayout());
        p4.setBorder(BorderFactory.createEmptyBorder(10,25,100,25));
        p4Test.setBorder(BorderFactory.createEmptyBorder(10,100,100,100));     
        p4.add(p4Test, BorderLayout.SOUTH);
        p4.setVisible(false);
        p1Test.setLayout(new BorderLayout());
        p2Test.setLayout(new BorderLayout()); //Movie 2 
       
        p1.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
        
           
        //Movie 1 Setup
        JLabel movieLabelOne = new JLabel(MovieImage1);
        p1Test.add(movieLabelOne, BorderLayout.WEST);
        
        //Calls Function
        this.MovieReadingOne(); 
            
           
        
        //Set Movie 1 Buttons
        JButton[] mtb1 = new JButton[movie1Total];
        //Finds Movie Title & Showtimes
        for (int i = 0; i < movie1Total ; i++)
        {        
            if (i == 0)//First of per document SHOULD b the Movie Title
            {
                //Finds Movie Title
                movieTitle1.setText(mtb1Array[i]);
                p2.add(movieTitle1);                               
            }
            else //Everything else SHOULD be the showtimes & makes them buttons
            {
                mtb1[i] = new JButton(mtb1Array[i]);
                
                //Each Showtime Button clicked takes you to the Theatre
                //Sitting setup so you can choose seats.
                mtb1[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {                                      
                        p1.removeAll();
                        p1.add(p4, BorderLayout.CENTER);
                        p4.setVisible(true);    
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
        
        //Set Movie 1 Buttons
        JButton[] mtb2 = new JButton[movie2Total];
        
        for (int i = 0; i < movie2Total ; i++)
        {        
            if (i == 0)
            {
                movieTitle2.setText(mtb2Array[i]);
                p3.add(movieTitle2);                               
            }
            else
            {
                mtb2[i] = new JButton(mtb2Array[i]);
                //mtb1[i].addActionListener(this);
                mtb2[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                
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
        for (numberOfRows = 0 ; numberOfRows < 3 ; numberOfRows++)
        {
            for( tempNum = 0 ; tempNum <3 ; tempNum++)
            {
                 //p4Test.add(new JToggleButton("A" + i));
                rowSeatArray[w] = new JToggleButton(rowLetter[numberOfRows]+ (tempNum + 1)); 
                seatingTest[w] = rowLetter[numberOfRows] + (tempNum + 1);
            
                rowSeatArray[w].addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae) {
                        model.removeAllElements();
                        for (int q = 0 ; q < 9 ; q++)
                        {                      
                            if (rowSeatArray[q].isSelected())
                                {
                                    model.addElement(seatingTest[q]);
                                }
                                else
                                {
                                    model.removeElement(seatingTest[q]);
                                }   
                        };
                    }
                });
                p4Test.add(rowSeatArray[w]);
                w = w + 1;
            }  
        }
        
        
        //Back Tabs takes you back to the main menu
        JButton backTab = new JButton("<-"); 
        backTab.setPreferredSize(new Dimension(70, 40));
        backTab.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                      
                p4.setVisible(false);
                //p1Test.setVisible(true);
                p1.removeAll();
                p1.add(p1Test);
                p1.add(p2Test);
                        
                model.removeAllElements();
                        
                //Reloads Movie Info in Error Message 
                try (BufferedReader brTest = new BufferedReader(new FileReader(movieFile1))) {
                    String line = null;                       
            
                    while ((line = brTest.readLine())!= null)
                    {
                        model.addElement(line);
                    }
                }catch (FileNotFoundException ex) {
                    Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MovieTest.class.getName()).log(Level.SEVERE, null, ex);
                }
                        
                try (BufferedReader brTest = new BufferedReader(new FileReader(movieFile2))) {
                    String line = null;                       
            
                    while ((line = brTest.readLine())!= null)
                    {
                        model.addElement(line);
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
                        {
                            rowSeatArray[q].setSelected(false);
                        }                                   
                    };
                }
                        
            }
                              
        });
        
     
        backt2.add(backTab, BorderLayout.WEST);
        backt1.add(backt2, BorderLayout.NORTH);
        backt1.add(theatrePic, BorderLayout.CENTER);
        p4.add(backt1, BorderLayout.NORTH);
      
   
        this.setVisible(true);       
    }
    
    
    //Function to read Movie File 1 & put it into an Array so the data can be
    //made into Showtime Buttons 
    void MovieReadingOne() throws FileNotFoundException, IOException{
        
        try (BufferedReader brTest = new BufferedReader(new FileReader(movieFile1))) {
            String line = null;
            p2.add(movieTitle1);           
            while ((line = brTest.readLine())!= null /*&& i < maxNum*/)
            {
                mtb1Temp.add(line);
                model.addElement(line);
                movie1Total = movie1Total + 1;
            }
        }
        mtb1Array = mtb1Temp.toArray(new String[mtb1Temp.size()]);
    }
    
    
    //Function to read Movie File 2 & put it into an Array so the data can be
    //made into Showtime Buttons 
    void MovieReadingTwo() throws FileNotFoundException, IOException{     
        
        try (BufferedReader brTest = new BufferedReader(new FileReader(movieFile2))) {
            String line = null;
            p3.add(movieTitle2);            
            while ((line = brTest.readLine())!= null)
            {
                mtb2Temp.add(line);
                model.addElement(line);
                movie2Total = movie2Total + 1;
            }
        }
        mtb2Array = mtb2Temp.toArray(new String[mtb2Temp.size()]);        
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
