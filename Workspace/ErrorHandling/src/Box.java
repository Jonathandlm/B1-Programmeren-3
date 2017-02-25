public class Box {
    private String name;
    private double weight;
    private boolean isFilled;
    private Item content;

    public Box(String name, double weight){
        this.name = name;
        this.weight = weight;
        this.isFilled = false;
    }

    public Box(String name){
        this(name, 5);
    }

    public void PlaceItem(Item item) throws NotEmptyException {
        if (!isFilled){
            content = item;
            isFilled = true;
        }
        else{
            throw new NotEmptyException("This box already has an item!");
        }
    }

    public void RetrieveItem() throws EmptyException {
        if (isFilled){
            content = null;
            isFilled = false;
        }
        else{
            throw new EmptyException("This box is already empty!");
        }
    }

    public double GetWeight(){
        return weight + (isFilled ? content.GetWeight() : 0);
    }
}
