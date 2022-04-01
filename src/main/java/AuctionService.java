import java.util.ArrayList;

public class AuctionService {
    public ArrayList<AuctionItem> items;

    public AuctionService(ArrayList<AuctionItem> items) {
        this.items = items;
    }


    public String placeBid(AuctionItem item, String bidType) {
        int bid;

        if (item.sold) {
            return "Item already sold";
        }

        try {
            bid = Integer.parseInt(bidType);
        } catch (Exception e) {
            return "Your bid should be an integer value.";
        }

        ArrayList<Integer> price = item.price;
        if (bid > item.getPrice()) {
            price.add(bid);
            return "Your bid is placed.";
        } else if (bid == item.getPrice())
            return "You can't bid the same price.";
        else
            return "Your bid is lower than last bid.";
    }

    public String removeLastBid(AuctionItem item) {
        ArrayList<Integer> price = item.price;
        if (price.size() == 1) {
            return "This is the starting bid, you cannot remove this.";
        } else {
            price.remove(price.size() - 1);
            return "Last bid removed.";
        }
    }

    public String addItem(AuctionItem item) {
        if (items.size() >= 5) {
            return "There are limited amount of seats for items";
        } else if (items.contains(item)) {
            return "This item is already being sold";
        } else if (item.getPrice() < 1000) {
            return "Min amount for the item is 1000";
        }
        items.add(item);
        return "Item added to the auction";
    }

    public String removeItem(AuctionItem item) {
        if (!items.contains(item))
            return "This item is not included in the item list";
        else if (item.sold)
            return "You cannot retrieve sold item";
        items.remove(item);
        return "Item successfully removed";
    }

    public String sellItem(AuctionItem sellItem) {
        if (sellItem.sold) {
            return "Item was already sold";
        }

        sellItem.sold = true;
        return "Item sold!";
    }
}