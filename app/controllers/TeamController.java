package controllers;

import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import play.twirl.api.Html;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TeamController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public TeamController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    public Result getSetupTeam()
    {
        return ok(views.html.setupteam.render());
    }

    @Transactional
    public Result postSetupTeam()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String teamName = form.get("teamname");

        String teamCity = form.get("teamcity");

        String ownerIdString = session().get("ownerId");

        String takeOverOrganizationIdString = session().get("takeOverOrganizationId");

        String coachIdString = session().get("coachId");

        Team team = new Team();

        team.setTeamCity(teamCity);
        team.setTeamName(teamName);
        team.setOwnerId(Integer.parseInt(ownerIdString));
        team.setCoachId(Integer.parseInt(coachIdString));
        team.setTakeOverOrganizationId(Integer.parseInt(takeOverOrganizationIdString));

        jpaApi.em().persist(team);

        String teamIdString = team.getTeamId().toString();

        session().put("teamId", teamIdString);

        return redirect("/draftteam");
    }

    @Transactional
    public Result getDraftTeam()
    {
        String ownerIdString = session().get("ownerId");
        Integer ownerId = Integer.parseInt(ownerIdString);

        String coachIdString = session().get("coachId");
        Integer coachId = Integer.parseInt(coachIdString);

        String takeOverOrganizationIdString = session().get("takeOverOrganizationId");
        Integer takeOverOrganizationId = Integer.parseInt(takeOverOrganizationIdString);

        String ownerSQL = "SELECT o FROM Owner o WHERE ownerId = :ownerId ";

        Owner owner = jpaApi.em().createQuery(ownerSQL, Owner.class).setParameter("ownerId", ownerId).getSingleResult();

        String coachSQL = "SELECT c FROM Coach c WHERE coachId = :coachId ";

        Coach coach = jpaApi.em().createQuery(coachSQL, Coach.class).setParameter("coachId", coachId).getSingleResult();

        String takeOverOrganizationSQL = "SELECT to FROM TakeOverOrganization to WHERE takeOverOrganizationId = :takeOverOrganizationId ";

        TakeOverOrganization takeOverOrganization = jpaApi.em().createQuery(takeOverOrganizationSQL, TakeOverOrganization.class).setParameter("takeOverOrganizationId", takeOverOrganizationId).getSingleResult();

        String coachSpecialtySQL = "SELECT cs FROM CoachSpecialty cs WHERE coachSpecialtyId = :coachSpecialtyId ";

        CoachSpecialty coachSpecialty = jpaApi.em().createQuery(coachSpecialtySQL, CoachSpecialty.class).setParameter("coachSpecialtyId", coach.getCoachSpecialtyId()).getSingleResult();

        String teamPlayersSQL = "SELECT tp FROM TeamPlayer tp ";

        List<TeamPlayer> teamPlayers = jpaApi.em().createQuery(teamPlayersSQL, TeamPlayer.class).getResultList();

        TeamDetail teamDetail = new TeamDetail();
        BigDecimal ZERO = new BigDecimal(0);
        teamDetail.setTeamSalaryAccrued(ZERO);

        Quarterback quarterback = new Quarterback();
        RunningBack runningBack = new RunningBack();
        WideReceiver1 wideReceiver1 = new WideReceiver1();
        WideReceiver2 wideReceiver2 = new WideReceiver2();
        WideReceiver3 wideReceiver3 = new WideReceiver3();
        TightEnd tightEnd = new TightEnd();
        Tackle1 tackle1 = new Tackle1();
        Tackle2 tackle2 = new Tackle2();
        Guard1 guard1 = new Guard1();
        Guard2 guard2 = new Guard2();
        Center center = new Center();
        DefensiveTackle1 defensiveTackle1 = new DefensiveTackle1();
        DefensiveTackle2 defensiveTackle2 = new DefensiveTackle2();
        DefensiveEnd1 defensiveEnd1 = new DefensiveEnd1();
        DefensiveEnd2 defensiveEnd2 = new DefensiveEnd2();
        OutsideLinebacker1 outsideLinebacker1 = new OutsideLinebacker1();
        OutsideLinebacker2 outsideLinebacker2 = new OutsideLinebacker2();
        InsideLinebacker insideLinebacker = new InsideLinebacker();
        Safety1 safety1 = new Safety1();
        Safety2 safety2 = new Safety2();
        Cornerback1 cornerback1 = new Cornerback1();
        Cornerback2 cornerback2 = new Cornerback2();
        Kicker kicker = new Kicker();
        Punter punter = new Punter();

        TeamDisplay teamDisplay = new TeamDisplay(quarterback, runningBack, wideReceiver1, wideReceiver2, wideReceiver3, tightEnd, tackle1, tackle2, guard1, guard2, center, defensiveTackle1, defensiveTackle2, defensiveEnd1, defensiveEnd2, outsideLinebacker1, outsideLinebacker2, insideLinebacker, safety1, safety2, cornerback1, cornerback2, punter, kicker);


        for (TeamPlayer teamPlayer : teamPlayers)
        {
            switch (teamPlayer.getSortOrderId())
            {
                case 1:
                    quarterback.setName(teamPlayer.getTeamPlayerName());
                    quarterback.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(quarterback.getValue());
                    teamDisplay.setQuarterback(quarterback);
                    break;
                case 2:
                    runningBack.setName(teamPlayer.getTeamPlayerName());
                    runningBack.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(runningBack.getValue());
                    teamDisplay.setRunningBack(runningBack);
                    break;
                case 3:
                    wideReceiver1.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver1.getValue());
                    teamDisplay.setWideReceiver1(wideReceiver1);
                    break;
                case 4:
                    wideReceiver2.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver2.getValue());
                    teamDisplay.setWideReceiver2(wideReceiver2);
                    break;
                case 5:
                    wideReceiver3.setName(teamPlayer.getTeamPlayerName());
                    wideReceiver3.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(wideReceiver3.getValue());
                    teamDisplay.setWideReceiver3(wideReceiver3);
                    break;
                case 6:
                    tightEnd.setName(teamPlayer.getTeamPlayerName());
                    tightEnd.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tightEnd.getValue());
                    teamDisplay.setTightEnd(tightEnd);
                    break;
                case 7:
                    tackle1.setName(teamPlayer.getTeamPlayerName());
                    tackle1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle1.getValue());
                    teamDisplay.setTackle1(tackle1);
                    break;
                case 8:
                    tackle2.setName(teamPlayer.getTeamPlayerName());
                    tackle2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(tackle2.getValue());
                    teamDisplay.setTackle2(tackle2);
                    break;
                case 9:
                    guard1.setName(teamPlayer.getTeamPlayerName());
                    guard1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard1.getValue());
                    teamDisplay.setGuard1(guard1);
                    break;

                case 10:
                    guard2.setName(teamPlayer.getTeamPlayerName());
                    guard2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(guard2.getValue());
                    teamDisplay.setGuard2(guard2);
                    break;
                case 11:
                    center.setName(teamPlayer.getTeamPlayerName());
                    center.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(center.getValue());
                    teamDisplay.setCenter(center);
                    break;
                case 13:
                    defensiveTackle1.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle1.getValue());
                    teamDisplay.setDefensiveTackle1(defensiveTackle1);
                    break;
                case 14:
                    defensiveTackle2.setName(teamPlayer.getTeamPlayerName());
                    defensiveTackle2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveTackle2.getValue());
                    teamDisplay.setDefensiveTackle2(defensiveTackle2);
                    break;
                case 15:
                    defensiveEnd1.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd1.getValue());
                    teamDisplay.setDefensiveEnd1(defensiveEnd1);
                    break;
                case 16:
                    defensiveEnd2.setName(teamPlayer.getTeamPlayerName());
                    defensiveEnd2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(defensiveEnd2.getValue());
                    teamDisplay.setDefensiveEnd2(defensiveEnd2);
                    break;
                case 17:
                    outsideLinebacker1.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker1.getValue());
                    teamDisplay.setOutsideLinebacker1(outsideLinebacker1);
                    break;
                case 19:
                    outsideLinebacker2.setName(teamPlayer.getTeamPlayerName());
                    outsideLinebacker2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(outsideLinebacker2.getValue());
                    teamDisplay.setOutsideLinebacker2(outsideLinebacker2);
                    break;
                case 18:
                    insideLinebacker.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(insideLinebacker.getValue());
                    teamDisplay.setInsideLinebacker(insideLinebacker);
                    break;
                case 20:
                    safety1.setName(teamPlayer.getTeamPlayerName());
                    safety1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety1.getValue());
                    teamDisplay.setSafety1(safety1);
                    break;
                case 21:
                    safety2.setName(teamPlayer.getTeamPlayerName());
                    safety2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(safety2.getValue());
                    teamDisplay.setSafety2(safety2);
                    break;
                case 22:
                    cornerback1.setName(teamPlayer.getTeamPlayerName());
                    cornerback1.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback1.getValue());
                    teamDisplay.setCornerback1(cornerback1);
                    break;
                case 23:
                    cornerback2.setName(teamPlayer.getTeamPlayerName());
                    cornerback2.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(cornerback2.getValue());
                    teamDisplay.setCornerback2(cornerback2);
                    break;
                case 24:
                    punter.setName(teamPlayer.getTeamPlayerName());
                    punter.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(punter.getValue());
                    teamDisplay.setPunter(punter);
                    break;
                case 12:
                    kicker.setName(teamPlayer.getTeamPlayerName());
                    kicker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDetail.addTeamSalaryAccrued(kicker.getValue());
                    teamDisplay.setKicker(kicker);
                    break;
            }
        }

        teamDetail.setCoachId(coachId);
        teamDetail.setOwnerId(ownerId);
        teamDetail.setTakeOverOrganizationId(takeOverOrganizationId);
        teamDetail.setCoachName(coach.getCoachName());
        teamDetail.setTakeOverOrganizationId(takeOverOrganization.getTakeOverOrganizationId());
        teamDetail.setOwnerName(owner.getOwnerName());
        teamDetail.setOrganizationName(owner.getOrganizationName());
        teamDetail.setOrganizationSalaryCap(takeOverOrganization.getOrganizationSalaryCap());
        teamDetail.setCoachSpecialtyName(coachSpecialty.getCoachSpecialtyName());
        teamDetail.setCoachTier(coach.getCoachTier());
        teamDetail.setTeamName(session().get("teamName"));

        return ok(views.html.draftteam.render(teamDetail, teamDisplay));
    }

    public Result postDraftTeam()
    {
        //Html Result = views.html.draftteam.render();

        return ok("saved");
    }

    @Transactional
    public Result getDraftPlayer(int sortOrderId)
    {
        int playerPositionId = sortOrderId;
        //WR
        if (sortOrderId == 3 || sortOrderId == 4 || sortOrderId == 5)
        {
            playerPositionId = 3;
        }
        //TE
        if (sortOrderId == 6)
        {
            playerPositionId = 4;
        }
        //T
        if (sortOrderId == 7 || sortOrderId == 8)
        {
            playerPositionId = 5;
        }
        //G
        if (sortOrderId == 9 || sortOrderId == 10)
        {
            playerPositionId = 6;
        }
        //C
        if (sortOrderId == 11)
        {
            playerPositionId = 7;
        }
        //K
        if (sortOrderId == 12)
        {
            playerPositionId = 15;
        }
        //DT
        if (sortOrderId == 13 || sortOrderId == 14)
        {
            playerPositionId = 8;
        }
        //DE
        if (sortOrderId == 15 || sortOrderId == 16)
        {
            playerPositionId = 9;
        }
        //OLB
        if (sortOrderId == 17 || sortOrderId == 19)
        {
            playerPositionId = 10;
        }
        //ILB
        if (sortOrderId == 18)
        {
            playerPositionId = 11;
        }
        //S
        if (sortOrderId == 20 || sortOrderId == 21)
        {
            playerPositionId = 12;
        }
        //CB
        if (sortOrderId == 22 || sortOrderId == 23)
        {
            playerPositionId = 13;
        }
        //P
        if (sortOrderId == 24)
        {
            playerPositionId = 14;
        }

        String playerPositionSQL = "SELECT pp FROM PlayerPosition pp WHERE playerPositionId = :playerPositionId ";

        PlayerPosition playerPosition = jpaApi.em().createQuery(playerPositionSQL, PlayerPosition.class).setParameter("playerPositionId", playerPositionId).getSingleResult();

        String existingTeammateSQL = "SELECT tp FROM TeamPlayer tp WHERE teamPlayerPositionId = :playerPositionId ";

        List<TeamPlayer> teamPlayerTeammates = jpaApi.em().createQuery(existingTeammateSQL, TeamPlayer.class).setParameter("playerPositionId", playerPositionId).getResultList();

        Integer firstTeammateId = 0;
        Integer secondTeammateId = 0;


        for (TeamPlayer teamPlayer : teamPlayerTeammates)
        {
            firstTeammateId = teamPlayer.getPlayerId();
            secondTeammateId = firstTeammateId;

        }
        String playerSQL = "SELECT p FROM Player p WHERE NOT playerId = :firstTeammateId AND NOT playerId = :secondTeammateId AND playerPositionId = :playerPositionId ORDER BY playerValue desc";

        List<Player> players = jpaApi.em().createQuery(playerSQL, Player.class).setParameter("playerPositionId", playerPositionId).setParameter("firstTeammateId", firstTeammateId).setParameter("secondTeammateId", secondTeammateId).getResultList();

        List<PlayerDetail> playerDetails = new ArrayList<>();

        for (Player player : players)
        {
            PlayerDetail playerDetail = new PlayerDetail();
            playerDetail.setPlayerId(player.getPlayerId());
            playerDetail.setPlayerName(player.getPlayerName());
            playerDetail.setPlayerValue(player.getPlayerValue());
            playerDetail.setPlayerHeightFeet(player.getPlayerHeightFeet());
            playerDetail.setPlayerHeightInches(player.getPlayerHeightInches());
            playerDetail.setPlayerWeight(player.getPlayerWeight());
            playerDetail.setYearsExperience(player.getYearsExperience());
            playerDetail.setPlayerCollege(player.getPlayerCollege());

            playerDetails.add(playerDetail);
        }
        return ok(views.html.draftplayer.render(playerPosition, playerDetails));
    }

    @Transactional
    public Result postDraftPlayer(int sortOrderId)
    {
        int playerPositionId = sortOrderId;
        //WR
        if (sortOrderId == 3 || sortOrderId == 4 || sortOrderId == 5)
        {
            playerPositionId = 3;
        }
        //TE
        if (sortOrderId == 6)
        {
            playerPositionId = 4;
        }
        //T
        if (sortOrderId == 7 || sortOrderId == 8)
        {
            playerPositionId = 5;
        }
        //G
        if (sortOrderId == 9 || sortOrderId == 10)
        {
            playerPositionId = 6;
        }
        //C
        if (sortOrderId == 11)
        {
            playerPositionId = 7;
        }
        //K
        if (sortOrderId == 12)
        {
            playerPositionId = 15;
        }
        //DT
        if (sortOrderId == 13 || sortOrderId == 14)
        {
            playerPositionId = 8;
        }
        //DE
        if (sortOrderId == 15 || sortOrderId == 16)
        {
            playerPositionId = 9;
        }
        //OLB
        if (sortOrderId == 17 || sortOrderId == 19)
        {
            playerPositionId = 10;
        }
        //ILB
        if (sortOrderId == 18)
        {
            playerPositionId = 11;
        }
        //S
        if (sortOrderId == 20 || sortOrderId == 21)
        {
            playerPositionId = 12;
        }
        //CB
        if (sortOrderId == 22 || sortOrderId == 23)
        {
            playerPositionId = 13;
        }
        //P
        if (sortOrderId == 24)
        {
            playerPositionId = 14;
        }

        DynamicForm form = formFactory.form().bindFromRequest();

        String ReplaceSQL = "SELECT tp FROM TeamPlayer tp WHERE sortOrderId = :sortOrderId ";

        try
        {
            TeamPlayer replacePlayer = jpaApi.em().createQuery(ReplaceSQL, TeamPlayer.class).setParameter("sortOrderId", sortOrderId).getSingleResult();

            jpaApi.em().remove(replacePlayer);

        } catch (Exception e)
        {
            ReplaceSQL = "";
        }
        String playerIdString = form.get("Id");

        Integer playerId = Integer.parseInt(playerIdString);

        String sql = "SELECT p FROM Player p WHERE playerId = :playerId";

        Player player = jpaApi.em().createQuery(sql, Player.class).setParameter("playerId", playerId).getSingleResult();

        TeamPlayer teamPlayer = new TeamPlayer();

        teamPlayer.setTeamId(Integer.parseInt(session().get("teamId")));
        teamPlayer.setPlayerId(playerId);
        teamPlayer.setTeamPlayerName(player.getPlayerName());
        teamPlayer.setTeamPlayerValue(player.getPlayerValue());
        teamPlayer.setTeamPlayerPositionId(playerPositionId);
        teamPlayer.setSortOrderId(sortOrderId);
        jpaApi.em().persist(teamPlayer);


        return redirect("/draftteam");
    }

}
