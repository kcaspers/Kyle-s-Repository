Feature: app makes speakers, calculates impedance and output

    Scenario: we click the add speaker button
    Given we open the speaker app
    When we open the add speaker module
    When we click the add speaker button
    Then the speaker is made to our specifications