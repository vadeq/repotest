/*
 * DesignState - Design Compression Technology 
 * @author: ptoleti 
 * @version: 1.0 
 * Created on Apr 24, 2008 2:48:26 PM 
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved
 */

package zws.security;

import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class CryptoUtil {
  public CryptoUtil() throws Exception {
    this(DESEDE_ENCRYPTION_SCHEME, getDefaultEncryptionKey());
  }
  
  public CryptoUtil(String encryptionScheme) throws Exception {
    this(encryptionScheme, getDefaultEncryptionKey());
  }

  private static String getDefaultEncryptionKey() {
    String s = DEFAULT_ENCRYPTION_KEY8 + DEFAULT_ENCRYPTION_KEY8 + DEFAULT_ENCRYPTION_KEY3 + DEFAULT_ENCRYPTION_KEY2 +DEFAULT_ENCRYPTION_KEY9;
    return s;
  }
  
  public CryptoUtil(String encryptionScheme, String encryptionKey) throws Exception {

    if (encryptionKey == null) throw new IllegalArgumentException("encryption key was null");
    if (encryptionKey.trim().length() < 24) throw new IllegalArgumentException("encryption key was less than 24 characters");

    byte[] keyAsBytes = encryptionKey.getBytes(UNICODE_FORMAT);

    if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
      keySpec = new DESedeKeySpec(keyAsBytes);
    } else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
      keySpec = new DESKeySpec(keyAsBytes);
    } else {
      throw new IllegalArgumentException("Encryption scheme not supported: " + encryptionScheme);
    }

    keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
    cipher = Cipher.getInstance(encryptionScheme);
  }

  public String encrypt(String unencryptedString) throws Exception {
    if (unencryptedString == null || unencryptedString.trim().length() == 0) { 
      throw new IllegalArgumentException("unencrypted string was null or empty");
      }

    SecretKey key = keyFactory.generateSecret(keySpec);
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] cleartext = unencryptedString.getBytes(UNICODE_FORMAT);
    byte[] ciphertext = cipher.doFinal(cleartext);

    BASE64Encoder base64encoder = new BASE64Encoder();
    return base64encoder.encode(ciphertext);
  }

  public String decrypt(String encryptedString) throws Exception {
    if (encryptedString == null || encryptedString.trim().length() <= 0) throw new IllegalArgumentException("encrypted string was null or empty");

    SecretKey key = keyFactory.generateSecret(keySpec);
    cipher.init(Cipher.DECRYPT_MODE, key);
    BASE64Decoder base64decoder = new BASE64Decoder();
    byte[] cleartext = base64decoder.decodeBuffer(encryptedString);
    byte[] ciphertext = cipher.doFinal(cleartext);

    return bytes2String(ciphertext);
  }

  private static String bytes2String(byte[] bytes) {
    StringBuffer stringBuffer = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      stringBuffer.append((char) bytes[i]);
    }
    return stringBuffer.toString();
  }
  

  
  public static void main(String arg[]) {
    String action = null;;
    String value = null;;
    try {
      if(arg.length != 2) {
        System.out.println("usage: CryptoUtil encrypt/decrypt value");
        return;
      }
      CryptoUtil encrypter = new CryptoUtil(DESEDE_ENCRYPTION_SCHEME);
      action = arg[0];
      value = arg[1];
      if("encrypt".equalsIgnoreCase(action)) {
        String encrypted = encrypter.encrypt(value);
        System.out.println("encrypted value");
        System.out.println(encrypted);
      } else if("decrypt".equalsIgnoreCase(action)) {
        String decrypted = encrypter.decrypt(value);
        System.out.println("decrypted value");
        System.out.println(decrypted);
      } else {
        System.out.println("invalid action entered");
      }
    } catch (Exception e) {
      System.out.println("Failed action " + action);
      //e.printStackTrace();
    }
  }
  
  private  static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
  private  static final String DES_ENCRYPTION_SCHEME = "DES";
  private  static final String DEFAULT_ENCRYPTION_KEY0 = "fgr$#%#thesrhew";
  private  static final String DEFAULT_ENCRYPTION_KEY1 = "fdg";
  private  static final String DEFAULT_ENCRYPTION_KEY2 = "gfdgdfsg";
  private  static final String DEFAULT_ENCRYPTION_KEY3 = "gfdsg";
  private  static final String DEFAULT_ENCRYPTION_KEY4 = "sfdgf";
  private  static final String DEFAULT_ENCRYPTION_KEY5 = "dsf$#%g34fdg";
  private  static final String DEFAULT_ENCRYPTION_KEY6 = "(7867567try";
  private  static final String DEFAULT_ENCRYPTION_KEY7 = "gfd5tgbn<.,";
  private  static final String DEFAULT_ENCRYPTION_KEY8 = "dfsgwq345[]]";
  private  static final String DEFAULT_ENCRYPTION_KEY9 = "345fgd[*67rt*&";
  private  static final String DEFAULT_ENCRYPTION_KEY10 = "345DFG][z{";
  private KeySpec keySpec;
  private SecretKeyFactory keyFactory;
  private Cipher cipher;
  private static final String UNICODE_FORMAT = "UTF8";

}
