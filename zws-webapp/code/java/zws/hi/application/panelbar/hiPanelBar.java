package zws.hi.application.panelbar; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 23, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */

import com.zws.hi.hiItem;
import com.zws.session.Member;
import zws.application.theme.*;

import zws.hi.application.panelbar.xml.PanelBarContext;
import zws.util.StringPair;

import java.util.*;

import Coalesys.PanelBar.*;

public class hiPanelBar extends hiItem{

  public hiPanelBar(Member m, String userAgent, PanelBarContext ctx) throws Exception {
    member=m;
    context=ctx;
    setName(ctx.getName());
    pb = new PanelBar();
    pb.setUserAgent(userAgent); // Set for browser auto detect
    pb.setUserData(licenseKey); // Register the PanelBar object
    if (notEmpty(ctx.getHover()) && TRUE.equalsIgnoreCase(ctx.getHover().trim())) pb.setFollowYScroll(true);
    else pb.setFollowYScroll(false);
    pb.setExpandMode(lookupExpandMode());
  }

  public void initialize() throws Exception {
    //if (notEmpty(ctx.getGraphicsTheme())) theme = Themes.find(ctx.getGrahpicsTheme());

    //PanelBar properties
    pb.setWidth(size("panel-width"));
    pb.setHeight(size("panel-height"));
    pb.setBorderSize(size("panel-border"));
    pb.setBackgroundColor(color("panel-background"));
    pb.setBackgroundImage(image("panel-background"));
    pb.setClearPixelImage(image("clear-pixel"));

    //Menu properties
    pb.setButtonHeight(size("menu-height"));
    pb.setButtonBorderSize(size("menu-border"));
    pb.setButtonColor(color("menu-background"));
    pb.setButtonBackgroundImage(image("menu-background"));
    populateFont(pb.getButtonFont(), "menu-title");
    populateFont(pb.getButtonHoverFont(), "menu-title-hover");

    //Menu Spacing properties
    pb.setGroupSpacing(size("menu-spacing-height"));
    pb.setGroupSpacingColor(color("menu-spacing"));

    //Menu Item Properties
    pb.setIconHeight(size("menu-item-icon-height"));
    pb.setIconWidth(size("menu-item-icon-width"));
    pb.setIconPosition(lookupIconPosition());
    populateFont(pb.getItemFont(), "menu-item");
    populateFont(pb.getItemHoverFont(), "menu-item-hover");

    /*
    pb.setScrollDownActiveImage("/web/button/panel-bar/zws/scrolldown_active.gif");
    pb.setScrollDownDisabledImage("/web/button/panel-bar/zws/scrolldown_disable.gif");
    pb.setScrollDownEnabledImage("/web/button/panel-bar/zws/scrolldown_enable.gif");
    pb.setScrollUpActiveImage("/web/button/panel-bar/zws/scrollup_active.gif");
    pb.setScrollUpDisabledImage("/web/button/panel-bar/zws/scrollup_disable.gif");
    pb.setScrollUpEnabledImage("/web/button/panel-bar/zws/scrollup_enable.gif");
    */
  }

  protected int size(String property) {
    String s = (String)properties.get(property);
    {} //System.out.println("size of " + property + "=" + s);
    if (null==s) return 0;
    if ("".equals(s.trim())) return 0;
    try { return Integer.valueOf(s).intValue(); }
    catch (NumberFormatException e) {
     e.printStackTrace();
     {} //System.out.println("size of " + property + "=" + s);
     return 0;
   }
  }

  protected String color(String key) {
    String s =theme.getColor(key);
    {} //System.out.println("color of " + key + "=" + s);
    return s;
  }

  protected String image(String name) {
    String s = "/web/button/panel-bar/" + context.getGraphicsTheme() + "/" + images.get(name) + ".gif";
    {} //System.out.println(s);
    return s;
  }

  protected String imageSrc(String src) {
    //leave off .gif for rollover scripts detection
	  String s = "/web/button/panel-bar/" + context.getGraphicsTheme() + "/" + src;
	  {} //System.out.println(s);
	  return s;
	}

  protected int lookupExpandMode() {
    String mode = (String)context.get("expand-mode");
    if ("scroll".equalsIgnoreCase(mode)) return ExpandModeConstants.SingleGroupCalculatedHeight;
    else if ("single".equalsIgnoreCase(mode)) return ExpandModeConstants.SingleGroupContentHeight;
    else if ("multi".equalsIgnoreCase(mode)) return ExpandModeConstants.MultiGroupContentHeight;
    else return ExpandModeConstants.MultiGroupContentHeight;
  }

  protected int lookupIconPosition() {
    String position = (String)properties.get("icon-position");
    if ("left".equalsIgnoreCase(position)) return IconPositionConstants.Left;
    else if ("right".equalsIgnoreCase(position)) return IconPositionConstants.Right;
    else if ("above".equalsIgnoreCase(position)) return IconPositionConstants.Above;
    else if ("below".equalsIgnoreCase(position)) return IconPositionConstants.Below;
    else return IconPositionConstants.Left;
  }

