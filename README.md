# Aplicação de Autoatendimento Autotem FastFood

## Visão Geral
Autotem é uma aplicação de autoatendimento projetada para uma lanchonete FastFood para modernizar seu sistema de pedidos. Ela permite que os clientes façam pedidos de forma autônoma, liberando os caixas para outras tarefas.

Interface de usuário e conexão com o banco de dados não está atualmente implementada.

## Classes

### AutotemApplication
O ponto de entrada principal para a aplicação.

### AutotemApplicationTests
Contém testes para o contexto da aplicação.

### Produto
Representa um produto no sistema, seja uma bebida ou um lanche. Inclui propriedades como ID, nome, preço e tipo (BEBIDA ou LANCHE).

### ProdutoController
Manipula as solicitações HTTP relacionadas aos produtos, incluindo a criação, atualização, exclusão e recuperação de produtos.

### ProdutoDTO
Um Objeto de Transferência de Dados usado para transferir dados de produto entre diferentes camadas da aplicação.

### ProdutoRepository
Uma interface que define o repositório para acessar os produtos no banco de dados.

### ProdutoService
Fornece serviços relacionados aos produtos, incluindo a listagem de todos os produtos, obtenção de um produto por ID, criação, atualização e exclusão de produtos.

### TipoProduto
Uma enumeração representando o tipo de produto, seja BEBIDA ou LANCHE.

## Relacionamentos
- `Produto` tem um relacionamento um-para-um com `TipoProduto`.
- `ProdutoController` cria `Produto` e `ProdutoDTO` e tem um relacionamento um-para-um com `ProdutoService`.
- `ProdutoService` tem um relacionamento um-para-um com `ProdutoRepository`.
