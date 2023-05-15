package com.example.royalfly;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;
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
        });
        //-------------------------------------- Mouse action for sign in-----------------------------------------------
        loginRegisterScene.setOnMouseClicked(event -> {
            signinRoot.getChildren().remove(signUpInformation);
            signinRoot.getChildren().remove(signInInformation);
            signinRoot.getChildren().add(signInInformation);

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

        submitButton.setOnAction(event -> {
            if(signinRoot.getChildren().contains(signUpInformation)){
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
            }
            else {
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
            }
        });




        /*



                                            BOOKING SCENE




         */

        Pane pane1=new Pane();

        Rectangle rectangle=new Rectangle(800,100,Color.web("f0f1f5") );
        rectangle.setStroke(Color.web("#d4d9ef"));
        rectangle.setStrokeWidth(4);
        rectangle.setTranslateY(250);


        Image image=new Image("https://i.pinimg.com/564x/5a/82/87/5a8287ebc0d2e742817df57691581b21.jpg");
        ImagePattern pattern=new ImagePattern(image,800,200,800,800,false);


        Arc arc = new Arc(400,0, 600, 200, 195, 150);
        arc.setFill(pattern);
        pane1.getChildren().addAll(arc);



        VBox Friday =new VBox(3);
        Label day1=new Label("Saturday");
        day1.setFont(Font.font("Comic Sans MS",18));
        Label price1=new Label("Start from SAR \n 300 ");
        price1.setFont(Font.font("Elephant",12));
        price1.setStyle("-fx-border-color:#424242; -fx-border-width:1px");
        Friday.getChildren().addAll(day1,price1);


        VBox Thursday=new VBox(3);
        Label day2=new Label("Sunday");
        day2.setFont(Font.font("Comic Sans MS",18));
        Label price2=new Label("Start from SAR \n 300 ");
        price2.setStyle("-fx-border-color:#424242; -fx-border-width:1px");
        Thursday.getChildren().addAll(day2,price2);

        VBox Wednesday=new VBox(3);
        Label day3=new Label("Monday");
        day3.setFont(Font.font("Comic Sans MS",18));
        Label price3=new Label("Start from SAR \n 300 ");
        price3.setStyle("-fx-border-color:#424242; -fx-border-width:1px");
        Wednesday.getChildren().addAll(day3,price3);

        VBox Tuesday=new VBox(3);
        Label day4=new Label("Tuesday");
        day4.setFont(Font.font("Comic Sans MS",18));
        Label price4=new Label("Start from SAR \n 300 ");
        price4.setStyle("-fx-border-color:#424242; -fx-border-width:1px");
        Tuesday.getChildren().addAll(day4,price4);

        VBox Monday=new VBox(3);
        Label day5=new Label("Wednesday");
        day5.setFont(Font.font("Comic Sans MS",18));
        Label price5=new Label("Start from SAR \n 300 ");
        price5.setStyle("-fx-border-color:#424242; -fx-border-width:1px");


        Monday.getChildren().addAll(day5,price5);

        VBox Sunday=new VBox(3);
        Label day6=new Label("Thursday");
        day6.setFont(Font.font("Comic Sans MS",18));
        Label price6=new Label("Start from SAR \n 300 ");
        price6.setStyle("-fx-border-color:#424242; -fx-border-width:1px");
        Sunday.getChildren().addAll(day6,price6);

        VBox Saturday=new VBox(3);
        Label day7=new Label("Friday");
        day7.setFont(Font.font("Comic Sans MS",18));
        Label price7=new Label("Start from SAR \n 300 ");
        price7.setStyle("-fx-border-color:#424242; -fx-border-width:1px");
        Saturday.getChildren().addAll(day7,price7);


        HBox daysAndprice=new HBox(30);
        daysAndprice.getChildren().addAll(Saturday,Sunday,Monday,Tuesday,Wednesday,Thursday,Friday);
        daysAndprice.setTranslateX(5);
        daysAndprice.setTranslateY(260);


        // Trip 1
        HBox city=new HBox(60);
        Text fromCity=new Text("RUH");
        fromCity.setFont(Font.font("Century",15));
        Text to=new Text("Non-stop.1h45m");
        to.setFont(Font.font("Century",10));
        Text toCity=new Text("ABHA");
        toCity.setFont(Font.font("Century",15));
        city.setTranslateX(500);
        city.setTranslateY(380);
        city.getChildren().addAll(toCity,to,fromCity);

        HBox Time=new HBox(20);
        Text fromTime=new Text("3:40");
        fromTime.setFont(Font.font("Century",30));
        Image image1=new Image("file:///Users/abeertunkar/Desktop/College%20CS/Advanced%20Programming/project/Project/src/main/java/com/example/project/airline.png");
        ImageView imageView=new ImageView(image1);
        imageView.setFitHeight(20);
        imageView.setFitWidth(150);
        Text toTime=new Text("5:35");
        toTime.setFont(Font.font("Century",30));
        Time.setTranslateX(480);
        Time.setTranslateY(380);
        Time.getChildren().addAll(fromTime,imageView,toTime);

        Pane pane2=new Pane();
        Rectangle rectangle1= new Rectangle();
        Text Gust=new Text("Guest");
        Gust.setFont(Font.font("Impact",15));
        Gust.setUnderline(true);
        Gust.setTranslateY(333);
        Gust.setTranslateX(267);

        Text from1=new Text("FROM SRA");
        from1.setFont(Font.font("Elephant",9));
        from1.setTranslateY(365);
        from1.setTranslateX(260);

        Text pr=new Text("688.88");
        pr.setFont(Font.font("Tahoma",20));
        pr.setTranslateY(386);
        pr.setTranslateX(260);

        rectangle1.setHeight(90);
        rectangle1.setWidth(150);
        rectangle1.setTranslateY(310);
        rectangle1.setTranslateX(250);
        rectangle1.setArcHeight(60);
        rectangle1.setArcWidth(60);
        Image image2=new Image("https://i.pinimg.com/564x/7e/40/8c/7e408cf13cbc0772ab2d5e135f7e9196.jpg");
        ImagePattern pattern1=new ImagePattern(image2);
        rectangle1.setFill(pattern1);
        pane2.getChildren().addAll(rectangle1,Gust,from1,pr);


        Pane Bus=new Pane();
        Rectangle rectangle2= new Rectangle();
        Text business=new Text("Business");
        business.setFont(Font.font("Impact",15));
        business.setUnderline(true);
        business.setTranslateX(65);
        business.setTranslateY(224);
        Text from=new Text("FROM SRA");
        from.setFont(Font.font("Elephant",9));
        from.setTranslateY(255);
        from.setTranslateX(60);
        Text price=new Text("1699");
        price.setFont(Font.font("Tahoma",20));
        price.setTranslateY(275);
        price.setTranslateX(60);


        rectangle2.setHeight(90);
        rectangle2.setWidth(150);
        rectangle2.setTranslateY(200);
        rectangle2.setTranslateX(50);
        rectangle2.setArcHeight(60);
        rectangle2.setArcWidth(60);
        rectangle2.setFill(pattern1);
        Bus.getChildren().addAll(rectangle2,business,from,price);


        VBox Trip1=new VBox();
        Trip1.getChildren().addAll(city,Time,pane2,Bus);//,trip2,trip3,trip4);
        // Trip1.setTranslateY(380);



        Group root=new Group(rectangle,pane1,daysAndprice,Trip1);
        ScrollPane scroll = new ScrollPane(root);
        scroll.setFitToWidth(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        Scene bookingScene = new Scene(scroll, 800, 600);
        bookingScene.setFill(Color.web("#f3f4f6"));


        searchButton.setOnAction(e -> stage.setScene(bookingScene));

        //bookingScene.getStylesheets().add(getClass().getResource("/stylesheet.css").toExternalForm());







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