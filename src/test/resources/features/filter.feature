@Filter
Feature: Filter files by attribute

  @Local
  Scenario: Bob wants to find files by name
    Given Bob has created the LocalFileService
    When Bob invokes find on "./examples" with a regex filter of "tes1.txt$"
    Then the file list contains:
      | ./examples/folder/tes1.txt      |
      | ./examples/folder/deep/tes1.txt |
      | ./examples/folder2/tes1.txt     |

  @Local
  Scenario: Jill wants to find files in a specific folder by name
    Given Jill has created the LocalFileService
    When Jill invokes find on "./examples" with a regex filter of "[^/]+/folder2/[^\.]+\.txt$"
    Then the file list contains:
      | ./examples/folder2/tes1.txt     |