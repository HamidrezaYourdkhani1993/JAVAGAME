package com.myjavagame.service;

import com.myjavagame.exception.InvalidGameException;
import com.myjavagame.exception.InvalidParamException;
import com.myjavagame.exception.NotFoundException;
import com.myjavagame.model.Game;
import com.myjavagame.model.GamePlay;
import com.myjavagame.model.Player;
import com.myjavagame.model.TicToe;
import com.myjavagame.storage.GameStorage;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.myjavagame.model.GameStatus.*;

@Service
@AllArgsConstructor
public class GameService {

    public Game createGame(Player player) {
        Game game = new Game();
        game.setBoard(new int[3][3]);
        game.setGameId(UUID.randomUUID().toString());
        game.setPlayer1(player);
        game.setStatus(NEW);
        game.setCurrentPlayer(TicToe.X); // Set the starting player

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToGame(Player player2, String gameId) throws InvalidParamException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gameId)) {
            throw new InvalidParamException("Game with provided id doesn't exist");
        }
        Game game = GameStorage.getInstance().getGames().get(gameId);

        if (game.getPlayer2() != null) {
            throw new InvalidGameException("Game is not valid anymore");
        }

        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        game.setCurrentPlayer(TicToe.X); // Set the starting player

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game connectToRandomGame(Player player2) throws NotFoundException {
        Game game = GameStorage.getInstance().getGames().values().stream()
                .filter(it -> it.getStatus().equals(NEW))
                .findFirst()
                .orElseThrow(() -> new NotFoundException("No available games"));

        game.setPlayer2(player2);
        game.setStatus(IN_PROGRESS);
        game.setCurrentPlayer(TicToe.X); // Set the starting player

        GameStorage.getInstance().setGame(game);
        return game;
    }

    public Game gamePlay(GamePlay gamePlay) throws NotFoundException, InvalidGameException {
        if (!GameStorage.getInstance().getGames().containsKey(gamePlay.getGameId())) {
            throw new NotFoundException("Game not found");
        }

        Game game = GameStorage.getInstance().getGames().get(gamePlay.getGameId());

        if (game.getStatus().equals(FINISHED)) {
            throw new InvalidGameException("Game is already finished");
        }

        // Check if it's the current player's turn
        if (gamePlay.getType() != game.getCurrentPlayer()) {
            throw new InvalidGameException("It's not your turn");
        }

        int[][] board = game.getBoard();
        board[gamePlay.getCoordinateX()][gamePlay.getCoordinateY()] = gamePlay.getType().getValue();

        // Check for a winner
        Boolean xWinner = checkWinner(board, TicToe.X);
        Boolean oWinner = checkWinner(board, TicToe.O);

        if (xWinner) {
            game.setWinner(TicToe.X);
            game.setStatus(FINISHED);
        } else if (oWinner) {
            game.setWinner(TicToe.O);
            game.setStatus(FINISHED);
        } else {
            // Switch the current player for the next turn
            game.setCurrentPlayer(gamePlay.getType() == TicToe.X ? TicToe.O : TicToe.X);
        }

        GameStorage.getInstance().setGame(game);
        return game;
    }

    private Boolean checkWinner(int[][] board, TicToe ticToe) {
        int[] boardArray = new int[9];
        int counterIndex = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boardArray[counterIndex] = board[i][j];
                counterIndex++;
            }
        }

        int[][] winCombinations = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
        for (int i = 0; i < winCombinations.length; i++) {
            int counter = 0;
            for (int j = 0; j < winCombinations[i].length; j++) {
                if (boardArray[winCombinations[i][j]] == ticToe.getValue()) {
                    counter++;
                    if (counter == 3) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
