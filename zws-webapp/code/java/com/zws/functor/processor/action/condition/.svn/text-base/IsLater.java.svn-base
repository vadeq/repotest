package com.zws.functor.processor.action.condition;

public class IsLater extends MetaDataDiscriminator {
  public boolean isTrue() {
    return getComparator().isLaterRevision() || (getComparator().isEqualRevision() && getComparator().isLaterVersion());
  }
}
