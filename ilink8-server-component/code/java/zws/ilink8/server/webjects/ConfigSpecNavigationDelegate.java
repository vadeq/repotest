package zws.ilink8.server.webjects;


import com.infoengine.object.factory.Webject;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import wt.adapter.BasicWebjectDelegate;
import wt.adapter.WebjectServiceException;
import wt.adapter.query.NavigationDelegate;
import wt.doc.WTDocumentStandardConfigSpec;
import wt.eff.EffContext;
import wt.effectivity.ConfigurationItem;
import wt.epm.EPMDocument;
import wt.epm.workspaces.EPMAsStoredConfigSpec;
import wt.fc.EvolvableHelper;
import wt.fc.QueryResult;
import wt.fc.ReferenceFactory;
import wt.lifecycle.State;
import wt.part.WTPartEffectivityConfigSpec;
import wt.part.WTPartStandardConfigSpec;
import wt.part.WTPartBaselineConfigSpec;
import wt.part.WTPartHelper;
import wt.pds.PDSObjectInput;
import wt.util.WTContext;
import wt.vc.baseline.Baseline;

import wt.vc.config.LatestConfigSpec;
import wt.vc.config.InUseConfigSpec;
import wt.vc.config.ConfigHelper;
import wt.vc.views.View;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectInput;
import java.io.InvalidClassException;


// Referenced classes of package wt.adapter.query:
// NavigationDelegate

