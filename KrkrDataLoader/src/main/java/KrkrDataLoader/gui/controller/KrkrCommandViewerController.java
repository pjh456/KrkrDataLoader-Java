package KrkrDataLoader.gui.controller;

import KrkrDataLoader.gui.interfaces.LogMessageInterface;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class KrkrCommandViewerController
		extends KrkrBaseController
{
	@FXML
	private TextFlow commandViewer;
	
	public KrkrCommandViewerController()
	{
		logMessageInterfaces.add(new LogMessageInterface()
		{
			@Override
			public void handleLogMessage(String message)
			{
				logCommand(message);
			}
		});
	}
	
	public void logCommand(String message)
	{
		commandViewer.getChildren().add(new Text(message + "\n"));
	}
}
