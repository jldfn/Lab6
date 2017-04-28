import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
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
        try {
            setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:/Users/Денис/Desktop/isla2.png")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSize(720,850);
        setResizable(false);
        setLayout(new FlowLayout());
        table=new LabTable(labCollection.getUselessData());
        JTextPane OutputPanel=new JTextPane();
        JSpinner spin = new JSpinner();
        JProgressBar jpb = new JProgressBar();
        AddingButton addHuman = new AddingButton(spin,labCollection.getUselessData(),table, jpb);
        JPanel spinPanel=new JPanel();
        spinPanel.setOpaque(false);
        spinPanel.add(addHuman);
        spinPanel.add(spin);
        spin.setValue(0);
        spin.setPreferredSize(new Dimension(90,30));
        addHuman.setText("Нажать, чтобы добавить людей в данном количестве");
        OutputPanel.setPreferredSize(new Dimension(690,135));
        OutputPanel.setText("Здравствуйте"+System.getProperty("line.separator")+"Здесь будут выводиться все сообщения системы");
        OutputPanel.setEditable(false);
        LabButton rmButton=new LabButton("Remove",labCollection.getUselessData(),table,"Rm",OutputPanel);
        LabButton rmLButton=new LabButton("Remove Lower",labCollection.getUselessData(),table,"RmL",OutputPanel);
        LabButton ImportButton=new LabButton("Import",labCollection.getUselessData(),table,"Imp",OutputPanel);
        JButton saveButton=new JButton("Save");
        TableRowSorter<LabTable> sort=new TableRowSorter<LabTable>(table);
        JTable sortTable=new JTable(table);
        JTextField filterText=new JTextField(30);
        JButton filterButton=new JButton("Filter");
        JPanel filterPanel=new JPanel();
        filterPanel.setOpaque(false);
        filterPanel.add(filterButton);
        filterPanel.add(filterText);
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
        add(rmLButton.getButtonPanel());
        add(ImportButton.getButtonPanel());
        add(rmButton.getButtonPanel());
        add(saveButton);
        add(filterPanel);
        add(new JScrollPane(sortTable));
        add(spinPanel);
        add(new JScrollPane(OutputPanel));
        jpb.setPreferredSize(new Dimension(600,35));
        jpb.setIndeterminate(false);
        add(jpb);
    }

}
