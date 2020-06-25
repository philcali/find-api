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

  @Local @Glob
  Scenario: Bob wants to find files glob style
    Given Bob has created the LocalFileService
    When Bob invokes find on "./examples" with a glob filter of "**/tes1.txt"
    Then the file list contains:
      | ./examples/folder/tes1.txt      |
      | ./examples/folder/deep/tes1.txt |
      | ./examples/folder2/tes1.txt     |

  @Local @Glob
  Scenario: Jill wants to find files glob style
    Given Jill has created the LocalFileService
    When Jill invokes find on "./examples" with a glob filter of "*/folde?/*.txt"
    Then the file list contains:
      | ./examples/folder/tes1.txt      |
      | ./examples/folder/tes2.txt      |

  @Local
  Scenario: Jane wants to find files by name at a specific depth
    Given Jane has created the LocalFileService
    When Jane invokes find on "./examples" with a regex filter of "[^/]+/folder\d?/tes\d.txt$"
    Then the file list contains:
      | ./examples/folder/tes1.txt      |
      | ./examples/folder/tes2.txt      |
      | ./examples/folder2/tes1.txt     |

  @Local
  Scenario: Jill wants to find files in a specific folder by name
    Given Jill has created the LocalFileService
    When Jill invokes find on "./examples" with a regex filter of "[^/]+/folder2/[^\.]+\.txt$"
    Then the file list contains:
      | ./examples/folder2/tes1.txt     |