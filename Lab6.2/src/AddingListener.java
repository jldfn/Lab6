import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeSet;

/**
 * Created by Алексей on 27.04.2017.
 */
public class AddingListener implements ActionListener {
    private TreeSet<Human> collection;
    private LabTable colTable;
    private JSpinner JSP;

    AddingListener(JSpinner spinner, TreeSet<Human> col,LabTable table){
        super();
        collection=col;
        this.colTable=table;
        JSP=spinner;
    }
    public TreeSet<Human> getCollection() {
        return collection;
    }

    public void setCollection(TreeSet<Human> collection) {
        this.collection = collection;
    }

    public LabTable getColTable() {
        return colTable;
    }

    public void setColTable(LabTable colTable) {
        this.colTable = colTable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int i= (int) JSP.getValue();
        while (i>0) {
            int random_number1 = 0 + (int) (Math.random() *50);
            Names name = Names.fromId(random_number1);
            String humanName = name.toString();
            int random_number2 = 0 + (int) (Math.random() *100);
            int ageOfHuman=random_number2;
            String location = "newLocation";
            Human RandomMan = new Human(humanName,ageOfHuman,location);
            collection.add(RandomMan);
            getColTable().fireTableDataChanged();
            i--;
        }
    }
}
