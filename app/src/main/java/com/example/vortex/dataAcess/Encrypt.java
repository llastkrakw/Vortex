package com.example.vortex.dataAcess;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {

    public static  String md5(String str){
        MessageDigest digest;

        try{

            digest = MessageDigest.getInstance("MD5");
            digest.reset();
            digest.update(str.getBytes());
            byte[] a = digest.digest();
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);

            for (byte b : a) {
                sb.append(Character.forDigit((b & 0xf0) >> 4, 16));
                sb.append(Character.forDigit((b & 0xf0), 16));
            }

            return sb.toString();


        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return null;
    }
}
