package com.polarbookshop.orderservice.book;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
public class BookClient {
	public static final String BOOKS_ROOT_API = "/books/";
	public final WebClient webClient;

	public BookClient(WebClient webClient) {
		this.webClient = webClient;
	}

	public Mono<Book> getBookByIsbn(String isbn) {
		return webClient
						.get()
						.uri(BOOKS_ROOT_API + isbn)
						.retrieve()
						.bodyToMono(Book.class)
						.onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.empty());
	}
}
