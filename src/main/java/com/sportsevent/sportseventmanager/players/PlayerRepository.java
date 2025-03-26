package com.sportsevent.sportseventmanager.players;

import com.sportsevent.sportseventmanager.players.dao.PlayerDAO;
import com.sportsevent.sportseventmanager.players.model.Player;

import java.util.List;

public class PlayerRepository {
    private PlayerDAO playerDAO;

    public PlayerRepository(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }

    public void addPlayer(Player player) {
        playerDAO.addPlayer(player);
    }

    public List<Player> getPlayers(int page, int size) {
        return playerDAO.getPlayers(page, size);
    }

    public long getTotalRecords() {
        return playerDAO.getTotalRecords();
    }

    public boolean existsPlayerByFirstNameAndLastName(String firstName, String lastName) {
        return playerDAO.existsPlayerByFirstNameAndLastName(firstName, lastName);
    }

    public boolean existsPlayerWithNumberInTeam(int teamId, int playerId) {
        return playerDAO.existsPlayerWithNumberInTeam(teamId, playerId);
    }
}
