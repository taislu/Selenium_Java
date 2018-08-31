# Q: What is StaleElementException?
A: This exception is thrown when the element is either deleted or no longer attached to the DOM (Document Object Model)

# Q: What is POM (Page Object Model)?
A: Page Object Model is a design pattern for creating an Object Repository for web UI elements. Each web page in the application is required to have its own corresponding page class. The page class is thus responsible for finding the WebElements in the page, and then perform operations on those WebElements.

# Q: What is Page Factory?
A: Page Factory is used to initialize the elements of the page object or instantiate the page objects. Instead of using “driver.findElement”, we use annotations like @FindBy to find WebElements, and initElements method to initialize web elements from the page factory class.

# Q: How do you achieve synchronization in WebDriver?
A: Implicit wait instructs the WebDriver to wait for some time by polling the DOM. Once you have declared implicit wait, it will be available for the entire life of the WebDriver instance. Explicit wait instructs the execution to wait for some time until some condition is achieved such as element to be clickable or present.
<br>
WebDriverWait wait=new WebDriverWait(driver, 20);
<br>
Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath( “<xpath”)));

# Q: How to scroll down to a particular element?
A: We can scroll down to a particular element using the function scrollIntoView().
<br>
((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

# Q: How to handle a dropdown in Selenium WebDriver? How to select a value from dropdown?
A: Use select tag to identify a dropdown element, and then select an option from that dropdown element
<br>
<select id="mySelect">
<br>
<option value="option1">Cars</option>
<br>
<option value="option2">Bikes</option>
<br>
<option value="option3">Trains</option>
<br>
</select>
<br>
WebElement mySelectElement = driver.findElement(By.id("mySelect"));
<br>
Select dropdown = new Select(mySelectElement);
<br>
Now to select an option from that dropdown, we can do it in either of the three ways:
<br>
1.	dropdown.selectByVisibleText(“Bikes”); → Selecting an option by the text that is visible
<br>
2.	dropdown.selectByIndex(“1”); → Selecting, by choosing the Index number of that option
<br>
3.	dropdown.selectByValue(“option2”); → Selecting, by choosing the value of that option
<br>

# Q: How to handle boostrap dropdown?
A: find and click the menu WebElement, then find the list of elements, and go through the list
<br>
driver.findElement(By.xpath(“//*[id=’menu1’]”)).click();
<br>
List <WebElement> dropDown = driver.findElements(By.xpath(“//ul[@class=’dropdown-menu’]//li/a”));
<br>
for(WebElement e:dropDown){
<br>
	String str = e.getAttribute(“innerHTML”);
<br>
	If( str.contentEquals(“JavaScript”) ){
<br>
		e.click();
<br>
		break;
<br>
	}
}
(ul = undordered list)

# Q: How to search for a list of WebElements in Page Factory?
A: Use FindAll annotation to search for all elements that match any of the FindBy criteria.
@FindAll(@FindBy(how = How.XPATH, using = "//*[contains(@class,'x-grid-tree-node-leaf')]"))
List<WebElement> allElements;

# Q: Explain how can you find broken links in a page using Selenium WebDriver?
A: Use the anchor tags <a> to find a list of WebElements. For each <a> tag, we can use the attribute href to obtain the hyperlink, and then analyze the response received via driver.get() method.

# Q: How to skip a method or a code block in TestNG?
A: @Test (enabled = false)

# Q: What’s the difference between Implicit, Explicit and Fluent Wait?
A: Implicit wait is used to wait for a certain amount of time before throwing an exception that selenium cannot find the element on the page. The implicit wait is set for entire session that the browser is open.
<br>
WebDriver driver = new FirefoxDriver();
<br>
driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
<br>
driver.get("http://url_that_delays_loading");
<br>
WebElement myDynamicElement = driver.findElement(By.id("myDynamicElement"));
<br>

Explicit wait is used to wait for a longer ExpectedConditions such as an element to become clickable, visible.
<br>
WebDriverWait wait = new WebDriverWait(driver, 10);
<br>
WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id("someid")));
<br>

Fluent Wait is used to wait for a condition with the maximum amount of time.
<br>
// Waiting 30 seconds for an element to be present on the page, checking
<br>
// for its presence once every 5 seconds.
<br>

  Wait wait = new FluentWait(driver)
  <br>
    .withTimeout(30, SECONDS)
  <br>
    .pollingEvery(5, SECONDS)
  <br>
    .ignoring(NoSuchElementException.class);
  <br>

  WebElement foo = wait.until(new Function() {
  <br>
    public WebElement apply(WebDriver driver) {
    <br>
    return driver.findElement(By.id("foo"));
    <br>
    }
   }); 
   
# Q: How do you read data from excel?
<br>
FileInputStream fis = new FileInputStream(“path of excel file”);
<br>
Workbook wb = WorkbookFactory.create(fis);
<br>
Sheet s = wb.getSheet(“sheetName”);
<br>
String value = s.getRow(rowNum).getCell(cellNum).getStringCellValue();

# Q: How to handle dynamic web elements?
Relative XPath with starting text : //button[starts-with(@id, ’Navyug-’)]
<br>
Relative XPath with Following or Preceding Node
//button [contains(@class, ‘Navyug-Class’)] /following:: input[contains(@id,’Navyug-’)]
//input [contains(@id,’Navyug-’)] /preceding:: button[contains(@class, ‘Navyug-Class’)]
<br>
Relative XPath with text contains : //button[contains(@class, ‘Navyug’)]
<br>
Relative XPath with Multiple Attribute
//button[contains(@id,’Navyug-’)] [contains(@class, ‘Navyug-Class-text’)]
<br>
Element with Index
Driver.findElements(By.tag(‘button’))[4]
