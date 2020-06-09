package problems.commission;

public class CommissionTest {
    public static void main(String[] args){
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculate(0,0,0));
        System.out.println(calculate.calculate(10,20,30));
        System.out.println(calculate.calculate(70,80,90));
    }
}
