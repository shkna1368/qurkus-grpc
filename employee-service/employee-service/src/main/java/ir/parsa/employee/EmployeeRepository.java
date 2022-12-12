package ir.parsa.employee;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeRepository implements PanacheRepository<Employee> {

    // put your custom logic here as instance methods

    public Employee findByName(String name){
        return find("name", name).firstResult();
    }
    public Employee findByNationalCode(String nationalCode){
        return find("nationalCode", nationalCode).firstResult();
    }


}