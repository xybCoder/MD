package com.library.common.util;

import android.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by dell on 2016/5/20.
 */
public class DigestUtil {

    /**
     * Used to build output as Hex
     */
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
            'e', 'f'                         };

    private DigestUtil() {
        throw new AssertionError();
    }

    /**
     * encode By MD5
     *
     * @param str
     * @return String
     */
    public static String md5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new String(encodeHex(messageDigest.digest()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 对数据（字节）进行Base64编码
     *
     * @param data 要编码的数据（字节数组）
     * @return 返回编码后的字符串
     */
    public static String Base64Encode(byte[] data) {
        String ret = null;
        if (data != null && data.length > 0) {
            ret = Base64.encodeToString(data, Base64.NO_WRAP);
        }
        return ret;
    }

    /**
     * 对Base64编码后的数据进行还原
     *
     * @param data 使用Base64编码过的数据
     * @return 返回还原后的数据（字节数组）
     */
    public static byte[] Base64Decode(String data) {
        byte[] ret = null;
        if (data != null && data.length() > 0) {
            ret = Base64.decode(data, Base64.NO_WRAP);
        }
        return ret;
    }

    /**
     * 使用MD5获取数据的摘要信息
     *
     * @param data 数据
     * @return 摘要信息
     */
    public static String toMD5(byte[] data) {
        String ret = null;
        try {
            byte[] digest = MessageDigest.getInstance("md5").digest(data);
            ret = Base64Encode(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return ret;
    }

    /**
     * Converts an array of bytes into an array of characters representing the hexadecimal values of each byte in order.
     * The returned array will be double the length of the passed array, as it takes two characters to represent any
     * given byte.
     *
     * @param data a byte[] to convert to Hex characters
     * @return A char[] containing hexadecimal characters
     */
    protected static char[] encodeHex(final byte[] data) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_LOWER[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_LOWER[0x0F & data[i]];
        }
        return out;
    }
}
