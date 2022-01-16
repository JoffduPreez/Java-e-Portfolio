# Java-e-Portfolio
Designed using Java in combination with the Swing GUI to create an investment portfolio.
This portfolio is used to keep track of buying and selling investments, searching for investments, and calculating the portfolio’s gain.

(1) The general problem I am trying to solve:
  The problem I was trying to solve for this program was to simulate an investment portfolio. This portfolio allows the user to keep track of their buying and selling of investments, search for relevant investments, update their investments' prices, calculate the total gain of their portfolio, and save/load their investments to/from a file.

(2) Assumptions and limitations of my program:
  I have assumed that the price and quantity entered will be a numeric value
  I have assumed that the quantity entered will be an integer value, not a double
  I have assumed that a symbol can only be one word

(3) What possible improvements could be made if I was to do this assignment again:
  I would have loved to have a much better UI
  I think it would be cool to have a menu option that automatically buys stocks so that when you test, you can quickly test without having to first buy a bunch of stocks

(4) How can a user build and test my program:
  Compile the program using javac followed by filenames

(5) How is my program tested for correctness:
TEST PLAN:
For this test plan, every test is run in one compilation so that each test can be built off of the previous one. Think of each test as selecting a menu option, not every time you run the program.
For example, TEST 3 will inform the user that the symbol is already taken because the symbol was used to buy a stock in TEST 1

TEST 1:
Input:
  buy
  stock
  AAPL
  Apple Inc
  500
  50
Expected result:
  Create a new stock object with its attributes equal to the correspoing inputs from above (AAPL, Apple Inc, 500, 50). Its book value should be 25009.99

TEST 2:
Input:
  b
  stock
  AAPL
  50
  55
Expected result:
  Add to the existing AAPL stock object, it will increase the quantity by 50 and set the price to 55. Its book value should be 27769.98

TEST 3:
Input:
  b
  Mutual Fund
  AAPL
  GOOG
  Google Inc
  100
  119.02
Expected result:
  When the AAPL symbol is entered, the program will inform the user that the symbol is already taken, and it will ask them to enter a different symbol. It will then create a new stock object with its attributes equal to the correspoing inputs from above (GOOG, Google Inc, 100, 119.02). Its book value should be 11902.0

TEST 4:
Input:
  Sell
  AAPL
  75
  150
Expected result:
  The program will inform the user that they recieved $11240.01 from the sale. The updated values will be: quantity = 350, price = 75, bookValue = 20196.34909

TEST 5:
Input:
  S
  TSLA
Expected result:
  The program will inform the user that the symbol does not exist, and they will be directed back to the command loop

TEST 6:
Input:
  Sell
  GOOG
  200
  1000
Expected result:
  The program will inform the user that the quantity they would like to sell exceeds the remaining quantity of the mutual fund, and they will be directed back to the command loop

TEST 7:
Input:
  Sell
  GOOG
  120
  100
Expected result:
  The program will inform the user that they recieved $11955.0 from the sale. The investment will be delete from the portfolio because all of its quantity was sold

TEST 8:
Input:
  update
  87.5
Expected result:
  The program will update the price of the AAPL stock to $87.5

TEST 9:
Input:
  getGain
Expected result:
  The program will inform the user that there total gain is $14793.660909090908

TEST 10:
Input:
  search
  leave the three search options blank by pressing enter for each one
Expected result:
  The program will print every investment and its values

TEST 11:
Input:
  buy
  m
  TSLA
  Tesla Inc Cars
  467
  324.2
Expected result:
  Create a new mutual fund object with its attributes equal to the correspoing inputs from above (TSLA, Tesla Inc Cars, 467, 324.2). Its book value should be 25009.99

TEST 12:
Input:
  search
  leave symbol field blank
  Inc
  -100
Expected result:
  The program will print the AAPL stock and its values, because it contains the word "Inc" in its name and its price is less then 100

TEST 13:
Input:
  search
  TSLA
  leave keywords to search for in the name blank
  leave the price range blank
Expected result:
  The program will print the STLA mutual fund and its values, because its symbol matches TSLA

TEST 14:
Input:
  save
  investmentFile.txt
Expected result:
  The program will save all the current investments to a file called investmentFile.txt. If investmentFile.txt already exists, then all its current information will be overwritten. If investmentFile.txt does not exist, the file will be created and the current investments will be saved to the file

TEST 15:
Input:
  q
Expected result:
  The program will end