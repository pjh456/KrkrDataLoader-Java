package KrkrDataLoader.core;

import KrkrDataLoader.config.Config;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

public class Scene
		extends KrkrData
{
	public List<Dialogue> dialogues;
	
	public Scene(String name, JsonElement data)
	{
		super(name, data);
		this.dialogues = new ArrayList<>();
		
		JsonArray dialogues_array = Config.getSingleConfig("dialogues").getValueAsJsonArray(data);
		for(JsonElement element: dialogues_array)
		{
			try
			{
				Dialogue new_dialogue = new Dialogue(Integer.toString(dialogues.size()), element);
				this.dialogues.add(new_dialogue);
				this.setChild(new_dialogue);
			}
			catch(Exception e)
			{
			
			}
		}
	}
}
