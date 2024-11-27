package KrkrDataLoader.core;

import KrkrDataLoader.config.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import jdk.jshell.execution.Util;

public class Scenes extends KrkrData
{
	public Scenes(JsonElement data) throws Throwable
	{
		super(Config.getSingleConfig("scenes_name").getValueAsJsonPrimitive(data).toString());
		
		JsonArray scene_array = Config.getSingleConfig("scene").getValueAsJsonArray(data);
		for(JsonElement object: scene_array)
		{
			setChild(new Scene(object));
		}
	}
	
	public Scenes(String path) throws Throwable
	{
		this(Utils.loadJsonFile(path));
	}
}
