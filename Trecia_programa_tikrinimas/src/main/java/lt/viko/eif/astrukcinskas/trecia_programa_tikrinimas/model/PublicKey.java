package lt.viko.eif.astrukcinskas.trecia_programa_tikrinimas.model;

import java.math.BigInteger;

public class PublicKey {
    private BigInteger eValue;
    private BigInteger nValue;

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
}
