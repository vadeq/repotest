package zws.hi.application.panelbar.xml; /*
 DesignState - Design Compression Technology.
 @author: Arbind Thakur
 Created on Feb 23, 2006
 @version: 1.0
 Copywrite (c) 2002-2005 Zero Wait-State Inc. All rights reserved */
import Coalesys.PanelBar.Group;

import com.zws.session.Member;

import zws.application.theme.Color;
import zws.application.theme.Text;
import zws.application.theme.Theme;
import zws.hi.application.panelbar.hiPanelBar;
import zws.util.StringPair;

import java.util.Stack;

import javax.servlet.http.HttpSession;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class PanelBarLoader extends DefaultHandler {
  public PanelBarLoader(Member member, HttpSession session, String userAgent) {
    memb=member;
    ses= session;
    uAgent = userAgent;
  }

  public void startElement (String uri, String name, String qName, Attributes atts) {
	  try {
			if ( qName.equalsIgnoreCase("panel-bar")) { pushPanelBar(atts); }
			if ( qName.equalsIgnoreCase("menu")) { pushMenu(atts); }
			if ( qName.equalsIgnoreCase("menu-item")) { pushMenuItem(atts); }
			if ( qName.equalsIgnoreCase("theme")) { setTheme(atts); }
			if ( qName.equalsIgnoreCase("color")) { addColor(atts); }
			if ( qName.equalsIgnoreCase("text")) { addText(atts); }
			if ( qName.equalsIgnoreCase("property")) { addProperty(atts); }
			if ( qName.equalsIgnoreCase("size")) { addProperty(atts); }
			if ( qName.equalsIgnoreCase("image")) { addImage(atts); }
	  }
	  catch (Exception e) { e.printStackTrace(); }
	}

  private void setTheme(Attributes atts) throws Exception {
    activeTheme = new Theme();
    activePanelBar.setTheme(activeTheme);
  }

  private void addColor(Attributes atts) throws Exception {
    Color c = unmarshallColor(atts);
    activeTheme.add(c);
  }

  private void addText(Attributes atts) throws Exception {
    Text t= unmarshallText(atts);
    activeTheme.add(t);
  }

  private void addProperty(Attributes atts) throws Exception {
	  StringPair pair = unmarshallProperty(atts);
	  activePanelBar.addProperty(pair);
	}

  private void addImage(Attributes atts) throws Exception {
	  StringPair pair = unmarshallProperty(atts);
	  activePanelBar.addImage(pair);
	}

  public void endElement (String uri, String name, String qName) {
	  try {
	    if ( qName.equalsIgnoreCase("panel-bar")) { popPanelBar(); }
	    if ( qName.equalsIgnoreCase("menu")) { popGroup(); }
	    if ( qName.equalsIgnoreCase("menu-item")) { popItem(); }
	  }
	  catch (Exception e) { e.printStackTrace(); }
	}

  private void pushPanelBar(Attributes atts) throws Exception {
    PanelBarContext ctx = unmarshall(atts);
    stack.push(ctx);
    hiPanelBar pb = new hiPanelBar(memb, uAgent, ctx);
    activePanelBar = pb;
    ses.setAttribute(pb.getName(), pb);
  }


  private void pushMenu(Attributes atts) throws Exception {
    PanelBarContext ctx = unmarshall(atts);
    PanelBarContext parent = (PanelBarContext)stack.peek();
    ctx.setParent(parent);
    stack.push(ctx);
    activeGroup = activePanelBar.addGroup(ctx);
  }

  private void pushMenuItem(Attributes atts) throws Exception {
	  PanelBarContext ctx = unmarshall(atts);
	  PanelBarContext parent = (PanelBarContext)stack.peek();
	  ctx.setParent(parent);
    stack.push(ctx);
    activePanelBar.additem(activeGroup, ctx);
  }

  private void popPanelBar() throws Exception {
   stack.pop();
   activePanelBar.initialize();
   activePanelBar=null;
  }

  private void popGroup() throws Exception {
    stack.pop();
    activeGroup=null;
  }

  private void popItem() {
    stack.pop();
  }

  private PanelBarContext unmarshall(Attributes atts) {
    PanelBarContext ctx = new PanelBarContext();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      ctx.set(key, value);
    }
    return ctx;
  }

  private Color unmarshallColor(Attributes atts) {
    Color color = new Color();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      if (NAME.equalsIgnoreCase(key)) color.setName(value);
      else if (COLOR.equalsIgnoreCase(key)) color.setValue(value);
    }
    return color;
  }

  private Text unmarshallText(Attributes atts) {
    Text text = new Text();
    String key, value;
    for (int idx = 0; idx < atts.getLength(); idx++){
      key = atts.getQName(idx);
      value = atts.getValue(idx);
      text.set(key, value);
    }
    return text;
  }

  private StringPair unmarshallProperty(Attributes atts) {
	  StringPair pair = new StringPair();
	  String key, value;
	  for (int idx = 0; idx < atts.getLength(); idx++){
	    key = atts.getQName(idx);
	    value = atts.getValue(idx);
	    if (NAME.equalsIgnoreCase(key)) pair.setString0(value);
	    else if (VALUE.equalsIgnoreCase(key)) pair.setString1(value);
	    else if (SRC.equalsIgnoreCase(key)) pair.setString1(value);
	  }
	  return pair;
	}

  private Member memb = null;
  private String uAgent=null;
  private HttpSession ses=null;

  private hiPanelBar activePanelBar=null;
  private Group activeGroup;
  private Theme activeTheme;
  private Stack stack = new Stack();

  private static String NAME="name";
  private static String VALUE="value";
  private static String COLOR="color";
  private static String SRC="src";

}
