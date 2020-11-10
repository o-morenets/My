package school.database;

import school.Main;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.time.LocalDate;
import java.util.ArrayList;

public class Student {

    private int id;
    private String name, surname, patron;
    private LocalDate birthDate;
    private ArrayList<Grade> grades;

    public Student(String name, String surname, String patron, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.patron = patron;
        this.birthDate = birthDate;
    }

    public JTable createTable() {
        grades = Main.databaseDAO.readGradesFromDB(id);
        int rowsCount = grades.size();
        String[] columns = {"ФИО", "Дата", "Предмет", "Оценки", "Учитель"};

        String[][] data = new String[rowsCount][columns.length + 1];

        int row = 0;
        for (int tRow = row; row < tRow + rowsCount; row++) {
            if (row == tRow)
                data[row][0] = getShortName();
            else
                data[row][0] = "";

            if (grades.size() > 0) {
                Grade g = grades.get(row - tRow);
                data[row][1] = g.getDate().toString();
                data[row][2] = g.getSubject().getName();
                data[row][3] = String.valueOf(g.getValue());
                data[row][4] = g.getTeacher();
                data[row][5] = String.valueOf(g.getId());
            } else {
                data[row][1] = "";
                data[row][2] = "";
                data[row][3] = "";
                data[row][4] = "";
                data[row][5] = "";
            }
        }

        TableModel dataModel = new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return grades.size();
            }

            @Override
            public int getColumnCount() {
                return columns.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                return data[rowIndex][columnIndex];
            }

            @Override
            public String getColumnName(int column) {
                return columns[column];
            }
        };
        return new JTable(dataModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    public void addGrade(Grade grade) {
        Main.databaseDAO.insertGradeIntoDB(this, grade);
    }

    public void addGrade(Subject subject, String teacher, int value, LocalDate date) {
        addGrade(new Grade(subject, teacher, value, date));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPatron() {
        return patron;
    }

    public String getShortName() {
        return surname + " " + name.charAt(0) + "." + (patron == null ? "" : " " + patron.charAt(0) + ".");
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        return getShortName();
    }
}
