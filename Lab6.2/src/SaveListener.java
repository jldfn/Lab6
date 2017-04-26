import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class SaveListener implements ActionListener{
    LabCollection collection;
    JTextPane OutputPanel;

    SaveListener(LabCollection collection, JTextPane out){
        super();
        this.collection=collection;
        OutputPanel=out;

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    FileWriter toEmptyFile = new FileWriter(ConsoleApp.defaultPath);
                    BufferedWriter headerWriter = new BufferedWriter(toEmptyFile);
                    headerWriter.write("<Collection name=\"" + collection.getName() + "\">");
                    headerWriter.close();
                    FileWriter fileWriter = new FileWriter(ConsoleApp.defaultPath, true);
                    BufferedWriter writer = new BufferedWriter(fileWriter);
                    writer.newLine();
                    for (Comparable elem : collection.getUselessData()) {
                        String[] a = elem.toString().split(" ");
                        switch (elem.toString().charAt(0)) {
                            case 'Ч': {
                                writer.write("\t<Human name=\"" + a[3] + "\" age=\"" + a[5] + "\" loc=\"" + a[9] + "\"/>");
                                writer.newLine();
                                break;
                            }
                        }
                        writer.flush();
                    }
                    writer.write("</Collection>");
                    writer.close();
                } catch (Exception f) {
                    System.out.println(f.getCause() + " " + f.getMessage());
                }
                getOutputPanel().setText(getOutputPanel().getText() + System.getProperty("line.separator") + "Коллекция сохранена в файл: " + ConsoleApp.defaultPath);
            }
        }).start();
    }

    public LabCollection getCollection() {
        return collection;
    }

    public void setCollection(LabCollection collection) {
        this.collection = collection;
    }

    public JTextPane getOutputPanel() {
        return OutputPanel;
    }

    public void setOutputPanel(JTextPane outputPanel) {
        OutputPanel = outputPanel;
    }
}
