package zws.hi.application.panelbar.xml; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 23, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import zws.application.Names;
import zws.util.DomainContext;

import java.util.*;

public class PanelBarContext extends DomainContext {
  public String getName() { return (String)get(NAME); }
  public void setName(String s) { set(NAME, s); }

  public boolean containsRole(String s) {
    if (null==s) return false;
    if ("".equals(s.trim())) return false;
    if (roles.isEmpty()) loadRoles();
    if (roles.containsKey(ALL_ROLES)) return true;
    if (roles.containsKey(s.trim().toLowerCase())) return true;
    return false;
  }

  public boolean containsUser(String s) {
    if (null==s) return false;
    if ("".equals(s.trim())) return false;
    if (users.isEmpty()) loadUsers();
    if (users.containsKey(ALL_USERS)) return true;
    if (users.containsKey(s.trim().toLowerCase())) return true;
    return false;
  }

  public void loadRoles() {
    if (null==getRoles()) return;
    if ("".equals(getRoles().trim())) return;
    StringTokenizer tokens = new StringTokenizer(getRoles().trim(), Names.DELIMITER);
    if (0==tokens.countTokens()) roles.put(getRoles().trim(), "true");
    while (tokens.hasMoreTokens()) roles.put(tokens.nextToken().trim().toLowerCase(), "true");
  }

  public void loadUsers() {
    if (null==getUsers()) return;
    if ("".equals(getUsers().trim())) return;
    StringTokenizer tokens = new StringTokenizer(getUsers().trim(), Names.DELIMITER);
    if (0==tokens.countTokens()) users.put(getUsers().trim(), "true");
    while (tokens.hasMoreTokens()) users.put(tokens.nextToken().trim().toLowerCase(), "true");
  }

  public String getRoles() { return (String)get(ROLES); }
  public void setRoles(String s) { set(ROLES, s); }
  public String getUsers() { return (String)get(USERS); }
  public void setUsers(String s) { set(USERS, s); }

  public String getHover() { return (String)get(HOVER); }
  public void setHover(String s) { set(HOVER, s); }
  public String getTitle() { return (String)get(TITLE); }
  public void setTitle(String s) { set(TITLE, s); }
  public String getCaption() { return (String)get(CAPTION); }
  public void setCaption(String s) { set(CAPTION, s); }
  public String getCaptionCSSStyle() { return (String)get(CAPTION_CSS_STYLE); }
  public void setCaptionCSSStyle(String s) { set(CAPTION_CSS_STYLE, s); }
  public String getTip() { return (String)get(TIP); }
  public void setTip(String s) { set(TIP, s); }
  public String getImage() { return (String)get(IMAGE); }
  public void setImage(String s) { set(IMAGE, s); }
  public String getIcon() { return (String)get(ICON); }
  public void setIcon(String s) { set(ICON, s); }
  public String getURL() { return (String)get(URL); }
  public void setURL(String s) { set(URL, s); }
  public String getOnClick() { return (String)get(ON_CLICK); }
  public void setOnClick(String s) { set(ON_CLICK, s); }
  public String getHTMLHeaderFile() { return (String)get(HTML_HEADER_FILE); }
  public void setHTMLHeaderFile(String s) { set(HTML_HEADER_FILE, s); }
  public String getHTMLFooterFile() { return (String)get(HTML_FOOTER_FILE); }
  public void setHTMLFooterFile(String s) { set(HTML_FOOTER_FILE, s); }


  public String getTheme() { return (String)get(THEME); }
  public void setTheme(String s) { set(THEME, s); }

  public String getGraphicsTheme() { return (String)get(GRAPHICS_THEME); }
  public void setGraphicsTheme(String s) { set(GRAPHICS_THEME, s); }

  private static String NAME="name";
  private static String HOVER="hover";
  private static String TITLE="title";
  private static String TIP="tip";
  private static String IMAGE="image";
  private static String ICON="icon";
  private static String CAPTION="caption";
  private static String CAPTION_CSS_STYLE="caption-css-style";
  private static String URL="url";
  private static String ON_CLICK="on-click";
  private static String THEME="theme";
  private static String GRAPHICS_THEME="graphics-theme";
  private static String HTML_HEADER_FILE="html-header-file";
  private static String HTML_FOOTER_FILE="html-footer-file";
  private static String ROLES="roles";
  private static String USERS="users";

  private static String ALL_ROLES = "all-roles";
  private static String ALL_USERS = "all-users";

  private Map roles = new HashMap();
  private Map users= new HashMap();

}
