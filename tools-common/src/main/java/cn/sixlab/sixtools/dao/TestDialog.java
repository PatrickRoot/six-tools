/*
 * Copyright (c) 2016 Sixlab. All rights reserved.
 *
 * Under the GPLv3(AKA GNU GENERAL PUBLIC LICENSE Version 3).
 * see http://www.gnu.org/licenses/gpl-3.0-standalone.html
 *
 * For more information, please see
 * https://sixlab.cn/
 * 
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @since 1.0.0(2016/2/14)
 */
package cn.sixlab.sixtools.dao;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;

import java.util.Optional;

/**
 * @author <a href="https://blog.sixlab.cn/">六楼的雨/Patrick Root</a>
 * @since 1.0.0(2016/2/14)
 */
public class TestDialog extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btn00 = new Button("弹出框1");
        btn00.setOnAction(event -> {
            //  http://code.makery.ch/blog/javafx-dialogs-official/

            //000000000000000000000000000000000
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "content text", ButtonType.OK, ButtonType.CANCEL);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("请确认");
            alert.setHeaderText("hear");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

            }
            //000000000000000000000000000000000
        });

        Button btn01 = new Button("弹出框2");
        btn01.setOnAction(e->{


            //11111111111111111111111111111
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("删除提醒");
            alert1.setHeaderText("删除提醒");
            alert1.setContentText("确定删除：");

            Optional<ButtonType> result1 = alert1.showAndWait();

            if (result1.get() == ButtonType.OK) {

            }
            //1111111111111111111111111111111
        });

        Button btn02 = new Button("自定义按钮");
        btn02.setOnAction(event -> {
            //  http://code.makery.ch/blog/javafx-dialogs-official/

            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Confirmation Dialog with Custom Actions");
            alert2.setHeaderText("Look, a Confirmation Dialog with Custom Actions");
            alert2.setContentText("Choose your option.");

            ButtonType buttonTypeOne = new ButtonType("One");
            ButtonType buttonTypeTwo = new ButtonType("Two");
            ButtonType buttonTypeThree = new ButtonType("Three");
            ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert2.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeThree, buttonTypeCancel);

            Optional<ButtonType> result2 = alert2.showAndWait();
            if (result2.get() == buttonTypeOne) {
                // ... user chose "One"
            } else if (result2.get() == buttonTypeTwo) {
                // ... user chose "Two"
            } else if (result2.get() == buttonTypeThree) {
                // ... user chose "Three"
            } else {
                // ... user chose CANCEL or closed the dialog
            }
        });

        Button btn03 = new Button("登录框");
        btn03.setOnAction(e->{

            // Create the custom dialog.
            Dialog<Pair<String, String>> dialog = new Dialog<>();
            dialog.setTitle("Login Dialog");
            dialog.setHeaderText("Look, a Custom Login Dialog");

            // Set the icon (must be included in the project).
            //dialog.setGraphic(new ImageView(this.getClass().getResource("login.png").toString()));

            // Set the button types.
            ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
            dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

            // Create the username and password labels and fields.
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));

            TextField username = new TextField();
            username.setPromptText("Username");
            PasswordField password = new PasswordField();
            password.setPromptText("Password");

            grid.add(new Label("Username:"), 0, 0);
            grid.add(username, 1, 0);
            grid.add(new Label("Password:"), 0, 1);
            grid.add(password, 1, 1);

            // Enable/Disable login button depending on whether a username was entered.
            Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
            loginButton.setDisable(true);

            // Do some validation (using the Java 8 lambda syntax).
            username.textProperty().addListener((observable, oldValue, newValue) -> {
                loginButton.setDisable(newValue.trim().isEmpty());
            });

            dialog.getDialogPane().setContent(grid);

            // Request focus on the username field by default.
            Platform.runLater(() -> username.requestFocus());

            // Convert the result to a username-password-pair when the login button is clicked.
            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == loginButtonType) {
                    return new Pair<>(username.getText(), password.getText());
                }
                return null;
            });

            Optional<Pair<String, String>> result3 = dialog.showAndWait();

            result3.ifPresent(usernamePassword -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());

                alert.showAndWait();
            });
        });

        HBox root = new HBox();
        root.getChildren().add(btn00);
        root.getChildren().add(btn01);
        root.getChildren().add(btn02);
        root.getChildren().add(btn03);
        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}