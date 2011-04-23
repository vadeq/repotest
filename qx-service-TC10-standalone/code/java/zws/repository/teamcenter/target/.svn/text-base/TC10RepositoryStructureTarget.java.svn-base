package zws.repository.teamcenter.target;
/*
DesignState - Design Compression Technology
@author: Arbind Thakur
@version: 1.0
Copywrite (c) 2003 Zero Wait-State Inc. All rights reserved */
import zws.origin.tc10.TC10ItemRevOrigin;
import zws.qx.QxContext;
import zws.repository.target.RepositoryStructureTarget;
import zws.repository.teamcenter.TC10Constants;
import zws.repository.teamcenter.TC10RepositoryBase;
import zws.security.Authentication;
import zws.bill.intralink.BillOfMaterials;
import zws.data.Metadata;
import zws.exception.InvalidOrigin;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.ugsolutions.iman.kernel.IMANComponentBOMLine;
import com.ugsolutions.iman.kernel.IMANComponentBOMViewRevision;
import com.ugsolutions.iman.kernel.IMANComponentBOMViewRevisionType;
import com.ugsolutions.iman.kernel.IMANComponentBOMWindow;
import com.ugsolutions.iman.kernel.IMANComponentBOMWindowType;
import com.ugsolutions.iman.kernel.IMANComponentItem;
import com.ugsolutions.iman.kernel.IMANComponentItemRevision;
import com.ugsolutions.iman.kernel.IMANComponentRevisionRule;
import com.ugsolutions.iman.kernel.IMANComponentViewType;
import com.ugsolutions.iman.kernel.IMANException;
import com.ugsolutions.iman.kernel.IMANPreferenceService;
import com.ugsolutions.iman.kernel.IMANSession;



/**
 * The Interface RepositoryStructureTarget.
 */
public class TC10RepositoryStructureTarget extends TC10RepositoryBase
implements RepositoryStructureTarget {

  /**
   * The Constructor.
   * @param parent parentContext
   */
  public TC10RepositoryStructureTarget(QxContext parent)
  {
    configureParentContext(parent);
  }

  public void structureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception
  {
    Metadata root = bill.getMetadata();

    HashMap tcSessions = (HashMap)getTeamcenterSessions();
    IMANSession imanSession = (IMANSession)tcSessions.get(id.getUsername());

    IMANPreferenceService prefService = imanSession.getPreferenceService();
    setDefaultViewType(prefService.getString( IMANPreferenceService.IMAN_preference_site,
                                              "PSE_default_view_type"));
    String preciseStr = prefService.getString( IMANPreferenceService.IMAN_preference_site,
                                               "IMAN_BOM_Precision_Preference");
    if( preciseStr.equals("Precise") ) setPrecise(true);
    else setPrecise(false);

    // Check for existing Items
    validateItemRevision(imanSession, runningCtx, root, id);

    // Create BOM View
    createBOM(imanSession, runningCtx, root, id);

  }

  private void validateItemRevision(IMANSession session, QxContext runningCtx, Metadata data, Authentication id) throws Exception
  {
    TC10ItemRevOrigin o = null;
    IMANComponentItemRevision rev = null;

    if( data.getOrigin() instanceof TC10ItemRevOrigin )
      o=(TC10ItemRevOrigin)data.getOrigin();
    else
      throw new InvalidOrigin(data.getOrigin());

    rev = findItemRevision(session, o.getItemId(), o.getRevision() );
    // If does not exist, create
    if(rev==null)
    {
      TC10RepositoryMetadataTarget metaTarget = new TC10RepositoryMetadataTarget(runningCtx);
      metaTarget.create(runningCtx, data, id);
    }

    // Check children
    Collection children = data.getSubComponents();
    if(children==null) return;

    Iterator it = children.iterator();
    if(it.hasNext())
    {
      Metadata subM = (Metadata)it.next();
      validateItemRevision(session, runningCtx, subM, id);
    }

  }

  private void createBOM(IMANSession session, QxContext runningCtx, Metadata data, Authentication id) throws Exception
  {
    String structureConfiguration = TC10Constants.DEFAULT_CONFIGURATION;

    TC10ItemRevOrigin o = null;
    IMANComponentBOMViewRevision bvr = null;

    if( data.getOrigin() instanceof TC10ItemRevOrigin )
      o=(TC10ItemRevOrigin)data.getOrigin();
    else
      throw new InvalidOrigin(data.getOrigin());

    IMANComponentBOMViewRevisionType bvrType = (IMANComponentBOMViewRevisionType)session.getTypeComponent("PSBOMViewRevision");
    IMANComponentViewType viewType = getType(session, o.getItemId(), o.getRevision(), getDefaultViewType());
    bvr = bvrType.create(o.getItemId(), o.getRevision(), viewType, isPrecise());

    IMANComponentRevisionRule revRule = getRevisionRule(session, structureConfiguration);
    IMANComponentBOMWindowType myBOMWindowType = (IMANComponentBOMWindowType) session.getTypeComponent("BOMWindow");
    IMANComponentBOMWindow myBOMWindow = myBOMWindowType.create(revRule);
    IMANComponentBOMLine bl = myBOMWindow.setWindowTopLine(null, null, null, bvr);

    //  Check children
    Collection children = data.getSubComponents();
    if(children==null) return;

    Iterator it = children.iterator();
    if(it.hasNext())
    {
      Metadata subM = (Metadata)it.next();
      createBOM(session, runningCtx, subM, id);

      TC10ItemRevOrigin oChild = null;
      IMANComponentItem item = null;
      IMANComponentItemRevision rev = null;
      IMANComponentBOMLine blChild = null;

      if( subM.getOrigin() instanceof TC10ItemRevOrigin )
        oChild=(TC10ItemRevOrigin)subM.getOrigin();
      else
        throw new InvalidOrigin(subM.getOrigin());

      item = findItem(session, oChild.getItemId());
      rev = findItemRevision(session, oChild.getItemId(), oChild.getRevision());
{} //System.out.println("adding...."+oChild.getItemId() + "/" + oChild.getRevision());
      blChild = bl.add(item, rev, null, false);

      // Set BomLine Properties
      blChild.setProperty("bl_quantity", subM.get(TC10Constants.QUANTITY));
    }

    myBOMWindow.save();
    myBOMWindow.close();

  }

  public IMANComponentViewType getType(IMANSession session, String itemId, String revId, String type)
  {
      String s = type;
      IMANComponentViewType viewType = null;
      if(s != null)
      {
        IMANComponentViewType viewTypes[] = null;
          try
          {
              IMANComponentBOMViewRevisionType imancomponentbomviewrevisiontype = (IMANComponentBOMViewRevisionType)session.getTypeComponent("PSBOMViewRevision");
              viewTypes = imancomponentbomviewrevisiontype.getAvailableViewTypes(itemId, revId);
          }
          catch(IMANException ex) { }
          for(int i = 0; viewTypes != null && viewType == null && i < viewTypes.length; i++)
              if(viewTypes[i].toString().equals(s))
                viewType = viewTypes[i];

      }
      return viewType;
  }

  protected String getDefaultViewType() {
    return defaultViewType;
  }

  protected void setDefaultViewType(String defaultViewType) {
    this.defaultViewType = defaultViewType;
  }

  protected boolean isPrecise() {
    return isPrecise;
  }

  protected void setPrecise(boolean isPrecise) {
    this.isPrecise = isPrecise;
  }

  public void createAndStructureBill(QxContext runningCtx, BillOfMaterials bill, Authentication id) throws Exception {
    // TODO Auto-generated method stub
    return ;
  }

  String defaultViewType;
  boolean isPrecise;
}
