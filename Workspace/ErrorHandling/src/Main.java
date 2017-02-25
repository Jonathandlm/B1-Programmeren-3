public class Main {

    public static void main(String[] args) {
	// write your code here
        Box smallBox = new Box("small box", 5);
        Item smallItem = new Item("small item", 2);

        try {
            smallBox.RetrieveItem();
        } catch (EmptyException e){
            System.out.println(e);
        }

        try {
            smallBox.PlaceItem(smallItem);
            smallBox.PlaceItem(smallItem);
        } catch (NotEmptyException e){
            System.out.println(e);
        }

        // CALCULATOR
        Calculator calculator = new Calculator();

        try {
            calculator.divide(1,0);
        } catch (IllegalArgumentException e){
            System.out.println(e);
        }
    }
}
