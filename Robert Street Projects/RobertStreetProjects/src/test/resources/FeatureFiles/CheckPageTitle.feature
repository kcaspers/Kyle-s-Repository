Feature: Check page title

Scenario: Navigate to login page
    Given I am on RSP site
    When I navigate to Sign In
    Then I check page title is Login
    And I close the browser 

Scenario: Navigate to blank search
    Given I am on RSP site
    When I search for nothing
    Then I check page title is blank search result
    And I close the browser 

Scenario: Navigate to search art
    Given I am on RSP site
    When I search for art
    Then I check page title is search art
    And I close the browser 

Scenario: Navigate to LinkedIn from icon
    Given I am on RSP site
    When I click on LinkedIn icon
    Then I check page title is LinkedIn
    And I close the browser 