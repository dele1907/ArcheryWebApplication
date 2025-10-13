package de.dele1907.Model.Tournament;

import de.dele1907.Model.Tournament.Participation.Participant;
import de.dele1907.Model.Tournament.Participation.ShooterTeam;

import java.sql.Date;
import java.util.List;

public class Tournament {
    private final String id;
    private final  List<Participant> participants;
    private final List<ShooterTeam> participantTeams;
    private final Date date;

    public Tournament(String id, List<Participant> participants, List<ShooterTeam> participantTeams, Date date) {
        this.id = id;
        this.participants = participants;
        this.participantTeams = participantTeams;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<ShooterTeam> getParticipantTeams() {
        return participantTeams;
    }

    public Date getDate() {
        return date;
    }
}
