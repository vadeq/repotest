package com.zws.service.account;

import zws.database.DB;
import zws.database.Database;
import zws.exception.*;
import zws.security.PasswordCipher;
import zws.util.StringUtil;

import com.zws.application.Properties;
import com.zws.domo.account.User;

import java.sql.*;
import java.util.Collection;
import java.util.Vector;


public class AccountService {

	// Rodney McCabe 11/26/2007, add Oracle Support
	private final String SQL_SET_ACTIVE = "UPDATE zwsuser SET active=? WHERE username=?";
	private final String SQL_FIND = "SELECT username, password, firstname, lastname, email, phone, role, active FROM zwsuser WHERE username=?";
	private final String SQL_FINDALL = "SELECT username, password, firstname, lastname, email, phone, role, active FROM zwsuser ORDER BY username";
	private final String SQL_INSERT = "insert INTO zwsuser (username, password, firstname, lastname, email, phone, role, active) VALUES(?,?,?,?,?,?,?, ?)";
	private final String SQL_UPD_PWD = "UPDATE zwsuser SET password=? WHERE username=?";
	private static PasswordCipher cipher;
  private static AccountService service = null; //singleton
  
  static {
    try {
      cipher = new PasswordCipher("zero0");
    } catch (Exception e) {
      {} //System.out.println("FATAL ERROR with password cipher: " + e.getMessage());
    }
  }
  
  private static String getDataSourceName() { return Properties.get(Properties.DesignStateDatabase); }
  private AccountService() { }
  public static AccountService getService(){ if (service==null) service=new AccountService(); return service; }

  public User find(String username) throws UserNotFoundException, Exception {
    User u = null;
    if (null==username) throw new NullPointerException("can not find a user with null username");

    PreparedStatement s =  database().prepareStatement(SQL_FIND);
    s.setString(1, username);

    ResultSet resultSet = database().executeQuery(s);
    while (resultSet.next()) u = unmarshallUser(resultSet);
    resultSet.close();
    s.close();
    if (null==u) throw new UserNotFoundException(username);
    return u;
  }

  public Collection findAll() throws Exception{
    User u = null;
    Collection allUsers = new Vector();
    PreparedStatement s =  database().prepareStatement(SQL_FINDALL);;

    ResultSet resultSet = database().executeQuery(s);
    while (resultSet.next()) {
      u = unmarshallUser(resultSet);
      allUsers.add(u);
    }
    resultSet.close();
    s.close();
    return allUsers;
  }

  public User authenticate(String username, String password) throws UserNotFoundException, InvalidPasswordException, Exception{
    User u;
    u = find(username);

    if (!password.equals(u.getPassword())) {
			{} //System.out.println("User " + username + " tried to log in with " + password + " instead of " + u.getPassword());
      {} //System.out.println("Invalid login attempt for user " + username);
			throw new InvalidPasswordException();
		}

    return u;
  }

  public synchronized void add(User user) throws DuplicateUsernameException, Exception {
    try { //make sure user does not already exist
      find (user.getUsername());
      throw new DuplicateUsernameException(user.getUsername());
    }
    catch (UserNotFoundException e) { ;/*good! - keep going*/ }

	// Rodney McCabe 11/26/2007, add Oracle Support
    /*
     *  ZWSUSER USERNAME  VARCHAR2
        ZWSUSER PASSWORD  VARCHAR2
        ZWSUSER FIRSTNAME VARCHAR2
        ZWSUSER LASTNAME  VARCHAR2
        ZWSUSER EMAIL     VARCHAR2
        ZWSUSER PHONE     VARCHAR2
        ZWSUSER ROLE      VARCHAR2
        ZWSUSER ACTIVE    NUMBER

     */
    
    PreparedStatement s =  database().prepareStatement(SQL_INSERT);;

    s.setString(1,StringUtil.truncateWithIndicator(user.getUsername(), 3999));
    s.setString(2, cipher.encrypt(user.getPassword()));
		s.setString(3,StringUtil.truncateWithIndicator(user.getFirstName(), 3999));
		s.setString(4,StringUtil.truncateWithIndicator(user.getLastName(), 3999));
		s.setString(5,StringUtil.truncateWithIndicator(user.getEmail(), 3999));
		s.setString(6,StringUtil.truncateWithIndicator(user.getPhone(), 3999));
		s.setString(7,StringUtil.truncateWithIndicator(user.getRole(), 3999));

		if (user.getAccountIsActive())
			s.setInt(8, 1);
		else
			s.setInt(8, 0);

    database().execute(s);
  }

  public synchronized void update(User user) throws Exception {
    if (null==user) throw new NullPointerException("Tried to save null user!");
    if (null==user.getUsername()) throw new NullPointerException("Tried to update for null username!");
    String sql = "UPDATE zwsuser ";
    sql += "SET firstName='"+user.getFirstName()+"', ";
    sql += "lastName='"+user.getLastName()+"', ";
    sql += "email='"+user.getEmail()+"', ";
    sql += "phone='"+user.getPhone()+"', ";
    sql += "role='"+user.getRole()+"' ";
    sql += "WHERE username='"+user.getUsername()+"'";
    PreparedStatement s = database().prepareStatement(sql);
    database().execute(s);
  }

  public synchronized void updatePassword(String username, String password) throws UserNotFoundException, Exception{
    if (null==username) throw new NullPointerException("Tried to update for null username!");

		// Rodney McCabe 11/26/2007, add Oracle Support
		PreparedStatement s =  database().prepareStatement(SQL_UPD_PWD);
		s.setString(1, cipher.encrypt(password));
		s.setString(2,username);
    database().execute(s);
  }

  public synchronized void updateRole(String username, String role) throws Exception{
    if (null==username) throw new NullPointerException("Tried to update for null username!");
    String sql = "UPDATE zwsuser ";
    sql += "SET role='"+role+"' ";
    sql += "WHERE username='"+username+"'";
    PreparedStatement s = database().prepareStatement(sql);
    database().execute(s);
  }

  public void activate(String username) throws UserNotFoundException, Exception
  { setActive(username, true); }

  public void deactivate(String username) throws UserNotFoundException, Exception
  { setActive(username, false); }

  public synchronized void setActive(String username, boolean active) throws UserNotFoundException, Exception {
    Connection connection = null;
    if (null==username) throw new NullPointerException("Tried to update for null username!");
    find(username); //make sure user exists, else throw exception

    PreparedStatement s = database().prepareStatement(SQL_SET_ACTIVE);

		if (active)
			s.setInt(1, 1);
		else
				s.setInt(1, 0);

			s.setString(2, username);
			database().execute(s);
  }

  public synchronized User delete(String username) throws UserNotFoundException, Exception {
    User u;
    if (null==username) throw new NullPointerException("Tried to delete using null username!");
    u = find(username);
    String sql = "DELETE FROM zwsuser WHERE username='"+username+"'";
    PreparedStatement s = database().prepareStatement(sql);
    database().execute(s);
    return u;
  }

  private User unmarshallUser(ResultSet r) throws Exception {
    User u = new User();
    u.setUsername(r.getString("username"));
    u.setFirstName(r.getString("firstName"));
    u.setLastName(r.getString("lastName"));
    u.setEmail(r.getString("email"));
    u.setPhone(r.getString("phone"));
    u.setRole(r.getString("role"));
    {} //System.out.println("Password: " + r.getString("password"));
    u.setPassword(cipher.decrypt(r.getString("password")));
		u.setAccountIsActive((r.getInt("active") == 1));

    return u;
  }
  
  private Database database()  throws Exception { return DB.source(getDataSourceName()); }
}