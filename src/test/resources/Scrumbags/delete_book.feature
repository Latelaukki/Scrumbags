Feature: User can delete books

    Scenario: user can delete existing book
        Given command delete is selected
        And type "book" is selected
        And search attribute "book name" is selected
        When a valid name "Bonanza" is entered
        And delete of item number 1 is selected and confirmed
        Then book is deleted

    Scenario: user can cancel delete of existing book
        Given command delete is selected
        And type "book" is selected
        And search attribute "book name" is selected
        When a valid name "Bonanza" is entered
        And delete of item number 1 is selected and not confirmed
        Then book is not deleted