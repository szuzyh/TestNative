import javax.swing.*;
import java.awt.*;

/**
 * Created by Leo on 2016/12/7.
 */
public class UploadFile {
    public UploadFile(String[] msgString, String picPath, String videoPath) throws InterruptedException {
        if (msgString[0]==null||picPath==null||videoPath==null){
            JLabel warningLabel=new JLabel("信息录入不完全！");
            ImageIcon image = new ImageIcon("1.jpg");
            warningLabel.setIcon(image);
            JFrame frame=new JFrame();
//            frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.pack();
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setSize(new Dimension(500,400));
            frame.setVisible(true);
            frame.add(warningLabel);
//            Thread.sleep(3000);
//            frame.dispose();
        }else {
            for (String s: msgString
                    ) {
                System.out.println(s);
            }
            System.out.println(picPath);
            System.out.println(videoPath);

        }

    }

    public UploadFile(String[] msgString, String path) {
        for (String s:msgString
             ) {
            System.out.println(s);
        }
        if (path.endsWith(".mp4")){
            System.out.println("录像："+path);
        }
        if (path.endsWith(".jpg")){
            System.out.println("截图："+path);
        }
    }
}
