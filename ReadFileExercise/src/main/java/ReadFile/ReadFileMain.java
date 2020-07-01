/**
 * Contributor: Peiqi Wang
 * Github: https://github.com/pwang53
 * Date: 06/30/2020
 *
 * Description:
 *      1. Write a program to read a file from a location
 *      2. Find all the words that appeared more than 5 times and are more than 3 characters in length.
 *      3. Populate a arraylist with all those words after reversing the words
 *      4. Iterate the arraylist and print.
 **/

package ReadFile;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.File;
import java.net.URISyntaxException;
import javafx.event.EventHandler;
import java.util.ArrayList;

public class ReadFileMain extends Application {
    private static String message;
    private static String result;
    private static myFileReader f;
    private static ArrayList<String> list;
    private static String path;
    private static File file;
    private Scene scene;
    private BorderPane root;
    private Button btn_generate;
    private Button btn_exit;
    private Text txt_display;
    private Text txt_array;
    private VBox vBox_message;
    private HBox hBox_button;
    private VBox vBox_display;

    public static void main(String[] args) throws URISyntaxException {

        // The way to get current file path
        file = new File(ReadFileMain.class.getProtectionDomain().getCodeSource().getLocation().toURI());

        // Get the parent file path and add the specific file to open
        // Here we want a file name:  myText.txt
        path = file.toURI().getPath();
        path = path.substring(0, path.lastIndexOf("/"));
        String url = path + "/myText.txt";

        f = new myFileReader(url);
        message = f.getMessage();
        // Call open file and count the number of specific words we want
//        f.openFile();
//
//        ArrayList<String> list = f.getWordList();
//
//        System.out.println(list);

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        setUpWindow(primaryStage);

        buttonClick(primaryStage);
    }

    // Set up the window
    private void setUpWindow(Stage primaryStage) {
        primaryStage.setTitle("File Reader");
        root = new BorderPane();
        btn_generate = new Button("Read File!");
        btn_generate.setMinWidth(100);
        btn_exit = new Button("Exit");
        btn_exit.setMinWidth(100);
        txt_display = new Text("Welcome to my File Reader! You are on the path: \n\n" + path
                + "\n\nPlease put the file named \"myText.txt\" under the same root of .jar file");
        txt_array = new Text();
        vBox_message = new VBox(10, txt_display, txt_array);
        vBox_message.setAlignment(Pos.CENTER);
        hBox_button = new HBox(20, btn_generate,btn_exit);
        hBox_button.setAlignment(Pos.CENTER);
        vBox_display = new VBox(40, vBox_message,hBox_button);
        vBox_display.setAlignment(Pos.CENTER);
        root.setCenter(vBox_display);
        scene = new Scene(root, 600,400);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Set up click action for buttons
    private void buttonClick(final Stage primaryStage) {
        btn_generate.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                readFile();
                txt_display.setText(message);
                txt_array.setText(result);
                result = "";
            }
        });

        btn_exit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                primaryStage.close();
            }
        });
    }

    private void readFile() {
        f.openFile();
        list = f.getWordList();
        message = f.getMessage();
        String listString = "All Words appear more than 5 times and the length is more than 3: \n";

        if (list.size() < 1) {
            listString += "**null**";
        } else {
            for (String s : list) {
                listString += "\""+ s + "\"\t";
            }
        }
        result = listString;
    }
}
