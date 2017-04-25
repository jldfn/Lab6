import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveListener extends LabListener{
    RemoveListener(JTextField field, TreeSet<Human> col,LabTable colTable){
        super(field,col,colTable);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator iter = getCollection().iterator();
        Human consoleArgument = ConsoleApp.ParseJSON(getObject().getText());
        boolean removed=false;
        while (iter.hasNext()) {
            Human currentElement = (Human) iter.next();
            if (currentElement.equals(consoleArgument)) {
                iter.remove();
                System.out.println("Элемент " + consoleArgument.toString() + " успешно удалён из коллекции");
                removed = true;
                getColTable().fireTableDataChanged();
                break;
            }
        }if(!removed){System.out.println("Данного элемента нет в коллекции");}
    }
}
