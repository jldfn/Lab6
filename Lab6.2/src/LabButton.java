import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class LabButton extends JButton{
    private JPanel ButtonPanel;

    LabButton(String title, TreeSet<Human> col,LabTable table,String type,JTextField nameField,JSpinner ageSpinner,JTextField locField, JProgressBar jPBar){
        super(title);
        switch (type){
            case "Rm":{
                addActionListener(new RemoveListener(nameField,ageSpinner,locField,col,table,jPBar));
            }break;
            case "RmL":{
                addActionListener(new RemoveLowerListener(nameField,ageSpinner,locField,col,table,jPBar));
            }break;
            case "Add":{
                addActionListener(new SingleAddingListener(nameField,ageSpinner,locField,col,table,jPBar));}
        }
        setPreferredSize(new Dimension(125,20));
        nameField.setColumns(22);
        locField.setColumns(22);
        ageSpinner.setPreferredSize(new Dimension(52,20));
        ButtonPanel=new JPanel();
        ButtonPanel.setOpaque(false);
        ButtonPanel.add(nameField);
        ButtonPanel.add(ageSpinner);
        ButtonPanel.add(locField);
        ButtonPanel.add(this);
    }

    LabButton( TreeSet<Human> col,LabTable table,JTextField pathField, JProgressBar jPBar){
        super("Import");
        setPreferredSize(new Dimension(125,20));
        addActionListener(new ImportListener(pathField,col,table,jPBar));
        pathField.setColumns(50);
        ButtonPanel=new JPanel();
        ButtonPanel.setOpaque(false);
        ButtonPanel.add(pathField);
        ButtonPanel.add(this);
    }

    public JPanel getButtonPanel() {
        return ButtonPanel;
    }

    public void setButtonPanel(JPanel buttonPanel) {
        ButtonPanel = buttonPanel;
    }

}
