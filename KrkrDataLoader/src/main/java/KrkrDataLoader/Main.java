package KrkrDataLoader;

import KrkrDataLoader.config.Config;
import KrkrDataLoader.config.Settings;
import KrkrDataLoader.gui.window.KrkrSceneWindow;
import javafx.application.Application;


public class Main
{
	public static void main(String[] args) throws Throwable
	{
		Settings.loadFromJson("KrkrDataLoader/src/main/resources/settings.json");
		
		String config_name = "KrkrDataLoader/src/test/configs.json";
		Config.loadFromJson(config_name);
		
		Application.launch(KrkrSceneWindow.class, args);
		
//		String data_name = "KrkrDataLoader/src/test/001・アーサー王ver1.07.ks.json";
//
//		Config.loadFromJson(config_name);
//
//		File file = new File(data_name);
//		try
//		{
//			KrkrScenes scenes = new KrkrScenes(KrkrUtils.loadJsonFile(file));
//			for(KrkrData scene: scenes.listChildren())
//			{
//				System.out.println(scene.name);
//				if(scene.size()>0)
//				{
//					for(KrkrData dialogue: scene.listChildren())
//					{
//						System.out.println("	"+((KrkrDialogue)dialogue).content);
//					}
//				}
//				else
//				{
//					System.out.println("	"+"null");
//				}
//			}
//		}
//		catch(Throwable e)
//		{
//			System.out.println("Load file error!");
//		}
		
	}
}
