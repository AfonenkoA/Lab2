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
    private JRadioButton createMRadioButton(String buttonName, final Double mem)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainFrame.this.curMem = mem;
                MainFrame.this.pack();
                MainFrame.this.revalidate();
            }});
        return button;
    }
    private JPanel createMemoryPanel()
    {
        Box box = Box.createVerticalBox();
        Box mem1Box = Box.createHorizontalBox();
        Box mem2Box = Box.createHorizontalBox();
        Box mem3Box = Box.createHorizontalBox();
        JRadioButton mem11Select = createMRadioButton("Переменная 1",mem1);
        JRadioButton mem21Select = createMRadioButton("Переменная 2",mem2);
        JRadioButton mem31Select = createMRadioButton("Переменная 3",mem3);;
        ButtonGroup memGroup = new ButtonGroup();
        memGroup.add(mem11Select);
        memGroup.add(mem21Select);
        memGroup.add(mem31Select);
        memGroup.setSelected(memGroup.getElements().nextElement().getModel(), true);
        int ident = 10;
        mem1Box.add(mem11Select);
        mem1Box.add(Box.createHorizontalStrut(ident));
        mem1Box.add(new JLabel(mem1.toString()));
        mem2Box.add(mem21Select);
        mem2Box.add(Box.createHorizontalStrut(ident));
        mem2Box.add(new JLabel(mem2.toString()));
        mem3Box.add(mem31Select);
        mem3Box.add(Box.createHorizontalStrut(ident));
        mem3Box.add(new JLabel(mem3.toString()));
        box.add(mem1Box);
        box.add(mem2Box);
        box.add(mem3Box);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(box);
        return panel;
    }
    private Box createResultBox()
    {
        return null;
    }
    private JPanel createInputPanel()
    {
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
        Box box = Box.createVerticalBox();
        box.add(Box.createHorizontalGlue());
        box.add(boxX);
        box.add(boxY);
        box.add(boxZ);
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.add(box);
        return panel;
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
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel inputPanel = this.createInputPanel();
        JPanel memPanel = this.createMemoryPanel();
        panel.add(memPanel);
        panel.add(inputPanel);
        box.add(formulaBox);
        box.add(panel);
        this.getContentPane().add(box);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();

    }
}
