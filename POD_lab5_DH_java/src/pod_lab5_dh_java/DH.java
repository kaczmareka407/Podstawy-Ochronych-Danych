package pod_lab5_dh_java;

import static java.lang.Integer.max;
import static java.lang.Math.pow;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ThreadLocalRandom;

public class DH
{

    boolean jest_liczba_pierwsza(int liczba)
    {
        if (liczba < 2)
        {
            return false;
        }
        for (int i = 2; i <= liczba / 2; i++)
        {
            if (liczba % i == 0)
            {
                return false;
            }
        }
        return true;
    }

    /*    BigInteger pierwiastek_modulo(BigInteger duza_liczba_pierwsza)
    {
    BigInteger wyliczone = new BigInteger("1");
    
    ArrayList<BigInteger> ciag = new ArrayList<BigInteger>();
    
    for (BigInteger pierwiastek_pierwotny = new BigInteger("2"); !pierwiastek_pierwotny.equals(duza_liczba_pierwsza); pierwiastek_pierwotny=pierwiastek_pierwotny.add(new BigInteger("1")))
    {
    //// System.out.println("PP: "+pierwiastek_pierwotny);
    //for (BigInteger i = new BigInteger("1");!i.equals(duza_liczba_pierwsza) ;i.add(new BigInteger("1")))
    for (int i=1; !duza_liczba_pierwsza.equals(i); i++)
    {
    
    wyliczone=pierwiastek_pierwotny;
    wyliczone=pierwiastek_pierwotny.pow(i);
    
    // wyliczone = (int) pow(pierwiastek_pierwotny, i);
    //System.out.println("pow "+wyliczone);
    //wyliczone = wyliczone % duza_liczba_pierwsza;
    wyliczone=wyliczone.mod(duza_liczba_pierwsza);
    //      System.out.println("% "+wyliczone);
    if (ciag.contains(wyliczone))
    {
    ciag.clear();
    break;
    }
    else
    {
    ciag.add(wyliczone);
    //  System.out.println(i+ " wyliczone: "+wyliczone);
    }
    BigInteger temp = duza_liczba_pierwsza.subtract(new BigInteger("1"));
    if (temp.equals(ciag.size()))
    {
    return pierwiastek_pierwotny;
    }
    
    
    /*if (i <duza_liczba_pierwsza-1)
    {
    ciag.add(wyliczone);
    }
    else if (wyliczone == ciag.get(i % (duza_liczba_pierwsza-1)))
    {	if (i == duza_liczba_pierwsza - 1)
    return pierwiastek_pierwotny;
    else break;
    }
    else { break; }*/
 /*}
        }
        return new BigInteger("-1");
        }*/
    BigInteger pierwiastek_modulo(BigInteger duza_liczba_pierwsza)
    {
        BigInteger wyliczone = new BigInteger("1");

        ArrayList<BigInteger> ciag = new ArrayList<BigInteger>();

        for (BigInteger pierwiastek_pierwotny = new BigInteger("2"); 
                !pierwiastek_pierwotny.equals(duza_liczba_pierwsza); 
                pierwiastek_pierwotny = pierwiastek_pierwotny.add(new BigInteger("1")))
        {
            for (int i = 1; !duza_liczba_pierwsza.equals(i); i++)
            {

                wyliczone = pierwiastek_pierwotny;
                wyliczone = pierwiastek_pierwotny.pow(i);

                wyliczone = wyliczone.mod(duza_liczba_pierwsza);
                //      System.out.println("% "+wyliczone);
                if (ciag.contains(wyliczone))
                {
                    ciag.clear();
                    break;
                } else
                {
                    ciag.add(wyliczone);
                    //  System.out.println(i+ " wyliczone: "+wyliczone);
                }
                BigInteger temp = duza_liczba_pierwsza.subtract(new BigInteger("1"));
                if (temp.equals(ciag.size()))
                {
                    return pierwiastek_pierwotny;
                }

            }
        }
        return new BigInteger("-1");
    }

    int losowanie_liczby(int min, int max) //czyli klucza prywatnego xA, xB
    {

        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        return randomNum;
    }

    int losowanie_liczby_pierwszej(int min, int max)
    {
        Random r = new Random();
        int a = r.nextInt(100);
        int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);

        while (!jest_liczba_pierwsza(randomNum))
        {
            randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
        }

