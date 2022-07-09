package models;

public class Lecturer extends User{
    private String name;
    private String login;
    private String password;
    private String position;
    private String faculty;
    private String departmaent;
    private String lastName;
    private String phone;

    public Lecturer() {
    }

    public Lecturer(String name, String login, String password, String position, String faculty, String departmaent, String lastName, String phone) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.position = position;
        this.faculty = faculty;
        this.departmaent = departmaent;
        this.lastName = lastName;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getDepartmaent() {
        return departmaent;
    }

    public void setDepartmaent(String departmaent) {
        this.departmaent = departmaent;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", position='" + position + '\'' +
                ", faculty='" + faculty + '\'' +
                ", departmaent='" + departmaent + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Object[] toArray(){
        return new Object[]{name, lastName, position, faculty, departmaent, phone};
    }
}
