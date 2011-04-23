/*
 * Created on Oct 22, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package zws.action.transformer.data;

/**
 * @author jyelizarov
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
import zws.action.transformer.Transformer;

public abstract class DataTransformer extends Transformer{
  /*
	public void execute() throws Exception{
		try {
		  Datasource ds = getDataSource();
		  if(ds == null){
			{} //System.out.println("looking for datasource " + getDataSourceName());
		  	ds = DatasourceSvc.find(getDataSourceName());
			setDataSource(ds);
		  }
		
		  {} //System.out.println("source metadata 1 " + getMetadata());
		
		  actionMap = constructActionMap(ds);
		  Iterator keys = actionMap.keySet().iterator();
		  while(keys.hasNext()){ //set all to copy
			String key = (String)keys.next();
			Transformer a = (Transformer)actionMap.get(key);
			if(a != null)
				a.execute();	
				
			{} //System.out.println("target metadata 1 " + getTarget());
		  }
		}
		catch(Exception e) {
				  //getActionLog().log("Failed: converting "+inputFile+" to PDF: "+getOutputPath());
			e.printStackTrace();
		}
	}
	
	private HashMap constructActionMap(Datasource ds){
		{} //System.out.println("constructing action map");
		setTarget(null);
		
		if(ds != null){
			HashMap actionMap = new HashMap(ds.getSchema());
			
			{} //System.out.println("processing schema");
			Iterator keys = actionMap.keySet().iterator();
			while(keys.hasNext()){ //set all to copy
				String key = (String)keys.next();
				{} //System.out.println("schema element " + key);
				
				Copy copyAction = new Copy();
				copyAction.setAttributeName(key);
				if(getTarget() == null){
					{} //System.out.println("new target");
					MetadataBase doc = new MetadataBase();
					doc.setName(getTargetName());
					{} //System.out.println("set target name " + getTargetName());
					setTarget(doc);	
					
				}				
//				copyAction.setMetadata(getMetadata());				
        copyAction.setContext(getContext());
				copyAction.setTarget(getTarget());
				actionMap.put(key, copyAction);
			}
			Iterator ignores = ignoreList.iterator();
			while(ignores.hasNext()){
				String key = (String)ignores.next();
				actionMap.put(key, null);
			}
			Iterator modifies = modifyList.iterator();
			while(modifies.hasNext()){
				Modify modifyAction = (Modify)modifies.next();
				String modKey = modifyAction.getAttributeName();
				if(actionMap.containsKey(modKey)){ 
					
					modifyAction.setTarget(getTarget());
//					modifyAction.setMetadata(getMetadata());
          modifyAction.setContext(getContext());
					actionMap.put(modKey, modifyAction);
				}
			}
			return actionMap;
		}
		return null;	
	}
	
	public void addIgnore(KeyValue pair) { ignoreList.add(pair.getKey()); } 
	public void addModify(Modify m) { 
		modifyList.add(m); 
	}
	
	private HashMap actionMap = null;
	private Vector ignoreList = new Vector();
	private Vector modifyList = new Vector();
*/	
}
