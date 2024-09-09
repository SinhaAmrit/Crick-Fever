package com.crickfever.services.impl;

import com.crickfever.entities.Matches;
import com.crickfever.repositories.MatchRepo;
import com.crickfever.services.MatchService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class MatchServicesImpl implements MatchService {

    @Value("${crickfever.website.liveUrl}")
    private String liveURL;

    @Value("${crickfever.website.pointsTableUrl}")
    private String pointsTableUrl;

    private MatchRepo matchRepo;

    public MatchServicesImpl(MatchRepo matchRepo) {
        this.matchRepo = matchRepo;
    }

    @Override
    public List<Matches> getLiveMatches() {
        List<Matches> matches = new ArrayList<>();
        try {
            String url = liveURL;
            Document document = Jsoup.connect(url).get();
            Elements liveScoreElements = document.select("div.cb-mtch-lst.cb-tms-itm");
            for (Element match : liveScoreElements) {
                HashMap<String, String> liveMatchInfo = new LinkedHashMap<>();
                String matchName = match.select("h3.cb-lv-scr-mtch-hdr").select("a").text();
                String matchNumberAndVenue = match.select("span").text();
                Elements matchBatTeamInfo = match.select("div.cb-hmscg-bat-txt");
                String team1 = matchBatTeamInfo.select("div.cb-hmscg-tm-nm").text();
                String team1Score = matchBatTeamInfo.select("div.cb-hmscg-tm-nm+div").text();
                Elements team2Info = match.select("div.cb-hmscg-bwl-txt");
                String team2 = team2Info.select("div.cb-hmscg-tm-nm").text();
                String team2Score = team2Info.select("div.cb-hmscg-tm-nm+div").text();
                String textLive = match.select("div.cb-text-live").text();
                String textComplete = match.select("div.cb-text-complete").text();
                String matchLink = match.select("a.cb-lv-scrs-well.cb-lv-scrs-well-live").attr("href").toString();

                Matches match1 = new Matches();
                match1.setMatchName(matchName);
                match1.setMatchNumberAndVenue(matchNumberAndVenue);
                match1.setTeam1(team1);
                match1.setTeam1Score(team1Score);
                match1.setTeam2(team2);
                match1.setTeam2Score(team2Score);
                match1.setLiveText(textLive);
                match1.setMatchLink(matchLink);
                match1.setTextComplete(textComplete);
                match1.setMatchStatus();
                matches.add(match1);
                updateMatchInDB(match1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return matches;
    }

    private void updateMatchInDB(Matches match1) {

        Matches match = this.matchRepo.findByMatchName(match1.getMatchName()).orElse(null);
        if (match == null) {
            this.matchRepo.save(match1);
        } else {

            match1.setMatchId(match.getMatchId());
            this.matchRepo.save(match1);
        }

    }

    @Override
    public List<List<String>> getPointsTable() {
        List<List<String>> pointTable = new ArrayList<>();
        String tableURL = pointsTableUrl;
        try {
            Document document = Jsoup.connect(tableURL).get();
            Elements table = document.select("table.cb-srs-pnts");
            Elements tableHeads = table.select("thead>tr>*");
            List<String> headers = new ArrayList<>();
            tableHeads.forEach(element -> {
                headers.add(element.text());
            });
            pointTable.add(headers);
            Elements bodyTrs = table.select("tbody>*");
            bodyTrs.forEach(tr -> {
                List<String> points = new ArrayList<>();
                if (tr.hasAttr("class")) {
                    Elements tds = tr.select("td");
                    String team = tds.get(0).select("div.cb-col-84").text();
                    points.add(team);
                    tds.forEach(td -> {
                        if (!td.hasClass("cb-srs-pnts-name")) {
                            points.add(td.text());
                        }
                    });
                    pointTable.add(points);
                }
            });

            System.out.println(pointTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pointTable;
    }

    @Override
    public List<Matches> getAllMatches() {
        return this.matchRepo.findAll();
    }
}