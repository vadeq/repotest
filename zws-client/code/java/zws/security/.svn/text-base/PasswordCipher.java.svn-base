/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Dec 7, 2007 1:42:51 PM
 * Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */

package zws.security;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;


public class PasswordCipher {

  private Cipher ecipher;
  private Cipher dcipher;
 
  // 8-byte Salt
  byte[] salt = {
      (byte)0xA9, (byte)0x9B, (byte)0xC8, (byte)0x32,
      (byte)0x56, (byte)0x35, (byte)0xE3, (byte)0x03
  };
  
  //Iteration count
  int iterationCount = 19;
  
  public PasswordCipher(String passPhrase)  throws Exception { 
    // Create the key
    KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
    SecretKey key = SecretKeyFactory.getInstance(
        "PBEWithMD5AndDES").generateSecret(keySpec);
    ecipher = Cipher.getInstance(key.getAlgorithm());
    dcipher = Cipher.getInstance(key.getAlgorithm());

    // Prepare the parameter to the ciphers
    AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);

    // Create the ciphers
    ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
    dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);   
  }
  
  public String encrypt(String text) throws Exception {
    byte[] utf8 = text.getBytes("UTF8");
    byte[] enc = ecipher.doFinal(utf8);
    return new sun.misc.BASE64Encoder().encode(enc);
  }

  public String decrypt(String text) throws Exception {
    byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(text);
    byte[] utf8 = dcipher.doFinal(dec);
    return new String(utf8, "UTF8");    
  }
  
  public static void main(String[] unused) throws Exception {
    
  
    PasswordCipher p = new PasswordCipher("zero0");
    String pwd = "agile";
    String enc = p.encrypt(pwd);
    {} //System.out.println(enc);
    {} //System.out.println(p.decrypt(enc));
    
    PasswordCipher np = new PasswordCipher("zero0");
    {} //System.out.println(np.decrypt(enc));
  }
}

