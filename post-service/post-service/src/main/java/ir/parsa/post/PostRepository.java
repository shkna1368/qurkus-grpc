package ir.parsa.post;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepository implements PanacheRepository<Post> {

    // put your custom logic here as instance methods

    public Post findByTilte(String title){
        return find("title", title).firstResult();
    }
    public Post findByEmployeeId(Long employeeId){
        return find("employeeId", employeeId).firstResult();
    }


}