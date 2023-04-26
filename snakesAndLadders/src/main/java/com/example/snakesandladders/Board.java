package com.example.snakesandladders;

import javafx.util.Pair;

import java.util.ArrayList;

public class Board {
    private ArrayList<Pair<Integer, Integer>> positionCoordinates;
    private  ArrayList<Integer>snakeLadder;

    public Board() {
        populatePositionCoordinates();
        setSnakeLadder();

    }
    private void setSnakeLadder(){
        snakeLadder = new ArrayList<>();
        for (int i =0;i<101;i++){
            snakeLadder.add(i);
        }
        snakeLadder.set(4,25);
        snakeLadder.set(13,46);
        snakeLadder.set(33,49);
        snakeLadder.set(50,69);
        snakeLadder.set(42,63);
        snakeLadder.set(62,81);
        snakeLadder.set(74,92);
        snakeLadder.set(99,41);
        snakeLadder.set(89,53);
        snakeLadder.set(76,58);
        snakeLadder.set(66,45);
        snakeLadder.set(54,31);
        snakeLadder.set(43,18);
        snakeLadder.set(40,3);
        snakeLadder.set(27,5);



    }

    private void populatePositionCoordinates() {
        positionCoordinates = new ArrayList<>();
        positionCoordinates.add(new Pair<>(0, 0));//dummy value
        for (int i = 0; i < SandL.height; i++) {
            for (int j = 0; j < SandL.width; j++) {
                // x-axis
                int xCord = 0;
                if (i % 2 == 0) {
                    xCord = j * SandL.tileSize + SandL.tileSize / 2;
                } else {
                    xCord = SandL.height * SandL.tileSize - j * SandL.tileSize - SandL.tileSize / 2;
                }
                //y-axis
                int yCord = SandL.height * SandL.tileSize - i * SandL.tileSize - SandL.tileSize / 2;

                positionCoordinates.add(new Pair<>(xCord, yCord));

            }
        }
    }

    public int getXCoordinate(int position) {
        if (position > 0 && position <= 100) {
            return positionCoordinates.get(position).getKey();
        }

        return -1;
    }
    public int getYCoordinate(int position) {
        if (position > 0 && position <= 100) {
              return positionCoordinates.get(position).getValue();
        }

        return -1;
    }
    public  int getSnakeLadder(int position){
        return snakeLadder.get(position);
    }


    public static void main(String[] args) {
        Board board = new Board();
        for(int i = 0;i<board.positionCoordinates.size();i++){
            System.out.println(i + "$ X :" + board.positionCoordinates.get(i).getKey() + " Y :" + board.positionCoordinates.get(i).getValue()   );
        }

    }


}

