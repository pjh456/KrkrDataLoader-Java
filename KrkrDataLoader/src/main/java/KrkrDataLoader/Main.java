package KrkrDataLoader;

import KrkrDataLoader.config.AutoPathLoader;
import KrkrDataLoader.config.Config;
import KrkrDataLoader.config.JsonPath;
import KrkrDataLoader.config.Settings;
import KrkrDataLoader.core.KrkrData;
import KrkrDataLoader.core.KrkrDialogue;
import KrkrDataLoader.core.KrkrScenes;
import KrkrDataLoader.core.KrkrUtils;
import KrkrDataLoader.gui.window.KrkrSceneWindow;
import javafx.application.Application;

import java.io.File;
import java.util.List;


public class Main
{
	public static void main(String[] args)
	throws Throwable
	{
		Settings.loadFromJson("KrkrDataLoader/src/main/resources/settings.json");
		
		String config_name = "KrkrDataLoader/src/test/configs.json";
		Config.loadFromJson(config_name);

//		Application.launch(KrkrSceneWindow.class, args);
		
		String data_name = "KrkrDataLoader/src/test/001・アーサー王ver1.07.ks.json";

		AutoPathLoader loader = new AutoPathLoader(data_name);

		System.out.println(loader.gotoChild(3).name);

		List<JsonPath> paths = loader.listChildren();

		for(JsonPath element:paths)
		{
			for(JsonPath childPath:element.getChild("label").listPath())
			{
				if(KrkrUtils.isPathInPath(childPath,element))
				{
					System.out.print("[");
					for(JsonPath path1:KrkrUtils.removeSamePath(childPath,element))
					{
						System.out.print(path1.name+",");
					}
					System.out.println("]");
				}
				//System.out.print(childPath.name+"->");
			}
			System.out.println();
		}
		
		//System.out.println(loader.gotoChild("firstLine"));
		
		//JsonPath jsonPath = new JsonPath(KrkrUtils.loadJsonFile(data_name), "root");
		
		//System.out.println(jsonPath.listChildren().get(1));
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
//				if(scene.size() > 0)
//				{
//					for(KrkrData dialogue: scene.listChildren())
//					{
//						System.out.println("	" + ( (KrkrDialogue) dialogue ).content);
//					}
//				}
//				else
//				{
//					System.out.println("	" + "null");
//				}
//			}
//		}
//		catch(Throwable e)
//		{
//			System.out.println("Load file error: " + e);
//		}
		
	}
}
