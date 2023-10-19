Feature: Login related scenarios

  Background:
    #Given user is navigated to application

  @regression @smoke @login
  Scenario: Valid admin login
   #Given user is navigated to application
    When user enters valid admin username and password
    And user clicks on login button
    Then user is successfully logged in the application


  @invalid  @sprint1 @login
  Scenario: invalid admin login
    #Given user is navigated to application
    When user enters invalid admin username and password
    And user clicks on login button
    Then error message is displayed

  @negative
  Scenario Outline: negative login test
    When user enters "<username>" and "<password>" and verifying the "<error>" for the combinations
    Examples:
      | username | password    | error                    |
      | admin    | fkfkkkj     | Invalid credentials      |
      | admin1   | Pspsp123sps | Invalid credentials      |
      |          | Pspsp123sps | Username cannot be empty |
      | admin    |             | Password cannot be empty |