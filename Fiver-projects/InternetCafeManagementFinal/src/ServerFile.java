import java.net.*;    
import java.io.*;
public class ServerFile   
{
    //varaible initialization 
    public static int port=5300;   //port to listen on 
    private static ServerSocket server_socket; 
    private static  BufferedReader buffer_reader;
    private static OutputStream outputStream;
    private static PrintWriter content_writer;
    private static FileReader reader;
    private static Socket client_socket;
    
  public static void main(String args[]) throws Exception
  {
    try{
        //connection establishment 
        server_socket= new ServerSocket(port);
        System.out.println("Server listening for connection at 127.0.0.1:"+port); //prints message on console when prrogram runs
        client_socket= server_socket.accept();            //port binding
        reader=new FileReader("costPerMinute.txt"); //character reader from file
        buffer_reader = new BufferedReader(reader ); //reading character from filereader
        outputStream= client_socket.getOutputStream(); // ready to send output to sink
        content_writer= new PrintWriter(outputStream, true); // writing to output stream
        String file_content;
        while((file_content = buffer_reader.readLine()) !=  null) // reading from file
        {
           content_writer.println(file_content); //writing fomated data to file_content string 
        }
         close_ConStream(); //closing stream
         new ServerFile();

       }catch(Exception e){
                       System.out.println("Error occured "+e);
         }
  }
  public static void close_ConStream() throws Exception{
      try{
          //closing client socket 
            client_socket.close(); 
            server_socket.close(); //closing server socket
            content_writer.close();  // 
            buffer_reader.close();     
      }catch(Exception e){
            System.out.println("Error occured "+e);
      }
  }
}