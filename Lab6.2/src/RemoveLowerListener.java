import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveLowerListener extends LabListener {
    RemoveLowerListener(JTextField field, TreeSet<Human> col, LabTable colTable,JTextPane out){
        super(field,col,colTable,out);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator iter = getCollection().iterator();
        while (iter.hasNext()) {
            Human consoleArgument = ConsoleApp.ParseJSON(getObject().getText());
            Human a = (Human) iter.next();
            if (consoleArgument.compareTo(a) > 0) {
                getOutputPanel().setText(getOutputPanel().getText()+System.getProperty("line.separator")+"Элемент " + a.toString() + " был удалён из коллекции");
                iter.remove();
                getColTable().fireTableDataChanged();
            }
        }
    }
}
