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

        } catch (Exception e) {
            JLabel warningLabel=new JLabel("请选择摄像机后重试");
            ImageIcon image = new ImageIcon("1.jpg");
            warningLabel.setIcon(image);
            JFrame frame=new JFrame();
            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setSize(new Dimension(500,400));
            frame.setVisible(true);
            frame.add(warningLabel);
        }
        ImageIO.write(webCam.getImage(),"JPEG",new File("E:\\"+path+".jpg"));
        System.out.println("get!");
    }
}
