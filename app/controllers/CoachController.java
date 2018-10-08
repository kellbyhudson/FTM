package controllers;

import com.google.common.io.Files;
import models.Coach;
import models.CoachDetail;
import models.Team;
import play.Logger;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CoachController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public CoachController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    public Result getAddCoach()
    {
        return ok(views.html.addcoach.render());
    }

    @Transactional
    public Result postAddCoach()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String coachName = form.get("coachname");
        Integer coachValue = Integer.parseInt(form.get("coachvalue"));
        Integer coachSpecialtyId = Integer.parseInt(form.get("coachspecialtyid"));
        Integer coachTier = Integer.parseInt(form.get("coachtier"));
        String result;

        if (coachName.length() <= 50)
        {
            Coach newCoach = new Coach();
            newCoach.setCoachName(coachName);
            newCoach.setCoachValue(coachValue);
            newCoach.setCoachSpecialtyId(coachSpecialtyId);
            newCoach.setCoachTier(coachTier);

            Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart<File> filePart = formData.getFile("coachpicture");
            File file = filePart.getFile();

            byte[] picture;

            try
            {
                picture = Files.toByteArray(file);
                if (picture != null && picture.length > 0)
                {
                    newCoach.setCoachPicture(picture);
                }
            } catch (Exception e)
            {
                picture = null;
            }

            jpaApi.em().persist(newCoach);
            result = "saved";
        }
        else
        {
            result = "not saved";
        }

        return ok(result);
    }

    @Transactional
    public Result getDraftCoach()
    {
        String coachSQL = "SELECT c FROM Coach c ORDER BY CoachName";
        List<Coach> coaches = jpaApi.em().createQuery(coachSQL, Coach.class).getResultList();

        List<CoachDetail> coachDetailList = new ArrayList<>();

        for(Coach coach : coaches)
        {
            CoachDetail coachDetail = new CoachDetail();
            coachDetail.setCoachId(coach.getCoachId());
            coachDetail.setCoachName(coach.getCoachName());
            coachDetail.setCoachTier(coach.getCoachTier());
            coachDetail.setCoachValue(coach.getCoachValue());
            coachDetail.setCoachSpecialtyId(coach.getCoachSpecialtyId());
            coachDetail.setCoachPicture(coach.getCoachPicture());
            coachDetailList.add(coachDetail);
        }

        return ok(views.html.draftcoach.render(coachDetailList));
    }

    @Transactional
    public Result postDraftCoach()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String coachId = form.get("Id");

        session().put("coachId", coachId);

        String result = "";

        String teamIdText = session().get("THISteamId");

        if (teamIdText == null)
        {
            result = "/setupteam";
        }
        else
        {
            Integer teamId = Integer.parseInt(session().get("THISteamId"));

            Logger.debug("teamId is " + teamId);

            String teamSQL = "SELECT t FROM Team t WHERE teamId = :teamId ";

            Team team = jpaApi.em().createQuery(teamSQL, Team.class).setParameter("teamId", teamId).getSingleResult();

            team.setCoachId(Integer.parseInt(coachId));

            jpaApi.em().persist(team);

            try
            {
                if (session().get("THISteamId").equals(session().get("MYteamId")))
                {
                    result = "/draftteam";
                }
                else
                {
                    result = "/managethisteam/";
                    result += teamIdText;
                }
            }catch (Exception e)
            {
                result = "/draftteam";
            }
        }

        return redirect(result);
    }

    @Transactional(readOnly = true)
    public Result getCoachPicture(int coachId)
    {
        String sql = "SELECT c FROM Coach c WHERE coachId = :coachId";

        Coach coach = jpaApi.em().createQuery(sql, Coach.class).setParameter("coachId", coachId).getSingleResult();

        return ok(coach.getCoachPicture()).as("image/jpg");
    }

    @Transactional
    public Result getManageCoach()
    {
        Logger.debug("teamId is " + session().get("MYteamId"));

        String coachSQL = "SELECT c FROM Coach c ORDER BY CoachName";
        List<Coach> coaches = jpaApi.em().createQuery(coachSQL, Coach.class).getResultList();

        Integer teamId = Integer.parseInt(session().get("MYteamId"));

        session().put("THISteamId", teamId.toString());

        String coachDetailSQL = "SELECT coachId FROM Team t WHERE teamId = :teamId ";

        Integer coachId = jpaApi.em().createQuery(coachDetailSQL, Integer.class).setParameter("teamId", teamId).getSingleResult();

        CoachDetail coachDetail = new CoachDetail();

        coachDetail.setCoachId(coachId);

        return ok(views.html.managecoach.render(coaches, coachDetail));
    }

    public Result postManageCoach()
    {
        return redirect("/draftcoach");
    }

    @Transactional
    public Result getManageThisCoach(Integer teamId)
    {
        String coachSQL = "SELECT c FROM Coach c ORDER BY CoachName";
        List<Coach> coaches = jpaApi.em().createQuery(coachSQL, Coach.class).getResultList();

        session().put("THISteamId", teamId.toString());

        String coachDetailSQL = "SELECT coachId FROM Team t WHERE teamId = :teamId ";

        Integer coachId = jpaApi.em().createQuery(coachDetailSQL, Integer.class).setParameter("teamId", teamId).getSingleResult();

        CoachDetail coachDetail = new CoachDetail();

        coachDetail.setCoachId(coachId);

        return ok(views.html.managethiscoach.render(coaches, coachDetail));

    }

    public Result postManageThisCoach(Integer teamId)
    {
        return redirect("/draftcoach");
    }

}
