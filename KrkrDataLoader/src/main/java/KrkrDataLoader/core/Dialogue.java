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
	
	public Dialogue(String name, JsonElement data) throws Throwable
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
