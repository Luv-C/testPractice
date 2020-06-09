package testExecution;

import excelOperation.ReadExcel;
import problems.commission.Calculate;

public class CommissionTest {
    public final String filePath="src/main/resources/commissionTestCases.xlsx";

    //执行单个测试用例
    public void executeOne(int number){
        //获取测试用例参数
        int param1=Integer.parseInt(ReadExcel.getDataCell(filePath,2,number+1));
        int param2=Integer.parseInt(ReadExcel.getDataCell(filePath,3,number+1));
        int param3=Integer.parseInt(ReadExcel.getDataCell(filePath,4,number+1));
        //建立Calculate实例并执行该测试用例
        Calculate calculate = new Calculate();
        String commission = ""+calculate.calculate(param1,param2,param3);
        //写入测试结果
        try{
            ReadExcel.setDataCell(commission,this.filePath,number+1,6);
        }catch (Exception e){
            e.printStackTrace();
        }
        //判断用例是否通过
        String str="";
        if(commission.equals(ReadExcel.getDataCell(filePath,5,number+1))){
            str = "pass";
        }
        else{
            str = "fail";
        }
        try {
            ReadExcel.setDataCell(str,filePath,number+1,7);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //执行所有测试用例
    public void executeAll(){
        int number = ReadExcel.getNumberOfRow(this.filePath);
        for(int i=1;i<number;i++){
            executeOne(i);
        }
    }
}
