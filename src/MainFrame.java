import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

class MainFrame extends JFrame
{
    private final Dimension frameSize = new Dimension(540,40);
    //formula variables
    private Box formulaBox = null;
    private JLabel formula1Label = null;
    private JLabel formula2Label = null;
    private int formulaId = 0;
    // memory variables
    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;
    private Double curMem = null;
    // text fields
    private JTextField textFieldX = null;
    private JTextField textFieldY = null;
    private JTextField textFieldZ = null;
    private JTextField textFieldResult = null;
    // methods
    private Box createControlBox()
    {
        return null;
    }
    private Box createMemoryBox()
    {
        return null;
    }
    private Box createResultBox()
    {
        return null;
    }
    private Box createInputBox()
    {
        Box box = Box.createHorizontalBox();
        Box boxX = Box.createHorizontalBox();
        Box boxY = Box.createHorizontalBox();
        Box boxZ = Box.createHorizontalBox();
        textFieldX = new JTextField("0",10);
        textFieldY = new JTextField("0",10);
        textFieldZ = new JTextField("0",10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        JLabel labelX = new JLabel("X:");
        JLabel labelY = new JLabel("Y:");
        JLabel labelZ = new JLabel("Z:");
        int labelIdent = 15;
        int boxIdent = 20;
        boxX.add(labelX);
        boxX.add(Box.createHorizontalStrut(labelIdent));
        boxX.add(textFieldX);
        boxY.add(labelY);
        boxY.add(Box.createHorizontalStrut(labelIdent));
        boxY.add(textFieldY);
        boxZ.add(labelZ);
        boxZ.add(Box.createHorizontalStrut(labelIdent));
        boxZ.add(textFieldZ);
        box.add(Box.createHorizontalGlue());
        box.add(boxX);
        box.add(Box.createHorizontalStrut(boxIdent));
        box.add(boxY);
        box.add(Box.createHorizontalStrut(boxIdent));
        box.add(boxZ);
        box.add(Box.createHorizontalGlue());
        return box;
    }
    private JRadioButton createFRadioButton(String buttonName, final int formulaId,final JLabel formulaImage)
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
        JRadioButton formula1Select = createFRadioButton("Формула 1",1,formula1Label);
        JRadioButton formula2Select = createFRadioButton("Формула 2",2,formula2Label);
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
        Box box = Box.createVerticalBox();
        formulaBox = this.createFormulaBox();
        Box inputBox = this.createInputBox();
        box.add(formulaBox);
        box.add(inputBox);
        this.add(box);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

    }
}
