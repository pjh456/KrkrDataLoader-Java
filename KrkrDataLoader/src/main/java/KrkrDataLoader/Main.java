package KrkrDataLoader;

import KrkrDataLoader.command.CommandAutoLoader;
import KrkrDataLoader.config.*;
import KrkrDataLoader.core.KrkrData;
import KrkrDataLoader.core.KrkrDialogue;
import KrkrDataLoader.core.KrkrScenes;
import KrkrDataLoader.core.KrkrUtils;
import KrkrDataLoader.gui.window.KrkrConfigWindow;
import KrkrDataLoader.gui.window.KrkrSceneWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.player.Player;

import javax.sound.sampled.*;
import javax.sound.sampled.spi.AudioFileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;


public class Main
{
	public static void main(String[] args)
	throws Throwable
	{
	
//		String audioPath = "KrkrDataLoader/src/test/mur001_001.ogg";
//		String audioPath = "KrkrDataLoader/src/test/Smiling-Swinging!!.mp3";
//		String audioPath = "KrkrDabtaLoader/src/test/testAudio1.wav";
		
//		File file = new File(audioPath);
//		InputStream inputStream = new FileInputStream(audioPath);
		
//		Player player = new Player(inputStream);
//		player.play();
//		System.out.println("Play!");
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.toURI().toURL());
//		Clip clip = AudioSystem.getClip();
//		clip.open(audioInputStream);
//		clip.start();
//		while(true)
//		{
//
//		}

		Settings.loadFromJson();

//		String config_name = "KrkrDataLoader/src/test/configs.json";
//		String config_name = "KrkrDataLoader/src/test/testConfig.json";
//		Config.loadFromJson(config_name);
//		Config.saveConfigs(config_name);
		
		Application.launch(KrkrSceneWindow.class, args);
		
//		File file = new File(voice_path);
//		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
//		Line dataLine = AudioSystem.getLine(new DataLine.Info(SourceDataLine.class,audioInputStream.getFormat()));
//		dataLine.open();
//		Clip clip = AudioSystem.getClip();
//		clip.open(audioInputStream);
//		clip.start();
//		System.out.println(clip.getMicrosecondLength());
//		Thread.sleep(clip.getMicrosecondLength()/1000);
//		clip.stop();

//		String data_name = "KrkrDataLoader/src/test/001・アーサー王ver1.07.ks.json";
//		String data_name = "KrkrDataLoader/src/test/001.始まりver1.07.ks.json";
//		String data_name = "KrkrDataLoader/src/test/004.父親との再会ver1.07.ks.json";
		
//		testOutputFileContent(data_name);
//		CommandAutoLoader.commandMain(data_name);

//		AutoPathLoader loader = new AutoPathLoader(data_name);
		
		//System.out.println(loader.gotoChild(3).name);
	}
	
	public static void playAudio()
	{
		String audioPath = "KrkrDataLoader/src/test/mur001_001.ogg";
//		String audioPath = "KrkrDataLoader/src/test/Smiling-Swinging!!.mp3";
		File file = new File(audioPath);
		Media media = new Media(file.toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(media);
		
		mediaPlayer.setOnReady(
			()->
			{
				System.out.println("Start");
				mediaPlayer.play();
			}
		);
		
		mediaPlayer.setOnEndOfMedia(
				()->
				{
					System.out.println("End");
					Platform.exit();
				}
		);
	}
	
	public static void testOutputFileContent(String data_name)
	{
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
						System.out.println("	" + ( (KrkrDialogue) dialogue ).toString());
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
