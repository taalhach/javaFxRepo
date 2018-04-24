import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class DisplayResult extends JFrame {
	//variable initialization
    JLabel PerMinCostText,usageCoseText,dateText,pcNoText, startTimeText, endTimeText;
	
    Container container;
    
    String perMinuteCose="";

    public void init(){
    	//setting frame properties 
	        setVisible(true);

	        setSize(500, 400);
                getContentPane().setBackground(Color.black);

	        setLayout(null);

	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        container=getContentPane();
    }
    //method to find difference between start and end times
    public long timeDifference(String startDate , String endDate) throws ParseException{
   	 
		//date format class
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		Date date1 = dateFormat.parse(startDate);
		Date date2 = dateFormat.parse(endDate);
                //finding difference
		long diff = date2.getTime() - date1.getTime();
                //conversion into seconds from miliseconds
		long totalSeconds=diff/1000;
                //minutes conversion
		long total_mins=totalSeconds/60;
		
		return total_mins;
    }

    //displaying results into frame
    public void getResultInfo(boolean isFileType ,File fileToProcess, String pcNumber , String startDate, String endDate, long total_mins){

	      //Show Computer Name
	        pcNoText = new JLabel("PC Number : "+pcNumber);
	        pcNoText.setBounds(100, 60, 400, 30);
                pcNoText.setForeground(Color.white);
	        add(pcNoText);
	        
	    	Date start = new Date(System.currentTimeMillis());
		String dateWithoutTime = start.toString().replaceAll("(\\d\\d:){2}\\d\\d\\s", "");

	        
		  //Show sessionDate
		     dateText = new JLabel("Usage date : "+dateWithoutTime);
                     dateText.setForeground(Color.white);
                     dateText.setBounds(100, 90, 400, 30);
		     add(dateText);

		     
		   //Show start date
			  startTimeText = new JLabel("Start Date : "+startDate);
			  startTimeText.setBounds(100, 120, 400, 30);
                          startTimeText.setForeground(Color.white);

			  add(startTimeText);
			
			  
		    //Show end date
			   endTimeText = new JLabel("End Date : "+endDate);
			   endTimeText.setBounds(100, 150, 400, 30);
                           endTimeText.setForeground(Color.white);
			   add(endTimeText);
			  
			   
			   if(!isFileType  && perMinuteCose.isEmpty()){
				   perMinuteCose="1";
			   }else if(!perMinuteCose.isEmpty()){

				    writeToFile(pcNumber , startDate , endDate ,perMinuteCose , String.valueOf(total_mins*Long.parseLong(perMinuteCose)));
			   }else{
				    perMinuteCose=processFile(fileToProcess);
				    writeToFile(pcNumber , startDate , endDate ,perMinuteCose , String.valueOf(total_mins*Long.parseLong(perMinuteCose)));
			   }
			   
			   
			   //Show start date
			    PerMinCostText = new JLabel("Cost Per Minute : "+perMinuteCose+"£");
			    PerMinCostText.setBounds(100, 180, 400, 30);
                            PerMinCostText.setForeground(Color.white);
				  add(PerMinCostText);
				
				  
			    //Show end date
				   usageCoseText = new JLabel("Net Usage Cost : "+total_mins*Long.parseLong(perMinuteCose)+"£");
				   usageCoseText.setBounds(100, 210, 400, 30);
                                   usageCoseText.setForeground(Color.white);
				   add(usageCoseText);
				        
			        
			        

    }
    //writng results into file
	private void writeToFile(String name,String start, String end, String perMinuteCose, String totalCost) {
		
		String fileName="outputFile.txt"; // filename
		try (
                        //writes results into outputFile.txt 
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                       String pcName = "PC NAME : "+name+"\n";
			String startTime="START TIME :"+start+"\n";
			String endTime="END TIME :"+end+"\n";
			String cost="COST PER MINUTE :"+perMinuteCose+" £S"+"\n";
			String netCost ="TOTAL COST :"+totalCost+" £"+"\n";
                        //writing properties into files
			writer.write(pcName);
			writer.write(startTime);
			writer.write(endTime);
			writer.write(cost);
			writer.write(netCost);
			

			System.out.println("Record saved");

		} catch (IOException e) {

			e.printStackTrace();

		}

	}


		

	private String processFile(File fileToProcess) { //reads content from file
		BufferedReader br = null;
		FileReader fr = null;
		String sCurrentLine = null;
		try {
                    

			fr = new FileReader(fileToProcess);
			br = new BufferedReader(fr);

                        //reading content from file line by line 
			while ((sCurrentLine = br.readLine()) != null) {
				return sCurrentLine;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
return null;
	}

        //constructor of class
	public DisplayResult(String pcNumber , String startDate , String endDate) throws ParseException{
		
		super("ICMS");
	        init();

			startDate= startDate+":00";
			endDate = endDate+":00";
			
			long total_mins= timeDifference(startDate, endDate);
	      
	        getResultInfo(Boolean.FALSE,null, pcNumber, startDate, endDate, total_mins);
			
	}

	//constructor of this classs
	public DisplayResult(String pcNumber , String startDate , String endDate,String costPerMinue) throws ParseException{
		
		super("Internet Cafe Management System");
	        init();
	        this.perMinuteCose = costPerMinue;

			startDate= startDate+":00";
			endDate = endDate+":00";
			
			long total_mins= timeDifference(startDate, endDate);
	      
	        getResultInfo(Boolean.FALSE,null, pcNumber, startDate, endDate, total_mins);
			
	}
        
		//constructor of this classs
	public DisplayResult(String pcNumber , String startDate , String endDate, File perMinuteCoseFile) throws ParseException{
		
		super("Internet Cafe Management System");
        init();

		startDate= startDate+":00";
		endDate = endDate+":00";
		
		long total_mins= timeDifference(startDate, endDate);
      
        getResultInfo(Boolean.TRUE, perMinuteCoseFile, pcNumber, startDate, endDate, total_mins);
	
        
	}

public static void main(String args[])
{

    new ManagementWindow();

}

}






