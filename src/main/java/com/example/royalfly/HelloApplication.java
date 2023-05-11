package com.example.royalfly;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image background = new Image("https://cdn.dribbble.com/users/35381/screenshots/3615473/media/d615db0b04ed048778d1dc60d052a5f2.png");
        ImageView show_background = new ImageView(background);

        Button signup = new Button("Sign up");
        signup.setFocusTraversable(false);
        Button signin = new Button("Sign in");
        signin.setFocusTraversable(false);
        HBox top = new HBox(10);
        top.getChildren().addAll(signup, signin);

        Text title = new Text("Royal Fly");
        title.setFont(Font.font("Stencil", 70));
        title.setFill(Color.web("#9494d1"));

        ObservableList<String> cities = FXCollections.observableArrayList(
                "Riyadh", "Jeddah", "Mecca", "Medina", "Dammam", "Taif", "Tabuk", "Buraidah", "Khamis Mushait",
                "Abha", "Al Khobar", "Hail", "Najran", "Yanbu", "Al Jubail", "Al Hofuf", "Jizan", "Al Qatif",
                "Ras Tanura", "Al Kharj"
        );
        Text from = new Text("From:");
        ComboBox<String> from_cities = new ComboBox<>();
        from_cities.setItems(cities);
        from_cities.setFocusTraversable(false);
        Text to = new Text("To:");
        ComboBox<String> to_cities = new ComboBox<>();
        to_cities.setItems(cities);
        to_cities.setFocusTraversable(false);
        Text adult = new Text("Adults:");
        TextField num_adult = new TextField();
        num_adult.setPromptText("1");
        num_adult.setPrefColumnCount(1);

        num_adult.setFocusTraversable(false);
        Text children = new Text("Children:");
        TextField num_children = new TextField();
        num_children.setPromptText("0");
        num_children.setPrefColumnCount(1);
        num_children.setFocusTraversable(false);
        DatePicker datePicker = new DatePicker();
        datePicker.setPrefSize(100, 10);
        RadioButton one_way = new RadioButton("One way");
        one_way.setFocusTraversable(false);
        RadioButton round_trip = new RadioButton("Round trip");
        round_trip.setFocusTraversable(false);
        ToggleGroup toggleGroup = new ToggleGroup();
        one_way.setToggleGroup(toggleGroup);
        round_trip.setToggleGroup(toggleGroup);
        HBox bottom = new HBox(10);
        bottom.getChildren().addAll(from,from_cities, to, to_cities, adult, num_adult, children,
                num_children, datePicker, one_way, round_trip);


        BorderPane pane = new BorderPane();
        pane.setCenter(title);
        pane.setTop(top);
        pane.setBottom(bottom);

        Group root = new Group(show_background, pane);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}