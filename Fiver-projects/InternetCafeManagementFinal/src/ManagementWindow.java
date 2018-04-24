import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ManagementWindow extends JFrame {

    //component initialzation
    JButton button;
    //labels and textfields initialization 
    JLabel appText,dateText,pcNoText, startTimeText, endTimeText;
    JTextField pcNoVal, startTimeVal,endTimeVal;

    Container container;
    //button events handler
    handler action_handler;

    public ManagementWindow(){
        //frame properties
        super("ICMS");
        setVisible(true);
        setLayout(null);
        container=getContentPane();
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
        pcNoText = new JLabel("PC number:");
        pcNoText.setForeground(Color.white);
        pcNoText.setBounds(100, 90, 400, 25);
        add(pcNoText);
        //pc number textfield
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
        action_handler = new handler();
        //calculate button 
        button=new JButton("Calculate");
        button.setBackground(Color.white);
        button.addActionListener(action_handler);
        button.setBounds(180, 200, 100, 25);
        add(button);	
        
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.black);
        setSize(500, 400);
    }
    //event halndler for button 
    class handler implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
                String pn=pcNoVal.getText();
        	String s_date = startTimeVal.getText();
        	String e_date= endTimeVal.getText();
                //checking inputs length
        	if(pn.length()!=0&&s_date.length()!=0&&e_date.length()!=0)
                {
		
            try {
                //displayResult object calling
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



    public static void main(String args[])
    {

        new ManagementWindow();

    }

}






