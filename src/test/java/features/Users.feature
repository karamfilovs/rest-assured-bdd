Feature: Test for INV.bg website

  Background:
    Given I set content type to Json

  Scenario: Get all invoices from the system
    When I execute GET request at URL: "/invoices"
    Then status code should be 200


  Scenario: Can get all items from the system
    When I execute GET request at URL: "/items"
    Then status code should be 200


  Scenario: Can get a specific item
    When I execute GET request at URL: "/item/2954"
    Then status code should be 200


  Scenario: Can get all clients
    When I execute GET request at URL: "/clients"
    Then status code should be 200

    Scenario Outline: Can create new client
      When I start building new Client
      And I set client firm_name "Pragmatic"
      And I set the current client as body
      And I execute POST request at URL: "/client"
      Then status code should be 400

      Examples:
      |firm_name|  |


  Scenario: Can create/get/delete new item
    When I start building new Item
    And I set item name "Alex"
    And I set item price_for_quantity "5"
    And I set item quantity_unit "кг"
    And I set the current item as body
    And I execute POST request at URL: "/item"
    Then status code should be 200
    When I store the id from the last response at path "$.success.id"
    And I get the last created entity at path "/item"
    Then status code should be 200
    When I delete the last created entity at path "/item"
    Then status code should be 200

  Scenario: Can create/get/delete new client
    When I start building new Client
    And I set client firm_name "Кафе12371"
    And I set the current client as body
    And I execute POST request at URL: "/client"
    Then status code should be 200
    When I store the id from the last response at path "$.success.id"
    And I get the last created entity at path "/client"
    Then status code should be 200
    When I delete the last created entity at path "/client"
    Then status code should be 200