import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class LabTable extends AbstractTableModel {
    TreeSet<Human> Humans;
    LabTable(TreeSet<Human> Humans){
        super();
        this.Humans=Humans;
    }

    @Override
    public String getColumnName(int column) {
        String result="";
        switch (column) {
            case 0:
                result = "Name";
                break;
            case 1:
                result = "Age";
                break;
            case 2:
                result = "Location";
                break;
        }
          //  case 3:return("Gender");
            return result;
    }

    @Override
    public int getRowCount() {
        return Humans.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int i=0;
        Human[] arr=new Human[Humans.size()];
        for(Human a:Humans){
            arr[i]=new Human();
            arr[i].setAge(a.getAge());
            arr[i].setName(a.getName());
            arr[i].setLocation(a.getLocation());
            //arr[i].setGender(a.getGender());
            i++;
        }
        switch(columnIndex){
            case 0:{return arr[rowIndex].getName();}
            case 1:{return arr[rowIndex].getAge();}
            case 2:{return arr[rowIndex].getLocation();}
           // case 3:{return arr[rowIndex].getGender()? "Male":"Female";}
            default:{return null;}
        }
    }

    @Override
    public int getColumnCount() {
        return 3; //4;
    }
}
