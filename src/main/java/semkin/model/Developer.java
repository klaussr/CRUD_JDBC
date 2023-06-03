package semkin.model;

public class Developer extends BaseEntity {
    private String firstName;

    private String lastName;
    private String  skill;
    private String specialty;
    private Status status;



    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String  getSkill() {
        return skill;
    }

    public void setSkill(String  skill) {
        this.skill = skill;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "id=" + getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", skill=" + skill +
                ", specialty=" + specialty +
                '}';
    }
}
