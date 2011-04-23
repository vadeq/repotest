<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>
<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>
<script type="text/javascript" language="JavaScript" src="/web/javascript/treeview/treeview.js"></script>

<html:html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<html:form action="/intralink/workspace/index.do">
<html:hidden property="event"/>

<%@ include file="/web/include/interactor.params"%>

<% String title="Personal Workspaces"; %>
<%@ include file="/web/include/header.jsp"%>

<script language="JavaScript">
	function selectFolder(folderName) {
     	document.forms[0].location.value = folderName;
      if (32<folderName.length()) document.forms[0].location.size = folderName.length()+3;
    }
</script>


<bean:define id="IEE" name="hiPersonalWorkspace"  property="IEE" type="zws.IntralinkClient"/>

<bean:define id="hiForm" name="hiPersonalWorkspace"  type="zws.hi.intralink.workspace.hiPersonalWorkspace"/>

<table border="0" cellpadding="2" cellspacing="5">
 <tr>

<!-- IEE Selector.. -->
<td valign="top">
<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">Select IEE Repository</td>
  </tr>
  <tr>
    <td>
      <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
        <tr>
          <td class="listHeader">Node</td>
          <td class="listHeader">Repository</td>
        </tr>
        <tr class="item">
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedIEEServer" name="hiForm" property="selectedIEEServer"/>
           <logic:iterate id="server" name="IEE" property="serverList" type="java.lang.String">  
           <%if(server.equals(selectedIEEServer)) { %>
           <tr><td class="selectedItem"><nobr>[<bean:write name="server"/>]</nobr></td></tr>
           <% } else { %>
           <tr><td class="item"><nobr><a href="index.do?selectedIEEServer=<bean:write name="server"/>" class="button"><bean:write name="server"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
         <td valign="top">
          <table border="0" cellpadding="0" cellspacing="0">
           <bean:define id="selectedIEERepository" name="hiForm" property="selectedIEERepository"/>
           <logic:present name="hiForm" property="IEERepositoryList">  
           <logic:iterate id="repository" name="hiForm" property="IEERepositoryList" type="java.lang.String">  
           <%if(repository.equals(selectedIEERepository)) { %>
            <tr><td class="selectedItem"><nobr>[<bean:write name="repository"/>]</nobr></td></tr>
           <% } else { %>
            <tr><td class="item"><nobr ><a href="index.do?selectedIEERepository=<bean:write name="repository"/>" class="button"><bean:write name="repository"/></a></nobr></td></tr>
           <% } %>
           </logic:iterate>  
           </logic:present>  
          </table border="0" cellpadding="0" cellspacing="0">
         </td>
        </tr>
      </table>
    </td>
  </tr>
</table>
</td>
<!-- ..IEE Selector -->

  <td width="33%" valign="top">
<!-- Workspace List.. -->
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;Personal Workspaces&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td class="item">
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listIconHeader" width="1%"><nobr><img src="/web/button/iconflict.gif" alt="Conflicts"/></nobr></td>
             <td class="listIconHeader" width="1%"><nobr><img src="/web/image/workspace/status.gif" alt="Status"/></nobr></td>
             <td class="listHeader" colspan="2"><nobr>Name</nobr></td>
            </tr>
            <logic:present name="hiPersonalWorkspace" property="workspaces">
            <logic:iterate id="ws" name="hiPersonalWorkspace" property="workspaces" type="zws.data.workspace.Workspace">
            <bean:define id="wsName" name="ws" property="name"/>
            <tr>
             <td class="itemIcon"><nobr><%if(ws.hasConflicts()){%><img src="/web/button/iconflict.gif" alt="Workspace has conflicts!"/><%}else {%>&nbsp;<%}%></nobr></td>
             <td class="itemIcon"><nobr><%if(ws.hasModifiedItems()){%><img src="/web/image/workspace/modified.gif" alt="Workspace has modified items."/><%} 
                                            else if(ws.hasNewItems()){%><img src="/web/image/workspace/new.gif" alt="Workspace has new items."/><%} else {%>&nbsp;<%}%></nobr></td>
             <td class="item"><nobr><b><a href="#" onClick="choose('<%=wsName%>')" class="button"><%=wsName%></a></b></nobr></td>
             <td class="itemIcon" width="1%"><a onclick="if(confirm('OK to delete <%=wsName%>?')) return true; else return false;" href="index.do?event=deleteWorkspace&workspaceName=<%=wsName%>" class="deleteButton" title="Delete workspace - <%=wsName%>"><img gif="itrash"/></a></td>
            </tr>
            </logic:iterate>
            </logic:present>
            <logic:notPresent name="hiPersonalWorkspace" property="workspaces">
            <tr>
             <td class="item" colspan="2"><nobr><i><b>No Workspaces Created<b><i></nobr></td>
            </tr>
            </logic:notPresent>
          </table>
        </td>
      </tr>
     <tr class="formMenu">
      <td>
       <table border="0" cellpadding="3" cellspacing="0" width="100%">
        <tr>
         <td class="formMenu"><nobr>New: <html:text property="workspaceName" onkeypress="javascript:autoSubmitEvent(this, 'createWorkspace');" size="28"/><a href="#" onclick="javascript:sendEvent('createWorkspace');"><img gif="create_workspace" alt="Create new workspace"/></a></nobr></td>
        </tr>
       </table>
      </td>
     </tr>
    </table>
<!-- ..WorkspaceList-->
  </td>

  <td width="33%" valign="top">
<logic:present name="hiPersonalWorkspace" property="chosenItem">
<!-- Selected Workspace.. -->
  <bean:define id="ws" name="hiPersonalWorkspace" property="selectedWorkspace" type="zws.data.workspace.Workspace"/>
  <bean:define id="wsName" name="ws" property="name"/>
    <table class="form" border="0" cellpadding="0" cellspacing="0" width="100%">
      <tr>
        <td class="formTitle" align="left">
          <div align="left"><nobr>&nbsp;<bean:write name="ws" property="name"/>&nbsp;&nbsp;&nbsp;</nobr></div>
        </td>
      </tr>
      <tr>
        <td class="item">
          <table class="listItems" border="0" cellpadding="2" cellspacing="1" width="100%">
            <tr>
             <td class="listIconHeader" width="1%"><nobr><img src="/web/image/workspace/status.gif" alt="Status"/></nobr></td>
             <td class="listIconHeader" width="1%"><nobr><img src="/web/image/workspace/locked.gif" alt="Locks"/></nobr></td>
             <td class="listIconHeader" width="1%"><nobr><img src="/web/button/iconflict.gif" alt="Conflicts"/></nobr></td>
             <td class="listHeader"><nobr>Name</nobr></td>
             <td class="listHeader"><nobr>Config</nobr></td>
             <td class="listHeader"><nobr>Release</nobr></td>
             <td class="listHeader" width="1%" colspan="2"><nobr>&nbsp;</nobr></td>
            </tr>
            <logic:iterate id="item" name="ws" property="itemList" type="zws.data.workspace.IlinkWorkspaceItem">
            <bean:define id="itemName" name="item" property="name"/>
            <tr>
             <bean:define id="state" name="item" property="state"/>
             <td class="itemIcon"><img src="/web/image/workspace/<%=state%>.gif" alt="<%=state%>"/> <!-- state = [ unchanged | modified | new] --></td>
             <td class="itemIcon"><nobr><logic:present name="item" property="lockOwner"><a href="index.do?event=unlockWorkspaceItem&ID=<%=itemName%>" class="actionButton" title="Unlock <%=itemName%>"><img gif="ilocked" alt="Owned by <bean:write name="item" property="lockOwner"/>"/></a>
              </logic:present><logic:notPresent name="item" property="lockOwner">
              <logic:equal name="item" property="state" value="new"><img gif="iunlocked" inactive="true" message="This file has not been checked into the commonspace. Only files in commonspace can be locked." alt="Can not lock a new file!"/>
              </logic:equal><logic:notEqual name="item" property="state" value="new"><a href="index.do?event=lockWorkspaceItem&ID=<%=itemName%>" class="actionButton" title="Lock <%=itemName%>"><img gif="iunlocked" alt="Not owned by anyone"/></a></logic:notEqual>              
              </logic:notPresent></nobr></td>
             <td class="itemIcon"><nobr>
              <logic:present name="item" property="conflicts">
			  <bean:define id="conflicts" type="java.lang.String" value="Conflicts:"/>
              <%
                 zws.data.workspace.WorkspaceConflict con;
                 java.util.Iterator i=item.getConflicts().iterator();
                 int idx=1;
                 while (i.hasNext()){
                   con = (zws.data.workspace.WorkspaceConflict)i.next();
                   //conflicts += "\n  " + idx + ") " + con.getConflictKey();
                   conflicts += "\n  - " + con.getConflictKey();
				   idx++;
                 }
                 conflicts += "\nClick to synchronize with the common space.";
              %><a href="index.do?event=synchronizeWithCommonSpace&ID=<%=itemName%>" class="actionButton" title="Unlock <%=itemName%>"><img gif="iconflict" alt="<%=conflicts%>"/></a>              
              </logic:present>
             </nobr></td>
             <td class="item"><nobr><b><bean:write name="item" property="name"/></b></nobr></td>
             <td class="item"><nobr><bean:write name="item" property="branch"/> <bean:write name="item" property="revision"/>.<bean:write name="item" property="version"/></nobr></td>
             <td class="item"><nobr><bean:write name="item" property="release"/></nobr></td>
             <td class="item"><nobr>
              <a href="index.do?nav=view-item&ID=<%=itemName%>" class="actionButton"><img gif="iview" alt="View Details"/></a>
              <a href="index.do?event=download&ID=<%=itemName%>" class="actionButton"><img gif="idownload" alt="Download binary"/></a>
              <a href="index.do?event=navToUpdateBinaryFile&ID=<%=itemName%>" class="actionButton"><img gif="iupload" alt="Update <%=itemName%> from a file on your local machine"/></a>
              <a href="index.do?event=synchronizeWithCommonSpace&ID=<%=itemName%>" class="actionButton" title="Unlock <%=itemName%>"><img gif="isynchronize" alt="Resynchronize with common space"/></a>
              <a href="index.do?nav=edit-item&ID=<%=itemName%>" class="actionButton"><img gif="iedit" alt="Edit metadata attributes"/></a>
             </nobr></td>
             <td class="itemIcon"><nobr>             
              <logic:equal name="item" property="state" value="unchanged"><a href="index.do?event=deleteWorkspaceItem&ID=<%=itemName%>" class="deleteButton" title="Remove <%=itemName%> from workspace: <%=wsName%>"><img gif="iremove"/></a>
              </logic:equal><logic:notEqual name="item" property="state" value="unchanged"><a onclick="if(confirm('Your Changes will be lost!\n\nOK to delete <%=itemName%>?')) return true; else return false;" href="index.do?event=deleteWorkspaceItem&ID=<%=itemName%>" class="deleteButton" title="Delete and loose changes made to <%=itemName%>"><img gif="itrash"/></a></logic:notEqual>
             </nobr></td>
            </tr>
            </logic:iterate>
          </table>
        </td>
      </tr>
      <tr class="formMenu">
       <td>
        <table border="0" cellpadding="3" cellspacing="0">
         <tr>
          <td class="formMenu"><a href="index.do?event=checkinSelectedWorkspace" class="actionButton" title="Checkin workspace: <%=wsName%>"><img gif="checkin" alt="Checkin workspace <%=wsName%>"/></a></td>
          <td class="formMenu"><a href="checkout.jsp" class="actionButton" title="Checkin workspace: <%=wsName%>"><img gif="checkout" alt="Checkout files from Common Space"/></a></td>
          <td class="formMenu"><a href="index.do?event=navToImportToWorkspace" class="actionButton"><img gif="import" alt="Upload a new file from your local machine"/></a></td>
         </tr>
        </table>
       </td>
      </tr>
    </table>
</logic:present>
&nbsp;
<!-- ..Selected Workspace -->
  </td>


 </tr>
</table border="0" cellpadding="2" cellspacing="5">

<logic:present name="hiPersonalWorkspace" property="uploadedFile00">
 <bean:define id="file" name="hiPersonalWorkspace" property="uploadedFile00"/>
 <bean:write name="file" property="fileName"/><br>
</logic:present>

<%@ include file="/web/include/footer.jsp"%>
</html:form>
</body>
</html:html>
