import java.io.DataInputStream; 
import java.io.DataOutputStream; 
import java.io.IOException; 
import java.net.InetAddress; 
import java.net.Socket; 
import java.net.UnknownHostException; 
import java.util.Scanner; 
  
public class Client 
{ 
    public static void main(String[] args) throws IOException 
    { 
        int port = 4444; 
        Scanner sc = new Scanner(System.in); 
  
        Socket s = new Socket("localhost", port); 
  
        DataInputStream dis = new DataInputStream(s.getInputStream()); 
        DataOutputStream dos = new DataOutputStream(s.getOutputStream()); 
        
        while (true) 
        { 
            System.out.print("Enter 1st Range "); 
	       int no1 = sc.nextInt(); 
            System.out.println("Enter 2nd Range"); 
	       int no2 = sc.nextInt();	  

            dos.writeUTF(String.valueOf(no1));
            dos.writeUTF(String.valueOf(no2)); 
            // wait till request is processed and sent back to client 
            String ans = dis.readUTF(); 
            System.out.println("Answer=" +no1+"+"+no2+" = "+ ans); 
        } 
    } 
} 