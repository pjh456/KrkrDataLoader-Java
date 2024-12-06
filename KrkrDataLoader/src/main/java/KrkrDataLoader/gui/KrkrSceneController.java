package KrkrDataLoader.gui;

import KrkrDataLoader.core.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class KrkrSceneController
		implements Initializable
{
	
	public static KrkrSceneController krkrSceneController;
	
	@FXML
	private MenuItem openFileItem;
	
	@FXML
	private TextFlow sceneTextViewer;
	
	@FXML
	private TextFlow commandTextViewer;
	
	@FXML
	private TreeView<KrkrData> sceneTreeViewer;
	
	public int index = 1;
	
	@Override
	public void initialize(URL url, ResourceBundle resourceBundle)
	{
		krkrSceneController = this;
		sceneTreeViewer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) ->
		{
			//logMessage(newValue.toString());
			clearDialogue();
			if(newValue.getValue() instanceof KrkrScenes)
			{
				loadDialogues((KrkrScenes) newValue.getValue());
			}
			else if(newValue.getValue() instanceof KrkrScene)
			{
				loadDialogues((KrkrScene) newValue.getValue());
			}
			else if(newValue.getValue() instanceof KrkrDialogue)
			{
				loadDialogue((KrkrDialogue) newValue.getValue());
			}
			else
			{
			
			}
		});
	}
	
	@FXML
	public void openSceneFile(ActionEvent event)
	{
		FileChooser fileChooser = new FileChooser();
		
		fileChooser.setTitle("Open Scene Data");
		
		fileChooser.setInitialDirectory(new File("KrkrDataLoader/src/test"));
		
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Scene File", "*.ks.json"), new FileChooser.ExtensionFilter("??", "*.txt"));
		
		viewSceneFile(fileChooser.showOpenDialog(KrkrSceneWindow.primaryStage));
	}
	
	public void viewSceneFile(File file)
	{
		try
		{
			logMessage("Loading File: " + file.getPath());
			
			KrkrScenes open_scene = new KrkrScenes(KrkrUtils.loadJsonFile(file));
			
			loadDialogues(open_scene);
//			for(KrkrData scene: open_scene.listChildren())
//			{
//				//logMessage("	Loading dialogues in Scene  " + scene.name);
//				if(scene.size() > 0)
//				{
//					for(KrkrData dialogue: scene.listChildren())
//					{
//						loadDialogue((KrkrDialogue) dialogue);
//					}
//				}
//			}
			
			//logMessage("Building Scene Tree...");
			buildSceneTree(open_scene);
			//sceneTextArea.setText(builder.toString());
		}
		catch(Throwable e)
		{
			logMessage("Loading File " + file.getPath() + " Error!");
		}
	}
	
	public void loadDialogue(KrkrDialogue dialogue)
	{
		sceneTextViewer.getChildren().add(new Text(dialogue.toString() + "\n"));
	}
	
	public void loadDialogues(KrkrScene scene)
	{
		if(scene.size() > 0)
		{
			for(KrkrData dialogue: scene.listChildren())
			{
				loadDialogue((KrkrDialogue) dialogue);
			}
		}
	}
	
	public void loadDialogues(KrkrScenes scenes)
	{
		for(KrkrData scene: scenes.listChildren())
		{
			loadDialogues((KrkrScene) scene);
		}
	}
	
	public void clearDialogue()
	{
		sceneTextViewer.getChildren().clear();
	}
	
	public void logMessage(String message)
	{
		commandTextViewer.getChildren().add(new Text(message + "\n"));
	}
	
	public void clearLog()
	{
		commandTextViewer.getChildren().clear();
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
