package screen;

import java.util.ArrayList;

import control.DB_Controller;
import Database.SongIF;
import control.Credits;
import control.SongUIIF;
import control.CreditsIF;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Screen_1A extends GridPane implements ScreenInterface {

	private static Screen_1A instance;

	Button back;
	Label nowPlaying;
	ScrollPane songlist;
	TextField search;
	DB_Controller db;


	Screen_1A(){
		setConstraints();
		makeComponents();
	}

	public static ScreenInterface getInstance(){
		if(instance != null){
			return instance;
		}
		else{
			instance = new Screen_1A();
			return instance;
		}
	}

	private void setConstraints(){
		ColumnConstraints col0 = new ColumnConstraints();
		col0.setPercentWidth(20);
		ColumnConstraints col1 = new ColumnConstraints();
		col1.setPercentWidth(80);

		RowConstraints row0 = new RowConstraints();
		row0.setPercentHeight(10);
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(80);
		RowConstraints row2 = new RowConstraints();
		row2.setPercentHeight(10);

		this.getColumnConstraints().addAll(col0, col1);
		this.getRowConstraints().addAll(row0, row1, row2);

	}

	private void makeScale(Button b){
		b.setMinHeight(0);
		b.setMaxHeight(Double.MAX_VALUE);
		b.setMinWidth(0);
		b.setMaxWidth(Double.MAX_VALUE);
	}
	private void makeScale(TextField t){
		t.setMinHeight(0);
		t.setMaxHeight(Double.MAX_VALUE);
		t.setMinWidth(0);
		t.setMaxWidth(Double.MAX_VALUE);
	}

	private void makeComponents(){
		db = new DB_Controller();
		this.setOnKeyPressed(keyHandler);
		back = new Button("Back");
		makeScale(back);
		back.setOnAction(buttonHandler);
		back.getStyleClass().add("but");
		this.add(back,0,0);

		search = new TextField();
		search.setPromptText("Search...");
		search.setOnAction(textHandler);
		makeScale(search);
		search.getStyleClass().add("text");
		this.add(search,1,0);


		GridPane atoz = new GridPane();
		ColumnConstraints third = new ColumnConstraints();
		third.setPercentWidth(33);
		atoz.getColumnConstraints().addAll(third,third,third);
		for(int i=0; i<26; i++){
			RowConstraints arow = new RowConstraints();
			arow.setPercentHeight(4);
			atoz.getRowConstraints().add(arow);
			Label l = new Label(((char)('A'+i))+"");
			setHalignment(l, HPos.CENTER);
			l.getStyleClass().add("label");
			//AddInvisButtonsHere
			atoz.add(l , 1, i);
		}
		this.add(atoz,0,1);
		DB_Controller db= new DB_Controller();

		VBox list = new VBox();
		for(SongIF song: db.songs){
			SongUIIF item = SongUIIF.makeElement(song);
			list.getChildren().add((Node)item);
		}
		songlist = new ScrollPane(list);
		songlist.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		songlist.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.add(songlist,1,1);

		nowPlaying = new Label ("");
		setHalignment(nowPlaying, HPos.CENTER);
		this.add(nowPlaying, 0, 2, 2, 1);

	}
	EventHandler<ActionEvent> buttonHandler = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
        Stage temp = (Stage)((Node) event.getSource()).getScene().getWindow();
        if(event.getSource()==back){
        	temp.setScene(ScreenBuilder.buildScreen1());

        }
        temp.getScene().getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        temp.setFullScreen(true);
        temp.show();
        }

    };
    EventHandler<ActionEvent> textHandler = new EventHandler<ActionEvent>(){
    	@Override
    	public void handle(ActionEvent event){

    	}
    };



    EventHandler<InputEvent> keyHandler = new EventHandler<InputEvent>() {
        @Override
        public void handle(InputEvent event) {
        	CreditsIF control = Credits.getInstance();
        	if(event instanceof KeyEvent){
        		KeyEvent key = (KeyEvent) event;
        		switch(key.getCode()){
        			case X:
        				control.insertMoney(0.05);
        				break;
        			case C:
        				control.insertMoney(0.1);
        				break;
        			case V:
        				control.insertMoney(0.25);
        				break;
        			case B:
        				control.insertMoney(1.0);
        				break;
        			case N:
        				control.insertMoney(5.0);
        				break;
        			case ENTER:
        				if(search==null||!search.getText().isEmpty()){
        					String query = "title LIKE '%" + search.getText() + "';";
        					ArrayList<SongIF> songs=db.selectSongs(query,false);
        					VBox list = new VBox();
        					for(SongIF s : songs){
        						SongUIIF item = SongUIIF.makeElement(s);
        						list.getChildren().add((Node)item);
        					}
        					songlist=new ScrollPane(list);
        				}
        			default:
						break;

        		}
        	}
        }
    };
    public void updateNowPlaying(String playing){
    	nowPlaying.setText(playing);
    }


}
