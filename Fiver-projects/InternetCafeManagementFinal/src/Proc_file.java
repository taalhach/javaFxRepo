import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
 
public class Proc_file extends JFrame {
 //initialization 

	FilePicker picker;
	JFileChooser chooser;
	
    JButton cal_btn,browse_btn;
    
    JLabel appText,dateText,pcNoText, startTimeText, endTimeText,chose_file;
    JTextField pcNoVal, startTimeVal,endTimeVal,path;

    File sendFile = null;
    
    Container c;
    BrowseHandler browseHandler;
    handler handler;
	
    public Proc_file() {
    	//constructor
        super("ICMS");

        //setting frame properties
        setVisible(true);

        setSize(1000, 1000);

        setLayout(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        c=getContentPane();
        //top label
        appText = new JLabel("Internet Cafe Management System");
        appText.setFont(new Font("Serif", Font.BOLD, 20));
        appText.setBounds(100, 30, 400, 25);
        appText.setForeground(Color.white);
        add(appText);
        //getting current date of system 
	Date start_date = new Date(System.currentTimeMillis());
        String date=start_date.toString();
	date = date.replaceAll("(\\d\\d:){2}\\d\\d\\s", "");
        //date label
        dateText = new JLabel("Date :"+date);
        dateText.setBounds(160, 60, 400, 25);
        dateText.setForeground(Color.white);
        add(dateText);
        //pc numnber label
        pcNoText = new JLabel("PC number:");
        pcNoText.setForeground(Color.white);
        pcNoText.setBounds(100, 90, 400, 25);
        add(pcNoText);	
        //enter pc number 
        pcNoVal = new JTextField();
        pcNoVal.setBounds(300, 90, 100, 25);
        add(pcNoVal);	
        startTimeText = new JLabel("Start Time(Format 12:30) :");
        startTimeText.setForeground(Color.white);
        startTimeText.setBounds(100, 120, 400, 25);
        add(startTimeText);	
        //enter start time
        startTimeVal = new JTextField();
        startTimeVal.setBounds(300, 120, 100, 25);
        add(startTimeVal);	
        endTimeText = new JLabel("End Time(Format 12:30) :");
        endTimeText.setForeground(Color.white);
        endTimeText.setBounds(100, 150, 400, 25);
        add(endTimeText);
        //enter end time
        endTimeVal = new JTextField();
        endTimeVal.setBounds(300, 150, 100, 25);
        add(endTimeVal);        
        //Enter end time label
         chose_file = new JLabel("Choose File :");
         chose_file.setBounds(100, 180, 400, 30);
         chose_file.setForeground(Color.white);
          add(chose_file);	
        //Choose file  
         path = new JTextField();
         path.setEnabled(false);
         path.setBounds(300, 180, 100, 25);
         add(path);

         browse_btn = new JButton("Browse File");
         browse_btn.addActionListener(browseHandler);  //browse button event listener
         browse_btn.setBounds(410, 180, 100, 25);
         browse_btn.setBackground(Color.white);
         add(browse_btn);	
         
         chooser = new JFileChooser();
         
        browseHandler = new BrowseHandler();
        handler = new handler();

        //Button to submit
        cal_btn=new JButton("Calculate");
        //adding actionlistener to the cal_btn
        cal_btn.addActionListener(handler);
        browse_btn.addActionListener(browseHandler);
        cal_btn.setBackground(Color.white);
        cal_btn.setBounds(250, 240, 100, 25);
        add(cal_btn);	
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(530, 450);
        getContentPane().setBackground(Color.black);
        setLocationRelativeTo(null);  
    }
    class handler implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            //getting pc number,start date and end date 
        	 String pn=pcNoVal.getText();
        	String s_date = startTimeVal.getText();
        	String e_date= endTimeVal.getText();
                //checking inputs lengths 
        	if(pn.length()!=0&&s_date.length()!=0&&e_date.length()!=0)
                {
	       try {
                   //displayResult call
                new DisplayResult(pn , s_date , e_date);
                }
                catch (ParseException e) {
                e.printStackTrace();
                }
              }
                else
                    JOptionPane.showMessageDialog(null, "Enter values first");
            dispose();
        }
    }

 class BrowseHandler implements ActionListener
    {
     //Browse button hanlder 
        public void actionPerformed(ActionEvent ae)
        {	
               int x = chooser.showOpenDialog(null);
                if (x == JFileChooser.APPROVE_OPTION) {
                    //getting selected files
                	chooser.getSelectedFile();
                	//
                	  sendFile = chooser.getSelectedFile();
                     path.setText(sendFile.getAbsolutePath());

                }
        }
    }

    //main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Proc_file().setVisible(true);
            }
        });
    }
    
    

 
 
}