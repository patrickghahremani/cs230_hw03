import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AuctionServiceTest {
    AuctionService service;
    AuctionItem item1 = new AuctionItem("American Gothic", 1000);
    AuctionItem item2 = new AuctionItem("Girl with a Pearl Earring", 1500);
    AuctionItem item3 = new AuctionItem("Ship on Stormy Seas", 2000);
    AuctionItem item4 = new AuctionItem("Mona Lisa", 3000);

    @BeforeEach
    void init(){

        ArrayList<AuctionItem> items = new ArrayList<AuctionItem>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        service = new AuctionService(items);
        item2.sold = true;
        service.placeBid(item3, "2500");

    }

    @Test
    void wrongInput(){
        assertEquals("Your bid should be an integer value.", service.placeBid(item1,"abc"));
    }

    @Test
    void placedBid(){
        assertEquals("Your bid is placed.", service.placeBid(item1,"1001"));
    }

    @Test
    void sameBid(){
        assertEquals("You can't bid the same price.", service.placeBid(item1,"1000"));
    }

    @Test
    void lowBid(){
        assertEquals("Your bid is lower than last bid.", service.placeBid(item1,"99"));
    }

    @Test
    void soldItem(){
        assertEquals("Item already sold", service.placeBid(item2,"12313"));
    }

    @Test
    void removedBid(){
        assertEquals("Last bid removed.", service.removeLastBid(item3));
    }

    @Test
    void cantRemoveBid(){
        assertEquals("This is the starting bid, you cannot remove this.", service.removeLastBid(item4));
    }

    @Test
    void addItemMoreThan5(){
        AuctionItem item5 = new AuctionItem("Random pic", 4500);
        service.addItem(item5);

        AuctionItem item =  new AuctionItem("test", 1000);
        assertEquals("There are limited amount of seats for items", service.addItem(item));
    }

    @Test
    void addItemPriceLessThanMin(){
        AuctionItem item =  new AuctionItem("test", 100);
        assertEquals("Min amount for the item is 1000", service.addItem(item));
    }

    @Test
    void addItemMoreThatExists(){
        assertEquals("This item is already being sold", service.addItem(item1));
    }

    @Test
    void addItemSuccess(){
        AuctionItem item =  new AuctionItem("test", 1000);
        assertEquals("Item added to the auction", service.addItem(item));
    }

    @Test
    void removeItemThatIsNotThere(){
        AuctionItem item =  new AuctionItem("test", 1000);
        assertEquals("This item is not included in the item list", service.removeItem(item));
    }

    @Test
    void removeItemThatIsSold(){
        assertEquals("You cannot retrieve sold item", service.removeItem(item2));
    }

    @Test
    void removeSuccess(){
        assertEquals("Item successfully removed", service.removeItem(item1));
    }

    @Test
    void sellSold(){
        assertEquals("Item was already sold", service.sellItem(item2));
    }

    @Test
    void sellSuccessful(){
        assertEquals("Item sold!", service.sellItem(item1));
    }


}