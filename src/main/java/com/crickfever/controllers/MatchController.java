package com.crickfever.controllers;

import com.crickfever.entities.Matches;
import com.crickfever.services.MatchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/match")
public class MatchController {

    private MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/live")
    public ResponseEntity<List<Matches>> getLiveMatches() {
        return new ResponseEntity<List<Matches>>(this.matchService.getLiveMatches(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Matches>> getAllMatches() {
        return new ResponseEntity<List<Matches>>(this.matchService.getAllMatches(), HttpStatus.OK);
    }

    @GetMapping("/points-table")
    public ResponseEntity<?> getPointsTable() {
        return new ResponseEntity<>(this.matchService.getPointsTable(), HttpStatus.OK);
    }
}
