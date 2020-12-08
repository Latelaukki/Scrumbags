Feature: User can delete book

    Scenario: user can delete existing book by name
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
        And book is not deleted

    Scenario: user can't delete nonexisting book by name
        Given command delete is selected
        And type "book" is selected
        And search attribute "book name" is selected
        When an invalid name "Wrong Name" is entered
        Then search has no results
        And book is not deleted

    Scenario: user can delete existing book by publishing year
        Given command delete is selected
        And type "book" is selected
        And search attribute "publishing year" is selected
        When a valid year "2015" is entered
        And delete of item number 1 is selected and confirmed
        Then book is deleted

    Scenario: user can't delete nonexisting book by publishing year
        Given command delete is selected
        And type "book" is selected
        And search attribute "publishing year" is selected
        When a non-existing year "5102" is entered
        Then search has no results
        And book is not deleted

    Scenario: user can delete existing book by author
        Given command delete is selected
        And type "book" is selected
        And search attribute "author" is selected
        When a valid author "David R Greenland" is entered
        And delete of item number 1 is selected and confirmed
        Then book is deleted

    Scenario: user can't delete nonexisting book by author
        Given command delete is selected
        And type "book" is selected
        And search attribute "author" is selected
        When an invalid author "Some Other Greenland" is entered
        Then search has no results
        And book is not deleted

    Scenario: user can delete existing book by isbn
        Given command delete is selected
        And type "book" is selected
        And search attribute "ISBN" is selected
        When a valid isbn "9781593935412" is entered
        And delete of item number 1 is selected and confirmed
        Then book is deleted

    Scenario: user can't delete nonexisting book by isbn
        Given command delete is selected
        And type "book" is selected
        And search attribute "author" is selected
        When  an invalid isbn "0000000000" is entered
        Then  search has no results
        And book is not deleted