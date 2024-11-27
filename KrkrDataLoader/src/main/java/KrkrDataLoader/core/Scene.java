package KrkrDataLoader.core;

import KrkrDataLoader.config.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class Scene
		extends KrkrData
{
	
	public Scene(JsonElement data) throws Throwable
	{
		super(Config.getSingleConfig("scene_label").getValueAsJsonPrimitive(data).toString());
		
		JsonArray dialogues_array = null;
		
		try
		{
			dialogues_array = Config.getSingleConfig("dialogues").getValueAsJsonArray(data);
			int index = 0;
			for(JsonElement element: dialogues_array)
			{
				this.setChild(new Dialogue(Integer.toString(index), element));
				index++;
			}
		}
		catch(Throwable ignored)
		{
		
		}
	}
}
