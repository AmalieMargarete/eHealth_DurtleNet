package Encryption;

/**
 * Test Class to check if the encryption method works like intended
 */
public class EncryptionTestClass {

    /**
     * Calls the HashClass function to hash the String test by the given algorithm SHA-224
     * @param args
     */
    public static void main(String[] args) {
        HashClass hash = new HashClass();
        String test = "test";
        System.out.println(hash.getHash(test.getBytes(), "SHA-224"));


    }
}
