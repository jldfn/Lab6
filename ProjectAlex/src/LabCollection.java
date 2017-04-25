
import java.util.TreeSet;

/**
 * Created by Денис on 12.03.2017.
 */
public class LabCollection {
    private String name;

    public TreeSet<Human> getUselessData() {
        return UselessData;
    }

    public void setUselessData(TreeSet<Human> uselessData) {
        UselessData = uselessData;
    }
    public void addUselessData(TreeSet<Human> uselessData){UselessData.addAll(uselessData);}
    private TreeSet<Human> UselessData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    LabCollection(){
        this.name="";
        UselessData=new TreeSet<Human>();
    }
    LabCollection(String name){
        this.name=name;
        UselessData=new TreeSet<Human>();
    }
}
