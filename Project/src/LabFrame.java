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
        table=new LabTable(labCollection.getUselessData());
        LabButton rmButton=new LabButton("Remove",labCollection.getUselessData(),table,"Rm");
        LabButton rmLButton=new LabButton("Remove Lower",labCollection.getUselessData(),table,"RmL");
        LabButton ImportButton=new LabButton("Import",labCollection.getUselessData(),table,"Imp");
        JButton saveButton=new JButton("Save");
        saveButton.addActionListener(new SaveListener(labCollection));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(rmLButton.getButtonPanel());
        add(ImportButton.getButtonPanel());
        add(rmButton.getButtonPanel());
        add(saveButton);
        add(new JScrollPane(new JTable(table)));
        pack();

    }

}
