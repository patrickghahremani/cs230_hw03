import java.util.ArrayList;

public class AuctionItem {
    ArrayList<Integer> price = new ArrayList<Integer>();
    String itemName;
    boolean sold;

    public AuctionItem(String itemName, int initialPrice) {
        this.itemName = itemName;
        price.add(initialPrice);
    }

    public int getPrice(){
        return price.get(price.size() - 1);
    }
}
