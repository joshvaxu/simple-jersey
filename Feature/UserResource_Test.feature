Feature: Testing User Resource API
  Users should be able to submit GET and POST requests to a web service, to save or get user resource. 

	Scenario: Successful Create an User.
		When users input user info
		Then the server should handle it and return a success status

	Scenario: Successful get an user.
		Given there a an user named Jony Washton
		When users search with name Jony Washton
		Then the server return an user info named Jony Washton
	 