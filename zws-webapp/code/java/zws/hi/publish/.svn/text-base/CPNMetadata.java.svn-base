package zws.hi.publish;/*
                         * DesignState - Design Compression Technology @author:
                         * arbthaku @version: 1.0 Created on Dec 27, 2007
                         * 8:25:10 PM Copywrite (c) 2007 Zero Wait-State Inc.
                         * All rights reserved
                         */

import zws.application.Names;
import zws.data.Metadata;
import zws.hi.report.MetadataAdapter;
import zws.origin.Origin;
import zws.qx.QxContext;
import zws.repository.Repository;
import zws.repository.source.RepositoryMetadataSource;
import zws.security.Authentication;
import zws.service.synchronization.qx.SynchronizationClient;
import zws.service.synchronization.qx.SynchronizationService;
import zws.util.FileNameUtil;

import java.util.Collection;
import java.util.Iterator;
import java.util.StringTokenizer;

public class CPNMetadata extends MetadataAdapter {

  public void adapt(Metadata source, Repository target, Authentication auth) throws Exception {
    id = auth;
    adapt(source);
    targetRepository = target;
    initialize();
  }

  public void detectSynchronizationStatus() {
    synchronizationStatus = null;
    try {
      SynchronizationService s = SynchronizationClient.getClient();
      Origin o = metadata.getOrigin();
      lastSynchronization = s.lastSynchronization(o.getDomainName(), o.getServerName(), o.getRepositoryName(), o.getName());
      if (null == lastSynchronization) {
        synchronizationStatus = Names.STATUS_NOT_SYNCHRONIZED;
        return;
      }
      Collection c = s.findAllSynchronizationOrigins(o);
      Iterator i;
      if (c != null && !c.isEmpty()) {
        ;
        i = c.iterator();
        Origin t;
        while (i.hasNext()) {
          t = (Origin) i.next();
          if (!t.getDomainName().equals(targetRepository.getDomainName())) continue;
          else if (!t.getServerName().equals(targetRepository.getServerName())) continue;
          else if (!t.getRepositoryName().equals(
              targetRepository.getRepositoryName())) continue;
          else {
            lastSynchronization = t;
            synchronizationStatus = Names.STATUS_SYNCHRONIZED;
            return;
          }
        }
      }
      if (null == synchronizationStatus) {
        c = s.findAllSynchronizationOrigins(lastSynchronization);
        i = c.iterator();
        Origin t;
        while (i.hasNext()) {
          t = (Origin) i.next();
          if (!t.getDomainName().equals(targetRepository.getDomainName())) continue;
          else if (!t.getServerName().equals(targetRepository.getServerName())) continue;
          else if (!t.getRepositoryName().equals(
              targetRepository.getRepositoryName())) continue;
          else {
            lastSynchronization = t;
            synchronizationStatus = Names.STATUS_PREVIOUSLY_SYNCHRONIZED;
            return;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (null == synchronizationStatus) synchronizationStatus = UNKNOWN;
  }

  private void initTargetData() throws Exception {
    String targetName = null;
    if (Names.STATUS_SYNCHRONIZED.equals(synchronizationStatus)) return;
    if (null != lastSynchronization) {
      initTargetCPN();
      targetName = targetCPN;
    }
    else {
    targetName = FileNameUtil.getBaseName(metadata.getName()).toUpperCase();
    }
    loadTargetMetadata(targetName );
  }

  private void initTargetCPN() {
    if (null != lastSynchronization) {
      targetCPN = lastSynchronization.getName();
      if (targetCPN.length() < 6) return;
      targetCPN = targetCPN.substring(4);
      /*  $1.prt => MEP_$1_P
          $1.gph => MEP_$1_U
          $1.asm => MEP_$1_A
          $1.drw => MEP_$1_D
          $1.frm => MEP_$1_F */
      if (targetCPN.toUpperCase().endsWith("_P") ||
          targetCPN.toUpperCase().endsWith("_U") ||
          targetCPN.toUpperCase().endsWith("_A") ||
          targetCPN.toUpperCase().endsWith("_D") ||
          targetCPN.toUpperCase().endsWith("_F")) {
        targetCPN = targetCPN.substring(0, targetCPN.length() - 2);
      }
    } else if (null != targetMetadata) {
      //targetCPN = targetMetadata.getName();
      targetCPN = targetMetadata.get("number");
    }
    else {
      //targetCPN = FileNameUtil.getBaseName(metadata.getName()).toUpperCase();
      targetCPN = FileNameUtil.getBaseName(metadata.get("number")).toUpperCase();
    }
    validateCPNFormat();
  }

  private void initStatusCPN() {
    if (Names.STATUS_SYNCHRONIZED.equals(synchronizationStatus)
        && null != lastSynchronization
        && 3 < lastSynchronization.getName().toLowerCase().indexOf( targetCPN.toLowerCase()))
      statusCPN = CPN_SYNCHRONIZED;
    else if (!validCPN && NO.equals(availableCPN))
      statusCPN = CPN_TEMPORARY;
    else if (!validCPN && YES.equals(availableCPN))
      statusCPN = CPN_ERROR;
    else if (validCPN && UNKNOWN.equals(availableCPN))
      statusCPN = CPN_NEEDS_VERIFICATION;
    else if (validCPN && NO.equals(availableCPN))
      statusCPN = CPN_MISSING;
    else if (validCPN && YES.equals(availableCPN))
      statusCPN = CPN_READY;
    else statusCPN = UNKNOWN;
  }

  /*private boolean targetCPNisValidFormat() {
    validateCPNFormat();
    return validCPN;
  }*/

  private void validateCPNFormat() {
    // Cisco Part Number Format:
    // N-N-N
    // CPN regex: \d{2,3}-\d{4,7}-\d{2,3}
    if (null == targetCPN) {
      validCPN = false;
      return;
    }
    targetCPN = targetCPN.trim();
    if (1 < targetCPN.indexOf('.')) targetCPN = FileNameUtil.getBaseName(targetCPN);
    targetCPN = targetCPN.trim().toUpperCase();
    if ("".equals(targetCPN)) {
      validCPN = false;
      return;
    }
    StringTokenizer tokens = new StringTokenizer(targetCPN, "-");
    if (tokens.countTokens()<2 || tokens.countTokens()>3) {
      validCPN = false;
      return;
    }
    String s1 = tokens.nextToken(); // 2 or 3 digits
    String s2 = tokens.nextToken(); // 4-7 digits
    String s3=null;
    //if (tokens.countTokens()==3)  s3 = tokens.nextToken(); // 2 or 3 digits
    if (tokens.hasMoreTokens())  s3 = tokens.nextToken(); // 2 or 3 digits
    if (!(2 <= s1.length() && s1.length() <= 3)) {
      validCPN = false;
      return;
    }
    if (!(4 <= s2.length() && s2.length() <= 7)) {
      validCPN = false;
      return;
    }
    if (null!=s3 && !(2 <= s3.length() && s3.length() <= 3)) {
      validCPN = false;
      return;
    }
    try {
      Integer.valueOf(s1);
    } catch (Exception ignore) {
      validCPN = false;
      return;
    }
    try {
      Integer.valueOf(s2);
    } catch (Exception ignore) {
      validCPN = false;
      return;
    }
    if (null!=s3) {
      try {
        Integer.valueOf(s3);
      } catch (Exception ignore) {
        validCPN = false;
        return;
      }
    }
    validCPN = true;
  }

  private void initMode() {
    if (CPN_SYNCHRONIZED.equals(statusCPN)) mode = LOCKED;
    else if (CPN_TEMPORARY.equals(statusCPN)) mode = SAVED;
    else if (CPN_NEEDS_VERIFICATION.equals(statusCPN)) mode = EDIT;
    else if (CPN_MISSING.equals(statusCPN)) mode = EDIT;
    else if (CPN_READY.equals(statusCPN)) mode = SAVED;
    else if (CPN_ERROR.equals(statusCPN)) mode = EDIT;
    else if (UNKNOWN.equals(statusCPN)) mode = EDIT;
  }

  private void initialize() throws Exception {
    detectSynchronizationStatus();
    initTargetData();
    initTargetCPN();
    initStatusCPN();
    initMode();
  }

  public void verifyCPN() throws Exception{
    boolean verified = false;
    if(CPN_SYNCHRONIZED.equals(statusCPN))
      verified=true; //item is already synchronized.
    else if (validCPN && null!=targetMetadata && targetMetadata.getName().equalsIgnoreCase(targetCPN))
      verified=true; //cpn format is valid and the cpn exists
    else if (!validCPN && NO.equals(availableCPN))
      verified=true; // cpn format is not valid so a cpn should not exist
    else if (!validCPN && YES.equals(availableCPN))
      verified=true; // cpn format is not valid so a cpn should not exist
    else
      verified=false; //remaining situations need to be checked..
    if (!verified) {
      targetMetadata = null;
      loadTargetMetadata(targetCPN);
    }
    initStatusCPN();
    initMode();
  }

  public boolean okToPublish() {
    if (null==statusCPN) return false;
    if (CPN_SYNCHRONIZED.equals(statusCPN)) return true;
    if (CPN_MISSING.equals(statusCPN)) return false;
    if (CPN_NEEDS_VERIFICATION.equals(statusCPN)) return false;
    if (UNKNOWN.equals(statusCPN)) return false;
    if (CPN_READY.equals(statusCPN)) return true;
    if (CPN_TEMPORARY.equals(statusCPN)) return true;
    return false;
  }

  private void loadTargetMetadata(String targetName) throws Exception{
    if (null == targetName ) {
      availableCPN = UNKNOWN;
      return;
    }
    RepositoryMetadataSource r = targetRepository.materializeMetadataSource();
    targetMetadata = r.findLatest(new QxContext(), targetName, id);
    if (null != targetMetadata) availableCPN = YES;
    else availableCPN = NO;
  }

  public String getTargetCPN() {
    return targetCPN;
  }

  public void setTargetCPN(String s) {
    if (null != s && s.trim().equals(targetCPN)) return;
    targetCPN = s;
    if (null != targetCPN) targetCPN = targetCPN.trim().toUpperCase();
    validateCPNFormat();
    if (null != targetMetadata
        && targetMetadata.getName().equalsIgnoreCase(targetCPN)) {
      availableCPN = YES;
    } else {
      targetMetadata = null;
      availableCPN = UNKNOWN;
    }
    initStatusCPN();
    initMode();
  }

  public void toggleEditMode() {
    if (EDIT.equals(mode)) mode = SAVED;
    if (SAVED.equals(mode)) mode = EDIT;
  }

  public String getSynchronizationStatus() {
    return synchronizationStatus;
  }

  public Origin getLastSynchronization() {
    return lastSynchronization;
  }

  public String getStatusCPN() {
    return statusCPN;
  }

  public boolean getValidCPN() {
    return validCPN;
  }

  public String getAvailableCPN() {
    return availableCPN;
  }

  public String getMode() {
    return mode;
  }

  public String getDescription() {
    String d;
    if (null != targetMetadata) d = targetMetadata.get("Description");
    else d = metadata.get("DESC");
    return d;
  }

  public String getStatusDescription() {
    String s  = "";
    if (CPN_TEMPORARY.equals(statusCPN)) {
      s += "Ok to publish (without a CPN).";
    }
    else if (CPN_SYNCHRONIZED.equals(statusCPN)) {
      if (validCPN) s += "Already published (under CPN).";
      else s += "Already published (without a CPN).";
    }
    else if (CPN_MISSING.equals(statusCPN)) s += "Detected missing CPN: Can not publish!";
    else if (CPN_ERROR.equals(statusCPN)) s += "Invalid Part Number found in Agile: Can not publish!";
    else if (CPN_NEEDS_VERIFICATION.equals(statusCPN)) {
      if (validCPN) s += "Needs to be verified.";
      else s += "Needs to be verified.";
    }
    else if (UNKNOWN.equals(statusCPN)) {
      if (validCPN) s += "Needs to be verified.";
      else s += "Needs to be verified.";
    }
    else if (CPN_READY.equals(statusCPN)) {
      if (validCPN) s += "Ok to publish under CPN";
      else s += "Ok to publish without CPN.";
    }
    else s += " Sync: "+synchronizationStatus+" Valid: "+validCPN+" Found: "+availableCPN+" CPNStatus: " + getStatusCPN();
    return s;
  }

  private Repository targetRepository = null;

  private Metadata targetMetadata = null;

  private String synchronizationStatus = null;;

  private String targetCPN = null;

  private String availableCPN = UNKNOWN;

  private Origin lastSynchronization = null;

  private String mode;

  private String statusCPN = null;

  private boolean validCPN = false;

  private Authentication id = null;

  public static String LOCKED = "locked";

  public static String EDIT = "edit";

  public static String SAVED = "saved";

  public static String CPN_SYNCHRONIZED = "cpn-synchronized";
  public static String CPN_TEMPORARY= "cpn-temporary";
  public static String CPN_MISSING = "cpn-missing";
  public static String CPN_READY = "cpn-ready";
  public static String CPN_ERROR = "cpn-error";
  public static String CPN_NEEDS_VERIFICATION = "cpn-needs-verification";

  public static String YES = "yes";

  public static String NO = "no";

  public static String UNKNOWN = "unknown";
}
