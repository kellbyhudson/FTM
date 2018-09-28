package controllers;

import com.google.common.io.Files;
import models.Coach;
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

        if(coachName.length() <= 50)
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
        String takeOverOrganizationSQL = "SELECT c FROM Coach c ORDER BY CoachName";
        List<Coach> coaches = jpaApi.em().createQuery(takeOverOrganizationSQL, Coach.class).getResultList();

        return ok(views.html.draftcoach.render(coaches));
    }

    public Result postDraftCoach()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String coachId = form.get("Id");

        session().put("coachId", coachId);

        //Logger.debug("Id is" + coachId);

        return redirect("/setupteam");
    }

    @Transactional(readOnly = true)
    public Result getCoachPicture(int coachId)
    {
        String sql = "SELECT c FROM Coach c WHERE coachId = :coachId";

        Coach coach = jpaApi.em().createQuery(sql, Coach.class).setParameter("coachId", coachId).getSingleResult();

        return ok(coach.getCoachPicture()).as("image/jpg");
    }
}
