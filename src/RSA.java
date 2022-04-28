import java.math.BigInteger;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
public class RSA {

    //made these public because there would have been too many getters....and it sucks

    public String inputText, encryptedText, decryptedText;
    public int p, q, n, z, e, d;

    public int[] publicKey, privateKey;

    public RSA(){
        privateKey = new int[2];
        publicKey = new int[2];
    }

    public void generateKeys(){
        do {
            p = ThreadLocalRandom.current().nextInt(50, 100);
            q = ThreadLocalRandom.current().nextInt(50, 100);
        } while (!prime(p) || !prime(q) || p == q); //check if numbers are prime and if are different

        this.calculus();
        this.setPublicKey(e, n);
        this.setPrivateKey(d, n);
    } //the bounds are low otherwise encoding text won't work

    public void setPublicKey(int e, int n){
        publicKey[0] = e;
        publicKey[1] = n;
    }

    public void setPrivateKey(int d, int n){
        privateKey[0] = d;
        privateKey[1] = n;
    }

    public void encryption(){
        String cText = "";
        for(int i = 0; i < inputText.length(); i++){
            BigInteger number = new BigInteger("1");
            for(int j = 0; j < publicKey[0]; j++){
                number = number.multiply(BigInteger.valueOf(inputText.charAt(i)));
            }
            number = number.mod(BigInteger.valueOf(publicKey[1]));
            cText += (char)number.intValue();
        } encryptedText = cText;
    }

    public void decryption(){
        String dText = "";
        for(int i = 0; i < inputText.length(); i++){
            BigInteger number = new BigInteger("1");
            for(int j = 0; j < privateKey[0]; j++){
                number = number.multiply(BigInteger.valueOf(encryptedText.charAt(i)));
            }
            number = number.mod(BigInteger.valueOf(privateKey[1]));
            dText += (char)number.intValue();
        } decryptedText = dText;
    }

    private void calculus(){
        n = p * q;
        z = (p - 1) * (q - 1);
        e = coprime(z);
        d = calculateD(e, z);
    }

    private boolean prime(int n){
        int count = 2;
        while(n % count != 0)
            count = count + 1;
        return count == n;
    }

    private int coprime(int z){
        ArrayList<Long> coprimes = new ArrayList<>();
        for(long i = 2; i <= z; i++){
            if(z % i == 0) coprimes.add(i);
        }
        int e;
        ArrayList<Integer> possibilities = new ArrayList<>();
        for(e = 2; e < z; e++){
            boolean found = true;
            for(int j = 0; j < coprimes.size() && found; j++){
                found = e % coprimes.get(j) != 0;
            } if(found) possibilities.add(e);
        } return possibilities.get(ThreadLocalRandom.current().nextInt(0,possibilities.size())); //take a random e from the list

        /*ArrayList<Long> coprimes = new ArrayList<>(); //with this version I take the first useful "e" value
        for(long i = 2; i <= z; i++){
            if(z % i == 0) coprimes.add(i);
        }
        int e;
        boolean found = false;
        for(e = 2; e < z && !found; e++){
            found = true;
            for(int j = 0; j < coprimes.size() && found; j++){
                found = e % coprimes.get(j) != 0;
            }
        } return e - 1;*/
    }

    private int calculateD(int e, int z){
        double d;
        int Q = 1;
        boolean trueInteger;
        do{
            d = ((z * Q) + 1.0) / e;
            trueInteger = (d - (int) d) == 0;
            Q++;
        } while(!trueInteger);
        return (int)d;
    }

    public void enteredText(String inputText){
        this.inputText = inputText;
    }
}
