Feature: Documentation links

Scenario Outline: To validate all internal links on the documentation page
	Given user is already on developer documentation page <URL>
	Then validate all internal links and AngularJS initialized
	

	
	Examples: 
		|URL										|
		|https://developer.here.com/documentation	|
		