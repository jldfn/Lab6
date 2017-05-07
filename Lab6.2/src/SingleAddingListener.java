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

    SingleAddingListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable){
        super();
        this.nameField=nameField;
        this.ageSpinner=ageSpinner;
        this.locField=locField;
        collection=col;
        table=colTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Human Person=new Human(nameField.getText(),(int) ageSpinner.getValue(),locField.getText());
        collection.add(Person);
        nameField.setText("");
        ageSpinner.setValue(0);
        locField.setText("");
        table.fireTableDataChanged();
        output.setText(output.getText()+System.getProperty("line.separator")+"Объект "+Person.toString()+" был успешно занесен в коллекцию");
    }
}
