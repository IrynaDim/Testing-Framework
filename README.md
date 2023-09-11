## PrestaShop Multithreading Testing Framework

### Technologies used:
- Selenium
- TestNG
- SLF4J
- AventStack ExtentReports
- Lombock

### Abilities:
You can choose web driver to start your tests in framework.properties file or pass it as parameter 'browser'. Current framework can work with Chrome or Firefox driver. 
Be sure that you have installed such driver on your computer. By default, Chrome driver is chosen.

Oslo its possible to choose resolution of the driver window in framework.properties file or pass it as parameter 'resolution'. By default, its 1280x1024. 

Tests can run in multithreading environment. By default, there are 2 threads. You can change it in test.xml file.

Page Object pattern, Steps & Chain of
invocations, Driver Factory and Property reader are implemented.

Report and logging are added. You can find logs in logs/main.log file. And report in report.html file. 
