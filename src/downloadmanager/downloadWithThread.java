
package downloadmanager;

import java.io.*;
import java.net.*;
import javafx.geometry.Pos;
import javafx.scene.image.*;
import javafx.util.*;
import org.controlsfx.control.Notifications;

public class downloadWithThread extends Thread {
    
    private String url;
    private final int timeOut = 1000;
    private final int blockSize = 1024;
    private downloadable level;
    
    public downloadWithThread(String url,downloadable level) {
        this.url = url;
        this.level = level;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection connect =(HttpURLConnection) new URL(url).openConnection();
            connect.setReadTimeout(timeOut);
            String fileName = new java.io.File(url).getName();
            File fileNew = new File(fileName);
            String filePath = this.level.showSave(fileNew);
            
            if (filePath.isEmpty()) {
                this.level.inTheFailed("File Path is Empty !");
                Image img = new Image("/photo/2.png");
                new notification("Error","File Path is Empty !",img);
            }
            
            int fileSize = connect.getContentLength();
            FileOutputStream fileMaking = new FileOutputStream(new File(filePath));
            this.level.inTheStrat();
            InputStream FileRead = connect.getInputStream();
            byte fileBuffer[] = new byte[blockSize];
            int blockLength = -1;
            double total = 0;
            while((blockLength = FileRead.read(fileBuffer,0,blockSize)) != -1){
                total += blockLength;  // remaning volume 
                fileMaking.write(fileBuffer,0,blockSize);
                this.level.inTheProgress("Download   ",(int)((total/fileSize)*10000));
            }
            FileRead.close();
            fileMaking.close();
            this.level.inTheComplet();
            
        } catch (Exception e) {
            this.level.inTheFailed("Error Downloading : " + e.getMessage());
            
            Image img = new Image("/photo/dont.png");
            new notification("Error During Download Process",e.getMessage(),img);
        }
    }
    
    
    
    
}
