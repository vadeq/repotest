package com.zws.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.*;

public class ListAction extends Action {
  public ActionForward perform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
    String page = "list";
    ListForm frm = (ListForm)form;

    // 1) execute business action
    if ("delete".equals(frm.getAction())) ItemService.delete(frm.getSelectedItems());
    else if ("add".equals(frm.getAction())) ItemService.add();
    frm.setItems(ItemService.findAll());  //find all for now

    //2) determine next page
    return mapping.findForward(page);
  }
}
