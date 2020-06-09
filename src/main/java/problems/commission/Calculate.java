package problems.commission;

public class Calculate {

    //hostNum为主机数量，screenNum为屏幕数量，deviceNum为外设数量
    //函数返回一个float类型的一位小数

    public float calculate(int hostNum,int screenNum,int deviceNum){
        final int hostPrice = 25;
        final int screenPrice = 30;
        final int devicePrice = 45;

        int sales = 0;
        float commission = 0.0f;
        //首先判断是否完整销售完一台机器
        if(hostNum>0&&screenNum>0&&deviceNum>0){
            //判断输入有没有超过库存
            if(hostNum>70){
                System.out.println("销售主机数量超过上限");
                return commission;
            }
            else if(screenNum>80){
                System.out.println("销售屏幕数量超过上限");
                return commission;
            }
            else if(deviceNum>90){
                System.out.println("销售外设数量超过上限");
                return commission;
            }
            else {
                //计算销售额和佣金
                sales = hostPrice*hostNum + screenPrice*screenNum + devicePrice*deviceNum;
                commission=sales*getPercent(sales);
                return commission;
            }

        }
        else{
            System.out.println("至少销售一台完整的机器");
            return commission;
        }
    }

    //根据销售额计算提成佣金的比例
    public float getPercent(int sales){
        if(sales<=1000){
            return (float)0.1;
        }
        else if(sales<=1800){
            return (float)0.15;
        }
        else return (float)0.2;
    }
}
