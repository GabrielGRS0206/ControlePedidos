# Projeto back-end com Spring Boot para Controle de Pedidos e Delivery

- Spring Framework
- Spring Boot
- Spring Data
- REST
- Swagger
- Mysql
- JDBC

O projeto consiste em uma API para controle de pedidos, desenvolvido totalmente com a tecnologia Java,
utilizando as tecnologias acima mencionadas.

O projeto possui cadastro de:
##### - Mercadoria (Products)
##### - Cliente (Customers)
##### - Rua (Street)
##### - Bairro (District)
##### - Cidade (City)

Para a parte de movimentação da API temos
##### - Pedido (Order)
##### - Caixa (CashRegister)

## Configurando o projeto

1) git clone ou download do zip: https://github.com/GabrielGRS0206/ControlePedidos

2) Importe o projeto em sua IDE de preferência

3) Altere o usuario e senha para que o projeto possa acessar o banco mysql. 
  * Vá até `/src/main/resources/application.properties`;
  * Altere as propriedades informado o usuário e senha do seu banco de dados: 
    - spring.datasource.username=usuario
    - spring.datasource.password=senha
    - spring.datasource.url = jdbc:mariadb://localhost:3306/seuBancoDeDados?useSSL=false
   * Na classe com.controle.spring.domain.utils.connection.configJdbc.ConnectionConfig altere 
   os dados:
   - this.username = usuario
   - this.password = senha 
   - this.driverClassName = org.mariadb.jdbc.Driver
   - this.url = bc:mariadb://localhost:3306/seuBancoDeDados?useSSL=false

4) Na classe App de um run na sua IDE.
5) Acesse: http://localhost:8080/swagger-ui.html para visualizar os endpoints




