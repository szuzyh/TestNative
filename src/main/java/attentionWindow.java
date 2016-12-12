import javax.swing.*;
import java.awt.*;

/**
 * Created by Leo on 2016/12/8.
 */
public class attentionWindow {
    public attentionWindow(String msg, String path, int width, int height) {
        JLabel attentionLabel=new JLabel(msg);
        ImageIcon image = new ImageIcon(path);
        attentionLabel.setIcon(image);
        attentionLabel.setVerticalTextPosition(JLabel.BOTTOM);
        attentionLabel.setHorizontalTextPosition(JLabel.CENTER);
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.pack();

        frame.setSize(new Dimension(width,height));
        frame.setVisible(true);
        frame.add(attentionLabel);
    }
}
