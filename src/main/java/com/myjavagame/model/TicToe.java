package com.myjavagame.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TicToe {
    X(1), O(2);

    private Integer value;

    /**
     * Get the TicTacToe enum based on its integer value.
     *
     * @param value The integer value to look up.
     * @return The corresponding TicTacToe enum or null if not found.
     */
    public static TicToe fromValue(Integer value) {
        for (TicToe ticToe : values()) {
            if (ticToe.value.equals(value)) {
                return ticToe;
            }
        }
        return null; // Value not found
    }
}
