/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod_lab7_podzialshamira;

import java.math.BigInteger;

/**
 *
 * @author user
 */
public class POD_lab7_PodzialShamira
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        /*int n=40000;
        int k=1000000;
        int s=50000;*/
        int nT = 4000;
        int kT = 200000;
        int sT = 123456;
      TrywialnaMetoda tm = new TrywialnaMetoda(nT, kT, sT);
      tm.main();

        BigInteger nS = new BigInteger("6");
        BigInteger tS =new BigInteger("5");
        BigInteger sS = new BigInteger("100000");
        SchematShamira ps = new SchematShamira(nS,tS,sS);
        ps.main();
    }

}
