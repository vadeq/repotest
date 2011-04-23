package zws.pen.policy.op.pendata.util;/*
 DesignState - Design Compression Technology
 @author: Administrator
 @version: 1.0
 Created on Aug 18, 2007 4:53:29 PM
 Copywrite (c) 2007 Zero Wait-State Inc. All rights reserved */
import zws.data.Metadata;
import zws.data.MetadataBase;
import zws.pen.policy.op.pendata.PENDataOp;
import zws.pen.policy.op.pendata.PENDataOpBase;
import zws.pen.policy.op.pendata.util.string.StringPairMaker;
import zws.recorder.util.RecorderUtil;
import zws.util.StringPair;

import java.util.Iterator;

/**
 * @author Administrator
 *
 */
public class CreateMetadataOp extends PENDataOpBase {

  public void execute() throws Exception {
    PENDataOp op=null;
    StringPair pair=null;
   if (null==getOps()) return;
   Iterator i = getOps().iterator();
   while (i.hasNext()) {
     op = (StringPairMaker) i.next();
     passConfiguration(op);
     op.execute();
     pair = (StringPair) op.getResult();
     add(pair);
   }
   setResult(metadata);
   RecorderUtil.logActivity(getQxCtx(), "Metadata created", metadata.getName());
  }

  public void add(StringPair pair) {
    metadata.set(pair.getString0(), pair.getString1());
  }

  public Object getResult() { return getMetadata(); }
  public Metadata getMetadata() { return metadata; }
  Metadata metadata=new MetadataBase();
}

/*
<add-subcomponent>
<create-metadata name="some name">
 <attribute name="cage-code" value="978"/>
 <attribute name="cage-code" value="978"/>
 <attribute name="cage-code" value="978"/>
 <attribute name="cage-code" value="978"/>
</create-metadata>

</add-subcomponent>
*/