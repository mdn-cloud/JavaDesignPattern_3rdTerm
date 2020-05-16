package lab2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Lab2 extends Application {

	private double width = 800, height = 600;

	private String title = "Chatter";
	private BorderPane root;
	private ObservableList<String> connections;
	private Alert alert;
	private TextArea chatBox;
	private TextField textField;

	@Override
	public void init() throws Exception {

		System.out.println("init method");

		connections = FXCollections.observableArrayList("1", "2", "3");
		root = new BorderPane();
		root.setTop(createOptionsBar());
		root.setBottom(createStatusBar());
		root.setCenter(createChatBox());
		root.setRight(createFriendList());

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("start method");
		alert = new Alert(AlertType.INFORMATION);
		Scene scene = new Scene(root, width, height, Color.LIGHTPINK);
		primaryStage.setScene(scene);
		primaryStage.setTitle(title);
		primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
			if (KeyCode.ESCAPE == event.getCode()) {
				primaryStage.hide();
			}
		});
		primaryStage.show();
		textField.requestFocus();
	}

	@Override
	public void stop() throws Exception {
		System.out.println("stop method");
	}

	public Region createFriendList() throws Exception {
		ListView<String> listView = new ListView<>(connections);

		return listView;

	}

	private Region createChatBox() {
		chatBox = new TextArea();
		chatBox.setEditable(false);
		return chatBox;
	}

	private Region createOptionsBar() {
		MenuItem save = new MenuItem("save");
		SeparatorMenuItem sepFile = new SeparatorMenuItem();
		MenuItem exit = new MenuItem("exit");
		exit.setOnAction(e -> Platform.exit());
		MenuItem info = new MenuItem("info");
		info.setOnAction(e -> {
			alert.setTitle("Information");
			alert.setHeaderText("Developer");
			alert.setContentText("Mukta D.\rdebn0003@algonquincollegelive.com");
			alert.show();
		});

		Menu file = new Menu("File");
		file.getItems().addAll(save, sepFile, exit);

		Menu help = new Menu("Help");
		help.getItems().addAll(info);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(file, help);

		return menuBar;
	}

	private Region createStatusBar() {
		textField = new TextField();
		textField.setPrefHeight(50);

		Button send = new Button("Send");
		send.setPrefHeight(50);

		EventHandler<ActionEvent> postChat = (ActionEvent) -> {
			StringBuilder builder = new StringBuilder(System.lineSeparator());

			DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
			LocalDateTime localTime = LocalDateTime.now();
			String time = localTime.format(formatter);

			builder.append(time);
			builder.append(">> ");
			builder.append(textField.getText());

			chatBox.appendText(builder.toString());
			textField.clear();

		};

		send.setOnAction(postChat);
		textField.setOnAction(postChat);
		HBox.setHgrow(textField, Priority.ALWAYS);
		Label status = new Label("Status: My World is here!!");
		VBox vbox = new VBox();
		vbox.getChildren().addAll(new ToolBar(textField, send), new ToolBar(status));
		return vbox;

	}

	public static void main(String[] args) {
		System.out.println("main method");
		launch(args);
	}

}