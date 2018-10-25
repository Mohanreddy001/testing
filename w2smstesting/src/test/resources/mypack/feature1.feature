Feature: Login
Scenorio:validate site launching
Given launch site using "chrome"
Then title contains "Free SMS"
And close site

Scenario Outline: validate login operation
Given launch site using "chrome"
When enter mobile number as "<x>"
And enter password as "<y>"
And click login
Then validate output for critiria "<z>"
And close site
Examples:
|     x       |       y     |     z         |
| 7382794414  |   mohan123  | all_valid     |
|             |   mohan123  | mbno_blank    |
| 7382794414  |             | pwd_blank     |
| 7382794415  |   mohan123  | mbno_invalid  |
| 7382794414  |   mohan224  | pwd_invalid   | 
