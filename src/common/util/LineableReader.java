package common.util;

import java.io.LineNumberReader;
import java.io.Reader;

public class LineableReader extends LineNumberReader{ 
	public LineableReader(Reader in) {
		super(in); 
	} 
}
