package com.athuman.mynumber.web.util;

import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

/**
 * @author phutt
 * */
public class AESUtil {

	private static SecretKeySpec generateMySQLAESKey(final String key,
			final String encoding) {
		try {
			final byte[] finalKey = new byte[16];
			int i = 0;
			for (byte b : key.getBytes(encoding)) {
				finalKey[i++ % 16] ^= b;
			}
			return new SecretKeySpec(finalKey, "AES");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static String encrypt(String value) throws Exception {

		if (StringUtil.isNotEmpty(value)) {
			final Cipher encryptCipher = Cipher.getInstance("AES");
			encryptCipher.init(Cipher.ENCRYPT_MODE,generateMySQLAESKey(PropertyUtil.getProperties("application.properties","database.encrypt.key"), "UTF-8"));
			return (new String(Hex.encodeHex(encryptCipher.doFinal(value.getBytes("UTF-8")))));
		}

		return null;
	}

	public static String decrypt(String value) throws Exception {
		if (StringUtil.isNotEmpty(value)) {
			final Cipher decryptCipher = Cipher.getInstance("AES");
			decryptCipher.init(Cipher.DECRYPT_MODE,generateMySQLAESKey(PropertyUtil.getProperties("application.properties","database.encrypt.key"), "UTF-8"));
			return (new String(decryptCipher.doFinal(Hex.decodeHex(value.toCharArray()))));
		}

		return null;
	}

}
