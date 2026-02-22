# Lista de revisões ou ideis que avalio aplicar

## Criar um service e um controller para cada entidade

- (21/02/2026): como modelei Book como um aggregate root, pensei em fazer um service só, mas agora acho que isso pode
  deixar o BookService muito grande. Caso fique, avaliar quebrar em 3 services e sempre validar de acordo. a 5 dias
  atrás, quando li sobre aggregate root não entendi muito bem como seria a estruturação dos pacotes.

> chapterService com validações pelo id do livro

> noteService sendo avaliada pelo id do livro e id do capitulo