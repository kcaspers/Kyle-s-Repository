
public class App {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dealer pokerDealer = new Dealer();
		Deck newDeck = pokerDealer.generateDeck();
		
		Hand hand1 = newDeck.dealHand();
		Hand hand2 = newDeck.dealHand();
		Hand hand3 = newDeck.dealHand();
		Hand hand4 = newDeck.dealHand();
		
		hand1.readHand();
	}

	
}
