//import java.io.*;
//import java.math.*;
//import java.sql.SQLOutput;
//import java.util.*;
//
//public class RSAalgo {
//    public static double gcd(double a, double h)
//    {
//        double temp;
//        while (true) {
//            temp = a % h;
//            if (temp == 0)
//                return h;
//            a = h;
//            h = temp;
//        }
//    }
//    public static void main(String[] args)
//    {
//        Scanner sc = new Scanner(System.in);
//        System.out.println("Enter the value of p: ");
//        double p = Double.parseDouble(sc.nextLine());
//        System.out.println("Enter the value of q: ");
//        double q = Double.parseDouble(sc.nextLine());
//        // Stores the first part of public key:
//        double n = p * q;
//        // Finding the other part of public key.
//        // double e stands for encrypt
//        System.out.println("Enter the value of e: ");
//        double e = Double.parseDouble(sc.nextLine());
//        double phi = (p - 1) * (q - 1);
//        while (e < phi) {
//            /*
//             * e must be co-prime to phi and
//             * smaller than phi.
//             */
//            if (gcd(e, phi) == 1)
//                break;
//            else
//                e++;
//        }
//        int k = 1; // A constant value
//        double d = (1 + (k * phi)) / e;
//        // Message to be encrypted
//        System.out.println("Enter the message to be encrypted: ");
//        double msg = Double.parseDouble(sc.nextLine());
//        System.out.println("\nMessage data = " + msg);
//        // Encryption c = (msg ^ e) % n
//        double c = Math.pow(msg, e);
//        c = c % n;
//        System.out.println("Encrypted data = " + c);
//        // Decryption m = (c ^ d) % n
//        double m = Math.pow(c, d);
//        m = m % n;
//        System.out.println("Original Message Sent(Decrypted data) = " + m);
//    }
//}

import java.util.*;
import java.lang.Math;
class RSAalgo {
    private static long power(long a, long b, long p)
    {
        if (b == 1)
            return a;
        else
            return (((long)Math.pow(a, b)) % p);
    }

    public static int gcd(int e,int phiN){
        int r1,r2,t1,t2,t,r,q;
        r1 = e;
        r2 = phiN;
        t1 = 0;
        t2 = 1;
        int result[] = new int[3];
        int lastr1,lastr2,lastt1,lastt2;
        // System.out.println("q"+"\t"+"r1"+"\t"+"r2"+"\t"+"r"+"\t"+"t1"+"\t"+"t2"+"\t"+"t");
        while(r2!=0){
            q = r1 / r2;
            // System.out.print(q+"\t");
            // r = r1 % r2;
            lastr1 = r1;
            lastr2 = r2;
            lastt1 = t1;
            lastt2 = t2;

            r = lastr1 - (q*lastr2);
            // System.out.print(lastr1+"\t"+lastr2+"\t"+r+"\t");
            r1 = lastr2;
            r2 = r;

            t = lastt1 - (q*lastt2);
            // System.out.print(lastt1+"\t"+lastt2+"\t"+t+"\t");
            t1 = lastt2;
            t2 = t;
            // System.out.println();
        }
        result[0] = r1;
        result[1] = t1;
        return result[0];
    }

    public static void main(String[] args) {
        System.out.print("Enter two prime numbers (p & q): ");
        long p,q,N,phiN,e,d,message,ciphertext,originalMessage;
        Scanner sc = new Scanner(System.in);
        p = sc.nextInt();
        q = sc.nextInt();

        N = p*q;
        System.out.println("N(p*q): "+N);

        phiN = (p-1)*(q-1);
        System.out.println("Phi(N): "+phiN);

        e = 6;
        while(e < phiN){
            int e1 = (int) e;
            int phi = (int)phiN;
            if(gcd(e1,phi) == 1)
                break;
            else
                e++;
        }
        System.out.println("e: "+e);

        // (d * e) mod phiN = 1
        d = 1;
        while(((d*e)%phiN) != 1){
            d++;
        }
        System.out.println("d: "+d);

        System.out.print("Enter message: ");
        message = sc.nextInt();

        ciphertext = power(message,e,N);
        System.out.println("Ciphertext: "+ciphertext);

        originalMessage = power(ciphertext,d,N);
        System.out.println("Original Message: "+originalMessage);
    }
}