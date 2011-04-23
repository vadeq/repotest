package zws.repository.R8; /*
DesignState - Design Compression Technology.
@author: ...
@version: 1.0
Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import java.util.Collection;
import java.util.HashMap;
import zws.data.Metadata;
import zws.exception.InvalidSyntax;
import zws.origin.Origin;
import zws.repository.R8.util.ZWS2PDMLinkAttributeMapper;
import zws.repository.search.RepositorySearchAgentBase;
import zws.search.criteria.parser.CriteriaParser;
import zws.search.criteria.parser.CriteriaParserBase;
import zws.util.StringMapper;

public class R8RepositorySearchAgent extends RepositorySearchAgentBase {

  public R8RepositorySearchAgent() {
    this.setName(Origin.FROM_ILINK_8);
  }



  public void setCriteria(String s) throws InvalidSyntax {
    if (null==s) throw new InvalidSyntax("Criteria is null");
    CriteriaParser parser = new CriteriaParserBase();
    parser.displayAsANDOperator("&");
    //parser.displayValueInQuotes(false);
    StringMapper zws2ilink8 = new ZWS2PDMLinkAttributeMapper();
    parser.setFieldNameMapper(zws2ilink8);
    setCriteria(parser.parse(s));

  }


    public void executeQuery() throws Exception {
      try {
        if (null==this.getCriteria() || 0==getCriteria().toString().length()) return;

        String criteria = getCriteria().toString();
        criteria = criteria.replaceAll("( )*&( )*", "&");
        criteria = criteria.replaceAll("\\(( )*", "");
        criteria = criteria.replaceAll("( )*\\)", "");

		R8RepositoryBase r8Base = (R8RepositoryBase) this.getRepository();
		String returnAttributes = getSelect();
		returnAttributes = r8Base.translateReturnAttributes(returnAttributes);


    //orderBy("Name")
    //setAsc(true | false)

		HashMap dataHM = new HashMap();
		dataHM.put("method", 		"Query-Objects");  // Get it from QxContext -- Later I though it should not be the case
		dataHM.put("attribute", 	returnAttributes);
		dataHM.put("type", 		    "wt.epm.EPMDocument"); // Get it from QxContext -- Later I though it should not be the case
		dataHM.put("where", 		criteria); // This is custom
		dataHM.put("filter", 		"latest");

		String xmlResult = R8Invoke.invokeMethod(r8Base.makeOriginHM(getAuthenticationToken()), dataHM);
		{} //System.out.println("The result is: " + xmlResult);
		Collection m = (Collection) r8Base.materializeMetadata(xmlResult, returnAttributes, false);
		store(m);
      }
      catch (Exception e) {
          e.printStackTrace();
      }
    }

    public void setDeleteOutput(boolean b) { deleteOutput=b; }
    public boolean getDeleteOutput() { return deleteOutput; }

    public void addSystemAttributes(Metadata data) {
      super.addSystemAttributes(data);
    }

    private boolean deleteOutput = true;
    private static String NAME = R8Constants.NAME;
    private static String BRANCH = R8Constants.BRANCH;
    private static String REVISION = R8Constants.REVISION;
    private static String VERSION = R8Constants.VERSION;
    private static String CREATED_ON = R8Constants.CREATED_ON;
    private static String RELEASE_LEVEL = R8Constants.RELEASE_LEVEL;

    private static String delim = Origin.delim;
}
