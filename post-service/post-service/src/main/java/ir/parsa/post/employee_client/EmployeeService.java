package ir.parsa.post.employee_client;


import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@RegisterRestClient(baseUri = "http://localhost:7070/")
@ApplicationScoped
public interface EmployeeService {

    @GET
    @Path("/employee/search-by-national-code/{nationalCode}")
    Employee searchByNationalCode( @PathParam("nationalCode") String nationalCode);
}
