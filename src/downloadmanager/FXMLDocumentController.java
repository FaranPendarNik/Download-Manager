/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package downloadmanager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.shape.Polyline;
import javax.swing.JFileChooser;

/**
 *
 * @author sorosh
 */
public class FXMLDocumentController implements Initializable,downloadable {

    @FXML
    private TextField txtUrl;
    @FXML
    private ProgressIndicator progress1;
    @FXML
    private JFXTextArea txtAreaInfo;
    @FXML
    private JFXButton btnDownload;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }    
    String setUrl;
    
    public boolean urlIsTrue (String setUrl){
    return setUrl.startsWith("http");
    }
    
    public void start (String setUrl){
    downloadWithThread dwt = new downloadWithThread(setUrl, this);
    dwt.start();
    }
    

    @Override
    public void inTheStrat() {
        txtAreaInfo.appendText("download start ...\n");
    }

    @Override
    public void inTheProgress(String description, int remaningVolume) {
        txtAreaInfo.appendText(description + " " + remaningVolume +"%\n");
        
        progress1.setProgress(remaningVolume);
    }

    @Override
    public void inTheFailed(String Error) {
        txtAreaInfo.appendText(Error+"\n");
    }

    @Override
    public void inTheComplet() {
        txtAreaInfo.appendText("... Download Complet ! ...\n");
    }

    @Override
    public String showSave(File file) {
        
        JFileChooser choose = new JFileChooser();
        choose.setSelectedFile(file);
        choose.setDialogTitle("save " + file.getName());       
        int select = choose.showSaveDialog(null);
        if (select == JFileChooser.APPROVE_OPTION) {
        File fileType = choose.getSelectedFile();  //get File Type
        return fileType.getAbsolutePath();
        }
        return null;
    }

    @FXML
    private void download(ActionEvent event) {
        setUrl = txtUrl.getText();
        if (urlIsTrue(setUrl)) {
            start(setUrl);
        }else{
            Image img = new Image("/photo/404.png");
            new notification("Incorrect URL","The File Address is Incorrect.",img);
        }
    }
    
}
