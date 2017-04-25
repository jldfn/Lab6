import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

/**
 * Created by Денис on 24.04.2017.
 */
public class LabFrame extends JFrame {
    //JPanel panel=new JPanel();
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
        JTextPane OutputPanel=new JTextPane();
        OutputPanel.setText("Вывод ошибок и прочей хуеты, которая вылезает при работе");
        table=new LabTable(labCollection.getUselessData());
        LabButton rmButton=new LabButton("Remove",labCollection.getUselessData(),table,"Rm",OutputPanel);
        LabButton rmLButton=new LabButton("Remove Lower",labCollection.getUselessData(),table,"RmL",OutputPanel);
        LabButton ImportButton=new LabButton("Import",labCollection.getUselessData(),table,"Imp",OutputPanel);
        JButton saveButton=new JButton("Save");
        saveButton.addActionListener(new SaveListener(labCollection,OutputPanel));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OutputPanel.setSize(700,300);
        OutputPanel.setVisible(true);
        add(rmLButton.getButtonPanel());
        add(ImportButton.getButtonPanel());
        add(rmButton.getButtonPanel());
        add(saveButton);
        add(OutputPanel);
        add(new JScrollPane(new JTable(table)));
        pack();

    }

}
