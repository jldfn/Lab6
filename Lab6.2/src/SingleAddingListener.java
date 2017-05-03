import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

/**
 * Created by Денис on 03.05.2017.
 */
public class SingleAddingListener implements ActionListener{

    private JTextField nameField;
    private JSpinner ageSpinner;
    private JTextField locField;
    private TreeSet<Human> collection;
    private LabTable table;
    private JTextPane output;

    SingleAddingListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable, JTextPane out){
        super();
        this.nameField=nameField;
        this.ageSpinner=ageSpinner;
        this.locField=locField;
        collection=col;
        table=colTable;
        output=out;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        collection.add(new Human(nameField.getText(),(int) ageSpinner.getValue(),locField.getText()));
        table.fireTableDataChanged();
    }
}
