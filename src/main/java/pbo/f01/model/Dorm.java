package pbo.f01.model;
import javax.persistence.*;
import java.util.List;

public class Dorm {
    private String name;
    private String gender;
    private int capacity;

    private List<Student> students;

    //construct
    public Dorm() {}
        public Dorm(String name, int capacity, String gender) {
            this.name = name;
            this.capacity = capacity;
            this.gender = gender;

        }
//getter dan setternya
public String getName() {
    return name;
}
public void setName(String name) {
    this.name = name;
}

public String getGender() {
    return gender;
}

public void setGender(String gender){
    this.gender = gender;
}

public int getCapacity(){
    return capacity;
}

public void setCapacity(int capacity) {
    this.capacity = capacity;
}

public List<Student> getStudents () {
    return students;
}

public void setStudents(List<Student> students) {
    this.students = students;
}
// public List<Student> getStudents() {
//     // TODO Auto-generated method stub
//     throw new UnsupportedOperationException("Unimplemented method 'getStudents'");
// }
}

