# SpringShelf

- Este projeto é baseado na aplicação de conceitos apresentados no "livro Domain Driven Design Quickly"
> Uma versão digital gratuita do livro pode ser encontrada em: https://www.infoq.com/minibooks/domain-driven-design-quickly/
 
> Este reposítorio é uma versão em Java, utilizando Spring do meu repositório
> book_queue: https://github.com/davidade300/book_queue

- A ideia é praticar Spring, pensando muito mais de forma arquitetural do que na sintaxe da coisa.

## Diferenças do SpringShelf para o book_queue

- A motivação ainda é a mesma, manter notas de livros que estudo.
- Aqui (até o momento 20/02/2026) estou seguindo DDD um pouco mais a risca, fazendo de Book um aggregate root

> no book_queue, as classes chapter e note são "soltas" e podem ser criadas/alteradas/removidas sem ter relacionamentos
> com o livros/ capitulos.

- O banco de dado também será postgresql.
- Aqui não estou seguindo TDD pois não tenho muita familiaridade com Junit ainda.