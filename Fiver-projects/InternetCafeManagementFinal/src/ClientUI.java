import java.awt.Color;
import java.net.*;
import java.text.ParseException;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.JOptionPane;

public class ClientUI extends JFrame  
{
    JButton button;
    
    JLabel appText,dateText,pcNoText, startTimeText, endTimeText;
    JTextField pcNoVal, startTimeVal,endTimeVal;

    Container container;
    handler handler;

    public static String costPerMinute="";

    
    public ClientUI(){
        
        super("ICMS");
        setVisible(true);
        setSize(1000, 1000);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        container=getContentPane();
        // main label on top
        appText = new JLabel("Internet Cafe Management System");
        appText.setFont(new Font("Serif", Font.BOLD, 20));
        appText.setBounds(100, 30, 400, 25);
        appText.setForeground(Color.white);
        add(appText);
        // date and time from system to display on window
	Date start_date = new Date(System.currentTimeMillis());
        String date=start_date.toString();
	date = date.replaceAll("(\\d\\d:){2}\\d\\d\\s", "");
        //date label
        dateText = new JLabel("Date :"+date);
        dateText.setBounds(160, 60, 400, 25);
        dateText.setForeground(Color.white);
        add(dateText);
        //pc number label
        pcNoText = new JLabel("PC number:");
        pcNoText.setForeground(Color.white);
        pcNoText.setBounds(100, 90, 400, 25);
        add(pcNoText);	
        // Pc number input
        pcNoVal = new JTextField();
        pcNoVal.setBounds(300, 90, 100, 25);
        add(pcNoVal);	
        startTimeText = new JLabel("Start Time(Format 12:30) :");
        startTimeText.setForeground(Color.white);
        startTimeText.setBounds(100, 120, 400, 25);
        add(startTimeText);
        //start time input
        startTimeVal = new JTextField();
        startTimeVal.setBounds(300, 120, 100, 25);
        add(startTimeVal);	
        endTimeText = new JLabel("End Time(Format 12:30) :");
        endTimeText.setForeground(Color.white);
        endTimeText.setBounds(100, 150, 400, 25);
        add(endTimeText);
        //end time input
        endTimeVal = new JTextField();
        endTimeVal.setBounds(300, 150, 100, 25);
        add(endTimeVal);
        //handler for button events 
        handler=new handler();
        //Calculate button 
        button=new JButton("Calculate");
        button.setBackground(Color.white);
        button.addActionListener(handler);
        button.setBounds(180, 200, 100, 25);
        add(button);	
        //panel setting
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setSize(500, 400);
    }
    class handler implements ActionListener
    {
        //handler for action performed
        public void actionPerformed(ActionEvent ae)
        {
          //getting entered values from inputs
        	String pc=pcNoVal.getText();
        	String s_date = startTimeVal.getText();
        	String e_date= endTimeVal.getText();
                // checking if any input field is not empty
        	if(pc.length()!=0&&s_date.length()!=0&&e_date.length()!=0)
                {
		
            try {
                //calling displayresult to display calculated amount
                new DisplayResult(pc , s_date , e_date);
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

	
  public static void main( String args[ ] ) throws Exception
  {
      //connecting to server 
      ServerFile serverObj= new ServerFile();
      //server port
     int port =serverObj.port;
     //connecting to server throught sockets
     Socket sock = new Socket( "127.0.0.1", port);
     //opening inputstream to read response from server
     InputStream response = sock.getInputStream();
     BufferedReader responseReader = new BufferedReader(new InputStreamReader(response));
    //Reading response text from a character-input stream
    String str;
     while((str = responseReader.readLine())  !=  null) // reading line-by-line 
     { //writing into string variable
       costPerMinute=str;         
     } 
     //closing bufferReader
     responseReader.close();      
     new ClientUI();

  }
}