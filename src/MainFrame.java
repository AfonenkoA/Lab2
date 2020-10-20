import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static java.lang.Math.*;

class MainFrame extends JFrame
{
    private final Dimension frameSize = new Dimension(540, 40);
    //formula variables
    private final Box formulaBox;
    private JLabel formula1Label = null;
    private JLabel formula2Label = null;
    private int formulaId = 1;
    // memory variables
    private JLabel mem1Label;
    private JLabel mem2Label;
    private JLabel mem3Label;
    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;
    private int memId = 1;
    // text fields
    private JTextField textFieldX = null;
    private JTextField textFieldY = null;
    private JTextField textFieldZ = null;
    private JTextField textFieldResult = null;

    // methods
    private double formula1(double x, double y, double z)
    {
        return sin(log(y)+sin(PI*y*y))*pow(x*x+sin(z)+exp(cos(z)),1.0/4);
    }
    private double formula2(double x, double y, double z)
    {
        return pow(cos(exp(x))+pow(log(1+y),2)+sqrt(exp(cos(x))+pow(sin(PI*z),2))+sqrt(1.0/x)+pow(cos(y),2),sin(z));
    }
    private JPanel createResultPanel()
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Box box = Box.createHorizontalBox();
        box.add(new JLabel("Результат"));
        box.add(Box.createHorizontalStrut(10));
        textFieldResult = new JTextField("0", 10);
        box.add(textFieldResult);
        panel.add(box);
        return panel;
    }
    private JButton createClearButton()
    {
        JButton button = new JButton("Очистить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });
        return button;
    }
    private JButton createCalcButton()
    {
        JButton button = new JButton("Вычислить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    double x = Double.parseDouble(textFieldX.getText());
                    double y = Double.parseDouble(textFieldY.getText());
                    double z = Double.parseDouble(textFieldZ.getText());
                    Double res;
                    if(formulaId==1)
                        res = formula1(x,y,z);
                    else
                        res = formula2(x,y,z);
                    textFieldResult.setText(res.toString());
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
                MainFrame.this.pack();
                MainFrame.this.revalidate();
            }
        });
        return button;
    }
    private JButton createMPlusButton()
    {
        JButton button = new JButton("M+");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    double res = Double.parseDouble(textFieldResult.getText());
                    if(memId==1)
                    {
                        mem1+=res;
                        res = mem1;
                        mem1Label.setText("П1 значение : " + mem1);
                    }
                    else
                    {
                        if (memId == 2)
                        {
                            mem2 += res;
                            res = mem2;
                            mem2Label.setText("П2 значение : " + mem2);
                        }
                        else
                        {
                            mem3 += res;
                            res = mem3;
                            mem3Label.setText("П3 значение : " + mem3);
                        }
                    }
                    textFieldResult.setText(Double.toString(res));
                }
                catch (NumberFormatException ex)
                {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой",
                            "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
                MainFrame.this.pack();
                MainFrame.this.revalidate();
            }

        });
        return button;
    }
    private JButton createMCButton()
    {
        JButton button = new JButton("MC");
        System.out.println(memId);
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (memId == 1)
                {
                    mem1 = 0.0;
                    mem1Label.setText("П1 значение : " + mem1.toString());
                }
                else
                {
                    if (memId == 2)
                    {
                        mem2 = 0.0;
                        mem2Label.setText("П2 значение : "+mem2.toString());
                    }
                    else
                    {
                        mem3 = 0.0;
                        mem3Label.setText("П3 значение : "+mem3.toString());
                    }
                }
                MainFrame.this.pack();
                MainFrame.this.revalidate();
            }
        });
        return button;
    }
    private JPanel createControlPanel()
    {
        Box memBox = Box.createVerticalBox();
        memBox.add(this.createMCButton());
        memBox.add(Box.createVerticalStrut(10));
        memBox.add(this.createMPlusButton());
        Box calcBox = Box.createVerticalBox();
        calcBox.add(this.createCalcButton());
        calcBox.add(Box.createVerticalStrut(10));
        calcBox.add(this.createClearButton());
        JPanel panel = new JPanel();
        panel.add(memBox);
        panel.add(calcBox);
        return panel;
    }
    private JRadioButton createMRadioButton(String buttonName,int memId)
    {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                MainFrame.this.memId = memId;
                MainFrame.this.pack();
                MainFrame.this.revalidate();
            }});
        return button;
    }
    private JPanel createMemoryShowPanel()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        mem1Label = new JLabel("П1 значение : "+mem1.toString());
        mem2Label = new JLabel("П2 значение : "+mem2.toString());
        mem3Label = new JLabel("П3 значение : "+mem3.toString());
        panel.add(mem1Label);
        panel.add(mem2Label);
        panel.add(mem3Label);
        return panel;
    }
    private JPanel createMemorySwitchPanel()
    {
        Box box = Box.createVerticalBox();
        JRadioButton mem11Select = createMRadioButton("Переменная 1",1);
        JRadioButton mem21Select = createMRadioButton("Переменная 2",2);
        JRadioButton mem31Select = createMRadioButton("Переменная 3",3);
        ButtonGroup memGroup = new ButtonGroup();
        memGroup.add(mem11Select);
        memGroup.add(mem21Select);
        memGroup.add(mem31Select);
        memGroup.setSelected(memGroup.getElements().nextElement().getModel(), true);
        box.add(mem11Select);
        box.add(mem21Select);
        box.add(mem31Select);
        JPanel panel = new JPanel();
        panel.add(box);
        return panel;
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
        JPanel panel = new JPanel();
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
        JPanel memSwitchPanel = this.createMemorySwitchPanel();
        JPanel memShowPanel = this.createMemoryShowPanel();
        JPanel controlPanel = this.createControlPanel();
        JPanel resultPanel = this.createResultPanel();
        panel.add(memSwitchPanel);
        panel.add(memShowPanel);
        panel.add(inputPanel);
        panel.add(controlPanel);
        box.add(formulaBox);
        box.add(panel);
        box.add(resultPanel);
        this.getContentPane().add(box);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }
}
