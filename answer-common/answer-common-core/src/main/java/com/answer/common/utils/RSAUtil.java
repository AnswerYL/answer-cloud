/**
 * @projectName answer-cloud
 * @package com.answer.common.utils
 * @className com.answer.common.utils.RsaUtils
 */
package com.answer.common.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RsaUtils
 * @description RSA加解密工具类
 * @author answer_wx
 * @date 2024/1/18 23:21
 * @version 1.0
 */
public class RSAUtil {
    /**
     * 默认"RSA"="RSA/ECB/PKCS1Padding"
     */
    private static final String CIPHER_INSTANCE = "RSA/ECB/PKCS1Padding";

    /**
     * 公钥加密
     * @param content 要加密的内容
     * @param publicKey 公钥
     */
    public static String encrypt(String content, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] output = cipher.doFinal(content.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 公钥加密
     * @param content 要加密的内容
     * @param publicKey 公钥
     */
    public static byte[] encrypt(byte[] content, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     * @param content 要解密的内容
     * @param privateKey 私钥
     */
    public static byte[] decrypt(byte[] content, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 私钥解密
     * @param content 要解密的内容
     * @param privateKey 私钥
     */
    public static String decrypt(String content, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_INSTANCE);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] b = cipher.doFinal(content.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String转公钥PublicKey
     * @param key 公钥字符
     */
    public static RSAPublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    /**
     * String转私钥PrivateKey
     * @param key 私钥字符
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = getPrivateKey("MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDqQIfyn4J7FwAE7pveBhotlCMTTGrYtlO9h3dcJKh4FlzSEWEh/jzpDDdXXubHWdG94dznCDArxCgwzMAADKdDYCVIMhOtD7CYXPhNaWT7hC36/qOGL4kF5iVUSSKnh3ifr64xjLew+2+uhaOjyPZPwJ2B3u8WN5qB+W0oyFOMNRz1ovjUpVlZKhk3E5MGyrB3pV/CMoL9CgHbkDYWOwoZNgtTqTZABc9IQ5f2OzfX/ilNcEE+pjkEj44ZbQ5/cMaEeH5zrmAl2ZeC+XXz5Eh/nfBtoUEzz9PcGLPVvBLkB6mC1lkPCxGF+70oNGJR+T5NMHF+oBHtYdFDpn/GiIYFAgMBAAECggEAMlFhQmskDoeuv+B3R7Zc2iyHakZQbzOfAke+imLvczgugQstp60ibVbr7k8a2HiBNr31AwV9v3D3xx+8IGaXaUAWUg87rcGAiAH0bkB06hgG/nLDMng0gTliDFxXXtAZR4fnrYoSDSi9YSogr/ZjflccHkECb23LOR8YZRm+5I883Hjy8gUqLYPnGK0uz/xGMrqSHqyUsP8TJbdskLR6Y7dx1lsT5WkpYtP/uLxyqvh+akrDRRrIXvZV5I7q+/fYpJxHOOmc/sZCOn9AaxOcgtIoDdDiqsQJ/bAPOpOh9XzzmHI7uBDsBg02PaWiKMmVne7PA01/L0fAi1UeHTQj4QKBgQD+cUvyP2LukGMgpmuKY9DO21l0YHdrpVgyIeNVcIaATodR1XkgSBZcDehtdOHFGvMjoV8Bky405ja0/VasduM0kfHt3qm+UMTMDNE0Tp7IOdZSXbIuZYE0kbr7qsRBr2FD+9psvONlcqsTtGjdpjext2fHFl8Bz9MmUOPB+oNitQKBgQDrr5i2K1kEfXizUvViUCjp4CYwMIS4Z/chVaEECHVVmy+qKE98n5Bmi0gbcZtvM/O/U8Hu+d9oWZ4SdUwxFNJoVzxhxTPOS/0XK5ums/k7O/mXm2e7R0wuRoA3zBATTl2CaDDZAxV1TXUeHKnjdkBPTRl9dfvS2FGiAkBdWxYYEQKBgDV/9QwWr+KhkXZIB8yteDZGgOSrc/C6b3T7EyXf5ygLWwCl9jYHckwg0IJTVTVwmTcgsy1NmSWeUlpz+kmBJfzjww7VBrPxn6+X8lUhQtmu75WaBeEjjqtTCRHpsiJtkfINfrvma5nlJnuaL4KLdKJ1geiml4t1+1vPHlSnLAERAoGBAKlHKtkU00/tSqcJ26MsTeaFDwinoHvOeGqh1Q9Pf+7nklmVsFmjRZTRTw4fD/PUDasK3ozlPXf2fhll1D6gZOt6VK0odagjXfmpYZdfRpAr6H3iqaOSdv35txfcYmNrcrA6Y3NKyrvA0XC6R69O/Ku4hald92wd4KwLD+29gWXxAoGBAKhtjDBtKuLp9b5M5j3NsRsZeZGaGbcCyfaK1zXbU5ilQbeBta/G0q3+L9+QZDm931QDWclbMziQXO9vzLJ6ymE06b0UKEIBX1YUHu2qKnOoNreYolWTQimAq+/cT6m5fQeGp7V6Fbq0L+zimpM/G3x1V13Gcenl7rKQ0MgJqCpf");
        RSAPublicKey publicKey = getPublicKey("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6kCH8p+CexcABO6b3gYaLZQjE0xq2LZTvYd3XCSoeBZc0hFhIf486Qw3V17mx1nRveHc5wgwK8QoMMzAAAynQ2AlSDITrQ+wmFz4TWlk+4Qt+v6jhi+JBeYlVEkip4d4n6+uMYy3sPtvroWjo8j2T8Cdgd7vFjeagfltKMhTjDUc9aL41KVZWSoZNxOTBsqwd6VfwjKC/QoB25A2FjsKGTYLU6k2QAXPSEOX9js31/4pTXBBPqY5BI+OGW0Of3DGhHh+c65gJdmXgvl18+RIf53wbaFBM8/T3Biz1bwS5AepgtZZDwsRhfu9KDRiUfk+TTBxfqAR7WHRQ6Z/xoiGBQIDAQAB");

        byte[] encrypt = encrypt("answer".getBytes(StandardCharsets.UTF_8), publicKey);
        System.out.println(new String(Base64.getEncoder().encode(encrypt)));
        System.out.println(new String(decrypt(encrypt, privateKey)));
    }
}