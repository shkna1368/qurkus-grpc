package ir.parsa.post;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import ir.parsa.post.employee_client.Employee;
import ir.parsa.post.employee_client.EmployeeService;
import ir.parsa.post.employee_client.PostEmployee;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/post")
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {
    @Inject
    @RestClient
    EmployeeService employeeService;



    @GrpcClient("employeeGService")
    GEmployeeServiceGrpc.GEmployeeServiceBlockingStub employeeGServiceBlocking;



    @GrpcClient("employeeGService")
    GEmployeeService gEmployeeService;




    @GET
    public List<Post> list() {
        return Post.listAll();
    }

    @Transactional
    @GET
    @Path("/{id}")
    public Post get(@PathParam("id") Long id) {
        return Post.findById(id);
    }

    @POST
    @Transactional
    public Response create(Post post) {
        post.persist();
        return Response.created(URI.create("/posts/" + post.id)).build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Post update(@PathParam("id") Long id, Post post) {
        Post entity = Post.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }

        // map all fields from the person parameter to the existing entity
        entity.title = post.title;;

        return entity;
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        Post entity = Post.findById(id);
        if(entity == null) {
            throw new NotFoundException();
        }
        entity.delete();
    }

    @GET
    @Path("/search-by-title/{title}")
    public Post searchByTitle(@PathParam("title") String title) {
        return Post.findByTitle(title);
    }


    @GET
    @Path("/rest/search-by-employee-Id/{employeeId}")
    public Post searchEmployeeId(@PathParam("employeeId") Long employeeId) {
        return Post.findByEmployeeId(employeeId);
    }

      @GET
    @Path("/rest/search-post-by-employee-national-code/{nationalCode}")
    public PostEmployee searchEmployeeId(@PathParam("nationalCode") String nationalCode) {


      Employee employee= employeeService.searchByNationalCode(nationalCode);
          Post post= Post.findByEmployeeId(employee.getId());

          return new PostEmployee(post,employee);

    }



     @GET
    @Path("/grpc/search-by-national-code/{nationalCode}")
     @Transactional

     public PostEmployee searchByNationalCodeWithGRPC(@PathParam("nationalCode") String nationalCode) {

       // Uni<EmployeeResponse> employee1 = gEmployeeService.getEmployee(EmployeeRequest.newBuilder().setNationalCode(nationalCode).build());

      //   employee1.subscribe().with(employeeResponse -> System.out.println(employeeResponse.getFamily()));
      //   String family= employee1.onItem().transform(employeeResponse -> employeeResponse.getFamily()).toString();
         EmployeeRequest empReq = EmployeeRequest.newBuilder().setNationalCode(nationalCode).build();
         EmployeeResponse employeeResponse = employeeGServiceBlocking.getEmployee(empReq);

       Employee employee=new Employee(employeeResponse.getId(),employeeResponse.getName(),employeeResponse.getFamily(),employeeResponse.getNationalCode());




         Post post= Post.findByEmployeeId(employee.getId());

         PostEmployee postEmployee=new PostEmployee(post,employee);

         return postEmployee;
    }






    @GET
    @Path("/count")
    public Long count() {
        return Post.count();
    }
}