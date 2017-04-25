import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveListener extends LabListener{
    JTextPane OutputPanel;
    RemoveListener(JTextField field, TreeSet<Human> col,LabTable colTable,JTextPane out){
        super(field,col,colTable,out);
        OutputPanel=out;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator iter = getCollection().iterator();
        Human consoleArgument = ConsoleApp.ParseJSON(getObject().getText(), OutputPanel);
        boolean removed=false;
        while (iter.hasNext()) {
            Human currentElement = (Human) iter.next();
            if (currentElement.equals(consoleArgument)) {
                iter.remove();
                System.out.println("Элемент " + consoleArgument.toString() + " успешно удалён из коллекции");
                getOutputPanel().setText(getOutputPanel().getText()+System.getProperty("line.separator")+" успешно удалён из коллекции");
                removed = true;
                getColTable().fireTableDataChanged();
                break;
            }
        }if(!removed){getOutputPanel().setText(getOutputPanel().getText()+System.getProperty("line.separator")+"Данного элемента нет в коллекции");}
    }
}