public class ConfigSpecNavigationDelegate implements NavigationDelegate,
    Externalizable {

//  private static final String RESOURCE = "wt.adapter.query.queryResource";

  private static final String CLASSNAME;

  static final long serialVersionUID = 1L;

  public static final long EXTERNALIZATION_VERSION_UID = 0xd4b6b5eeea339daL;

  protected static final long OLD_FORMAT_VERSION_UID = 0xed40a5e0feb81f73L;

  //private static final String ADAPTER_RESOURCE = "wt.adapter.adapterResource";

  public ConfigSpecNavigationDelegate() {}

  public void writeExternal(ObjectOutput objectoutput) throws IOException {
    objectoutput.writeLong(0xd4b6b5eeea339daL);
  }

  public void readExternal(ObjectInput objectinput) throws IOException,
      ClassNotFoundException {
    long l = objectinput.readLong();
    readVersion(this, objectinput, l, false, false);
  }

  protected boolean readVersion(
      ConfigSpecNavigationDelegate configspecnavigationdelegate,
      ObjectInput objectinput, long l, boolean flag, boolean flag1)
      throws IOException, ClassNotFoundException {
    boolean flag2 = true;
    if (l != 0xd4b6b5eeea339daL) {
      flag2 = readOldVersion(objectinput, l, flag, flag1);
      if (objectinput instanceof PDSObjectInput) {
        EvolvableHelper.requestRewriteOfEvolvedBlobbedObject();
      }
    }
    return flag2;
  }

  private boolean readOldVersion(ObjectInput objectinput, long l, boolean flag,
      boolean flag1) throws IOException, ClassNotFoundException {
    boolean flag2 = true;
    if (l != 0xed40a5e0feb81f73L) {
      throw new InvalidClassException(CLASSNAME,
          "Local class not compatible: stream classdesc externalizationVersionUID="
              + l + " local class externalizationVersionUID="
              + 0xd4b6b5eeea339daL);
    } else {
      return flag2;
    }
  }

  public static String getObidFromUfid(String ufid) {
    if (ufid == null) return null;
    String obid = ufid;
    final String DELIM = ":";

    int index = ufid.indexOf(DELIM);
    if (index != -1) {
      int endIdx = ufid.lastIndexOf(DELIM);
      int middleIdx = ufid.indexOf(DELIM, index + 1);
      if ((endIdx == middleIdx) || (endIdx == index)) {
        return ufid;
      }// if
      else {
        obid = ufid.substring(0, endIdx);
      }// else
    }// if

    return obid;
  }

  public QueryResult invoke(QueryResult queryresult, Webject webject)
      throws WebjectServiceException {

    //Object obj = null;
    String s = webject.paramValue("SELECTBY");
    try {
      Object obj1;
      if (s.equals("") || s.equalsIgnoreCase("ASSTORED")) {
        EPMDocument epmdocument = (EPMDocument) new ReferenceFactory()
            .getReference(getObidFromUfid(webject.paramValue("OBJECT_REF")))
            .getObject();
        obj1 = EPMAsStoredConfigSpec.newEPMAsStoredConfigSpec(epmdocument);
      } else if (s.equals("") || s.equalsIgnoreCase("LATEST")) {
        obj1 = new LatestConfigSpec();

      } else if (s.equals("USER")) {
        obj1 = WTPartHelper.service.findWTPartConfigSpec();
        if (obj1 == null) {
          obj1 = new LatestConfigSpec();
        }
      } else if (s.equalsIgnoreCase("IN_USE")) {
        InUseConfigSpec inuseconfigspec = InUseConfigSpec.newInUseConfigSpec();
        String s5 = webject.paramValue("SELECTBY_IN_USE_BY_USER");
        if (s5.equals("") || s5.equalsIgnoreCase("true")
            || s5.equalsIgnoreCase("yes") || s5.equalsIgnoreCase("on")) {
          inuseconfigspec.setInUseByCurrentPrincipalIncluded(true);
        } else if (s5.equalsIgnoreCase("false") || s5.equalsIgnoreCase("no")
            || s5.equalsIgnoreCase("off")) {
          inuseconfigspec.setInUseByCurrentPrincipalIncluded(false);
        } else {
          throw new WebjectServiceException(null, "wt.adapter.adapterResource",
              "21", new Object[] { "SELECTBY_IN_USE_BY_USER", s5 });
        }
        obj1 = inuseconfigspec;
      } else if (s.equalsIgnoreCase("BASELINE")) {
        String s1 = webject.paramValue("SELECTBY_BASELINE_REF");
        if (s1.equals("")) {
          s1 = webject.paramValue("SELECTBY_PARAM");
          if (s1.equals("")) { throw new WebjectServiceException(null,
              "wt.adapter.adapterResource", "8",
              new Object[] { "SELECTBY_BASELINE_REF" }); }
        }
        obj1 = WTPartBaselineConfigSpec
            .newWTPartBaselineConfigSpec((Baseline) BasicWebjectDelegate
                .getObjectByUfid(s1));
      } else if (s.equalsIgnoreCase("EFFECTIVE_DATE")) {
        String s2 = webject.paramValue("SELECTBY_DATE");
        if (s2.equals("")) {
          s2 = webject.paramValue("SELECTBY_PARAM");
          if (s2.equals("")) { throw new WebjectServiceException(null,
              "wt.adapter.adapterResource", "8",
              new Object[] { "SELECTBY_DATE" }); }
        }
        DateFormat dateformat = DateFormat.getDateInstance();
        Date date = dateformat.parse(s2);
        obj1 = WTPartEffectivityConfigSpec
            .newWTPartEffectivityConfigSpec(new Timestamp(date.getTime()));
      } else if (s.equalsIgnoreCase("EFFECTIVITY")) {
        WTPartEffectivityConfigSpec wtparteffectivityconfigspec = WTPartEffectivityConfigSpec
            .newWTPartEffectivityConfigSpec();
        String s6 = webject.paramValue("SELECTBY_CONFIG_ITEM_REF");
        if (!s6.equals("")) {
          wtparteffectivityconfigspec
              .setEffectiveConfigItem((ConfigurationItem) BasicWebjectDelegate
                  .getObjectByUfid(s6));
        }
        s6 = webject.paramValue("SELECTBY_CONTEXT_REF");
        if (!s6.equals("")) {
          wtparteffectivityconfigspec
              .setEffectiveContext((EffContext) BasicWebjectDelegate
                  .getObjectByUfid(s6));
        }
        s6 = webject.paramValue("SELECTBY_DATE");
        if (!s6.equals("")) {
          DateFormat dateformat1 = DateFormat.getDateInstance();
          Date date1 = dateformat1.parse(s6);
          Calendar calendar = Calendar.getInstance(WTContext.getContext()
              .getTimeZone(), WTContext.getContext().getLocale());
          //Calendar _tmp = calendar;
          //Calendar _tmp1 = calendar;
          wtparteffectivityconfigspec.setEffectiveDate(new Timestamp(date1
              .getTime()
              - (long) calendar.get(15) - (long) calendar.get(16)));
        }
        s6 = webject.paramValue("SELECTBY_UNIT");
        if (!s6.equals("")) {
          wtparteffectivityconfigspec.setEffectiveUnit(s6);
        }
        s6 = webject.paramValue("SELECTBY_VIEW_REF");
        if (!s6.equals("")) {
          wtparteffectivityconfigspec.setView((View) BasicWebjectDelegate
              .getObjectByUfid(s6));
        }
        obj1 = wtparteffectivityconfigspec;
      } else if (s.equalsIgnoreCase("STANDARD")
          || s.equalsIgnoreCase("STANDARD_PART")) {
        WTPartStandardConfigSpec wtpartstandardconfigspec = WTPartStandardConfigSpec
            .newWTPartStandardConfigSpec();
        String s7 = webject.paramValue("SELECTBY_VIEW_REF");
        if (!s7.equals("")) {
          wtpartstandardconfigspec.setView((View) BasicWebjectDelegate
              .getObjectByUfid(s7));
        }
        s7 = webject.paramValue("SELECTBY_LIFECYCLE_STATE");
        if (!s7.equals("")) {
          wtpartstandardconfigspec.setLifeCycleState(State.toState(s7));
        }
        s7 = webject.paramValue("SELECTBY_INCLUDE_WORKING");
        if (!s7.equals("")) {
          if (s7.equalsIgnoreCase("true") || s7.equalsIgnoreCase("yes")
              || s7.equalsIgnoreCase("on")) {
            wtpartstandardconfigspec.setWorkingIncluded(true);
          } else if (s7.equalsIgnoreCase("false") || s7.equalsIgnoreCase("no")
              || s7.equalsIgnoreCase("off")) {
            wtpartstandardconfigspec.setWorkingIncluded(false);
          } else {
            throw new WebjectServiceException(null,
                "wt.adapter.adapterResource", "21", new Object[] {
                    "SELECTBY_INCLUDE_WORKING", s7 });
          }
        }
        obj1 = wtpartstandardconfigspec;
      } else if (s.equalsIgnoreCase("STANDARD_DOCUMENT")) {
        WTDocumentStandardConfigSpec wtdocumentstandardconfigspec = WTDocumentStandardConfigSpec
            .newWTDocumentStandardConfigSpec();
        String s8 = webject.paramValue("SELECTBY_LIFECYCLE_STATE");
        if (!s8.equals("")) {
          wtdocumentstandardconfigspec.setLifeCycleState(State.toState(s8));
        }
        s8 = webject.paramValue("SELECTBY_INCLUDE_WORKING");
        if (!s8.equals("")) {
          if (s8.equalsIgnoreCase("true") || s8.equalsIgnoreCase("yes")
              || s8.equalsIgnoreCase("on")) {
            wtdocumentstandardconfigspec.setWorkingIncluded(true);
          } else if (s8.equalsIgnoreCase("false") || s8.equalsIgnoreCase("no")
              || s8.equalsIgnoreCase("off")) {
            wtdocumentstandardconfigspec.setWorkingIncluded(false);
          } else {
            throw new WebjectServiceException(null,
                "wt.adapter.adapterResource", "21", new Object[] {
                    "SELECTBY_INCLUDE_WORKING", s8 });
          }
        }
        s8 = webject.paramValue("SELECTBY_IN_USE_BY_USER");
        if (!s8.equals("")) {
          InUseConfigSpec inuseconfigspec1 = InUseConfigSpec
              .newInUseConfigSpec();
          if (s8.equalsIgnoreCase("true") || s8.equalsIgnoreCase("yes")
              || s8.equalsIgnoreCase("on")) {
            inuseconfigspec1.setInUseByCurrentPrincipalIncluded(true);
          } else if (s8.equalsIgnoreCase("false") || s8.equalsIgnoreCase("no")
              || s8.equalsIgnoreCase("off")) {
            inuseconfigspec1.setInUseByCurrentPrincipalIncluded(false);
          } else {
            throw new WebjectServiceException(null,
                "wt.adapter.adapterResource", "21", new Object[] {
                    "SELECTBY_IN_USE_BY_USER", s8 });
          }
          wtdocumentstandardconfigspec.setInUse(inuseconfigspec1);
        }
        obj1 = wtdocumentstandardconfigspec;
      } else if (s.equalsIgnoreCase("LIFECYCLE")) {
        String s3 = webject.paramValue("SELECTBY_LIFECYCLE_STATE");
        if (s3.equals("")) {
          s3 = webject.paramValue("SELECTBY_PARAM");
          if (s3.equals("")) { throw new WebjectServiceException(null,
              "wt.adapter.adapterResource", "8",
              new Object[] { "SELECTBY_LIFECYCLE_STATE" }); }
        }
        WTPartStandardConfigSpec wtpartstandardconfigspec1 = WTPartStandardConfigSpec
            .newWTPartStandardConfigSpec();
        wtpartstandardconfigspec1 = WTPartStandardConfigSpec
            .newWTPartStandardConfigSpec();
        wtpartstandardconfigspec1.setLifeCycleState(State.toState(s3));
        obj1 = wtpartstandardconfigspec1;
      } else if (s.equalsIgnoreCase("VIEW")) {
        String s4 = webject.paramValue("SELECTBY_VIEW_REF");
        if (s4.equals("")) {
          s4 = webject.paramValue("SELECTBY_PARAM");
          if (s4.equals("")) { throw new WebjectServiceException(null,
              "wt.adapter.adapterResource", "8",
              new Object[] { "SELECTBY_VIEW_REF" }); }
        }
        WTPartStandardConfigSpec wtpartstandardconfigspec2 = WTPartStandardConfigSpec
            .newWTPartStandardConfigSpec();
        wtpartstandardconfigspec2.setView((View) BasicWebjectDelegate
            .getObjectByUfid(s4));
        obj1 = wtpartstandardconfigspec2;
      } else {
        throw new WebjectServiceException(null, "wt.adapter.adapterResource",
            "21", new Object[] { "SELECTBY", s });
      }
      return ConfigHelper.service.filteredIterationsOf(queryresult,
          ((wt.vc.config.ConfigSpec) (obj1)));
    } catch (Exception exception) {
      exception.printStackTrace(System.err);
      throw new WebjectServiceException(exception);
    }
  }

  static {
    CLASSNAME = (wt.adapter.query.ConfigSpecNavigationDelegate.class).getName();
  }
}
