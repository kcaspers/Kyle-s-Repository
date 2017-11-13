package com.flashcards.flashcards;

import com.flashcards.flashcards.model.Card;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class FlashcardsApplication {
    
	public static void main(String[] args) {
		SpringApplication.run(FlashcardsApplication.class, args);
                
                Card newCard = new Card();
                newCard.setQuestion("This is the question.");
                newCard.setAnswer("This is the answer.");
                
	}
}
