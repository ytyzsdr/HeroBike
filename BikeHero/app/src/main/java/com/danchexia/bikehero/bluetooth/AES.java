package com.danchexia.bikehero.bluetooth;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by farley on 17/7/25.
 * description:加密AES 128
 */

public class AES {
    public static  byte[] staticKey = {(byte)0x87,(byte)0x16,(byte)0x7D,(byte)0xAF,
            (byte)0xBF,(byte)0xC4,(byte)0x14,(byte)0xEA,
            (byte)0x3C,(byte)0x2F,(byte)0x9B,(byte)0x24,(byte)0x8A,(byte)0x63,
            (byte)0x28,(byte)0x54};
    //    public static byte[] staticPaw = {(byte)0x30,(byte)0x30,(byte)0x30,(byte)0x30,(byte)0x30,(byte)0x30};20:26:22:1f:24:21
    public static byte[] staticPaw = {(byte)0x20,(byte)0x26,(byte)0x22,(byte)0x1f,(byte)0x24,(byte)0x21};

    public static byte[] encrypt(byte[] plainText) {
        return encrypt(plainText,staticKey);
    }

    /**
     * 加密
     *
     * @param content
     *            需要加密的内容
     * @param enckey
     *            加密密码
     * @return
     */
    public static byte[] encrypt(byte[] content, byte[] enckey) {
        try {
            SecretKeySpec key = new SecretKeySpec(enckey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 创建密码器
            cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] cipherText) {
        return decrypt(cipherText,staticKey);
    }
    /**
     * 解密
     *
     * @param content
     *            待解密内容
     * @param deckey
     *            解密密钥
     * @return
     */
    public static byte[] decrypt(byte[] content, byte[] deckey) {
        try {
            SecretKeySpec key = new SecretKeySpec(deckey, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 创建密码器
            cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
            byte[] result = cipher.doFinal(content);
            return result; // 加密
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
