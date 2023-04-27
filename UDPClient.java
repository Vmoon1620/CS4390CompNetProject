import java.io.*; 
import java.net.*; 
  
class UDPClient { 
    public static void main(String args[]) throws Exception 
    { 
    	
      System.out.println("Please enter a mathematical expression: ");	
      // Create a BufferedReader to read input from the user via standard input stream
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); 
      
       // Create a DatagramSocket for the client
      DatagramSocket clientSocket = new DatagramSocket(); 
   // Get the IP address of the server
      InetAddress IPAddress = InetAddress.getByName("127.0.0.1"); 
  
      // Create a byte array to store data to send and receive
      byte[] sendData = new byte[1024]; 
      byte[] receiveData = new byte[1024]; 
        



      // Read a sentence from the user
      String sentence = inFromUser.readLine(); 

          // Convert the sentence to a byte array and store it in sendData
      sendData = sentence.getBytes();         

// Create a DatagramPacket to send the data to the server
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8080); 
    	  
      // Send the packet to the server
    	      clientSocket.send(sendPacket); 
    	  
    	      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
    	  
               // Wait for a packet to be received from the server
    	      clientSocket.receive(receivePacket); 
    	  
               // Convert the received data to a string
    	      String modifiedSentence =  new String(receivePacket.getData()); 
    	  
    	      System.out.println("Your answer is: " + modifiedSentence); 
    	      clientSocket.close(); 

              System.exit(0);
    	      } 
    	}
