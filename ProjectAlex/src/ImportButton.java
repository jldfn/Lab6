import javax.swing.*;
import java.util.TreeSet;

/**
 * Created by Алексей on 26.04.2017.
 */
public class ImportButton extends JButton {
    JTextField imTextField;
    ImportButton(String title, TreeSet<Human> col, LabTable table, JTextPane out){
        super(title);
        imTextField=new JTextField("Введите полный путь до файла",40);
        addActionListener(new RemoveListener(imTextField,col,table,out));
    }
}
