import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Deck {

	private List<Card> cards = new ArrayList<>();
	//List<Card> dealtCards = new ArrayList<>();
	Card[] dealtCards = new Card[52];
	//List<Integer> dealtCards = new ArrayList<>();
	
	public Deck() {
		
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
//	public void addCard(Card card){
//		cards.add(card);
//	}
	
	//deal hand
	public Hand dealHand(){
		Random rand = new Random();
		Hand dealtHand = new Hand();
		
		
		
		while(dealtHand.getCards().size() < 5){
			int cardIndex = rand.nextInt(52)+1;
			if(dealtCards[cardIndex] == null){
				dealtHand.getCards().add(cards.get(cardIndex));
				//dealtCards.add(cards.get(cardIndex));
				dealtCards[cardIndex] = cards.get(cardIndex);
			}
			
		}
		
		return dealtHand;
	}
	
	
}
