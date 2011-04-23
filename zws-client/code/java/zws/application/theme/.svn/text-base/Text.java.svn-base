package zws.application.theme; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 23, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import java.io.Serializable;

public class Text implements Serializable {
  public String getName() { return name; }
  public void setName(String s) { name=s; }
  public String getFont() { return font; }
  public void setFont(String s) { font=s; }
  public String getColor() { return color; }
  public void setColor(String s) { color=s; }
  public String getSize() { return size; }
  public void setSize(String s) { size=s; }
  public String getAlignment() { return alignment; }
  public void setAlignment(String s) { alignment=s; }
  public String getLeftPadding() { return leftPadding; }
  public void setLeftPadding(String s) { leftPadding=s; }
  public String getRightPadding() { return rightPadding; }
  public void setRightPadding(String s) { rightPadding=s; }
  public boolean isBold() { return isBold; }
  public void setBold(boolean b) { isBold=b; }
  public boolean isUnderlined() { return isUnderlined; }
  public void setUnderlined(boolean b) { isUnderlined=b; }
  public boolean isItalics() { return isItalics; }
  public void setItalics(boolean b) { isItalics=b; }
  public String getWeight() { 
    if (isBold) return BOLD;
    return NORMAL;
  }
  public String getStyle() {
   if (isItalics) return "italics";
   return NONE;
  }
  public String getDecoration() {
	  if (isUnderlined) return "underline";
	  return NONE;
  }

  public void set(String field, String value) {
    if (NAME.equalsIgnoreCase(field)) setName(value);
    else if (FONT.equalsIgnoreCase(field)) setFont(value);
    else if (FACE.equalsIgnoreCase(field)) setFont(value);
    else if (FAMILY.equalsIgnoreCase(field)) setFont(value);
    else if (FONT_FAMILY.equalsIgnoreCase(field)) setFont(value);
    else if (COLOR.equalsIgnoreCase(field)) setColor(value);
    else if (SIZE.equalsIgnoreCase(field)) setSize(value);
    else if (BOLD.equalsIgnoreCase(field)) setBold(TRUE.equalsIgnoreCase(value));
    else if (WEIGTH.equalsIgnoreCase(field)) if (BOLD.equalsIgnoreCase(value)) setBold(true); else setBold(false);
    else if (UNDERLINE.equalsIgnoreCase(field)) setUnderlined(TRUE.equalsIgnoreCase(value));
    else if (UNDERLINED.equalsIgnoreCase(field)) setUnderlined(TRUE.equalsIgnoreCase(value));
    else if (DECORATION.equalsIgnoreCase(field)) if (UNDERLINE.equalsIgnoreCase(value)) setUnderlined(true); else setUnderlined(false);
    else if (ITALICS.equalsIgnoreCase(field)) if (TRUE.equalsIgnoreCase(value)) setItalics(true); else setItalics(false);
    else if (STYLE.equalsIgnoreCase(field)) if (ITALICS.equalsIgnoreCase(value)) setItalics(true); else setItalics(false);
    else if (ALIGNMENT.equalsIgnoreCase(field)) setAlignment(value);
    else if (LEFT_PADDING.equalsIgnoreCase(field)) setLeftPadding(value);
    else if (RIGHT_PADDING.equalsIgnoreCase(field)) setRightPadding(value);
  }

  private static String NAME="name";
  private static String FONT="font";
  private static String FACE="face";
  private static String FAMILY="family";
  private static String FONT_FAMILY="font-family";
  private static String COLOR="color";
  private static String SIZE="size";
  private static String BOLD="bold";
  private static String NORMAL="normal";
  private static String NONE="none";
  private static String WEIGTH="weight";
  private static String UNDERLINE="underline";
  private static String UNDERLINED="underlined";
  private static String DECORATION="decoration";
  private static String ALIGNMENT="alignment";
  private static String LEFT_PADDING="left-padding";
  private static String RIGHT_PADDING="right-padding";
  private static String ITALICS="italics";
  private static String STYLE="style";
  private static String TRUE="true";
  public static String BOLD_WEIGHT="bold";
  
  private String name=null;
  private String font="Trebuchet MS";
  private String color="black";
  private String size="12px";
  private String alignment="left";
  private String leftPadding="2";
  private String rightPadding="2";
  private boolean isBold=false;
  private boolean isItalics=false;
  private boolean isUnderlined=false;
}
