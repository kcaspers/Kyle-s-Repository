import java.util.ArrayList;
import java.util.List;


public class Hand {

	private List<Card> handCards = new ArrayList<>();
	
	public Hand() {
		// TODO Auto-generated constructor stub
	}

	public List<Card> getCards() {
		return handCards;
	}

	public void setCards(List<Card> cards) {
		this.handCards = cards;
	}
	
	public void readHand(){
		for(Card c : handCards){
			System.out.println(c.getCardName());
		}
	}

}
