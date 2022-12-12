package ir.parsa.post;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;

@ApplicationScoped
public class Seeder {

    @Transactional
    void onStart(@Observes StartupEvent ev) {

        System.out.println("startup********");
if(Post.count()==0){

        Post post1 = new Post();
        post1.title = "Accounting";
       post1.employeeId=1l;
        post1.persist();

         Post post2 = new Post();
        post2.title = "Security";
       post2.employeeId=2l;
        post2.persist();


         Post post3 = new Post();
        post3.title = "IT-MANAGER";
       post3.employeeId=3l;
        post3.persist();

}
        System.out.println(Post.findAll().list().toString());
    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("endup********");

    }
}
