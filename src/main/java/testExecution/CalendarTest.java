package testExecution;

import excelOperation.ReadExcel;
import problems.calendar.NextDay;

public class CalendarTest {
    public final String filePath="src/main/resources/calendarTestCases.xlsx";

    //执行单个测试案例
    public void executeOne(int number){
        //获取年月日
        int param1=Integer.parseInt(ReadExcel.getDataCell(filePath,2,number+1));
        int param2=Integer.parseInt(ReadExcel.getDataCell(filePath,3,number+1));
        int param3=Integer.parseInt(ReadExcel.getDataCell(filePath,4,number+1));
        //调用NextDay中方法获取下一天的日期
        NextDay calendar = new NextDay();
        String date=calendar.getNextDay(param1,param2,param3);
        //将日期写回文件中
        try {
            ReadExcel.setDataCell(date,filePath,number+1,6);
        }catch (Exception e){
            e.printStackTrace();
        }
        //判断是否通过测试
        String str="";
        if(date.equals(ReadExcel.getDataCell(filePath,5,number+1))){
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

    //执行全部测试案例
    public void executeAll(){
        int number = ReadExcel.getNumberOfRow(this.filePath);
        for(int i= 1;i<number;i++){
            executeOne(i);
        }
    }
}
