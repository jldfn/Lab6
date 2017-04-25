/**
 * Created by Денис on 02.03.2017.
 */

import javax.swing.*;
import java.io.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleApp {
    final static String defaultPath="C:/Users/Денис/Desktop/input.txt";


    public static void main(String[] args) {
        LabCollection ExpCol=ImportFrom(defaultPath);
        LabFrame frame=new LabFrame("Test",ExpCol.getUselessData());
        frame.setVisible(true);
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
            frame.getTable().fireTableDataChanged();
            currentString=consoleReader.nextLine();
        }
        SaveCollection(ExpCol);
    }

    public static Comparable ParseJSON(String parseString) {
        String[] sepString = parseString.split(" ");
        Scanner itemReader = new Scanner(System.in);
        try{
        if(sepString[0].contains("\"Human\":{")){
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
                }else{System.out.println("Объекты данного типа не могут быть занесены в коллекцию");}
        }catch(IndexOutOfBoundsException e){System.out.println("Ошибка преобразования объекта");}catch(NumberFormatException l){System.out.println("Ошибка преобразования численного поля");}
        return 0;
    }

    public static LabCollection ImportFrom(String path){
        LabCollection a=new LabCollection();
        try{
            File input=new File(path);
            Scanner startReader=new Scanner(input);
            String currentString=startReader.nextLine();
            a=new LabCollection(currentString.split(" ")[1].substring(6,currentString.split(" ")[1].length()-2));
            currentString= startReader.nextLine();
            while(!currentString.contains("</Collection>")){
            String[] sepString=currentString.split(" ");
                System.out.println(sepString[0].matches("\t<Human"));
                if(sepString[0].matches("\t<Human")){
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
                }else{System.out.println("Объекты данного типа не могут быть занесены в коллекцию");}
                currentString=startReader.nextLine();
            }
        }catch (FileNotFoundException e){System.out.println("Файл не найден");}
    return a;
    }


    public static void SaveCollection(LabCollection collection){
        try {
            FileWriter toEmptyFile=new FileWriter(defaultPath);
            BufferedWriter headerWriter=new BufferedWriter(toEmptyFile);
            headerWriter.write("<Collection name=\""+collection.getName()+"\">");
            headerWriter.close();
            FileWriter fileWriter=new FileWriter(defaultPath,true);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            writer.newLine();
            for (Comparable elem : collection.getUselessData()) {
                String[] a=elem.toString().split(" ");
                switch (elem.toString().charAt(0)){
                    case 'Ч':{writer.write("\t<Human name=\""+a[3]+"\" age=\""+a[5]+"\" loc=\""+a[9]+"\"/>"); writer.newLine(); break;}
                }
                writer.flush();}
            writer.write("</Collection>");
        writer.close();}
        catch(Exception f){System.out.println(f.getCause()+" "+f.getMessage());
        }
    }
}

