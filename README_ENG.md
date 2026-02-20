# SpringShelf
- Im basing this project on the reading of Domain Driven Design Quickly.
> This repository is a Java version, using Spring, of my repository
> book_queue: https://github.com/davidade300/book_queue

- The idea is to practice Spring, thinking much more architecturally than about syntax.

## Differences between SpringShelf and book_queue

- The motivation is still the same: keeping notes on books I study.
- Here (as of 02/20/2026) I am following DDD a bit more strictly, making Book an aggregate root.

> In book_queue, the chapter and note classes are "loose" and can be created/changed/removed without having relationships
> with books/chapters.

- The database will also be postgres.
- I am not following TDD here as I'm not very familiar with JUnit yet.