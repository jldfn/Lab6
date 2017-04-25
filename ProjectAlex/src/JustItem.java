

import java.util.ArrayList;

public class JustItem extends Item {
    JustItem(String n,int v){
        super(n,v);
    }
    JustItem (){
        super();
    }
    public void PutIn(Container container){
        if (container.getSpace()>0){
            container.setValue(container.getValue()+this.getValue());
        ArrayList<JustItem> newContents=container.getContents();
        newContents.add(this);
        container.setContents(newContents);
        container.setSpace(container.getSpace()-1);}
    }
}
