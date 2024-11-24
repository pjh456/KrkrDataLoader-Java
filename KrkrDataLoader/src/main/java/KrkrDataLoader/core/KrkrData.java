package KrkrDataLoader.core;

import com.google.gson.JsonElement;

public class KrkrData
{
	public String name;
	private JsonElement data;
	
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
		return data;
	}
	
	public void setData(JsonElement data)
	{
		this.data = data;
	}
}
