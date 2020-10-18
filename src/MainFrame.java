import javax.swing.*;
import java.awt.*;

class MainFrame extends JFrame
{
    private final Dimension frameSize = new Dimension(300,300);
    public MainFrame()
    {
        super("Вычисление формул");
        this.setSize(frameSize);
        this.setLocationRelativeTo(null);
        Box box = Box.createVerticalBox();
        box.setBorder( BorderFactory.createLineBorder(Color.YELLOW));
        JLabel label = new JLabel("form 1",JLabel.LEFT);
        this.setLayout(new FlowLayout());
        box.add(Box.createVerticalGlue());
        box.add(label);
        box.add(Box.createHorizontalStrut(10));
        //JPanel p = this.getContentPane();
        add(box, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
