package ru.itis.restoke.servlets.helpers;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class HashFunctions {
    public static String getHash(byte[] inputBytes, String algorithms) {
        String hashValue = "";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithms);
            messageDigest.update(inputBytes);
            byte[] digestBytes = messageDigest.digest();
            hashValue = DatatypeConverter.printHexBinary(digestBytes).toLowerCase();
        }
        catch (Exception e) {

        }
        return hashValue;
    }
}
