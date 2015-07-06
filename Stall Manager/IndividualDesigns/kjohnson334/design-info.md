# **Rational Document**

##1 Assumptions##
There is always 1 instance of the manager, customer, payment, and transaction classes. The customer owns at least 1 credit card, because that is the only form of payment that we accept. The 1 credit card scanner and 1 or many payment-processing services are outside of our system, the credit card scanner has to be attached to at least 1 payment-processing service. 

##2 Design Decisions##

I decided not to have association labels because I believe that they clutter the diagram.

###1 Manager Class###

1. Add or edit one of the customer class.
2. Display 1 or many of the transaction class.
3. Display zero or many of the transaction history class.
4. Process 1 of the payment class which will tell the manager class if it has completed successfully or failed.
5. Manage 0 or many rewards that the customer class has earned.

###2 Customer Class###

Consists of a first and last name, the zip code where they live, an email address, an ID to identify the customer, the threshold until they reach gold status and whether or not they reach gold status.

1. Can be a Gold Member which gives them a discount.
2. Earns zero or many of the reward class.
3. Owns at least 1 of the credit card class. 
4. Makes 1 or many of the payment class for 1 or many of the transaction class and will be told that the payment has completed successfully or failed by the payment class. 

###3 Transaction Class###

Consists of a ID for the transaction, a date of the transaction, the amount of the transaction, the customer ID who started the transaction, and whether the payment was successful or not.

1. Calculates the 0 or many of the reward class the customer has earned.
2. Calculates how much of the 1 payment class the customer class needs to make.
 
###4 Transaction History Class###

Consists of 1 or many of the transactions class.


###5 Payment Class###

Consists of a ID for the payment, a date of the payment, the total, the customer who made the payment, and the transaction ID that is associated with the payment.

1. Tells the 1 transaction and 1 customer class that the payment has succeeded or failed.
2. Emails the customer class when the customer class has met the gold threshold.

###6 Reward Class###

Consists of the amount of rewards the customer has accrued, the threshold until they get to another reward, and the customer ID of who is getting the reward.

###7 Credit Card###

Consists of the card holder name, account number, expiration date, and the security code (which is not stored).




