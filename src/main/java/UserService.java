import dao.UserDao;
import models.Lecturer;
import models.Student;
import models.StudentSubjectAccessibility;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    UserDao userDao = new UserDao();
    public User findUser(String login, String password){
        User user;
        user = userDao.findAdminByLoginAndPassword(login, password);
        if (user == null){
            user = userDao.findLecturerByLoginAndPassword(login, password);
            if (user == null){
                user = userDao.findStudentByLoginAndPassword(login, password);
            }
        }
        return user;
    }

    public ArrayList<Student> findStudents(){
        return userDao.findAllStudents();
    }

    public ArrayList<Lecturer> findLectors(){
        return userDao.findAllLectors();
    }

    public String[] getGroupNames(){
        return userDao.findGroupNames();
    }

    public void addStudent(String login, String password, String speciality, String form,
                           String faculty, String name, String lastName, String phone, String group){
        userDao.addStudent(login, password, speciality, form, faculty, name, lastName, phone, group);
    }

    public void addLector(String name, String login, String password, String position,
               String faculty, String department, String lastName, String phone){
        userDao.addLector(name, login, password, position, faculty, department, lastName, phone);
    }

    public void addSubject(String name, String credits, String hours, String lectorFullName, String type){
        String[] nameLector = lectorFullName.split(" ");
        userDao.addSubject(name, Integer.parseInt(credits), Integer.parseInt(hours), nameLector[0], nameLector[1], type);
    }

    public String[] lectorsFullNames(){
        String[] lectorsFullNames;
        ArrayList<Lecturer> lecturers = userDao.findAllLectors();
        lectorsFullNames = new String[lecturers.size()];
        for (int i = 0; i < lecturers.size(); i++) {
            lectorsFullNames[i] = lecturers.get(i).getName() + " " + lecturers.get(i).getLastName();
        }
        return lectorsFullNames;
    }

    public String[] studentFullNames(){
        String[] studentFullNames;
        ArrayList<Student> students = userDao.findAllStudents();
        studentFullNames = new String[students.size()];
        for (int i = 0; i < students.size(); i++) {
            studentFullNames[i] = students.get(i).getName() + " " + students.get(i).getLastName();
        }
        return studentFullNames;
    }

    public ArrayList<String> getGroupsBySubject(String subjectName){
        return userDao.findGroupsBySubject(subjectName);
    }

    public List<String> getSubjects(String lecturerName){
        return userDao.findSubjectsByLecturer(lecturerName);
    }

    public List<String> getStudentSubjects(String studentName, String studentLastName){
        return userDao.findSubjectsByStudent(studentName, studentLastName);
    }

    public List<StudentSubjectAccessibility> getStudentsMarks(String subjectName, String group){
        return userDao.findMarksBySubjectAndGroup(subjectName, group);
    }

    public StudentSubjectAccessibility getSingleStudentMarks(String name, String lastName, String subjectName){
        return userDao.findSingleStudentMarks(name, lastName, subjectName);
    }

    public void removeStudent(String studentFullName){
        String[] nameStudent = studentFullName.split(" ");
        userDao.removeStudent(nameStudent[0], nameStudent[1]);
    }

    public void removeLector(String lectorFullName){
        String[] nameLector = lectorFullName.split(" ");
        userDao.removeLector(nameLector[0], nameLector[1]);
    }
}
