package zws.qx;
import zws.context.StringContext;
import zws.qx.xml.QxXML;

public abstract class QxInvoker implements Qx {
  public abstract QxXML executeQx(StringContext ctx, QxXML XMLInstruction);  
}

/*
 * convert QxXML dataInstruction to FunctorTree
 * executeFunctor Tree (using returnStack to set args)
 * convert returnStack into QxXML response
 * return QxXML response
 * 
 * Functors will use reflection to invoke java methods (On objects or classes)
 */

/*
function:
  Tr method(T0 arg0...Tn argn);

at one point in time and in one place:
  - know arg0
  - know argn
  - know method name
  - arg0 must be available and retreived now
  - argn must be available and retreived now
  - method must invoked now 
  - response must be recieved now
*/

/*
benefits of a functor over a function
- instantiate function at one place and time 
- configure the function at another place and time 
- execute the function at another place and time 
- retrieve the response at at another place and time 

create the functor at one place and time
access and define arg0  at another place and another time
access and define argn  at yet another place and another time
can transfer the objec to another location
invoke the object at any later time and in another location.

  
  String
  Primitives
  Metadata
  ECO
  File

Functor:
  classs Op {
  
  void execute(){
    process arg0 ... argn
    and setResults
  }
  T0 get()
  set(T0 arg0)
  
  Tn get()
  set(Tn argn)
  
  Tr getResult();
}

*/

/*
Invoker:
  create a functor tree:
  
 <login username="admin" password="admin">
   <lock>
     <find name="abc.prt"/>
   </lock>
 </login>

 <login username="admin" password="admin">
   <lock name="abc.prt">
 </login>


login [username, passwod]
 - find [name]
 - lock [metadata]


login Functor
  - Lock Functor
    -  find functor
    
    lockfunctor:
      setName(Metadata m) { setName(m.getaName); }
      setName(String name)
*/
