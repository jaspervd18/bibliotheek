package be.ewdj.bibliotheek.perform

import org.springframework.web.reactive.function.client.WebClient;

import be.oliviergilquin.ewdj.book.Book;
import reactor.core.publisher.Mono;

public class ReactiveWebClient {

    private final String SERVER_URI = "http://localhost:8080/api/books/";
    private WebClient webClient = WebClient.create(SERVER_URI);

  public PerformRestBook() {
    getBookByIsbn("isbn16");
  }

    private void getBookByIsbn(String isbn) {
        webClient.get().uri(isbn).retrieve().bodyToFlux(Book.class).flatMap(book -> {
            System.out.println(book);
            return Mono.empty();
        }).blockLast();
    }

}
