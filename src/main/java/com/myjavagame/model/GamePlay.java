package com.myjavagame.model;

import lombok.Data;

@Data
public class GamePlay {

    private TicToe type;          // Type of player making the move (e.g., X or O)
    private Integer coordinateX;  // X-coordinate of the move on the game board
    private Integer coordinateY;  // Y-coordinate of the move on the game board
    private String gameId;        // Identifier of the game this move belongs to

    // Additional methods or constructors can be added if necessary
}
