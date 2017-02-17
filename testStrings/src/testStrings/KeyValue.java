package testStrings;

public class KeyValue 
{
	private String key;
	private Object value;
	
	public KeyValue(){}
	public KeyValue(String m_key,Object m_value)
	{
		this.setKey(m_key);
		this.setValue(m_value); 
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}

}
