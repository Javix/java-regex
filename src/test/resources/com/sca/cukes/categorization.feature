Feature: Parse Categorization values

  Scenario: Split categorization into groups
    Given a categorization with following groups:
      """
      [CATEGORIE]-My category,[MEDIA]-some media,[IMG_URL]-path/to/some/image.png
      """

    When I parse the categorization
    Then it should contain the following groups:
      |   [CATEGORIE]-My category           |
      |   [MEDIA]-some media                |
      |   [IMG_URL]-path/to/some/image.png  |

  Scenario Outline: Assign the matched group values
    Given the following "group"
    When it matches the "pattern"
    Then the "attribute" should have the "value" set up

    Examples:
    |     group            |   pattern                    |   attribute   |   value                 |
    | [META]-some meta data|   \\[\META\\]-?[a-zA-Z-\s/]+ |     meta      |   some meta data        |