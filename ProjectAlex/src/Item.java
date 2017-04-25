
/**
 * Created by Денис on 19.11.2016.
 */
public abstract class Item implements Comparable{
     String name;
    private int value;
    Item(String n,int v){
        setName(n);
        setValue(v);
    }
    Item(){
        setName("Nothing");
        setValue(0);
    }
    public String getName() {
        return name;
    }

    public void setName(String name){this.name=name;};


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;

        Item item = (Item) o;

        if (getValue() != item.getValue()) return false;
        return getName().equals(item.getName());

    }
    public int compareTo(Object o) {
        int c1=0,c2=0;
        for(int i=0;i<o.toString().length();i++) c1+=o.toString().charAt(i);
        for(int i=0;i<this.toString().length();i++) c2+=this.toString().charAt(i);
        return(c2-c1);
    }

    public int hashCode() {
        int result = getName().hashCode();
        result = 17 * result + getValue();
        return result;
    }
    public String toString(){
        return ("Предмет "+getName()+" стоимостью "+getValue());
    }
}
