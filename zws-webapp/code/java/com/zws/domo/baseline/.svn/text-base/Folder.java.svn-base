/*
 * Created on Sep 5, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.zws.domo.baseline;

import com.zws.domo.BaseDomainObject;

import java.util.*;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class Folder extends BaseDomainObject {

	private Folder parent = null;
	private ArrayList children = null;
	private String name = "";
	
	public Folder(String name){
		this.name = name;
	}
	
	public Folder getParent(){ return parent;}
	public void setParent(Folder parent){ this.parent = parent;}
	
	public Collection getChildren() { return children; }
	
	public String getName(){return name;}
	public void addChild(Folder child){
		if(children == null){
			children = new ArrayList();
		}
		children.add(child);
		child.parent = this;
	}
	
	public String getFQL(String pathSeparator){
		Stack st = new Stack();
		st.push(name);
		Folder dad = getParent();
		while(dad != null){
			st.push(dad.getName());
			dad = dad.getParent();
		}
		String fql = "";
		while(!st.isEmpty()){
			String pathElement = (String)st.pop();
			fql = fql + pathElement + pathSeparator;
		}
		return fql;
	}
	
	public Collection getChildernAsStrings(){
		ArrayList stList = new ArrayList();
		Iterator chI = children.iterator();
		while(chI.hasNext()){
			Folder child = (Folder)chI.next();
			stList.add(child.getName());	
		}
		return stList;
	}
	
	public static Folder getFolderFromPath(Folder root, String path, String separator){
		
		Folder curr = root;
		if(path == null || path.length() == 0)
			return curr;
		StringTokenizer st = new StringTokenizer(path, separator);
		st.nextToken(); //fast forward over root
	 	while(st.hasMoreTokens()){
	 		String name = st.nextToken();
	 		curr = curr.findChildByName(name);
	 		if (curr == null)
	 			break;
	 	}
		return curr;
	}
	
	private Folder findChildByName(String name){
		Folder child = null;
		if(children != null){
			Iterator chI = children.iterator();
			while(chI.hasNext()){
				Folder ch = (Folder)chI.next();
				if(name.equals(ch.getName())){
					child = ch;
					break;	
				}
			}	
		}
		return child;	
	}
	
	public void setChildren(Collection newChildren){
		
		if(newChildren == null)
			return;
		if(children != null)
			children.clear();
		Iterator chI = newChildren.iterator();
		while(chI.hasNext()){
			Folder child = (Folder)chI.next();
		    addChild(child); 	
		}
	}
	 
}
