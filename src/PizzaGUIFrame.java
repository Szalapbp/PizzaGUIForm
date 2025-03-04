import javax.swing.*;
import java.awt.*;

public class PizzaGUIFrame extends JFrame
{

    JPanel mainPnl, pizzaPnl, recieptPnl, buttonPnl;
    JRadioButton crustType;
    JComboBox sizeCost;
    JCheckBox toppings;
    JTextArea orderArea;
    JButton orderBtn, clearBtn, quitBtn;

    public PizzaGUIFrame()
    {
        mainPnl = new JPanel();
        mainPnl.setLayout(new BorderLayout());
        add(mainPnl);
        createPizzaPnl();
        createReceiptPnl();
        createButtonPnl();

        setTitle("Pizza Order Form");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int screenH = screenSize.height;
        int screenW = screenSize.width;
        int frameH = screenH * 3/4;
        int frameW = screenW * 3/4;

        setSize(frameW, frameH);
        setLocation((screenW - frameW) / 2, (screenH - frameH) / 2);


    }

    public void createPizzaPnl()
    {

    }

    public void createReceiptPnl()
    {

    }

    public void createButtonPnl()
    {

    }

    private void printReceipt()
    {

    }
}
