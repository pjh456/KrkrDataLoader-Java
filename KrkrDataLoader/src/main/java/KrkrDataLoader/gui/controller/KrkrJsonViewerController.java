package KrkrDataLoader.gui.controller;

import KrkrDataLoader.config.JsonPath;
import KrkrDataLoader.core.*;
import KrkrDataLoader.gui.interfaces.OpenFileInterface;
import KrkrDataLoader.gui.interfaces.ShowContentInterface;
import KrkrDataLoader.gui.interfaces.ShowStringInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class KrkrJsonViewerController extends KrkrBaseController
{
	
	@FXML
	private TextFlow jsonViewer;
	
	public KrkrJsonViewerController()
	{
		openFileInterfaces.add(new OpenFileInterface() {
			@Override
			public void handleOpenFile(File file)
			{
				try
				{
					//JsonPath root = new JsonPath();
					viewJsonFile(KrkrUtils.loadJsonFile(file).toString());
				}
				catch(Throwable e)
				{
					e.printStackTrace();
				}
			}
		});
		
		showStringInterfaces.add(new ShowStringInterface() {
			@Override
			public void handleShowString(JsonElement data)
			{
				clearJson();
				try
				{
					viewJsonFile(data.toString());
				}
				catch(Exception e)
				{
					logMessage(e.getMessage());
				}
			}
		});
	}
	
	public void viewJsonFile(String data)
	{
		clearJson();
		loadJson(data);
	}
	
	public void loadJson(String data)
	{
		jsonViewer.getChildren().add(new Text(data + "\n"));
	}
	
	public void clearJson()
	{
		jsonViewer.getChildren().clear();
	}
	
}
