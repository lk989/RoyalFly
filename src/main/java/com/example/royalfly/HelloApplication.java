package com.example.royalfly;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDate;



/*
 * change city
 * matches cities
 * spaces between from-to and what is under it
 */

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        /*
        *
        *
        *
                                        HOME PAGE
        *
        *
        *
        */

        //-----------------------------------background image-----------------------------------------
        Image background = new Image("https://cdn.dribbble.com/users/35381/screenshots/3615473/media/d615db0b04ed048778d1dc60d052a5f2.png");
        ImageView showBackground = new ImageView(background);

        //-----------------------------------sign up / sign in buttons-----------------------------------------
        Label signInButtonHomepage = new Label("Sign In");
        signInButtonHomepage.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        signInButtonHomepage.setUnderline(true);
        signInButtonHomepage.setTextFill(Color.web("#9494d1"));
        Label signUpButtonHomepage = new Label("Sign Up");
        signUpButtonHomepage.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        signUpButtonHomepage.setUnderline(true);
        signUpButtonHomepage.setTextFill(Color.web("#9494d1"));

        //hbox container for sign in - sign up buttons
        HBox signHBox = new HBox(15);
        signHBox.setPadding(new Insets(10));
        signHBox.setTranslateX(650);
        signHBox.getChildren().addAll(signInButtonHomepage, signUpButtonHomepage);

        //-----------------------------------ROYAL FLY-----------------------------------------
        Text title = new Text("Royal Fly");
        title.setFont(Font.font("Impact", FontWeight.BOLD, FontPosture.REGULAR, 80));
        title.setFill(Color.web("#9494d1"));
        title.setTranslateX(100);

        //-----------------------------------search box-----------------------------------------
        //radio buttons
        RadioButton oneWayRadioButton = new RadioButton("One way");
        oneWayRadioButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        oneWayRadioButton.setTextFill(Color.web("#79709e"));

        RadioButton roundTripRadioButton = new RadioButton("Round trip");
        roundTripRadioButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        roundTripRadioButton.setTextFill(Color.web("#79709e"));
        roundTripRadioButton.setSelected(true);

        //toggle group for radio buttons
        ToggleGroup tripTypeToggleGroup = new ToggleGroup();
        oneWayRadioButton.setToggleGroup(tripTypeToggleGroup);
        roundTripRadioButton.setToggleGroup(tripTypeToggleGroup);

        // hbox container for radio buttons
        HBox radioButtonsHBox = new HBox(10);
        radioButtonsHBox.getChildren().addAll(oneWayRadioButton, roundTripRadioButton);

        //list of cities
        ObservableList<String> cities = FXCollections.observableArrayList(
                "Abu Dhabi", "Dubai", "Sharjah", "Ajman", "Ras Al Khaimah", "Fujairah", "Umm Al Quwain", "Doha", "Al Wakrah", "Al Khor", "Al Rayyan", "Madinat ash Shamal", "Manama", "Muharraq", "Riffa", "Hamad Town", "Busaiteen", "Kuwait City", "Al Ahmadi", "Hawalli", "Jahra", "Farwaniya", "Dammam", "Riyadh", "Jeddah", "Mecca", "Medina", "Taif", "Khobar", "Dhahran", "Dammam", "Muscat", "Salalah", "Sohar", "Seeb", "Khor Fakkan", "Sharjah", "Ajman", "Ras Al Khaimah", "Fujairah", "Umm Al Quwain"
        );

        //from
        Label fromLabel = new Label("From:");
        fromLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        fromLabel.setTextFill(Color.web("#79709e"));

        ComboBox<String> fromComboBox = new ComboBox<>();
        fromComboBox.setItems(cities);
        fromComboBox.setEditable(true);
        fromComboBox.setPrefSize(200, 8);

        //to
        Label toLabel = new Label("To:");
        toLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        toLabel.setTextFill(Color.web("#79709e"));

        ComboBox<String> toComboBox = new ComboBox<>();
        toComboBox.setItems(cities);
        toComboBox.setEditable(true);
        toComboBox.setPrefSize(200, 8);

        //city not found alerts
        Label notFoundAlert = new Label("Sorry, city not found");
        notFoundAlert.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        notFoundAlert.setTextFill(Color.web("#cc2727"));
        notFoundAlert.setTranslateX(90);

        Label notFoundAlert2 = new Label("Sorry, city not found");
        notFoundAlert2.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        notFoundAlert2.setTextFill(Color.web("#cc2727"));
        notFoundAlert2.setTranslateX(340);

        //HBox for alerts
        HBox alertsHBox = new HBox(30);

        //user input for (from city) and event
        TextField fromCityInput = fromComboBox.getEditor();
        fromCityInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                fromComboBox.setItems(cities);
                alertsHBox.getChildren().remove(notFoundAlert);
                fromComboBox.hide();
            } else {
                ObservableList<String> inputCity = cities.filtered(item -> item.toLowerCase().startsWith(newValue.toLowerCase()));
                if (inputCity.isEmpty()) {
                    fromComboBox.hide();
                    if (!alertsHBox.getChildren().contains(notFoundAlert)) {
                        alertsHBox.getChildren().add(notFoundAlert);
                    }
                } else {
                    fromComboBox.setItems(inputCity);
                    alertsHBox.getChildren().remove(notFoundAlert);
                    fromComboBox.show();
                }
            }
        });

        //user input for (to city) and event
        TextField toCityInput = toComboBox.getEditor();
        toCityInput.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                toComboBox.setItems(cities);
                alertsHBox.getChildren().remove(notFoundAlert2);
                toComboBox.hide();
            } else {
                ObservableList<String> inputCity = cities.filtered(item -> item.toLowerCase().startsWith(newValue.toLowerCase()));
                if (inputCity.isEmpty()) {
                    toComboBox.hide();
                    if (!alertsHBox.getChildren().contains(notFoundAlert2)) {
                        alertsHBox.getChildren().add(notFoundAlert2);
                    }
                } else {
                    toComboBox.setItems(inputCity);
                    alertsHBox.getChildren().remove(notFoundAlert2);
                    toComboBox.show();
                }
            }
            /*
            if (newValue.equalsIgnoreCase(from_cities.getValue())){
                alerts.getChildren().add(matchAlert);
            }
            else{
                if (alerts.getChildren().contains(matchAlert)) {
                    alerts.getChildren().remove(matchAlert);
                }
            }
             */
        });

        //HBox for (from - to cities)
        HBox fromToCitiesHBox = new HBox(20);
        fromToCitiesHBox.getChildren().addAll(fromLabel, fromComboBox, toLabel, toComboBox);

        //adults
        Label adultLabel = new Label("Adults:");
        adultLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        adultLabel.setTextFill(Color.web("#79709e"));

        TextField adultsNumber = new TextField();
        adultsNumber.setPrefColumnCount(2);
        adultsNumber.setPromptText("1");

        //children
        Label childrenLabel = new Label("Children:");
        childrenLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        childrenLabel.setTextFill(Color.web("#79709e"));

        TextField childrenNumber = new TextField();
        childrenNumber.setPrefColumnCount(2);
        childrenNumber.setPromptText("1");

        //date
        Label fromDateLabel = new Label("From date:");
        fromDateLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        fromDateLabel.setTextFill(Color.web("#79709e"));

        DatePicker fromDatePicker = new DatePicker();
        fromDatePicker.setPrefSize(140, 10);

        Label toDateLabel = new Label("To date:");
        toDateLabel.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        toDateLabel.setTextFill(Color.web("#79709e"));

        DatePicker toDatePicker = new DatePicker();
        toDatePicker.setPrefSize(140, 10);

        LocalDate today = LocalDate.now();
        fromDatePicker.setDayCellFactory(getDayCellFactory(today));
        fromDatePicker.setOnAction(e -> {
            LocalDate selectedDate = fromDatePicker.getValue();
            toDatePicker.setDayCellFactory(getDayCellFactory(selectedDate));
        });

        //search button
        Button searchButton = new Button("Search");
        searchButton.setTranslateX(320);
        searchButton.setTextFill(Color.web("#79709e"));
        searchButton.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 14));
        searchButton.setStyle("-fx-background-color: white;");

        //container for trip information
        HBox tripInformationHBox = new HBox(10);
        tripInformationHBox.getChildren().addAll(adultLabel, adultsNumber, childrenLabel, childrenNumber, fromDateLabel, fromDatePicker, toDateLabel, toDatePicker);

        //trip type events
        oneWayRadioButton.setOnAction(e -> {
            if(tripInformationHBox.getChildren().contains(toDatePicker))
                tripInformationHBox.getChildren().removeAll(toDateLabel, toDatePicker);
        });

        roundTripRadioButton.setOnAction(e -> {
            tripInformationHBox.getChildren().addAll(toDateLabel, toDatePicker);
        });

        //container for search box
        VBox searchBoxVBox = new VBox(20);
        searchBoxVBox.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7);");
        searchBoxVBox.setPadding(new Insets(20, 40, 20, 40));
        searchBoxVBox.setPrefWidth(800);
        searchBoxVBox.getChildren().addAll(radioButtonsHBox, fromToCitiesHBox, alertsHBox, tripInformationHBox, searchButton);

        //----------------------------------------home page containers ---------------------------------------------------
        // VBox container
        VBox homePageContainerVBox = new VBox(80);
        homePageContainerVBox.getChildren().addAll(signHBox, title, searchBoxVBox);

        //root
        Group homepageRoot = new Group(showBackground, homePageContainerVBox);

        //scene
        Scene homepage = new Scene(homepageRoot, 800, 600);

        //stage
        stage.setTitle("Home Page");
        stage.setScene(homepage);


        /*
        *
        *
        *
                                        SIGN IN / SIGN UP
        *
        *
        *
        */


        //-------------------------------------- sign up information  ------------------------------------------------
        Label fNameLabel = new Label("First name: ");
        fNameLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TextField fNameInput = new TextField();

        Label lNameLabel = new Label("Last name: ");
        lNameLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TextField lNameInput = new TextField();

        Label emailLabel = new Label("Email: ");
        emailLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TextField emailInput = new TextField();
        emailInput.setPromptText("yourname@example.com");

        Label phoneNumberLabel = new Label("Phone number: ");
        phoneNumberLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TextField phoneNumberInput = new TextField();

        Label passwordLabel = new Label("Password: ");
        passwordLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        PasswordField passwordInput = new PasswordField();

        Label passwordConirmationLabel = new Label("Re-enter password: ");
        passwordConirmationLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        PasswordField passwordConfirmationInput = new PasswordField();


        //-------------------------------------- sign in information  ------------------------------------------------
        Label emailLoginLabel = new Label("Email: ");
        emailLoginLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        TextField emailLoginInput = new TextField();
        emailLoginInput.setPromptText("yourname@example.com");

        Label passwordLoginLabel = new Label("Password: ");
        passwordLoginLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));

        PasswordField passwordLoginInput = new PasswordField();


        //-------------------------------------- sign in - sign up buttons  ------------------------------------------------
        Button signUpRegisterScene = new Button("Sign up");
        signUpRegisterScene.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));
        signUpRegisterScene.setScaleX(1.4);
        signUpRegisterScene.setScaleY(1.4);
        signUpRegisterScene.setStyle("-fx-background-color: Lavender; -fx-text-fill: black;");

        Button loginRegisterScene = new Button("Sign in");
        loginRegisterScene.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));
        loginRegisterScene.setScaleX(1.4);
        loginRegisterScene.setScaleY(1.4);
        loginRegisterScene.setStyle("-fx-background-color: Lavender; -fx-text-fill: black;");


        //-------------------------------------- HBox for sign in - sign up buttons  ------------------------------------------------
        HBox sigInUpHBox = new HBox();
        sigInUpHBox.getChildren().addAll(signUpRegisterScene, loginRegisterScene);
        sigInUpHBox.setTranslateX(310);
        sigInUpHBox.setTranslateY(120);
        sigInUpHBox.setSpacing(90);


        //-------------------------------------- required alert ------------------------------------------------
        Label requiredLabel = new Label();
        requiredLabel.setTranslateX(350);
        requiredLabel.setTranslateY(430);
        requiredLabel.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));


        //-------------------------------------- Grid Pane for sign up information ------------------------------------------------
        GridPane signUpInformation = new GridPane();
        signUpInformation.setAlignment(Pos.CENTER);

        signUpInformation.add(fNameLabel, 0, 0);
        signUpInformation.add(fNameInput, 1, 0);
        signUpInformation.add(lNameLabel, 0, 1);
        signUpInformation.add(lNameInput, 1, 1);
        signUpInformation.add(phoneNumberLabel, 0, 4);
        signUpInformation.add(phoneNumberInput, 1, 4);
        signUpInformation.add(emailLabel, 0, 3);
        signUpInformation.add(emailInput, 1, 3);
        signUpInformation.add(passwordLabel, 0, 5);
        signUpInformation.add(passwordInput, 1, 5);
        signUpInformation.add(passwordConirmationLabel, 0, 6);
        signUpInformation.add(passwordConfirmationInput, 1, 6);
        signUpInformation.setTranslateX(250);
        signUpInformation.setTranslateY(200);
        signUpInformation.setVgap(10);
        signUpInformation.setHgap(50);
        signUpRegisterScene.requestFocus();


        //-------------------------------------- Grid Pane for sign in information ------------------------------------------------
        GridPane signInInformation = new GridPane();
        signUpInformation.setAlignment(Pos.CENTER);

        signInInformation.add(emailLoginLabel, 1, 4);
        signInInformation.add(emailLoginInput, 2, 4);
        signInInformation.add(passwordLoginLabel, 1, 5);
        signInInformation.add(passwordLoginInput, 2, 5);
        signInInformation.setTranslateX(260);
        signInInformation.setTranslateY(200);
        signInInformation.setVgap(10);
        signInInformation.setHgap(30);
        loginRegisterScene.requestFocus();


        //-------------------------------------- submit button  ------------------------------------------------
        Button submitButton = new Button("submit");
        submitButton.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));
        submitButton.setStyle("-fx-background-color: Lavender; -fx-text-fill: black;");
        submitButton.setScaleX(1.4);
        submitButton.setScaleY(1.4);


        //-------------------------------------- back button ------------------------------------------------
        Button back = new Button("back");
        back.setFont(Font.font("Lucida Bright", FontWeight.BOLD, FontPosture.REGULAR, 12));
        back.setStyle("-fx-background-color: Lavender; -fx-text-fill: black;");
        back.setScaleX(1.2);
        back.setScaleY(1.2);


        //-------------------------------------- HBox for submit - back buttons ------------------------------------------------
        HBox submitBackHBox = new HBox();
        submitBackHBox.getChildren().addAll(back,submitButton);
        submitBackHBox.setTranslateX(310);
        submitBackHBox.setTranslateY(480);
        submitBackHBox.setSpacing(90);


        //-------------------------------------- register scene-----------------------------------------------
        ImageView showBackground2 = new ImageView(background);
        Group signinRoot = new Group(showBackground2, sigInUpHBox, submitBackHBox, requiredLabel);
        Scene signinScene = new Scene(signinRoot);


        //-------------------------------------- Mouse action for signup------------------------------------------------
        signUpRegisterScene.setOnMouseClicked(e -> {
            signinRoot.getChildren().remove(signUpInformation);
            signinRoot.getChildren().remove(signInInformation);
            signinRoot.getChildren().add(signUpInformation);
            submitButton.setOnMouseClicked(event -> {
                if (fNameInput.getText().isEmpty()) {
                    fNameInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                } else if (lNameInput.getText().isEmpty() && !fNameInput.getText().isEmpty()) {
                    lNameInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    fNameInput.setStyle("");
                } else if (emailInput.getText().isEmpty() && !fNameInput.getText().isEmpty() && !lNameLabel.getText().isEmpty()) {
                    emailInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    fNameInput.setStyle("");
                    lNameInput.setStyle("");
                } else if (phoneNumberInput.getText().isEmpty() && !emailInput.getText().isEmpty() && !fNameInput.getText().isEmpty() && !lNameLabel.getText().isEmpty()) {
                    phoneNumberInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    fNameInput.setStyle("");
                    lNameInput.setStyle("");
                    emailInput.setStyle(" ");
                } else if (passwordInput.getText().isEmpty() && !phoneNumberInput.getText().isEmpty() && !emailInput.getText().isEmpty() && !fNameInput.getText().isEmpty() && !lNameLabel.getText().isEmpty()) {
                    passwordInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    fNameInput.setStyle("");
                    lNameInput.setStyle("");
                    emailInput.setStyle(" ");
                    phoneNumberInput.setStyle(" ");
                } else if (passwordConfirmationInput.getText().isEmpty() && !phoneNumberInput.getText().isEmpty() && !passwordInput.getText().isEmpty() && !emailInput.getText().isEmpty() && !fNameInput.getText().isEmpty() && !lNameLabel.getText().isEmpty()) {
                    passwordConfirmationInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    fNameInput.setStyle("");
                    lNameInput.setStyle("");
                    emailInput.setStyle(" ");
                    passwordInput.setStyle(" ");
                    phoneNumberInput.setStyle(" ");
                } else if (!passwordInput.getText().matches(".\\d.") || !passwordInput.getText().matches(".[!@#$%&+\\:;<>?].*")) {
                    requiredLabel.setText("password must contain at least one digit and at least one special character");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    requiredLabel.setTranslateX(80);
                } else if (!passwordInput.getText().matches(passwordConfirmationInput.getText())) {
                    requiredLabel.setText("password does not match");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    requiredLabel.setTranslateX(200);
                } else if (phoneNumberInput.getText().length() == 9) {
                    requiredLabel.setText(" ");
                } else if (phoneNumberInput.getText().length() != 9) {
                    requiredLabel.setText("Invalid phone number ");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    requiredLabel.setTranslateX(200);
                } else {
                    fNameInput.setStyle("");
                    lNameInput.setStyle("");
                    emailInput.setStyle(" ");
                    passwordInput.setStyle(" ");
                    phoneNumberInput.setStyle(" ");
                    passwordConfirmationInput.setStyle(" ");
                    requiredLabel.setText(" ");
                }
            });
        });
        //-------------------------------------- Mouse action for sign in-----------------------------------------------
        loginRegisterScene.setOnMouseClicked(event -> {
            signinRoot.getChildren().remove(signUpInformation);
            signinRoot.getChildren().remove(signInInformation);
            signinRoot.getChildren().add(signInInformation);
            submitButton.setOnMouseClicked(e -> {
                if (emailLoginInput.getText().isEmpty()) {
                    emailLoginInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                } else if (passwordLoginInput.getText().isEmpty() && !emailLoginInput.getText().isEmpty()) {
                    passwordLoginInput.setStyle("-fx-border-color:red; -fx-border-width:1; -fx-border-radius:5;");
                    requiredLabel.setText("this field is required");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    emailLoginInput.setStyle("");
                } else if (!passwordLoginInput.getText().matches(".\\d.") || !passwordLoginInput.getText().matches(".[!@#$%&+\\:;<>?].*")) {
                    requiredLabel.setText("password must contain at least one digit and at least one special character");
                    requiredLabel.setStyle("-fx-text-fill:red;");
                    requiredLabel.setTranslateX(80);
                } else {
                    emailLoginInput.setStyle("");
                    passwordLoginInput.setStyle("");
                    requiredLabel.setText(" ");

                }
            });
        });


        signInButtonHomepage.setOnMouseClicked(e -> {
            if(signinRoot.getChildren().contains(signUpInformation))
                signinRoot.getChildren().remove(signUpInformation);
            signinRoot.getChildren().add(signInInformation);
            stage.setScene(signinScene);
        });
        signUpButtonHomepage.setOnMouseClicked(e -> {
            if(signinRoot.getChildren().contains(signInInformation))
                signinRoot.getChildren().remove(signInInformation);
            stage.setScene(signinScene);
            signinRoot.getChildren().add(signUpInformation);
        });
        back.setOnAction(e -> stage.setScene(homepage));




        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private Callback<DatePicker, DateCell> getDayCellFactory(LocalDate minDate) {
        return datePicker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setDisable(item.isBefore(minDate)); // Disable dates before the minimum date
            }
        };
    }
}