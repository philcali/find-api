@Local
Feature: Find files using a FileService

  This method will find all files in a provided directory

  Scenario: Bob can create the LocalFileService
    When Bob creates the LocalFileService
    Then Bob can interact with a FileService

  Scenario: Jill can list all files in the specific directory
    Given Jill has created the LocalFileService
    When Jill invokes find on "./examples"
    Then the file list contains:
      | ./examples/folder/tes1.txt      |
      | ./examples/folder/tes2.txt      |
      | ./examples/folder/deep/tes1.txt |
      | ./examples/folder2/tes1.txt     |

  Scenario: Jane can list all files in a deeper directory
    Given Jane creates the LocalFileService
    When Jane invokes find on "./examples/folder2"
    Then the file list contains:
      | ./examples/folder2/tes1.txt     |