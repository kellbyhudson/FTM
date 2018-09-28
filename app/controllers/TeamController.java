package controllers;

import models.*;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import sun.security.ssl.KerberosClientKeyExchange;

import javax.inject.Inject;
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

        TeamDetail teamDetail = new TeamDetail();
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

        String teamPlayersSQL = "SELECT tp FROM TeamPlayer tp ";

        List<TeamPlayer> teamPlayers = jpaApi.em().createQuery(teamPlayersSQL, TeamPlayer.class).getResultList();

        int wideReceiverCount = 0;
        int tackleCount = 0;
        int guardCount = 0;
        int defensiveTackleCount = 0;
        int defensiveEndCount = 0;
        int outsideLinebackerCount = 0;
        int safetyCount = 0;
        int cornerbackCount = 0;

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

        TeamDisplay teamDisplay = new TeamDisplay(quarterback,runningBack,wideReceiver1,wideReceiver2,wideReceiver3,tightEnd,tackle1,tackle2,guard1,guard2,center,defensiveTackle1,defensiveTackle2,defensiveEnd1,defensiveEnd2,outsideLinebacker1,outsideLinebacker2,insideLinebacker,safety1,safety2,cornerback1,cornerback2,punter,kicker);


        for (TeamPlayer teamPlayer : teamPlayers)
        {
            switch (teamPlayer.getTeamPlayerPositionId())
            {
                case 1:
                    quarterback.setName(teamPlayer.getTeamPlayerName());
                    quarterback.setValue(teamPlayer.getTeamPlayerValue());
                    teamDisplay.setQuarterback(quarterback);
                    break;
                case 2:
                    runningBack.setName(teamPlayer.getTeamPlayerName());
                    runningBack.setValue(teamPlayer.getTeamPlayerValue());
                    teamDisplay.setRunningBack(runningBack);
                    break;
                case 3:
                    if (wideReceiverCount == 0)
                    {
                        wideReceiver1.setName(teamPlayer.getTeamPlayerName());
                        wideReceiver1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setWideReceiver1(wideReceiver1);
                        wideReceiverCount++;
                    }
                    else if (wideReceiverCount == 1)
                    {
                        wideReceiver2.setName(teamPlayer.getTeamPlayerName());
                        wideReceiver2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setWideReceiver2(wideReceiver2);
                        wideReceiverCount++;
                    }
                    else
                    {
                        wideReceiver3.setName(teamPlayer.getTeamPlayerName());
                        wideReceiver3.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setWideReceiver3(wideReceiver3);
                        wideReceiverCount = 0;
                    }
                    break;
                case 4:
                    tightEnd.setName(teamPlayer.getTeamPlayerName());
                    tightEnd.setValue(teamPlayer.getTeamPlayerValue());
                    teamDisplay.setTightEnd(tightEnd);
                    break;
                case 5:
                    if (tackleCount == 0)
                    {
                        tackle1.setName(teamPlayer.getTeamPlayerName());
                        tackle1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setTackle1(tackle1);
                        tackleCount++;
                    }
                    else
                    {
                        tackle2.setName(teamPlayer.getTeamPlayerName());
                        tackle2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setTackle2(tackle2);
                        tackleCount = 0;
                    }
                    break;
                case 6:
                    if (guardCount == 0)
                    {
                        guard1.setName(teamPlayer.getTeamPlayerName());
                        guard1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setGuard1(guard1);
                        guardCount++;
                    }
                    else
                    {
                        guard2.setName(teamPlayer.getTeamPlayerName());
                        guard2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setGuard2(guard2);
                        guardCount = 0;
                    }
                    break;
                case 7:
                    center.setName(teamPlayer.getTeamPlayerName());
                    center.setValue(teamPlayer.getTeamPlayerValue());
                    teamDisplay.setCenter(center);
                    break;
                case 8:
                    if (defensiveTackleCount == 0)
                    {
                        defensiveTackle1.setName(teamPlayer.getTeamPlayerName());
                        defensiveTackle1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setDefensiveTackle1(defensiveTackle1);
                        defensiveTackleCount++;
                    }
                    else
                    {
                        defensiveTackle2.setName(teamPlayer.getTeamPlayerName());
                        defensiveTackle2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setDefensiveTackle2(defensiveTackle2);
                        defensiveTackleCount = 0;
                    }
                    break;
                case 9:
                    if (defensiveEndCount == 0)
                    {
                        defensiveEnd1.setName(teamPlayer.getTeamPlayerName());
                        defensiveEnd1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setDefensiveEnd1(defensiveEnd1);
                        defensiveEndCount++;
                    }
                    else
                    {
                        defensiveEnd2.setName(teamPlayer.getTeamPlayerName());
                        defensiveEnd2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setDefensiveEnd2(defensiveEnd2);
                        defensiveEndCount = 0;
                    }
                    break;
                case 10:
                    if (outsideLinebackerCount == 0)
                    {
                        outsideLinebacker1.setName(teamPlayer.getTeamPlayerName());
                        outsideLinebacker1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setOutsideLinebacker1(outsideLinebacker1);
                        outsideLinebackerCount++;
                    }
                    else
                    {
                        outsideLinebacker2.setName(teamPlayer.getTeamPlayerName());
                        outsideLinebacker2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setOutsideLinebacker2(outsideLinebacker2);
                        outsideLinebackerCount = 0;
                    }
                    break;
                case 11:
                    insideLinebacker.setName(teamPlayer.getTeamPlayerName());
                    insideLinebacker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDisplay.setInsideLinebacker(insideLinebacker);
                    break;
                case 12:
                    if (safetyCount == 0)
                    {
                        safety1.setName(teamPlayer.getTeamPlayerName());
                        safety1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setSafety1(safety1);
                        safetyCount++;
                    }
                    else
                    {
                        safety2.setName(teamPlayer.getTeamPlayerName());
                        safety2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setSafety2(safety2);
                        safetyCount = 0;
                    }
                    break;
                case 13:
                    if (cornerbackCount == 0)
                    {
                        cornerback1.setName(teamPlayer.getTeamPlayerName());
                        cornerback1.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setCornerback1(cornerback1);
                        cornerbackCount++;
                    }
                    else
                    {
                        cornerback2.setName(teamPlayer.getTeamPlayerName());
                        cornerback2.setValue(teamPlayer.getTeamPlayerValue());
                        teamDisplay.setCornerback2(cornerback2);
                        cornerbackCount = 0;
                    }
                    break;
                case 14:
                    punter.setName(teamPlayer.getTeamPlayerName());
                    punter.setValue(teamPlayer.getTeamPlayerValue());
                    teamDisplay.setPunter(punter);
                    break;
                case 15:
                    kicker.setName(teamPlayer.getTeamPlayerName());
                    kicker.setValue(teamPlayer.getTeamPlayerValue());
                    teamDisplay.setKicker(kicker);
                    break;
            }
        }

        return ok(views.html.draftteam.render(teamDetail, teamDisplay));
    }

    public Result postDraftTeam()
    {

        return ok("saved");
    }

    @Transactional
    public Result getDraftPlayer(int playerPositionId)
    {
        String playerPositionSQL = "SELECT pp FROM PlayerPosition pp WHERE playerPositionId = :playerPositionId ";

        PlayerPosition playerPosition = jpaApi.em().createQuery(playerPositionSQL, PlayerPosition.class).setParameter("playerPositionId", playerPositionId).getSingleResult();

        String playerSQL = "SELECT p FROM Player p WHERE playerPositionId = :playerPositionId ORDER BY playerValue desc";

        List<Player> players = jpaApi.em().createQuery(playerSQL, Player.class).setParameter("playerPositionId", playerPositionId).getResultList();

        return ok(views.html.draftplayer.render(playerPosition, players));
    }

    @Transactional
    public Result postDraftPlayer(int playerPositionId)
    {
        DynamicForm form = formFactory.form().bindFromRequest();

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
        jpaApi.em().persist(teamPlayer);


        return redirect("/draftteam");
    }

}
