/* DesignState - Design Compression Technology
 * @author: Rodney
 * @version: 1.0
 * Created on Apr 23, 2008 7:53:47 AM
 * Copywrite (c) 2008 Zero Wait-State Inc. All rights reserved */

package zws.hi.security;

import com.zws.hi.Interactor;
import zws.security.CryptoUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class hiEncryptPassword extends Interactor {

  
  public String encrypt() {
    status = "";
    encryptedPassword1 ="";
    try {
      if (password1 == null || password2 == null || password1.length()<1 || password2.length() <1) {
        logFormError("err.password.required");
      } else if (!password1.equals(password2)) {
        logFormError("err.password.not.equal");
      } else {
        CryptoUtil cryptoUtil = new CryptoUtil(); 
        encryptedPassword1 = cryptoUtil.encrypt(password1);
        logFormStatus("status.password.encrypted");
      }
    } catch (Exception e) {
      logFormError("system.err" + e.getMessage());
    }
    return "index";      
  }

  public String checkEncrypt() {
    status = "";
    try {
      if (password3 == null || encryptedPassword2 == null || password3.length()<1 || encryptedPassword2.length()<1) {
        logFormError("err.password.required");
      } else if (isEncrypted(password3, encryptedPassword2)) {
        logFormStatus("status.password.matched");
        status = "Matched";
      } else {
        logFormError("err.password.not.equal");
      }
    } catch (Exception e) {
      logFormError("err.password.not.equal");
    }    
    return "index";      
  }
  
  private boolean isEncrypted(String src, String encryptedStr) {
    boolean result = false;
    
    try {
      CryptoUtil cryptoUtil = new CryptoUtil();
      String tempPassword = cryptoUtil.decrypt(encryptedStr);
      if(src.equals(tempPassword)) {
        result = true;
      }
    } catch (Exception e) {
      // error while decrypting
    }
    return result;
  }
  

  public String getStatus() {return status;}
  public void setStatus(String s) {status = s;}

  
  public String getPassword1() {return password1;}
  public void setPassword1(String s) {password1 = s;}

  public String getPassword2() {return password2;}
  public void setPassword2(String s) {password2 = s;}
  
  public String getPassword3() {return password3;}
  public void setPassword3(String s) {password3 = s;}

  public String getEncryptedPassword1() {return encryptedPassword1;}
  public void setEncryptedPassword1(String s) {encryptedPassword1 = s;}
  
  public String getEncryptedPassword2() {return encryptedPassword2;}
  public void setEncryptedPassword2(String s) { 
    encryptedPassword2 = s;}

  private String password1= "";
  private String password2 = "";
  private String password3 = "";
  private String encryptedPassword1 = "";
  private String status = "";
  private String encryptedPassword2 = "";
  
}
