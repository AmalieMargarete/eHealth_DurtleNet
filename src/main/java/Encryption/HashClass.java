package Encryption;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

// Java Doc: https://docs.oracle.com/javase/9/security/java-security-overview1.htm#JSSEC-GUID-2EF91196-D468-4D0F-8FDC-DA2BEA165D10
/**
 * Class is used to hold encryption functions.
 * @author Viktor Benini; StudentID: 1298976
 */
public class HashClass {
    // Available algorithms are: MD2, MD5, SHA-1, SHA-224, SHA-256, SHA-384, SHA-512
    // (ordered by security level, SHA-224 is minimum lvl of security)

    /**
     * By inputting a String "algorithm" the encryption method is suggested and the corresponding
     * constructor is called. By adding the inputBytes, by converting a string, the update Method
     * uses the hash ont the string. By the digest call the encrypted message will be completed.
     * Afterward the hashed string will be returned as a HexString.
     * @param inputBytes
     * @param algorithm
     * @return
     */
    public static String getHash(byte[] inputBytes, String algorithm){
        String hashValue = "";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            messageDigest.update(inputBytes);
            // SMALL INFORMATION ABOUT FUNCTIONALITY
            // Der Eingangswert wird in gleichlange Elemente unterteilt. Diese nennt man Blöcke.
            // Da ein Vielfaches der Blocklänge benötigt wird, ist es in der Regel notwendig, die
            // Daten aufzufüllen, also zu expandieren. Der aufgefüllte Wert ist das Padding. Die Verarbeitung erfolgt danach blockweise.
            // Es werden die Blöcke durchlaufen und dabei jeweils als Schlüssel für Zwischenberechnungen an den nachfolgend zu kodierenden Daten verwendet.
            // Der Datensatz wird in 64 Runden (SHA224 und SHA256) oder 80 Runden (SHA384 und SHA512) durchlaufen. Das Ergebnis der letzten Berechnung
            // ist der Ausgabewert, also der Hash.

            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        }catch(Exception e){

        }
        return hashValue;
    }

    /**
     * same functionality like in first method, just with defined algorithm and by parsing a String.
     * For easier use in the existing program
     * @param password
     * @return
     */
    public static String getHash(String password){
        String hashValue = "";
        try{
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256"); // hash algorithm that's not too slow but also not to fast
            messageDigest.update(password.getBytes());
            byte[] digestedBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
        }catch(Exception e){

        }
        return hashValue;
    }
}
