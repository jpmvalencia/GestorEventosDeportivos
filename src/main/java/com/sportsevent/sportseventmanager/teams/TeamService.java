package com.sportsevent.sportseventmanager.teams;

import com.sportsevent.sportseventmanager.common.exception.DuplicateTeamException;
import com.sportsevent.sportseventmanager.common.exception.TeamNotFoundException;
import com.sportsevent.sportseventmanager.common.pagination.dto.PaginationDTO;
import com.sportsevent.sportseventmanager.common.response.SuccessResponse;
import com.sportsevent.sportseventmanager.teams.dto.TeamDTO;
import com.sportsevent.sportseventmanager.teams.model.Team;

import java.util.List;

public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    /* public SuccessResponse getTeams(PaginationDTO paginationDTO) {
        int page = paginationDTO.getPage();
        int size = paginationDTO.getSize();

        List<Team> teams = teamRepository.getTeams(page, size);
        long totalRecords = teamRepository.getTotalRecords();

        List<TeamWithPlayerDTO> teamsWithPlayers = teams.stream().map(team -> {
            List<String> playerNames = new ArrayList<>();

            try {
                for (Integer playerId : team.getPlayers()) {
                    Player player = playerService.getPlayerById(playerId);

                    if (player != null) {
                        playerNames.add(player.getFirstName() + " " + player.getLastName());
                    }
                }
            } catch (PlayerNotFoundException _) {
            }

            return new TeamWithPlayerDTO(
                    team.getId(),
                    team.getName(),
                    team.getSport(),
                    team.getCity(),
                    team.getFoundationDate(),
                    team.getLogo(),
                    playerNames
            );
        }).collect(Collectors.toList());

        return new SuccessResponse(
                "teams retrieved successfully",
                200,
                teamsWithPlayers,
                totalRecords
        );
    } */

    public SuccessResponse getTeams(PaginationDTO paginationDTO) {
        int page = paginationDTO.getPage();
        int size = paginationDTO.getSize();

        List<Team> teams = teamRepository.getTeams(page, size);
        long totalRecords = teamRepository.getTotalRecords();

        return new SuccessResponse(
                "teams retrieved successfully",
                200,
                teams,
                totalRecords
        );
    }

    public SuccessResponse addTeam(TeamDTO teamDTO) throws DuplicateTeamException {
        Team team = new Team(
                teamDTO.getName(), teamDTO.getSport(), teamDTO.getCity(), teamDTO.getFoundationDate(), teamDTO.getLogo()
        );

        if (teamRepository.existsTeamByNameAndSport(team.getName(), team.getSport())) {
            throw new DuplicateTeamException("team with the same name and sport already exists", 409);
        }

        teamRepository.addTeam(team);

        return new SuccessResponse(
                "team created successfully",
                201,
                team
        );
    }

    public SuccessResponse addPlayerToTeam(int teamId, int playerId) throws TeamNotFoundException {
        Team team = this.getTeamById(teamId);

        teamRepository.addPlayerToTeam(teamId, playerId);

        return new SuccessResponse(
                "player added succesfully",
                200,
                team
        );
    }

    public Team getTeamById(int teamId) throws TeamNotFoundException {
        Team team = teamRepository.getTeamById(teamId);

        if (team == null) {
            throw new TeamNotFoundException("team with id " + teamId + " not found", 404);
        }

        return team;
    }

    public double getAveragePlayersPerTeam() {
        List<Team> teams = teamRepository.getAllTeams();

        if (teams.isEmpty()) {
            return 0.0;
        }

        int totalPlayers = teams.stream().mapToInt(team -> team.getPlayers().size()).sum();

        return (double) totalPlayers / teams.size();
    }
}
