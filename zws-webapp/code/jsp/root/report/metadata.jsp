<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<bean:define id="metadata" name="metadata"/>
<bean:write name="metadata" property="origin"/>
<logic:iterate id="data" name="metadata" property="history" type="zws.data.Metadata">
  <bean:write name="data" property="origin"/>
</logic:iterate>
