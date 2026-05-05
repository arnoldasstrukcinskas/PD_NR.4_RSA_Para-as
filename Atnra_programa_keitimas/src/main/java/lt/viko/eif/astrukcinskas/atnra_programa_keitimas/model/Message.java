package lt.viko.eif.astrukcinskas.atnra_programa_keitimas.model;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class Message {

    private BigInteger eValue;
    private BigInteger nValue;
    private String initial_text;
    private BigInteger signature;

    public BigInteger geteValue() {
        return eValue;
    }

    public void seteValue(BigInteger eValue) {
        this.eValue = eValue;
    }

    public BigInteger getnValue() {
        return nValue;
    }

    public void setnValue(BigInteger nValue) {
        this.nValue = nValue;
    }

    public String getInitial_text() {
        return initial_text;
    }

    public void setInitial_text(String initial_text) {
        this.initial_text = initial_text;
    }

    public BigInteger getSignature() {
        return signature;
    }

    public void setSignature(BigInteger signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return "Message{" +
                "eValue=" + eValue +
                ", nValue=" + nValue +
                ", initial_text='" + initial_text + '\'' +
                ", signature=" + signature +
                '}';
    }
}
