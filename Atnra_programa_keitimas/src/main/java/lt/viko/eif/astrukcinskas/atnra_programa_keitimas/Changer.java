package lt.viko.eif.astrukcinskas.atnra_programa_keitimas;

import lt.viko.eif.astrukcinskas.atnra_programa_keitimas.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

@Component
public class Changer {

    @Autowired
    private Message message;

    public void openSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1233);
        System.out.println("<====================Changer launched!====================>");

        while(true){
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Pirmoji programa prisijunge!");

                Scanner input = new Scanner(socket.getInputStream());

                while (input.hasNextLine()) {
                    messageManager(input);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void messageManager(Scanner input){
        ObjectMapper objectMapper = new ObjectMapper();

        String messageJson = input.nextLine();

        Map<String, String> receivedMessage = objectMapper.readValue(messageJson, Map.class);

        this.message.seteValue(new BigInteger(receivedMessage.get("eValue")));
        this.message.setnValue(new BigInteger(receivedMessage.get("nValue")));
        this.message.setInitial_text(receivedMessage.get("initial_text"));
        this.message.setSignature(new BigInteger(receivedMessage.get("signature")));
    }

    public Message getMessage(){
        return this.message;
    }
}
