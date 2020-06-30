package problems.whiteBox;

public class CommissionTest {
    public static void main(String[] args) {
        //满足语句覆盖
        Commission commision = new Commission();
        System.out.println(commision.calculate(2800000, 5, 0.8));
        System.out.println(commision.calculate(1000000, 6, 0.5));
        System.out.println(commision.calculate(3600000, 12, 0.65));
        System.out.println(commision.calculate(5000000, 15, 0.9));
    }
}
