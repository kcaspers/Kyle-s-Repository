Feature: test low impedance warning

    Scenario: A total impedance less than or equal to 2 ohm should diplay a warning
    Given I open the speaker impedance app
    When I add two 4 ohm cabinets
    Then the impedance is low enough to display the warning messgage
    
