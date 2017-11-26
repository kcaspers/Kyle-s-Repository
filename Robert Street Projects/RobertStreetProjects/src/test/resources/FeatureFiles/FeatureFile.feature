Feature: the category can be retrieved
    Scenario: client tries to log in without credentials
        When the client clicks Sign In tab
        Then the client is redirected to the sign in page