import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.TreeSet;

/**
 * Created by Денис on 25.04.2017.
 */
public abstract class LabListener implements ActionListener {
    private JTextField nameField;
    private JSpinner ageSpinner;
    private JTextField locField;
    private TreeSet<Human> collection;
    private LabTable table;
    private JProgressBar jProgress;

    LabListener(JTextField nameField, JSpinner ageSpinner, JTextField locField, TreeSet<Human> col, LabTable colTable,JProgressBar jpb){
        super();
        this.nameField=nameField;
        this.ageSpinner=ageSpinner;
        this.locField=locField;
        jProgress=jpb;
        collection=col;
        table=colTable;
    }

    LabListener(JTextField field, TreeSet<Human> col, LabTable colTable, JProgressBar jpb){
        super();
        collection=col;
        nameField=field;
        jProgress=jpb;
        this.table=colTable;
    }

    public TreeSet<Human> getCollection() {
        return collection;
    }


    public LabTable getTable() {
        return table;
    }


    public JTextField getNameField() {
        return nameField;
    }

    public JSpinner getAgeSpinner() {
        return ageSpinner;
    }

    public JTextField getLocField() {
        return locField;
    }

    protected void makeCall(String command,Human object) {
        try {
            SocketAddress address = new InetSocketAddress(ConsoleApp.HOSTNAME, 8885);
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] sendData;
            byte[] receiveData = new byte[1024];
            String sentence = command + " " + object.toString();
            sendData = sentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address);
            clientSocket.send(sendPacket);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        }catch(Exception e){e.printStackTrace();}
    }
}
