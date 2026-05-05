package lt.viko.eif.astrukcinskas.atnra_programa_keitimas;

import lt.viko.eif.astrukcinskas.atnra_programa_keitimas.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

@Component
public class Changer {

    @Autowired
    private Message message;
    private Socket socket;

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

        this.message = objectMapper.readValue(messageJson, Message.class);
    }

    public Message getMessage(){
        return this.message;
    }

    public Message setMessage(Message message) {

        if(this.message.getEValue().equals(message.getEValue()) &&
        this.message.getInitial_text().equals(message.getInitial_text()) &&
        this.message.getNValue().equals(message.getNValue()) &&
        this.message.getSignature().equals(message.getSignature()))
        {
            return null;
        }

        this.message = message;

        return message;
    }

    public void connect() throws IOException {
        socket = new Socket("localhost", 1234);
        System.out.println("Keitimo aplikacija prisijunge prie tikrinimo aplikacijos");

    }

    public String sendMessage() throws IOException {
        if (socket == null)
        {
            connect();
            System.out.println("Check");
        }

        ObjectMapper objectMapper = new ObjectMapper();
        OutputStream out = socket.getOutputStream();

        try {

            String messageJson = objectMapper.writeValueAsString(this.message);
            out.write((messageJson + "\n").getBytes());
            out.flush();
        } catch (Exception e) {
            System.out.println("Changer: Nepavyko nusiusti zinutes");
        }
        return "Zinute sekmingai nusiusta";
    }
}
