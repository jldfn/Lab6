import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public class LabTable extends AbstractTableModel implements TableModel {

    private TreeSet<Human> Humans;

    LabTable(TreeSet<Human> Humans){
        super();
        this.Humans=Humans;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex!=0){
        return true;
        }else return false;
    }

    @Override
    public String getColumnName(int column) {
        String result="";
        switch (column) {
            case 0:
                result="№";
                break;
            case 1:
                result = "Name";
                break;
            case 2:
                result = "Age";
                break;
            case 3:
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
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Human[] arr=Humans.toArray(new Human[Humans.size()]);
        switch (columnIndex){
            case 1: Humans.add(new Human((String)aValue,  arr[(int) getValueAt(rowIndex,0)-1].getAge(),  arr[(int) getValueAt(rowIndex,0)-1].getLocation())); break;
            case 2: Humans.add(new Human(arr[(int) getValueAt(rowIndex,0)-1].getName(),  (int)aValue,  arr[(int) getValueAt(rowIndex,0)-1].getLocation())); break;
            case 3: Humans.add(new Human(arr[(int) getValueAt(rowIndex,0)-1].getName(),  arr[(int) getValueAt(rowIndex,0)-1].getAge(),  (String)aValue)); break;
        }
        Humans.remove(arr[(int) getValueAt(rowIndex,0)-1]);
        fireTableDataChanged();
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
            case 0:{return rowIndex+1;}
            case 1:{return arr[rowIndex].getName();}
            case 2:{return arr[rowIndex].getAge();}
            case 3:{return arr[rowIndex].getLocation();}
           // case 3:{return arr[rowIndex].getGender()? "Male":"Female";}
            default:{return null;}
        }
    }

    @Override
    public int getColumnCount() {
        return 4; //4;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        Class returnValue;
        if ((columnIndex >= 0) && (columnIndex < getColumnCount())) {

            returnValue = getValueAt(0, columnIndex).getClass();
        } else {
            returnValue = Object.class;
        }
        return returnValue;
    }
}
