package zws.ilink8.server.webjects;

import java.util.Vector;
import wt.fc.ObjectVector;
import wt.fc.PersistenceHelper;
import wt.fc.QueryResult;
import wt.fc.ReferenceFactory;
import wt.inf.container.WTContained;
import wt.inf.container.WTContainer;
import wt.query.QuerySpec;
import wt.query.SearchCondition;
import wt.util.WTException;
import wt.vc.config.LatestConfigSpec;
import com.infoengine.object.factory.Group;
import com.infoengine.object.factory.Task;
import com.infoengine.object.factory.Webject;
import com.ptc.core.meta.type.common.TypeInstance;

// Referenced classes of package com.ptc.core.adapter.server.impl:
//            AbstractWebjectDelegate

public class QueryObjectsWebjectDelegate extends com.ptc.core.adapter.server.impl.QueryObjectsWebjectDelegate{

    private static final String CLASSNAME;
    static final long serialVersionUID = 1L;
    public static final long EXTERNALIZATION_VERSION_UID = 0xcd3b0a178d537d30L;
    protected static final long OLD_FORMAT_VERSION_UID = 0xe825c0fc792c58d7L;

    public QueryObjectsWebjectDelegate() { }

    public Task invoke(Task task) throws WTException {
    	Webject webject = task.getWebject();
    	String filter = webject.paramValue("FILTER");
        if(filter == null || filter.equals("")){
        	return super.invoke(task);
        }
        preset(task);
        String type = webject.paramValue("TYPE");
        Vector typeInstances = getTypeInstances(targetType, whereClause, new String[]{"thePersistInfo.createStamp"});//this.getCustomResult(targetType, whereClause);

        ObjectVector objVector = new ObjectVector();
        for(int i=0; i < typeInstances.size(); i++){
        	TypeInstance ti = (TypeInstance) typeInstances.elementAt(i);
	    	String pi = ti.getPersistenceIdentifier();
	    	objVector.addElement(new ReferenceFactory().getReference(pi).getObject());
        }        
        
        QueryResult queryResult = new QueryResult(objVector);

		if(filter.equalsIgnoreCase("latest")){
			queryResult = new LatestConfigSpec().process(queryResult);
		}
        Group groupOut = new Group("output");
        groupOut.setClassName(type);
        
        while(queryResult.hasMoreElements()) {
       	 	Object object = queryResult.nextElement();
       	 	addTypeInstance(groupOut, getTypeInstance(object.toString(), attributeParams));
        }
        Task response = new Task();
        response.addVdb(groupOut);
        return response;
    }

	public static String replacePattern(String str, String pattern, String replace) {
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();

		while ((e = str.indexOf(pattern, s)) >= 0) {
			result.append(str.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(str.substring(s));
		return result.toString();
	}

    public WTContained getWTContainerByName(String containerType, String containerName, String comparator) throws WTException{
    	WTContainer container = null;
    	try{
    		if(containerName.indexOf("*") != -1){containerName = containerName.replaceAll("[*]", "%");}
	    	Class class1 = Class.forName(containerType);
	    	QuerySpec queryspec = new QuerySpec(class1);
	    	SearchCondition searchcondition = new SearchCondition(class1, "containerInfo.name", comparator, containerName, false);
	    	queryspec.appendWhere(searchcondition, 0, -1);
	    	QueryResult queryresult = PersistenceHelper.manager.find(queryspec);
	    	if(queryresult.size() == 0){ return null; }
	    	container = (WTContainer)queryresult.nextElement();
    	}catch(Exception e) { throw new WTException(e); }
    	return container;
    }

    static
    {
        CLASSNAME = (zws.ilink8.server.webjects.QueryObjectsWebjectDelegate.class).getName();
    }
}