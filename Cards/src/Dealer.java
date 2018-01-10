
public class Dealer {

	public Dealer() {
		// TODO Auto-generated constructor stub
	}

	public static Deck generateDeck(){
		Deck newDeck = new Deck();
		for(int i = 0; i < 4; i++){
			//4 suits
			
			for(int j = 1; j <= 13; j++){
				if(i == 0){
					Card newCard = new Card(Suit.CLUBS, j);
					//newDeck.addCard(newCard);
					newDeck.getCards().add(newCard);
				}else if(i == 1){
					Card newCard = new Card(Suit.DIAMONDS, j);
					//newDeck.addCard(newCard);
					newDeck.getCards().add(newCard);
				}else if(i == 2){
					Card newCard = new Card(Suit.HEARTS, j);
					//newDeck.addCard(newCard);
					newDeck.getCards().add(newCard);
				} else if(i ==3){
					Card newCard = new Card(Suit.SPADES, j);
					//newDeck.addCard(newCard);
					newDeck.getCards().add(newCard);
				}
			}
		}
		
		return newDeck;
	}
}
