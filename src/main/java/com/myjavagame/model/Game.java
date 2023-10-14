package com.myjavagame.model;

import lombok.Data;

@Data
public class Game {

    private String gameId;         // Unique identifier for the game
    private Player player1;        // First player
    private Player player2;        // Second player
    private GameStatus status;     // Current game status (e.g., in-progress, finished)
    private int[][] board;         // Game board representation
    private TicToe winner;      // Player who has won the game (if any)
    private TicToe currentPlayer;
    // Additional methods and constructors can be added as needed
}
