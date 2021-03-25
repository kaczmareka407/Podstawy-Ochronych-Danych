package pod_lab6_rsa;

import java.io.FileNotFoundException;
import java.math.BigInteger;

public class POD_lab6_RSA {

    public static void main(String[] args) throws FileNotFoundException {
        BigInteger p= BigInteger.valueOf(1019);
        BigInteger q= BigInteger.valueOf(1009);
        RSAv2 rsa = new RSAv2(p,q);
        rsa.main();

    }
    
}
