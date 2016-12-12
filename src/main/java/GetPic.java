import com.github.sarxos.webcam.Webcam;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Leo on 2016/12/7.
 */
public class GetPic {
    public GetPic(Webcam webCam,String path) throws IOException {
        try {
            webCam.open(true);
            ImageIO.write(webCam.getImage(),"JPEG",new File("E:\\"+path+".jpg"));
            new attentionWindow("截图完成！","23.jpg",500,400);
        } catch (Exception e) {
            new attentionWindow("请选择摄像头后重试！","1.jpg",500,400);
        }
        System.out.println("get!");
    }
}
