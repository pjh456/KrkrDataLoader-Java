package KrkrDataLoader.config;


import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.ArrayList;
import java.util.List;

public class SingleConfig
{
	public String name;                // 配置的唯一标识符
	// 由于提供对多种匹配模式的支持，这里是一个列表嵌套
	public List<List<Object>> fields_list;    // 相对于上一层级的相对路径
	
	public SingleConfig(String name)
	{
		this.name = name;
		this.fields_list = new ArrayList<>();
	}
	
	public SingleConfig(String name, List<Object> fields)
	{
		this.name = name;
		this.fields_list = new ArrayList<>();
		
		try
		{
			add_fields(fields);
		}
		catch(Throwable e)
		{
		
		}
	}
	
	public void add_fields(List<Object> fields) throws Throwable
	{
		for(Object field:fields)
		{
			if((!(field instanceof String)) && (!(field instanceof Integer)))
			{
				throw new Exception("Fields only support String and Interger!");
			}
		}
		this.fields_list.add(fields);
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
	public JsonArray getValueAsJsonArray(JsonElement data)
	{
		JsonArray value = null;
		try
		{
			value = getValueAsJsonElement(data).getAsJsonArray();
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
		JsonElement new_data = null;
		for(List<Object> fields: fields_list)
		{
			if(new_data != null)
			{
				break;
			}
			new_data = getValueFromList(data, fields);
		}
		if(new_data == null)
		{
			throw new NoSuchFieldError("Can't get data from fields!");
		}
		return new_data;
	}
	
	private JsonElement getValueFromList(JsonElement data, List<Object> fields)
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
					cache_element = cache_element.getAsJsonArray().get((Integer) field);
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
		return cache_element;
	}
}
