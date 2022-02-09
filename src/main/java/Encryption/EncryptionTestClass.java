package Encryption;

public class EncryptionTestClass {

    public static void main(String[] args) {
        HashClass hash = new HashClass();
        String test = "test";
        System.out.println(hash.getHash(test.getBytes(), "SHA-224"));


    }
}
