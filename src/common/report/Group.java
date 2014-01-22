package common.report;

import java.util.ArrayList;
import java.util.List;

public class Group {
	private List<Column> c = new ArrayList<Column>();

	public Group(Column c) { 
		this.c.add(c);
	}
	
	public Group addColumn(Column c){
		this.c.add(c);
		return this;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(" group by ");
		for(Column cc:c){
			buf.append(cc.toString());
			buf.append(",");
		}
		buf.deleteCharAt(buf.lastIndexOf(","));
		return buf.append(" ").toString();
	}
}
