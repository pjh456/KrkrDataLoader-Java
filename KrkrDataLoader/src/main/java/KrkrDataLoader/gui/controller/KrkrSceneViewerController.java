package KrkrDataLoader.gui.controller;

import KrkrDataLoader.core.*;
import KrkrDataLoader.gui.interfaces.OpenFileInterface;
import KrkrDataLoader.gui.interfaces.SelectSceneTreeItemInterface;
import KrkrDataLoader.gui.interfaces.ShowContentInterface;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.File;

public class KrkrSceneViewerController extends KrkrBaseController
{
	@FXML
	private TextFlow sceneViewer;
	
	public KrkrSceneViewerController()
	{
		openFileInterfaces.add(new OpenFileInterface() {
			@Override
			public void handleOpenFile(File file)
			{
				try
				{
					KrkrScenes scenes = new KrkrScenes(KrkrUtils.loadJsonFile(file));
					viewSceneFile(scenes);
				}
				catch(Throwable e)
				{
					//logMessage("Load Scene Error: "+e);
					e.printStackTrace();
				}
			}
		});
		
		showContentInterfaces.add(new ShowContentInterface() {
			@Override
			public void handleShowContent(KrkrData data)
			{
				clearDialogue();
				loadDialogues(data);
			}
		});
	}
	
	public void viewSceneFile(KrkrData data)
	{
		clearDialogue();
		loadDialogues(data);
	}
	
	public void loadDialogue(KrkrDialogue dialogue)
	{
		sceneViewer.getChildren().add(new Text(dialogue.toString() + "\n"));
		//System.out.println(dialogue.toString());
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
	
	public void loadDialogues(KrkrData data)
	{
		if(data instanceof KrkrScenes)
		{
			loadDialogues((KrkrScenes) data);
		}
		else if(data instanceof KrkrScene)
		{
			loadDialogues((KrkrScene) data);
		}
		else if(data instanceof KrkrDialogue)
		{
			loadDialogue((KrkrDialogue) data);
		}
	}
	
	
	public void clearDialogue()
	{
		sceneViewer.getChildren().clear();
	}
	
}
