package jdupreez_a3.ePortfolio;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/** This class manages the buying and selling of mutual funds */
public class MutualFund extends Investment {
  /** This is a constructor to intialize the data for the class */
  public MutualFund(String symbol, String name, int quantity, double price) throws Exception {
    if (symbol == null || name == null || quantity < 0 || price < 0) {
      throw new Exception("Attributes of class must not be null, and price/quantity must be positive");
    } else {
      this.setSymbol(symbol);
      this.setName(name);
      this.setQuantity(quantity);
      this.setPrice(price);
      this.setBookValue(quantity * price);
    }
  }

  /** This is a method to buy a mutual fund */
  public static void Buy(Investment investments, String investmentSymbol, GUI ePortfolio) {
    Scanner scannerObject = new Scanner(System.in);
    int quantity;
    String name, currentNameWord;
    double price;
    boolean nameFound = false;

    name = "";

    // check that the other type of investment doest not have an investment with the
    // same symbol
    for (int k = 0; k < investments.investmentList.size(); k++) {
      while (investmentSymbol.compareTo(investments.investmentList.get(k).getSymbol()) == 0
          && investments.investmentList.get(k) instanceof Stock) {
        ePortfolio.messageBuy.setText(
            "That symbol is already taken for a stock investment, please enter a different symbol for your investment:");
        return;
      }
    }

    int symbolExists = 0, index = 0;
    for (int i = 0; i < investments.investmentList.size(); i++) {
      // check if an investment with the same symbol of the given kind exists in the
      // system
      if (investments.investmentList.get(i).getSymbol().compareTo(investmentSymbol) == 0
          && investments.investmentList.get(i) instanceof MutualFund) {
        symbolExists = 1;
      }
      index = i;
    }

    // if the symbol exists, then dont ask for a name, but if it does, then ask for
    // a name
    if (symbolExists == 1) {
      if (ePortfolio.buyQuantityField.getText().compareTo("") == 0) {
        ePortfolio.messageBuy.setText("Cannot leave the quantity field blank");
        return;
      }
      if (Double.parseDouble(ePortfolio.buyQuantityField.getText()) < 0) {
        ePortfolio.messageBuy.setText("Please enter a positive integer for the quantity");
        return;
      }
      quantity = Integer.parseInt(ePortfolio.buyQuantityField.getText());
      investments.investmentList.get(index).setQuantity(quantity + investments.investmentList.get(index).getQuantity());
      if (ePortfolio.buyPriceField.getText().compareTo("") == 0) {
        ePortfolio.messageBuy.setText("Cannot leave the price field blank");
        return;
      }
      if (Double.parseDouble(ePortfolio.buyPriceField.getText()) < 0) {
        ePortfolio.messageBuy.setText("Please enter a positive number for the price");
        return;
      }
      price = Double.parseDouble(ePortfolio.buyPriceField.getText());
      investments.investmentList.get(index).setPrice(price);

      investments.investmentList.get(index).setBookValue(investments.investmentList.get(index).getBookValue()
          + quantity * investments.investmentList.get(index).getPrice());
    } else {
      name = ePortfolio.buyNameField.getText();
      if (ePortfolio.buyNameField.getText().compareTo("") == 0) {
        ePortfolio.messageBuy.setText("Cannot leave the name field blank");
        return;
      }
      if (ePortfolio.buyQuantityField.getText().compareTo("") == 0) {
        ePortfolio.messageBuy.setText("Cannot leave the quantity field blank");
        return;
      }
      if (Double.parseDouble(ePortfolio.buyQuantityField.getText()) < 0) {
        ePortfolio.messageBuy.setText("Please enter a positive integer for the quantity");
        return;
      }
      quantity = Integer.parseInt(ePortfolio.buyQuantityField.getText());
      if (ePortfolio.buyPriceField.getText().compareTo("") == 0) {
        ePortfolio.messageBuy.setText("Cannot leave the price field blank");
        return;
      }
      if (Double.parseDouble(ePortfolio.buyPriceField.getText()) < 0) {
        ePortfolio.messageBuy.setText("Please enter a positive number for the price");
        return;
      }
      price = Double.parseDouble(ePortfolio.buyPriceField.getText());

      try {
        investments.investmentList.add(new MutualFund(investmentSymbol, name, quantity, price));
      } catch (Exception e) {
        ePortfolio.messageBuy.setText(e.getMessage());
      }

      StringTokenizer nameWords = new StringTokenizer(name, " ");

      while (nameWords.hasMoreTokens()) { // for each word in the name
        currentNameWord = nameWords.nextToken();
        currentNameWord = currentNameWord.toLowerCase();

        nameFound = false;
        // compare current word in the name to each key
        for (Map.Entry<String, ArrayList<Integer>> key : investments.nameIndexes.entrySet()) {
          if (currentNameWord.compareToIgnoreCase(key.getKey()) == 0) {
            nameFound = true;
          }
        }

        ArrayList<Integer> tempList = new ArrayList<Integer>();
        if (nameFound == true) {
          // add the new position at the end of its position list
          tempList = investments.nameIndexes.get(currentNameWord);
          tempList.add(investments.investmentList.size());
          investments.nameIndexes.put(currentNameWord, tempList);
        } else {
          // create a new entry on the HashMap so that the new keyword is assocated with a
          // list that contains the new position
          tempList.add(investments.investmentList.size());
          investments.nameIndexes.put(currentNameWord, tempList);
        }
      }
    }
    ePortfolio.messageBuy.setText("Mutual fund bought successfully");
  }

