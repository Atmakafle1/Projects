# Use Case Model

**Author**: Team 07

## 1 Use Case Diagram

![Use Case Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/use_case_v6.png)

## 2 Use Case Descriptions

**Add Customer**
- Requirements: Must allow the Manager to add a Customer's information to the system.
- Pre-conditions: There must be a Manager and Customer.
- Post-conditions: All of the Customer's information has been added, including first name, last name, email address and zip code.
- Scenarios: The Customer has come to the Manager. The Manager enters the information that the Customer provides him/her.

**Edit Customer**
- Requirements: Must allow the Manager to edit a Customer's information in the system.
- Pre-conditions: There must be a Manager and Customer.
- Post-conditions: All of the Customer's new information has been updated, including first name, last name, email address and zip code.
- Scenarios: The Customer has come to the Manager. The Manager enters the information that the Customer provides him/her.

**Checkout**
- Requirements: Must allow the Manager starts a Transaction and finish in successful or failed Transaction.
- Pre-conditions: There must be a Manager and Customer. The Customer has something to purchase and has a credit card to pay for them.
- Post-conditions: The Transaction has been completed successfully or failed. The Reward information has been updated 
- Scenarios: The Customer has come to the Manager. The Manager starts the Transaction, which calculates the amount and the Rewards. Upon successful or failed completion of the Payment the Customer and Manager are notified. 

**View Transactions**
- Requirements: Must allow the Manager view all the transactions that have successfully completed.
- Pre-conditions: There must be a Manager and Customer. The Customer has completed a transaction successfully.
- Post-conditions: The list of transaction is displayed to the Manager.
- Scenarios: The Customer has come to the Manager. The Customer would like to know how many transactions he or she has completed to date. The Manager is displayed the Transaction History.
