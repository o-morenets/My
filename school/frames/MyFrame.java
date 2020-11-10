package school.frames;

import school.Main;
import school.database.SchoolClass;
import school.database.Student;
import school.database.Subject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

abstract class MyFrame extends JFrame {

    private JFrame parent;

    protected JComboBox<Student> studentBox;
    protected JComboBox<Subject> subjectBox;
    protected JComboBox<String> teacherBox;

    public abstract void showFrame();

    MyFrame(String title, JFrame parent, int width, int height) {
        super(title);
        this.parent = parent;

        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setAlwaysOnTop(true);
        getParent().setEnabled(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        showFrame();
        repaint();
    }

    protected JComboBox<SchoolClass> createClassBox() {
        JComboBox<SchoolClass> classBox = new JComboBox<>(Main.getClassesAsArray());
        classBox.setBounds(150, 10, 90, 25);
        return classBox;
    }

    @Override
    public Container getParent() {
        return parent;
    }

    @Override
    public void dispose() {
        getParent().setEnabled(true);
        super.dispose();
    }

}
