package KrkrDataLoader.core;

import KrkrDataLoader.config.Config;
import com.google.gson.JsonElement;

public class Dialogue
		extends KrkrData
{
	public String speaker = Config.default_speaker;
	public String content = Config.default_content;
	private Voice voice = null;
	
	public Dialogue(String name)
	{
		super(name);
	}
	
	public Dialogue(String name, JsonElement data)
	{
		super(name, data);
		speaker = Config.getSingleConfig("speaker").getValueAsJsonPrimitive(data).toString();
		content = Config.getSingleConfig("content").getValueAsJsonPrimitive(data).toString();
		
		JsonElement voiceElement = Config.getSingleConfig("voice").getValueAsJsonPrimitive(data);
		if(! voiceElement.isJsonNull())
		{
			voice = new Voice("voice", voiceElement.toString());
			setChild(voice);
		}
	}
	
	public void play()
	{
		if(voice != null)
		{
			voice.play();
		}
	}
	
	public void stop()
	{
		if(voice != null)
		{
			voice.stop();
		}
	}
}
