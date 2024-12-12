package KrkrDataLoader.config;

import com.google.gson.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonPath
{
	private static int defaultIndex = 0;
	
	private final JsonElement data;
	
	public String name;
	
	private boolean isInRow = false;
	
	private final Map<String,JsonPath> childMap = new LinkedHashMap<>();
	
	public JsonPath parent = null;
	
	public JsonPath(JsonElement data, String name, JsonPath parent, Boolean isInRow)
	{
		this.data = data;
		this.name = name;
		this.parent = parent;
		
		if(data instanceof JsonArray)
		{
			for(JsonElement childData: (JsonArray) data)
			{
				childMap.put(Integer.toString(childMap.size()), new JsonPath(childData, getDefaultName(), this, true));
			}
		}
		else if(data instanceof JsonObject)
		{
			Map<String,JsonElement> jsonMap = ( (JsonObject) data ).asMap();
			for(String childName: jsonMap.keySet())
			{
				childMap.put(childName, new JsonPath(jsonMap.get(childName), childName, this, false));
			}
		}
		else
		{
			// Ignore
			//System.out.println("Error in JsonPath!");
		}
	}
	
	public JsonPath(JsonElement data, String name, Boolean isInRow) { this(data, name, null, isInRow); }
	
	public JsonPath(JsonElement data, Boolean isInRow) { this(data, getDefaultName(), null, isInRow); }
	
	public JsonPath(JsonElement data, String name) { this(data, name, null, false); }
	
	public JsonPath(JsonElement data) { this(data, getDefaultName(), null, false); }
	
	public boolean isInRow() { return isInRow; }
	
	public List<JsonPath> listPath()
	{
		List<JsonPath> parentPathList = parent == null ? new ArrayList<>() : parent.listPath();
		parentPathList.add(this);
		return parentPathList;
	}
	
	public List<JsonPath> listChildren() { return childMap.values().stream().toList(); }
	
	public JsonPath getChild(String name) { return childMap.get(name); }
	
	public JsonPath getChild(int index) { return childMap.values().stream().toList().get(index); }
	
	@Override
	public String toString() { return name + ": " + data; }
	
	public static String getDefaultName() { return "defaultPath(" + Integer.toString(JsonPath.defaultIndex++) + ")"; }
}
