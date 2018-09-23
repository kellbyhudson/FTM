package controllers;

import com.google.common.io.Files;
import models.TakeOverOrganization;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.io.File;

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
        Integer organizationSalaryCap = Integer.parseInt(form.get("organizationsalarycap"));
        String result;

        if(takeOverOrganizationName.length() <= 30)
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
}
