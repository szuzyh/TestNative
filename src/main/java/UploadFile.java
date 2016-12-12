import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by Leo on 2016/12/7.
 */
public class UploadFile {
    public UploadFile(String[] msgString, String picPath, String videoPath) throws InterruptedException {
        if (msgString==null||picPath==null||videoPath==null){
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

    public UploadFile(String[] msgString, final String path) throws IOException {
        if (path==null||msgString==null){
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
        } else {
            for (String s:msgString
                    ) {
                System.out.println(s);
            }
            if (path.endsWith(".mp4")){
//                Spark.post("/Video", new Route() {
//                    @Override
//                    public Object handle(Request request, Response response) throws Exception {
//                        File video=new File(path);
//                        InputStream in=new FileInputStream(video);
//                        byte[] videoByte=new byte[in.available()];
//                        in.read(videoByte);
//                        OutputStream out=null;
//                        out.write(videoByte);
//                        request.headers("video/x-msvideo");
//                        return videoByte;
//                    }
//                });
                System.out.println("finish");
            }
            if (path.endsWith(".jpg")){

            }
        }

    }
}