        return randomNum;
    }

    BigInteger obliczanie_z_klucza_prywatnego(BigInteger duza_liczba_pierwsza, BigInteger pierwiastek_pierwotny, int wylosowana_duza_liczba_calkowita)
    {
        BigInteger liczba = new BigInteger("1");
        liczba = pierwiastek_pierwotny;
        liczba = liczba.pow(wylosowana_duza_liczba_calkowita);
        //System.out.println("Potega: "+liczba);
        //System.out.println("Liczba znakow: "+liczba.toString().length());
        liczba = liczba.mod(duza_liczba_pierwsza);
        //System.out.println("Modulo: "+liczba);
        return liczba;

    }

    BigInteger obliczanie_klucza_sesji(BigInteger klucz_prywatny, int wylosowana_duza_liczba_calkowita, BigInteger duza_liczba_pierwsza)
    {
        BigInteger liczba = new BigInteger("1");
        liczba = klucz_prywatny;
        liczba = liczba.pow(wylosowana_duza_liczba_calkowita);
        liczba = liczba.mod(duza_liczba_pierwsza);

        return liczba;
    }

    void program(BigInteger n, BigInteger g)
    {
        int xA = losowanie_liczby(10000, 20000);
        int xB = losowanie_liczby(10000, 20000);
        BigInteger XA = obliczanie_z_klucza_prywatnego(n, g, xA);
        BigInteger XB = obliczanie_z_klucza_prywatnego(n, g, xB);
        BigInteger kluczA = obliczanie_klucza_sesji(XA, xB, n);
        BigInteger kluczB = obliczanie_klucza_sesji(XB, xA, n);

        System.out.println("Liczba pierwsza: " + n);
        System.out.println("Pierwiastek pierwotny: " + g);
        System.out.println("Wylosowana duza liczba calkowita dla A (klucz prywatny): " + xA);
        System.out.println("Wylosowana duza liczba calkowita dla B (klucz prywatny): " + xB);
        System.out.println("XA: " + XA);
        System.out.println("XB: " + XB);
        System.out.println("Klucz sesji A: " + kluczA);
        System.out.println("Klucz sesji B: " + kluczB);
        System.out.println("\n\n\n");

    }

    void program_dla3(BigInteger n, BigInteger g)
    {
        int xA = losowanie_liczby(10000, 20000);
        int xB = losowanie_liczby(10000, 20000);
        int xC = losowanie_liczby(10000, 20000);
        BigInteger XA = obliczanie_z_klucza_prywatnego(n, g, xA);
        BigInteger XB = obliczanie_z_klucza_prywatnego(n, g, xB);
        BigInteger XC = obliczanie_z_klucza_prywatnego(n, g, xB);
        BigInteger kluczA = obliczanie_klucza_sesji(XA, xB * xC, n);
        BigInteger kluczB = obliczanie_klucza_sesji(XB, xA * xC, n);
        BigInteger kluczC = obliczanie_klucza_sesji(XC, xA * xB, n);

        System.out.println("Duza liczba pierwsza: " + n);
        System.out.println("Pierwiastek pierwotny: " + g);
        System.out.println("Wylosowana duza liczba calkowita dla A (klucz prywatny): " + xA);
        System.out.println("Wylosowana duza liczba calkowita dla B (klucz prywatny): " + xB);
        System.out.println("Wylosowana duza liczba calkowita dla C (klucz prywatny): " + xC);
        System.out.println("XA: " + XA);
        System.out.println("XB: " + XB);
        System.out.println("XC: " + XC);
        System.out.println("Klucz sesji A: " + kluczA);
        System.out.println("Klucz sesji B: " + kluczB);
        System.out.println("Klucz sesji C: " + kluczC);
        System.out.println("\n\n\n");

    }

    void main()
    {

        BigInteger n = new BigInteger("1009"); //duza liczba pierwsza 1229
        BigInteger g = new BigInteger("11");
        program(n, g);
        program(new BigInteger("13"), new BigInteger("2"));
        program(new BigInteger("53"), new BigInteger("13"));
        program(new BigInteger("97"), new BigInteger("2"));

        /* program_dla3(n,g);
        program_dla3(new BigInteger("13"),new BigInteger("6"));
        program_dla3(new BigInteger("53"),new BigInteger("26"));
        program_dla3(new BigInteger("97"),new BigInteger("10"));
	/*BigInteger liczbapierwsza = new BigInteger("13");
        BigInteger pierwiastek = pierwiastek_modulo(liczbapierwsza);
        System.out.println("Pierwiastek "+pierwiastek);*/
    }
}
