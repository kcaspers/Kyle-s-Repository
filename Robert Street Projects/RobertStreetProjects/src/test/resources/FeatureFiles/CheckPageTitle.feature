Feature: Check page title

Background: User is on RSP page
    Given User is on RSP page

Scenario: Navigate to login page
    When I navigate to "loginBtn"
    Then I check page title is "Log In"
    And I close the browser 

Scenario: Navigate to static page
    When I click the about dropdown
    When I navigate to "aboutPage"
    Then I check page title is "Static Page"
    And I close the browser 

Scenario: Navigate to Facebook from icon
    When I navigate to "facebookLink"
    Then I check page title is "Facebook - Log In or Sign Up"
    And I close the browser 

Scenario: Navigate to LinkedIn from icon
    When I navigate to "linkedinLink"
    Then I check page title is "LinkedIn"
    And I close the browser 