package ir.parsa.employee;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {

    @GET
    public List<Employee> list() {
        return Employee.listAll();
    }

    @Transactional
    @GET
    @Path("/{id}")
    public Employee get(@PathParam("id") Long id) {
        return Employee.findById(id);
    }

    @POST
    @Transactional
    public Response create(Employee person) {
        person.persist();
        return Response.created(URI.create("/employees/" + person.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Employee update(@PathParam("id") Long id, Employee employee) {
        Employee entity = Employee.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        entity.name = employee.name;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Employee entity = Employee.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @GET
    @Path("/search-by-name/{name}")
    public Employee search(@PathParam("name") String name) {
        return Employee.findByName(name);
    }


    @GET
    @Path("/search-by-national-code/{nationalCode}")
    public Employee searchByNationalCode(@PathParam("nationalCode") String nationalCode) {
        return Employee.findByNationalCode(nationalCode);
    }




    @GET
    @Path("/count")
    public Long count() {
        return Employee.count();
    }
}