package excelOperation;



public class ExcelTest {
    public static void main(String[] args){
       ReadExcel test=new ReadExcel();
       System.out.println(test.getNumberOfRow("src/main/resources/calendarTestCases.xlsx"));
    }
}
