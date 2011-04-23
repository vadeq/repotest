package com.zws.functor.processor.action.condition;

public class IsEarlier extends MetaDataDiscriminator {
  public boolean isTrue() {
    return getComparator().isEarlierRevision() || ( getComparator().isEqualRevision()&& getComparator().isEarlierVersion());
  }
}
