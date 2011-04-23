package zws.service.property; /*
DesignState - Design Compression Technology.
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.application.Properties;
import zws.application.server.property.Names;
import zws.property.*;
import zws.property.enums.*;

import zws.util.Namespace;
import zws.database.DB;
import zws.database.Database;
import zws.exception.*;

import java.sql.*;
import java.util.*;

public class PropertySvc {
/*    
  public void create(zwsListOfProperties props) throws Exception{
   
  }
  
  public void create(zwsProperty prop) throws Exception{
   
  }
  
  public void create(zwsEnumeration enum) throws Exception{
   
  }
  
  
  public void update(zwsListOfProperties props) throws Exception{
   
  }
  
  public void update(zwsProperty prop) throws Exception{
   
  }
  
  public void update(zwsEnumeration enum) throws Exception{
   
  }
  
  public void delete(zwsListOfProperties props) throws Exception{
   
  }
  
  public void delete(zwsProperty prop) throws Exception{
   
  }
  
  public void delete(zwsEnumeration enum) throws Exception{
   
  }
  
  public void findProperty(Namespace namespace, String name) throws Exception{
   
  }
  
  public void findListOfProperties(Namespace namespace) throws Exception{
   
  }
  
  
  public void findListOfProperties(Namespace namespace, String Category) throws Exception{
   
  }
  
  
  public void findListOfProperties(Namespace namespace) throws Exception{
   
  }
  
insert:
    try{
        PreparedStatement s = database().prepareStatement("INSERT into "+SYNCHRONIZATION_TABLE+" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        java.sql.Date d = new java.sql.Date(System.currentTimeMillis());
        s.setString(1,sync.getDomainName0());
        s.setString(2,sync.getServerName0());
        s.setString(3,sync.getDatasourceType0());
        s.setString(4,sync.getDatasourceName0());
        s.setLong(5,sync.getTimeOfCreation0InMillis());
        s.setString(6,sync.getUID0());
        s.setString(7,sync.getName0());
        s.setString(8,sync.getLocationA());
        s.setString(9,sync.getStateA());
        s.setString(10,sync.getDomainNameA());
        s.setString(11,sync.getServerNameA());
        s.setString(12,sync.getDatasourceTypeA());
        s.setString(13,sync.getDatasourceNameA());
        s.setLong(14,sync.getTimeOfCreationAInMillis());
        s.setString(15,sync.getUIDA());
        s.setString(16,sync.getNameA());
        s.setString(17,sync.getLocationA());
        s.setString(18,sync.getStateA());
        database().execute(s);
      }
      catch (SQLException e) {
        if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new Duplicate(sync.toString());
        else throw e;
      }

      
      
update:
s = database().prepareStatement("UPDATE "+SYNCHRONIZATION_TABLE+" SET "+SYNCHRONIZATION_TABLE_uid0+"=?, "+SYNCHRONIZATION_TABLE_name0+"=? WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_name0+"=?)");
s.setString(1,newOrigin.getUniqueID());
s.setString(2,newOrigin.getName());
s.setString(3,origin.getDomainName());
s.setString(4,origin.getServerName());
s.setString(5,origin.getDatasourceName());
s.setString(6,origin.getName());
database().execute(s);

delete:
PreparedStatement s;
s = database().prepareStatement("DELETE FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_uid0+"=? AND "+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_uidA+"=?");
s.setString(1,sync.getDomainName0());
s.setString(2,sync.getServerName0());
s.setString(3,sync.getDatasourceName0());
s.setString(4,sync.getUID0());
s.setString(5,sync.getDomainNameA());
s.setString(6,sync.getServerNameA());
s.setString(7,sync.getDatasourceNameA());
s.setString(8,sync.getUIDA());
database().execute(s);

purge:
      PreparedStatement s = database().prepareStatement("DELETE FROM "+SYNCHRONIZATION_TABLE+" WHERE ("+SYNCHRONIZATION_TABLE_domainName0+"=? AND "+SYNCHRONIZATION_TABLE_serverName0+"=? AND "+SYNCHRONIZATION_TABLE_datasourceName0+"=? AND "+SYNCHRONIZATION_TABLE_uid0+"=?) OR ("+SYNCHRONIZATION_TABLE_domainNameA+"=? AND "+SYNCHRONIZATION_TABLE_serverNameA+"=? AND "+SYNCHRONIZATION_TABLE_datasourceNameA+"=? AND "+SYNCHRONIZATION_TABLE_uidA+"=?)");
      s.setString(1,domainName);
      s.setString(2,serverName);
      s.setString(3,source);
      s.setString(4,uid);
      s.setString(5,domainName);
      s.setString(6,serverName);
      s.setString(7,source);
      s.setString(8,uid);
      database().execute(s);

      
      

find:
    PreparedStatement s = database().prepareStatement("SELECT "+USER_TABLE+".* FROM "+USER_TABLE+" WHERE "+USER_TABLE_username+"=?");
    s.setString(1,username);
    User user = unmarshallUser(database().executeQuery(s));
    if (null==user) throw new NameNotFound(username);
    return user;

find list:
    PreparedStatement s = database().prepareStatement("SELECT "+USER_TABLE+".* FROM "+USER_TABLE);
    Collection users = unmarshallUsers(database().executeQuery(s));
    return users;

order:
    
    s = database().prepareStatement("SELECT "+SYNCHRONIZATION_TABLE_timeOfCreationA+" FROM "+SYNCHRONIZATION_TABLE+" WHERE "+SYNCHRONIZATION_TABLE_name0+"=? OR "+SYNCHRONIZATION_TABLE_nameA+"=? ORDER BY "+SYNCHRONIZATION_TABLE_timeOfCreationA+" DESC LIMIT 1");
    s.setString(1,name);
    s.setString(2,name);
    r = database().executeQuery(s);
    
    

  public static void create(User u) throws Exception {
    try{
      PreparedStatement s = database().prepareStatement("INSERT into "+USER_TABLE+" VALUES(?,?,?,?,?,?,?)");
      s.setString(1,u.getUsername());
      s.setString(2,u.getAlias());
      s.setString(3,u.getFirstName());
      s.setString(4,u.getLastName());
      s.setString(5,u.getEmail());
      s.setString(6,u.getPhone());
      s.setBoolean(7,u.getIsActive());
      database().execute(s);
    }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new DuplicateName(u.getUsername());
      else throw e;
    }
    create(u.getUserSpace());
    allow(u, u.getUserSpace());
  }
  
  public static void remove(User u) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("DELETE FROM "+USER_TABLE+" WHERE "+USER_TABLE_username+"=?");
    s.setString(1,u.getUsername());
    database().execute(s);
    removeUserSpace(u.getSpace());
  }
  
  public static User findUser(String username) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+USER_TABLE+".* FROM "+USER_TABLE+" WHERE "+USER_TABLE_username+"=?");
    s.setString(1,username);
    User user = unmarshallUser(database().executeQuery(s));
    if (null==user) throw new NameNotFound(username);
    return user;
  }

  public static Collection findUsers() throws Exception { 
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+USER_TABLE+".* FROM "+USER_TABLE);
    Collection users = unmarshallUsers(database().executeQuery(s));
    return users;
  }
  
  public static Collection findUsers(UserSpace uSpace) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_TABLE+".* FROM "+SECURE_USER_SPACE_TABLE+","+USER_TABLE+" where "+SECURE_USER_SPACE_TABLE_uSpace+"=? AND "+SECURE_USER_SPACE_TABLE_username+"="+USER_TABLE_username);
    s.setString(1, uSpace.getSpace());
    Collection users = unmarshallUsers(database().executeQuery(s));
    return users;
  }

  public static Collection findUsers(VadeSpace vSpace) throws Exception { 
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_TABLE+".* FROM "+SECURE_VADE_SPACE_TABLE+","+SECURE_USER_SPACE_TABLE+","+USER_TABLE+" where "+SECURE_VADE_SPACE_TABLE_vSpace+"=? AND "+SECURE_VADE_SPACE_TABLE_uSpace+"="+SECURE_USER_SPACE_TABLE_uSpace+" AND "+SECURE_USER_SPACE_TABLE_username+"="+USER_TABLE_username);
    s.setString(1, vSpace.getSpace());
    Collection users = unmarshallUsers(database().executeQuery(s));
    return users;
  }
  
  public static void activate(User u) throws Exception { activate(u.getUsername()); }
  public static void activate(String username) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("UPDATE USER SET active=1 where "+USER_TABLE_username+"=?");
    s.setString(1,username);
    database().execute(s);
  }

  public static void deactivate(User u) throws Exception { deactivate(u.getUsername()); }
  public static void deactivate(String username) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("UPDATE USER SET active=0 where "+USER_TABLE_username+"=?");
    s.setString(1,username);
    database().execute(s);
  }
    
  public static Collection lookupUserSpaceTypes()  throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+USER_SPACE_TYPE_TABLE+".* FROM "+USER_SPACE_TYPE_TABLE);
    ResultSet r = database().executeQuery(s);
    Collection spaces = new Vector();
    while (r.next()) spaces.add(r.getString(USER_SPACE_TYPE_TABLE_type));
    return spaces;
  }
  
  public static void create(UserSpace uSpace) throws Exception {
    try {
      database().insertText(USER_SPACE_TABLE, uSpace.getSpace(), uSpace.getDescription(), uSpace.getType(), uSpace.getClass().getName());
    }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new DuplicateName(uSpace.getName(), uSpace.getNamespace().toString());
      else throw e;
    }
    logUserSpaceType(uSpace);
  }

  private static void logUserSpaceType(UserSpace uSpace) throws Exception {
      //Connection c = database().connect();
      PreparedStatement s = database().prepareStatement("INSERT IGNORE into "+USER_SPACE_TYPE_TABLE+" VALUES(?)");
      s.setString(1,uSpace.getType());
      database().execute(s);
  }

  public static void remove(UserSpace uSpace) throws Exception { removeUserSpace(uSpace.getSpace()); }
  public static void removeUserSpace(String uSpace) throws DuplicateName, Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("Delete from "+USER_SPACE_TABLE+" where "+USER_SPACE_TABLE_uSpace+"=?");
    s.setString(1,uSpace);
    database().execute(s);
  }

  public static UserSpace findUserSpace(String uSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+USER_SPACE_TABLE+".* FROM "+USER_SPACE_TABLE+" WHERE "+USER_SPACE_TABLE_uSpace+"=?");
    s.setString(1,uSpace);
    UserSpace space = unmarshallUserSpace(database().executeQuery(s));
    if (null==space) throw new NameNotFound(uSpace);
    return space;
  }

  private static Map subUserSpaces() throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_SPACE_FAMILY_TABLE+".parent FROM "+USER_SPACE_FAMILY_TABLE);
    ResultSet r = database().executeQuery(s);
    Map excludeList = new HashMap();
    while (r.next()) excludeList.put(r.getString(USER_SPACE_FAMILY_TABLE_parent), null);
    return excludeList;
  }

  
  public static Collection findRootUserSpaces() throws Exception {
    return unmarshallRootUserSpaces(database().executeQuery(getUserSpacesStatement()), subUserSpaces());
  }
  public static Collection findUserSpaces() throws Exception {
    return unmarshallUserSpaces(database().executeQuery(getUserSpacesStatement()));
  }  
  public static PreparedStatement getUserSpacesStatement() throws Exception {
    //Connection c = database().connect();
    return database().prepareStatement("SELECT "+USER_SPACE_TABLE+".* FROM "+USER_SPACE_TABLE);
  }  

  
  public static Collection findRootUserSpaces(String type) throws Exception {
    return unmarshallRootUserSpaces(database().executeQuery(getUserSpacesStatement(type)), subUserSpaces());
  }
  public static Collection findUserSpaces(String type) throws Exception {
    return unmarshallUserSpaces(database().executeQuery(getUserSpacesStatement(type)));
  }
  public static PreparedStatement getUserSpacesStatement(String type) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+USER_SPACE_TABLE+".* FROM "+USER_SPACE_TABLE+" WHERE "+USER_SPACE_TABLE_type+"=?");
    s.setString(1,type);
    return s;
  }

  
  public static Collection findRootUserSpaces(User user) throws Exception {
    return unmarshallRootUserSpaces(database().executeQuery(getUserSpacesStatement(user)), subUserSpaces());
  }
  public static Collection findUserSpaces(User user) throws Exception { 
    return unmarshallUserSpaces(database().executeQuery(getUserSpacesStatement(user)));
  }
  public static PreparedStatement getUserSpacesStatement(User user) throws Exception { 
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_SPACE_TABLE+".* FROM "+SECURE_USER_SPACE_TABLE+","+USER_SPACE_TABLE+" where "+SECURE_USER_SPACE_TABLE_username+"=? AND "+SECURE_USER_SPACE_TABLE_uSpace+"="+USER_SPACE_TABLE_uSpace);
    s.setString(1, user.getUsername());
    return s;
  }

  
  public static Collection findRootUserSpaces(User user, String type) throws Exception {
    return unmarshallRootUserSpaces(database().executeQuery(getUserSpacesStatement(user, type)), subUserSpaces());
  }
  public static Collection findUserSpaces(User user, String type) throws Exception { 
    return unmarshallUserSpaces(database().executeQuery(getUserSpacesStatement(user, type)));
  }
  public static PreparedStatement getUserSpacesStatement(User user, String type) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_SPACE_TABLE+".* FROM "+SECURE_USER_SPACE_TABLE+","+USER_SPACE_TABLE+" where "+SECURE_USER_SPACE_TABLE_username+"=? AND "+SECURE_USER_SPACE_TABLE_uSpace+"="+USER_SPACE_TABLE_uSpace+" AND "+USER_SPACE_TABLE_type+"=?");
    s.setString(1, user.getUsername());
    s.setString(2, type);
    return s;
  }

  
  public static Collection findRootUserSpaces(VadeSpace vSpace) throws Exception {
    return unmarshallRootUserSpaces(database().executeQuery(getUserSpacesStatement(vSpace)), subUserSpaces());
  }
  public static Collection findUserSpaces(VadeSpace vSpace) throws Exception { 
    return unmarshallUserSpaces(database().executeQuery(getUserSpacesStatement(vSpace)));
  }
  public static PreparedStatement getUserSpacesStatement(VadeSpace vSpace) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_SPACE_TABLE+".* FROM "+SECURE_VADE_SPACE_TABLE+","+USER_SPACE_TABLE+" where "+SECURE_VADE_SPACE_TABLE_vSpace+"=? AND "+SECURE_VADE_SPACE_TABLE_uSpace+"="+USER_SPACE_TABLE_uSpace);
    s.setString(1, vSpace.getSpace());
    return s;
  }

  
  public static Collection findRootUserSpaces(VadeSpace vSpace, String type) throws Exception {
    return unmarshallRootUserSpaces(database().executeQuery(getUserSpacesStatement(vSpace, type)), subUserSpaces());
  }
  public static Collection findUserSpaces(VadeSpace vSpace, String type) throws Exception { 
    return unmarshallUserSpaces(database().executeQuery(getUserSpacesStatement(vSpace, type)));
  }
  public static PreparedStatement getUserSpacesStatement(VadeSpace vSpace, String type) throws Exception { 
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_SPACE_TABLE+".* FROM "+SECURE_VADE_SPACE_TABLE+","+USER_SPACE_TABLE+" where "+SECURE_VADE_SPACE_TABLE_vSpace+"=? AND "+SECURE_VADE_SPACE_TABLE_uSpace+"="+USER_SPACE_TABLE_uSpace+" AND "+USER_SPACE_TABLE_type+"=?");
    s.setString(1, vSpace.getSpace());
    s.setString(2, type);
    return s;
  }

  public static Collection findSubSpaces(UserSpace uSpace) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+USER_SPACE_TABLE+".* FROM "+USER_SPACE_FAMILY_TABLE+","+USER_SPACE_TABLE+" where "+USER_SPACE_FAMILY_TABLE_parent+"=? AND "+USER_SPACE_FAMILY_TABLE_child+"="+USER_SPACE_TABLE_uSpace);
    s.setString(1, uSpace.getSpace());
    Collection users = unmarshallUserSpaces(database().executeQuery(s));
    return users;
  }
  
  public static Collection findParentSpaces(UserSpace uSpace) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+USER_SPACE_TABLE+".* FROM "+USER_SPACE_FAMILY_TABLE+","+USER_SPACE_TABLE+" where "+USER_SPACE_FAMILY_TABLE_child+"=? AND "+USER_SPACE_FAMILY_TABLE_parent+"="+USER_SPACE_TABLE_uSpace);
    s.setString(1, uSpace.getSpace());
    Collection users = unmarshallUserSpaces(database().executeQuery(s));
    return users;
  }
  
  public static void linkSubSpace(UserSpace parent, UserSpace child) throws Exception { linkSubSpace(parent.getSpace(), child.getSpace()); }
  public static void linkSubSpace(String uParentSpace, String uChildSpace) throws DuplicateName, CircularDependency, Exception {
    if (createsUserSpaceCircle(uParentSpace, uChildSpace)) throw new CircularDependency(uParentSpace, uChildSpace);
    try { database().insertText(USER_SPACE_FAMILY_TABLE, uParentSpace, uChildSpace); }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Cannot add or update a child row: a foreign key constraint fails"))
        throw new RequiresDependency("parent: " + uParentSpace + " or child: " + uChildSpace + " does not exist");
      else if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new Duplicate("Link between" + uParentSpace + " and " + uChildSpace + " already exists.");
      else throw e;
    }
  }

  public static void unlinkSubSpace(UserSpace parent, UserSpace child) throws Exception { unlinkSubSpace(parent.getSpace(), child.getSpace()); }
  public static void unlinkSubSpace(String uParentSpace, String uChildSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("DELETE from "+USER_SPACE_FAMILY_TABLE+" where "+USER_SPACE_FAMILY_TABLE_parent+"=? AND "+USER_SPACE_FAMILY_TABLE_child+"=?");
    s.setString(1,uParentSpace);
    s.setString(2,uChildSpace);
    database().execute(s);
  }

  public static boolean createsUserSpaceCircle(UserSpace parent, UserSpace child) { return createsUserSpaceCircle(parent.getSpace(), child.getSpace()); }
  public static boolean createsUserSpaceCircle(String uParentSpace, String uChildSpace) {
    return false; //+++ +++ todo !!!!
  }
  
  public static Collection lookupVadeSpaceTypes()  throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+VADE_SPACE_TYPE_TABLE+".* FROM "+VADE_SPACE_TYPE_TABLE);
    ResultSet r = database().executeQuery(s);
    Collection spaces = new Vector();
    while (r.next()) spaces.add(r.getString(VADE_SPACE_TYPE_TABLE_type));
    return spaces;
  }
  
  public static void create(VadeSpace vSpace) throws DuplicateName, Exception {
    try {
      //Connection c = database().connect();
      PreparedStatement s = database().prepareStatement("INSERT into "+VADE_SPACE_TABLE+" VALUES(?,?,?,?,?,?)");
      s.setString(1,vSpace.getSpace());
      s.setString(2,vSpace.getDescription());
      s.setString(3,vSpace.getType());
      s.setString(4,vSpace.getClass().getName());
      s.setBoolean(5,vSpace.getPasswordProtected());
      s.setBoolean(6,vSpace.getIsActive());
      database().execute(s);
    }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new DuplicateName(vSpace.getName(), vSpace.getNamespace().toString());
      else throw e;
    }
    logVadeSpaceType(vSpace);
  }
  
  private static void logVadeSpaceType(VadeSpace vSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("INSERT IGNORE into "+VADE_SPACE_TYPE_TABLE+" VALUES(?)");
    s.setString(1,vSpace.getType());
    database().execute(s);
  }
  public static void remove(VadeSpace vSpace) throws Exception { removeVadeSpace(vSpace.getSpace()); }
  public static void removeVadeSpace(String vSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("DELETE from "+VADE_SPACE_TABLE+" where vSpace=?");
    s.setString(1,vSpace);
    database().execute(s);
  }


  private static Map subVadeSpaces() throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+VADE_SPACE_FAMILY_TABLE+".parent FROM "+VADE_SPACE_FAMILY_TABLE);
    ResultSet r = database().executeQuery(s);
    Map excludeList = new HashMap();
    while (r.next()) excludeList.put(r.getString(VADE_SPACE_FAMILY_TABLE_parent), null);
    return excludeList;
  }

  public static VadeSpace findVadeSpace(String vSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+VADE_SPACE_TABLE+".* FROM "+VADE_SPACE_TABLE+" WHERE "+VADE_SPACE_TABLE_vSpace+"=?");
    s.setString(1,vSpace);
    VadeSpace space = unmarshallVadeSpace(database().executeQuery(s));
    if (null==space) throw new NameNotFound(vSpace);
    return space;
  }
  
  public static Collection findRootVadeSpaces() throws Exception { 
    return unmarshallRootVadeSpaces(database().executeQuery(getVadeSpacesStatement()), subVadeSpaces());
  } 
  public static Collection findVadeSpaces() throws Exception { 
    return unmarshallVadeSpaces(database().executeQuery(getVadeSpacesStatement()));
  } 
  public static PreparedStatement getVadeSpacesStatement() throws Exception {
    //Connection c = database().connect();
    return database().prepareStatement("SELECT "+VADE_SPACE_TABLE+".* FROM "+VADE_SPACE_TABLE);
  }
  
  public static Collection findRootVadeSpaces(String type) throws Exception { 
    return unmarshallRootVadeSpaces(database().executeQuery(getVadeSpacesStatement(type)), subVadeSpaces());
  } 
  public static Collection findVadeSpaces(String type) throws Exception { 
    return unmarshallVadeSpaces(database().executeQuery(getVadeSpacesStatement(type)));
  } 
  public static PreparedStatement getVadeSpacesStatement(String type) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+VADE_SPACE_TABLE+".* FROM "+VADE_SPACE_TABLE+" WHERE "+VADE_SPACE_TABLE_type+"=?");
    s.setString(1,type);
    return s;
  }

  public static Collection findRootVadeSpaces(User user) throws Exception { 
    return unmarshallRootVadeSpaces(database().executeQuery(getVadeSpacesStatement(user)), subVadeSpaces());
  } 
  public static Collection findVadeSpaces(User user) throws Exception { 
    return unmarshallVadeSpaces(database().executeQuery(getVadeSpacesStatement(user)));
  } 
  public static PreparedStatement getVadeSpacesStatement(User user) throws Exception { 
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+VADE_SPACE_TABLE+".* FROM "+SECURE_VADE_SPACE_TABLE+","+VADE_SPACE_TABLE+","+SECURE_USER_SPACE_TABLE+" where "+SECURE_USER_SPACE_TABLE_username+"=? AND "+SECURE_USER_SPACE_TABLE_uSpace+"="+SECURE_VADE_SPACE_TABLE_uSpace + " AND "+SECURE_VADE_SPACE_TABLE_vSpace+"="+VADE_SPACE_TABLE_vSpace);
    s.setString(1, user.getUsername());
    return s;
  }

  public static Collection findRootVadeSpaces(User user, String type) throws Exception { 
    return unmarshallRootVadeSpaces(database().executeQuery(getVadeSpacesStatement(user, type)), subVadeSpaces());
  } 
  public static Collection findVadeSpaces(User user, String type) throws Exception { 
    return unmarshallVadeSpaces(database().executeQuery(getVadeSpacesStatement(user, type)));
  } 
  public static PreparedStatement getVadeSpacesStatement(User user, String type) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+VADE_SPACE_TABLE+".* FROM "+SECURE_VADE_SPACE_TABLE+","+VADE_SPACE_TABLE+","+SECURE_USER_SPACE_TABLE+" where "+SECURE_USER_SPACE_TABLE_username+"=? AND "+SECURE_USER_SPACE_TABLE_uSpace+"="+SECURE_VADE_SPACE_TABLE_uSpace + " AND "+SECURE_VADE_SPACE_TABLE_vSpace+"="+VADE_SPACE_TABLE_vSpace+" AND "+VADE_SPACE_TABLE_type+"=?");
    s.setString(1, user.getUsername());
    s.setString(2, type);
    return s;
  }

  public static Collection findRootVadeSpaces(UserSpace uSpace) throws Exception { 
    return unmarshallRootVadeSpaces(database().executeQuery(getVadeSpacesStatement(uSpace)), subVadeSpaces());
  } 
  public static Collection findVadeSpaces(UserSpace uSpace) throws Exception { 
    return unmarshallVadeSpaces(database().executeQuery(getVadeSpacesStatement(uSpace)));
  } 
  public static PreparedStatement getVadeSpacesStatement(UserSpace uSpace) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+VADE_SPACE_TABLE+".* FROM "+SECURE_VADE_SPACE_TABLE+","+VADE_SPACE_TABLE+" where "+SECURE_VADE_SPACE_TABLE_uSpace+"=? AND "+SECURE_VADE_SPACE_TABLE_vSpace+"="+VADE_SPACE_TABLE_vSpace);
    s.setString(1, uSpace.getSpace());
    return s;
  }

  public static Collection findRootVadeSpaces(UserSpace uSpace, String type) throws Exception { 
    return unmarshallRootVadeSpaces(database().executeQuery(getVadeSpacesStatement(uSpace, type)), subVadeSpaces());
  } 
  public static Collection findVadeSpaces(UserSpace uSpace, String type) throws Exception { 
    return unmarshallVadeSpaces(database().executeQuery(getVadeSpacesStatement(uSpace, type)));
  } 
  public static PreparedStatement getVadeSpacesStatement(UserSpace uSpace, String type) throws Exception { 
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+VADE_SPACE_TABLE+".* FROM "+SECURE_VADE_SPACE_TABLE+","+VADE_SPACE_TABLE+" where "+SECURE_VADE_SPACE_TABLE_uSpace+"=? AND "+SECURE_VADE_SPACE_TABLE_vSpace+"="+VADE_SPACE_TABLE_vSpace+" AND "+VADE_SPACE_TABLE_type+"=?");
    s.setString(1, uSpace.getSpace());
    s.setString(2, type);
    return s;
  }
  
  public static Collection findSubVades(VadeSpace vSpace) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT DISTINCT "+VADE_SPACE_TABLE+".* FROM "+VADE_SPACE_FAMILY_TABLE+","+VADE_SPACE_TABLE+" where "+VADE_SPACE_FAMILY_TABLE_parent+"=? AND "+VADE_SPACE_FAMILY_TABLE_child+"="+VADE_SPACE_TABLE_vSpace);
    s.setString(1, vSpace.getSpace());
    Collection users = unmarshallUserSpaces(database().executeQuery(s));
    return users;
  }
  
  public static Collection findParentVades(VadeSpace vSpace) throws Exception { 
    //does not return users associated withsubspaces
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+VADE_SPACE_TABLE+".* FROM "+VADE_SPACE_FAMILY_TABLE+","+VADE_SPACE_TABLE+" where "+VADE_SPACE_FAMILY_TABLE_child+"=? AND "+VADE_SPACE_FAMILY_TABLE_parent+"="+VADE_SPACE_TABLE_vSpace);
    s.setString(1, vSpace.getSpace());
    Collection users = unmarshallUserSpaces(database().executeQuery(s));
    return users;
  }
  
  public static void linkSubVade(VadeSpace parent, VadeSpace child) throws Exception { linkSubSpace(parent.getSpace(), child.getSpace()); }
  public static void linkSubVade(String vParentSpace, String vChildSpace) throws DuplicateName, CircularDependency, Exception {
    if (createsVadeSpaceCircle(vParentSpace, vChildSpace)) throw new CircularDependency(vParentSpace, vChildSpace);
    try { database().insertText(VADE_SPACE_FAMILY_TABLE, vParentSpace, vChildSpace); }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Cannot add or update a child row: a foreign key constraint fails"))
        throw new RequiresDependency("parent: " + vParentSpace + " or child: " + vChildSpace + " does not exist");
      else if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new Duplicate("Link between" + vParentSpace + " and " + vChildSpace + " already exists.");
      else throw e;
    }
  }

  public static void unlinkSubVade(VadeSpace parent, VadeSpace child) throws Exception { unlinkSubVade(parent.getSpace(), child.getSpace()); }
  public static void unlinkSubVade(String vParentSpace, String vChildSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("DELETE from "+VADE_SPACE_FAMILY_TABLE+" where "+VADE_SPACE_FAMILY_TABLE_parent+"=? AND "+VADE_SPACE_FAMILY_TABLE_child+"=?");
    s.setString(1,vParentSpace);
    s.setString(2,vChildSpace);
    database().execute(s);
  }

  public static boolean createsVadeSpaceCircle(VadeSpace parent, VadeSpace child) { return createsVadeSpaceCircle(parent.getSpace(), child.getSpace()); }
  public static boolean createsVadeSpaceCircle(String vParentSpace, String vChildSpace) {
    return false; //+++ +++ todo !!!!
  }
  
  
  public static void allow(User user, UserSpace uSpace) throws Exception { allow(user.getUsername(), uSpace.getSpace()); }
  public static void allow(String username, String uSpace) throws Exception {
    try { database().insertText(SECURE_USER_SPACE_TABLE, username, uSpace); }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Cannot add or update a child row: a foreign key constraint fails"))
        throw new RequiresDependency("User: " + username + " or User Space: " + uSpace + " does not exist");
      else if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new Duplicate(username + " is already allowed in " + uSpace);
      else throw e;
    }
  }
  
  public static void include(VadeSpace vSpace, User user) throws Exception { includeVadeSpace(vSpace.getSpace(), user.getUserSpace().getSpace()); }
  public static void include(VadeSpace vSpace, UserSpace uSpace) throws Exception { includeVadeSpace(vSpace.getSpace(), uSpace.getSpace()); }
  public static void includeVadeSpace(String vSpace, String uSpace) throws Exception {
    try { database().insertText(SECURE_VADE_SPACE_TABLE, vSpace, uSpace); }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Cannot add or update a child row: a foreign key constraint fails"))
        throw new RequiresDependency ("Data: " + vSpace + " or User Space: " + uSpace + " does not exist");
      else if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new Duplicate(vSpace + " is already included in " + uSpace);
      else throw e;
    }
  }
  
  public static void register(UserSpace uSpace, VadeSpace vSpace, Authentication authentication) throws Exception {
    setAuthentication(uSpace, vSpace, authentication);
  }
  
  public static void setAuthentication(UserSpace uSpace, VadeSpace vSpace, Authentication authentication) throws Exception {
    setAuthentication(uSpace.getSpace(), vSpace.getSpace(), authentication);
  }
  public static void setAuthentication(String uSpace, String vSpace, Authentication authentication) throws Exception {
    try { database().insertText(PASSWORD_TABLE, vSpace, uSpace, authentication.getUsername(), authentication.getPassword()); }
    catch (SQLException e) {
      if (-1 != e.getMessage().indexOf("Cannot add or update a child row: a foreign key constraint fails"))
        throw new RequiresDependency ("Data Space: " + vSpace+" has not been included into User Space: " + uSpace);
      else if (-1 != e.getMessage().indexOf("Duplicate entry")) throw new Duplicate("Username and password for " + uSpace + " to access " + vSpace + " is already defined");
      else throw e;
    }
  }

  public static void changeAuthentication(UserSpace uSpace, VadeSpace vSpace, Authentication authentication) throws Exception { 
    changeAuthentication(uSpace.getSpace(), vSpace.getSpace(), authentication); 
  }
  public static void changeAuthentication(String uSpace, String vSpace, Authentication authentication) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("UPDATE "+PASSWORD_TABLE+" SET "+PASSWORD_TABLE_username+"=?,"+PASSWORD_TABLE_password+"=? where "+PASSWORD_TABLE_vSpace+"=? AND "+PASSWORD_TABLE_uSpace+"=?");
    s.setString(1,authentication.getUsername());
    s.setString(2,authentication.getPassword());
    s.setString(3,vSpace);
    s.setString(4,uSpace);
    database().execute(s);
  }

  public static void updateAuthentication(UserSpace uSpace, VadeSpace vSpace, Authentication old, Authentication authentication) throws Exception {
    updateAuthentication(uSpace.getSpace(), vSpace.getSpace(), old, authentication);
  }
  public static void updateAuthentication(String uSpace, String vSpace, Authentication old, Authentication authentication) throws Exception {
    Authentication current = findAuthentication(uSpace,vSpace);
    if (!current.getPassword().equals(old.getPassword())) throw new BadPassword(old.getUsername());
    changeAuthentication(uSpace, vSpace, authentication);
  }

  public static String findRegisteredUsername(UserSpace uSpace, VadeSpace vSpace) throws Exception {
    return findRegisteredUsername(uSpace.getSpace(), vSpace.getSpace()); 
  }
  public static String findRegisteredUsername(String uSpace, String vSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT " + PASSWORD_TABLE_username + " from " + PASSWORD_TABLE + " where "+PASSWORD_TABLE_vSpace+"=? AND "+PASSWORD_TABLE_uSpace+"=?");
    s.setString(1,vSpace);
    s.setString(2,uSpace);
    ResultSet r = database().executeQuery(s);
    if (!r.next()) throw new NameNotFound("No username found for " + uSpace + " access to "+ vSpace);
    return r.getString(PASSWORD_TABLE_username);
  }
  
  public static Authentication findAuthentication(UserSpace uSpace, VadeSpace vSpace) throws Exception { 
    return findAuthentication(uSpace.getSpace(), vSpace.getSpace()); 
  }
  private static Authentication findAuthentication(String uSpace, String vSpace) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+PASSWORD_TABLE_username+","+PASSWORD_TABLE_password+" from "+PASSWORD_TABLE+" where "+PASSWORD_TABLE_vSpace+"=? AND "+PASSWORD_TABLE_uSpace+"=?");
    s.setString(1,vSpace);
    s.setString(2,uSpace);
    ResultSet r = database().executeQuery(s);
    if (!r.next()) throw new NameNotFound("No password found for " + uSpace + " access to "+ vSpace);
    return new Authentication(r.getString(PASSWORD_TABLE_username), r.getString(PASSWORD_TABLE_password));
  }
  
  private static void authenticate(UserSpace uSpace, VadeSpace vSpace, Authentication authentication) throws Exception { 
    authenticate(uSpace, vSpace, authentication); 
  }
  private static void authenticate (String uSpace, String vSpace, Authentication authentication) throws Exception {
    //Connection c = database().connect();
    PreparedStatement s = database().prepareStatement("SELECT "+PASSWORD_TABLE_username+","+PASSWORD_TABLE_password+" from "+PASSWORD_TABLE+" where "+PASSWORD_TABLE_vSpace+"=? AND "+PASSWORD_TABLE_uSpace+"=?");
    s.setString(1,vSpace);
    s.setString(2,uSpace);
    ResultSet r = database().executeQuery(s);
    if (!r.next()) throw new NameNotFound("No username password found for " + uSpace + " access to "+ vSpace);
    if (authentication.getUsername().equals(r.getString(PASSWORD_TABLE_password)) &&  authentication.getPassword().equals(r.getString(PASSWORD_TABLE_password))) return;
    throw new FailedToAuthenticate(uSpace, vSpace, authentication.getUsername());
  }
  
  private static Collection unmarshallUsers(ResultSet r) throws Exception {
    Collection c = new Vector();
    User user;
    do { 
      user = unmarshallUser(r);
      if (null!=user) c.add(user);
    }
    while (null!=user);
    return c;
  }
  
  private static Collection unmarshallRootUserSpaces(ResultSet r, Map excludeList) throws SQLException, CanNotMaterialize {
    Collection c = new Vector();
    UserSpace uSpace;
    while (r.next()) {
      uSpace = unmarshallRootUserSpace(r, excludeList);
      if (null!=uSpace) c.add(uSpace);
    }
    return c;
  }

  private static Collection unmarshallUserSpaces(ResultSet r) throws SQLException, CanNotMaterialize {
    Collection c = new Vector();
    UserSpace uSpace;
    do {
      uSpace = unmarshallUserSpace(r);
      if (null!=uSpace) c.add(uSpace);
    }
    while (null!=uSpace);
    return c;
  }

  private static Collection unmarshallRootVadeSpaces(ResultSet r, Map excludeList) throws SQLException, CanNotMaterialize {
    Collection c = new Vector();
    VadeSpace vSpace;
    while (r.next()) {
      vSpace = unmarshallRootVadeSpace(r, excludeList);
      if (null!=vSpace) c.add(vSpace);
    }
    return c;
  }

  private static Collection unmarshallVadeSpaces(ResultSet r) throws SQLException, CanNotMaterialize {
    Collection c = new Vector();
    VadeSpace vSpace;
    do { 
      vSpace = unmarshallVadeSpace(r);
      if (null!=vSpace) c.add(vSpace);
    }
    while (null!=vSpace);
    return c;
  }

  private static User unmarshallUser(ResultSet r) throws SQLException {
    UserBase user = new UserBase();
    user.setUsername(r.getString(USER_TABLE_username));
    user.setAlias(r.getString(USER_TABLE_alias));
    user.setFirstName(r.getString(USER_TABLE_first));
    user.setLastName(r.getString(USER_TABLE_last));
    user.setEmail(r.getString(USER_TABLE_email));
    user.setPhone(r.getString(USER_TABLE_phone));
    user.setIsActive(r.getBoolean(USER_TABLE_active));
    return user;
  }
  
  private static UserSpace unmarshallRootUserSpace(ResultSet r, Map excludeList) throws SQLException, CanNotMaterialize {
    if (r.getString(USER_SPACE_TABLE_type).equals("user")) return null;
    if (excludeList.containsKey(r.getString(USER_SPACE_TABLE_uSpace))) return null;
    UserSpaceBase uSpace = (UserSpaceBase)UserSpaceBase.materialize (r.getString(USER_SPACE_TABLE_uSpace), r.getString(USER_SPACE_TABLE_note), r.getString(USER_SPACE_TABLE_fqcn));
    uSpace.setDescription(r.getString(USER_SPACE_TABLE_note));
    return uSpace;
  }

  private static UserSpace unmarshallUserSpace(ResultSet r) throws SQLException, CanNotMaterialize {
    if (!r.next()) return null;
    UserSpaceBase uSpace = (UserSpaceBase)UserSpaceBase.materialize (r.getString(USER_SPACE_TABLE_uSpace), r.getString(USER_SPACE_TABLE_note), r.getString(USER_SPACE_TABLE_fqcn));
    uSpace.setDescription(r.getString(USER_SPACE_TABLE_note));
    return uSpace;
  }
  
  private static VadeSpace unmarshallRootVadeSpace(ResultSet r, Map excludeList) throws SQLException, CanNotMaterialize {
    if (excludeList.containsKey(r.getString(VADE_SPACE_TABLE_vSpace))) return null;
    VadeSpaceBase vSpace = (VadeSpaceBase)VadeSpaceBase.materialize(r.getString(USER_SPACE_TABLE_uSpace), r.getString(VADE_SPACE_TABLE_note), r.getBoolean(VADE_SPACE_TABLE_password), r.getBoolean(VADE_SPACE_TABLE_active), r.getString(USER_SPACE_TABLE_fqcn));
    vSpace.setDescription(r.getString(USER_SPACE_TABLE_note));
    return vSpace;
  }

  private static VadeSpace unmarshallVadeSpace(ResultSet r) throws SQLException, CanNotMaterialize {
    if (!r.next()) return null;
    VadeSpaceBase vSpace = (VadeSpaceBase)VadeSpaceBase.materialize(r.getString(VADE_SPACE_TABLE_vSpace), r.getString(VADE_SPACE_TABLE_note), r.getBoolean(VADE_SPACE_TABLE_password), r.getBoolean(VADE_SPACE_TABLE_active), r.getString(VADE_SPACE_TABLE_fqcn));
    return vSpace;
  }
  
  private static Database database()  throws Exception { return DB.source(datasourceName()); }
  private static String datasourceName() { return Properties.get(Names.PROPERTY_DATABASE); }
  
  private static String NAMESPACE_TABLE = "namespace";
  private static String CATEGORY_TABLE = "category";
  private static String PROPERTY_TABLE = "property";
  private static String ENUM_TABLE = "enumeration";
  
  private static String NAMESPACE_namespace= NAMESPACE_TABLE+".namespace";
  
  private static String CATEGORY_namespace= CATEGORY_TABLE+".namespace";
  private static String CATEGORY_category= CATEGORY_TABLE+".category";
  private static String CATEGORY_spot= CATEGORY_TABLE+".spot";

  private static String PROPERTY_namespace= PROPERTY_TABLE+".namespace";
  private static String PROPERTY_category= PROPERTY_TABLE+".category";
  private static String PROPERTY_name= PROPERTY_TABLE+".name";
  private static String PROPERTY_value= PROPERTY_TABLE+".value";
  private static String PROPERTY_primitive= PROPERTY_TABLE+".primitive";
  private static String PROPERTY_spot= PROPERTY_TABLE+".spot";
  private static String PROPERTY_enumerated= PROPERTY_TABLE+".enumerated";
  private static String PROPERTY_is_free= PROPERTY_TABLE+".enumerationIsFree";
  private static String PROPERTY_is_changeable= PROPERTY_TABLE+".enumerationIsChangeable";
*/
}
