package KrkrDataLoader.core;

import KrkrDataLoader.config.Config;
import KrkrDataLoader.config.Settings;
import com.google.gson.JsonElement;

public class KrkrDialogue
		extends KrkrData
{
	public String speaker = Settings.default_speaker;
	public String content = Settings.default_content;
	private Voice voice = null;
	
	public KrkrDialogue(String name)
	{
		super(name);
	}
	
	public KrkrDialogue(String name, JsonElement data) throws Throwable
	{
		super(name);
		
		this.speaker = null;
		try
		{
			this.speaker = Config.getSingleConfig("speaker").getValueAsJsonPrimitive(data).toString();
		}
		catch(Throwable ignored)
		{
		
		}
		
		this.content = Config.getSingleConfig("content").getValueAsJsonPrimitive(data).toString();
		
		this.voice = null;
		try
		{
			voice = new Voice("voice", Config.getSingleConfig("voice").getValueAsJsonPrimitive(data).toString());
			setChild(voice);
		}
		catch(Throwable ignored)
		{
		
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
