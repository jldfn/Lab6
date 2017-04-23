/**
 * Created by Денис on 02.03.2017.
 */

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleApp {
    final static String defaultPath=System.getenv("Lab5");


    public static void main(String[] args) {
        LabCollection ExpCol=ImportFrom(defaultPath);
        Scanner consoleReader=new Scanner(System.in);
        String currentString=consoleReader.nextLine();
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run() {
                SaveCollection(ExpCol);
        }
        }
        );
        while (!(currentString.matches("exit"))){
            String[] sepString=currentString.split(" ");
            switch (sepString[0]){
                case "save":{SaveCollection(ExpCol); System.out.println("Коллекция сохранена в файл: "+ defaultPath); break;}
                case "import":{
                    Matcher m= Pattern.compile("\\{([^}]+)\\}").matcher(currentString);
                    while (m.find())
                    ExpCol.addUselessData(ImportFrom(m.group().substring(1,m.group().length()-1)).getUselessData()); break;}
                case "remove": {
                    Iterator iter = ExpCol.getUselessData().iterator();
                    Comparable consoleArgument = ParseJSON(currentString.substring(7));
                    while (iter.hasNext()) {
                        Comparable currentElement = (Comparable) iter.next();
                        if (!currentElement.toString().contains("Контейнер")) {
                            if (currentElement.toString().matches(consoleArgument.toString())) {
                                iter.remove();
                                System.out.println("Элемент " + consoleArgument.toString() + " успешно удалён из коллекции");
                                break;
                            } else ;
                        } else {
                            if (currentElement.toString().matches(consoleArgument.toString())) {
                                Container consoleCon = (Container) consoleArgument;
                                Container currentCon = (Container) currentElement;
                                int identity = 0;
                                for (JustItem conItem : currentCon.getContents()) {
                                    for (JustItem someItem : consoleCon.getContents()) {
                                        if (conItem.toString().matches(someItem.toString())) identity++;
                                    }
                                }
                                if (identity == consoleCon.getSize() - consoleCon.getSpace()) {
                                    iter.remove();
                                    System.out.println("Элемент успешно удалён из коллекции");
                                }
                            }
                        }
                    }
                }break;
                case "remove_lower": {
                    Iterator iter = ExpCol.getUselessData().iterator();
                    while (iter.hasNext()) {
                        Comparable consoleArgument = ParseJSON(currentString.substring(13, currentString.length()));
                        Comparable a = (Comparable) iter.next();
                        if (consoleArgument.compareTo(a) > 0) {
                            System.out.println("Элемент " + a.toString() + " был удалён из коллекции");
                            iter.remove();
                        }
                    }
                }break;
                case "view":{for(Comparable colElem:ExpCol.getUselessData()){
                    System.out.println(colElem.toString());
                }
                break;}
                default:System.out.println("Нет такой команды");
            }
            currentString=consoleReader.nextLine();
        }
        SaveCollection(ExpCol);
    }

    public static Comparable ParseJSON(String parseString) {
        String[] sepString = parseString.split(" ");
        Scanner itemReader = new Scanner(System.in);
        try{
        switch (sepString[0]) {
            case "{\"Human\":{":{
                String name=null;
                boolean[] hasAttributes=new boolean[3];
                int age=-1;
                String location=null;
                Matcher forHuman=Pattern.compile("\"[A-z]+\":\"[A-z,А-я,0-9]+\"").matcher(parseString);
                while(forHuman.find()){
                    String[] buffString=forHuman.group().split(":");
                    switch(buffString[0]){
                        case "\"name\"": Matcher hName=Pattern.compile("[0-9]").matcher(buffString[1]);
                            if(hName.find()){System.out.println("Поле name может содержать исключительно символы латинского алфавита и кириллицы");}
                            else{if(name!=null)System.out.println("Атрибут name введён повторно, будет использовано следующее значение: "+buffString[1]);
                                name=buffString[1].substring(1,buffString[1].length()-1);
                                hasAttributes[0]=true;} break;
                        case "\"age\"":Matcher hAge=Pattern.compile("\"[0-9]+\"").matcher(buffString[1]);
                            if(hAge.matches()){
                                if(age!=-1)System.out.println("Атрибут age введён несколько раз, будет использовано последнее значение: "+buffString[1]);
                                age=Integer.parseInt(buffString[1].substring(1,buffString[1].length()-1));
                                hasAttributes[1]=true;}else
                            {System.out.println("В возрасте могут присутствовать только цифры!");}break;
                        case "\"loc\"":if(location!=null)System.out.println("Атрибут location введён несколько раз, будет использовано корректное последнее значение"+buffString[1]);
                            location=buffString[1].substring(1,buffString[1].length()-1);
                            hasAttributes[2]=true; break;
                        default:System.out.println("В классе Human нет поля "+buffString[0]);
                    }
                }
                if(hasAttributes[0]&&hasAttributes[1]&&hasAttributes[2]){ return (new Human(name,age,location));}
                else{if(!hasAttributes[0]) System.out.println("Атрибут name не был задан");
                    if(!hasAttributes[1])System.out.println("Атрибут age не был задан");
                    if(!hasAttributes[2])System.out.println("Атрибут location не был задан");}
                } break;
            case "{\"JustItem\":{":{
                String name=null;
                boolean[] hasAttributes=new boolean[2];
                int value=-1;
                Matcher forJustItem=Pattern.compile("\"[A-z]+\":\"[A-z,А-я,0-9]+\"").matcher(parseString);
                while(forJustItem.find()){
                    String[] buffString=forJustItem.group().split(":");
                    switch(buffString[0]){
                        case "\"name\"": Matcher hName=Pattern.compile("[0-9]").matcher(buffString[1]);
                            if(hName.find()){System.out.println("Поле name может содержать исключительно символы латинского алфавита и кириллицы");}
                            else{
                                if(name!=null)System.out.println("Атрибут name введён несколько раз, будет использовано последнее значение: "+buffString[1]);
                                name=buffString[1].substring(1,buffString[1].length()-1);
                                hasAttributes[0]=true;} break;
                        case "\"value\"":Matcher hAge=Pattern.compile("[0-9]+").matcher(buffString[1]);
                            if(hAge.find()){
                                if(value!=-1)System.out.println("Атрибут value введён несколько раз, будет использовано последнее значение: ");
                                value=Integer.parseInt(buffString[1].substring(1,buffString[1].length()-1));
                                hasAttributes[1]=true;}else
                            {System.out.println("В цене могут присутствовать только цифры!");}break;
                        default:System.out.println("В классе JustItem нет поля "+buffString[0]);
                    }
                }
                if(hasAttributes[0]&&hasAttributes[1]){return (new JustItem(name,value));}
                else{if(!hasAttributes[0]) System.out.println("Атрибут name не был задан");
                    if(!hasAttributes[1])System.out.println("Атрибут value не был задан");}
            }break;
            case "{\"Container\":{": {
                Container bufContainer = new Container(sepString[1].substring(8, sepString[1].length() - 2), Integer.parseInt(sepString[2].substring(8, sepString[2].length() - 2)));
                int b1 = Integer.parseInt(sepString[2].substring(8, sepString[2].length() - 2));
                int b2 = Integer.parseInt(sepString[3].substring(9, sepString[3].length() - 2));
                for (int i = 0; i < (b1 - b2); i++) {
                    parseString=itemReader.nextLine();
                    String name=null;
                    boolean[] hasAttributes=new boolean[2];
                    int value=-1;
                    Matcher forJustItem=Pattern.compile("\"[A-z]+\":\"[A-z,А-я,0-9]+\"").matcher(parseString);
                    while(forJustItem.find()){
                        String[] buffString=forJustItem.group().split(":");
                        switch(buffString[0]){
                            case "\"name\"": Matcher hName=Pattern.compile("[0-9]").matcher(buffString[1]);
                                if(hName.find()){System.out.println("Поле name может содержать исключительно символы латинского алфавита и кириллицы");}
                                else{
                                    if(name!=null)System.out.println("Атрибут name введён несколько раз, будет использовано последнее значение: "+buffString[1]);
                                    name=buffString[1].substring(1,buffString[1].length()-1);
                                    hasAttributes[0]=true;} break;
                            case "\"value\"":Matcher hAge=Pattern.compile("[0-9]+").matcher(buffString[1]);
                                if(hAge.find()){
                                    if(value!=-1)System.out.println("Атрибут value введён несколько раз, будет использовано последнее значение: ");
                                    value=Integer.parseInt(buffString[1].substring(1,buffString[1].length()-1));
                                    hasAttributes[1]=true;}else
                                {System.out.println("В цене могут присутствовать только цифры!");}break;
                            default:System.out.println("В классе JustItem нет поля "+buffString[0]);
                        }
                    }
                    if(hasAttributes[0]&&hasAttributes[1]){new JustItem(name,value).PutIn(bufContainer);}
                    else{if(!hasAttributes[0]) System.out.println("Атрибут name не был задан");
                        if(!hasAttributes[1])System.out.println("Атрибут value не был задан");}
                }
                itemReader.nextLine();
                return bufContainer;
            }
            default:System.out.println("Объекты данного типа не могут быть занесены в коллекцию");
        }}catch(IndexOutOfBoundsException e){System.out.println("Ошибка преобразования объекта");}catch(NumberFormatException l){System.out.println("Ошибка преобразования численного поля");}
        return 0;
    }

    public static LabCollection ImportFrom(String path){
        LabCollection a=new LabCollection();
        try{
            File input=new File(path);
        Scanner startReader=new Scanner(input);
        String currentString=startReader.nextLine();
        a=new LabCollection(currentString.split(" ")[1].substring(6,currentString.split(" ")[1].length()-2));
        currentString=startReader.nextLine();
        while(!currentString.contains("</Collection>")){
            String[] sepString=currentString.split(" ");
            if(!currentString.contains("<Container")){
                switch(sepString[0]){
                    case "\t<Human":{
                        String name=null;
                        boolean[] hasAttributes=new boolean[3];
                        int age=-1;
                        String location=null;
                        Matcher forHuman=Pattern.compile("[A-z]+=\"[A-z,А-я,0-9]+\"").matcher(currentString);
                        while(forHuman.find()){
                            String[] buffString=forHuman.group().split("=");
                            switch(buffString[0]){
                                case "name": Matcher hName=Pattern.compile("[0-9]").matcher(buffString[1]);
                                    if(hName.find()){System.out.println("Поле name может содержать исключительно символы латинского алфавита и кириллицы");}
                                    else{if(name!=null)System.out.println("Атрибут name введён повторно, будет использовано следующее значение: "+buffString[1]);
                                        name=buffString[1].substring(1,buffString[1].length()-1);
                                    hasAttributes[0]=true;} break;
                                case "age":Matcher hAge=Pattern.compile("\"[0-9]+\"").matcher(buffString[1]);
                                    if(hAge.matches()){
                                    if(age!=-1)System.out.println("Атрибут age введён несколько раз, будет использовано последнее значение: "+buffString[1]);
                                    age=Integer.parseInt(buffString[1].substring(1,buffString[1].length()-1));
                                    hasAttributes[1]=true;}else
                                    {System.out.println("В возрасте могут присутствовать только цифры!");}break;
                                case "loc":if(location!=null)System.out.println("Атрибут location введён несколько раз, будет использовано корректное последнее значение"+buffString[1]);
                                    location=buffString[1].substring(1,buffString[1].length()-1);
                                    hasAttributes[2]=true; break;
                                default:System.out.println("В классе Human нет поля "+buffString[0]);
                            }
                        }
                        if(hasAttributes[0]&&hasAttributes[1]&&hasAttributes[2]){a.getUselessData().add(new Human(name,age,location));}
                        else{if(!hasAttributes[0]) System.out.println("Атрибут name не был задан");
                        if(!hasAttributes[1])System.out.println("Атрибут age не был задан");
                        if(!hasAttributes[2])System.out.println("Атрибут location не был задан");}
                    }break;
                    case "\t<JustItem":
                        String name=null;
                        boolean[] hasAttributes=new boolean[2];
                        int value=-1;
                        Matcher forJustItem=Pattern.compile("[A-z]+=\"[A-z,А-я,0-9]+\"").matcher(currentString);
                        while(forJustItem.find()){
                            String[] buffString=forJustItem.group().split("=");
                            switch(buffString[0]){
                                case "name": Matcher hName=Pattern.compile("[0-9]").matcher(buffString[1]);
                                    if(hName.find()){System.out.println("Поле name может содержать исключительно символы латинского алфавита и кириллицы");}
                                    else{
                                        if(name!=null)System.out.println("Атрибут name введён несколько раз, будет использовано последнее значение: "+buffString[1]);
                                        name=buffString[1].substring(1,buffString[1].length()-1);
                                        hasAttributes[0]=true;} break;
                                case "value":Matcher hAge=Pattern.compile("[0-9]+").matcher(buffString[1]);
                                    if(hAge.find()){
                                        if(value!=-1)System.out.println("Атрибут value введён несколько раз, будет использовано последнее значение: ");
                                        value=Integer.parseInt(buffString[1].substring(1,buffString[1].length()-1));
                                        hasAttributes[1]=true;}else
                                    {System.out.println("В цене могут присутствовать только цифры!");}break;
                                default:System.out.println("В классе JustItem нет поля "+buffString[0]);
                            }
                        }
                        if(hasAttributes[0]&&hasAttributes[1]){a.getUselessData().add(new JustItem(name,value));}
                        else{if(!hasAttributes[0]) System.out.println("Атрибут name не был задан");
                            if(!hasAttributes[1])System.out.println("Атрибут value не был задан");}break;
                }
            }
            else{
                Container c=new Container(sepString[1].substring(6,sepString[1].length()-1),Integer.parseInt(sepString[2].substring(6,sepString[2].length()-1)));
                startReader.nextLine();
                currentString=startReader.nextLine();
                int b1=Integer.parseInt(sepString[2].substring(6,sepString[2].length()-1));
                int b2=Integer.parseInt(sepString[3].substring(7,sepString[3].length()-2));
                for(int i=0;i<(b1-b2);i++){
                    String name=null;
                    boolean[] hasAttributes=new boolean[2];
                    int value=-1;
                    Matcher forHuman=Pattern.compile("[A-z]+=\"[A-z,А-я,0-9]+\"").matcher(currentString);
                    while(forHuman.find()){
                        String[] buffString=forHuman.group().split("=");
                        switch(buffString[0]){
                            case "name": Matcher hName=Pattern.compile("[0-9]").matcher(buffString[1]);
                                if(hName.find()){System.out.println("Поле name может содержать исключительно символы латинского алфавита и кириллицы");}
                                else{
                                    if(name!=null)System.out.println("Атрибут name введён несколько раз, будет использовано последнее значение: "+buffString[1]);
                                    name=buffString[1].substring(1,buffString[1].length()-1);
                                    hasAttributes[0]=true;} break;
                            case "value":Matcher hAge=Pattern.compile("[0-9]+").matcher(buffString[1]);
                                if(hAge.find()){
                                    if(value!=-1)System.out.println("Атрибут age введён несколько раз, будет использовано последнее значение: ");
                                    value=Integer.parseInt(buffString[1].substring(1,buffString[1].length()-1));
                                    hasAttributes[1]=true;}else
                                {System.out.println("В возрасте могут присутствовать только цифры!");}break;
                            default:System.out.println("В классе Human нет поля "+buffString[0]);
                        }
                    }
                    if(hasAttributes[0]&&hasAttributes[1]){new JustItem(name,value).PutIn(c);}
                    else{if(!hasAttributes[0]) System.out.println("Атрибут name не был задан");
                        if(!hasAttributes[1])System.out.println("Атрибут value не был задан");
                }currentString=startReader.nextLine();}
                startReader.nextLine();
                a.getUselessData().add(c);

            }
            currentString=startReader.nextLine();
    }}catch (FileNotFoundException e){System.out.println("Файл не найден");}
    return a;
    }


    public static void SaveCollection(LabCollection collection){
        try {
            FileWriter toEmptyFile=new FileWriter(defaultPath);
            BufferedWriter headerWriter=new BufferedWriter(toEmptyFile);
            headerWriter.write("<Collection name=\""+collection.getName()+"\">\n");
            headerWriter.close();
            FileWriter fileWriter=new FileWriter(defaultPath,true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Comparable elem : collection.getUselessData()) {
                String[] a=elem.toString().split(" ");
                switch (elem.toString().charAt(0)){
                    case 'Ч':{writer.write("\t<Human name=\""+a[3]+"\" age=\""+a[5]+"\" loc=\""+a[9]+"\"/>"); writer.newLine(); break;}
                    case 'П':{writer.write("\t<JustItem name=\""+a[1]+"\" value=\""+a[3]+"\"/>"); writer.newLine(); break;}
                    case 'К':{writer.write("\t<Container name=\""+a[1]+"\" size=\""+a[3]+"\" space=\""+(Integer.parseInt(a[3])-Integer.parseInt(a[5]))+"\">");
                        writer.newLine();
                        writer.write("\t\t<Contents>");
                        writer.newLine();
                        Container bc=(Container) elem;
                        for(JustItem conElem:bc.getContents()){
                        writer.write("\t\t<JustItem name=\""+conElem.getName()+"\" value=\""+conElem.getValue()+"\"/>");
                            writer.newLine();
                        }
                        writer.write("\t\t</Contents>");
                        writer.newLine();
                        writer.write("\t</Container>");
                        writer.newLine();
                    break; }
                }
                writer.flush();}
            writer.write("</Collection>");
        writer.close();}
        catch(Exception f){System.out.println(f.getCause()+" "+f.getMessage());
        }
    }
}

