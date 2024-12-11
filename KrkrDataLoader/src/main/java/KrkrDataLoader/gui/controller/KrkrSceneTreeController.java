package KrkrDataLoader.gui.controller;

import KrkrDataLoader.core.*;
import KrkrDataLoader.gui.interfaces.OpenFileInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class KrkrSceneTreeController
		extends KrkrBaseController
{
	@FXML
	private TreeView<KrkrData> sceneTreeViewer;
	
	public KrkrSceneTreeController()
	{
		openFileInterfaces.add(new OpenFileInterface()
		{
			@Override
			public void handleOpenFile(File file)
			{
				try
				{
					logMessage("Loading File Tree: " + file.getPath());
					
					KrkrScenes open_scenes = new KrkrScenes(KrkrUtils.loadJsonFile(file));
					buildSceneTree(open_scenes);
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
		sceneTreeViewer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
		{
			showContent(newValue.getValue());
		});
		super.initialize(url, resourceBundle);
	}
	
	public TreeItem<KrkrData> spawnSceneTree(KrkrDialogue dialogue)
	{
		return new TreeItem<>(dialogue);
	}
	
	public TreeItem<KrkrData> spawnSceneTree(KrkrScene scene)
	{
		TreeItem<KrkrData> root = new TreeItem<>(scene);
		
		for(KrkrData dialogue: scene.listChildren())
		{
			root.getChildren().add(spawnSceneTree((KrkrDialogue) dialogue));
		}
		
		return root;
	}
	
	public TreeItem<KrkrData> spawnSceneTree(KrkrScenes scenes)
	{
		TreeItem<KrkrData> root = new TreeItem<>(scenes);
		
		for(KrkrData scene: scenes.listChildren())
		{
			if(scene.size() > 0)
			{
				root.getChildren().add(spawnSceneTree((KrkrScene) scene));
			}
			else
			{
				root.getChildren().add(new TreeItem<>(scene));
			}
		}
		
		return root;
	}
	
	public void buildSceneTree(KrkrDialogue dialogue)
	{
		sceneTreeViewer.setRoot(spawnSceneTree(dialogue));
	}
	
	public void buildSceneTree(KrkrScene scene)
	{
		sceneTreeViewer.setRoot(spawnSceneTree(scene));
	}
	
	public void buildSceneTree(KrkrScenes scenes)
	{
		sceneTreeViewer.setRoot(spawnSceneTree(scenes));
	}
}
