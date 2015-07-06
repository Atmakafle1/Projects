# Design Document

**Author**: Team07

## 1 Design Considerations

We made several choices consciously in our design.  
 1. Should each customer keep an entire history of reward, or just the current one?  
   - In order to keep the software having a light memory footprint, we decided to keep only the current reward.  
 2. Should we use a Payment Interface for extendability?  
   - Since this is only used by small farmer merchants, we decided not to use it for simplicity purposes.  
 3. Should we implement voiding a transaction  
   - Implementing voiding a transaction will complicate how reward and credit to be adjusted. Also it doesn not make sense to do return for fresh produce purchases. Hence this is not implemented  
 4. Should we implement client/server architecture with the software running cellphone just have a thin client?  
   - Since the customer could be in a place where there is no wireless signal for his carrier, we decided to have the data stay on the cellphone as well. This also makes sense because we do not want to host data for customers, which will incur our cost, and customers do not expect to host their data on a separate server either.  
 5. Should we have a BussinessRule interface that get triggered in  
   - Since this is for small for farmer merchants, this is not implemented for the purpose of simplicity.  

### 1.1 Assumptions

 1. The software should function under the following environment:  
   - Mobile Cell Phone or Tablet with CPU having at least 500MHz, at least 32MB of memory and 256MB of disk space.  
   - Andoid OS 4.0 or above  
   - Internet connectivity is not required.  
 2. The software should be used for small farmer merchant with credit card transactions only. It should have no more than 5000 customers and will keep the most recent 1000000 transactions.  
 3. All the operation need to be done on a single mobile device. No multiple-device checkout, no cross-device data sharing, no cross-device synchronization will be implemented.  
 4. The budget to implement this project is two man week.  
 5. The system will depend on external systems to scan credit cards and to process payments.   
  
### 1.2 Constraints

 1. The software need to operate under a mobile environment. Hence, it should be written efficiently interm of both CPU utilization and memory footprint.  
 2. The software will be operated at places that potentially has no wireless connection of the cellphone carrier. So, it must operate without relying on internet connections.  
 3. The volumn of data will be small. So no external databases software is neccessary.  

### 1.3 System Environment

 1. The software should function under the following environment:  
   - Mobile Cell Phone or Tablet with CPU having at least 500MHz, at least 32MB of memory and 256MB of disk space.  
   - Andoid OS 4.0 or above  
 2. The software will need to work with credit card scanner software or payment processing provider software through APIs.  

## 2 Architectural Design


### 2.1 Component Diagram

  Since the software is small, it is inappropriate to break it down to multiple components. In this project, there is only one component, StoreManager, which does checkout, apply rewards and print out a transaction happened in the past.

### 2.2 Deployment Diagram

  The software will be deloyed to customers smartphones or tablets through side loading the application. In the future with the customer's consent we may deploy the app through Google Play Store.

## 3 Low-Level Design


### 3.1 Class Diagram

  ![Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/TeamDesign/design.png)

### 3.2 Other Diagrams

  ![Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/seq_diagram_v1.png)

## 4 User Interface Design

  ![Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/home_page.png)
  ![Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/add_new_customer.png)
  ![Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/edit_customer.png)
  ![Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/check_out.png)
  ![Diagram](https://github.com/gt-ud-softeng/6300Spring15Team07/blob/master/Project2/D1/images/lookup_transaction.png)

