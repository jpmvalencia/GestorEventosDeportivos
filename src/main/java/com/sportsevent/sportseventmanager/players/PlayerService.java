package com.sportsevent.sportseventmanager.players;

import com.sportsevent.sportseventmanager.common.exception.DuplicatePlayerException;
import com.sportsevent.sportseventmanager.common.exception.DuplicatePlayerNumberException;
import com.sportsevent.sportseventmanager.common.exception.TeamNotFoundException;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.players.dto.PlayerDTO;
import com.sportsevent.sportseventmanager.players.model.Player;
import com.sportsevent.sportseventmanager.teams.TeamService;

public class PlayerService {
    private PlayerRepository playerRepository;
    private TeamService teamService;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    public SuccessResponse addPlayer(PlayerDTO playerDTO) throws TeamNotFoundException, DuplicatePlayerException, DuplicatePlayerNumberException {
        Player player = new Player(
                playerDTO.getFirstName(),
                playerDTO.getLastName(),
                playerDTO.getBirthDate(),
                playerDTO.getNationality(),
                playerDTO.getNumber(),
                playerDTO.getTeamId()
        );

        if (playerRepository.existsPlayerByFirstNameAndLastName(player.getFirstName(), player.getLastName())) {
            throw new DuplicatePlayerException("player already exists in a team", 409);
        }

        if (playerRepository.existsPlayerWithNumberInTeam(player.getTeamId(), player.getNumber())) {
            throw new DuplicatePlayerNumberException("number " + player.getNumber() + " is already taken in the team", 409);
        }

        teamService.getTeamById(playerDTO.getTeamId());

        playerRepository.addPlayer(player);
        teamService.addPlayerToTeam(playerDTO.getTeamId(), player.getId());

        return new SuccessResponse(
                "player created successfully",
                201,
                player
        );
    }
}
