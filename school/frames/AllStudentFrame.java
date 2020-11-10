package school.frames;


import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import school.Main;

public class AllStudentFrame extends MyFrame {

    public AllStudentFrame(JFrame parent){
        super("Все студенты", parent, 250, 200);

    }
    @Override
    public void showFrame() {
        JList<Object> list = new JList<>(Main.getClassesAsArray());
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        listScroller.setBounds(0, 0, 250, 300);
        add(listScroller);



        setVisible(true);


    }

}