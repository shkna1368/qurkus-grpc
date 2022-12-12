package ir.parsa.post.employee_client;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;


@Data
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
    private String family;
    private String nationalCode;




}