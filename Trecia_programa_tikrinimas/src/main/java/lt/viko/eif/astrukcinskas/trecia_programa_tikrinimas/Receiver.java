package lt.viko.eif.astrukcinskas.trecia_programa_tikrinimas;

import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

public class Receiver {

    public void openSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);

        System.out.println("Waiting for message");
        try {
            Socket socket = serverSocket.accept();
            Scanner input = new Scanner(socket.getInputStream());

            while(input.hasNextLine()){
                messageChecker(input);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void messageChecker(Scanner input){
        ObjectMapper objectMapper = new ObjectMapper();

        String messageJson = input.nextLine();
        Map<String, String> message = objectMapper.readValue(messageJson, Map.class);
        System.out.println(message.get("eValue"));
        System.out.println(message.get("nValue"));
        System.out.println(message.get("initial_text"));
        System.out.println(message.get("signature"));

    }


}
