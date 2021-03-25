/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod_lab7_podzialshamira;

import java.math.BigInteger;
import java.util.Random;
import java.math.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author user
 */
class SchematShamira
{

    BigInteger n, t, s;

    public SchematShamira()
    {
    }

    public SchematShamira(BigInteger nS, BigInteger tS, BigInteger sS)
    {
        this.n = nS;
        this.t = tS;
        this.s = sS;

    }

    BigInteger liczba_pierwsza()
    {
        Random rnd = new Random();
        BigInteger liczba_pierwsza = new BigInteger("0");
        if (n.compareTo(s) == 1)
        {
            liczba_pierwsza = new BigInteger(n.toString().length(), rnd);

            //liczba_pierwsza = new BigInteger("1520");/////////////////
            liczba_pierwsza = n.nextProbablePrime();
            //System.out.println("liczba_pierwsza " + liczba_pierwsza);

        } else
        {

            liczba_pierwsza = new BigInteger(s.toString().length(), rnd);
            //liczba_pierwsza = new BigInteger("1520");//////////////
            liczba_pierwsza = s.nextProbablePrime();
            //System.out.println("liczba_pierwsza " + liczba_pierwsza);
        }
        return liczba_pierwsza;
    }

    public ArrayList<BigInteger> PodzialSekretu(BigInteger liczba_pierwsza)
    {
        // BigInteger PS = new BigInteger("1");
        Random rnd = new Random();
        ArrayList<BigInteger> ps = new ArrayList<BigInteger>();
        //for (BigInteger i = new BigInteger("0"); i.compareTo(t.subtract(dwa))==0; i.add(jeden))
        ps.add(new BigInteger("0"));
        for (int i = 1; i < t.intValue(); i++)
        {
            //int randomNum = ThreadLocalRandom.current().nextInt(0, k - 1);
            BigInteger randomNum = new BigInteger(5, rnd); //(2^n-1,rnd)

            ps.add(randomNum);
        }
        ArrayList<BigInteger> zbior_si = new ArrayList<BigInteger>();
        zbior_si.add(0, s);
        for (int i = 1; i < t.intValue(); i++)
        {
            //System.out.println("i: " + i);
            BigInteger si = s;
            BigInteger suma = new BigInteger("0");
            for (int j = 1; j < t.intValue() - 1; j++)
            {
                // System.out.println("j: " + j);
                BigInteger x = BigInteger.valueOf(i);

                suma = suma.add(ps.get(j).multiply(x.pow(j)));

            }

            while (suma.compareTo(BigInteger.ZERO) == -1)
            {
                suma = suma.add(liczba_pierwsza);

            }

            suma = suma.mod(liczba_pierwsza);
            suma = suma.add(s);
            zbior_si.add(i, suma);

        }

        for (int i = 0; i < zbior_si.size(); i++)
        {
            System.out.print(" [" + i + "] " + zbior_si.get(i));
        }
        return zbior_si;

    }

    BigInteger OdtwarzanieSekretu(ArrayList<BigInteger> ps, BigInteger liczba_pierwsza)
    {
        BigInteger wyraz_wolny = new BigInteger("0");
        BigInteger ww = new BigInteger("0");
        BigInteger nawias = new BigInteger("1");
        for (int i = 0; i < t.intValue(); i++)
        {
            ww = ps.get(i);
            for (int j = 0; j < t.intValue(); j++)
            {

                if (j != i)
                {

                    //BigInteger licznik = BigInteger.valueOf(i);
                    BigInteger licznik = BigInteger.valueOf(100);
                    //BigInteger licznik = ps.get(i);
                    licznik = licznik.subtract(/*ps.get*/BigInteger.valueOf(j));

                    BigInteger mianownik = ps.get(i)/*BigInteger.valueOf(i)*/;
                    mianownik = mianownik.subtract(ps.get(i))/*BigInteger.valueOf(j))*/;
                    
                   mianownik = mianownik.multiply(new BigInteger("-1"));
                   
                    BigInteger iloraz = new BigInteger("1");

                    iloraz = licznik;
                    iloraz = iloraz.multiply(mianownik);
                    while (iloraz.compareTo(BigInteger.ZERO) == -1)
                    {
                        iloraz = iloraz.add(liczba_pierwsza);

                    }
                    iloraz = iloraz.mod(liczba_pierwsza);
                    nawias = nawias.multiply(iloraz);

                }
                ww = ww.multiply(nawias);
                wyraz_wolny = wyraz_wolny.add(ww);
            }
        }

        return wyraz_wolny;
    }

    public void main()
    {
        System.out.println("n=" + n + " t=" + t + " s=" + s);
        BigInteger liczba_pierwsza = liczba_pierwsza();
        ArrayList<BigInteger> ps = PodzialSekretu(liczba_pierwsza);

        BigInteger os = OdtwarzanieSekretu(ps, liczba_pierwsza);
        System.out.println("\n Wyraz wolny: " + os);

    }
}
