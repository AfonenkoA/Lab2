import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class MainFrame extends JFrame
{
    private final Dimension frameSize = new Dimension(540,40);
    private Box formulaBox = null;
    private JLabel formula1Label = null;
    private JLabel formula2Label = null;
    private int formulaId = 0;
    private JRadioButton createRadioButton(String buttonName, final int formulaId,final JLabel formulaImage)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainFrame.this.formulaId = formulaId;
                MainFrame.this.formulaBox.remove(1);
                MainFrame.this.formulaBox.add(formulaImage);
                MainFrame.this.pack();
                MainFrame.this.revalidate();
            }});
        return button;
    }
    private Box createFormulaBox()
    {
        // common container
        Box box = Box.createHorizontalBox();
        // image part
        String formula1Path = "src/Formula1.png";
        String formula2Path = "src/Formula2.png";
        try
        {
            formula1Label = new JLabel(new ImageIcon(ImageIO.read(new File(formula1Path))));
            formula2Label = new JLabel(new ImageIcon(ImageIO.read(new File(formula2Path))));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //radio group part
        JRadioButton formula1Select = createRadioButton("Формула 1",1,formula1Label);
        JRadioButton formula2Select = createRadioButton("Формула 2",2,formula2Label);
        ButtonGroup formulaGroup = new ButtonGroup();
        formulaGroup.add(formula1Select);
        formulaGroup.add(formula2Select);
        formulaGroup.setSelected(formulaGroup.getElements().nextElement().getModel(), true);
        Box formulaRadioBox = Box.createVerticalBox();
        formulaRadioBox.add(formula1Select);
        formulaRadioBox.add(formula2Select);
        //add to common container
        box.add(formulaRadioBox);
        box.add(formula1Label);
        return box;
    }
    public MainFrame()
    {
        super("Вычисление формул");
        this.setSize(frameSize);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        formulaBox = this.createFormulaBox();
        this.add(formulaBox);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
}
