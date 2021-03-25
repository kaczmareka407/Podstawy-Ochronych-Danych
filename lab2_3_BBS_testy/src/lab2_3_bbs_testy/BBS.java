
package lab2_3_bbs_testy;

import java.math.BigInteger;
import java.util.Random;
import java.util.Vector;

public class BBS {
   


   public BigInteger LiczbaPierwsza1 = new BigInteger("100000000003");
   public BigInteger LiczbaPierwsza2 = new BigInteger("100000000019"); 
   
   //public BigInteger LiczbaPierwsza1 = new BigInteger("67");
  // public BigInteger LiczbaPierwsza2 = new BigInteger("43"); 
    

    
    public BigInteger Iloczyn_Liczb_Pierwszych = new BigInteger("1"); 


    public BigInteger LiczbaPierwszaX = new BigInteger("2102662787");
//public BigInteger LiczbaPierwszaX = new BigInteger("127");

    
    public BigInteger PierwotnaWartoscGeneratora = new BigInteger("1");
        public BigInteger PierwotnaWartoscGeneratora1 = new BigInteger("1");


    int rozmiar_ciagu=20000;
    
    public int[] ciag = new int[rozmiar_ciagu];
    Random generator = new Random();

    int najmniej_znaczacy_bit;
    
            int []dlugosc_serii=new int [6];
    
    public void wyznaczanieBBS()
    {
               
        Iloczyn_Liczb_Pierwszych=Iloczyn_Liczb_Pierwszych.multiply(LiczbaPierwsza1);
        Iloczyn_Liczb_Pierwszych=Iloczyn_Liczb_Pierwszych.multiply(LiczbaPierwsza2);
        for (int i=0; i<rozmiar_ciagu; i++)
        {

        PierwotnaWartoscGeneratora = LiczbaPierwszaX.pow(2);
        PierwotnaWartoscGeneratora = PierwotnaWartoscGeneratora.mod(Iloczyn_Liczb_Pierwszych);


        najmniej_znaczacy_bit=PierwotnaWartoscGeneratora.intValue();
        najmniej_znaczacy_bit=najmniej_znaczacy_bit&1;

        ciag[i]=najmniej_znaczacy_bit;
        LiczbaPierwszaX=PierwotnaWartoscGeneratora;
        }
        for (int i=0; i<rozmiar_ciagu; i++)
        {
            if(ciag[i]==1) {System.out.print(" .");}
            else {System.out.print(" "+ciag[i]);}
            if (i%80==0)
            {System.out.println("");}
            
        }
       System.out.println("");

    }
    public void test_pojedynczych_bitow()
    {
        int liczba_jedynek=0;
    for (int i=0; i<rozmiar_ciagu; i++)
    {
    if (ciag[i]==1)
    {
        liczba_jedynek++;
    }
    }
        System.out.println("Liczba jedynek: "+liczba_jedynek);
    if (liczba_jedynek>9725 && liczba_jedynek<10275)
    {
        System.out.println("Test pojedynczych bitow pozytywny, poniewaz liczba jedynek nalezy do przedzialu (9725;10275)");
    }
    else
    {
         System.out.println("Test pojedynczych bitow negatywny, poniewaz liczba jedynek nie nalezy do przedzialu (9725;10275)");
    }
    }
    
        public boolean czy_test_serii_pozytywny(int []dlugosc_serii)
    {
            System.out.println("1 <2315;2685>: " +  dlugosc_serii[0]);
            System.out.println("2 <1114;1386>: " +  dlugosc_serii[1]);
            System.out.println("3   <527;723>: " +  dlugosc_serii[2]);
            System.out.println("4   <240;384>: " +  dlugosc_serii[3]);
            System.out.println("5   <103;209>: " +  dlugosc_serii[4]);
            System.out.println(">=6 <103;209>: " +  dlugosc_serii[5]);
        
        if ((dlugosc_serii[0]>=2315 && dlugosc_serii[0]<=2685)&&
            (dlugosc_serii[1]>=1114 && dlugosc_serii[1]<=1386)&&
            (dlugosc_serii[2]>=527 && dlugosc_serii[2]<=723)&&
            (dlugosc_serii[3]>=240 && dlugosc_serii[3]<=384)&&
            (dlugosc_serii[4]>=103 && dlugosc_serii[4]<=209)&&
            (dlugosc_serii[5]>=103 && dlugosc_serii[5]<=209))
            return true;
        else return false;
    }
        
    public void test_serii()
    {
        int liczba_zer=0;

            for (int i=0; i<ciag.length; i++)
        {
            if(i==0 && ciag[i]==0)
            {
                liczba_zer++;
            }
            
             else if (ciag[i]==0 && ciag[i-1]==0)
            {
                liczba_zer++;
            }
             else if (ciag[i]==0 && ciag[i-1]==1)
            {
                liczba_zer++;
            }
            else if (i==0 && ciag[i]==1) {}
          
            else if (ciag[i-1]==0 && ciag[i]==1 )
            {   
                switch(liczba_zer)
                {
                    case 1:
                        dlugosc_serii[0]++;
                        break;
                    case 2:
                        dlugosc_serii[1]++;
                        break;
                    case 3:
                        dlugosc_serii[2]++;
                        break;
                    case 4:
                        dlugosc_serii[3]++;
                        break;
                    case 5:
                        dlugosc_serii[4]++;
                        break;
                    default:
                        dlugosc_serii[5]++;
                        break;
                }
                liczba_zer=0;
            }
            else{}
        }
            
            
            if (czy_test_serii_pozytywny(dlugosc_serii)==true)
                System.out.println("Wynik serii jest pozytywny");
            else
                System.out.println("Wynik serii jest negatywny");
       
    }

