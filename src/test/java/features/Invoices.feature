Feature: This is feature file for invoices tests


  Background:
    Given I set content type to Json

    Scenario: Can get all invoices from the system
      When I execute GET request at URL: "/invoices"
      Then status code should be 200

      Scenario: Can create invoice for not existing client
        When I start building new Invoice