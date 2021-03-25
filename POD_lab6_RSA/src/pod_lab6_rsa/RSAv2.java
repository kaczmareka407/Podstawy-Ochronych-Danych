/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod_lab6_rsa;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author user
 */
public class RSAv2
{

    BigInteger p;
    BigInteger q;

    public RSAv2()
    {

    }

    public RSAv2(BigInteger p, BigInteger q)
    {
        this.p = p;
        this.q = q;
    }

    BigInteger NWD(BigInteger a, BigInteger b)
    {
        if (!b.equals(BigInteger.ZERO))
        {
            return NWD(b, a.mod(b));
        }

        return a;
    }

    BigInteger GenerowanieE(BigInteger phi)
    {
        BigInteger LiczbaPierwsza = phi;

        while (!NWD(LiczbaPierwsza, phi).equals(BigInteger.ONE))
        {
            LiczbaPierwsza = LiczbaPierwsza.nextProbablePrime();

        }
        System.out.println("Liczba pierwsza: " + LiczbaPierwsza);
        return LiczbaPierwsza;
    }

    void main()
    {
        //Generowanie klucza
        System.out.println("p=" + p);
        System.out.println("q=" + q);

        BigInteger n = p;
        n = n.multiply(q);
        System.out.println("n=" + n);

        BigInteger phi = p.subtract(BigInteger.ONE);
        phi = phi.multiply(q.subtract(BigInteger.ONE));
        System.out.println("phi=" + phi);

        BigInteger e = GenerowanieE(phi);
        System.out.println("e=" + e);

        BigInteger d = new BigInteger("1");

        for (int i = 2;; i++)
        {
            BigInteger D = BigInteger.valueOf(i);
            if (D.multiply(e).mod(phi).equals(BigInteger.ONE))
            {
                d = D;
                break;
            }

        }
        System.out.println("d=" + d);
        System.out.println("Klucz publiczny: [" + e + "," + n + "]");
        System.out.println("Klucz prywatny: [" + e + "," + d + "]");
        
        String m = "Lorem ipsum dolor sit amet, consectetur cras amet."; 
        //String m = "kajak";
        //Szyfrowanie wiadomosci
        //c = m ^ e mod n;
        ArrayList<BigInteger> c = new ArrayList<BigInteger>();
        for (int i=0; i<m.length(); i++)
        {
            c.add(BigInteger.valueOf((int)m.charAt(i)).modPow(e, n));
        }
        System.out.println(c);
        //Odszyfrowanie wiadomosci
        //m = c ^ d mod n;
        ArrayList<BigInteger> m2 = new ArrayList<BigInteger>();
        String m3 = new String();
        for (int i=0; i<m.length(); i++)
        {
           
           m2.add(c.get(i).modPow(d, n));
           m3=m3.concat(Character.toString(m2.get(i).intValue()));
           
        }
        System.out.println(m2);
        System.out.println(m3);
        
    }
}
