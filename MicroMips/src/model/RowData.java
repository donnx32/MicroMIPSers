package model;

import java.util.HashMap;
import java.util.Map;

public class RowData {
	private Map<Integer, Object> data = new HashMap<Integer, Object>();
	
	public Object getValueAtCol(int col) {
		if(data.containsKey(col)) {
			return data.get(col);
		}
		return null;
	}
	
	public void setValueAtCol(Object v, int col) {
		data.put(col,  v);
	}
}
