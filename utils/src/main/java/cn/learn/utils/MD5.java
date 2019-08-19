package cn.learn.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/4/17 0017.
 */
@Slf4j
public class MD5 {
    //正式  710006  测试 700025
    public static final String compId = "710006";

    //正式  8948d93aa82eccf5f2ded725cf15ace8  测试 68aa3c7fa62daa507f9a71126ae71545
    public static final String secretKey = "8948d93aa82eccf5f2ded725cf15ace8";

    public static String md5(String plain) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
        md.update(plain.getBytes());
        return new BigInteger(1, md.digest()).toString(16).toUpperCase();
    }

    public static String sign(Map<String, String> unSignMap) {
        Set<String> keySet = unSignMap.keySet();
        Iterator<String> iter = keySet.iterator();
        StringBuilder sign = new StringBuilder(secretKey);
        while (iter.hasNext()) {
            String key = iter.next();
            sign.append(key).append(unSignMap.get(key));
        }
        sign.append(compId);
        System.out.println(myEncrypt32(sign.toString()).toUpperCase());
        return myEncrypt32(sign.toString()).toUpperCase();
    }

    public static String signNew(Map<String, String> unSignMap,String secretKey,String compId) {
        Set<String> keySet = unSignMap.keySet();
        Iterator<String> iter = keySet.iterator();
        StringBuilder sign = new StringBuilder(secretKey);
        while (iter.hasNext()) {
            String key = iter.next();
//            System.out.println(key+"="+unSignMap.get(key));
            sign.append(key).append(unSignMap.get(key));
        }
        sign.append(compId);
//        System.out.println(sign.toString());
        return myEncrypt32(sign.toString()).toUpperCase();
    }

    public static String signStr(String sign) {
        if(sign != null && sign.length() > 0 ){
            return myEncrypt32(sign);
        }else{
            return  null;
        }
    }


    public static String myEncrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString().toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }


    public static void main(String[] args) {
        Integer test = null;
        System.out.println(test + "");
    }

}
