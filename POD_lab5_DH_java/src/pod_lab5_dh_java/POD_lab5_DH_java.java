/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod_lab5_dh_java;

import java.math.BigInteger;

public class POD_lab5_DH_java {

 
    public static void main(String[] args) {
        BigInteger p= BigInteger.valueOf(1000);
        BigInteger q= BigInteger.valueOf(1000);
        
        DHv2 dh = new DHv2(p,q);
        dh.main();
    }
    
}
