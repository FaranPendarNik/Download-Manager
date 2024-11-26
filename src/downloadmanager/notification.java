
package downloadmanager;
import javafx.geometry.Pos;
import javafx.scene.image.*;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
public class notification {
    
    private String title;
    private String text;
    private String addressImage;
    private Image img;

    public notification(String title, String text, Image img) {
        this.title = title;
        this.text = text;
        this.addressImage = addressImage;
        this.img = img;      
        Notifications ntf = Notifications.create()
        .title(title)
        .text(text)
        .position(Pos.TOP_LEFT)
        .graphic(new ImageView(img))
        .hideAfter(Duration.seconds(8));
        ntf.show();
    }   
    
    
    
}
