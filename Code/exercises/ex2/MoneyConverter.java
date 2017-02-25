package be.leerstad.javabasics.exercises.ex2;

public class MoneyConverter {

	/*
     * Create a class MoneyConverter that performs the following: Is constructed
	 * with an amount representing the change rate from one money to another one
	 * (for example : 1 Eur = 40.3399 BEF ) Take an amount in the first money
	 * and convert it to the second. Take an amount in the second money and
	 * convert it to the first.
	 */

    private final float changeRate;

    public MoneyConverter(final float changeRate) {
        this.changeRate = changeRate;

    }

    public float convertToNewMonetaryUnit(final float amount) {
        return (Math.round(amount / changeRate * 100)) / 100f;
    }

    public float convertToBaseMonetaryUnit(final float amount) {
        return Math.round(amount * changeRate * 100) / 100;
    }

}
