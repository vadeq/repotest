package zws.service.securespace;/*
DesignState - Design Compression Technology
@author: athakur
@version: 1.0
Created on February 22, 2004, 2:49 PM
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */

import zws.application.Configurator;
import zws.securespace.User;
import zws.securespace.UserBase;
import zws.securespace.user.*;
import zws.securespace.vade.*;
import zws.security.Password;
//import zws.util.{}//Logwriter;

import java.util.Collection;
import java.util.Iterator;

/*
  public void create(UserSpace uSpace) throws Exception;
  public void remove(UserSpace uSpace) throws Exception;
  public UserSpace findUserSpace(String uSpace) throws Exception;
  public Collection findUserSpaces(String type) throws Exception;
  public Collection findUserSpaces(User user, String type) throws Exception;
  public Collection findUserSpaces(VadeSpace dSpace, String type) throws Exception;
  public Collection findSubSpaces(UserSpace uSpace) throws Exception;
 */

public class DBTest {
  private DBTest() { }
  public static void main(String[] args) {
    try {
      Configurator.load();
      DBTest test = new DBTest();
      test.run();
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  static User u0=createUser(),u1=createUser(),u2=createUser(),u3=createUser(),u4=createUser(),u5=createUser(),u6=createUser(),u7=createUser(),u8=createUser(),u9=createUser(),uA=createUser();
  static Site s0=createSite(),s1=createSite(),s2=createSite();
  static Role r0=createRole(),r1=createRole(),r2=createRole();
  static Group g0=createGroup(),g1=createGroup(),g2=createGroup(),g3=createGroup(),g4=createGroup(),g5=createGroup();
  static VadeSpace ds0=createVadeSpace(),ds1=createVadeSpace(),ds2=createVadeSpace();
  static SecurityProfile p0=createProfile(),p1=createProfile(),p2=createProfile();
  static Application a0=createApplication(),a1=createApplication(),a2=createApplication(),a3=createApplication(),a4=createApplication(),a5=createApplication();

  static Domain d0=createDomain(),d1=createDomain();
  static DriveLocation f0=createDriveLocation(),f1=createDriveLocation(),f2=createDriveLocation();
  static Intralink i0=createIntralink(),i1=createIntralink();
  static Server v0=createServer(), v1=createServer();
  static Workspace w0=createWorkspace(), w1=createWorkspace(), w2=createWorkspace();
  static Password pw0,pw1,pw2,pw3;
  public void run() {
    try {
      pw0=new Password("abc");pw1=new Password("123");pw2=new Password("abcdef");pw3=new Password("abcdefdfsd");
      //p(SecureSpaceSvc.findUsers());
      //p(SecureSpaceSvc.findUserSpaces());
      //p(SecureSpaceSvc.findVadeSpaces());
      {}//Logwriter.printOnConsole("==============================================================");
      //create();
      //register();
      //SecureSpaceSvc.linkSubSpace(s0,s1);
      //SecureSpaceSvc.linkSubSpace(s0,g0);
//      p(SecureSpaceSvc.findUserSpaces());
      //SecureSpaceSvc.linkSubSpace(g2,g3);
      p(SecureSpaceSvc.findRootUserSpaces());
//      p(SecureSpaceSvc.findSubSpaces(s0));
//      p(SecureSpaceSvc.findSubSpaces(s0));
//      p(SecureSpaceSvc.findParentSpaces(s0));
//      p(SecureSpaceSvc.findParentSpaces(g0));
//      p(SecureSpaceSvc.findParentSpaces(g1));
      /*
      SecureSpaceSvc.updatePassword(u0, d0, pw0, pw1);
      SecureSpaceSvc.updatePassword(u1, d0, pw0, pw1);
      SecureSpaceSvc.updatePassword(u2, d0, pw0, pw1);
      SecureSpaceSvc.updatePassword(u3, d1, pw0, pw1);

      SecureSpaceSvc.changePassword(u4, d0, pw0);
      SecureSpaceSvc.changePassword(u5, d1, pw0);
      SecureSpaceSvc.changePassword(u6, d0, pw0);
      SecureSpaceSvc.changePassword(u7, d0, pw0);
      SecureSpaceSvc.changePassword(u8, d1, pw0);
      SecureSpaceSvc.changePassword(u9, d0, pw0);
      SecureSpaceSvc.changePassword(uA, d1, pw0);
      SecureSpaceSvc.changePassword(uA, d0, pw0);
       */
      //     p(SecureSpaceSvc.findUsers(d0));
      //     p(SecureSpaceSvc.findUsers(d1));
      //allow();
      //include();
      /*
      p(SecureSpaceSvc.lookupUserSpaceTypes());
      Iterator i = SecureSpaceSvc.lookupUserSpaceTypes().iterator();
      while (i.hasNext()) p(SecureSpaceSvc.findUserSpaces(i.next().toString()));
      p(SecureSpaceSvc.lookupVadeSpaceTypes());
      i = SecureSpaceSvc.lookupVadeSpaceTypes().iterator();
      while (i.hasNext()) p(SecureSpaceSvc.findVadeSpaces(i.next().toString()));
       **/
      //SecureSpaceSvc.remove(u6);
      //p(SecureSpaceSvc.findUserSpaces(d0));
      //p(SecureSpaceSvc.findUsers(s2));
      //p(SecureSpaceSvc.findVadeSpaces(u0));
      //p(SecureSpaceSvc.findVadeSpaces());
      //p(SecureSpaceSvc.findUserSpaces());

      /*
      p(SecureSpaceSvc.findVadeSpaces(u0));
      p(SecureSpaceSvc.findVadeSpaces(u1));
      p(SecureSpaceSvc.findVadeSpaces(u2));
      p(SecureSpaceSvc.findVadeSpaces(u3));
      p(SecureSpaceSvc.findVadeSpaces(u4));
      p(SecureSpaceSvc.findVadeSpaces(u5));
      p(SecureSpaceSvc.findVadeSpaces(u6));
      p(SecureSpaceSvc.findVadeSpaces(u7));
      p(SecureSpaceSvc.findVadeSpaces(u8));
      p(SecureSpaceSvc.findVadeSpaces(u9));
      p(SecureSpaceSvc.findVadeSpaces(uA));
      /*
      p(SecureSpaceSvc.findVadeSpaces(s0));
      p(SecureSpaceSvc.findVadeSpaces(s1));
      p(SecureSpaceSvc.findVadeSpaces(s2));
      p(SecureSpaceSvc.findVadeSpaces(r0));
      p(SecureSpaceSvc.findVadeSpaces(r1));
      p(SecureSpaceSvc.findVadeSpaces(r2));
      p(SecureSpaceSvc.findVadeSpaces(g0));
      p(SecureSpaceSvc.findVadeSpaces(g1));
      p(SecureSpaceSvc.findVadeSpaces(g2));
      p(SecureSpaceSvc.findVadeSpaces(g3));
      p(SecureSpaceSvc.findVadeSpaces(g4));
      p(SecureSpaceSvc.findVadeSpaces(g5));
      p(SecureSpaceSvc.findUserSpaces(u0));
      p(SecureSpaceSvc.findUserSpaces(uA));
      p(SecureSpaceSvc.findUserSpaces(u0, RoleBase.USPACE_ROLE));
      p(SecureSpaceSvc.findUserSpaces(uA, RoleBase.USPACE_ROLE));
      p(SecureSpaceSvc.findUsers());
      p(SecureSpaceSvc.findUserSpaces());
      p(SecureSpaceSvc.findVadeSpaces());
      p(SecureSpaceSvc.findUsers(s0));
      p(SecureSpaceSvc.findUsers(s1));
      p(SecureSpaceSvc.findUsers(s2));
      p(SecureSpaceSvc.findUsers(r0));
      p(SecureSpaceSvc.findUsers(r1));
      p(SecureSpaceSvc.findUsers(r2));
      p(SecureSpaceSvc.findUsers(d0));
      p(SecureSpaceSvc.findUsers(d1));
      p(SecureSpaceSvc.findUsers(g0));
      p(SecureSpaceSvc.findUsers(g1));
      p(SecureSpaceSvc.findUsers(g2));
      p(SecureSpaceSvc.findUsers(g3));
      p(SecureSpaceSvc.findUsers(g4));
      p(SecureSpaceSvc.findUsers(g5));
       */

      //      SecureSpaceSvc.create(ds0);
      //      SecureSpaceSvc.create(ds1);
      //      SecureSpaceSvc.create(ds2);
    }
    catch (Exception e) { e.printStackTrace(); }
    finally{
      try {
        //destroy();
        {}//Logwriter.printOnConsole("====================================================================");
        //p(SecureSpaceSvc.findUsers());
        //p(SecureSpaceSvc.findUserSpaces());
        //p(SecureSpaceSvc.findVadeSpaces());
      }
      catch (Exception e) { e.printStackTrace(); }
    }
  }

  private static void register() throws Exception {
    SecureSpaceSvc.register(u0, d0, pw0);
    SecureSpaceSvc.register(u1, d0, pw1);
    SecureSpaceSvc.register(u2, d0, pw1);
    SecureSpaceSvc.register(u3, d1, pw2);
    SecureSpaceSvc.register(u4, d0, pw2);
    SecureSpaceSvc.register(u5, d1, pw3);
    SecureSpaceSvc.register(u6, d0, pw3);
    SecureSpaceSvc.register(u7, d0, pw2);
    SecureSpaceSvc.register(u8, d1, pw0);
    SecureSpaceSvc.register(u9, d0, pw2);
    SecureSpaceSvc.register(uA, d1, pw3);
    SecureSpaceSvc.register(uA, d0, pw3);
  }

  private static void allow() throws Exception {
    SecureSpaceSvc.allow(u0,s0);
    SecureSpaceSvc.allow(u1,s0);
    SecureSpaceSvc.allow(u2,s0);
    SecureSpaceSvc.allow(uA,s0);
    SecureSpaceSvc.allow(u3,s1);
    SecureSpaceSvc.allow(u4,s1);
    SecureSpaceSvc.allow(u5,s1);
    SecureSpaceSvc.allow(u9,s1);
    SecureSpaceSvc.allow(uA,s1);
    SecureSpaceSvc.allow(u6,s2);
    SecureSpaceSvc.allow(u7,s2);
    SecureSpaceSvc.allow(u8,s2);
    SecureSpaceSvc.allow(uA,s2);

    SecureSpaceSvc.allow(u0,r0);
    SecureSpaceSvc.allow(u2,r0);
    SecureSpaceSvc.allow(u5,r0);
    SecureSpaceSvc.allow(uA,r0);
    SecureSpaceSvc.allow(u1,r1);
    SecureSpaceSvc.allow(u3,r1);
    SecureSpaceSvc.allow(u4,r1);
    SecureSpaceSvc.allow(uA,r1);
    SecureSpaceSvc.allow(u6,r2);
    SecureSpaceSvc.allow(u7,r2);
    SecureSpaceSvc.allow(u8,r2);
    SecureSpaceSvc.allow(uA,r2);

    SecureSpaceSvc.allow(u0,g0);
    SecureSpaceSvc.allow(u2,g0);
    SecureSpaceSvc.allow(u1,g1);
    SecureSpaceSvc.allow(u3,g1);
    SecureSpaceSvc.allow(u4,g2);
    SecureSpaceSvc.allow(u5,g2);
    SecureSpaceSvc.allow(u6,g3);
    SecureSpaceSvc.allow(u7,g3);
    SecureSpaceSvc.allow(u8,g4);
    SecureSpaceSvc.allow(u9,g4);
    SecureSpaceSvc.allow(uA,g5);
  }

  private static void include() throws Exception {
    SecureSpaceSvc.include(f0,s0);
    SecureSpaceSvc.include(w0,s0);
    SecureSpaceSvc.include(v0,s0);
    SecureSpaceSvc.include(f1,s1);
    SecureSpaceSvc.include(w1,s1);
    SecureSpaceSvc.include(v1,s1);
    SecureSpaceSvc.include(i0,s1);
    SecureSpaceSvc.include(f2,s2);
    SecureSpaceSvc.include(w2,s2);
    SecureSpaceSvc.include(i1,s2);
    SecureSpaceSvc.include(w0,r2);
    SecureSpaceSvc.include(w1,r2);
    SecureSpaceSvc.include(w2,r2);
    SecureSpaceSvc.include(f0,g0);
    SecureSpaceSvc.include(f1,g0);
    SecureSpaceSvc.include(f2,g0);
    SecureSpaceSvc.include(i0,g0);
    SecureSpaceSvc.include(i1,g0);
    SecureSpaceSvc.include(i0,g1);
    SecureSpaceSvc.include(i1,g1);
    SecureSpaceSvc.include(v0,g1);
    SecureSpaceSvc.include(v1,g1);
    SecureSpaceSvc.include(w0,g1);
    SecureSpaceSvc.include(w1,g1);
    SecureSpaceSvc.include(w0,g2);
    SecureSpaceSvc.include(w1,g2);
    SecureSpaceSvc.include(w2,g2);
    SecureSpaceSvc.include(v0,g3);
    SecureSpaceSvc.include(v1,g3);
    SecureSpaceSvc.include(f0,g4);
    SecureSpaceSvc.include(i0,g4);
    SecureSpaceSvc.include(v0,g4);
    SecureSpaceSvc.include(w0,g4);
  }

  private static void create() throws Exception {
    SecureSpaceSvc.create(u0);
    SecureSpaceSvc.create(u1);
    SecureSpaceSvc.create(u2);
    SecureSpaceSvc.create(u3);
    SecureSpaceSvc.create(u4);
    SecureSpaceSvc.create(u5);
    SecureSpaceSvc.create(u6);
    SecureSpaceSvc.create(u7);
    SecureSpaceSvc.create(u8);
    SecureSpaceSvc.create(u9);
    SecureSpaceSvc.create(uA);

    SecureSpaceSvc.create(a0);
    SecureSpaceSvc.create(a1);
    SecureSpaceSvc.create(a2);
    SecureSpaceSvc.create(a3);
    SecureSpaceSvc.create(a4);
    SecureSpaceSvc.create(a5);

    SecureSpaceSvc.create(r0);
    SecureSpaceSvc.create(r1);
    SecureSpaceSvc.create(r2);

    SecureSpaceSvc.create(g0);
    SecureSpaceSvc.create(g1);
    SecureSpaceSvc.create(g2);
    SecureSpaceSvc.create(g3);
    SecureSpaceSvc.create(g4);
    SecureSpaceSvc.create(g5);

    SecureSpaceSvc.create(s0);
    SecureSpaceSvc.create(s1);
    SecureSpaceSvc.create(s2);

    SecureSpaceSvc.create(p0);
    SecureSpaceSvc.create(p1);
    SecureSpaceSvc.create(p2);

    //DATA SPACES
    SecureSpaceSvc.create(d0);
    SecureSpaceSvc.create(d1);

    SecureSpaceSvc.create(i0);
    SecureSpaceSvc.create(i1);


    SecureSpaceSvc.create(v0);
    SecureSpaceSvc.create(v1);

    SecureSpaceSvc.create(w0);
    SecureSpaceSvc.create(w1);
    SecureSpaceSvc.create(w2);

    SecureSpaceSvc.create(f0);
    SecureSpaceSvc.create(f1);
    SecureSpaceSvc.create(f2);
  }

  private static void destroy()  throws Exception {
    SecureSpaceSvc.remove(u0);
    SecureSpaceSvc.remove(u1);
    SecureSpaceSvc.remove(u2);
    SecureSpaceSvc.remove(u3);
    SecureSpaceSvc.remove(u4);
    SecureSpaceSvc.remove(u5);
    SecureSpaceSvc.remove(u6);
    SecureSpaceSvc.remove(u7);
    SecureSpaceSvc.remove(u8);
    SecureSpaceSvc.remove(u9);
    SecureSpaceSvc.remove(uA);

    SecureSpaceSvc.remove(a0);
    SecureSpaceSvc.remove(a1);
    SecureSpaceSvc.remove(a2);
    SecureSpaceSvc.remove(a3);
    SecureSpaceSvc.remove(a4);
    SecureSpaceSvc.remove(a5);

    SecureSpaceSvc.remove(r0);
    SecureSpaceSvc.remove(r1);
    SecureSpaceSvc.remove(r2);

    SecureSpaceSvc.remove(g0);
    SecureSpaceSvc.remove(g1);
    SecureSpaceSvc.remove(g2);
    SecureSpaceSvc.remove(g3);
    SecureSpaceSvc.remove(g4);
    SecureSpaceSvc.remove(g5);

    SecureSpaceSvc.remove(s0);
    SecureSpaceSvc.remove(s1);
    SecureSpaceSvc.remove(s2);

    SecureSpaceSvc.remove(p0);
    SecureSpaceSvc.remove(p1);
    SecureSpaceSvc.remove(p2);

    //DATA SPACES
    SecureSpaceSvc.remove(d0);
    SecureSpaceSvc.remove(d1);

    SecureSpaceSvc.remove(i0);
    SecureSpaceSvc.remove(i1);

    SecureSpaceSvc.remove(v0);
    SecureSpaceSvc.remove(v1);

    SecureSpaceSvc.remove(w0);
    SecureSpaceSvc.remove(w1);
    SecureSpaceSvc.remove(w2);

    SecureSpaceSvc.remove(f0);
    SecureSpaceSvc.remove(f1);
    SecureSpaceSvc.remove(f2);
  }

  private static User createUser() {
    int i = getUserCount();
    return new UserBase("login"+i, "alias"+i, "first"+i, "last"+i,  "email"+i, "phone"+i, true);
  }

  private static VadeSpace createVadeSpace() {
    int i = getVadeSpaceCount();
    VadeSpaceBase d = new VadeSpaceBase();
    d.setName("dSpace"+i);
    d.setDescription("desc"+i);
    return d;
  }

  private static Application createApplication() {
    int i = getApplicationCount();
    ApplicationBase a = new ApplicationBase();
    a.setName("app"+i);
    a.setDescription("desc"+i);
    return a;
  }

  private static Role createRole() {
    int i = getRoleCount();
    RoleBase d = new RoleBase();
    d.setName("role"+i);
    d.setDescription("desc"+i);
    return d;
  }

  private static Group createGroup() {
    int i = getGroupCount();
    GroupBase d = new GroupBase();
    d.setName("group"+i);
    d.setDescription("desc"+i);
    return d;
  }

  private static Site createSite() {
    int i = getSiteCount();
    SiteBase d = new SiteBase();
    d.setName("site"+i);
    d.setDescription("desc"+i);
    return d;
  }

  private static SecurityProfile createProfile() {
    int i = getProfileCount();
    SecurityProfileBase d = new SecurityProfileBase();
    d.setName("profile"+i);
    d.setDescription("desc"+i);
    return d;
  }

  private static Domain createDomain() {
    int i = getDomainCount();
    DomainBase d = new DomainBase();
    d.setName("domain"+i);
    d.setDescription("desc"+i);
    return d;
  }

  private static DriveLocation createDriveLocation() {
    int i = getDriveCount();
    DriveLocationBase d = new DriveLocationBase();
    d.setName("drive"+i);
    d.setDescription("desc"+i);
    d.setServerName("server"+i);
    return d;
  }

  private static Server createServer() {
    int i = getServerCount();
    ServerBase d = new ServerBase();
    d.setName("server"+i);
    d.setDescription("desc"+i);
    return d;
  }

  private static Intralink createIntralink() {
    int i = getIntralinkCount();
    IntralinkBase d = new IntralinkBase();
    d.setName("intralink"+i);
    d.setDescription("desc"+i);
    d.setServerName("server"+i);
    return d;
  }

  private static Workspace createWorkspace() {
    int i = getWorkspaceCount();
    WorkspaceBase d = new WorkspaceBase();
    d.setName("worspace"+i);
    d.setDescription("desc"+i);
    d.setServerName("server"+i);
    d.setLocation("location"+i);
    return d;
  }


  private static void p(Collection c) {
    {}//Logwriter.printOnConsole("_______________________________________"+c.size());
    Iterator i = c.iterator();
    while (i.hasNext()) p(i.next());
    {}//Logwriter.printOnConsole("***************************************"+c.size());
    {}//Logwriter.printOnConsole(" ");
    {}//Logwriter.printOnConsole(" ");
  }

  private static void p(Object o) { {}//Logwriter.printOnConsole(o); }
    
  }

  private static int userCount=0;
  private static int getUserCount() { return userCount++; }
  private static int applicationCount=0;
  private static int getApplicationCount() { return applicationCount++; }
  private static int roleCount=0;
  private static int getRoleCount() { return roleCount++; }
  private static int groupCount=0;
  private static int getGroupCount() { return groupCount++; }
  private static int siteCount=0;
  private static int getSiteCount() { return siteCount++; }
  private static int profileCount=0;
  private static int getProfileCount() { return profileCount++; }

  private static int domainCount=0;
  private static int getDomainCount() { return domainCount++; }
  private static int driveCount=0;
  private static int getDriveCount() { return driveCount++; }
  private static int intralinkCount=0;
  private static int getIntralinkCount() { return intralinkCount++; }
  private static int serverCount=0;
  private static int getServerCount() { return serverCount++; }
  private static int workspaceCount=0;
  private static int getWorkspaceCount() { return workspaceCount++; }

  private static int dataSpaceCount=0;
  private static int getVadeSpaceCount() { return dataSpaceCount++; }

  private void print() throws Exception {
  }
}
