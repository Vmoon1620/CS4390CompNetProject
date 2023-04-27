import java.io.*; 
import java.net.*; 
import java.util.logging.*;

class UDPServerTEST { 
	
    // Create a logger for the UDPServer class
    private static final Logger LOGGER = Logger.getLogger(UDPServerTEST.class.getName());


	    public static double evaluate(String expression) {
	        // Split the expression into individual tokens (numbers and operators)
	        String[] tokens = expression.split(" ");

	        // Initialize the result to the first number
	        double result = Double.parseDouble(tokens[0]);

	        // Loop through the tokens
	        for (int i = 1; i < tokens.length; i += 2) {
	            // Get the operator and the next number
	            String operator = tokens[i];
	            double number = Double.parseDouble(tokens[i+1]);

	            // Apply the operator to the current result and the next number
	            if (operator.equals("+")) {
	                result += number;
	            } else if (operator.equals("-")) {
	                result -= number;
	            } else if (operator.equals("*")) {
	                result *= number;
	            } else if (operator.equals("/")) {
	                result /= number;
	            }
	        }

	        // Return the final result
	        return result;
	    }

	
	
  public static void main(String args[]) throws Exception
    { 
  
      DatagramSocket serverSocket = new DatagramSocket(8080); 
  
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 

        // Print a message to indicate that the server is running and waiting for a message
        System.out.println("Server online, waiting for client's message...");

      while(true) 
        { 
  
    	  
    	   // Create a DatagramPacket to receive data from the client
          DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length); 
          
          // Wait for a packet to be received from a client
          serverSocket.receive(receivePacket); 

          // Convert the received data to a string
           String sentence = new String(receivePacket.getData()); 
   
           // Get the IP address of the client
           InetAddress clientAddress = receivePacket.getAddress(); 
   
             // Get the port number of the client
           int clientPort = receivePacket.getPort(); 
           
           double result = evaluate(sentence);
           String resultString = Double.toString(result);
          
           // Log the client's IP address and port number
           LOGGER.log(Level.INFO, "Received message from {0}:{1}", 
           new Object[] {clientAddress, clientPort});
           //assume above is client's 

           // Convert the  sentence to a byte array and store it in sendData
           sendData = resultString.getBytes(); 

           

           // Create a DatagramPacket to send data back to the client
           DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort); 
  
  
            
            
           // Send the packet to the client
           serverSocket.send(sendPacket); 
         } 
     } 
 }