package org.eu.sixlab.sixtools;

import javax.swing.*;
import java.awt.*;

/**
 * Created by loki/六楼的雨 on 2015/1/18.
 */
public class SixTools {
    private JPanel mainPanel;
    private JToolBar toolBar;
    private JTabbedPane tabPanel;
    private JPanel tab1;
    private JPanel tab2;
    private JPanel tab3;
    private JPanel tab4;
    private JPanel tab5;

    public SixTools() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("SixTools");
        frame.setContentPane(new SixTools().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setFrameSize(frame);

        frame.setVisible(true);
    }

    public static JFrame setFrameSize(JFrame frame) {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Double screenHeight = screenSize.getHeight();
        Double screenWidth  = screenSize.getWidth();

        Double goldProportion = (Math.sqrt(5)-1)/2;

        Integer goldHeight = (int)Math.floor((screenHeight * goldProportion));
        Integer goldWidth = (int)Math.floor((screenWidth * goldProportion)) ;

        frame.setPreferredSize(new Dimension(goldWidth + 16, goldHeight + 38));
        frame.pack();

        Integer x = (int)((screenWidth/2)  - goldWidth/2 );
        Integer y = (int)((screenHeight/2) - goldHeight/2);
        frame.setLocation(x, y);

        return frame;
    }
}
