Feature: User can delete link

    Scenario: user can delete existing podcast by name
        Given command delete is selected
        And type "podcast" is selected
        When existing podcast "Urheilucast" is selected
        And delete of item number 1 is selected and confirmed
        Then podcast is deleted

    Scenario: user can cancel delete of existing podcast
        Given command delete is selected
        And type "podcast" is selected
        When existing podcast "Urheilucast" is selected
        And delete of item number 1 is selected and not confirmed
        Then podcast is not deleted

    Scenario: user can't delete non-existing podcast
        Given command delete is selected
        And type "podcast" is selected
        When nonexisting podcast "Aristoteleen kantapää" is selected
        Then search has no results
        And podcast is not deleted
