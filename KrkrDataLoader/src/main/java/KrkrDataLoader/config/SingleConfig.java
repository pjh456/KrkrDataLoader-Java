package KrkrDataLoader.config;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class SingleConfig
{
	public String name;				// 配置的唯一标识符
	public List<Object> fields;	// 相对于上一层级的相对路径
	
	public SingleConfig(String name)
	{
		this.name = name;
		this.fields = new ArrayList<>();
	}
	
	public JsonObject getValueAsJsonObject(JsonElement data)
	{
		JsonObject value = null;
		try
		{
			value = getValueAsJsonElement(data).getAsJsonObject();
		}
		catch(Throwable e)
		{
			System.out.println(e);
		}
		return value;
	}
	
	public JsonPrimitive getValueAsJsonPrimitive(JsonElement data)
	{
		JsonPrimitive value = null;
		try
		{
			value = getValueAsJsonElement(data).getAsJsonPrimitive();
		}
		catch(Throwable e)
		{
			System.out.println(e);
		}
		return value;
	}
	
	private JsonElement getValueAsJsonElement(JsonElement data) throws Throwable
	{
		JsonElement cache_element = data;
		for(Object field: fields)
		{
			if(field instanceof String)
			{
				if(cache_element.isJsonObject())
				{
					cache_element = cache_element.getAsJsonObject().get(field.toString());
				}
				else
				{
					// throw new Exception();
				}
			}
			else if(field instanceof Integer)
			{
				if(cache_element.isJsonArray())
				{
					cache_element = cache_element.getAsJsonArray().get((Integer)field);
				}
				else
				{
					// throw new Exception();
				}
			}
			else
			{
				// throw new Exception();
			}
		}
		return data;
	}
}
