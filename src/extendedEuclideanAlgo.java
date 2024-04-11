import java.util.*;
public class extendedEuclideanAlgo {
    public static int[] calculateGCD(int a, int b){
        int r1,r2,t1,t2,t,r,q;
        r1 = a;
        r2 = b;
        t1 = 0;
        t2 = 1;
        int result[] = new int[3];
        int lastr1,lastr2,lastt1,lastt2;
        System.out.println("q"+"\t"+"r1"+"\t"+"r2"+"\t"+"r"+"\t"+"t1"+"\t"+"t2"+"\t"+"t");
        while(r2!=0){
            q = r1 / r2;
            System.out.print(q+"\t");
            // r = r1 % r2;
            lastr1 = r1;
            lastr2 = r2;
            lastt1 = t1;
            lastt2 = t2;
            r = lastr1 - (q*lastr2);
            System.out.print(lastr1+"\t"+lastr2+"\t"+r+"\t");
            r1 = lastr2;
            r2 = r;
            t = lastt1 - (q*lastt2);
            System.out.print(lastt1+"\t"+lastt2+"\t"+t+"\t");
            t1 = lastt2;
            t2 = t;
            System.out.println();
        }
        result[0] = r1;
        result[1] = t1;
        return result;
    }
    public static void main(String[] args) {
        int n, b;
        // to calculate gcd(n,b) -- to take input of b over Zn
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter n1: ");
        n = sc.nextInt();
        System.out.print("Enter n2: ");
        b = sc.nextInt();
        int result[] = new int[3];
        result = calculateGCD(n,b);
        if(result[0]==1){
            System.out.println("The multiplicative inverse exists");
            if(result[1]<0){
                result[1] = n + result[1];
                System.out.println("The inverse is "+result[1]);
            }
        }
        else
            System.out.println("The multiplicative inverse does not exist");
    }
}
