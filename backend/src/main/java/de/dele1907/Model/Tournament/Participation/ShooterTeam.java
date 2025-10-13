package de.dele1907.Model.Tournament.Participation;

import java.util.List;

public record ShooterTeam(String id, String name, Integer clubId, Integer totalScore, Integer rank, List<String> memberIds) {}
