package testExecution;

import excelOperation.ReadExcel;
import problems.whiteBox.Commission;


public class WhiteBoxTest {
    public final String filePath="src/main/resources/whiteBoxTestCases.xlsx";
    public void executeOne(int number){
        //获取参数
        int para1=Integer.parseInt(ReadExcel.getDataCell(filePath,2,number+1));
        int para2=Integer.parseInt(ReadExcel.getDataCell(filePath,3,number+1));
        double para3=Double.parseDouble(ReadExcel.getDataCell(filePath,4,number+1));
        //执行测试用例
        Commission commission = new Commission();
        String result=""+commission.calculate(para1,para2,para3);
        //写入测试用例执行结果
        try{
            ReadExcel.setDataCell(result,filePath,number+1,6);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //判断测试是否通过
        String str="";
        if(result.equals(ReadExcel.getDataCell(filePath,5,number+1))){
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

    /**
     * 执行三角形问题所有测试用例并写入结果
     */
    public void executeAll(){
        //获取测试用例数量
        int number=ReadExcel.getNumberOfRow(this.filePath);
        //循环执行所有测试用例
        for(int i=1;i<number;i++){
            executeOne(i);
        }
    }
}
