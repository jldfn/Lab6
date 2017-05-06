import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public abstract class LabListener implements ActionListener {
    private JTextField object;
    private TreeSet<Human> collection;
    private LabTable colTable;

    LabListener(JTextField field, TreeSet<Human> col, LabTable colTable){
        super();
        collection=col;
        object=field;
        this.colTable=colTable;
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

    public JTextField getObject() {

        return object;
    }

    public void setObject(JTextField object) {
        this.object = object;
    }
}
