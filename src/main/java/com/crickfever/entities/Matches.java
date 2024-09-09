package com.crickfever.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "match_tbl")
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int matchId;
    @Enumerated
    private MatchStatus matchStatus;
    private String matchName;
    private String matchNumberAndVenue;
    private String team1Score;

    public Matches(int matchId, MatchStatus matchStatus, String matchName, String matchNumberAndVenue, String team1Score, String team2Score, String team1, String team2, String matchLink, String liveText, String textComplete, Date date) {
        this.matchId = matchId;
        this.matchStatus = matchStatus;
        this.matchName = matchName;
        this.matchNumberAndVenue = matchNumberAndVenue;
        this.team1Score = team1Score;
        this.team2Score = team2Score;
        this.team1 = team1;
        this.team2 = team2;
        this.matchLink = matchLink;
        this.liveText = liveText;
        this.textComplete = textComplete;
        this.date = date;
    }

    public String getTeam2Score() {
        return team2Score;
    }

    public void setTeam2Score(String team2Score) {
        this.team2Score = team2Score;
    }

    private String team2Score;
    private String team1;
    private String team2;
    private String matchLink;
    private String liveText;
    private String textComplete;
    private Date date = new Date();



    @Override
    public String toString() {
        return "Matches{" +
                "matchId=" + matchId +
                ", matchStatus=" + matchStatus +
                ", matchName='" + matchName + '\'' +
                ", matchNumberAndVenue='" + matchNumberAndVenue + '\'' +
                ", team1Score='" + team1Score + '\'' +
                ", team1='" + team1 + '\'' +
                ", team2='" + team2 + '\'' +
                ", matchLink='" + matchLink + '\'' +
                ", liveText='" + liveText + '\'' +
                ", textComplete='" + textComplete + '\'' +
                ", date=" + date +
                '}';
    }

    public Matches() {
    }

    public int getMatchId() {
        return matchId;
    }

    public void setMatchId(int matchId) {
        this.matchId = matchId;
    }

    public MatchStatus getMatchStatus() {
        return matchStatus;
    }

    public void setMatchStatus() {
        if(textComplete.isBlank()) {
            this.matchStatus = MatchStatus.LIVE;
        }
        else {
            this.matchStatus = MatchStatus.ENDED;
        }
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

    public String getMatchNumberAndVenue() {
        return matchNumberAndVenue;
    }

    public void setMatchNumberAndVenue(String matchNumberAndVenue) {
        this.matchNumberAndVenue = matchNumberAndVenue;
    }

    public String getTeam1Score() {
        return team1Score;
    }

    public void setTeam1Score(String team1Score) {
        this.team1Score = team1Score;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getMatchLink() {
        return matchLink;
    }

    public void setMatchLink(String matchLink) {
        this.matchLink = matchLink;
    }

    public String getLiveText() {
        return liveText;
    }

    public void setLiveText(String liveText) {
        this.liveText = liveText;
    }

    public String getTextComplete() {
        return textComplete;
    }

    public void setTextComplete(String textComplete) {
        this.textComplete = textComplete;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
