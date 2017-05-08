import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

/**
 * Created by Денис on 03.05.2017.
 */
public class SingleAddingListener extends LabListener{

    private JTextField nameField;
    private JSpinner ageSpinner;
    private JTextField locField;
    private TreeSet<Human> collection;
    private LabTable table;

    SingleAddingListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable,JProgressBar jpb){
        super(nameField,ageSpinner,locField,col,colTable,jpb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Human Person=new Human(nameField.getText(),(int) ageSpinner.getValue(),locField.getText());
        collection.add(Person);
        nameField.setText("");
        ageSpinner.setValue(0);
        locField.setText("");
        table.fireTableDataChanged();
        System.out.print("Объект "+Person.toString()+" был успешно занесен в коллекцию");
    }
}
