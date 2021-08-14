// Java program to illustrate Server Side Programming 
// for Simple Calculator using TCP 
import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.ServerSocket; 
import java.net.Socket; 
import java.util.StringTokenizer; 
  
public class Server 
{ 
    public static void main(String args[]) throws IOException 
    { 
  
        ServerSocket ss = new ServerSocket(4444); 
        Socket s = ss.accept();

        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
        int result=0; 


              /*  InetAddress ip = InetAddress.getLocalHost(); 
                int port = 1234; 
              */  //Scanner sc = new Scanner(System.in); 
          
          try
               {
                
                // wait for input 
                System.out.println("I'm Main Server waiting to get numbers from Client :");
                String r1 = dis.readUTF(); 
      	         String r2 = dis.readUTF();		
                 
    	       
                int oprnd1 = Integer.parseInt(r1); 
  		        int oprnd2 = Integer.parseInt(r2);
                System.out.println("Number Received from Client : "+oprnd1 +" & "+oprnd2);

                int range=oprnd2 - oprnd1;
                range+=1;
                System.out.println("range : "+range);
                int mod = range % 3;
                
                if (mod!=0) {
                    range =range - mod;
                }
                range = range/3;

                //creating client pgm 1...
                Socket s1 = new Socket("localhost", 1234); 
          
                DataInputStream dis1 = new DataInputStream(s1.getInputStream()); 
                DataOutputStream dos1 = new DataOutputStream(s1.getOutputStream()); 
    
                dos1.writeUTF(String.valueOf(oprnd1));
                dos1.writeUTF(String.valueOf(range)); 
                
                System.out.println("----------------- Number sent to Subserver 1 ------------- " + oprnd1+" & "+range);

                String subresult1 = dis1.readUTF(); 
                
                System.out.println("Result Received from Subserver = 1 : "+subresult1); 
                //end client pgm-1

                //creating client pgm 2...
                Socket s2 = new Socket("localhost", 1995); 
          
                DataInputStream dis2 = new DataInputStream(s2.getInputStream()); 
                DataOutputStream dos2 = new DataOutputStream(s2.getOutputStream()); 
                int secondrange=range+range;
                range+=1;

                dos2.writeUTF(String.valueOf(range));
                dos2.writeUTF(String.valueOf(secondrange)); 
                
                System.out.println("----------------- Number sent to Subserver 2 ------------- " + range +" & "+secondrange);

                String subresult2 = dis2.readUTF(); 
                
                System.out.println("Result Received from Subserver = 2: "+subresult2); 
                //end client pgm-2
                

                 //creating client pgm 3...
                Socket s3 = new Socket("localhost", 2000); 
          
                DataInputStream dis3 = new DataInputStream(s3.getInputStream()); 
                DataOutputStream dos3 = new DataOutputStream(s3.getOutputStream()); 
                
                secondrange+=1;

                dos3.writeUTF(String.valueOf(secondrange));
                dos3.writeUTF(String.valueOf(oprnd2)); 
                
                System.out.println("----------------- Number sent to Subserver 3 ------------- " + secondrange +" & "+oprnd2);

                String subresult3 = dis3.readUTF(); 
                
                System.out.println("Result Received from Subserver = 3: "+subresult3); 
                //end client pgm -3 


                int subresult = (Integer.valueOf(subresult1) + Integer.valueOf(subresult2) + Integer.valueOf(subresult3));
                System.out.println("Final Result : "+subresult); 

        //sent back to server
            dos.writeUTF(String.valueOf(subresult)); 
	}catch(NumberFormatException ex){
		System.out.println(""+ex);}
        
    } 
}