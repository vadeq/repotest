<tr>
 <td valign="top" align="center" colspan="4">
  <table border="0" cellpadding="0" cellspacing="0" width="100%">  
   <tr class="pageHeader"><td>
   	<table width="100%">
   		<tr><td class="headerUser">
			<logic:present name="__member" scope="session">
				logged in as <bean:write name="__member" property="username" scope="session"/>
			</logic:present> 
	    	
    	 </td></tr>
		 <tr><td class="pageTitle"><%=title%></td></tr>
   	</table>
   </td></tr>
   <tr>
    <td><br /></td>
   </tr>   
   <tr>
   
    <td>
    	<div class="formStatusLog"><html:errors property="form.status"/></div>
    	<div class="formWarningLog"><html:errors property="form.warning"/></div>
    	<div class="formErrorLog"><html:errors property="form.error"/></div>
    	<logic:notPresent name="__member" scope="session">
    		<div class="formErrorLog">Please Log In</div><br>
    	</logic:notPresent>
    </td>
    
   </tr>
  </table>
 </td>
</tr>

