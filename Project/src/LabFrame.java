import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

/**
 * Created by Денис on 24.04.2017.
 */
public class LabFrame extends JFrame {
    JPanel panel=new JPanel();
    LabTable table;

    public LabTable getTable() {
        return table;
    }

    public void setTable(LabTable table) {
        this.table = table;
    }

    LabFrame(String title, LabCollection labCollection){
        super(title);
        setLayout(new FlowLayout());
        JButton saveButton=new JButton();
        saveButton.addActionListener(new SaveListener(labCollection));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table=new LabTable(labCollection.getUselessData());
        add(saveButton);
        add(new JScrollPane(new JTable(table)));
        pack();

    }

}