  /** This is a method to sell a mutual fund */
  public static void Sell(Investment investments, String investmentSymbol, int quantity, double price, GUI ePortfolio) {
    ArrayList<Integer> hashNameIndex = new ArrayList<Integer>();
    ArrayList<String> valuesToRemove = new ArrayList<String>();

    for (int i = 0; i < investments.investmentList.size(); i++) {
      if (investmentSymbol.compareTo(investments.investmentList.get(i).getSymbol()) == 0) {
        if (investments.investmentList.get(i).getQuantity() < quantity) {
          ePortfolio.messageSell
              .setText("The quantity you would like to sell exceeds the remaining quantity of your stock");
        } else if (investments.investmentList.get(i).getQuantity() == quantity) {
          for (Map.Entry<String, ArrayList<Integer>> key : investments.nameIndexes.entrySet()) {
            hashNameIndex = key.getValue();
            for (int j = hashNameIndex.size() - 1; j >= 0; j--) {
              if (hashNameIndex.get(j) == i) {
                hashNameIndex.remove(j);
                break;
              }
              if (hashNameIndex.get(j) > i) {
                hashNameIndex.set(j, hashNameIndex.get(j) - 1);
              }
            }

            if (hashNameIndex.size() == 0) {
              valuesToRemove.add(key.getKey());
            } else {
              investments.nameIndexes.put(key.getKey(), hashNameIndex);
            }
          }

          for (int k = 0; k < valuesToRemove.size(); k++) {
            investments.nameIndexes.remove(valuesToRemove.get(k));
          }

          investments.investmentList.remove(i);
          ePortfolio.messageSell.setText("You recieved $" + (quantity * price - 45)
              + " from the sale");
        } else {
          investments.investmentList.get(i).setPrice(price);
          investments.investmentList.get(i).setBookValue(
              investments.investmentList.get(i).getBookValue() - investments.investmentList.get(i).getBookValue()
                  * quantity / investments.investmentList.get(i).getQuantity());
          investments.investmentList.get(i).setQuantity(investments.investmentList.get(i).getQuantity() - quantity);

          ePortfolio.messageSell.setText("You recieved $" + (quantity * price - 45)
              + " from the sale");
        }
      }
    }
  }

  /** this returns the data for the current object */
  public String toString() {
    return ("Symbol = " + this.getSymbol() + ", Name = " + this.getName() + ", Quantity = " + this.getQuantity()
        + ", Price = " + this.getPrice() + ", Book value = " + this.getBookValue());
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
