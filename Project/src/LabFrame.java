import javax.swing.*;
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

    LabFrame(String title, TreeSet<Human> humanTreeSet){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table=new LabTable(humanTreeSet);
        add(new JTable(table));
        pack();

    }

}
