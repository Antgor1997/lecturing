package dao;

import models.Lecturer;
import models.Student;
import models.StudentSubjectAccessibility;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    static final String FIND_ALL_STUDENTS = "SELECT * FROM student";

    static final String FIND_ALL_LECTORS = "SELECT * FROM lecturer";

    static final String FIND_LECTURER_BY_AUTHORIZATION = "SELECT * FROM lecturer where (login = ? and password = ?)";

    static final String FIND_STUDENT_BY_AUTHORIZATION = "SELECT * FROM student where (login = ? and password = ?)";

    static final String FIND_ADMIN_BY_AUTHORIZATION = "SELECT * FROM administrator where (login = ? and password = ?)";

    static final String FIND_GROUPS = "SELECT name FROM groupstudent";

    static final String FIND_SUBJECTS_BY_LECTOR = "SELECT distinct name FROM subject where lecturer_id = (SELECT idlecturer FROM lecturer where name = ?)";

    static final String FIND_SUBJECTS_BY_STUDENT = "select subject.name from subject\n" +
            "inner join groupstudent_subject on idsubject = id_subject  where groupstudent_subject.id_group = \n" +
            "(select id_group from student where name = ? and lastName = ?);";

    static final String FIND_GROUPS_BY_SUBJECT_NAME = "select distinct groupstudent.name from groupstudent, subject where subject.name = ?";

    static final String ADD_STUDENT = "insert into student values(default, ?, ?, ?, ?, ?, ?, ?, ?, (select idGroup from groupstudent where name = ?))";

    static final String ADD_LECTOR = "insert into lecturer values(default, ?, ?, ?, ?, ?, ?, ?, ?)";

    static final String ADD_SUBJECT = "insert into subject values(default, ?, ?, ?, " +
            "(select idlecturer from lecturer where lecturer.name = ? and lecturer.lastName = ?), ?);";
    static final String FIND_SINGLE_STUDENT_MARKS = "select student.name, student.lastName, mark.firstLab, mark.secondLab, mark.thirdLab, mark.fourthLab, mark.fifthLab, mark.sixthLab, mark.module, mark.total \n" +
            "from mark inner join student on mark.id_student = student.idstudent\n" +
            "where mark.id_subject = (select idsubject from subject where name = ?) \n" +
            "and student.idstudent = (select idstudent from student where name = ? and lastName = ?);";

    static final String FIND_MARKS_BY_SUBJECT_AND_GROUP = "select student.name, student.lastName, mark.firstLab, mark.secondLab, mark.thirdLab, mark.fourthLab, " +
            "mark.fifthLab, mark.sixthLab, mark.module, mark.total " +
            "from mark " +
            "inner join student on mark.id_student = student.idstudent " +
            "where mark.id_subject = (select idsubject from subject where name = ?) " +
            "and student.id_group = (select idgroup from groupstudent where name = ?)";

    static final String REMOVE_LECTOR = "delete from lecturer where name = ? and lastName = ?";
    static final String REMOVE_STUDENT = "delete from student where name = ? and lastName = ?";

    private Lecturer makeLectorFromResultSet(ResultSet resultSet) throws SQLException {
        Lecturer lecturer = new Lecturer();
        lecturer.setLogin(resultSet.getString("login"));
        lecturer.setPassword(resultSet.getString("password"));
        lecturer.setDepartmaent(resultSet.getString("department"));
        lecturer.setFaculty(resultSet.getString("faculty"));
        lecturer.setName(resultSet.getString("name"));
        lecturer.setLastName(resultSet.getString("lastName"));
        lecturer.setPhone(resultSet.getString("phone"));
        lecturer.setPosition(resultSet.getString("position"));
        lecturer.setTypeUser("Lector");
        return lecturer;
    }

    private Student makeStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setLogin(resultSet.getString("login"));
        student.setPassword(resultSet.getString("password"));
        student.setName(resultSet.getString("name"));
        student.setLastName(resultSet.getString("lastName"));
        student.setPhone(resultSet.getString("phone"));
        student.setSpeciality(resultSet.getString("speciality"));
        student.setFaculty(resultSet.getString("faculty"));
        student.setForm(resultSet.getInt("form"));
        student.setTypeUser("Student");
        return student;
    }

    public Lecturer findLecturerByLoginAndPassword(String login, String password){
        Lecturer lecturer = null;
        ResultSet resultSet;
        try {
           resultSet = QueryExecutor.getInstance().getResultSet(FIND_LECTURER_BY_AUTHORIZATION, login, password);
            if (resultSet.next()){
                lecturer = makeLectorFromResultSet(resultSet);
            }
            QueryExecutor.getInstance().closeConnection();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lecturer;
    }

    public Student findStudentByLoginAndPassword(String login, String password){
        Student student = null;
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_STUDENT_BY_AUTHORIZATION, login, password);
            if (resultSet.next()){
                student = makeStudentFromResultSet(resultSet);
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return student;
    }

    public User findAdminByLoginAndPassword(String login, String password){
        User user = null;
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_ADMIN_BY_AUTHORIZATION, login, password);
            if (resultSet.next()){
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setTypeUser("Admin");
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    public ArrayList<Student> findAllStudents(){
        ArrayList<Student> students = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_ALL_STUDENTS);

            while (resultSet.next()){
                students.add(makeStudentFromResultSet(resultSet));
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return students;
    }

    public ArrayList<Lecturer> findAllLectors(){
        ArrayList<Lecturer> lecturers = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_ALL_LECTORS);
            while (resultSet.next()){
                lecturers.add(makeLectorFromResultSet(resultSet));
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return lecturers;
    }

    public String[] findGroupNames(){
        String[] groupNames;
        ArrayList<String> groups = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_GROUPS);
            while (resultSet.next()){
                groups.add(resultSet.getString("name"));
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        groupNames = new String[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            groupNames[i] = groups.get(i);
        }
        return groupNames;
    }

    public void addStudent(String login, String password, String speciality, String form, String faculty, String name, String lastName,
                           String phone, String group){
        try {
            QueryExecutor.getInstance().updateDatabase(ADD_STUDENT, login, password, speciality, form, faculty, name, lastName, phone, group);
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addLector(String name, String login, String password, String position, String faculty, String department, String lastName,
                           String phone){
        try {
            QueryExecutor.getInstance().updateDatabase(ADD_LECTOR, name, login, password, position, faculty, department, lastName, phone);
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void addSubject(String name, Integer credits, Integer hours, String lecturerName,
                           String lecturerLastName, String type){
        try {
            QueryExecutor.getInstance().updateDatabase(ADD_SUBJECT, name, credits, hours, lecturerName, lecturerLastName, type);
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<String> findGroupsBySubject(String subjectName){
        ArrayList<String> groups = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_GROUPS_BY_SUBJECT_NAME, subjectName);
            while (resultSet.next()){
                groups.add(resultSet.getString("name"));
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return groups;
    }

    public ArrayList<String> findSubjectsByLecturer(String lecturerName){
        ArrayList<String> subjects = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_SUBJECTS_BY_LECTOR, lecturerName);
            while (resultSet.next()){
                subjects.add(resultSet.getString("name"));
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    public ArrayList<String> findSubjectsByStudent(String studentName, String studentLastName){
        ArrayList<String> subjects = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_SUBJECTS_BY_STUDENT, studentName, studentLastName);
            while (resultSet.next()){
                subjects.add(resultSet.getString("name"));
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    private StudentSubjectAccessibility makeMarks(ResultSet resultSet) throws SQLException {
        StudentSubjectAccessibility studentSubjectAccessibility = new StudentSubjectAccessibility();
        studentSubjectAccessibility.setName(resultSet.getString("name"));
        studentSubjectAccessibility.setLastName(resultSet.getString("lastName"));
        studentSubjectAccessibility.setFirstPractice(resultSet.getInt("firstLab"));
        studentSubjectAccessibility.setSecondPractice(resultSet.getInt("secondLab"));
        studentSubjectAccessibility.setThirdPractice(resultSet.getInt("thirdLab"));
        studentSubjectAccessibility.setFourthPractice(resultSet.getInt("fourthLab"));
        studentSubjectAccessibility.setFifthPractice(resultSet.getInt("fifthLab"));
        studentSubjectAccessibility.setSixthPractice(resultSet.getInt("sixthLab"));
        studentSubjectAccessibility.setModule(resultSet.getInt("module"));
        studentSubjectAccessibility.setTotal(resultSet.getInt("total"));
        return studentSubjectAccessibility;
    }

    public List<StudentSubjectAccessibility> findMarksBySubjectAndGroup(String subjectName, String group){
        List<StudentSubjectAccessibility> studentMarks = new ArrayList<>();
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_MARKS_BY_SUBJECT_AND_GROUP, subjectName, group);
            while (resultSet.next()){
                studentMarks.add(makeMarks(resultSet));
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return studentMarks;
    }

    public StudentSubjectAccessibility findSingleStudentMarks(String name, String lastName, String subjectName){
        StudentSubjectAccessibility studentMarks = null;
        ResultSet resultSet;
        try {
            resultSet = QueryExecutor.getInstance().getResultSet(FIND_SINGLE_STUDENT_MARKS, subjectName, name, lastName);
            if (resultSet.next()) {
                studentMarks = makeMarks(resultSet);
            }
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return studentMarks;
    }

    public void removeLector(String name, String lastName){
        try {
            QueryExecutor.getInstance().updateDatabase(REMOVE_LECTOR, name, lastName);
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void removeStudent(String name, String lastName){
        try {
            QueryExecutor.getInstance().updateDatabase(REMOVE_STUDENT, name, lastName);
            QueryExecutor.getInstance().closeConnection();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}