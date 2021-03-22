package ru.itis.restoke.controllers.helpers;

import javax.xml.bind.*;
import java.security.*;

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
