# Test Plan

**Author:** Team 07

## 1 Testing Strategy

### 1.1 Overall strategy

**Unit-Test:** Imad  
Unit testing will be used to test the modules of the software in isolation. Each module will be tested using test cases to ensure each method is correctly functioning. Because of the smaller scale of the software, this can be done by one team member.

**Integration-Test:**Imad  
Once the modules have passed their Unit test, different parts of the software will be tested using Integration testing. Because many modules are associated with one another, the Integrations Test will test the interactions each module has with one another.

**System-Test:** Imad  
Once Integration testing has been completed, a System Test will be done. Given the size of the software, a functional test will be done however, a non-functional test is not a necessity.
Furthermore, our testing strategy will not include Regression-testing as described below but will include Acceptance Testing.

**Acceptance-Test:** Imad  
The purpose of the software is to fulfill the needs and requirements of the customer. To ensure that our software does this, we will include Acceptance testing as part of our testing strategy and that each of the customer requirements are fulfilled.

**Regression-Testing**  
Regression-testing is not as important for this software because of its smaller size and concrete requirements. Once the software is completed and tested using the methods above, there shouldn’t be a reason to have any changes made to the software unless the customer changes the requirement and requests additional features. 

### 1.2 Test Selection

Test cases will be selected using the software specifications by breaking down the specifications into testable parts. The input used for the test will include normal and obscure parameters to test functionality given such. 
Several use cases were defined and using those, test cases will be selected as well.

### 1.3 Adequacy Criterion

Quality of test cases:
Coverage of requirements:
The software has a set number of requirements that need to fulfilled and the test cases are selected using that requirement. The quality of the test cases will be assessed by whether the test cases cover all the requirements including a range of different inputs that could potentially create bugs or break the software.
Coverage of scenarios defined in use cases.
A set of use use cases were defined and the quality of the test cases are assessed whether the test cases include those use cases. 


### 1.4 Bug Tracking

BloodHound will used for bug tracking.

### 1.5 Technology

JUnit will be used for testing. 

## 2 Test Cases

|Test#|Purpose                      |Steps                                                  |Expected Result                             |Actual Result|Pass/Fail|
|-----|-----------------------------|-------------------------------------------------------|--------------------------------------------|-------------|---------|
|  1  |Adding a new Customer        |Manager adds new customer                              |Customer info added                         |             |         |
|  2  |Editing customer information |Manager edits customer info                            |Customer info updated                       |             |         | 
|  3  |Checkout                     |Manager starts a transaction                           |Transaction completed successfully or failed|             |         |
|  4  |Checkout with rewards        |Customer uses reward discount on a payment             |Reward discount is applied to payment       |             |         |
|  5  |Process Payment              |Processed payment sent to credit card service provider |Payment completed or failed                 |             |         |
|  6  |Make a payment               |Customer makes a payment                               |Payment info ready to be processed          |             |         |
|  7  |Display transaction history  |Manager accesses transaction history                   |Transaction history is displayed            |             |         |
|  8  |Manage rewards               |Manager accesses rewards                               |Customer rewards are displayed              |             |         |
|  9  |Gold membership              |Customer achieves "Gold" status                        |5% discount on payments                     |             |         |
| 10  |Reward acquisition           |Cusomter spends more than $100                         |$10 added to reward value                   |             |         |
| 11  |Gold email                   |Customer achieve "Gold" status                         |An email is sent notifying customer         |             |         |
| 12  |Reward email                 |Customer earns a reward                                |An email is sent notifying customer         |             |         |

