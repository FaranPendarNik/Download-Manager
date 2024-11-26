
package downloadmanager;
import java.io.File;
public interface downloadable{
 
    void inTheStrat();
    void inTheProgress(String description, int remaningVolume);
    void inTheFailed(String Error);
    void inTheComplet();
    
    String showSave(File file);
    
}
