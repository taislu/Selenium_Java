# Selenium_Java
Selenium Java Case Studies


# CaseStudy_m2.java

Description :
Launch the Edureka portal and find the elements using different attributes (id, xpath, css selector and so on). 
The automation script should support any browser (Chrome, Firefox) without changing the attributes.

Automation Steps :
1. Launch Chrome browser using Selenium WebDriver 
2. Navigate to “https://www.edureka.co/” 
3. Maximise the Browser Window 
4. Inspect the search box with text “Search for a Course” for various attributes 
5. Get attributes id, name, xpath, class name, css values 
6. Inspect the “Log In” link to get the link text and partial link text


# Synchronise.java (m3)

Description :
Automate Edureka web application with the help of Selenium WebDriver, to search for a course, handle all the page loading time, 
using selenium wait approach and handling dynamically changing elements

Automation Steps :
1. Launch Chrome browser using Selenium WebDriver 
2. Set the Selenium page load timeout after launching the browser. This timeout will be applicable to any page load 
3. Navigate to “https://www.edureka.co/ ” 
4. Maximize the Browser Window 
5. Set implicit wait for all the activities on the browser 
6. Set selenium script timeout and execute any asynchronous script  
(Ex: window.setTimeout (arguments[arguments. Length - 1], 500);) to validate the  script timeout 
7. Search for “Selenium” course in the search box and press “Enter” 
8. Add explicit wait for page to navigate to selenium course and click on the course 
9. Wait for the whole selenium course page to be loaded and get the title of the page 
10.  Validate the page title to be “Selenium 3.0 WebDriver Online Training” 
11.  Navigate back to the previous page 
12.  Wait for whole page to be loaded and now select “All courses” from the   menu option 
13.  Add a fluent wait script to wait for whole page to be loaded under “All course”


# TicketBooking.java (m4)

Description :
A person wants to check for the one-way flights from Bengaluru to Lucknow for 5 passengers (3 Adults and 2 children) 
on Indigo portal for the current date. Automate the same using (https://www.goindigo.in/?linkNav=home_header ) portal.

Automation Steps :
1. Launch the browser with “https://www.goindigo.in/?linkNav=home_header ” 
2. Select “One way” in the option 
3. Fill all the details specified above 
4. Click on search button 
5. Wait for the page to load and list all the flights available


# CaseStudy_802.java (m5)

Description :
The candidate who has registered for edureka portal wants to update all the professional and personal details available in the portal.

Automation Steps :
1. Login to the edureka “https://www.edureka.co/”
2. Navigate to My Profile 
3. Update professional and personal details 
4. Explore the blogs and Navigate to Main page 
5. Logout of the portal


# CaseStudy_804.java (m6)

Description :
Log in to the edureka portal with the registered email id, and search for all the courses available and Logout of the edureka portal. 
Write testcases using TestNG for above scenario.

Automation Steps :
1. Launch the Chrome browser 
2. Log in to the account and browser for all the courses 
3. Logout of the account 
4. Use all the concepts learnt in TestNG (dependency, priority, etc) 
5. Create another test case to register the user with same email and an exception has to be shown on portal
6. Check for the HTML report generated


# CaseStudy_805.java (m7)

Description :
To search and check out a selenium book on flipkart website.

Automation Steps :
1. Open browser with Flipkart website “https://www.flipkart.com/”
2. Search for a Selenium book 
3. Check if it is delivered to the pin code specified and print the duration needed 
4. Add to the cart 
5. While checking out, give a random email id and password 
6. Capture the error message and capture the screen shot using TakeScreenshot.


# Test_Gmail.java (m8)

Description :
Automate the Gmail webpage to login, send a mail and logout using page object model testing of selenium, without Page factory. 
Repeat the same with page factory concept.

Automation Steps :
1. Login to the Gmail account 
2. Compose and send a mail 
3. Logout from Gmail


# POM_m9.java

Description :
Write an automation script to explore the blogs written in Edureka portal for Selenium interview question.  
Use the concept of framework mechanism in selenium to read the login credentials through excel page and update the status of 
the testcase to excel sheet back.

Automation Steps :
1. Launch browser and navigate to the Edureka portal “https://www.edureka.co/”
2. Log In with your credentials, reading the data from excel sheet (username, password) 
3. Open blogs in a new tab 
4. Filter for selenium blogs 
5. Navigate to interview questions in the blog 
6. Automate using Framework mechanism, create a generic framework for reading and writing data to excel


# POM_m10.java

Description :
Write a hybrid Test framework that can Login to edureka portal , select a course of your choice , checkout the course with all the valid address details and at the end of payment , cancel the product that was selected.

Automation Steps :
1. Create different Modules using POM for each action to be performed (LogIn, search course, Add the course, Checkout,Update the Address,Cancel before payment) 
2. Login to the edureka portal “https://www.edureka.co/”
3. Search for the course of your choice and add to the list 
4. Checkout at the end adding all the details and cancel just before payment.


# Upload_EdurekaPhoto.java (m11)

Description :
Create a maven project to upload your photo on edureka portal using maven frameworks. Configure the same with Jenkins to run the test every day.

Automation Steps :
1. Launch edureka portal on the browser. 
2. Login with the credentials 
3. Navigate to edit profile page 
4. Edit uploading photo button 
5. Integrate with jenkins to rerun the testcase daily


# HeadlessBrowsers.java (m12)

Description :
Use Selenium headless browser mechanism to Search for the Edureka portal in google search and capture the screenshot of the webpage.

Automation Steps :
1. Launch a headless Browser (phantomjs) 
2. Navigate to Google search 
3. Search for Edureka  
4. Check if the page title has been changed 
5. Capture the screenshot and save it in local 
6. Repeat the same using HTML Unit Driver


# Final Project
testCases.test1_CheckEnrolledCourses.java
testCases.test2_ExploreBlogs.java
testCases.test3_ExploreCourse.java
testCases.test4_UpdateProfile.java
pageObjects.EdurekaPF_Blog.java
pageObjects.EdurekaPF_Home.java
pageObjects.EdurekaPF_Login.java
utilityTools.ExcelReadWrite.java
utilityTools.Log.java
utilityTools.WebExplorer.java

Description :
Edureka wants to test their website that can be used by multiple QA experts globally. To test the website we would be using selenium WebDriver with Java. This portal provides an interface to take any instructor-led trainings.

Create a hybrid framework project using selenium and java to test the testcases. Use the concepts of hybrid framework ,Page Object Factory, Log4j to log the outputs, POI concept to read the data from an excel page.

Automation Steps:
Testing Edureka portal to check the enrolled courses for a user  
1. Login to the Portal 
2. Navigate to the My Course Page  
3. Validate if any courses have been enrolled for the user
4. Logout of the Portal

Testing Edureka Portal to list the blogs written on selenium 
1. Login to the portal 
2. Navigate to blogs page 
3. Refine the search for selenium blogs. 
4. Explore the blogs on interview questions 
5. Close all the blogs tab.

Testing Edureka Portal for selenium course 
1. Login to the portal 
2. Navigate to “All Course” page 
3. Refine the search for selenium 
4. Add the selenium course to wishlist.

Testing Edureka portal to update the user profile data 
1.  Login to the portal 
2. Navigate to My Profile 
3. Click on Edit Profile for the user 
4. Update all the Professional details 
5. Save the data and ensure that the data is updated. 
6. Close the browser


