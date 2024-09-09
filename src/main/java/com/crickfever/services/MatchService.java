package com.crickfever.services;

import com.crickfever.entities.Matches;

import java.util.List;

public interface MatchService {
    List<Matches> getAllMatches();
    List<Matches> getLiveMatches();
    List<List<String>> getPointsTable();
}
