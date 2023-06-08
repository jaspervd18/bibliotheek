package be.ewdj.bibliotheek.perform;

import org.springframework.web.reactive.function.client.WebClient;

import be.ewdj.bibliotheek.models.Boek;
import reactor.core.publisher.Mono;

public class ReactiveWebClient {

    private final String SERVER_URI = "http://localhost:8080/api/boeken/";
    private WebClient webClient = WebClient.create(SERVER_URI);

    public ReactiveWebClient() {
        getBookByIsbn("9789403139005");
        getBooksByAuthor("Rowling");

    }

    private void getBookByIsbn(String isbn) {
        webClient.get().uri(isbn).retrieve().bodyToMono(Boek.class).doOnSuccess(System.out::println).block();
    }

    private void getBooksByAuthor(String auteurNaam) {
        webClient.get()
                .uri("/auteur/{auteurNaam}", auteurNaam)
                .retrieve()
                .bodyToFlux(Boek.class)
                .flatMap(boek -> {
                    System.out.println(boek);
                    return Mono.empty();
                })
                .blockLast();
    }

}
