Feature: API de Empregados

	Scenario: 1 - Obter lista de empregados com método correto
		Given realizo um "GET" para a url "http://dummy.restapiexample.com/api/v1/employees" com parametros
			| null |
		Then recebo uma lista de empregados com status code 200

	Scenario: 2 - Obter lista de empregados com método errado
		Given realizo um "POST" para a url "http://dummy.restapiexample.com/api/v1/employees" com parametros
			| id |
			|  1 |
		Then recebo status code 405

	Scenario: 3 - Obter empregado por id, passando id correto
		Given realizo um "GET" para a url 'http://dummy.restapiexample.com/api/v1/employee/{id}' com parametros
			| id |
			|  1 |
		Then recebo um empregado com id 1 e com status code 200

	Scenario: 4 - Obter empregado por id inexistente
		Given realizo um "GET" para a url 'http://dummy.restapiexample.com/api/v1/employee/{id}' com parametros
			| id     |
			| 999999 |
		Then recebo um empregado nulo e com status code 200

	Scenario: 5 - Obter empregado por id do tipo incorreto
		Given realizo um "GET" para a url 'http://dummy.restapiexample.com/api/v1/employee/{id}' com parametros
			| id   |
			| aaaa |
		Then recebo um empregado nulo e com status code 200

	Scenario: 6 - Criar empregado com metodo correto e parametros corretos
		Given realizo um "POST" para a url "http://dummy.restapiexample.com/api/v1/create" com parametros
			| name | salary | age |
			| test |    123 |  23 |
		Then recebo status code 200

	Scenario: 7 - Criar empregado com metodo errado
		Given realizo um "GET" para a url "http://dummy.restapiexample.com/api/v1/create" com parametros
			| null |
		Then recebo status code 404
