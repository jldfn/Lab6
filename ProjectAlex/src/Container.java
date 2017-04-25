import java.util.ArrayList;

/**
 * Created by Денис on 04.12.2016.
 */
public class Container extends Item {
    private int size;
    private int space;
    private ArrayList<JustItem> contents=new ArrayList<JustItem>();
    Container(String n,int s){
        setName(n);
        setSize(s);
        setSpace(s);
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public ArrayList<JustItem> getContents() {
        return contents;
    }

    public void setContents(ArrayList<JustItem> contents) {
        this.contents = contents;
    }

    public String toString(){
        return ("Контейнер "+getName()+" размером "+getSize()+" содержащий "+(getSize()-getSpace())+" предметов общей стоимостью "+getValue());
    }

}
