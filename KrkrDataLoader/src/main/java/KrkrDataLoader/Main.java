package KrkrDataLoader;

import KrkrDataLoader.config.Settings;
import javafx.application.Application;


public class Main
{
	public static void main(String[] args) throws Throwable
	{
		Settings.loadFromJson("KrkrDataLoader/src/main/resources/settings.json");
		//Application.launch(KrkrConfigWindow.class, args);
	}
	/*
	public static void main(String[] args)
	{
		String config_name = "KrkrDataLoader/src/test/configs.json";
		String data_name = "KrkrDataLoader/src/test/001・アーサー王ver1.07.ks.json";
		
		try
		{
			Config.loadFromJson(config_name);
			KrkrScenes scenes = new KrkrScenes(data_name);
			for(KrkrData scene: scenes.listChildren())
			{
				System.out.println(scene.name);
				if(scene.size()>0)
				{
					for(KrkrData dialogue: scene.listChildren())
					{
						System.out.println("	"+((KrkrDialogue)dialogue).content);
					}
				}
				else
				{
					System.out.println("	"+"null");
				}
			}
		}
		catch(Throwable ignored)
		{
			System.out.println(ignored);
		}
	}
	 */
}
