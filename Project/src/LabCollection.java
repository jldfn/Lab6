
import java.util.TreeSet;

/**
 * Created by Денис on 12.03.2017.
 */
public class LabCollection {
    private String name;

    public TreeSet<Comparable> getUselessData() {
        return UselessData;
    }

    public void setUselessData(TreeSet<Comparable> uselessData) {
        UselessData = uselessData;
    }
    public void addUselessData(TreeSet<Comparable> uselessData){UselessData.addAll(uselessData);}
    private TreeSet<Comparable> UselessData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    LabCollection(){
        this.name="";
        UselessData=new TreeSet<Comparable>();
    }
    LabCollection(String name){
        this.name=name;
        UselessData=new TreeSet<Comparable>();
    }
}
