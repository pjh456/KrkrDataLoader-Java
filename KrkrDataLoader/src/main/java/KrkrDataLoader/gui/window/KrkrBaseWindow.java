package KrkrDataLoader.gui.window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class KrkrBaseWindow extends Application
{
	public static String fxmlPath = "file:KrkrDataLoader/src/main/resources/KrkrBaseWindow.fxml";
	
	public static String iconPath = "file:KrkrDataLoader/src/main/resources/KrkrDataLoaderIcon.png";
	
	public static String title = "KrkrDataLoader";
	
	public static Stage primaryStage;
	
	@Override
	public void start(Stage stage) throws Exception
	{
		primaryStage = stage;
		
		initFxml();
		
		initConstants();
		
		primaryStage.show();
	}
	
	private void initFxml() throws IOException
	{
		try
		{
			Parent root = FXMLLoader.load(new URL(fxmlPath));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
		}
		catch(Throwable e)
		{
			//System.out.println("Error!"+e);
			e.printStackTrace();
			//primaryStage.close();
		}
	}
	
	private void initConstants()
	{
		primaryStage.setTitle(title);
		
		primaryStage.getIcons().add(new Image(iconPath));
	}
}
