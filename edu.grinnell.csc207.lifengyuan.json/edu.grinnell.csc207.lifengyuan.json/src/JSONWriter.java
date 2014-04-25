import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class JSONWriter {
	private boolean isCompress = false;
	
	public String write(JSONObject obj) {
		return write(0, obj);
	}
	
	public void setCompress(boolean compress){
		this.isCompress = compress;
	}

	@SuppressWarnings("unchecked")
	private String write(int level, JSONObject obj) {
		String str = "";
		if(!isCompress){
			str += "\n";
			for (int i = 0; i < level; i++) {
				str += "\t";
			}
		}
		str += "{";
		if(!isCompress){
			str += "\n";
		}

		Set<String> keySet = obj.getKeySet();
		
		for(Iterator<String> iter = keySet.iterator(); iter.hasNext();){
			String key = iter.next();
			
			if(!isCompress){
				for (int i = 0; i <= level; i++) {
					str += "\t";
				}
			}
			
			str += '"' + key + "\":";
			Object value = obj.get(key);
			if (value instanceof Hashtable) {
				str += write(level + 1, obj.getJSONObject(key));
			} else if (value instanceof String) {
				str += '"' + value.toString() + '"';
			} else if (value instanceof List) {
				str += getArrayString(level, (List<Object>)value);
			} else {
				str += value.toString();
			}
			
			if(iter.hasNext()){
				str += ",";
			}
			
			if(!isCompress)
				str += "\n";
		}
		
		if(!isCompress){
			for (int i = 0; i < level; i++) {
				str += "\t";
			}
		}
		str += "}";
		return str;
	}
	
	@SuppressWarnings("unchecked")
	private String getArrayString(int level, List<Object> list){
		String str = "[";
		for(Iterator<Object> iter = list.iterator(); iter.hasNext();){
			Object value = iter.next();
			if (value instanceof Hashtable) {
				str += write(level + 1, new JSONObject((Hashtable<String, Object>)value));
			} else if (value instanceof String) {
				str += '"' + value.toString() + '"';
			} else if (value instanceof List) {
				str += getArrayString(level, (List<Object>)value);
			} else {
				str += value.toString();
			}
			
			if(iter.hasNext()){
				str += ",";
			}
			
			if(!isCompress){
				str += " ";
			}
		}
		str += ']';
		return str;
	}
}
