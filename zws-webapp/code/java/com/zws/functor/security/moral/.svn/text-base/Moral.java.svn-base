package com.zws.functor.security.moral;

import com.zws.domo.account.User;
import com.zws.functor.Functor;

public abstract class Moral extends Functor {

  public void execute () throws Exception { setResult(check()); }

  public abstract Boolean check();

  public User getUser() { return user; }
  public void setUser(User u) { user=u; }
  public Boolean isGood() { return good; }

  public Object getResult() { return isGood(); }
  public void setResult(Object o ) { good = (Boolean)o; }

  private Boolean good=null;
  private User user=null;

}


