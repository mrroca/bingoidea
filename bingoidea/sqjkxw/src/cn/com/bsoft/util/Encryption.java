package cn.com.bsoft.util;

import java.security.*;

import javax.crypto.Cipher;

import javax.crypto.SecretKey;

import javax.crypto.SecretKeyFactory;

import javax.crypto.spec.DESKeySpec;

public class Encryption {

	private static final String PASSWORD_CRYPT_KEY = "SZDataCenter";

	private final static String DES = "DES";

	public static void main(String[] args) {
		String a = Encryption.encrypt("password$szdc");
		String b = Encryption.decrypt(a);
		String c = Encryption.decrypt("1AD30D7DF3B5BE23");
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
	}

	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {

		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);

		return cipher.doFinal(src);

	}

	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {

		SecureRandom sr = new SecureRandom();

		DESKeySpec dks = new DESKeySpec(key);

		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);

		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance(DES);

		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

		return cipher.doFinal(src);

	}

	public final static String decrypt(String data) {

		try {

			return new String(decrypt(hex2byte(data.getBytes()),

			PASSWORD_CRYPT_KEY.getBytes()));

		} catch (Exception e) {

		}

		return null;

	}

	public final static String encrypt(String password) {

		try {

			return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY
					.getBytes()));
		} catch (Exception e) {

		}

		return null;

	}

	public static String byte2hex(byte[] b) {

		String hs = "";

		String stmp = "";

		for (int n = 0; n < b.length; n++) {

			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));

			if (stmp.length() == 1)

				hs = hs + "0" + stmp;

			else

				hs = hs + stmp;

		}

		return hs.toUpperCase();

	}

	public static byte[] hex2byte(byte[] b) {

		if ((b.length % 2) != 0)

			throw new IllegalArgumentException("目标不是二进制数");

		byte[] b2 = new byte[b.length / 2];

		for (int n = 0; n < b.length; n += 2) {

			String item = new String(b, n, 2);

			b2[n / 2] = (byte) Integer.parseInt(item, 16);

		}

		return b2;
	}
}
