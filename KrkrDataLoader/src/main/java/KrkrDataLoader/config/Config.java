package KrkrDataLoader.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import KrkrDataLoader.core.KrkrUtils;

public class Config
{
	private static boolean is_init = false;
	
	public static SingleConfig ScenesNameConfig = null;
	public static SingleConfig SceneNameConfig = null;
	public static SingleConfig SceneConfig = null;
	public static SingleConfig DialoguesConfig = null;
	public static SingleConfig VoiceConfig = null;
	public static SingleConfig ContentConfig = null;
	public static SingleConfig SpeakerConfig = null;
	
	private static Map<String,SingleConfig> single_config_map = new HashMap<>();
	
	public static boolean isInit() { return is_init; }
	
	public static SingleConfig getSingleConfig(String name) { return single_config_map.get(name); }
	
	public static void setSingleConfig(SingleConfig config) { single_config_map.put(config.name, config); }
	
	public static void loadFromJson(JsonObject data)
	{
		single_config_map = new HashMap<>();
		
		for(Map.Entry<String,JsonElement> json_config: data.entrySet())
		{
			List<Object> fields = new ArrayList<>();
			for(JsonElement field: json_config.getValue().getAsJsonArray())
			{
				if(field.getAsJsonPrimitive().isString()) { fields.add(field.getAsString()); }
				else if(field.getAsJsonPrimitive().isNumber()) { fields.add((Integer) field.getAsInt()); }
			}
			//setSingleConfig(new SingleConfig(json_config.getKey(), fields));
			SingleConfig singleConfig = new SingleConfig(json_config.getKey(), fields);
			
			switch(json_config.getKey())
			{
				case "scenes_name":
					ScenesNameConfig = singleConfig;
					break;
				case "scene_label":
					SceneNameConfig = singleConfig;
					break;
				case "scene":
					SceneConfig = singleConfig;
					break;
				case "dialogues":
					DialoguesConfig = singleConfig;
					break;
				case "speaker":
					SpeakerConfig = singleConfig;
					break;
				case "content":
					ContentConfig = singleConfig;
					break;
				case "voice":
					VoiceConfig = singleConfig;
					break;
			}
			
			setSingleConfig(singleConfig);
		}
		
		is_init = true;
	}
	
	public static void loadFromJson(String path)
	throws Throwable
	{
		loadFromJson(KrkrUtils.loadJsonFile(path));
	}
	
	public static void loadFromJson(File file)
	throws Throwable
	{
		loadFromJson(KrkrUtils.loadJsonFile(file));
	}
	
}
