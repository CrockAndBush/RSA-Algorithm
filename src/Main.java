
public class Main {
    public static void main(String args[]){ //test class
        //////////// example 1 //////////// in this example I will "generate" the random keys
        RSA rsa_1 = new RSA();
        rsa_1.enteredText("I like pizza with pepperoni");
        rsa_1.generateKeys();
        rsa_1.encryption();
        rsa_1.decryption();

        System.out.println("Entered text : " + rsa_1.inputText + "\n" +
                "Encrypted text : " + rsa_1.encryptedText + "\n" +
                "Decrypted text : " + rsa_1.decryptedText);

        System.out.println("p = " + rsa_1.p +
                ", q = " + rsa_1.q +
                ", n = " + rsa_1.n +
                ", z = " + rsa_1.z +
                ", e = " + rsa_1.e +
                ", d = " + rsa_1.d);

        System.out.println("Private Key : (" + rsa_1.privateKey[0] + ", " + rsa_1.privateKey[1] + ")" +
                " Public Key : (" + rsa_1.publicKey[0] + ", " + rsa_1.publicKey[1] + ")" + "\n");

        //////////// example 2 //////////// in this one there are already the keys
        RSA rsa_2 = new RSA();
        rsa_2.enteredText("I like pizza with pepperoni");
        rsa_2.setPublicKey(1240, 4832);
        rsa_2.setPrivateKey(2195, 4832);
        rsa_2.encryption();
        rsa_2.decryption();

        System.out.println("Entered text : " + rsa_1.inputText + "\n" +
                "Encrypted text : " + rsa_1.encryptedText + "\n" +
                "Decrypted text : " + rsa_1.decryptedText);

        System.out.println("Private Key : (" + rsa_2.privateKey[0] + ", " + rsa_2.privateKey[1] + ")" +
                " Public Key : (" + rsa_2.publicKey[0] + ", " + rsa_2.publicKey[1] + ")");
    }
}
