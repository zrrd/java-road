package cn.learn.utils;

import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by Administrator on 2018/4/17 0017.
 */
@Slf4j
public class Md5Util {

    /**
     * 云讯AXB模式外呼sign组装
     * @param unSignMap
     * @param secretKey
     * @param compId
     * @return
     * @autor tangjinge
     */
    public static String msgdgt(Map<String, Object> unSignMap, String secretKey, String compId) {
        if (unSignMap == null || unSignMap.isEmpty()) {
            return null;
        }
        Set<String> keySet = unSignMap.keySet();
        Iterator<String> iter = keySet.iterator();
        StringBuilder sign = new StringBuilder(secretKey);
        while (iter.hasNext()) {
            String key = iter.next();
            sign.append(key).append((String)unSignMap.get(key));
        }
        sign.append(compId);
        log.info("new-sign: ----------" + sign.toString());
        return myNewEncrypt32(sign.toString());
    }

    /**
     * MD5 加密参数且大写返回
     * @param encryptStr
     * @return
     * @autor tangjinge
     */
    public static String myNewEncrypt32(String encryptStr) {
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
            encryptStr = hexValue.toString().toUpperCase();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     * md5 32位加密
     * @param encryptStr
     * @return
     */
    public static String encrypt32(String encryptStr) {
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
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    /**
     *   密码 盐值加密
     * @param encryptStr
     * @return
     */
    public static String myEncrypt32(String encryptStr,String beginStr,String endStr) {
        MessageDigest md5;
        try {
            if(!StringUtils.isEmpty(beginStr)){
                encryptStr = beginStr + encryptStr;
            }
            if(!StringUtils.isEmpty(endStr)){
                encryptStr = encryptStr + endStr;
            }
            md5 = MessageDigest.getInstance("MD5");
            log.info("待加密串："+encryptStr);
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16)
                    hexValue.append("0");
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        log.info("加密后："+encryptStr);
        return encryptStr;
    }

    public static void main(String[] args) {
        System.out.println(myEncrypt32("cde522873141ae8eabef4d711695a110anucode1,2,3appkeyXZGF_0005calldisplay0,0callrecording0callrestrict1expiration180requestIdhkdw78540783subts20180621125404telA13588430320telB13082833223telX13194458418ts20180621125404000756700014",null,null));
    }
}
