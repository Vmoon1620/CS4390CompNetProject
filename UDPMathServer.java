import java.io.*; 
import java.net.*; 

class UDPServer { 
	
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
  
      DatagramSocket serverSocket = new DatagramSocket(8080); //its this, diff port numb
  
      byte[] receiveData = new byte[1024]; 
      byte[] sendData  = new byte[1024]; 
      
      
      while(true) 
        { 
  
    	  System.out.println("Server waiting for connection" + "\n");
    	  
          DatagramPacket receivePacket = 
             new DatagramPacket(receiveData, receiveData.length); 
           serverSocket.receive(receivePacket); 

           String sentence = new String(receivePacket.getData()); 
   
           InetAddress IPAddress = receivePacket.getAddress(); 
   
           int port = receivePacket.getPort(); 
           
           double result = evaluate(sentence);
           String resultString = Double.toString(result);
          
           sendData = resultString.getBytes(); 
           
           
           DatagramPacket sendPacket = 
              new DatagramPacket(sendData, sendData.length, IPAddress, 
                                port); 
   
           serverSocket.send(sendPacket); 
         } 
     } 
 }