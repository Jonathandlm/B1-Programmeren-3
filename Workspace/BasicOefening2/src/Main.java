public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("TEST");
        System.out.println("----");
        System.out.println("1) Test MoneyConverter");
        MoneyConverter EurToBEF = new MoneyConverter(40.3399);
        System.out.format("100€ moet 4033.99BEF zijn: %f%n",EurToBEF.ConvertMoney(100));
        System.out.format("403.399BEF moet 10€ zijn: %f%n",EurToBEF.ConvertMoneyBack(403.399));
        System.out.format("1€ moet na terugconverteren terug 1€ zijn: %f%n",EurToBEF.ConvertMoneyBack(EurToBEF.ConvertMoney(1)));
        System.out.println("----");
        System.out.println("2) Test Employee");
        Employee Jan = new Employee("Jan", "Janssens");
        Employee Eric = new Employee("Eric", "Ericson");
        System.out.println(Jan.toString());
        System.out.println(Eric.toString());
        System.out.println("----");
        System.out.println("3) Test Box");
        Item televisie = new Item("Samsung", 15);
        System.out.format("Televisie moet 15 wegen: %f%n",televisie.GetWeight());
        Box standaardDoos = new Box("Medium");
        System.out.format("standaardDoos moet 5 wegen: %f%n",standaardDoos.GetWeight());
        Box groteDoos = new Box("Large", 10);
        System.out.format("groteDoos moet 10 wegen: %f%n",groteDoos.GetWeight());
        groteDoos.PlaceItem(televisie);
        System.out.format("groteDoos moet nu 25 wegen: %f%n",groteDoos.GetWeight());
        groteDoos.RetrieveItem();
        System.out.format("groteDoos moet nu terug 10 wegen: %f%n",groteDoos.GetWeight());
        System.out.println("Foutmelding: reeds leeg");
        groteDoos.RetrieveItem();
        groteDoos.PlaceItem(televisie);
        System.out.println("Foutmelding: reeds gevuld");
        groteDoos.PlaceItem(televisie);
        System.out.println("----");
    }
}
