package com.example.snakesandladders;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


import java.io.IOException;

//import static java.util.Collections.addAll;

public class SandL extends Application {

    public static final int  tileSize=  40,  width = 10, height = 10,
    buttonLine = height*tileSize+50, infoLine = height*tileSize +20;

    Player playerOne, playerTwo;

    boolean firstPlayerTurn =true ,gameStart = false;

    int diceValue;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tileSize, height*tileSize+100);
        // putting 100 tiles on the ui
        for(int i = 0; i<height;i++){
            for(int j = 0; j<width;j++){
                Tile tile = new Tile(tileSize);

                tile.setTranslateX(i*tileSize);
                tile.setTranslateY(j*tileSize);

                root.getChildren().addAll(tile);

            }
        }
        // Putting an image on the root
        Image img = new Image("file:/home/ajinsato/IdeaProjects/snakesAndLadders/src/img.png");
        ImageView boarImage = new ImageView();
        boarImage.setFitWidth(width*tileSize);
        boarImage.setFitHeight(height*tileSize);
        boarImage.setImage(img);

        root.getChildren().addAll(boarImage);


        //Buttons and Info
        Button startButton = new Button("Start");
        startButton.setTranslateX(10);
        startButton.setTranslateY(buttonLine);

        Button playerOneButton = new Button("Player One");
        playerOneButton.setTranslateX(100);
        playerOneButton.setTranslateY(buttonLine);


        Button playerTwoButton = new Button("Player Two");
        playerTwoButton.setTranslateX(200);
        playerTwoButton.setTranslateY(buttonLine);

        Label diceLabel= new Label("Start the Game");
        diceLabel.setTranslateX(150);
        diceLabel.setTranslateY(infoLine);

        playerOne = new Player("lift", Color.BLACK, tileSize/2);
        playerTwo = new Player("breakLift" , Color.WHITE, tileSize/2-5 );

        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            if (gameStart) {
                                                if (firstPlayerTurn) {

                                                    diceValue = rollDice();
                                                    diceLabel.setText("Dice: " + diceValue);
                                                    playerOne.movePlayer(diceValue);
                                                    firstPlayerTurn = !firstPlayerTurn;
                                                    if(playerOne.checkWinner()){
                                                        diceLabel.setText("Winner is " + playerOne.getName());

                                                        startButton.setText("Restart ");
                                                        startButton.setDisable(false);
                                                        firstPlayerTurn=true;
                                                        gameStart=false;
                                                    }
                                                }


                                            }
                                        }
                                    }
        );
        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {
                                            if (gameStart) {
                                                if (!firstPlayerTurn) {

                                                    diceValue = rollDice();
                                                    diceLabel.setText("Dice: " + diceValue);
                                                    playerTwo.movePlayer(diceValue);
                                                    firstPlayerTurn = !firstPlayerTurn;
                                                }
                                                if(playerTwo.checkWinner()){
                                                    diceLabel.setText("Winner is " + playerTwo.getName());

                                                    startButton.setText("Restart ");
                                                    startButton.setDisable(false);
                                                    firstPlayerTurn=true;
                                                    gameStart = false;
                                                }


                                            }
                                        }
                                    }
        );

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gameStart =true;
                startButton.setDisable(true);
                playerOne.setInitialPosition();
                playerTwo.setInitialPosition();
            }
        });

//        Tile tile = new Tile(tileSize);
        root.getChildren().addAll(startButton, playerOneButton, playerTwoButton, diceLabel,

        playerOne.getCoin(),playerTwo.getCoin());
        return root;
    }

    private int rollDice(){
        return (int) (Math.random()*6+1);

    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SandL.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Scene scene = new Scene(createContent());
        stage.setTitle("Snakes & Ladders!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}