package KrkrDataLoader.gui.controller;

import KrkrDataLoader.core.KrkrData;
import KrkrDataLoader.core.KrkrScenes;
import KrkrDataLoader.core.KrkrUtils;
import KrkrDataLoader.gui.interfaces.*;
import KrkrDataLoader.gui.window.KrkrSceneWindow;
import com.google.gson.JsonElement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class KrkrBaseController
		implements Initializable
{
	
	protected static List<OpenFileInterface> openFileInterfaces = new ArrayList<>();
	
	protected static List<LogMessageInterface> logMessageInterfaces = new ArrayList<>();
	
	protected static List<ShowContentInterface> showContentInterfaces = new ArrayList<>();
	
	protected static List<ShowStringInterface> showStringInterfaces = new ArrayList<>();
	
	protected static List<SelectSceneTreeItemInterface> selectSceneTreeItemInterfaces = new ArrayList<>();
	
	@FXML
	protected MenuItem openFile;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
	
	}
	
	public void openSceneFile(ActionEvent actionEvent)
	{
		try
		{
			FileChooser fileChooser = new FileChooser();
			
			fileChooser.setTitle("Open Scene Data");
			
			fileChooser.setInitialDirectory(new File("KrkrDataLoader/src/test"));
			
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Scene File", "*.ks.json"), new FileChooser.ExtensionFilter("??", "*.txt"));
			
			File file = fileChooser.showOpenDialog(KrkrSceneWindow.primaryStage);
			
			for(OpenFileInterface openFileInterface: openFileInterfaces)
			{
				openFileInterface.handleOpenFile(file);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public static void logMessage(String message)
	{
		for(LogMessageInterface logMessageInterface: logMessageInterfaces)
		{
			logMessageInterface.handleLogMessage(message);
		}
	}
	
	public static void showContent(File file)
	{
		try
		{
			KrkrScenes scenes = new KrkrScenes(KrkrUtils.loadJsonFile(file));
			
			showContent(scenes);
		}
		catch(Throwable e)
		{
			logMessage("Load Scene Error: " + e);
		}
	}
	
	public static void showContent(KrkrData data)
	{
		for(ShowContentInterface showContentInterface: showContentInterfaces)
		{
			showContentInterface.handleShowContent(data);
		}
	}
	
	public static void showJson(JsonElement data)
	{
		for(ShowStringInterface showStringInterface:showStringInterfaces)
		{
			showStringInterface.handleShowString(data);
		}
	}
	
}
