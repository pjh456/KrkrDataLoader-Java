package KrkrDataLoader.gui;

import KrkrDataLoader.config.Settings;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class KrkrSceneWindow
		extends Application
{
	
	public static Stage primaryStage;
	
	
	public static void main(String[] args)
	{
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws IOException
	{
		KrkrSceneWindow.primaryStage = primaryStage;
		
		initFxml();
		
		//initScene();
		primaryStage.show();
	}
	
	
	private void initFxml() throws IOException
	{
		try
		{
			Parent root = FXMLLoader.load(new URL("file:KrkrDataLoader/src/main/resources/KrkrSceneWindow.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		}
		catch(Throwable e)
		{
			System.out.println("Error!"+e);
			//primaryStage.close();
		}
		
	}

}
