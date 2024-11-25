package KrkrDataLoader.core;

import com.google.gson.JsonElement;

import java.util.Map;

public class KrkrData
{
	public String name;
	private JsonElement data;
	private Map<String, KrkrData> children_map;
	
	public KrkrData(String name)
	{
		this.name = name;
		data = null;
	}
	
	public KrkrData(String name, JsonElement data)
	{
		this.name = name;
		this.data = data;
	}
	
	public JsonElement getData()
	{
		return this.data;
	}
	
	public void setData(JsonElement data)
	{
		this.data = data;
	}
	
	public KrkrData getChild(String name)
	{
		return this.children_map.get(name);
	}
	
	public void setChild(KrkrData child)
	{
		this.children_map.put(child.name, child);
	}
}
