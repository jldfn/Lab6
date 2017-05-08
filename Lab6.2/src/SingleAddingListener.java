import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * Created by Денис on 03.05.2017.
 */
public class SingleAddingListener extends LabListener {

    private JTextField nameField;
    private JSpinner ageSpinner;
    private JTextField locField;
    private TreeSet<Human> collection;
    private LabTable table;

    SingleAddingListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable, JProgressBar jpb) {
        super(nameField, ageSpinner, locField, col, colTable, jpb);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((int) getAgeSpinner().getValue() >= 0 && (int) getAgeSpinner().getValue() <= 120) {
            if (Pattern.compile("[A-zА-я]+").matcher(getNameField().getText()).matches()) {
                if (Pattern.compile("[A-zА-я0-9\\-_]+").matcher(getLocField().getText()).matches()) {
                    Human Person = new Human(nameField.getText(), (int) ageSpinner.getValue(), locField.getText());
                    collection.add(Person);
                    nameField.setText("");
                    ageSpinner.setValue(0);
                    locField.setText("");
                    table.fireTableDataChanged();
                    System.out.print("Объект " + Person.toString() + " был успешно занесен в коллекцию");
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
