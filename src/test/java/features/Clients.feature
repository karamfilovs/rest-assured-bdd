Feature: All tests for Clients resource

  Scenario Outline: Can create new client
    When I start building new Client
    And I set client firm_name "<firm_name>"
    And I set client city "<city>"
    And I set client address "<address>"
    And I set client vat registration to "<isVATCompany>"
    And I set client mol to "<mol>"
    And I set the current client as body
    And I execute POST request at URL: "/client"
    Then status code should be 200
    When I store the id from the last response at path "$.success.id"
    And I get the last created entity at path "/client"
    Then status code should be 200
    When I delete the last created entity at path "/client"
    Then status code should be 200
    And message found at path "$.success.message" should be "<message>"


    Examples:
      | firm_name       | city  | address              | isVATCompany | mol   | message          |
      | Pragmatic2      | Sofia | Ivan Stranski street | true         | Mutlu | Клиента е изтрит |
      | Pragmatic123456 | A     | #$%^@#!              | true         | лео   | Клиента е изтрит |


  Scenario Outline: Can't create new client with invalid data
    When I start building new Client
    And I set client firm_name "<firm_name>"
    And I set client city "<city>"
    And I set client address "<address>"
    And I set client vat registration to "<isVATCompany>"
    And I set client mol to "<mol>"
    And I set the current client as body
    And I execute POST request at URL: "/client"
    Then status code should be 400
    And value found at path "$.error.message" should be "<message>"
    And value found at path "$.error.description" should be "<description>"

    Examples:
      | firm_name       | city  | address              | isVATCompany | mol   | message     | description              |
      |                 | Sofia | Ivan Stranski street | true         | Mutlu | Bad Request | Липсващо име на фирмата! |
      | Pragmatic123456 |       | #$%^@#!              | true         | лео   | Bad Request | Липсващ град на фирмата! |
      | Pragmatic123456 |       |                      | true         | лео   | Bad Request | Липсващ град на фирмата! |
#      | Pragmatic123456 | Sofia | #$%^@#!              |              | лео   | Bad Request | mol missing |
      | Pragmatic123456 | Sofia | #$%^@#!              | true         |       | Bad Request | Липсващ МОЛ на фирмата!  |