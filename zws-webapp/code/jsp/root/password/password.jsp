<%@ page language="java" import="java.text.SimpleDateFormat;"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%@ include file="/web/include/head.html"%>
<%@ include file="/web/include/panel-bar.jsp"%>


<script type="text/javascript" language="JavaScript" src="/web/javascript/DesignState.js"></script>

<html>
<body topmargin="0" leftmargin="0" bgcolor="white">
<% String title="Encrypt Password"; %>

<%@ include file="/web/include/header.jsp"%>
	
<html:form action="/password/encryptPassword.do" focus="encryptedPassword1" >

<div class="queList">
	<!--  search form here -->
	<input type="hidden" id="event" name="event" value="search"/>	

<table class="form" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td class="formTitle">&nbsp;Password Encryption</td>
  </tr>
  <tr>
    <td>
      <table class="formProps" border="1" cellpadding="0" cellspacing="1" width="100%">
        <tr>
          <td width>
          <table class="formProps" border="0" cellpadding="1" cellspacing="0" width="100%">
            <tr>
              <td class="prop">Enter password</td>
              <td class="value"><html:password property="password1" size="16" styleClass="input"/></td></nobr></td>
            </tr>

			<tr>
              <td class="prop">Re-enter password</td>
              <td class="value"><html:password property="password2" size="16" styleClass="input"/></td></nobr></td>
            </tr>

			<tr>
              <td class="prop">Encrypted password</td>
              <td class="value" onMouseover="this.className='highlightLink';showtip(this,event,'The encrypted password is already copied to clipboard.')"
								onMouseout="this.className='normalLink';hidetip()">
					<html:text readonly="true" property="encryptedPassword1" size="16" styleClass="highlitedItem"/>
			</td></nobr></td>
            </tr>
			<tr></tr><tr></tr>
			<tr></tr><tr></tr>
			<tr></tr><tr></tr>
			<tr></tr><tr></tr>
		  <tr class="formMenu">
			<td>
					<table border="0" cellpadding="1" cellspacing="0" width="100%">
						<tr>
						<td class="editButton">
						<html:link href="#" onclick="javascript:sendEvent('encrypt','');" styleClass="okButton"><img gif="encrypt"/></html:link>
						</td>
						<td width="100%" align="right" class="editButton">
						<html:link href="#" onclick="javascript:clearField('password1');clearField('password2');clearField('encryptedPassword1');" styleClass="okButton"><img gif="clear"/></html:link>
						</td>
						</tr>
					  </table>
					</td>
				  </tr>
				  </table>
          </td>

          <td>
          <table class="formProps" border="0" cellpadding="1" cellspacing="0" width="100%">
		   <tr>
              <td class="prop">&nbsp</td>
              <td class="value">&nbsp</td></nobr></td>
            </tr>
            <tr>
              <td class="prop">Enter password</td>
              <td class="value"><html:password property="password3" size="16" styleClass="input"/></td></nobr></td>
            </tr>

			<tr>
              <td class="prop">Enter Encrypted password</td>
              <td class="value"><html:password property="encryptedPassword2" size="16" styleClass="input"/></td></nobr></td>
            </tr>


			<tr></tr><tr></tr>
			<tr></tr><tr></tr>
			<tr></tr><tr></tr>
			<tr></tr><tr></tr>
		  <tr class="formMenu">
			<td>
					<table border="0" cellpadding="1" cellspacing="0" width="100%">
						<tr>
						<td width="100%" align="right" class="editButton">
						<html:link href="#" onclick="javascript:sendEvent('checkEncrypt','');" styleClass="verify"><img gif="checkout"/></html:link>
						</td>
						<td width="100%" align="right" class="editButton">
						<html:link href="#" onclick="javascript:clearField('password3');clearField('encryptedPassword2');" styleClass="okButton"><img gif="clear"/></html:link>
						</td>
						</tr>
					  </table>
					</td>
				  </tr>
				  </table>
          </td>

        </tr>
      </table>
    </td>
  </tr>


</table>

	<br/>

</div>
</html:form>
<script>
copytoClipBoard('encryptedPassword1');
clearField('password1');
clearField('password2');
clearField('password3');
clearField('encryptedPassword2');
</script>
<%@ include file="/web/include/footer.jsp"%>
</body>
</html>