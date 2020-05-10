# A smart energy management and monitoring system
----
* This system is a task in college and  I  want to redo it.
* Luckily I find my coursework file.
----
## How to understand version in my java files
* Example 0.1.20200510.1228
 * 0: ifnormal version, it increase if mang changes happen.
 * 1: base version, it will not change for the time being.
 * 20200510: data of last modify
 * 1228: time of last modify
----
## Specification of project

You are a software development team that is responsible for developing a **smart energy management and monitoring system** as part of the big smart home project.
 * The smart energy monitoring system should make it easy for the consumer to view their electricity and gas usage and help them to keep the cost down.
 * The energy provider should be able to use the energy management system to set up tariff, get the meter reading from each household and generate bills.
 * Each household can have only one smart energy monitoring software, one smart electricity meter and one gas meter.
 * The smart energy monitoring software can communicate with the smart electricity meter and smart gas meter.

The high-level specifications and requirements are described as follows:

### Smart energy monitoring software

* **View electricity consumption and cost**  
The system should display the live electricity usage of the day, in kWh. The usage should be updated frequently, e.g. approximately every 30 seconds. It should also show the current electricity cost of the day, in pounds and pence £.                       
 
* **View gas consumption and cost**  
The system should display the live gas usage of the day, in kWh. The usage does not need to be updated frequently, e.g. approximately every 30 minutes would be good enough. It should also show the current gas cost of the day, in pounds and pence £. 
 
* **Budget and alert**  
Consumer should be able to set a budget to keep track of the spending. The system should be able to show the consumption level, compare with the budget and give alert. For example, if the electricity or gas usage is lower than the budget, display a green signal; or if it is over the budget, display a red signal. The consumer should be able to change the budget at any time. The budget can be set in either usage (kWh) or cost (£).   

* **View historic consumption and costs**  
Consumer should be able to view the historic consumption and costs, for example daily, weekly and monthly electricity/gas usage and cost. 
 
* **Check tariff**  
The tariff is set by the energy provider and can only be changed by the energy provider. The consumer can only view the current tariff (price per kWh).  
 
* **Send meter readings to energy provider**  
The system should send gas and electricity meter readings to the energy provider every month, on a fixed day of the month (e.g. every 1st of the month). The meter reading is a 4digit number, (0000-9999) with each unit is 1 kWh. 
 
### Smart energy management system 
 
* **Add/view/remove consumer and meter information of each household**  
The energy provider should be able to add new consumer to the system and remove existing consumer. When adding the consumer to the system, the household’s smart energy monitoring software and smart meter are registered to the system. The system should be able to remove an existing consumer. The energy can view the readings and bills history of the consumer. 
 
* **Set tariff**  
The tariff is set by the energy provider and can only be changed by the energy provider. The standard tariff is as below: 
    * Gas Unit rate 3.88p per kWh
    * Electricity Unit rate 14.60p per kWh 
 
* **Receive meter readings and generate bills**  
The system should receive monthly gas and electricity meter readings from households and generate a monthly bill and send it to the consumer by email and/or post. 

### Other requirements 
 
* Basic restrictions and error checking must be considered: for example, the meter reading should be a positive integer; meter reading can only increase. etc.
* The software should be easy to use: that is, the user should be able to operate the software with common sense or with simple instructions.
* The software should be user friendly: for example, the user should be able to cancel the operation at any time; it should display messages promptly to user during the operation; etc.
* The software must be developed using Java as a stand-alone application. Simple graphic user interface (GUI) should be used. Java SE 8 or above should be used.
* All input and output files should be in simple text file format. You may use plain Text (txt), CSV or XML. Do NOT use database.
* Your design must be flexible and extensible, so that it can adapt to continuously changing requirements and can be used in a general market in the future. E.g. connect to other smart home devices such as smart light, smart radiator, smart curtain and smart speakers etc. 
* Your design of the software must be capable of adapting to such future changes. That is, when developing a new software, you should be able to reuse the existing components. When adding new features to the existing software, you should make the least impact on the existing code.  

----
