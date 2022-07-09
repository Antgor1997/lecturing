package models;

public class Student extends User {
    private String name;
    private String login;
    private String password;
    private String speciality;
    private Integer form;
    private String faculty;
    private String lastName;
    private String phone;



    public Student() {}


    public Student(String name, String login, String password, String speciality, Integer form, String faculty, String lastName, String phone) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.speciality = speciality;
        this.form = form;
        this.faculty = faculty;
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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Integer getForm() {
        return form;
    }

    public void setForm(Integer form) {
        this.form = form;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
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
        return "Student{" +
                "name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", speciality='" + speciality + '\'' +
                ", form=" + form +
                ", faculty='" + faculty + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public Object[] toArray(){
        return new Object[]{name, lastName, speciality, form, faculty, phone};
    }
}
