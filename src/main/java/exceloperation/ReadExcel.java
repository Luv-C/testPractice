package exceloperation;

/**
 * @author PC
 */

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

    /**
     * 读取文件中某个单元格的内容
     * @param filePath 表格文件路径
     * @param column 列号 从1开始
     * @param row 行号 从1开始
     * @return 以字符串形式返回单元格内容
     */
    public static String getDataCell(String filePath,int column, int row){
        Workbook wb= readExcel(filePath);
        //默认该单元格在文件中第一个sheet内
        Sheet sheet=wb.getSheetAt(0);
        int rowNum=sheet.getPhysicalNumberOfRows();
        Row row1 = null;
        String result=null;
        //先读行的内容
        row1=sheet.getRow(row-1);
        if(row1!=null){
            //再读列的内容
            result=(String) getCellFormatValue(row1.getCell(column-1));
        }
        return result;

    }

    /**
     * 向文件中特定单元格中写入内容
     * @param data 需要写入的数据 为字符串格式
     * @param filePath 文件路径
     * @param row 行号 从1开始
     * @param col 列号 从1开始
     * @throws FileNotFoundException
     */
    public static void setDataCell(String data,String filePath,int row,int col) throws FileNotFoundException {
        Workbook wb =readExcel(filePath);
        Sheet sheet=wb.getSheetAt(0);
        Row row1 = sheet.getRow(row - 1);
        Cell cell = row1.getCell(col - 1);
        if(cell==null){
            //如果该单元格中没有内容，则需要先创建该单元格
            cell=row1.createCell(col-1);
        }
        cell.setCellValue(data);

        try {
            //流式写入
            File file=new File(filePath);
            FileOutputStream fos =new FileOutputStream(file);
            wb.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取文件工作簿
     * @param filePath 文件路径
     * @return 工作簿
     */
    public static Workbook readExcel(String filePath){
        Workbook wb = null;
        if(filePath==null){
            return null;
        }
        //获取文件后缀名
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is = null;
        //根据不同文件后缀，调用不同方法来获取工作簿
        try {
            is = new FileInputStream(filePath);
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else{
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }

    /**
     *
     * @param filePath 文件路径
     * @return 表格行数
     */
    public static int getNumberOfRow(String filePath){
        Workbook wb =ReadExcel.readExcel(filePath);
        Sheet sheet=wb.getSheetAt(0);
        return sheet.getPhysicalNumberOfRows();
    }

    /**
     *
     * @param cell 已读取的单元格
     * @return 格式化后的内容
     */
    public static Object getCellFormatValue(Cell cell){
        Object cellValue = null;
        if(cell!=null){
            //判断cell类型
            switch(cell.getCellType()){
                case NUMERIC:{
                    //将数值型cell设置为string型
                    cell.setCellType(CellType.STRING);
                    cellValue = cell.getStringCellValue();
                    break;
                }
                case FORMULA:{
                    //判断cell是否为日期格式
                    if(DateUtil.isCellDateFormatted(cell)){
                        //转换为日期格式YYYY-mm-dd
                        cellValue = cell.getDateCellValue();
                    }else{
                        //数字
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING:{
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        }else{
            cellValue = "";
        }
        return cellValue;
    }
}
