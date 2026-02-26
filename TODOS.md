# Lista com o que falta/ tem de ser feito.

## Services para:

- Chapter
- Note

> todos os services vão usar o BookRepository, com todas as operações sendo delegadas para Book e Chapter, respeitando
> o padrão de aggregate root.

## Controllers para:

- Chapter
- Note

## ChapterController:

rever query do ChapterUpdate:

>Hibernate: select
b1_0.id,b1_0.author,b1_0.cover_img_url,b1_0.description,b1_0.isbn10,b1_0.isbn13,b1_0.last_updated_at,b1_0.publisher,b1_0.release_date,b1_0.title,b1_0.version
from books b1_0 where b1_0.id=?
Hibernate: select c1_0.book_id,c1_0.id,c1_0.created_at,c1_0.last_updated_at,c1_0.summary,c1_0.title from chapters c1_0
where c1_0.book_id=?