package ir.parsa.employee;

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
if(Employee.count()==0){
        Employee emp1 = new Employee();
        emp1.name = "Reza";
       emp1.family="ahmadi";
       emp1.nationalCode="1234567890";
        emp1.persist();

        Employee emp2 = new Employee();
        emp2.name = "AHMAD";
       emp2.family="REZAEE";
       emp2.nationalCode="9876543210";
        emp2.persist();


        Employee emp3 = new Employee();
        emp3.name = "JAMSHID";
        emp2.family="REZAVAND";
        emp3.nationalCode="6549873210";
        emp3.persist();


}
        System.out.println( Employee.findByNationalCode("1234567890"));
        System.out.println(Employee.findAll().list().toString());
    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("endup********");

    }
}
