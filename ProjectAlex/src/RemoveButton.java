import javax.swing.*;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveButton extends JButton{
    JTextField rmTextField;
    RemoveButton(String title, TreeSet<Human> col,LabTable table, JTextPane out){
        super(title);
        rmTextField=new JTextField("Введите объект в формате JSON",40);
        addActionListener(new RemoveListener(rmTextField,col,table,out));
    }
}
