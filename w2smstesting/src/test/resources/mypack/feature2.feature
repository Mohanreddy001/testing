Feature:Logout
Scenorio:validate logout operation
Given launch site using "chrome"
When do login with valid data
|mbno      |pwd     |
|7382794414|mohan123|
And do logout
Then home page will be reopened
And close site