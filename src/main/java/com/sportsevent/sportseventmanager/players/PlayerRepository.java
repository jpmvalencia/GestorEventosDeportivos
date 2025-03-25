package com.sportsevent.sportseventmanager.players;

import com.sportsevent.sportseventmanager.players.dao.PlayerDAO;

public class PlayerRepository {
    private PlayerDAO playerDAO;

    public PlayerRepository(PlayerDAO playerDAO) {
        this.playerDAO = playerDAO;
    }
}
