package lt.viko.eif.astrukcinskas.atnra_programa_keitimas.model;

import lt.viko.eif.astrukcinskas.atnra_programa_keitimas.Changer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigInteger;

@RestController
public class ChangerController {

    @Autowired
    private Changer changer;

    @GetMapping("showMessage")
    public ResponseEntity<Message> showMessage(){
        Message response = changer.getMessage();

        if (response == null) {
            return ResponseEntity.badRequest().body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("sendMessage")
    public ResponseEntity<String> sendMessage() throws IOException {
        return ResponseEntity.ok(changer.sendMessage());
    }

    @PutMapping("changeMessage")
    public ResponseEntity<Message> changeMessage(@RequestParam BigInteger eValue,
                                                 @RequestParam String initialText,
                                                 @RequestParam BigInteger nValue,
                                                 @RequestParam BigInteger signature
                                                 ){

        Message message = new Message();
        message.setEValue(eValue);
        message.setInitial_text(initialText);
        message.setNValue(nValue);
        message.setSignature(signature);

        changer.setMessage(message);

        return ResponseEntity.ok(message);
    }

    @PutMapping("changeMessageObject")
    public ResponseEntity<Message> changeMessageObject(@RequestBody Message message){
        Message repsonse = changer.setMessage(message);

        if (repsonse == null)
        {
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.ok(message);
    }

    @GetMapping("Disconnect")
    public ResponseEntity<String> disconnect() throws IOException {
        changer.disconnect();

        return ResponseEntity.ok("Antra programa atsijuge nuo treciosios");
    }
}
