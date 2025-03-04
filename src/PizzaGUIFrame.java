import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;


public class PizzaGUIFrame extends JFrame
{

    JPanel mainPnl, pizzaPnl, receiptPnl, buttonPnl;
    JRadioButton thinCrust, regularCrust, deepDishCrust;
    JComboBox sizeCost;
    JCheckBox topping1, topping2, topping3, topping4, topping5, topping6;
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
        int frameW = screenW * 1/5;

        setSize(frameW, frameH);
        setLocation((screenW - frameW) / 2, (screenH - frameH) / 2);


    }

    public void createPizzaPnl()
    {
        pizzaPnl = new JPanel();
        pizzaPnl.setLayout(new GridLayout(3,1));

        // this is the sub panel where you select the type of pizza crust with the
        // JRadioButtons
        JPanel crustTypePnl = new JPanel();
        crustTypePnl.setBorder(BorderFactory.createTitledBorder("Choose The Crust Type"));
        ButtonGroup crustTypeGroup = new ButtonGroup();
        thinCrust = new JRadioButton("Thin Crust");
        regularCrust = new JRadioButton("Regular Crust");
        deepDishCrust = new JRadioButton("Deep-Dish Crust");

        crustTypeGroup.add(thinCrust);
        crustTypeGroup.add(regularCrust);
        crustTypeGroup.add(deepDishCrust);

        crustTypePnl.add(thinCrust);
        crustTypePnl.add(regularCrust);
        crustTypePnl.add(deepDishCrust);
        pizzaPnl.add(crustTypePnl);


        //This is the sub panel where you select the size of the pizza with the JComboBox
        // It also displays the price of each size.
        JPanel sizePnl = new JPanel();
        sizePnl.setBorder(BorderFactory.createTitledBorder("Choose The Size"));
        String[] sizes = {"Small - $8.00", "Medium - $12.00", "Large - $16.00", "Super - $20.00"};
        sizeCost = new JComboBox<>(sizes);
        sizePnl.add(sizeCost);
        pizzaPnl.add(sizePnl);



        // This is the sub panel where you select all the toppings you want
        // Each topping is an extra dollar and there are six to choose from.
        //created with a JCheckBox

        JPanel toppingsPnl = new JPanel();
        toppingsPnl.setBorder(BorderFactory.createTitledBorder("Choose The Toppings (Additional $1.00 Each)"));
        toppingsPnl.setLayout(new GridLayout(2,3));

        topping1 = new JCheckBox("Topping 1");
        topping2 = new JCheckBox("Topping 2");
        topping3 = new JCheckBox("Topping 3");
        topping4 = new JCheckBox("Topping 4");
        topping5 = new JCheckBox("Topping 5");
        topping6 = new JCheckBox("Topping 6");

        toppingsPnl.add(topping1);
        toppingsPnl.add(topping2);
        toppingsPnl.add(topping3);
        toppingsPnl.add(topping4);
        toppingsPnl.add(topping5);
        toppingsPnl.add(topping6);

        pizzaPnl.add(toppingsPnl);
        mainPnl.add(pizzaPnl, BorderLayout.NORTH);

    }

    public void createReceiptPnl()
    {

        receiptPnl = new JPanel();
        receiptPnl.setLayout(new BorderLayout());
        receiptPnl.setBorder(BorderFactory.createTitledBorder("Order Receipt"));

        orderArea = new JTextArea();
        orderArea.setEditable(false);
        orderArea.setLineWrap(true);
        orderArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(orderArea);

        receiptPnl.add(scrollPane, BorderLayout.CENTER);
        mainPnl.add(receiptPnl, BorderLayout.CENTER);

    }

    public void createButtonPnl()
    {

        buttonPnl = new JPanel();
        buttonPnl.setLayout(new FlowLayout());

        orderBtn = new JButton("Order");
        orderBtn.addActionListener(e -> printReceipt());
        buttonPnl.add(orderBtn);

        clearBtn = new JButton("Clear Order");
        clearBtn.addActionListener(e -> orderArea.setText(""));
        buttonPnl.add(clearBtn);

        quitBtn = new JButton("Quit");
        quitBtn.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to quit?",
                    "Confirm", JOptionPane.YES_NO_OPTION);
            if(confirm == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        });

        buttonPnl.add(quitBtn);
        mainPnl.add(buttonPnl, BorderLayout.SOUTH);


    }

    private void printReceipt()
    {
        //this is where the crust type is found and stored as the string variable crustType, it is later
        //used to post it in the receipt . instanceof JRadioButton is what discerns which crust is selected
        String crustType = "";
        Component[] crustComponents = ((JPanel) pizzaPnl.getComponent(0)).getComponents();
        for (Component component : crustComponents) {
            if (component instanceof JRadioButton radioButton && radioButton.isSelected()){
                crustType = radioButton.getText();
                break;
            }
        }

        //This is where the selected size of the pizza is called and turned into a double that decides price as well as stores
        //the size string to be used on the receipt with the sizeDetails.split function

        String sizeDetails = (String) sizeCost.getSelectedItem();
        double sizePrice = 0;
        String size = "";
        if(sizeDetails != null){
            String[] sizeParts = sizeDetails.split(" - \\$");
            size = sizeParts[0];
            sizePrice = Double.parseDouble(sizeParts[1]);
        }

        //This is where the toppings are found and the int toppingCount is established to later find how much money
        //in topings there is. the instanceof JCheckbox is used to find which toppings are currently selected.


        Component[] toppingComponents = ((JPanel) pizzaPnl.getComponent(2)).getComponents();
        StringBuilder toppingsList = new StringBuilder();
        int toppingCount = 0;
        for (Component component : toppingComponents) {
            if(component instanceof JCheckBox checkBox && checkBox.isSelected()){
                toppingsList.append(checkBox.getText()).append("\n");
                toppingCount++;
            }
        }
        double toppingsPrice = toppingCount;

        double subtotal = sizePrice + toppingsPrice;
        double tax = subtotal * 0.07;
        double total = subtotal + tax;
        DecimalFormat df = new DecimalFormat("0.00");
        String formattedTax = df.format(tax);
        String formattedSubtotal = df.format(subtotal);
        String formattedToppings = df.format(toppingsPrice);
        String formattedSize = df.format(sizePrice);
        String formattedTotal = df.format(total);


        //there may be an easier way to do this but I got the format to work this way.

        StringBuilder receipt = new StringBuilder();
        receipt.append("==============================\n");
        receipt.append(size + ", " + crustType + "\n");
        receipt.append("                                                        $" + df.format(sizePrice) + "\n");
        receipt.append(toppingsList.toString());
        receipt.append("                                                        $" + df.format(toppingsPrice) + "\n");
        receipt.append("\n");
        receipt.append("\n");
        receipt.append("Subtotal\n");
        receipt.append("                                                        $" + df.format(subtotal) + "\n");
        receipt.append("Tax\n");
        receipt.append("                                                        $" + df.format(tax) + "\n");
        receipt.append("\n");
        receipt.append("----------------------------------------------------");
        receipt.append("\n");
        receipt.append("Total\n");
        receipt.append("                                                        $" + df.format(total) + "\n");
        receipt.append("\n");
        receipt.append("==============================\n");



        orderArea.setText(receipt.toString());



    }
}
