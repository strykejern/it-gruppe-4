package system;

/**
 *
 * @author Anders
 */
public class WaiterFetchedOrder extends FetchedOrder{
    public WaiterFetchedOrder(FetchedOrder original){
        super(original);
    }

    @Override
    public String toString(){
        if (true) return super.toString();
        return "Orderinfo + customer name";
    }
}
