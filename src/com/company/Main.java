package com.company;


import com.company.controller.LoginPageController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }


    @Override
    public void start(Stage stage) throws Exception {
        LoginPageController root = new LoginPageController();
        stage.setScene(new Scene(root.getLoginPage()));
        stage.setHeight(500);
        stage.setWidth(600);
        stage.resizableProperty().setValue(Boolean.FALSE);
        stage.setTitle("Gmail");
        stage.show();
    }
}
