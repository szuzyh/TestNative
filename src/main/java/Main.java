import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.github.sarxos.webcam.ds.nativeapi.NativeWebcamDriver;
import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by Leo on 2016/12/6.
 */
public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {
        Webcam.setDriver(new NativeWebcamDriver());

        List<Webcam> webcams = Webcam.getWebcams();
        System.out.println("Number of Webcams found: "+webcams.size());
        for (Webcam wc: webcams) {
            System.out.println(wc);
            System.out.println("Supported Resolutions: ");
            for(Dimension res: wc.getViewSizes()) {
                System.out.println(res);
            }
        }

        Webcam webcam = Webcam.getDefault();
        if (webcam != null) {
//            webcam.setViewSize(WebcamResolution.VGA.getSize());
//            webcam.open();
//            ImageIO.write(webcam.getImage(),"JPEG",new File("E:\\m.jpg"));
//
//
            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);

            System.out.println(webcam.getImage().getWidth());
            System.out.println(webcam.getImage().getHeight());
            JFrame window = new JFrame("Test webcam panel");
            window.add(panel);
            window.setResizable(true);
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setVisible(true);
            new CreateVideo("6",webcam);
//            File file = new File("E:\\output.ts");
//
//            IMediaWriter writer = ToolFactory.makeWriter(file.getName());
//            Dimension size = WebcamResolution.QVGA.getSize();
//
//            writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_H264, size.width, size.height);
//            webcam.setViewSize(size);
//            webcam.open(true);
        }
    }
}
