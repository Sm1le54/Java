package kursovaya;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Основной класс приложения.
 *
 * @author Prozorov Vladislav
 * @version 1.0
 */
public class Main extends Application {
    /**
     * Точка входа в приложение.
     *
     * @param stage  сцена приложения
     * @throws Exception  если произошла ошибка при запуске приложения
     */
    @Override
    public void start(Stage stage) throws Exception {
        Button chooseFileButton = new Button("Выбрать .class файл");
        chooseFileButton.setStyle("-fx-text-fill: black;");
        Label resultsLabel = new Label("");

        chooseFileButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Выберите .class файл");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Class Files", "*.class"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            if (selectedFile != null) {
                try {
                    Counter.display(selectedFile, resultsLabel);
                } catch (IOException e) {
                    resultsLabel.setText("Ошибка при анализе файла: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });

        VBox vbox = new VBox(10, chooseFileButton, resultsLabel);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.setStyle("-fx-background-color: black;");
        resultsLabel.setStyle("-fx-text-fill: white;");

        Scene scene = new Scene(vbox, 400, 200);

        stage.setScene(scene);
        stage.setTitle("Class Analyzer");
        stage.show();
    }

    /**
     * Точка входа в приложение.
     *
     * @param args  аргументы командной строки
     */
    public static void main(String[] args) {
        launch(args);
    }
}
