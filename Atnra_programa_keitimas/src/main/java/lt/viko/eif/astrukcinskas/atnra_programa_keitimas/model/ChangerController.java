package lt.viko.eif.astrukcinskas.atnra_programa_keitimas.model;

import lt.viko.eif.astrukcinskas.atnra_programa_keitimas.Changer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChangerController {

    @Autowired
    private Changer changer;

    @GetMapping("showMessage")
    public ResponseEntity<String> showMessage(){
        Message response = changer.getMessage();

        if (response == null) {
            return ResponseEntity.badRequest().body("Nėra jokios žinutės");
        }

        return ResponseEntity.ok(String.valueOf(response));
    }
}
