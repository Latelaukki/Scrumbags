Feature: User can delete link

    Scenario: user can delete existing link by name
        Given command delete is selected
        And type "link" is selected
        When existing link "Google" is entered
        And delete of item number 1 is selected and confirmed
        Then link is deleted

    Scenario: user can cancel delete of existing link
        Given command delete is selected
        And type "link" is selected
        When existing link "Google" is entered
        And delete of item number 1 is selected and not confirmed
        Then link is not deleted

    Scenario: user can't delete nonexisting link
        Given command delete is selected
        And type "link" is selected
        When nonexisting link "pölyhuisku" is entered
        Then search has no results
        And link is not deleted

    Scenario: user can cancel deleting a link
        Given command delete is selected
        And type "link" is selected
        When existing link "Google" is entered
        And delete of item number 1 is selected and not confirmed
        Then link is not deleted