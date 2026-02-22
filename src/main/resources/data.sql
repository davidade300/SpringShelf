INSERT INTO books
(title, version, release_date, author, publisher, isbn10, isbn13, cover_img_url, description, last_updated_at)
VALUES ('Patterns of Enterprise Application Architecture', 1, '2002-11-15 00:00:00', 'Martin Fowler', 'Addison-Wesley',
        '0321127420', '9780321127426', 'https://example.com/poeaa.jpg',
        'Padrões arquiteturais para aplicações corporativas.', CURRENT_TIMESTAMP);

INSERT INTO books
(title, version, release_date, author, publisher, isbn10, isbn13, cover_img_url, description, last_updated_at)
VALUES ('Test Driven Development: By Example', 1, '2002-05-20 00:00:00', 'Kent Beck', 'Addison-Wesley',
        '0321146530', '9780321146533', 'https://example.com/tdd.jpg',
        'TDD na prática: Red, Green, Refactor.', CURRENT_TIMESTAMP);

INSERT INTO chapters
    (title, summary, book_id, created_at, last_updated_at)
VALUES ('Layered Architecture', 'Separação de responsabilidades em camadas.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO chapters
    (title, summary, book_id, created_at, last_updated_at)
VALUES ('Domain Model', 'Modelagem de domínio e comportamento rico.', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO chapters
    (title, summary, book_id, created_at, last_updated_at)
VALUES ('The TDD Cycle', 'Ciclo Red-Green-Refactor.', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO notes
    (title, content, created_at, last_updated_at, chapter_id)
VALUES ('Responsabilidades claras', 'Cada camada deve ter responsabilidades bem definidas.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO notes
    (title, content, created_at, last_updated_at, chapter_id)
VALUES ('Evitar lógica no controller', 'Controller orquestra; regra fica no domínio/service.', CURRENT_TIMESTAMP,
        CURRENT_TIMESTAMP, 1);

INSERT INTO notes
    (title, content, created_at, last_updated_at, chapter_id)
VALUES ('Entidades ricas', 'Domínio deve conter comportamento, não apenas dados.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        2);

INSERT INTO notes
    (title, content, created_at, last_updated_at, chapter_id)
VALUES ('Red-Green-Refactor', 'Escreva o teste falhando, faça passar, refatore.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
        3);