Feature: Test Happy Flow

  @Testing
  Scenario: Test Happy Flow
    Given we go the the url
    Then we see lorem ipsum text
    When we click on about
    Then the about page title is displayed