package fastcampus.spring.batch.part3;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String age;
    private String addr;

    public Person(String name, String age, String addr) {
        this(0,name,age,addr);
    }
    public Person(int id, String name, String age, String addr) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.addr = addr;
    }
}
