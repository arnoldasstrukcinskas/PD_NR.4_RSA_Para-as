package lt.viko.eif.astrukcinskas.trecia_programa_tikrinimas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class TreciaProgramaTikrinimasApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TreciaProgramaTikrinimasApplication.class, args);
        Receiver receiver = new Receiver();
        receiver.openSocket();
    }

}
