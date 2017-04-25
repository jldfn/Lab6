
/**
 * Created by Денис on 19.11.2016.
 */
public class Human implements Comparable {
    private String name;
    private int age;
    private String location;
    private Boolean IsMale;

    public Boolean getGender() {
        return IsMale;
    }

    public void setGender(Boolean male) {
        IsMale = male;
    }

    Human(){}
    Human(String n,int a){
        setAge(a);
        setName(n);
    }

    Human(String n,int a,String loc){
        setLocation(loc);
        setName(n);
        setAge(a);
    }

    public void Think(String thoughts){
        System.out.println( "\""+thoughts+"\""+"- подумал "+name);
    }
    public void MoveTo(String l){
        setLocation(l);
        System.out.println(name+" находится в локации: "+getLocation());
    }
    public void Disappear(){MoveTo("неизвестно где");
    System.out.println(name+" изчез");}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAge() {return age;}

    public void setAge(int age) {this.age = age;}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;

        Human human = (Human) o;

        if (!getName().equals(human.getName())) return false;
        return getLocation().equals(human.getLocation());

    }

    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getLocation().hashCode();
        return result;
    }
    public String toString(){
        return ("Человек по имени "+name+" возрастом "+getAge()+" находящийся в локации: "+location);
    }

    public int compareTo(Object o) {
        int c1=0,c2=0;
        for(int i=0;i<o.toString().length();i++) c1+=o.toString().charAt(i);
        for(int i=0;i<this.toString().length();i++) c2+=this.toString().charAt(i);
        return(c2-c1);
    }
}

