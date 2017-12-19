Feature: Admin Sign In/Out

Scenario: Admin signs in succesfully
    Given User is on RSP page
    When User navigates to sign in page
    And User enters correct admin name and password
    Then User is re-routed to main page with privleges

Scenario: Admin fails to sign in succesfully
    Given User is on RSP page
    When User navigates to sign in page
    And User enters incorrect sign in info
    Then The login page is redisplayed