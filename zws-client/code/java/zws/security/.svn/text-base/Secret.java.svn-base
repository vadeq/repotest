package zws.security;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;

/*
 * TBD: description, how to use. DesignState - Design Compression Technology
 * @author: HP USER @version: 1.0 Created on Apr 17, 2007 8:40:46 PM Copywrite
 * (c) 2007 Zero Wait-State Inc. All rights reserved
 */

public class Secret {

  private Cipher encryptCipher = null;

  private Cipher decryptCipher = null;

  private PublicKey localPublicKey = null;

  private PrivateKey privateKey = null;

  private SecretKey secretKey = null;

  private String encryptionAlgo = null;

  private String keyPairAlgo = null;

  private static String prime1024 = "100213114278716568824123615835936278359210139657578045359023414779715720051987303300168495084039573811277882063779533774205945083198850524804681598646100262913769312439789617411750210270494160685905212842715350211783960825044038057404395077139556916318790839695923831973660202525094672754456307635210327376341";

  private static String base1024 = "89314146013974073167928821438611631168001156750237599217197501120472453802591601513747746578085299314484223515632357487034781761736966972282150652313921292665119493870062809160180904602469679234662338501036872199547260128476367082809500668920828738669771596563891059005760625808597429488380245544782573401420";

  private static int randomExp1024 = 1020;

  public Secret() {
    initialize("DES", "DH", prime1024, base1024, randomExp1024);
  }

  public Secret(String encryptionAlgorithm, String keyPairAlgorithm,
      String prime, String base, int randomExp) {
    initialize(encryptionAlgorithm, keyPairAlgorithm, prime, base, randomExp);
  }

