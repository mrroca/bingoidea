package cn.com.bsoft.proxy.util;

import java.io.*;
import java.security.*;
//import javax.crypto.*;
//import javax.crypto.spec.*;

/**
 * <p>
 * Title: RSA非对称型加密的公钥和私钥
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class KeyRSA {
	private KeyPairGenerator kpg = null;
	private KeyPair kp = null;
	private PublicKey public_key = null;
	private PrivateKey private_key = null;
	private FileOutputStream public_file_out = null;
	private ObjectOutputStream public_object_out = null;
	private FileOutputStream private_file_out = null;
	private ObjectOutputStream private_object_out = null;

	/**
	 * 构造函数
	 * 
	 * @param in
	 *            指定密匙长度（取值范围：512～2048）
	 * @throws NoSuchAlgorithmException
	 *             异常
	 */
	public KeyRSA(int in, String address) throws NoSuchAlgorithmException,
			FileNotFoundException, IOException {
		kpg = KeyPairGenerator.getInstance("RSA"); // 创建‘密匙对’生成器
		kpg.initialize(in); // 指定密匙长度（取值范围：512～2048）
		kp = kpg.genKeyPair(); // 生成‘密匙对’，其中包含着一个公匙和一个私匙的信息
		public_key = kp.getPublic(); // 获得公匙
		private_key = kp.getPrivate(); // 获得私匙
		// 保存公匙
		public_file_out = new FileOutputStream(address + "/public_key.dat");
		public_object_out = new ObjectOutputStream(public_file_out);
		public_object_out.writeObject(public_key);
		// 保存私匙
		private_file_out = new FileOutputStream(address + "/private_key.dat");

		private_object_out = new ObjectOutputStream(private_file_out);
		private_object_out.writeObject(private_key);
	}

	public static void main(String[] args) {
		try {
			System.out.println("私匙和公匙保存到C盘下的文件中.");

			new KeyRSA(1024, "c:/");
		} catch (IOException ex) {
		} catch (NoSuchAlgorithmException ex) {
		}
	}

}