package lt.viko.eif.astrukcinskas.trecia_programa_tikrinimas;

import lt.viko.eif.astrukcinskas.trecia_programa_tikrinimas.model.PublicKey;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.io.IOException;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Scanner;

public class Receiver {

    public void openSocket() throws IOException {
        ServerSocket serverSocket = new ServerSocket(1234);

        System.out.println("<====================Reciever launched!====================>");

        while(true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Antroji programa prisijunge!");

                Scanner input = new Scanner(socket.getInputStream());


                while (input.hasNextLine()) {
                    messageChecker(input);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public void messageChecker(Scanner input) throws NoSuchAlgorithmException {
        ObjectMapper objectMapper = new ObjectMapper();

        String messageJson = input.nextLine();

        JsonNode message = objectMapper.readTree(messageJson);

        PublicKey publicKey = new PublicKey();
        publicKey.seteValue(new BigInteger(message.get("eValue").asString()));
        publicKey.setnValue(new BigInteger(message.get("nValue").asString()));

        messageVerifier(publicKey, message.get("initial_text").asString(), message.get("signature").asString());
    }

    public void messageVerifier(PublicKey publicKey, String message, String signature) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");

        byte[] hashBytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));

        BigInteger messageHash = new BigInteger(1, hashBytes);

        BigInteger signatureValue = new BigInteger(signature);
        BigInteger x = signatureValue.modPow(publicKey.geteValue(), publicKey.getnValue());

        if (messageHash.equals(x))
        {
            System.out.printf("Žinutė verifikuota: %s%n", message);
        } else {
            System.out.println("Žinutė neverifikuota");
        }
    }


}

