import models.Lecturer;
import models.Student;
import models.StudentSubjectAccessibility;
import models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static JFrame jFrame;
    static JPanel jPanel;
    static User user;

    static Dimension dimension;

    static Toolkit toolkit;

    static UserService userService;

    static {
        jFrame = new JFrame();
        jPanel = new JPanel();
        jFrame.add(jPanel);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 500, 500);
        jFrame.setTitle("lecturing app");
    }

    public static void authorization(){
        jPanel.removeAll();
        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 350, 150);
        user = null;
        JTextField loginField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        jPanel.add(new JLabel("Your login:"));
        jPanel.add(loginField);
        jPanel.add(new JLabel("Your password:"));
        jPanel.add(passwordField);
        JButton confirmButton = new JButton("Ok");
        jPanel.add(confirmButton);
        jPanel.revalidate();
        JLabel warningLabel = new JLabel("Wrong parameters! Please reinput.");
        warningLabel.setVisible(false);
        warningLabel.setForeground(Color.RED);
        jPanel.add(warningLabel);
        confirmButton.addActionListener(e -> {
            userService = new UserService();
            user = userService.findUser(loginField.getText(), passwordField.getText());
            if (user == null){
                warningLabel.setVisible(true);
            }else {
                if (user.getTypeUser().equals("Admin")){
                    initializeAdmin();
                }else if (user.getTypeUser().equals("Lector")){
                    initializeLecturer();
                }else {
                    initializeStudent();
                }
            }
        });
    }

    public static void initializeAdmin(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 250, 150);


        JButton studentsButton = new JButton("Students");
        jPanel.add(studentsButton);
        studentsButton.addActionListener(e -> makeStudentsList());
        JButton lectorsButton = new JButton("Lectors");
        jPanel.add(lectorsButton);
        lectorsButton.addActionListener(e -> makeLectorsList());
        JButton addingSubjectButton = new JButton("Add subject");
        jPanel.add(addingSubjectButton);
        addingSubjectButton.addActionListener(e -> addSubject());
        JButton logout = new JButton("LogOut");
        jPanel.add(logout);
        logout.addActionListener(e -> authorization());
        jFrame.revalidate();
    }

    public static void makeStudentsList(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 500, 500);


        ArrayList<Student> students = userService.findStudents();
        jFrame.setTitle("Students");
        JTable studentsTable = makeTableStudents(students);
        JScrollPane sp = new JScrollPane(studentsTable);
        jPanel.add(sp);

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeAdmin());

        JButton addStudentButton = new JButton("Add student");
        jPanel.add(addStudentButton);
        addStudentButton.addActionListener(e -> addStudent());

        JButton removeStudentButton = new JButton("Remove student");
        jPanel.add(removeStudentButton);
        removeStudentButton.addActionListener(e -> removeStudent());

        jFrame.revalidate();
    }

    public static void makeLectorsList(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);
        ArrayList<Lecturer> lecturers = userService.findLectors();

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 500, 500);

        jFrame.setTitle("Lecturers");
        JTable lectorsTable = makeTableLectors(lecturers);
        lectorsTable.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(lectorsTable);
        jPanel.add(sp);

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeAdmin());

        JButton addLectorButton = new JButton("Add lector");
        jPanel.add(addLectorButton);
        addLectorButton.addActionListener(e -> addLector());

        JButton removeLectorButton = new JButton("Remove lector");
        jPanel.add(removeLectorButton);
        removeLectorButton.addActionListener(e -> removeLector());

        jFrame.revalidate();
    }

    public static void addStudent(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 250, 450);

        JTextField loginField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField nameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField specialityField = new JTextField(20);
        Integer[] forms = {1,2,3,4};
        JComboBox formField = new JComboBox(forms);
        JTextField facultyField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        String[] groups = userService.getGroupNames();
        JComboBox groupField = new JComboBox(groups);


        JLabel loginLabel = new JLabel("Login:");
        JLabel passwordLabel = new JLabel("Password:");


        jPanel.add(loginLabel);
        jPanel.add(loginField);

        jPanel.add(passwordLabel);
        jPanel.add(passwordField);

        jPanel.add(new JLabel("Name:"));
        jPanel.add(nameField);
        jPanel.add(new JLabel("Last Name:"));
        jPanel.add(lastNameField);
        jPanel.add(new JLabel("Speciality:"));
        jPanel.add(specialityField);
        jPanel.add(new JLabel("Form:"));
        jPanel.add(formField);
        jPanel.add(new JLabel("Faculty:"));
        jPanel.add(facultyField);
        jPanel.add(new JLabel("Phone:"));
        jPanel.add(phoneField);
        jPanel.add(new JLabel("Group:"));
        jPanel.add(groupField);

        JButton confirmButton = new JButton("Ok");
        jPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            userService.addStudent(loginField.getText(), passwordField.getText(),
                    specialityField.getText(), formField.getSelectedItem().toString(), facultyField.getText(),
                    nameField.getText(), lastNameField.getText(), phoneField.getText(), groupField.getSelectedItem().toString());
            makeStudentsList();
        });

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeAdmin());

        jFrame.revalidate();
    }

    public static void removeStudent(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 250, 100);

        String[] studentFullNames = userService.studentFullNames();
        JComboBox studentsField = new JComboBox(studentFullNames);
        jPanel.add(new JLabel("Remove student:"));
        jPanel.add(studentsField);

        JButton confirmButton = new JButton("Ok");
        jPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            userService.removeStudent(studentsField.getSelectedItem().toString());
            makeStudentsList();
        });

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeAdmin());
        jFrame.revalidate();
    }

    public static void addLector(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width / 2 - 250, dimension.height / 2 - 250, 250, 450);

        JTextField loginField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JTextField nameField = new JTextField(20);
        JTextField lastNameField = new JTextField(20);
        JTextField positionField = new JTextField(20);
        JTextField facultyField = new JTextField(20);
        JTextField departmentField = new JTextField(20);
        JTextField phoneField = new JTextField(20);

        jPanel.add(new JLabel("Login:"));
        jPanel.add(loginField);
        jPanel.add(new JLabel("Password:"));
        jPanel.add(passwordField);
        jPanel.add(new JLabel("Name:"));
        jPanel.add(nameField);
        jPanel.add(new JLabel("Last Name:"));
        jPanel.add(lastNameField);
        jPanel.add(new JLabel("Position:"));
        jPanel.add(positionField);
        jPanel.add(new JLabel("Faculty:"));
        jPanel.add(facultyField);
        jPanel.add(new JLabel("Department:"));
        jPanel.add(departmentField);
        jPanel.add(new JLabel("Phone:"));
        jPanel.add(phoneField);

        JButton confirmButton = new JButton("Ok");
        jPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            userService.addLector(nameField.getText(), loginField.getText(), passwordField.getText(),
                    positionField.getText(), facultyField.getText(), departmentField.getText(), lastNameField.getText(),  phoneField.getText());
            makeLectorsList();
        });

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeAdmin());

        jFrame.revalidate();
    }

    public static void addSubject(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 225, 300);

        JTextField nameField = new JTextField(20);
        JTextField creditsField = new JTextField(20);
        JTextField hoursField = new JTextField(20);
        String[] lectorsFullNames = userService.lectorsFullNames();
        JComboBox lectorsField = new JComboBox(lectorsFullNames);
        String[] forms = {"Ekzamen", "Zalik"};
        JComboBox typeField = new JComboBox(forms);


        jPanel.add(new JLabel("Name of subject:"));
        jPanel.add(nameField);
        jPanel.add(new JLabel("Credits:"));
        jPanel.add(creditsField);
        jPanel.add(new JLabel("Hours:"));
        jPanel.add(hoursField);
        jPanel.add(new JLabel("Lector:"));
        jPanel.add(lectorsField);
        jPanel.add(new JLabel("Type of subject:"));
        jPanel.add(typeField);



        JButton confirmButton = new JButton("Ok");
        jPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            userService.addSubject(nameField.getText(), creditsField.getText(), hoursField.getText(),
                    lectorsField.getSelectedItem().toString(), typeField.getSelectedItem().toString());
            initializeAdmin();
        });

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeAdmin());
        jFrame.revalidate();
    }

    public static void removeLector(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 250, 100);

        String[] lectorsFullNames = userService.lectorsFullNames();
        JComboBox lectorsField = new JComboBox(lectorsFullNames);
        jPanel.add(new JLabel("Remove lector:"));
        jPanel.add(lectorsField);

        JButton confirmButton = new JButton("Ok");
        jPanel.add(confirmButton);
        confirmButton.addActionListener(e -> {
            userService.removeLector(lectorsField.getSelectedItem().toString());
            makeLectorsList();
        });

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeAdmin());
        jFrame.revalidate();
    }

    public static JTable makeTableStudents(ArrayList<Student> students){
        JTable studentsTable;
        Object[] titles = {"name", "lastName", "speciality", "form", "faculty", "phone"};
        Object[][] data = new Object[students.size()][6];
        for (int i = 0; i < students.size(); i++){
            data[i] = students.get(i).toArray();
        }
        studentsTable = new JTable(data, titles);
        return studentsTable;
    }

    public static JTable makeTableLectors(ArrayList<Lecturer> lecturers){
        JTable lectorsTable;
        Object[] titles = {"name", "lastName", "position", "faculty", "departmaent", "phone"};
        Object[][] data = new Object[lecturers.size()][6];
        for (int i = 0; i < lecturers.size(); i++){
            data[i] = lecturers.get(i).toArray();
        }
        lectorsTable = new JTable(data, titles);
        return lectorsTable;
    }

    public static void initializeLecturer(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 250, 150);

        JButton subjectsButton = new JButton("My subjects");
        jPanel.add(subjectsButton);
        subjectsButton.addActionListener(e -> makeSubjectForLectorWindow());
        JButton logout = new JButton("LogOut");
        jPanel.add(logout);
        logout.addActionListener(e -> authorization());
        jFrame.revalidate();
    }

    public static void makeSubjectForLectorWindow(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setTitle("My subjects");
        List<String> subjects = userService.getSubjects(user.getName());
        for (String subject : subjects) {
            JButton subjectButton = new JButton(subject);
            subjectButton.addActionListener(e1 -> makeGroups(subjectButton.getText()));
            jPanel.add(subjectButton);
        }
        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeLecturer());
        jFrame.revalidate();
    }

    public static void makeGroups(String subjectName){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        JButton first = new JButton("Groups");
        JButton second = new JButton("Lections");
        JButton third = new JButton("Practice works");
        jPanel.add(first);
        jPanel.add(second);
        jPanel.add(third);
        first.addActionListener(e -> {
            jFrame.getContentPane().removeAll();
            jPanel = new JPanel();
            jFrame.add(jPanel);

            jFrame.setTitle("Groups");
            ArrayList<String> groups = userService.getGroupsBySubject(subjectName);
            for (String group : groups) {
                JButton groupButton = new JButton(group);
                groupButton.addActionListener(e1 -> makeGroupMarks(subjectName, group));
                jPanel.add(groupButton);
            }
            JButton backToMainButton = new JButton("Main");
            jPanel.add(backToMainButton);
            backToMainButton.addActionListener(e2 -> initializeLecturer());
            jFrame.revalidate();
        });
        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeLecturer());
        jFrame.revalidate();
    }

    public static void makeGroupMarks(String subjectName, String group){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 500, 500);

        jFrame.setTitle("Group marks");
        List<StudentSubjectAccessibility> studentMarks = userService.getStudentsMarks(subjectName, group);
        JTable marksGUI = makeTableMarks(studentMarks);
        marksGUI.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(marksGUI);
        jPanel.add(sp);

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeLecturer());

        jFrame.revalidate();
    }

    private static JTable makeTableMarks(List<StudentSubjectAccessibility> studentMarks){
        JTable marksGui;
        Object[] titles = {"name", "lastName", "firstLab", "secondLab", "thirdLab", "fourthLab", "fifthLab", "sixthLab", "module", "total"};
        Object[][] data = new Object[studentMarks.size()][10];
        for (int i = 0; i < studentMarks.size(); i++){
            data[i] = studentMarks.get(i).toArray();
        }
        marksGui = new JTable(data, titles);
        TableColumn col = marksGui.getColumnModel().getColumn(9);
        //define the renderer
        col.setCellRenderer(new Renderer());
        return marksGui;
    }

    public static void initializeStudent(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 250, 120);

        JButton subjects = new JButton("My subjects");
        jPanel.add(subjects);
        subjects.addActionListener(e -> makeSubjectStudentWindow());
        JButton logout = new JButton("LogOut");
        jPanel.add(logout);
        logout.addActionListener(e -> authorization());

        jFrame.revalidate();
    }

    public static void makeSubjectStudentWindow(){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setTitle("My subjects");
        List<String> subjects = userService.getStudentSubjects(user.getName(), user.getLastName());
        for (String subject : subjects) {
            JButton subjectButton = new JButton(subject);
            subjectButton.addActionListener(e1 -> subjectForStudentWindow(subjectButton.getText()));
            jPanel.add(subjectButton);
        }
        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeStudent());

        jFrame.revalidate();
    }

    public static void subjectForStudentWindow(String subjectName){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        JButton studentMarksButton = new JButton("My marks");
        JButton lectionsButton = new JButton("Lections");
        JButton practicesButton = new JButton("Practices");
        studentMarksButton.addActionListener(e1 -> makeSingleStudentMarks(subjectName));
        jPanel.add(studentMarksButton);

        jFrame.revalidate();
    }

    public static void makeSingleStudentMarks(String subjectName){
        jFrame.getContentPane().removeAll();
        jPanel = new JPanel();
        jFrame.add(jPanel);

        jFrame.setBounds(dimension.width/2 - 250, dimension.height/2-250, 500, 500);


        jFrame.setTitle("My " + subjectName + " marks");
        StudentSubjectAccessibility singleStudentMarks = userService.getSingleStudentMarks(user.getName(), user.getLastName(), subjectName);
        ArrayList<StudentSubjectAccessibility> list = new ArrayList<>();
        list.add(singleStudentMarks);
        JTable marksGUI = makeTableMarks(list);
        marksGUI.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(marksGUI);
        jPanel.add(sp);

        JButton backToMainButton = new JButton("Main");
        jPanel.add(backToMainButton);
        backToMainButton.addActionListener(e -> initializeStudent());

        jFrame.revalidate();
    }

    public static void main(String[] args) {authorization();}
}

class Renderer extends DefaultTableCellRenderer {
    public Renderer() {super();}
    public Component getTableCellRendererComponent(JTable table, Object
            value, boolean isSelected, boolean hasFocus, int row, int column)
    {
        Component cell = super.getTableCellRendererComponent(table, value,
                isSelected, hasFocus, row, column);
        if (Integer.parseInt(value.toString()) < 60){
            cell.setBackground(Color.RED);
        }else if (Integer.parseInt(value.toString()) > 82){
            cell.setBackground(Color.GREEN);
        }else {
            cell.setBackground(table.getBackground());
        }
        return cell;
    }
}