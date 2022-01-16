package jdupreez_a3.ePortfolio;

import java.io.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This class maintains two ArrayLists for stocks and mutual funds for buying,
 * selling, updating, and computing the total gain for the related investments,
 * searching the lists sequentially for the matched investments, and displaying
 * the result on the screen
 */
public class Investment {
  public ArrayList<Investment> investmentList = new ArrayList<Investment>();
  public HashMap<String, ArrayList<Integer>> nameIndexes = new HashMap<String, ArrayList<Integer>>();
  private String symbol;
  private String name;
  private int quantity;
  private double price;
  private double bookValue;

  /**
   * @param args
   */

  /** accessor method */
  public String getSymbol() {
    return this.symbol;
  }

  /** accessor method */
  public String getName() {
    return this.name;
  }

  /** accessor method */
  public int getQuantity() {
    return this.quantity;
  }

  /** accessor method */
  public double getPrice() {
    return this.price;
  }

  /** accessor method */
  public double getBookValue() {
    return this.bookValue;
  }

  /** mutator method */
  public String setSymbol(String symbol) {
    return this.symbol = symbol;
  }

  /** mutator method */
  public String setName(String name) {
    return this.name = name;
  }

  /** mutator method */
  public int setQuantity(int quantity) {
    return this.quantity = quantity;
  }

  /** mutator method */
  public double setPrice(double price) {
    return this.price = price;
  }

  /** mutator method */
  public double setBookValue(double bookValue) {
    return this.bookValue = bookValue;
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
