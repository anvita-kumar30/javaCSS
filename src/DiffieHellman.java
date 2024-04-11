import java.util.*;

public class DiffieHellman {
    // Power function to return value of a ^ b mod P
    private static long power(long a, long b, long p)
    {
        if (b == 1)
            return a;
        else
            return (((long)Math.pow(a, b)) % p);
    }
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        long P, G, x, a, y, b, ka, kb;
        // Both the persons will be agreed upon the
        // public keys G and P
        // A prime number P is taken
        System.out.println("Enter the value of P: ");
        P = sc.nextLong();
        // A primitive root for P, G is taken
        System.out.println("Enter the value of G: ");
        G = sc.nextLong();
        // Anvita will choose the private key a
        // a is the chosen private key
        System.out.println("Enter the value of a: ");
        a = sc.nextLong();
        System.out.println("The private key a for Anvita: " + a);
        // Gets the generated key
        x = power(G, a, P);
        // Nia will choose the private key b
        // b is the chosen private key
        System.out.println("\nEnter the value of b: ");
        b = sc.nextLong();
        System.out.println("The private key b for Nia: " + b);
        // Gets the generated key
        y = power(G, b, P);
        // Generating the secret key after the exchange of keys
        ka = power(y, a, P); // Secret key for Anvita
        kb = power(x, b, P); // Secret key for Nia
        System.out.println("\nSecret key for Anvita is: " + ka);
        System.out.println("Secret key for Nia is: " + kb);
        System.out.println("\n" + ka + " is the shared secret.");
    }
}