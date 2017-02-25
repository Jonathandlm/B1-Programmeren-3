public class MoneyConverter {

    private double exchangeRate;

    public MoneyConverter(double exchangeRate){
        this.exchangeRate = exchangeRate;
    }

    public double ConvertMoney(double amount){
        return amount * exchangeRate;
    }

    public double ConvertMoneyBack(double amount){
        return amount / exchangeRate;
    }
}
