// Java program to illustrate Server Side Programming 
// for Simple Calculator using TCP 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer; 
  
public class SubServer 
{ 
    public static void main(String args[]) throws IOException 
    { 
  
        ServerSocket ss = new ServerSocket(1234); 
        Socket s = ss.accept();
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
        int result=0; 
        System.out.println("I'm SubServer ");
       
            // wait for input 
            String r1 = dis.readUTF(); 
  	         String r2 = dis.readUTF();	
             //System.out.println("Number-1"+r1+"& Number-2"+r2);
    	try
    	{
                int oprnd1 = Integer.parseInt(r1); 
  		        int oprnd2 = Integer.parseInt(r2);
                //System.out.println("Received "+oprnd1+" to "+oprnd2); 
                System.out.println("Number1"+r1+"& Number2"+r2);
                for (int i=oprnd1;i<=oprnd2 ;i++ ) {

                    result+=i;
                }

          System.out.println("Sent Result to Main Server : "+result); 
            dos.writeUTF(Integer.toString(result)); 
	}catch(NumberFormatException ex){
		System.out.println(""+ex);}
        } 
    
}