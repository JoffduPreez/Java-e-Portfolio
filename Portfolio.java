package jdupreez_a3.ePortfolio;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class Portfolio {
  /**
   * @param args
   */
  public static void main(String[] args) {
    Investment investments = new Investment();
    GUI ePortfolio = new GUI(investments);

    int quantity, investmentCount, investmentType, symbolExists;
    double price;
    String investmentSymbol, searchSymbol, searchName, searchPrice, selectedOption, stockType;
    Scanner scannerObject = new Scanner(System.in);

    selectedOption = "";
    investmentType = 0;

    if (args.length != 0) {
      int count = 0;
      String[] fileInfo = new String[6];

      try {
        File investmentFile = new File(args[0]);
        Scanner fileScanner = new Scanner(investmentFile);
        while (fileScanner.hasNextLine()) {
          // ge the words between the quotations
          Pattern p = Pattern.compile("\"([^\"]*)\"");
          Matcher m = p.matcher(fileScanner.nextLine());
          while (m.find()) {
            fileInfo[count] = m.group(1);
          }
          // add the investments to the arraylist
          if (count == 5) {
            if (fileInfo[0].compareTo("Stock") == 0) {
              investments.investmentList.add(
                  new Stock(fileInfo[1], fileInfo[2], Integer.parseInt(fileInfo[3]), Double.parseDouble(fileInfo[4])));
            } else if (fileInfo[0].compareTo("Mutual Fund") == 0) {
              investments.investmentList.add(new MutualFund(fileInfo[1], fileInfo[2], Integer.parseInt(fileInfo[3]),
                  Double.parseDouble(fileInfo[4])));
            }
            count = 0;
          } else {
            count++;
          }
        }

        // Loading the initial hashmap:
        String name, currentNameWord;
        boolean keyFound;
        StringTokenizer nameWords;

        for (int i = 0; i < investments.investmentList.size(); i++) { // for each investment in the list
          name = investments.investmentList.get(i).getName();
          nameWords = new StringTokenizer(name, " "); // get the name of the current investment and tokenize it

          while (nameWords.hasMoreTokens()) { // for each word in the name
            currentNameWord = nameWords.nextToken();
            currentNameWord = currentNameWord.toLowerCase();

            keyFound = false;
            for (Map.Entry<String, ArrayList<Integer>> key : investments.nameIndexes.entrySet()) {
              if (currentNameWord.compareToIgnoreCase(key.getKey()) == 0) {
                keyFound = true;
              }
            }
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            if (keyFound == true) { // if that key was found, add the current investment index to its list
              tempList = investments.nameIndexes.get(currentNameWord);
              tempList.add(i);
              investments.nameIndexes.put(currentNameWord, tempList);
            } else { // if the key does not exist, add the current word to the hashmap as a new key
              tempList.add(i);
              investments.nameIndexes.put(currentNameWord, tempList);
            }
          }
        }
      } catch (Exception e) {
        System.out.println("Could not open file");
      }
    }

    symbolExists = 0;

    ePortfolio.setOptionPanels(investments, ePortfolio);

    if (selectedOption.compareToIgnoreCase("save") == 0) {
      String type = "";
      String fileString = "";
      System.out.println("Please enter the name of the file to save your investments to:");
      String filename = scannerObject.nextLine();
      if (investments.investmentList.size() == 0) {
        System.out.println("Sorry, you have no investments to save");
        return;
      }

      try {
        // clear the contents of the file
        PrintWriter writer = new PrintWriter(filename);
        writer.print("");
        writer.close();

        PrintWriter fileWriter = new PrintWriter(filename, "UTF-8");
        // write each line from the arraylist
        for (int i = 0; i < investments.investmentList.size(); i++) {
          if (investments.investmentList.get(i) instanceof Stock) {
            type = "Stock";
          } else {
            type = "Mutual Fund";
          }
          fileString = "type = \"" + type + "\"";
          fileWriter.println(fileString);

          fileString = "symbol = \"" + investments.investmentList.get(i).getSymbol() + "\"";
          fileWriter.println(fileString);

          fileString = "name = \"" + investments.investmentList.get(i).getName() + "\"";
          fileWriter.println(fileString);

          fileString = "quantity = \"" + investments.investmentList.get(i).getQuantity() + "\"";
          fileWriter.println(fileString);

          fileString = "price = \"" + investments.investmentList.get(i).getPrice() + "\"";
          fileWriter.println(fileString);

          fileString = "bookValue = \"" + investments.investmentList.get(i).getBookValue() + "\"";
          fileWriter.println(fileString);
        }
        fileWriter.close();
      } catch (Exception e) {
        System.out.println("Failed to save to file.");
      }
    }
  }

  public static void printList(Investment investments, GUI ePortfolio) {
    for (int i = 0; i < investments.investmentList.size(); i++) {
      ePortfolio.messagePrint
          .setText(ePortfolio.messagePrint.getText() + investments.investmentList.get(i).toString() + "\n");
    }
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
