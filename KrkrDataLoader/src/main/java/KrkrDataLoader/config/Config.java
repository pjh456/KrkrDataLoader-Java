package KrkrDataLoader.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import KrkrDataLoader.core.Utils;

public class Config
{
	public static String audio_suffix = ".ogg";
	
	// Default Configs
	public static String default_speaker = "unknown";
	public static String default_content = "unknown";
	public static String default_title = "unknown";
	
	private static Map<String,SingleConfig> single_config_map = new HashMap<>();
	
	public static SingleConfig getSingleConfig(String name)
	{
		return single_config_map.get(name);
	}
	
	public static void setSingleConfig(SingleConfig config)
	
	{
		single_config_map.put(config.name, config);
	}
	
	public static void loadFromJson(String path) throws Throwable
	{
		single_config_map = new HashMap<>();
		
		JsonObject config_array = Utils.loadJsonFile(path);
		
		for(Map.Entry<String,JsonElement> json_config: config_array.entrySet())
		{
			List<Object> fields = new ArrayList<>();
			for(JsonElement field: json_config.getValue().getAsJsonArray())
			{
				//System.out.println(field.getAsString());
				if(field.getAsJsonPrimitive().isString())
				{
					fields.add(field.getAsString());
				}
				else if(field.getAsJsonPrimitive().isNumber())
				{
					fields.add((Integer) field.getAsInt());
				}
			}
			setSingleConfig(new SingleConfig(json_config.getKey(), fields));
		}
	}
	
	
}
