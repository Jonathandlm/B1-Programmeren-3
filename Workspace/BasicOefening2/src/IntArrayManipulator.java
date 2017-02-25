public class IntArrayManipulator {
    public static int GetSum(int[] array){
        int sum = 0;
        for (int n : array) {
            sum += n;
        }
        return sum;
    }

    public static int GetIndexOf(int[] array, int inputNumber){
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == inputNumber) index = i; break;
        }
        return index;
    }

    public static int GetGreatestNumber(int[] array){
        int greatest = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > greatest) greatest = array[i];
        }
        return greatest;
    }
}
