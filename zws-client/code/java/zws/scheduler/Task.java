package zws.scheduler;

import zws.op.TimerTaskOp;
import zws.util.Named;

public abstract class Task extends TimerTaskOp implements Named {
	public abstract void execute() throws Exception;

	private String name;
	public String getName(){ return name; }
	public void setName(String s) { name = s; }

}

