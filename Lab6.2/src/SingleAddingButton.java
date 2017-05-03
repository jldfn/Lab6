import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

/**
 * Created by Денис on 03.05.2017.
 */
public class SingleAddingButton extends JButton{

    private JPanel addingPanel;

    SingleAddingButton(JTextField nameField,JSpinner ageSpinner,JTextField locField,TreeSet<Human> col, LabTable colTable, JTextPane out){
        super("Add");
        addingPanel=new JPanel();
        addActionListener(new SingleAddingListener(nameField,ageSpinner,locField,col,colTable,out));
        setPreferredSize(new Dimension(125,25));
        nameField.setColumns(22);
        locField.setColumns(22);
        ageSpinner.setPreferredSize(new Dimension(52,25));
        addingPanel.setOpaque(false);
        addingPanel.add(nameField);
        addingPanel.add(ageSpinner);
        addingPanel.add(locField);
        addingPanel.add(this);
    }

    public JPanel getAddingPanel() {
        return addingPanel;
    }

    public void setAddingPanel(JPanel addingPanel) {
        this.addingPanel = addingPanel;
    }
}
