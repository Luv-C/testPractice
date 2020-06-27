package UI;

import excelOperation.ReadExcel;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import testExecution.CalendarBeforeTest;
import testExecution.CommissionTest;
import testExecution.TriangleTest;
import testExecution.CalendarTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PC
 */
public class MainApp extends Application {
    final Button button = new Button ("执行");
    final Label notification = new Label ();
    final TextField subject = new TextField("");
    final TextArea text = new TextArea ("");
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("ComboBoxSample");

        //建立条形图
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("测试用例通过与否");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("数量");

        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

        //选择框1--选择问题类型
        final ComboBox<String> problemComboBox = new ComboBox<String>();
        problemComboBox.getItems().addAll(
                "三角形问题",
                "佣金问题",
                "万年历问题",
                "万年历问题（bug.ver）"
        );
        //选择框2--选择待执行的用例
        final ComboBox<String> testCaseComboBox = new ComboBox<String>();

        problemComboBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if(problemComboBox.getSelectionModel().getSelectedIndex()==0){
                    String filePath = "src/main/resources/triangleTestCases.xlsx";
                    int number = ReadExcel.getNumberOfRow(filePath);
                    getTestCaseComboBox(testCaseComboBox,number);
                    barChart.setTitle("三角形问题测试");
                }
                else if(problemComboBox.getSelectionModel().getSelectedIndex()==1){
                    String filePath = "src/main/resources/commissionTestCases.xlsx";
                    int number = ReadExcel.getNumberOfRow(filePath);
                    getTestCaseComboBox(testCaseComboBox,number);
                    barChart.setTitle("佣金问题测试");
                }
                else if(problemComboBox.getSelectionModel().getSelectedIndex()==2){
                    String filePath = "src/main/resources/calendarTestCases.xlsx";
                    int number = ReadExcel.getNumberOfRow(filePath);
                    getTestCaseComboBox(testCaseComboBox,number);
                    barChart.setTitle("万年历问题测试");
                }
                else if (problemComboBox.getSelectionModel().getSelectedIndex()==3){
                    String filePath = "src/main/resources/calendarTestCases.xlsx";
                    int number = ReadExcel.getNumberOfRow(filePath);
                    getTestCaseComboBox(testCaseComboBox,number);
                    barChart.setTitle("万年历问题(bug.ver)测试");
                }
            }
        });


        //执行按钮处理
        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
               int index=problemComboBox.getSelectionModel().getSelectedIndex();
               if(index==0){
                   //处理三角形问题
                   TriangleTest triangleTest = new TriangleTest();
                   String filePath = "src/main/resources/triangleTestCases.xlsx";
                   int number = ReadExcel.getNumberOfRow(filePath);
                   if(testCaseComboBox.getSelectionModel().getSelectedIndex()==0){
                       triangleTest.executeAll();
                       changeBarChart(number,barChart,filePath,1);
                   }
                   else {
                       number=testCaseComboBox.getSelectionModel().getSelectedIndex();
                       triangleTest.executeTestCases(number);
                       changeBarChart(number,barChart,filePath,2);
                   }
               }
               else if(index == 1){
                   //处理佣金问题
                   CommissionTest commissionTest = new CommissionTest();
                   String filePath = "src/main/resources/commissionTestCases.xlsx";
                   int number = ReadExcel.getNumberOfRow(filePath);
                   if(testCaseComboBox.getSelectionModel().getSelectedIndex()==0){
                       commissionTest.executeAll();
                       changeBarChart(number,barChart,filePath,1);
                   }
                   else {
                       number=testCaseComboBox.getSelectionModel().getSelectedIndex();
                       commissionTest.executeOne(number);
                       changeBarChart(number,barChart,filePath,2);
                   }
               }
               else if(index==2){
                   //处理万年历问题
                   CalendarTest calendarTest = new CalendarTest();
                   String filePath = "src/main/resources/calendarTestCases.xlsx";
                   int number = ReadExcel.getNumberOfRow(filePath);
                   if(testCaseComboBox.getSelectionModel().getSelectedIndex()==0){
                       calendarTest.executeAll();
                       changeBarChart(number,barChart,filePath,1);
                   }
                   else {
                       number=testCaseComboBox.getSelectionModel().getSelectedIndex();
                       calendarTest.executeOne(number);
                       changeBarChart(number,barChart,filePath,2);
                   }

               } else if (index == 3) {
                   //处理万年历bug版本问题
                   CalendarBeforeTest calendarBeforeTest = new CalendarBeforeTest();
                   String filePath = "src/main/resources/calendarTestCases.xlsx";
                   int number = ReadExcel.getNumberOfRow(filePath);
                   if(testCaseComboBox.getSelectionModel().getSelectedIndex()==0){
                       calendarBeforeTest.executeAll();
                       changeBarChart(number,barChart,filePath,1);
                   }
                   else {
                       number=testCaseComboBox.getSelectionModel().getSelectedIndex();
                       calendarBeforeTest.executeOne(number);
                       changeBarChart(number,barChart,filePath,2);
                   }

               }
            }
        });

        //显示元素排版
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("测试对象"), 0, 0);
        grid.add(problemComboBox, 1, 0);
        grid.add(new Label("执行测试用例编号"), 2, 0);
        grid.add(testCaseComboBox, 3, 0);
        grid.add(barChart, 0, 2, 4, 1);
        grid.add(button, 0, 3);
//        grid.add (notification, 1, 3, 3, 1);

        VBox vBox = new VBox(grid);

        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    //根据对应的问题获取选择框2中的内容
    public void getTestCaseComboBox(ComboBox testCaseComboBox,int number){
        List<String> items = new ArrayList<>();
        items.add("All");
        for(int i=1;i<number;i++){
            items.add(String.valueOf(i));
        }
        testCaseComboBox.setItems(FXCollections.observableArrayList(items));
    }

    //修改条形图内容函数
    private void changeBarChart(int number, BarChart bc,String filePath,int type) {
        int numberOfPass=0;
        int numberOfWait=0;
        int numberOfFail=0;
        if(type == 1) {
            for (int i = 2; i <= number; i++) {
                if ("pass".equals(ReadExcel.getDataCell(filePath, 7, i))) {
                    numberOfPass++;
                } else if ("fail".equals(ReadExcel.getDataCell(filePath, 7, i))) {
                    numberOfFail++;
                } else {
                    numberOfWait++;
                }
            }
        }
        else if(type==2){
            if ("pass".equals(ReadExcel.getDataCell(filePath, 7, number+1))) {
                numberOfPass++;
            } else if ("fail".equals(ReadExcel.getDataCell(filePath, 7, number+1))) {
                numberOfFail++;
            } else {
                numberOfWait++;
            }
        }
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("pass");
        dataSeries1.getData().add(new XYChart.Data<String, Number>("pass", numberOfPass));

        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
        dataSeries2.setName("fail");
        dataSeries2.getData().add(new XYChart.Data<String, Number>("fail", numberOfFail));

        XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<String, Number>();
        dataSeries3.setName("wait");
        dataSeries3.getData().add(new XYChart.Data<String, Number>("wait", numberOfWait));

        ObservableList<XYChart.Series> barChartDataUpdate = FXCollections.observableArrayList(
                dataSeries1,
                dataSeries2,
                dataSeries3
        );

        bc.getData().setAll(barChartDataUpdate);
        //System.out.println(numberOfPass+";"+numberOfFail+";"+numberOfWait);
    }
}
