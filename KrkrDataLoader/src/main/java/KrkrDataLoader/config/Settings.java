package KrkrDataLoader.config;

import KrkrDataLoader.core.KrkrUtils;
import com.google.gson.JsonObject;

public class Settings
{
	
	public static String audio_suffix = ".ogg";
	
	// Default Data Configs
	public static String default_speaker;
	public static String default_content;
	public static String default_scene_title;
	
	// Default GUI Configs
	
	public static String default_window_title;
	public static String default_window_icon;
	
	public static void loadFromJson(String path) throws Exception
	{
		JsonObject obj = KrkrUtils.loadJsonFile(path);
		
		audio_suffix = obj.get("audio_suffix").getAsString();
		
		default_speaker = obj.get("speaker").getAsString();
		default_content = obj.get("content").getAsString();
		default_scene_title = obj.get("scene_title").getAsString();
		
		default_window_title = obj.get("window_title").getAsString();
		default_window_icon = obj.get("window_icon").getAsString();
	}
	
	
}
