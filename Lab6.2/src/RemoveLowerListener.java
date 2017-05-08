import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveLowerListener extends LabListener {
    JProgressBar jpb1;
    RemoveLowerListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable,JProgressBar jpb){
        super(nameField,ageSpinner,locField,col,colTable,jpb);
        jpb1=jpb;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ProgressBarThread jPBarThread = new ProgressBarThread(jpb1);
        jPBarThread.start();
        Iterator iter = getCollection().iterator();
        while (iter.hasNext()) {
            Human consoleArgument = new Human(getNameField().getText(),(int)getAgeSpinner().getValue(),getLocField().getText());
            Human a = (Human) iter.next();
            if (consoleArgument.compareTo(a) > 0) {
                System.out.print(a.toString() + " был удалён из коллекции");
                iter.remove();
                getTable().fireTableDataChanged();
            }
        }
        getNameField().setText("");
        getAgeSpinner().setValue(0);
        getLocField().setText("");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        jPBarThread.interrupt();
        System.out.println("Called interruption");
        //jPBarThread.setCancel(true);
        //JProgressBar jpb=jPBarThread.getJPB();
    }
}
