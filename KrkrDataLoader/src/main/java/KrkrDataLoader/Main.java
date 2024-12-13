package KrkrDataLoader;

import KrkrDataLoader.config.*;
import KrkrDataLoader.core.KrkrData;
import KrkrDataLoader.core.KrkrDialogue;
import KrkrDataLoader.core.KrkrScenes;
import KrkrDataLoader.core.KrkrUtils;

import java.io.File;


public class Main
{
	public static void main(String[] args)
	throws Throwable
	{
		Settings.loadFromJson("KrkrDataLoader/src/main/resources/settings.json");
		
		String config_name = "KrkrDataLoader/src/test/configs.json";
//		Config.loadFromJson(config_name);

//		Application.launch(KrkrSceneWindow.class, args);
		
		String data_name = "KrkrDataLoader/src/test/001・アーサー王ver1.07.ks.json";

		AutoPathLoader loader = new AutoPathLoader(data_name);

		//System.out.println(loader.gotoChild(3).name);
		
		JsonPath rootPath = loader.getCurrentPath();
		JsonPath scenesNamePath = rootPath.getChild("name");
		JsonPath scenePath = rootPath.getChild("scenes");
		
		JsonPath singleScenePath = scenePath.getChild(2);
		
		JsonPath sceneNamePath = singleScenePath.getChild("label");
		JsonPath dialoguesPath = singleScenePath.getChild("texts");
		
		JsonPath singleDialoguePath = dialoguesPath.getChild(20);
		
		JsonPath speakerPath = singleDialoguePath.getChild(0);
		JsonPath contentPath = singleDialoguePath.getChild(2);
		JsonPath voicePath = singleDialoguePath.getChild(3).getChild(0).getChild("voice");
		
//		System.out.println(voicePath.toString());
		
		ConfigList configList = new ConfigList();
		configList.setScenesNamePath(scenesNamePath);
		configList.setScenePath(scenePath);
		configList.setSceneNamePath(sceneNamePath);
		configList.setDialoguesPath(dialoguesPath);
		configList.setSpeakerPath(speakerPath);
		configList.setContentPath(contentPath);
		configList.setVoicePath(voicePath);
		
		Config.loadFromConfigList(configList);
		
		System.out.println(Config.ScenesNameConfig.fields_list.get(0));
		System.out.println(Config.SceneNameConfig.fields_list.get(0));
		System.out.println(Config.SceneConfig.fields_list.get(0));
		System.out.println(Config.DialoguesConfig.fields_list.get(0));
		System.out.println(Config.SpeakerConfig.fields_list.get(0));
		System.out.println(Config.ContentConfig.fields_list.get(0));
		System.out.println(Config.VoiceConfig.fields_list.get(0));
		//List<JsonPath> paths = loader.listChildren();

		
//		for(JsonPath element:paths)
//		{
//			for(JsonPath childPath:element.getChild("label").listPath())
//			{
//				if(KrkrUtils.isPathInPath(childPath,element))
//				{
//					System.out.print("[");
//					for(JsonPath path1:KrkrUtils.removeSamePath(childPath,element))
//					{
//						System.out.print(path1.name+",");
//					}
//					System.out.println("]");
//				}
//				//System.out.print(childPath.name+"->");
//			}
//			System.out.println();
//		}
		
		//System.out.println(loader.gotoChild("firstLine"));
		
		//JsonPath jsonPath = new JsonPath(KrkrUtils.loadJsonFile(data_name), "root");
		
		//System.out.println(jsonPath.listChildren().get(1));
//
//
		File file = new File(data_name);
		try
		{
			KrkrScenes scenes = new KrkrScenes(KrkrUtils.loadJsonFile(file));
			for(KrkrData scene: scenes.listChildren())
			{
				System.out.println(scene.name);
				if(scene.size() > 0)
				{
					for(KrkrData dialogue: scene.listChildren())
					{
						System.out.println("	" + ( (KrkrDialogue) dialogue ).content);
					}
				}
				else
				{
					System.out.println("	" + "null");
				}
			}
		}
		catch(Throwable e)
		{
			//System.out.println("Load file error: " + e);
			e.printStackTrace();
		}
		
	}
}
