package com.sportsevent.sportseventmanager.players;

import com.sportsevent.sportseventmanager.players.dao.PlayerDAO;
import com.sportsevent.sportseventmanager.players.model.Player;

public class PlayerRepository {
    private PlayerDAO playerDAO;

    public PlayerRepository(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public void addPlayer(Player player) {
        playerDAO.addPlayer(player);
    }

    public boolean existsPlayerByFirstNameAndLastName(String firstName, String lastName) {
        return playerDAO.existsPlayerByFirstNameAndLastName(firstName, lastName);
    }

    public boolean existsPlayerWithNumberInTeam(int teamId, int playerId) {
        return playerDAO.existsPlayerWithNumberInTeam(teamId, playerId);
    }
}
