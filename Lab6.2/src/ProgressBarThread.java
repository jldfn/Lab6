import javax.swing.*;
import java.util.*;
import java.util.Timer;

/**
 * Created by Алексей on 08.05.2017.
 */
public class ProgressBarThread extends Thread {
    private JProgressBar jProgressBar = new JProgressBar();
    //private volatile boolean cancelled = false;

    ProgressBarThread(JProgressBar jpb) {
        jProgressBar = jpb;
    }

    public JProgressBar getJPB() {
        return jProgressBar;
    }

    @Override
    public void run() {
       try{
           jProgressBar.setIndeterminate(true);
           sleep(100000);
       }catch (InterruptedException e1){
           System.out.println("Interrupted");
           try {
               sleep(100);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           jProgressBar.setIndeterminate(false);
       }
    }
}
