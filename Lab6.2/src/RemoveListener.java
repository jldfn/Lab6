import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.text.Style;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveListener implements ActionListener{
    JTextField object;
    TreeSet<Human> collection;
    LabTable colTable;
    JTextPane obj;
    RemoveListener(JTextField field, TreeSet<Human> col,LabTable colTable, JTextPane pole){
        super();
        collection=col;
        object=field;
        obj=pole;
        this.colTable=colTable;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Iterator iter = collection.iterator();
        Human consoleArgument = ConsoleApp.ParseJSON(object.getText());
        boolean removed=false;
        while (iter.hasNext()) {
            Human currentElement = (Human) iter.next();
            if (currentElement.equals(consoleArgument)) {
                iter.remove();
                System.out.println("Элемент " + consoleArgument.toString() + " успешно удалён из коллекции"); //добавить вывод в большое текстовое окно
                removed = true;
                String str = "Элемент " + consoleArgument.toString() + " успешно удалён из коллекции";
                obj.setText(obj.getText()+System.getProperty("line.separator")+str);
                colTable.fireTableDataChanged();
                break;
            }
        }if(!removed){System.out.println("Данного элемента нет в коллекции");
        String str = "Данного элемента нет в коллекции";
        obj.setText(obj.getText()+System.getProperty("line.separator")+str);
        }
    }

}
