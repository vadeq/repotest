package com.zws.functor.security;

import com.zws.functor.Functor;
import com.zws.functor.security.moral.Moral;
import com.zws.functor.util.FunctorIterator;
import com.zws.functor.util.FunctorVector;
import com.zws.session.SessionData;

public class Guard extends Functor {

  public void execute () throws Exception {
    Moral moral=null;
    setResult(null);
    FunctorIterator i = getMorals().copyIterator();
    while (i.hasNext()) {
      moral = (Moral)i.copyNext();
      moral.setUser(SessionData.getMember().getUser());
      if (!moral.check().booleanValue()) return;
    }
    setResult(getBinding());
  }

  public void add(Moral m) { morals.add(m); }
  public FunctorVector getMorals() { return morals; }

  public void bind(Object o ) { data=o; }
  public Object getBinding() { return data; }

  private FunctorVector morals = new FunctorVector();
  private Object data;
}
