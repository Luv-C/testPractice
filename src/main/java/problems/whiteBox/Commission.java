package problems.whiteBox;

public class Commission {
    public double calculate(int sales, int absence, double percent){
        if(sales>2000000 && absence<= 10 && percent>=0.6){
            return sales/7;
        }
        else if (percent<0.6){
            return 0;
        }
        else if(percent<=0.85){
            return sales/6;
        }
        else{
            return sales/5;
        }
    }
}
