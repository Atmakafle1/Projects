# Use Case Model

**Author**: Team 07

## 1 Use Case Diagram

![Use Case Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/use_case_v6.png)

## 2 Use Case Descriptions

**Add Customer**
- Requirements: Must allow the Manager to add a Customer's information to the system.
- Pre-conditions: There must be a Manager and Customer.
- Post-conditions: All of the Customer's information has been updated, including Reward information.
- Scenarios: The Customer has come to the Manager. The Manager enters the information that the Customer provides him/her.

**Edit Customer**
- Requirements: Must allow the Manager to edit a Customer's information in the system.
- Pre-conditions: There must be a Manager and Customer.
- Post-conditions: All of the Customer's information has been updated, including Reward information.
- Scenarios: The Customer has come to the Manager. The Manager enters the information that the Customer provides him/her.

**Checkout**
- Requirements: Must allow the Manager starts a Transaction and finish in successful or failed Transaction.
- Pre-conditions: There must be a Manager and Customer. The Customer been issued a Credit Card.
- Post-conditions: The Transaction has been completed successfully or failed. The Reward information has been updated 
- Scenarios: The Customer has come to the Manager. The Manager starts the Transaction, which calculates the amount and the Rewards. Upon successful or failed completion of the Payment the Customer and Manager are notified. 

**Process Payment**
- Requirements: Must allow the Manager tp process the Payment that the Customer submitted and sends it to the Credit Card Service Provider.
- Pre-conditions: There must be a Manager and Customer. The Customer has been issued a Credit Card and made a payment for the transaction.
- Post-conditions: The Payment has completed successfully or failed.
- Scenarios: The Customer has been told the total amount for the Transaction. The Customer slides his or her Credit Card through the Credit Card Scanner. The Credit Card information is sent to the Credit Card Service Provider. The Credit Card Service Provider returns that the Payment has succefully or failed to completed.

**Track Transactions**
- Requirements: Must allow the Manager track all the transactions that have successfully completed.
- Pre-conditions: There must be a Manager and Customer. The Customer has completed a transaction successfully.
-- Post-conditions: The Transaction is displayed to the Manager.
- Scenarios: The Customer has come to the Manager. The Customer would like to know how many transactions he or she has completed to date. The Manager is displayed the Transaction History.

**Manage Reward**
- Requirements: Must allow the Manager manage the Rewards that the Customer has accrued.
- Pre-conditions: There must be a Manager and Customer. The Customer has accrued a single or many rewards.
- Post-conditions: The Rewards that the customer has accrued are displayed to the manager
- Scenarios: The Customer has come to the Manager. The Customer would like to know how many Rewards he or she has accrued to date. The Manager is displayed the Rewards information for the Customer.

**Make Payment**
- Requirements: Must allow the Customer to make a Payment for the total amount of the Transaction.
- Pre-conditions: The Customer has been issued a Credit Card.
- Post-conditions: The Payment information is ready to be processed.
- Scenarios: The Customer has been told the total amount for the Transaction. The Customer slides his or her Credit Card through the Credit Card Scanner.

