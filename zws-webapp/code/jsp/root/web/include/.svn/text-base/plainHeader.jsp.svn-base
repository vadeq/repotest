   <table border="0" cellpadding="0" cellspacing="0" width="100%">
    <tr>
     <td valign="top" align="center" background="/web/image/logo_background.gif">
      <div align="center"><img border="0" src="/web/image/logo.gif"/></div>
     </td>
    </tr>
    <tr>
     <td height="17" valign="top">
      <div class="formErrorLog"><html:errors property="form.error"/></div><div class="formWarningLog"><html:errors property="form.warning"/></div><div class="formStatusLog"><html:errors property="form.status" /></div>
     </td>
    </tr>
    <tr>
     <td height="43" valign="top" align="center">
      <logic:present name="__member" scope="session">
       <html:link href="/document/index.do" styleClass="menuItem">Documents</html:link><img src="/web/image/spacer.gif" width="12" height="2"/>
       <html:link href="/account/profile/index.do" styleClass="menuItem">Account</html:link><img src="/web/image/spacer.gif" width="12" height="2"/>
       <logic:equal name="__member" property="role" value="admin" scope="session">
        <html:link href="/account/user/index.do" styleClass="menuItem">Users</html:link><img src="/web/image/spacer.gif" width="12" height="2"/>
        <html:link href="/document/processing/metadataSet.jsp" styleClass="menuItem">Metadata</html:link><img src="/web/image/spacer.gif" width="12" height="2"/>
        <html:link href="/document/processing/schedule/view.jsp"styleClass="menuItem">Scheduler</html:link>
       </logic:equal>
       <img src="/web/image/spacer.gif" width="60" height="2"/> <html:link href="/logout.do?event=logout" styleClass="logoutButton">Log Out</html:link>
      </logic:present>
      &nbsp;
     </td>
    </tr>
  </table>
