package com.sportsevent.sportseventmanager.teams;

import com.sportsevent.sportseventmanager.common.exception.DuplicateTeamException;
import com.sportsevent.sportseventmanager.teams.dto.TeamDTO;
import com.sportsevent.sportseventmanager.teams.model.Team;

public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team addTeam(TeamDTO teamDTO) throws DuplicateTeamException {
        Team team = new Team(
                teamDTO.getName(), teamDTO.getSport(), teamDTO.getCity(), teamDTO.getFoundationDate(), teamDTO.getLogo()
        );

        if (teamRepository.existsTeamByNameAndSport(team.getName(), team.getSport())) {
            throw new DuplicateTeamException("team with the same name and sport already exists", 409);
        }

        teamRepository.addTeam(team);

        return team;
    }
}
