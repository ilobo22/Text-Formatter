import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends VBox{
	
	private StringFormatter formatString;
	
	public GUI(Stage primaryStage) throws Exception{
		
		primaryStage.setTitle("Text Formatter");

		FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);

        //generating buttons and text boxes
        TextArea preview = new TextArea();
        preview.setPrefWidth(570);
        preview.setWrapText(true);
        //preview.setMouseTransparent(true);
		TextArea errorLog = new TextArea();
		errorLog.setMouseTransparent(true);
		Button button1 = new Button("Load");
		Button button2 = new Button("Save as");
		preview.setEditable(false);
		errorLog.setEditable(false);

		button1.setOnAction(e -> {

			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			
			if(selectedFile == null) {
				errorLog.appendText("File is Empty.\n");
			}

			
			try {

				FileReader file = new FileReader(selectedFile.getAbsolutePath());
				BufferedReader br = new BufferedReader(file);
				formatString = new StringFormatter(br);
				preview.setFont(Font.font("Monospace", 11.1));
				preview.clear();
				preview.appendText(formatString.toString());
				file.close();
				br.close();


			} catch (NullPointerException e1) {
					errorLog.appendText("File not Found.\n");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} 
		});

		button2.setOnAction(e -> {

			
			// Show save file dialog
			File file = fileChooser.showSaveDialog(primaryStage);
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(file));
				bw.write(formatString.toString());
				bw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NullPointerException e1) {
				errorLog.appendText("Can't save empty file.\n");
			}
			


		});

		HBox hbox1 = new HBox(button1, button2);

		hbox1.setAlignment(Pos.CENTER);
		hbox1.setSpacing(50);

		VBox vbox = new VBox();

		vbox.setSpacing(30);

		Label label = new Label("Preview");
		Label label1 = new Label("ErrorLog");
		
		VBox vbox1 = new VBox(label, preview); 

		HBox hbox = new HBox(vbox1);
		HBox hbox2 = new HBox(new VBox(label1, errorLog));
		hbox.setAlignment(Pos.CENTER);
		hbox2.setAlignment(Pos.CENTER);

		ObservableList list = vbox.getChildren();

		list.addAll(hbox1, hbox, hbox2);

		Scene scene = new Scene(vbox, 700, 600);

		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
