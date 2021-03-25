/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod_lab7_podzialshamira;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author user
 */
public class TrywialnaMetoda
{

    int n; //liczba wsyzstkich udzialow i potrzbna do odtworzenia sekretu t==n
    int k;
    int s;

    // BigInteger t = new BigInteger("1"); //liczba udzialow potrzebna do odtworzenia sekretu
    //BigInteger k = new BigInteger("1"); //wartosc liczbowa okreslajaca rozmiar przestrzeni liczbowej
    //BigInteger s = new BigInteger("1"); //sekret reprezentowany za pomoca liczby calkowitej z zakresu <0;k-1>
    public TrywialnaMetoda()
    {
    }

    //public TrywialnaMetoda(int n, BigInteger k, BigInteger s)
    public TrywialnaMetoda(int n, int k, int s)
    {
        this.n = n;
        this.k = k;
        this.s = s;
    }

    public ArrayList<Integer> PodzialSeretu()
    {
        ArrayList<Integer> ps = new ArrayList<Integer>();
        for (int i = 0; i < n - 1; i++)
        {
            int randomNum = ThreadLocalRandom.current().nextInt(0, k - 1);

            ps.add(randomNum);
        }
        /*System.out.println("Wylosowanych "+(n-1)+" liczb");
        for (int i = 0; i < n - 1; i++)
        {
            System.out.println(" "+ps.get(i));
        }*/
        int sn=s;
        //wyznaczenie n-tego udzialu
        for (int i=0; i<n-1; i++)
        {
            sn-=ps.get(i);
            System.out.print("  s["+i+"]="+sn);
        }
        while (sn<0)
        {
            sn+=k;
        }
            sn=sn%k;
         System.out.println("s(n)="+sn);
         ps.add(sn);
        return ps;
    }
    public int OdtwarzanieSektretu(ArrayList<Integer> ps)
    {   int suma=0;
        for (int i=0; i<ps.size(); i++)
        {
      
           suma+=ps.get(i); 
           
        }
        while (suma<0)
        {
            suma+=k;
        }
        return suma%k;
    }
    public void main()
    {
        System.out.println("n= "+n+" k="+k+" s="+s);
        ArrayList<Integer> ps = new ArrayList<>(n);
        ps = PodzialSeretu();
        int sekret=0;
        sekret=OdtwarzanieSektretu(ps);
        System.out.println("Sekret: "+sekret);
        
    }
}
