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
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

/**
 *
 * @author J.S
 */
public class MovieTest extends JFrame{

    int maxNum = 5;
    
    int movie1Total = 0;
    int movie2Total = 0;
    
    String movieFile1 = ("src/MovieTest/Movie1.txt");
    String movieFile2 = ("src/MovieTest/Movie2.txt");
    List<String> mtb1Temp = new ArrayList<String>();
    List<String> mtb2Temp = new ArrayList<String>();
    String[] mtb1Array = new String[maxNum];
    String[] mtb2Array = new String[maxNum];
     
    
    JLabel movieLabel1 = new JLabel();
    JLabel movieLabel2 = new JLabel();
    JLabel movieTitle1 = new JLabel();
    JLabel movieTitle2 = new JLabel();
    JPanel panelOne = new JPanel();
    
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> errorList = new JList<>(model);
    
    JPanel p1 = new JPanel(); //Main Menu
    JPanel p2 = new JPanel(); //Movie 1 Times (without picture)
    JPanel p3 = new JPanel(); //Movie 2 Times (without picture)
    JPanel p1Test = new JPanel(); //Movie 1 Slot (with picture)
    JPanel p2Test = new JPanel(); //Movie 2 Slot (with picture)
 
    
    ImageIcon MovieImage1 = new ImageIcon("src/MovieTest/Thor-Ragnarok-Poster.jpg");
    ImageIcon MovieImage2 = new ImageIcon("src/MovieTest/Spider-Man.jpg");
    ImageIcon movieLogo = new ImageIcon("src/MovieTest/MovieLogo.jpg");
        
    
    public MovieTest() throws IOException{
       
        //Frame setup
        this.setTitle("Movie Theatre");
        this.setSize(600, 650);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        
        
        this.add(errorList, BorderLayout.EAST);
        
        JLabel logo = new JLabel(movieLogo);
        
        this.add(logo, BorderLayout.NORTH);
       
        p1.setLayout(new GridLayout (2, 1)); //Overall Menu
        p2.setLayout(new GridLayout (1,6)); //Movie 1 Menu
        p3.setLayout(new GridLayout (1,6)); //Movie 2 Menu
        
        //Set Layouts for Panels used
        p1Test.setLayout(new BorderLayout());
        p2Test.setLayout(new BorderLayout()); //Movie 2 
       
        p1.setBorder(BorderFactory.createEmptyBorder(25,25,25,25));
           
        //Movie 1 Setup
        JLabel movieLabelOne = new JLabel(MovieImage1);
        p1Test.add(movieLabelOne, BorderLayout.WEST);
        
        this.MovieReadingOne(); 
               
        //Set Movie 1 Buttons
        JButton[] mtb1 = new JButton[movie1Total];
        
        for (int i = 0; i < movie1Total ; i++)
        {        
            if (i == 0)
            {
                movieTitle1.setText(mtb1Array[i]);
                p2.add(movieTitle1);                               
            }
            else
            {
                mtb1[i] = new JButton(mtb1Array[i]);
                //mtb1[i].addActionListener(this);
                mtb1[i].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                
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
        
        //Main Panel size
        JScrollPane movieScrollPane = new JScrollPane(p1);
        movieScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        movieScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        movieScrollPane.setPreferredSize(new Dimension (500, 50));
        movieScrollPane.setViewportBorder(new LineBorder(Color.RED));
        
        this.add(movieScrollPane, BorderLayout.CENTER);
                      
        this.setVisible(true);
        
    }
    
    void MovieReadingOne() throws FileNotFoundException, IOException{
        
        BufferedReader brTest = new BufferedReader(new FileReader(movieFile1));
        String line = null;        
        p2.add(movieTitle1);
  
        while ((line = brTest.readLine())!= null /*&& i < maxNum*/)
        {       
            mtb1Temp.add(line);
            model.addElement(line);
            movie1Total = movie1Total + 1;
        }
        brTest.close();       
        mtb1Array = mtb1Temp.toArray(new String[mtb1Temp.size()]);

    }
    
    void MovieReadingTwo() throws FileNotFoundException, IOException{
       
        BufferedReader brTest = new BufferedReader(new FileReader(movieFile2));
        String line = null;        
        p3.add(movieTitle2);
  
        while ((line = brTest.readLine())!= null /*&& i < maxNum*/)
        {       
            mtb2Temp.add(line);
            model.addElement(line);
            movie2Total = movie2Total + 1;
        }
        brTest.close();       
        mtb2Array = mtb2Temp.toArray(new String[mtb2Temp.size()]);
        
    }
    
    void MovieShowing1 (){
        
        
    }
    
    void MovieShowing2 (){
        
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
       JFrame.setDefaultLookAndFeelDecorated(true);
        MovieTest frame = new MovieTest();       
    }  
    
}
