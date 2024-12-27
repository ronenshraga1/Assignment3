import java.math.BigInteger;
import java.util.Random;

public class BigIntegerOperations {
    //Task 2.1
    //Assumes n!=null
    //Returns the sum of positive integers smaller than n
    public static BigInteger sumSmaller(BigInteger n){
        BigInteger sum = null;
        // ---------------write your code BELOW this line only! ------------------
        if(n == null)
            throw new IllegalArgumentException("Illegal value");
        sum = new BigInteger("0");
        for(BigInteger i = new BigInteger("0") ;i.compareTo(n) <0; i = i.add(BigInteger.valueOf(1))){
            sum = sum.add(i);
        }

        // ---------------write your code ABOVE this line only! ------------------
        return sum;
    }

    //Task 2.2
    //Assumes n>=0
    //prints n pseudo-random numbers
    public static void printRandoms(int n){
        // ---------------write your code BELOW this line only! ------------------
        Random random = new Random();
        for(int i =0;i<n;i++){
            System.out.println(random.nextInt());
        }
        // ---------------write your code ABOVE this line only! ------------------
    }

    //Task 2.3
    // Assumes n!=null and n>=0
    //Returns true iff n is a prime number
    public static boolean isPrime(BigInteger n){
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------
            if(n == null || n.compareTo(new BigInteger("0"))<0)
                throw new IllegalArgumentException("not valid number");
            BigInteger prime = new BigInteger("2");
            while (n.compareTo(prime.multiply(prime)) >=0 && ans){
                if(n.mod(prime) == BigInteger.valueOf(0))
                    ans = false;
               prime = prime.add(BigInteger.valueOf(1));
            }
            if(n.compareTo(new BigInteger("1"))<=0)
                ans = false;

        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }

    //Task 2.4
    //Assumes n>1
    //Returns a randomly chosen prime number, smaller than 2^n
    public static BigInteger randomPrime(int n){
        BigInteger myRand = null;
        // ---------------write your code BELOW this line only! ------------------
        if(n <= 1)
            throw new IllegalArgumentException("not valid number");
        boolean isprime = false;
        while (!isprime){
            myRand = new BigInteger(n,new Random());
            isprime =isPrime(myRand);
        }

        // ---------------write your code ABOVE this line only! ------------------
        return myRand;
    }

    //Task 2.5
    // No assumptions
    //Returns false if primes==null, or is not sorted, or includes duplicates, or includes a composite number
    public static boolean isValidPrimesArray(BigInteger[] primes) {
        boolean isValid = true; // Assume the array is valid initially
        // ---------------write your code BELOW this line only! ------------------
        if(primes == null)
            isValid = false;
        if(isValid && primes.length != 0) {
            for (int i = 0; isValid && i < primes.length - 1; i++) {
                if (primes[i].compareTo(primes[i + 1]) > -1 || !isPrime(primes[i]) || !isPrime(primes[i + 1])) {
                    isValid = false;
                }
                for (int j = 0; j < i && isValid; j++) {
                    if (primes[i].equals(primes[j])) {
                        isValid = false;
                    }
                }

            }
        }
        // ---------------write your code ABOVE this line only! ------------------
        return isValid;
    }


    //Task 2.6
    //Assumes primes != null, n !=null, and n>1. 'primes' is sorted, and includes only unique prime numbers.
    //Returns true iff n can be expressed as a product of prime numbers from primes
    public static boolean canFactorizeToTarget(BigInteger[] primes, BigInteger n) {
        boolean ans = true;
        // ---------------write your code BELOW this line only! ------------------
        if(n== null || n.compareTo(BigInteger.valueOf(1))<=0 || primes == null ||!isValidPrimesArray(primes))
            throw new IllegalArgumentException("not valid value or array");
        ans = canFactorizeToTarget(primes,n,0);
        // ---------------write your code ABOVE this line only! ------------------
        return ans;
    }
    public static boolean canFactorizeToTarget(BigInteger[] primes, BigInteger n,int i) {
        boolean ans = true;
        if(n.equals(BigInteger.valueOf(1))){
            ans = true;
        } else if (i == primes.length) {
            ans = false;
        } else{
            if(n.mod(primes[i]) == BigInteger.valueOf(0)){
                ans = canFactorizeToTarget(primes,n.divide(primes[i]),i);
            } else{
                i =i + 1;
                ans = canFactorizeToTarget(primes,n,i);
            }
        }
        return ans;

    }

    //Task 2.7
    //Assumes primes != null, n !=null, and n>1. 'primes' is sorted, and includes only unique prime numbers.
    //If n can be expressed as a product of prime numbers from primes, it prints the numbers in the factorization
    public static void printFactorization(BigInteger[] primes, BigInteger n) {
        // ---------------write your code BELOW this line only! ------------------
        if(!canFactorizeToTarget(primes,n)){
            throw new IllegalArgumentException("data is not valid");
        }
        printFactorization(primes,n,0);
        // ---------------write your code ABOVE this line only! ------------------
    }
    public static void printFactorization(BigInteger[] primes, BigInteger n,int i) {
        if(n.equals(BigInteger.valueOf(1)) || primes.length ==0){
            System.out.println("");
        } else if (n.equals(primes[i])) {
            System.out.print(primes[i]);
            printFactorization(primes,n.divide(primes[i]),i);
        } else{
            if(n.mod(primes[i]) == BigInteger.valueOf(0)){
                System.out.print(primes[i] +",");
                printFactorization(primes,n.divide(primes[i]),i);
            } else{
                i =i + 1;
                printFactorization(primes,n,i);
            }
        }

    }

}
