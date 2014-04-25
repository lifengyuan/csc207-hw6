import java.util.Hashtable;
import java.util.List;
import java.util.Set;


public class JSONObject {
	private Hashtable<String, Object> table;
	
	public JSONObject(){
		this.table = new Hashtable<String, Object>();
	}
	
	public JSONObject(Hashtable<String, Object> table){
		this.table = table;
	}
	
	public Object get(String key){
		return table.get(key);
	}
	
	public Number getNumber(String key){
		Object obj = get(key);
		if(obj instanceof Number){
			return (Number)obj;
		}else{
			return null;
		}
	}
	
	public Object remove(String key){
		return table.remove(key);
	}
	
	public String getString(String key){
		Object obj = get(key);
		if(obj instanceof String){
			return (String)obj;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getArray(String key){
		Object obj = get(key);
		if(obj instanceof List){
			return (List<Object>)obj;
		}else{
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject getJSONObject(String key){
		Object obj = get(key);
		if(obj instanceof Hashtable){
			return new JSONObject((Hashtable<String, Object>)obj);
		}else{
			return null;
		}
	}
	
	public Set<String> getKeySet(){
		return table.keySet();
	}
	
	@Override
	public String toString() {
		JSONWriter writer = new JSONWriter();
		return writer.write(this);
	}
}
