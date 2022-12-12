package ir.parsa.post;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;


@Entity
public class Post extends PanacheEntity {
    public String title;

    public Long employeeId;


    public static Post findByTitle(String name){
        return find("title", name).firstResult();
    }
    public static Post findByEmployeeId(Long employeeId){
        return find("employeeId", employeeId).firstResult();
    }



/*
*
*
// creating a person
Person person = new Person();
person.name = "Stef";
person.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
person.status = Status.Alive;

// persist it
person.persist();

// note that once persisted, you don't need to explicitly save your entity: all
// modifications are automatically persisted on transaction commit.

// check if it's persistent
if(person.isPersistent()){
    // delete it
    person.delete();
}

// getting a list of all Person entities
List<Person> allPersons = Person.listAll();

// finding a specific person by ID
person = Person.findById(personId);

// finding a specific person by ID via an Optional
Optional<Person> optional = Person.findByIdOptional(personId);
person = optional.orElseThrow(() -> new NotFoundException());

// finding all living persons
List<Person> livingPersons = Person.list("status", Status.Alive);

// counting all persons
long countAll = Person.count();

// counting all living persons
long countAlive = Person.count("status", Status.Alive);

// delete all living persons
Person.delete("status", Status.Alive);

// delete all persons
Person.deleteAll();

// delete by id
boolean deleted = Person.deleteById(personId);

// set the name of all living persons to 'Mortal'
Person.update("name = 'Mortal' where status = ?1", Status.Alive);
*
*
*
*
*
* */

    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", employeeId=" + employeeId +
                '}';
    }
}