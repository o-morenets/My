package school;

import school.database.DatabaseDAO;
import school.database.SchoolClass;
import school.database.Student;
import school.frames.*;

import javax.management.openmbean.KeyAlreadyExistsException;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Main {

    public Main() {
        createMainFrame();
    }

    private ArrayList<SchoolClass> classes;

    private JFrame mainFrame;
    private JList<SchoolClass> classList;
    private JList<Student> studentList;
    private JTable table;
    private JScrollPane classListPane;
    private JScrollPane studentListPane;
    private JScrollPane tablePane;
    public DatabaseDAO databaseDAO = new DatabaseDAO();

    public JList<SchoolClass> getClassList() {
        return classList;
    }

    public JList<Student> getStudentList() {
        return studentList;
    }

    public ArrayList<SchoolClass> getClasses() {
        return classes;
    }

    public SchoolClass[] getClassesAsArray() {
        classes = databaseDAO.readAllClassesFromDB();
        return classes.toArray(new SchoolClass[0]);
    }

    public void addClass(SchoolClass schoolClass) throws KeyAlreadyExistsException {
        for (SchoolClass c : classes) {
            if (c.getName().equals(schoolClass.getName()))
                throw new KeyAlreadyExistsException(schoolClass.getName());
        }
        databaseDAO.insertClassIntoDB(schoolClass);
        classList.setListData(getClassesAsArray());
    }

    public void deleteClass(SchoolClass schoolClass) {
        databaseDAO.deleteClassFromDB(schoolClass);
        classList.setListData(getClassesAsArray());
        updateStudentsList();
    }

    public void selectStudent(SchoolClass schoolClass, Student student) {
        try {
            classList.setSelectedIndex(classes.indexOf(schoolClass));
            updateStudentsList();
            studentList.setSelectedIndex(schoolClass.getStudents().indexOf(student));
            updateTable();
        } catch (Exception ignored) {
        }
    }

    public void updateStudentsList() {
        if (classList.getSelectedValue() != null) {
            studentList.setListData(classList.getSelectedValue().getStudentsAsArray());
            updateTable();
        }
    }

    public void updateTable() {
        if (tablePane != null)
            mainFrame.remove(tablePane);

        if (studentList.getSelectedValue() != null) {
            Student select = studentList.getSelectedValue();
            table = select.createTable();
            tablePane = new JScrollPane(table);
            tablePane.setBounds(300, 0, mainFrame.getWidth() - 300, mainFrame.getHeight() - 50);
            mainFrame.add(tablePane);
        } else {
            tablePane = null;
        }

        mainFrame.validate();
        mainFrame.repaint();
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu classMenu = new JMenu("Классы");
        JMenuItem createClassItem = new JMenuItem("Создать");
        createClassItem.addActionListener(actionEvent -> new CreateClassFrame(mainFrame));
        JMenuItem deleteClassItem = new JMenuItem("Удалить");
        deleteClassItem.addActionListener(actionEvent -> new DeleteClassFrame(mainFrame));
        classMenu.add(createClassItem);
        classMenu.add(deleteClassItem);

        JMenu studentMenu = new JMenu("Ученики");
        JMenuItem findStudentItem = new JMenuItem("Найти");
        findStudentItem.addActionListener(actionEvent -> new FindStudentFrame(mainFrame));
        JMenuItem addStudentItem = new JMenuItem("Добавить");
        addStudentItem.addActionListener(actionEvent -> new AddStudentFrame(mainFrame));
        JMenuItem showAllStudentItem = new JMenuItem("Вывести всех учеников");
        showAllStudentItem.addActionListener(actionEvent -> new AllStudentFrame(mainFrame));
        JMenuItem deleteStudentItem = new JMenuItem("Удалить");
        deleteStudentItem.addActionListener(actionEvent -> new DeleteStudentFrame(mainFrame));

        studentMenu.add(findStudentItem);
        studentMenu.add(addStudentItem);
        studentMenu.add(showAllStudentItem);
        studentMenu.add(deleteStudentItem);

        menuBar.add(classMenu);
        menuBar.add(studentMenu);

        return menuBar;
    }

    private void createMainFrame() {
        mainFrame = new JFrame("Журнал");
        mainFrame.setMinimumSize(new Dimension(960, 720));
        mainFrame.setSize(mainFrame.getMinimumSize());

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);

        classList = new JList<>(getClassesAsArray());
        classList.setBorder(new TitledBorder("Классы"));
        classList.addListSelectionListener(e -> updateStudentsList());
        classListPane = new JScrollPane(classList);
        classListPane.setBounds(0, 0, 100, mainFrame.getHeight());

        studentList = new JList<>();
        studentList.setBorder(new TitledBorder("Ученики"));
        studentList.addListSelectionListener(e -> updateTable());
        studentListPane = new JScrollPane(studentList);
        studentListPane.setBounds(100, 0, 200, mainFrame.getHeight());

        JButton addGrade = new JButton("Добавить оценку");
        addGrade.setBounds(350, 600, 150, 25);
        addGrade.addActionListener(e -> {
            if (classList.getSelectedValue() != null && studentList.getSelectedValue() != null) {
                new AddGradeFrame(mainFrame);
            }
        });

        JButton deleteGrade = new JButton("Удалить оценку");
        deleteGrade.setBounds(700, 600, 150, 25);
        deleteGrade.addActionListener(e -> {
            if (table != null) {
                int tableSelectedRow = table.getSelectedRow();
                if (tableSelectedRow != -1) {
                    int id = Integer.parseInt((String) table.getModel().getValueAt(tableSelectedRow, 5));
                    databaseDAO.deleteGrade(id);
                    updateTable();
                }
            }
        });

        mainFrame.add(classListPane);
        mainFrame.add(studentListPane);
        mainFrame.setJMenuBar(createMenuBar());
        mainFrame.add(addGrade);
        mainFrame.add(deleteGrade);


        mainFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                classListPane.setBounds(0, 0, 100, mainFrame.getHeight());
                studentListPane.setBounds(100, 0, 200, mainFrame.getHeight());
                if (tablePane != null)
                    tablePane.setBounds(300, 0, mainFrame.getWidth() - 300, mainFrame.getHeight() - 50);
            }
        });

        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        Main myApp = new Main();
        SwingUtilities.invokeLater(myApp::createMainFrame);
    }
}