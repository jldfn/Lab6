import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveListener extends LabListener{
    RemoveListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable,JProgressBar jpb){
        super(nameField,ageSpinner,locField,col,colTable,jpb);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator iter = getCollection().iterator();
        Human consoleArgument = new Human(getNameField().getText(),(int) getAgeSpinner().getValue(),getLocField().getText());
        boolean removed=false;
        while (iter.hasNext()) {
            Human currentElement = (Human) iter.next();
            if (currentElement.equals(consoleArgument)) {
                iter.remove();
                System.out.print(consoleArgument.toString() + " успешно удалён из коллекции");
                removed = true;
                getTable().fireTableDataChanged();
                break;
            }
        }if(!removed){ System.out.print("Данного элемента нет в коллекции");}
        getNameField().setText("");
        getAgeSpinner().setValue(0);
        getLocField().setText("");
    }
}
