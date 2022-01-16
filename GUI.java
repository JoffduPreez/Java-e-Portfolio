package jdupreez_a3.ePortfolio;

import java.io.*;
import java.util.*;

import javax.sound.sampled.Port;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
  JPanel initialPanel;
  JMenuBar menuBar;
  JMenu commandMenu;
  JComboBox typeBox = new JComboBox();

  JPanel topBuy;
  JTextArea messageBuy = new JTextArea(15, 30);
  JPanel buyButtonPanel;
  JPanel buyPanel;
  JPanel buyDataFields;
  JTextField buySymbolField = new JTextField();
  JTextField buyNameField = new JTextField();
  JTextField buyQuantityField = new JTextField();
  JTextField buyPriceField = new JTextField();
  JLabel buySymbolLabel;
  JLabel buyNameLabel;
  JLabel buyQuantityLabel;
  JLabel buyPriceLabel;
  JButton buyResetButton;
  JButton buyButton;
  JLabel buyTitle;
  JLabel buySpaceFiller;

  JPanel topSell;
  JTextArea messageSell = new JTextArea(15, 30);
  JPanel sellButtonPanel;
  JPanel sellPanel;
  JPanel sellDataFields;
  JTextField sellSymbolField = new JTextField();
  JTextField sellQuantityField = new JTextField();
  JTextField sellPriceField = new JTextField();
  JLabel sellSymbolLabel;
  JLabel sellQuantityLabel;
  JLabel sellPriceLabel;
  JButton sellResetButton;
  JButton sellButton;
  JLabel sellTitle;
  JLabel sellSpaceFiller;

  JPanel topUpdate;
  JTextArea messageUpdate = new JTextArea(15, 30);
  JPanel updateButtonPanel;
  JPanel updatePanel;
  JPanel updateDataFields;
  JTextField updateSymbolField = new JTextField();
  JTextField updateNameField = new JTextField();
  JTextField updatePriceField = new JTextField();
  JLabel updateSymbolLabel;
  JLabel updateNameLabel;
  JLabel updatePriceLabel;
  JButton nextButton;
  JButton prevButton;
  JButton saveButton;
  JLabel updateTitle;
  JLabel updateSpaceFiller;
  int count = 0;

  JPanel topGain;
  JTextArea messageGain = new JTextArea(15, 30);
  JPanel gainButtonPanel;
  JPanel gainPanel;
  JPanel gainDataFields;
  JTextField gainNameField = new JTextField();
  JLabel gainNameLabel;
  JLabel gainTitle;
  JLabel gainSpaceFiller;
  JLabel gainSpaceFiller2;
  JLabel gainSpaceFiller3;

  JPanel topSearch;
  JTextArea messageSearch = new JTextArea(15, 30);
  JPanel searchButtonPanel;
  JPanel searchPanel;
  JPanel searchDataFields;
  JTextField searchSymbolField = new JTextField();
  JTextField searchNameField = new JTextField();
  JTextField searchLowPriceField = new JTextField();
  JTextField searchHighPriceField = new JTextField();
  JLabel searchSymbolLabel;
  JLabel searchNameLabel;
  JLabel searchLowPriceLabel;
  JLabel searchHighPriceLabel;
  JButton searchResetButton;
  JButton searchButton;
  JLabel searchTitle;
  JLabel searchSpaceFiller;

  JPanel topPrint;
  JTextArea messagePrint = new JTextArea(15, 30);
  JPanel printButtonPanel;
  JPanel printPanel;
  JPanel printDataFields;
  JButton printResetButton;
  JButton printButton;
  JLabel printTitle;
  JLabel printSpaceFiller;
  JLabel printSpaceFiller2;
  JLabel printSpaceFiller3;

  public class pannelChanger implements ActionListener {
    public ArrayList<Investment> investmentList = new ArrayList<Investment>();

    public pannelChanger(Investment investments) {
      investmentList = investments.investmentList;
    }

    public void actionPerformed(ActionEvent e) {
      String commandOption = e.getActionCommand();
      switch (commandOption) {
        case "Buy":
          sellPanel.setVisible(false);
          searchPanel.setVisible(false);
          gainPanel.setVisible(false);
          updatePanel.setVisible(false);
          initialPanel.setVisible(false);
          printPanel.setVisible(false);
          buyPanel.setVisible(true);
          break;
        case "Sell":
          buyPanel.setVisible(false);
          searchPanel.setVisible(false);
          gainPanel.setVisible(false);
          updatePanel.setVisible(false);
          initialPanel.setVisible(false);
          printPanel.setVisible(false);
          sellPanel.setVisible(true);
          break;
        case "Update":
          buyPanel.setVisible(false);
          searchPanel.setVisible(false);
          gainPanel.setVisible(false);
          gainPanel.setVisible(false);
          initialPanel.setVisible(false);
          sellPanel.setVisible(false);
          printPanel.setVisible(false);
          if (investmentList.size() > 0) {
            updateSymbolField.setText(investmentList.get(0).getSymbol());
          }
          if (investmentList.size() > 0) {
            updateNameField.setText(investmentList.get(0).getName());
          }
          if (investmentList.size() == 0) {
            prevButton.setEnabled(false);
            nextButton.setEnabled(false);
          }
          System.out.println("update");
          updatePanel.setVisible(true);
          break;
        case "getGain":
          buyPanel.setVisible(false);
          searchPanel.setVisible(false);
          initialPanel.setVisible(false);
          sellPanel.setVisible(false);
          updatePanel.setVisible(false);
          printPanel.setVisible(false);
          gainPanel.setVisible(true);
          break;
        case "Search":
          buyPanel.setVisible(false);
          initialPanel.setVisible(false);
          sellPanel.setVisible(false);
          updatePanel.setVisible(false);
          gainPanel.setVisible(false);
          printPanel.setVisible(false);
          searchPanel.setVisible(true);
          break;
        case "Quit":
          System.exit(0);
          break;
        case "Print":
          buyPanel.setVisible(false);
          searchPanel.setVisible(false);
          initialPanel.setVisible(false);
          sellPanel.setVisible(false);
          updatePanel.setVisible(false);
          gainPanel.setVisible(false);
          printPanel.setVisible(true);
          break;
      }
    }
  }

  public GUI(Investment investments) {
    super("ePortfolio");

    initialPanel = new JPanel();

    topBuy = new JPanel();
    buyButtonPanel = new JPanel();
    buyPanel = new JPanel();
    buyDataFields = new JPanel();

    topSell = new JPanel();
    sellButtonPanel = new JPanel();
    sellPanel = new JPanel();
    sellDataFields = new JPanel();

    topUpdate = new JPanel();
    updateButtonPanel = new JPanel();
    updatePanel = new JPanel();
    updateDataFields = new JPanel();

    topGain = new JPanel();
    gainButtonPanel = new JPanel();
    gainPanel = new JPanel();
    gainDataFields = new JPanel();

    topSearch = new JPanel();
    searchButtonPanel = new JPanel();
    searchPanel = new JPanel();
    searchDataFields = new JPanel();

    topPrint = new JPanel();
    printButtonPanel = new JPanel();
    printPanel = new JPanel();
    printDataFields = new JPanel();

    // Set up the initial screen
    setSize(1000, 600);
    setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    commandMenu = new JMenu("Command");
    JMenuItem buy = new JMenuItem("Buy");
    buy.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sellPanel.setVisible(false);
        searchPanel.setVisible(false);
        gainPanel.setVisible(false);
        updatePanel.setVisible(false);
        initialPanel.setVisible(false);
        printPanel.setVisible(false);
        buyPanel.setVisible(true);
      }
    });
    commandMenu.add(buy);
    JMenuItem sell = new JMenuItem("Sell");
    sell.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buyPanel.setVisible(false);
        searchPanel.setVisible(false);
        gainPanel.setVisible(false);
        updatePanel.setVisible(false);
        initialPanel.setVisible(false);
        printPanel.setVisible(false);
        sellPanel.setVisible(true);
      }
    });
    commandMenu.add(sell);
    JMenuItem update = new JMenuItem("Update");
    update.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buyPanel.setVisible(false);
        searchPanel.setVisible(false);
        gainPanel.setVisible(false);
        gainPanel.setVisible(false);
        initialPanel.setVisible(false);
        sellPanel.setVisible(false);
        printPanel.setVisible(false);
        if (investments.investmentList.size() > 0) {
          updateSymbolField.setText(investments.investmentList.get(0).getSymbol());
          updateNameField.setText(investments.investmentList.get(0).getName());
          count = 0;
          prevButton.setEnabled(false);

          if (count != investments.investmentList.size() - 1) {
            nextButton.setEnabled(true);
          }
        }
        if (investments.investmentList.size() == 0) {
          prevButton.setEnabled(false);
          nextButton.setEnabled(false);
        } else {
          nextButton.setEnabled(true);
        }
        updatePanel.setVisible(true);
      }
    });
    commandMenu.add(update);
    JMenuItem getGain = new JMenuItem("getGain");
    getGain.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buyPanel.setVisible(false);
        searchPanel.setVisible(false);
        initialPanel.setVisible(false);
        sellPanel.setVisible(false);
        updatePanel.setVisible(false);
        printPanel.setVisible(false);
        gainPanel.setVisible(true);

        double totalSaleValue;
        double totalBookValue;
        double individalSaleValue;
        double individalBookValue;
        double totalGain;
        double individalGain;

        totalSaleValue = 0;
        totalBookValue = 0;

        // these two loops calculate the total sale value and total book value
        for (int i = 0; i < investments.investmentList.size(); i++) {
          if (investments.investmentList.get(i) instanceof Stock) {
            totalSaleValue += investments.investmentList.get(i).getQuantity()
                * investments.investmentList.get(i).getPrice() - 9.99;
            individalSaleValue = investments.investmentList.get(i).getQuantity()
                * investments.investmentList.get(i).getPrice() - 9.99;
          } else {
            totalSaleValue += investments.investmentList.get(i).getQuantity()
                * investments.investmentList.get(i).getPrice() - 45;
            individalSaleValue = investments.investmentList.get(i).getQuantity()
                * investments.investmentList.get(i).getPrice() - 9.99;
          }
          totalBookValue += investments.investmentList.get(i).getBookValue();
          individalBookValue = investments.investmentList.get(i).getBookValue();
          individalGain = individalSaleValue - individalBookValue;
          messageGain.setText(messageGain.getText() + "Gain for the " + investments.investmentList.get(i).getSymbol()
              + " investment is " + individalGain + "\n");
        }

        // this uses the total sale value and total book value to calculate the total
        // gain
        totalGain = totalSaleValue - totalBookValue;
        gainNameField.setText("" + totalGain);
      }
    });
    commandMenu.add(getGain);
    JMenuItem search = new JMenuItem("Search");
    search.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buyPanel.setVisible(false);
        initialPanel.setVisible(false);
        sellPanel.setVisible(false);
        updatePanel.setVisible(false);
        gainPanel.setVisible(false);
        printPanel.setVisible(false);
        searchPanel.setVisible(true);
      }
    });
    commandMenu.add(search);
    JMenuItem quit = new JMenuItem("Quit");
    quit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    commandMenu.add(quit);
    JMenuItem print = new JMenuItem("Print");
    print.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buyPanel.setVisible(false);
        searchPanel.setVisible(false);
        initialPanel.setVisible(false);
        sellPanel.setVisible(false);
        updatePanel.setVisible(false);
        gainPanel.setVisible(false);
        printPanel.setVisible(true);
      }
    });
    commandMenu.add(print);

    menuBar = new JMenuBar();
    menuBar.add(commandMenu);
    setJMenuBar(menuBar);

    String message = "<html>Welcome to ePortfolio.<br/><br/>Choose a command from the “Commands” menu to buy or sell<br/>an investment, update prices for all investments, get gain for the<br/>portfolio, search for relevant investments, or quit the program.<html>";
    JLabel startMsg = new JLabel(message, JLabel.CENTER);
    startMsg.setAlignmentX(0);
    startMsg.setAlignmentY(0);
    initialPanel.setLayout(new BorderLayout());
    initialPanel.add(startMsg);
    initialPanel.setVisible(true);
    add(initialPanel);
    setVisible(true);
  }

  public void setOptionPanels(Investment investments, GUI ePortfolio) {
    // Buy Panel
    buyPanel.setLayout(new GridLayout(2, 0));
    topBuy.setLayout(new GridLayout(0, 2));
    buyDataFields.setLayout(new GridLayout(6, 2));
    buyButtonPanel.setLayout(new GridLayout(2, 0));
    buyTitle = new JLabel("<html>Buying an investment:<br><html>");
    buySpaceFiller = new JLabel("");
    buyDataFields.add(buyTitle);
    buyDataFields.add(buySpaceFiller);
    JLabel typeLabel = new JLabel("Type: ");
    typeBox.addItem("Stock");
    typeBox.addItem("Mutual Fund");
    buyDataFields.add(typeLabel);
    buyDataFields.add(typeBox);
    buySymbolLabel = new JLabel("<html><br>Symbol: <html>");
    buyDataFields.add(buySymbolLabel);
    buyDataFields.add(buySymbolField);
    buyNameLabel = new JLabel("<html><br>Name:<br><html>");
    buyDataFields.add(buyNameLabel);
    buyDataFields.add(buyNameField);
    buyQuantityLabel = new JLabel("<html><br>Quantity:<br><html>");
    buyDataFields.add(buyQuantityLabel);
    buyDataFields.add(buyQuantityField);
    buyPriceLabel = new JLabel("<html><br>Price: <html>");
    buyDataFields.add(buyPriceLabel);
    buyDataFields.add(buyPriceField);
    topBuy.add(buyDataFields);
    buyPanel.add(topBuy);
    buyResetButton = new JButton("Reset");
    buyResetButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        buySymbolField.setText("");
        buyNameField.setText("");
        buyQuantityField.setText("");
        buyPriceField.setText("");
      }
    });
    buyButton = new JButton("Buy");
    buyButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String stockType = typeBox.getSelectedItem().toString();
        String investmentSymbol = buySymbolField.getText();
        if (investmentSymbol.compareTo("") == 0) {
          messageBuy.setText("Cannot leave the symbol field blank");
        } else {
          System.out.println(stockType);
          System.out.println(investmentSymbol);
          if (stockType.compareToIgnoreCase("Stock") == 0) {
            Stock.Buy(investments, investmentSymbol, ePortfolio);
          } else if (stockType.compareToIgnoreCase("Mutual Fund") == 0) {
            MutualFund.Buy(investments, investmentSymbol, ePortfolio);
          }
        }
      }
    });
    buyButtonPanel.add(buyButton);
    buyButtonPanel.add(buyResetButton);
    topBuy.add(buyButtonPanel);
    JScrollPane scrollBuy = new JScrollPane(messageBuy);
    scrollBuy.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollBuy.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    buyPanel.add(messageBuy);
    buyButtonPanel.setVisible(true);
    buyDataFields.setVisible(true);
    topBuy.setVisible(true);
    buyPanel.setVisible(false);
    add(buyPanel);

    // Sell panel
    sellPanel.setLayout(new GridLayout(2, 0));
    topSell.setLayout(new GridLayout(0, 2));
    sellDataFields.setLayout(new GridLayout(4, 2));
    sellButtonPanel.setLayout(new GridLayout(2, 0));
    sellTitle = new JLabel("<html>Selling an investment:<br><html>");
    sellSpaceFiller = new JLabel("");
    sellDataFields.add(sellTitle);
    sellDataFields.add(sellSpaceFiller);
    sellSymbolLabel = new JLabel("<html><br>Symbol: <html>");
    sellDataFields.add(sellSymbolLabel);
    sellDataFields.add(sellSymbolField);
    sellQuantityLabel = new JLabel("<html><br>Quantity:<br><html>");
    sellDataFields.add(sellQuantityLabel);
    sellDataFields.add(sellQuantityField);
    sellPriceLabel = new JLabel("<html><br>Price: <html>");
    sellDataFields.add(sellPriceLabel);
    sellDataFields.add(sellPriceField);
    topSell.add(sellDataFields);
    sellPanel.add(topSell);
    sellButton = new JButton("Sell");
    sellButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        String investmentSymbol = sellSymbolField.getText();
        int investmentType, quantity, symbolExists;
        double price;
        investmentType = symbolExists = 0;

        if (investmentSymbol.compareTo("") == 0) {
          messageBuy.setText("Cannot leave the symbol field blank");
        } else {
          for (int i = 0; i < investments.investmentList.size(); i++) {
            if (investments.investmentList.get(i).getSymbol().compareTo(investmentSymbol) == 0) {
              symbolExists = 1;
              if (investments.investmentList.get(i) instanceof Stock) {
                investmentType = 1;
              } else {
                investmentType = 2;
              }
            }
          }
        }
        // this checks if the symbol the user wants to sell exists, and it sets the
        // investment type in order to call the correct method to sell

        // if the symbol that the user wants to sell exists, then the inputs are
        // collected and the specific type of investment is sold
        if (symbolExists == 1) {
          if (sellPriceField.getText().compareTo("") == 0) {
            messageSell.setText("Cannot leave the price field blank");
            return;
          }
          price = Double.parseDouble(sellPriceField.getText());
          if (price < 0) {
            messageSell.setText("Please enter a positive number for the price");
            return;
          }
          if (sellQuantityField.getText().compareTo("") == 0) {
            messageSell.setText("Cannot leave the quantity field blank");
            return;
          }
          quantity = Integer.parseInt(sellQuantityField.getText());
          if (quantity < 0) {
            messageSell.setText("Please enter a positive integer for the quantity");
            return;
          }

          if (investmentType == 1) {
            Stock.Sell(investments, investmentSymbol, quantity, price, ePortfolio);
          } else if (investmentType == 2) {
            MutualFund.Sell(investments, investmentSymbol, quantity, price, ePortfolio);
          }
        } else {
          messageSell.setText("Sorry, no stock with that symbol exists");
        }
      }
    });

    sellButtonPanel.add(sellButton);
    sellResetButton = new JButton("Reset");
    sellButtonPanel.add(sellResetButton);
    sellResetButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        sellSymbolField.setText("");
        sellQuantityField.setText("");
        sellPriceField.setText("");
      }
    });
    topSell.add(sellButtonPanel);
    JScrollPane scrollSell = new JScrollPane(messageSell);
    scrollSell.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollSell.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    sellPanel.add(messageSell);
    sellButtonPanel.setVisible(true);
    sellDataFields.setVisible(true);
    topSell.setVisible(true);
    sellPanel.setVisible(false);
    add(sellPanel);

    // Update panel
    updatePanel.setLayout(new GridLayout(2, 0));
    topUpdate.setLayout(new GridLayout(0, 2));
    updateDataFields.setLayout(new GridLayout(4, 2));
    updateButtonPanel.setLayout(new GridLayout(3, 0));
    updateTitle = new JLabel("<html>Updating investments:<br><html>");
    updateSpaceFiller = new JLabel("");
    updateDataFields.add(updateTitle);
    updateDataFields.add(updateSpaceFiller);
    updateSymbolLabel = new JLabel("<html><br>Symbol: <html>");
    updateDataFields.add(updateSymbolLabel);
    updateSymbolField.setEditable(false);
    updateDataFields.add(updateSymbolField);
    if (investments.investmentList.size() > 0) {
      updateSymbolField.setText(investments.investmentList.get(0).getSymbol());
    }

    updateNameLabel = new JLabel("<html><br>Name:<br><html>");
    updateDataFields.add(updateNameLabel);
    updateNameField.setEditable(false);
    updateDataFields.add(updateNameField);
    if (investments.investmentList.size() > 0) {
      updateNameField.setText(investments.investmentList.get(0).getName());
    }

    updatePriceLabel = new JLabel("<html><br>Price: <html>");
    updateDataFields.add(updatePriceLabel);
    updateDataFields.add(updatePriceField);
    topUpdate.add(updateDataFields);
    updatePanel.add(topUpdate);
    prevButton = new JButton("Prev");
    prevButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        nextButton.setEnabled(true);
        updatePriceField.setText("");
        if (count != 0) {
          prevButton.setEnabled(true);
          count--;
          updateSymbolField.setText(investments.investmentList.get(count).getSymbol());
          updateNameField.setText(investments.investmentList.get(count).getName());
        }

        if (count == 0) {
          prevButton.setEnabled(false);
        }
      }
    });
    nextButton = new JButton("Next");
    nextButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        prevButton.setEnabled(true);
        updatePriceField.setText("");

        if (count != investments.investmentList.size() - 1) {
          nextButton.setEnabled(true);
          count++;
          updateSymbolField.setText(investments.investmentList.get(count).getSymbol());
          updateNameField.setText(investments.investmentList.get(count).getName());
        }

        if (count == investments.investmentList.size() - 1) {
          nextButton.setEnabled(false);
        }
      }
    });
    saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        double price;
        if (updatePriceField.getText().compareTo("") == 0) {
          messageUpdate.setText("Cannot leave price field blank");
          return;
        }
        if (Double.parseDouble(updatePriceField.getText()) < 0) {
          messageUpdate.setText("Please enter a positive number for the price");
          return;
        }
        price = Double.parseDouble(updatePriceField.getText());
        investments.investmentList.get(count).setPrice(price);

        messageUpdate.setText(investments.investmentList.get(count).toString() + "\n");
      }
    });
    updateButtonPanel.add(prevButton);
    updateButtonPanel.add(nextButton);
    updateButtonPanel.add(saveButton);
    topUpdate.add(updateButtonPanel);
    JScrollPane scrollUpdate = new JScrollPane(messageUpdate);
    scrollUpdate.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollUpdate.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    updatePanel.add(messageUpdate);
    updateButtonPanel.setVisible(true);
    updateDataFields.setVisible(true);
    topUpdate.setVisible(true);
    updatePanel.setVisible(false);
    add(updatePanel);

    // Gain panel
    gainPanel.setLayout(new GridLayout(2, 0));
    topGain.setLayout(new GridLayout(0, 2));
    gainDataFields.setLayout(new GridLayout(2, 2));
    gainButtonPanel.setLayout(new GridLayout(2, 0));
    gainTitle = new JLabel("<html>Getting total gain:<br><html>");
    gainSpaceFiller = new JLabel("");
    gainDataFields.add(gainTitle);
    gainDataFields.add(gainSpaceFiller);
    gainNameLabel = new JLabel("<html><br>Total Gain:<br><html>");
    gainDataFields.add(gainNameLabel);
    gainDataFields.add(gainNameField);
    gainNameField.setEnabled(false);
    topGain.add(gainDataFields);
    gainPanel.add(topGain);
    gainSpaceFiller2 = new JLabel("");
    gainSpaceFiller3 = new JLabel("");
    gainButtonPanel.add(gainSpaceFiller2);
    gainButtonPanel.add(gainSpaceFiller3);
    topGain.add(gainButtonPanel);
    JScrollPane scrollGain = new JScrollPane(messageGain);
    scrollGain.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollGain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    gainPanel.add(messageGain);
    gainButtonPanel.setVisible(true);
    gainDataFields.setVisible(true);
    topGain.setVisible(true);
    gainPanel.setVisible(false);
    add(gainPanel);

    // Search panel
    searchPanel.setLayout(new GridLayout(2, 0));
    topSearch.setLayout(new GridLayout(0, 2));
    searchDataFields.setLayout(new GridLayout(5, 2));
    searchButtonPanel.setLayout(new GridLayout(2, 0));
    searchTitle = new JLabel("<html>Searching investments:<br><html>");
    searchSpaceFiller = new JLabel("");
    searchDataFields.add(searchTitle);
    searchDataFields.add(searchSpaceFiller);
    searchSymbolLabel = new JLabel("<html><br>Symbol: <html>");
    searchDataFields.add(searchSymbolLabel);
    searchDataFields.add(searchSymbolField);
    searchNameLabel = new JLabel("<html><br>Name keywords: <html>");
    searchDataFields.add(searchNameLabel);
    searchDataFields.add(searchNameField);
    searchHighPriceLabel = new JLabel("<html><br>High price: <html>");
    searchDataFields.add(searchHighPriceLabel);
    searchDataFields.add(searchHighPriceField);
    searchLowPriceLabel = new JLabel("<html><br>Low price: <html>");
    searchDataFields.add(searchLowPriceLabel);
    searchDataFields.add(searchLowPriceField);
    topSearch.add(searchDataFields);
    searchPanel.add(topSearch);
    searchResetButton = new JButton("Reset");
    searchResetButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        searchSymbolField.setText("");
        searchNameField.setText("");
        searchHighPriceField.setText("");
        searchLowPriceField.setText("");
      }
    });
    searchButtonPanel.add(searchResetButton);
    searchButton = new JButton("Search");
    searchButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        // this function makes a duplicate list that is equal to the original lists, and
        // removes stuff from the duplicate list that does not match the search criteria
        ArrayList<Investment> filteredInvestmentList = new ArrayList<Investment>();
        String searchSymbol, searchName;
        double lowPriceRange, highPriceRange;
        searchSymbol = searchName = "";
        lowPriceRange = highPriceRange = -1;

        searchSymbol = searchSymbolField.getText();
        searchName = searchNameField.getText();

        if (Double.parseDouble(searchLowPriceField.getText()) < 0) {
          messageSearch.setText("Price must be higher than zero");
          return;
        }
        if (Double.parseDouble(searchHighPriceField.getText()) < 0) {
          messageSearch.setText("Price must be higher than zero");
          return;
        }
        if (Double.parseDouble(searchLowPriceField.getText()) > Double.parseDouble(searchHighPriceField.getText())) {
          messageSearch.setText("Low price cannot be higher then high price");
          return;
        }

        if (searchLowPriceField.getText().compareTo("") != 0) {
          lowPriceRange = Double.parseDouble(searchLowPriceField.getText());
        }
        if (searchHighPriceField.getText().compareTo("") != 0) {
          highPriceRange = Double.parseDouble(searchHighPriceField.getText());
        }

        StringTokenizer nameWords = new StringTokenizer(searchName, " ");

        String keyName, currentNameWord;
        boolean keyFound = false;
        ArrayList<Integer> previousList = new ArrayList<Integer>();
        ArrayList<Integer> currentList = new ArrayList<Integer>();
        ArrayList<Integer> nameIntersections = new ArrayList<Integer>();
        int count = 0;
        keyName = "";

        while (nameWords.hasMoreTokens()) { // for each word in the name
          currentNameWord = nameWords.nextToken();

          for (Map.Entry<String, ArrayList<Integer>> key : investments.nameIndexes.entrySet()) {
            if (currentNameWord.compareToIgnoreCase(key.getKey()) == 0) {
              keyFound = true;
              keyName = key.getKey();
            }
          }

          // if there is only 1 word
          if (count == 0 && nameWords.countTokens() == 0) {
            currentList = investments.nameIndexes.get(keyName);
          }

          // if a key was found, save its list of indexes to the currentList, and set
          // nameIntersections to the intersection of the current list and previous list
          previousList = currentList;
          if (keyFound == true) {
            currentList = investments.nameIndexes.get(keyName);

            for (int i = 0; i < currentList.size(); i++) {
              for (int k = 0; k < previousList.size(); k++) {
                if (currentList.get(i) == previousList.get(k)) {
                  nameIntersections.add(previousList.get(k));
                }
              }
            }
          }
          count++;
        }

        // set the filtered list equal to the intersections of the names
        if (nameIntersections.size() != 0) {
          for (int i = 0; i < nameIntersections.size(); i++) {
            filteredInvestmentList.add(investments.investmentList.get(nameIntersections.get(i)));
          }
        } else {
          for (int i = 0; i < investments.investmentList.size(); i++) {
            filteredInvestmentList.add(investments.investmentList.get(i));
          }
        }

        // remove investments from the filtered list that do not match the symbol
        for (int i = filteredInvestmentList.size() - 1; i >= 0; i--) {
          if (searchSymbol.compareToIgnoreCase("") != 0
              && searchSymbol.compareToIgnoreCase(filteredInvestmentList.get(i).getSymbol()) != 0) {
            filteredInvestmentList.remove(i);
          }
        }

        // remove investments from the filtered list that do not match the price range
        if (lowPriceRange != -1 || highPriceRange != -1 && lowPriceRange != highPriceRange) {
          for (int i = 0; i < filteredInvestmentList.size(); i++) {
            if (lowPriceRange == -1 && highPriceRange != -1) {
              if (filteredInvestmentList.get(i).getPrice() > highPriceRange) {
                filteredInvestmentList.remove(i);
              }
            } else if (lowPriceRange != -1 && highPriceRange == -1) {
              if (filteredInvestmentList.get(i).getPrice() < lowPriceRange) {
                filteredInvestmentList.remove(i);
              }
            } else {
              if (filteredInvestmentList.get(i).getPrice() < lowPriceRange ||
                  filteredInvestmentList.get(i)
                      .getPrice() > highPriceRange) {
                filteredInvestmentList.remove(i);
              }
            }
          }
        } else if (lowPriceRange == highPriceRange && (lowPriceRange != -1 && highPriceRange != -1)) {
          int[] indexesToRemove = new int[filteredInvestmentList.size()];
          for (int i = 0; i < filteredInvestmentList.size(); i++) {
            if (filteredInvestmentList.get(i).getPrice() != highPriceRange && filteredInvestmentList.get(i)
                .getPrice() != lowPriceRange) {
              indexesToRemove[i] = 1;
            } else {
              indexesToRemove[i] = 0;
            }
          }

          for (int i = indexesToRemove.length - 1; i >= 0; i--) {
            if (indexesToRemove[i] == 1) {
              filteredInvestmentList.remove(i);
            }
          }
        }

        if (filteredInvestmentList.size() != 0) {
          messageSearch.setText("The investments that match the search criteria are: \n");
          for (int i = 0; i < filteredInvestmentList.size(); i++) {
            messageSearch
                .setText(messageSearch.getText() + "Symbol: " + filteredInvestmentList.get(i).getSymbol() + "\n");
            messageSearch.setText(messageSearch.getText() + "Name: " + filteredInvestmentList.get(i).getName() + "\n");
            messageSearch
                .setText(messageSearch.getText() + "Quantity: " + filteredInvestmentList.get(i).getQuantity() + "\n");
            messageSearch
                .setText(messageSearch.getText() + "Price: " + filteredInvestmentList.get(i).getPrice() + "\n");
            messageSearch.setText(
                messageSearch.getText() + "BookValue: " + filteredInvestmentList.get(i).getBookValue() + "\n\n");
          }
        } else {
          messageSearch.setText("Sorry, no investments match that search criteria");
        }
      }
    });
    searchButtonPanel.add(searchButton);
    topSearch.add(searchButtonPanel);
    JScrollPane scrollSearch = new JScrollPane(messageSearch);
    scrollSearch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollSearch.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    searchPanel.add(messageSearch);
    searchButtonPanel.setVisible(true);
    searchDataFields.setVisible(true);
    topSearch.setVisible(true);
    searchPanel.setVisible(false);
    add(searchPanel);

    // Gain panel
    printPanel.setLayout(new GridLayout(2, 0));
    topPrint.setLayout(new GridLayout(0, 2));
    printDataFields.setLayout(new GridLayout(2, 2));
    printButtonPanel.setLayout(new GridLayout(2, 0));
    printTitle = new JLabel("<html>Printing the list:<br><html>");
    printSpaceFiller = new JLabel("");
    printSpaceFiller2 = new JLabel("");
    printSpaceFiller3 = new JLabel("");
    printDataFields.add(printTitle);
    printDataFields.add(printSpaceFiller);
    printDataFields.add(printSpaceFiller2);
    printDataFields.add(printSpaceFiller3);
    topPrint.add(printDataFields);
    printPanel.add(topPrint);
    printButton = new JButton("Press to print");
    printButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        Portfolio.printList(investments, ePortfolio);
      }
    });
    printResetButton = new JButton("Press to clear text");
    printResetButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        messagePrint.setText("");
      }
    });
    printButtonPanel.add(printButton);
    printButtonPanel.add(printResetButton);
    topPrint.add(printButtonPanel);
    JScrollPane scrollPrint = new JScrollPane(messagePrint);
    scrollPrint.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPrint.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    printPanel.add(messagePrint);
    printButtonPanel.setVisible(true);
    printDataFields.setVisible(true);
    topPrint.setVisible(true);
    printPanel.setVisible(false);
    add(printPanel);

    // Set message boxes
    messageBuy.setText("Messages");
    messageBuy.setEditable(false);
    messageBuy.setLineWrap(true);
    messageSell.setText("Messages");
    messageSell.setEditable(false);
    messageSell.setLineWrap(true);
    messageUpdate.setText("Messages");
    messageUpdate.setEditable(false);
    messageUpdate.setLineWrap(true);
    messageGain.setText("");
    messageGain.setEditable(false);
    messageGain.setLineWrap(true);
    messageSearch.setText("Search Results");
    messageSearch.setEditable(false);
    messageSearch.setLineWrap(true);
  }

  /** This tests if the object equals another object */
  public boolean equals(Object investment) {
    if (this.equals(investment)) {
      return true;
    } else {
      return false;
    }
  }
}
