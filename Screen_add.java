package Screen;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
/**
 * 
 * @author JamieBurchette
 * 
 * This method was created by Zachary Lorenzo, but logic was created by Jamie. credit where credit is due
 *
 */
public class Screen_add extends GridPane implements ScreenInterface {
	private static Screen_add instance;

	protected Screen_add() {
		setConstraints();
		makeComponents();
	}
	public static ScreenInterface getInstance(){
		if(instance != null){
			return instance;
		}
		else{
			instance = new Screen_add();
			return instance;
		}
	}
	private void setConstraints(){
		int columnNumber = 3;
		int rowNumber = 7;
		for(int i = 0; i < columnNumber;i++){
			ColumnConstraints col0 = new ColumnConstraints();		
			if(i == 1){
				col0.setPercentWidth(60);
			}
			else {
				col0.setPercentWidth(20);
			}
			this.getColumnConstraints().add(col0);
		}

		for(int i = 0; i<rowNumber;i++){
			RowConstraints row0 = new RowConstraints();
			row0.setPercentHeight(10);
			this.getRowConstraints().add(row0);
		}
	}
	private void makeComponents(){
		Button backButton = new Button("Back");
		this.add(backButton,0,0,1,1);
		GridPane.setHalignment(backButton, HPos.LEFT);
		GridPane.setValignment(backButton, VPos.TOP);
		backButton.setMinSize(0, 0);
		backButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		
		
		TextField title = new TextField();
		TextField artist = new TextField();
		TextField year = new TextField();
		
		title.setPromptText("Enter Title");
		artist.setPromptText("Enter Artist");
		year.setPromptText("Enter Release Year");
		
		this.add(title, 1,2,1,1);
		this.add(artist, 1,3,1,1);
		this.add(year, 1,4,1,1);
		
		Label pictureLabel = new Label("Picture:");
		Label fileLabel = new Label("File:"); 
		
		this.add(pictureLabel, 0,5,1,1);
		this.add(fileLabel, 0,6,1,1);
		GridPane.setHalignment(pictureLabel, HPos.RIGHT);
		GridPane.setHalignment(fileLabel, HPos.RIGHT);
		
		Button pictureButton = new Button("Browse");
		Button fileButton = new Button("Browse");
		
		pictureButton.setPrefSize(200, 50);
		pictureButton.setMinSize(0, 0);
		
		fileButton.setPrefSize(200, 50);
		fileButton.setMinSize(0, 0);
		
		this.add(pictureButton, 2,5,1,1);
		this.add(fileButton, 2,6,1,1);
	}

}