  protected void populateFont(Font font, String themeText) {
	  font.setSize(theme.getTextSize("menu-title-hover"));
	  font.setColor(theme.getTextColor("menu-title-hover"));
	  font.setStyle(theme.getTextStyle("menu-title-hover"));
	  font.setWeight(theme.getTextWeight("menu-title-hover"));
	  font.setFamily(theme.getTextFont("menu-title-hover"));
	  font.setAlignment(theme.getTextAlignment("menu-title-hover"));
	  font.setPaddingLeft(integer(theme.getTextLeftPadding("menu-title-hover")));
	  font.setPaddingRight(integer(theme.getTextRightPadding("menu-title-hover")));
	  font.setTextDecoration(theme.getTextDecoration("menu-title-hover"));
  }

  protected int integer(String s) { return Integer.valueOf(s).intValue(); }
  public void setTheme(Theme t) throws Exception{ theme = t; }
  public void addProperty(StringPair pair) throws Exception{ properties.put(pair.getString0(), pair.getString1()); }
  public void addImage(StringPair pair) throws Exception{ images.put(pair.getString0(), pair.getString1()); }

  protected String imagePath(String name) {
    return "/web/button/panel-bar/"+context.getGraphicsTheme()+"/"+name+".gif";
  }

  public Group addGroup(PanelBarContext ctx) throws Exception{
    boolean authorized = false;
    if (ctx.containsRole(member.getRole())) authorized = true;
    if (ctx.containsUser(member.getUsername())) authorized = true;
    if (!authorized) return null;
    boolean hyperLinked=false;
	  Group group = pb.getGroups().Add();
	  if (notEmpty(ctx.getHTMLHeaderFile())) group.setHtmlAboveItems(read(ctx.getHTMLHeaderFile()));
	  if (notEmpty(ctx.getTitle())) group.setCaption(ctx.getTitle());
	  if (notEmpty(ctx.getOnClick())) { group.setOnClick(ctx.getOnClick()); hyperLinked=true; }
	  else if (notEmpty(ctx.getURL())) { group.setOnClick("goto('"+ctx.getURL()+"')"); ; hyperLinked=true; }
    if (notEmpty(ctx.getCaptionCSSStyle())) group.setCaptionCssClass(ctx.getCaptionCSSStyle());
	  if (notEmpty(ctx.getImage())) {
	    String image = ctx.getImage();
	    if (hyperLinked) image +="_nav";
	    group.setButtonImage(imagePath(image));
	  }
	  String footer = "<img src=\"" + image("menu-bottom") + "\" border=\"0\"/>";
	  if (notEmpty(ctx.getHTMLFooterFile())) group.setHtmlBelowItems(read(ctx.getHTMLFooterFile()) + footer);
	  else group.setHtmlBelowItems(footer);
	  return group;
  }

  public Item additem (Group group, PanelBarContext ctx) throws Exception{
    boolean authorized = false;
    if (ctx.containsRole(member.getRole())) authorized = true;
    if (ctx.containsUser(member.getUsername())) authorized = true;
    if (!authorized) return null;

    Item item = group.getItems().Add();
    if (notEmpty(ctx.getImage())) {
      item.setIconImage(imageSrc(ctx.getImage()));
      if (notEmpty(ctx.getCaption()));
    }
    else if (notEmpty(ctx.getIcon())) {
      item.setIconImage(imageSrc(ctx.getIcon()));
      if (null!=ctx.getCaption()) item.setCaption(ctx.getCaption());
    }
    else if (notEmpty(ctx.getCaption()))
      item.setCaption(ctx.getCaption());
    else if (notEmpty(ctx.getTip())) item.setCaption(ctx.getTip());
    else item.setCaption("??????");
    if (notEmpty(ctx.getCaptionCSSStyle())) item.setCaptionCssClass(ctx.getCaptionCSSStyle());
    if (notEmpty(ctx.getTip())) item.setIconALT(ctx.getTip());
    if (notEmpty(ctx.getURL())) item.setURL(ctx.getURL());
    if (notEmpty(ctx.getOnClick())) item.setOnClick(ctx.getOnClick());
    return item;
  }

  public String getPanelBarCode() {
    try {  return pb.GeneratePanelBar(0); }
    catch(Exception e)  { e.printStackTrace(); return "";}
  }

  public String getJavaScriptCode() {
    try {  return pb.GenerateJavaScript(0); }
    catch(Exception e)  { e.printStackTrace(); return "";}
  }

  public String getStyleSheetCode() {
    try {  return pb.GenerateStyleSheet(); }
    catch(Exception e)  { e.printStackTrace(); return "";}
  }

  public String getOnLoadEvent() {
    try {  return pb.GenerateOnLoadEvent(); }
    catch(Exception e)  { e.printStackTrace(); return "";}
  }

  public String getOnResizeEvent() {
    try {  return pb.GenerateOnResizeEvent(); }
    catch(Exception e)  { e.printStackTrace(); return "";}
  }

  public String getName() { return name; }
  public void setName(String s) { name=s; }

  private boolean notEmpty(String s) {
    if (null==s) return false;
    if ("".equals(s.trim())) return false;
    return true;
  }

  private String read(String filename) {
    return null;
    //+++
  }

  private String name=null;
  private PanelBar pb=null;
  private Theme theme=null;
  private PanelBarContext context=null;
  private Map properties = new HashMap();
  private Map images = new HashMap();
  private Member member=null;
  private static String licenseKey = "dstewart:zerowait-state:1654630035";
  private static String TRUE = "true";

}
