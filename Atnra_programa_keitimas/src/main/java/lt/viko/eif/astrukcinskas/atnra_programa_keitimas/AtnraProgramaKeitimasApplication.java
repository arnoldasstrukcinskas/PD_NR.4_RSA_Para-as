package lt.viko.eif.astrukcinskas.atnra_programa_keitimas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class AtnraProgramaKeitimasApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(AtnraProgramaKeitimasApplication.class, args);
    }

}
