
public class Card {

	//each card has value 1-13
	private int value;
	private Suit suit;
	
	public Card(Suit suit, int value) {
		this.suit = suit;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Suit getSuit() {
		return suit;
	}

	public void setSuit(Suit suit) {
		this.suit = suit;
	}
	
	public String getCardName(){
		if(value == 1){
			return "Ace of " + suit;
		} else if(value == 10){
			return "Jack of " +  suit;
		} else if(value == 11){
			return "Queen of " + suit;
		} else if(value == 12){
			return "King of " + suit;
		}
		return value + " of " + suit;
	}
	
}
