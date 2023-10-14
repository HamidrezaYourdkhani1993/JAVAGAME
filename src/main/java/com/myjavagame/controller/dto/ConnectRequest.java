package com.myjavagame.controller.dto;

import com.myjavagame.model.Player;
import lombok.Data;

/**
 * A DTO (Data Transfer Object) representing a player's connection request.
 * */
@Data
public class ConnectRequest {
    /**
     * The player associated with the connection request.
     */
    private Player player;

    /**
     * The unique identifier of the game to which the player wants to connect.
     */
    private String gameId;
}

