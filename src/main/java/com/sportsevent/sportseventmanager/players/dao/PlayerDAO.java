package com.sportsevent.sportseventmanager.players.dao;

import com.sportsevent.sportseventmanager.players.model.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private List<Player> players = new ArrayList<>();
    private int idCounter = 1;

    public void addPlayer(Player player) {
        player.setId(idCounter++);
        players.addFirst(player);
    }

    public boolean existsPlayerByFirstNameAndLastName(String firstName, String lastName) {
        for (Player player : players) {
            if (player.getFirstName().equals(firstName) && player.getLastName().equals(lastName)) {
                return true;
            }
        }
        return false;
    }

    public boolean existsPlayerWithNumberInTeam(int teamId, int playerNumber) {
        for (Player player : players) {
            if (player.getTeamId() == teamId && player.getNumber() == playerNumber) {
                return true;
            }
        }
        return false;
    }
}
