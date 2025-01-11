package KrkrDataLoader.gui.window;

import KrkrDataLoader.Main;
import javafx.application.Platform;
import javafx.stage.Stage;

public class KrkrConfigWindow extends KrkrBaseWindow
{
	static
	{
		fxmlPath = "file:KrkrDataLoader/src/main/resources/KrkrConfigWindow.fxml";
		title = "KrkrDataLoader Config Loader";
	}
	
	@Override
	public void start(Stage stage) throws Exception
	{
		super.start(stage);
		
		Platform.runLater(new Runnable() {
			@Override
			public void run()
			{
				System.out.println("OPen!");
				Main.playAudio();
			}
		});
	}
	
}
