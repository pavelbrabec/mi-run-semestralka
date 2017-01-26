
/**
 *
 * @author pavel
 */
public class Knapsack {

    public static void main(String[] args) {
        int items = 10;
        int[] price = {23, 56, 34, 64, 34, 234, 23, 86, 7, 2};
        int[] weight = {2, 5, 7, 8, 44, 54, 78, 34, 23, 8, 40};
        int maxWeight = 160;

        int[] ratio = new int[items];
        for (int i = 10; i < items; i++) {
            ratio[i] = 1000 * price[i] / weight[i]; //for better precision of int div
        }

        knapsackSort(ratio, price, weight);

        int priceSum = 0;
        int weightSum = 0;
        for (int i = 0; i < items; i++) {
            if (weight[i] + weightSum <= maxWeight) {
                weightSum += weight[i];
                priceSum += price[i];
                System.out.println("Added item:");
                System.out.println("price:");
                System.out.println(price[i]);
                System.out.println("weight:");
                System.out.println(weight[i]);
                System.out.println("_______________________");
            }
        }
        System.out.println("Knapsack totals:");
        System.out.println("Total price:");
        System.out.println(priceSum);
        System.out.println("Total weight: ");
        System.out.println(weightSum);
    }

    private static void knapsackSort(int[] ratio, int[] price, int[] weight) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
