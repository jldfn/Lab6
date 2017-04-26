import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        JTextPane OutputPanel=new JTextPane();
        table=new LabTable(labCollection.getUselessData());
        LabButton rmButton=new LabButton("Remove",labCollection.getUselessData(),table,"Rm",OutputPanel);
        LabButton rmLButton=new LabButton("Remove Lower",labCollection.getUselessData(),table,"RmL",OutputPanel);
        LabButton ImportButton=new LabButton("Import",labCollection.getUselessData(),table,"Imp",OutputPanel);
        JButton saveButton=new JButton("Save");
        TableRowSorter<LabTable> sort=new TableRowSorter<LabTable>(table);
        JTable sortTable=new JTable(table);
        JTextField filterText=new JTextField(30);
        JButton filterButton=new JButton("Filter");
        JPanel filterPanel=new JPanel();
        filterPanel.add(filterButton);
        filterPanel.add(filterText);
        JScrollPane tPanel=new JScrollPane(sortTable);
        JPanel TablePanel= new JPanel();
        TablePanel.add(filterPanel);
        TablePanel.add(tPanel);
        filterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(filterText.getText().length()==0){sort.setRowFilter(null);}
                else{
                    sort.setRowFilter(RowFilter.regexFilter(filterText.getText()));
                }
            }
        });
        sortTable.setRowSorter(sort);
        saveButton.addActionListener(new SaveListener(labCollection,OutputPanel));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OutputPanel.setSize(700,300);
        OutputPanel.setVisible(true);
        add(rmLButton.getButtonPanel());
        add(ImportButton.getButtonPanel());
        add(rmButton.getButtonPanel());
        add(saveButton);
        add(OutputPanel);
        add(TablePanel);
        pack();
    }

}
