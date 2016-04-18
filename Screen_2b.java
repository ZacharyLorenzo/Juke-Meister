package Screen;
import javafx.scene.control.Button;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;


public class Screen_2b extends GridPane implements ScreenInterface{

	private static Screen_2b instance;

	private Screen_2b() {
		setConstraints();
		makeComponents();
	}

	/**
	 * The getInstance method returns the singleton instance of the screen
	 *  @return instance
	 */
	public static ScreenInterface getInstance(){
		if(instance != null){
			return instance;
		}
		else{
			instance = new Screen_2b();
			return instance;
		}
	}

	/**
	 * This method is a helper to the constructor of the class to make itself
	 * without having an extremely long constructor
	 *
	 */
	private void setConstraints(){

	
	     ColumnConstraints col1 = new ColumnConstraints();
	     col1.setPercentWidth(15);
	     ColumnConstraints col2 = new ColumnConstraints();
	     col2.setPercentWidth(15);
	     ColumnConstraints col3 = new ColumnConstraints();
	     col3.setPercentWidth(40);
	     ColumnConstraints col4 = new ColumnConstraints();
	     col4.setPercentWidth(30);
	     this.getColumnConstraints().addAll(col1,col2,col3,col4);

	     RowConstraints row1 = new RowConstraints();
	     row1.setPercentHeight(10);
	     RowConstraints row2 = new RowConstraints();
	     row2.setPercentHeight(10);
	     RowConstraints row3 = new RowConstraints();
	     row3.setPercentHeight(60);
	     RowConstraints row4 = new RowConstraints();
	     row4.setPercentHeight(30);
	    
	     this.getRowConstraints().addAll(row1,row2,row3,row4);

	}

	/**
	 * This method defines the components on the screen and adds them to it.
	 */
	private void makeComponents(){

		Button back = new Button("Back");
		//Add listener for the button later
		back.setMinSize(0, 0);
		back.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		this.add(back,0,0);
	
		

		GridPane gridpane2 = new GridPane();
		
		//another grid layout inside the grid
		RowConstraints subrow1 = new RowConstraints();
		subrow1.setPercentHeight(20);
		RowConstraints subrow2 = new RowConstraints();
		subrow2.setPercentHeight(20);
		RowConstraints subrow3 = new RowConstraints();
		subrow3.setPercentHeight(20);
		RowConstraints subrow4 = new RowConstraints();
		subrow4.setPercentHeight(20);
		RowConstraints subrow5 = new RowConstraints();
		subrow5.setPercentHeight(20);
	    gridpane2.getRowConstraints().addAll(subrow1,subrow2,subrow3,subrow4,subrow5);
		
	    ColumnConstraints subcol = new ColumnConstraints();
	    subcol.setPercentWidth(100);
	    gridpane2.getColumnConstraints().addAll(subcol);
	 
		Button single = new Button("Add Single");
		single.setMinSize(0, 0);
		single.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		single.setWrapText(true);
		gridpane2.add(single,0,0);
		
		
		Button album = new Button("Add Album");
		//Add listener for the button later
		album.setMinSize(0, 0);
		album.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		album.setWrapText(true);
		gridpane2.add(album,0,2);
		
		
		Button song = new Button("Remove Song");
		//Add listener for the button later
		song.setMinSize(0, 0);
		song.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		song.setWrapText(true);
		gridpane2.add(song,0,4);
		
		
		
		this.add(gridpane2,2,2);
		
	
	}//end makeComponents
	
}