    public void test_dlugiej_serii()
    {
        int liczba_zer=0;
        int liczba_jedynek=0;
        int najdluzszy_ciag_jedynek=0;
        int najdluzszy_ciag_zer=0;
        
        for (int i=0; i<rozmiar_ciagu; i++)
        {
            if (liczba_zer==26 || liczba_jedynek==26)
            {
                System.out.println("Liczba ciagu zer lub jedynek przekorczyla 26. Test dlugiej serii negatywny");
                break;
            }
            else if(i==0 && ciag[i]==0)
            {
                liczba_zer++;
            }
            else if(i==0&& ciag[i]==1)
            {
                liczba_jedynek++;
            }
            else if (ciag[i]==0 && ciag[i-1]==0)
            {
                liczba_zer++;
            }
            else if (ciag[i]==1 && ciag[i-1]==1)
            {
                liczba_jedynek++;
            }
             else if ((ciag[i]==0 && ciag[i-1]==1)||(ciag[i]==1 && ciag[i-1]==0))
            {
                if (liczba_zer>najdluzszy_ciag_zer) {najdluzszy_ciag_zer=liczba_zer;}
                if (liczba_jedynek>najdluzszy_ciag_jedynek) {najdluzszy_ciag_jedynek=liczba_jedynek;}
                liczba_zer=0;
                liczba_jedynek=0;
                
            }
        
        }
        if (liczba_zer<26&&liczba_jedynek<26)
        {
            System.out.println("Test dlugiej serii pozytywny");
            System.out.println("Najdluzszy ciag zer: "+najdluzszy_ciag_zer);
            System.out.println("Najdluzszy ciag jedynek: "+najdluzszy_ciag_jedynek);

        }
    
    }
    public void test_pokerowy()
    {
        int[] wystapienia_liczby_4bitowej = new int[16];
        
        for (int i=0; i<rozmiar_ciagu; i=i+4 )
        {
           if (ciag[i]==0 && ciag[i+1]==0 && ciag[i+2]==0 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[0]+=1;
           }
           else if (ciag[i]==0 && ciag[i+1]==0 && ciag[i+2]==0 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[1]+=1;
           }
           else if (ciag[i]==0 && ciag[i+1]==0 && ciag[i+2]==1 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[2]+=1;
           }
           else if (ciag[i]==0 && ciag[i+1]==0 && ciag[i+2]==1 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[3]+=1;
           }
           else if (ciag[i]==0 && ciag[i+1]==1 && ciag[i+2]==0 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[4]+=1;
           }
           else if (ciag[i]==0 && ciag[i+1]==1 && ciag[i+2]==0 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[5]+=1;
           }
           else if (ciag[i]==0 && ciag[i+1]==1 && ciag[i+2]==1 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[6]+=1;
           }
           else if (ciag[i]==0 && ciag[i+1]==1 && ciag[i+2]==1 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[7]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==0 && ciag[i+2]==0 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[8]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==0 && ciag[i+2]==0 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[9]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==0 && ciag[i+2]==1 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[10]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==0 && ciag[i+2]==1 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[11]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==1 && ciag[i+2]==0 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[12]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==1 && ciag[i+2]==0 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[13]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==1 && ciag[i+2]==1 && ciag[i+3]==0)
           {
               wystapienia_liczby_4bitowej[14]+=1;
           }
           else if (ciag[i]==1 && ciag[i+1]==1 && ciag[i+2]==1 && ciag[i+3]==1)
           {
               wystapienia_liczby_4bitowej[15]+=1;
           }
        }
        
        int wystapienia=0;
        int suma_wystapien=0;
        float test=0;
        for (int i=0; i<wystapienia_liczby_4bitowej.length; i++)
        {
            wystapienia=wystapienia_liczby_4bitowej[i];
            wystapienia=wystapienia*wystapienia;
            suma_wystapien=suma_wystapien+wystapienia;   
        }
        test = (float)(suma_wystapien)*16/5000-5000;
        System.out.println("Wynik: "+ test);
        if ((test > 2.16) && (test <  46.17))
        {
            System.out.println("Test pokera pozytywny, wynik nalezy do zbioru (2.16;46,17)");
        }
        else
        {
            System.out.println("Test pokera negatywny, wynik nie nalezy do zbioru (2.16;46,17)");
        }
    }
    public void zadanie()
    {
       System.out.println("Uzyte liczby: ");
    System.out.println("p="+LiczbaPierwsza1);
    System.out.println("q="+LiczbaPierwsza2);
    
    System.out.println("x="+LiczbaPierwszaX);
    wyznaczanieBBS();
    System.out.println("N="+Iloczyn_Liczb_Pierwszych);   
 
    System.out.println("Test pojedynczych bitow:");
    test_pojedynczych_bitow();
        System.out.println("");
    System.out.println("Test serii:");
    test_serii();
            System.out.println("");

    System.out.println("Test dlugiej serii:");
    test_dlugiej_serii();
            System.out.println("");

    System.out.println("Test pokerowy:");
    test_pokerowy();

    }
    
}
