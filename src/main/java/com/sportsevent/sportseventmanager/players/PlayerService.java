package com.sportsevent.sportseventmanager.players;

import com.sportsevent.sportseventmanager.common.exception.DuplicatePlayerException;
import com.sportsevent.sportseventmanager.common.exception.DuplicatePlayerNumberException;
import com.sportsevent.sportseventmanager.common.exception.TeamNotFoundException;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.players.dto.PlayerDTO;
import com.sportsevent.sportseventmanager.players.dto.PlayerWithTeamDTO;
import com.sportsevent.sportseventmanager.players.model.Player;
import com.sportsevent.sportseventmanager.teams.TeamService;
import com.sportsevent.sportseventmanager.teams.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerService {
    private PlayerRepository playerRepository;
    private TeamService teamService;

    public PlayerService(PlayerRepository playerRepository, TeamService teamService) {
        this.playerRepository = playerRepository;
        this.teamService = teamService;
    }

    public SuccessResponse getPlayers(PaginationDTO paginationDTO) {
        int page = paginationDTO.getPage();
        int size = paginationDTO.getSize();

        List<Player> players = playerRepository.getPlayers(page, size);
        long totalRecords = playerRepository.getTotalRecords();

        List<PlayerWithTeamDTO> playersWithTeam = players.stream().map(player -> {
            String teamName = null;

            try {
                Team team = teamService.getTeamById(player.getTeamId());

                if (team != null) {
                    teamName = team.getName();
                }
            } catch (TeamNotFoundException _) {
            }

            return new PlayerWithTeamDTO(
                    player.getId(),
                    player.getFirstName(),
                    player.getLastName(),
                    player.getBirthDate(),
                    player.getNationality(),
                    player.getNumber(),
                    player.getTeamId(),
                    teamName
            );
        }).collect(Collectors.toList());

        return new SuccessResponse(
                "players retrieved successfully",
                200,
                playersWithTeam,
                totalRecords
        );
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
