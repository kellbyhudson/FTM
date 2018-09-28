package controllers;

import com.google.common.io.Files;
import models.TakeOverOrganization;
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
import java.math.BigDecimal;
import java.util.List;

public class OrganizationController extends Controller
{
    private JPAApi jpaApi;
    private FormFactory formFactory;

    @Inject
    public OrganizationController(JPAApi jpaApi, FormFactory formFactory)
    {
        this.jpaApi = jpaApi;
        this.formFactory = formFactory;
    }

    public Result getAddDatabaseOrganization()
    {
        return ok(views.html.adddatabaseorganization.render());
    }

    @Transactional
    public Result postAddDatabaseOrganization()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String takeOverOrganizationName = form.get("organizationname");
        BigDecimal organizationSalaryCap = new BigDecimal(form.get("organizationsalarycap"));
        String result;

        if (takeOverOrganizationName.length() <= 30)
        {
            TakeOverOrganization newTakeOverOrganization = new TakeOverOrganization();
            newTakeOverOrganization.setTakeOverOrganizationName(takeOverOrganizationName);
            newTakeOverOrganization.setOrganizationSalaryCap(organizationSalaryCap);


            Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
            Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
            File file = filePart.getFile();

            byte[] picture;

            try
            {
                picture = Files.toByteArray(file);
                if (picture != null && picture.length > 0)
                {
                    newTakeOverOrganization.setPicture(picture);
                }
            } catch (Exception e)
            {
                picture = null;
            }

            jpaApi.em().persist(newTakeOverOrganization);
            result = "saved";
        }
        else
        {
            result = "not saved";
        }

        return ok(result);
    }

    @Transactional
    public Result getDraftOrganization()
    {
        String takeOverOrganizationSQL = "SELECT t FROM TakeOverOrganization t ORDER BY TakeOverOrganizationName";
        List<TakeOverOrganization> takeOverOrganizations = jpaApi.em().createQuery(takeOverOrganizationSQL, TakeOverOrganization.class).getResultList();

        return ok(views.html.draftorganization.render(takeOverOrganizations));
    }

    public Result postDraftOrganization()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String organizationId = form.get("Id");

        session().put("takeOverOrganizationId", organizationId);

        //Logger.debug("Id is" + organizationId);

        return redirect("/draftcoach");
    }

    @Transactional(readOnly = true)
    public Result getOrganizationPicture(int takeOverOrganizationId)
    {
        String sql = "SELECT t FROM TakeOverOrganization t WHERE takeOverOrganizationId = :takeOverOrganizationId";

        TakeOverOrganization takeOverOrganization = jpaApi.em().createQuery(sql, TakeOverOrganization.class).setParameter("takeOverOrganizationId", takeOverOrganizationId).getSingleResult();

        return ok(takeOverOrganization.getPicture()).as("image/jpg");
    }
}