  private void initialize(String encryptionAlgorithm, String keyPairAlgorithm,
      String prime, String base, int randomExp) {
    this.encryptionAlgo = encryptionAlgorithm;
    this.keyPairAlgo = keyPairAlgorithm;
    try {
      KeyPairGenerator kpGen = KeyPairGenerator.getInstance(keyPairAlgorithm);

      // {} //System.out.println(": " + prime + " " + base + " " + randomExp);

      DHParameterSpec dhSpec = new DHParameterSpec(new BigInteger(prime),
          new BigInteger(base), randomExp);

      kpGen.initialize(dhSpec);
      KeyPair keyPair = kpGen.generateKeyPair();

      privateKey = keyPair.getPrivate();
      localPublicKey = keyPair.getPublic();
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  public void configure(PublicKey remotePublic) {

    try {

      KeyAgreement keyAgree = KeyAgreement.getInstance(keyPairAlgo);
      keyAgree.init(privateKey);
      keyAgree.doPhase(remotePublic, true);

      secretKey = keyAgree.generateSecret(encryptionAlgo);
      // secretKeys.put(id, secretKey);

      encryptCipher = Cipher.getInstance("DES");
      encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

      decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
      decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);

    } catch (Exception ex) {
      ex.printStackTrace();
      System.err.println("Exception while generating secret key: "
          + ex.getMessage());
    }
  }
  
  public Secret(SecretKey secretKey){
    try {

      encryptCipher = Cipher.getInstance("DES");
      encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);

      decryptCipher = Cipher.getInstance("DES/ECB/NoPadding");
      decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);

    } catch (Exception ex) {
      ex.printStackTrace();
      System.err.println("Exception while generating secret key: "
          + ex.getMessage());
    }
  }

  public PublicKey getLocalPublicKey() {
    return localPublicKey;
  }

  public PrivateKey getPrivateKey() {
    return privateKey;
  }
  
  public SecretKey getSecretKey(){
    return secretKey;
  }

  /*
   * public SecretKey getSecretKey(){ return secretKey; }
   */

  public String encrypt(String toEncrypt) {
    String encryptedString = null;
    try {

      byte[] encBytes = encryptCipher.doFinal(toEncrypt.getBytes("UTF8"));
      encryptedString = new sun.misc.BASE64Encoder().encode(encBytes);
    } catch (Exception ex) {
      ex.printStackTrace();
      {} //System.out.println("Exception while encrypting the data: "          + ex.getMessage());
    }
    return encryptedString;
  }
  
  public void serializeByEncrypting(OutputStream os, Object o) {
    try {
    //  Create stream
       BufferedOutputStream bos =
       new BufferedOutputStream(os);
      CipherOutputStream cos =
       new CipherOutputStream(bos, encryptCipher);
      ObjectOutputStream oos =
       new ObjectOutputStream(cos);
  
      // Write objects
      oos.writeObject(o);
      oos.flush();
      oos.close();
    } catch(Exception ex){
      ex.printStackTrace();
    }

  }
  
  public Object deserializeByDecrypting(InputStream is) {
    try {
      BufferedInputStream bis =
      new BufferedInputStream(is);
      CipherInputStream cis =
      new CipherInputStream(bis, decryptCipher);
      ObjectInputStream ois =
       new ObjectInputStream(cis);
      return ois.readObject();
    } catch(Exception ex){
      ex.printStackTrace();
    }
    return null;
  }

  public void encryptFile(InputStream in, OutputStream out) {
    byte[] buf = new byte[1024];
    try {
      // Bytes written to out will be encrypted
      out = new CipherOutputStream(out, encryptCipher);

      // Read in the cleartext bytes and write to out to encrypt
      int numRead = 0;
      while ((numRead = in.read(buf)) >= 0) {
        out.write(buf, 0, numRead);
      }
      
    } catch (java.io.IOException e) {
      e.printStackTrace();
      {} //System.out.println("Unable to encrypt file: " + e.getMessage());
    } finally {
      try {
        out.close();
        in.close();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
  }

  public String decrypt(String toDecrypt) {
    String decryptedString = null;
    try {
      // Decode base64 to get bytes
      byte[] decryptBytes = new sun.misc.BASE64Decoder()
          .decodeBuffer(toDecrypt);
      // {} //System.out.println(decryptBytes);
      // Decrypt
      byte[] utf8 = decryptCipher.doFinal(decryptBytes);
      decryptedString = new String(utf8, "UTF8");
    } catch (Exception ex) {
      {} //System.out.println("Exception while decrypting the data: "          + ex.getMessage());
    }
    return decryptedString.trim();
  }
  
  public void decryptFile(InputStream in, OutputStream out) {
    byte[] buf = new byte[1024];
    try {
        // Bytes read from in will be decrypted
        in = new CipherInputStream(in, decryptCipher);

        // Read in the decrypted bytes and write the cleartext to out
        int numRead = 0;
        while ((numRead = in.read(buf)) >= 0) {
            out.write(buf, 0, numRead);
        }
    } catch (java.io.IOException e) {
      e.printStackTrace();
    }finally {
      try {
        out.close();
        in.close();
      }catch(Exception e){
        e.printStackTrace();
      }
    }
}



  public static PublicKey constructPublicKey(byte[] pubKeyBytes) {
    PublicKey remotePublic = null;
    try {
      X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKeyBytes);
      KeyFactory keyFact = KeyFactory.getInstance("DH");
      remotePublic = keyFact.generatePublic(x509KeySpec);
    } catch (Exception ex) {
      {} //System.out.println("Exception while generating public key from bytes");
      ex.printStackTrace();
    }
    return remotePublic;
  }

  /**
   * This param set should be same at both client and server. This method cannot
   * be used runtime It can be used as a utility to generate prime and base
   * values which should be stored on both client and server
   * 
   * @return string in xml format.
   */
  public static String generateDHParamSet(String keyPairAlgorithm, int keySize) {
    String xmlDHParamSet = null;
    try {
      AlgorithmParameterGenerator aParamGen = AlgorithmParameterGenerator
          .getInstance(keyPairAlgorithm);
      aParamGen.init(keySize);
      AlgorithmParameters aParams = aParamGen.generateParameters();
      DHParameterSpec dhSpec = (DHParameterSpec) aParams
          .getParameterSpec(DHParameterSpec.class);
      {} //System.out.println(": " + dhSpec.getP() + " " + dhSpec.getG() + " "          + dhSpec.getL());
      xmlDHParamSet = "<DHParams prime='" + dhSpec.getP() + "' base='"
          + dhSpec.getG() + "' exponent='" + dhSpec.getL() + "'/>";
      {} //System.out.println(xmlDHParamSet);
    } catch (Exception ex) {
      System.err.println("Exception while generating DH Param Set");
      ex.printStackTrace();
    }
    return xmlDHParamSet;
  }

  public static void main(String[] args) {

  /*
   * String prime =
   * "10053776548662673179511759928845035994918254207026704727561457740539680980592162075014614679602073335037570753987727141658551858107297125544804362622016647";
   * String base =
   * "3633262221140915020982594780547472202048738122136571555432130525604794582430717778843610356665997813030045414179065116359415032719572377842202556707714818";
   * int randomExp = new Random(512).nextInt(); String prime1024 =
   * "100213114278716568824123615835936278359210139657578045359023414779715720051987303300168495084039573811277882063779533774205945083198850524804681598646100262913769312439789617411750210270494160685905212842715350211783960825044038057404395077139556916318790839695923831973660202525094672754456307635210327376341";
   * String base1024 =
   * "89314146013974073167928821438611631168001156750237599217197501120472453802591601513747746578085299314484223515632357487034781761736966972282150652313921292665119493870062809160180904602469679234662338501036872199547260128476367082809500668920828738669771596563891059005760625808597429488380245544782573401420";
   * int randomExp1024 = new Random(1024).nextInt(1025);
   * {} //System.out.println(randomExp1024);
   */

  }

  /*
   * private static String prime512 =
   * "10053776548662673179511759928845035994918254207026704727561457740539680980592162075014614679602073335037570753987727141658551858107297125544804362622016647";
   * private static String base512 =
   * "3633262221140915020982594780547472202048738122136571555432130525604794582430717778843610356665997813030045414179065116359415032719572377842202556707714818";
   * private static int randomExp512 = 512; private static String prime1024 =
   * "100213114278716568824123615835936278359210139657578045359023414779715720051987303300168495084039573811277882063779533774205945083198850524804681598646100262913769312439789617411750210270494160685905212842715350211783960825044038057404395077139556916318790839695923831973660202525094672754456307635210327376341";
   * private static String base1024 =
   * "89314146013974073167928821438611631168001156750237599217197501120472453802591601513747746578085299314484223515632357487034781761736966972282150652313921292665119493870062809160180904602469679234662338501036872199547260128476367082809500668920828738669771596563891059005760625808597429488380245544782573401420";
   * private static int randomExp1024 = 1020;
   */

}
