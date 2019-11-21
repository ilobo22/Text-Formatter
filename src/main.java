
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		primaryStage.setTitle("Text Formatter");

		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

		TextArea preview = new TextArea();
		TextArea errorLog = new TextArea();
		Button button1 = new Button("Load");
		Button button2 = new Button("Save as");

		button1.setOnAction(e -> {

			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			try {

				String strCurrentLine;
				FileReader file = new FileReader(selectedFile.getAbsolutePath());
				BufferedReader br = new BufferedReader(file);
				while ((strCurrentLine = br.readLine()) != null) {

					preview.appendText(strCurrentLine);
				}

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				errorLog.appendText("File not Found.");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				errorLog.appendText("File is not a .txt file.");
			}
		});

		button2.setOnAction(e -> {


			// Show save file dialog
			File file = fileChooser.showSaveDialog(primaryStage);


		});

		HBox hbox1 = new HBox(button1, button2);

		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(50);

		VBox vbox = new VBox();

		vbox.setSpacing(30);

		Label label = new Label("Preview");
		Label label1 = new Label("ErrorLog");

		HBox hbox = new HBox(new VBox(label, preview));
		HBox hbox2 = new HBox(new VBox(label1, errorLog));
		hbox.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);

		ObservableList list = vbox.getChildren();

		// Adding all the nodes to the observable list (HBox)
		list.addAll(hbox1, hbox, hbox2);

		Scene scene = new Scene(vbox, 600, 500);

		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
