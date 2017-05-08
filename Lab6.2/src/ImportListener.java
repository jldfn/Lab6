import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Денис on 25.04.2017.
 */
public class ImportListener extends LabListener {
    ImportListener(JTextField field, TreeSet<Human> col, LabTable colTable, JProgressBar jpb){
        super(field,col,colTable,jpb);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable(){
            @Override
            public void run() {
                Matcher m= Pattern.compile("\\{([^}]+)\\}").matcher(getNameField().getText());
                while (m.find()) {
                    getCollection().addAll(ConsoleApp.ImportFrom(m.group().substring(1, m.group().length() - 1)).getUselessData());
                    getTable().fireTableDataChanged();
                }
                getNameField().setText("");
            }
        }).start();
    }
}
