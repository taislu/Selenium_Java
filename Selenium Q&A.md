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
