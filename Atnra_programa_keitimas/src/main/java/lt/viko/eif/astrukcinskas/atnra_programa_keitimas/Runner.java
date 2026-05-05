package lt.viko.eif.astrukcinskas.atnra_programa_keitimas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {
    private final Changer changer;

    public Runner(Changer changer) {
        this.changer = changer;
    }

    @Override
    public void run(String... args) throws Exception {
        changer.openSocket();
    }
}
