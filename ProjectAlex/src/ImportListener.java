import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Денис on 25.04.2017.
 */
public class ImportListener extends LabListener {
    JTextPane OutPane;
    JTextField Field;
    ImportListener(JTextField field, TreeSet<Human> col, LabTable colTable,JTextPane out){
        super(field,col,colTable,out);
        OutPane=out;
        Field=field;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
     /*   new Runnable(){
            @Override
            public void run() {
                Matcher m= Pattern.compile("\\{([^}]+)\\}").matcher(getObject().getText());
                while (m.find()) {
                    getCollection().addAll(ConsoleApp.ImportFrom(m.group().substring(1, m.group().length() - 1)).getUselessData());
                    getColTable().fireTableDataChanged();
                }
            }
        };
    }*/
    String newWay = Field.getText();
    try{
        LabCollection ExpCol = ConsoleApp.ImportFrom(newWay);
        //ConsoleApp.SaveCollection(ExpCol);

    } catch (NullPointerException f){getOutputPanel().setText(getOutputPanel().getText()+System.getProperty("line.separator")+" не верный путь");}
    }

}
