package UI;

import exceloperation.ReadExcel;
import javafx.application.Application;
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
import testExecution.TriangleTest;

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

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("测试用例通过与否");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("数量");

        // Create a BarChart
        BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);

//        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
//        dataSeries1.setName("pass");
//        dataSeries1.getData().add(new XYChart.Data<String, Number>("testCase", 13));
//
//        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
//        dataSeries2.setName("fail");
//        dataSeries2.getData().add(new XYChart.Data<String, Number>("testCase", 0));
//
//        XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<String, Number>();
//        dataSeries3.setName("wait");
//        dataSeries3.getData().add(new XYChart.Data<String, Number>("testCase", 0));

        // Add Series to BarChart.

        barChart.setTitle("一些编程语言");


        button.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                TriangleTest triangleTest = new TriangleTest();
                triangleTest.executeAll();
                String filePath="src/main/resources/triangleTestCases.xlsx";
                int number= ReadExcel.getNumberOfRow(filePath);
                changeBarChart(number,barChart,filePath);
            }
        });

        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setHgap(10);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(new Label("测试对象"), 0, 0);
        grid.add(new Label("三角形问题"), 1, 0);
        grid.add(new Label("执行测试用例编号"), 2, 0);
        grid.add(new Label("All"), 3, 0);
        grid.add(barChart, 0, 2, 4, 1);
        grid.add(button, 0, 3);
//        grid.add (notification, 1, 3, 3, 1);




        VBox vBox = new VBox(grid);

        Scene scene = new Scene(vBox, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void changeBarChart(int number, BarChart bc,String filePath) {
        int numberOfPass=0;
        int numberOfWait=0;
        int numberOfFail=0;
        for(int i=2;i<=number;i++){
            if("pass".equals(ReadExcel.getDataCell(filePath,7,i)))
            {
                numberOfPass++;
            }
            else if("fail".equals(ReadExcel.getDataCell(filePath,7,i))){
                numberOfFail++;
            }
            else {
                numberOfWait++;
            }
        }
        XYChart.Series<String, Number> dataSeries1 = new XYChart.Series<String, Number>();
        dataSeries1.setName("pass");
        dataSeries1.getData().add(new XYChart.Data<String, Number>("testCase", numberOfPass));

        XYChart.Series<String, Number> dataSeries2 = new XYChart.Series<String, Number>();
        dataSeries2.setName("fail");
        dataSeries2.getData().add(new XYChart.Data<String, Number>("testCase", numberOfFail));

        XYChart.Series<String, Number> dataSeries3 = new XYChart.Series<String, Number>();
        dataSeries3.setName("wait");
        dataSeries3.getData().add(new XYChart.Data<String, Number>("testCase", numberOfWait));

        ObservableList<XYChart.Series> barChartDataUpdate = FXCollections.observableArrayList(
                dataSeries1,
                dataSeries2,
                dataSeries3
        );

        bc.getData().setAll(barChartDataUpdate);
        System.out.println(numberOfPass+";"+numberOfFail+";"+numberOfWait);
    }
}
