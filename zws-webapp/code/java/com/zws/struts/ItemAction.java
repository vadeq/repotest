package com.zws.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;


public class ItemAction extends Action {
  public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    String page = "view";
    boolean error = false;
    {} //System.out.println("performing item.do");
    ItemForm frm = (ItemForm)form;

    //1) execute business action
    {} //System.out.println("before using frm");
    if (frm.getState().equals("edit")) {
      {} //System.out.println("edit");
      if (frm.getAction().equals("save")) {
        try{
          ItemService.save(frm.getItem());
        }
        catch (Exception e) { error = true; }
      }
      if (frm.getAction().equals("delete")) ItemService.delete(frm.getItem().getID());
    }
    frm.setItem(ItemService.findItem(frm.getID()));
    {} //System.out.println("after using frm");


    //2) determine next page
    if (frm.getState().equals("edit")) {
      if (error) page = "edit";  //error can not execute business logic.
      else if (frm.getAction().equals("save")) page = "view";
      else if (frm.getAction().equals("delete")) page = "list";
      else if(frm.getAction().equals("cancel") ) page = "view";
    }
    else if (frm.getState().equals("view")) {
      if (frm.getAction().equals("edit") ) page = "edit";
      if (frm.getAction().equals("close") ) page = "list";
    }
    {} //System.out.println("page =" + page);
    return mapping.findForward(page);
  }
}
