package KrkrDataLoader.gui.controller;

import KrkrDataLoader.config.JsonPath;
import KrkrDataLoader.core.*;
import KrkrDataLoader.gui.interfaces.OpenFileInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class KrkrJsonTreeController
		extends KrkrBaseController
{
	private Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	@FXML
	private TreeView<JsonPath> configTreeViewer;
	
	public KrkrJsonTreeController()
	{
		openFileInterfaces.add(new OpenFileInterface()
		{
			@Override
			public void handleOpenFile(File file)
			{
				try
				{
					logMessage("Loading File Tree: " + file.getPath());
					
					JsonPath path = new JsonPath(KrkrUtils.loadJsonFile(file));
					buildConfigTree(path);
				}
				catch(Throwable e)
				{
					logMessage("Loading File Tree " + file.getPath() + " Error!");
				}
			}
		});
	}
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		configTreeViewer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
		{
			if(newValue == null) return;
			showJson(newValue.getValue().getOriginalData());
		});
		super.initialize(url, resourceBundle);
	}
	
	public TreeItem<JsonPath> spawnJsonTree(JsonPath data)
	{
		TreeItem<JsonPath> root = new TreeItem<>(data);
		
		for(JsonPath child: data.listChildren())
		{
			if(child.size() > 0)
			{
				root.getChildren().add(spawnJsonTree(child));
			}
			else
			{
				root.getChildren().add(new TreeItem<>(child));
			}
		}
		
		return root;
	}
	
	public void buildConfigTree(JsonPath data)
	{
		configTreeViewer.setRoot(spawnJsonTree(data));
	}
}
