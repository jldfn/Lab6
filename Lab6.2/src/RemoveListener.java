import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Created by Денис on 25.04.2017.
 */
public class RemoveListener extends LabListener {
    RemoveListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable, JProgressBar jpb) {
        super(nameField, ageSpinner, locField, col, colTable, jpb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((int) getAgeSpinner().getValue() >= 0 && (int) getAgeSpinner().getValue() <= 120) {
            if (Pattern.compile("[A-zА-я]+").matcher(getNameField().getText()).matches()) {
                if (Pattern.compile("[A-zА-я0-9\\-_]+").matcher(getLocField().getText()).matches()) {
                    Iterator iter = getCollection().iterator();
                    Human consoleArgument = new Human(getNameField().getText(), (int) getAgeSpinner().getValue(), getLocField().getText());
                    boolean removed = false;
                    while (iter.hasNext()) {
                        Human currentElement = (Human) iter.next();
                        if (currentElement.equals(consoleArgument)) {
                            iter.remove();
                            System.out.print(consoleArgument.toString() + " успешно удалён из коллекции");
                            removed = true;
                            getTable().fireTableDataChanged();
                            break;
                        }
                    }
                    if (!removed) {
                        System.out.print("Данного элемента нет в коллекции");
                    }
                    getNameField().setText("");
                    getAgeSpinner().setValue(0);
                    getLocField().setText("");
                } else {
                    System.out.print("Поле \"Локация\" не может являться пустым. В локации могут содержаться лишь символы кириллицы, латинского алфавита, цифры, \"-\" и \"_\"");
                }
            } else {
                System.out.print("Поле \"Имя\" не может являться пустым.В имени могут содержаться только символы кириллицы и латинского алфавита");
            }
        } else {
            System.out.print("Возраст может быть только в пределах от 0 до 120 лет");
        }
    }
}
