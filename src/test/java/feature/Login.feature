Feature: Application login

Scenario: Facebook valid login user 1
Given Enter the URL
When User logs in with username "abc" and password "bmc"
Then User enters into the facebook page
And Facebook image are displayed 


Scenario: Facebook valid login user 2
Given Enter the URL
When User logs in with username "xyz" and password "bmc"
Then User enters into the facebook page
And Facebook image are displayed