package edu.neu.nutrons.lib;

import com.sun.squawk.microedition.io.FileConnection;
import edu.wpi.first.wpilibj.Timer;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;

/**
 * Keeps a log of values and saves them to a file.
 *
 * @author Ziv
 */
public class Log extends Vector {

    private String path;
    private Timer t = new Timer();

    public Log(String path) {
        this.path = path;
        t.start();
    }

    public void put(double x) {
        double[] data = new double[2];
        data[0] = t.get();
        data[1] = x;
        addElement(data);
    }

    public void save() {
        DataOutputStream theFile = null;
        FileConnection fc = null;
        try {
            fc = (FileConnection) Connector.open("file:///" + path, Connector.WRITE);
            fc.create();
            theFile = fc.openDataOutputStream();
        }
        catch(Exception ex) {
            System.out.println("Failed to open data output stream");
        }
        if(fc != null) {
            System.out.println("Data output stream opened successfully");
            String output = "";
            for(Enumeration e = this.elements(); e.hasMoreElements();) {
                double[] point = (double[])e.nextElement();
                output += point[0] + " " + point[1] + "\n";
            }
            boolean worked = true;
            try {
                // theFile.write(nextLine.getBytes(), 0, nextLine.length());
                theFile.writeUTF(output);
            }
            catch(IOException ex) {
                worked = false;
                System.out.println("Failed to load file " + path);
            }
            try {
                fc.close();
            }
            catch(IOException ex) {
                worked = false;
                System.out.println("Failed to close file " + path);
            }
            if(worked) {
                System.out.println("Wrote");
                System.out.print(output);
                System.out.println("to " + path);
            }
        }
    }
}
