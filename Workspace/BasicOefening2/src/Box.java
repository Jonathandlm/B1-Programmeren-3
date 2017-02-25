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

    public void PlaceItem(Item item){
        if (!isFilled){
            content = item;
            isFilled = true;
        }
        else{
            System.out.println("This box already has an item!");
        }
    }

    public void RetrieveItem(){
        if (isFilled){
            content = null;
            isFilled = false;
        }
        else{
            System.out.println("This box is already empty!");
        }
    }

    public double GetWeight(){
        return weight + (isFilled ? content.GetWeight() : 0);
    }
}
