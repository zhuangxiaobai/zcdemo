package com.example.zcdemo03.bean;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GraphTest3 {
    public static void main(String[] args) throws Exception {
        String templatePath = "C://Users/zc/Desktop/poi/line-chart-template.docx";

        InputStream is = new FileInputStream(new File(templatePath));
        XWPFDocument doc = new XWPFDocument(is);

        //模拟统计图数据
        //系列
        String[] seriesTitles = {"日处理能力(kg)", "湿垃圾(kg)", "干垃圾(kg)"};
        //x轴
        String[] categories = {"2020-02-20", "2020-02-21", "2020-02-22", "2020-02-23", "2020-02-24", "2020-02-25", "2020-02-26"};
        List<Number[]> values = new ArrayList<>();
        //日处理能力
        Number[] value1 = {1000, 1000, 1000, 1000, 1000, 1000, 1000};
        //湿垃圾
        Number[] value2 = {450.2, 622.1, 514, 384.7, 486.5, 688.9, 711.1};
        //干垃圾
        Number[] value3 = {200.2, 321.4, 266, 156.5, 232.2, 325.5, 319.5};

        values.add(value1);
        values.add(value2);
        values.add(value3);

        XWPFChart xChart = doc.getCharts().get(0);//获取第1个图表
        generateChart(xChart, seriesTitles, categories, values);

        try (FileOutputStream fos = new FileOutputStream("C://Users/zc/Desktop/poi/mytest.docx")) {
            doc.write(fos);
        }

    }

    public static void generateChart(XWPFChart chart, String[] series, String[] categories, List<Number[]> values) {
        String chartTitle = "收运量统计图";
        final List<XDDFChartData> data = chart.getChartSeries();//不知道这个ChartSeries代表什么意思
        final XDDFLineChartData line = (XDDFLineChartData) data.get(0);//这里一般获取第一个,我们这里是折线图就是XDDFLineChartData

        final int numOfPoints = categories.length;

        final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 0, 0));

        final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(categories, categoryDataRange, 0);
        for (int i = 0; i < values.size(); i++) {
            final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, i + 1, i + 1));
            Number[] value = values.get(i);
            final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(value, valuesDataRange, i + 1);
            XDDFChartData.Series ser;//图表中的系列
            ser = line.getSeries().get(i);
            ser.replaceData(categoriesData, valuesData);
            CellReference cellReference = chart.setSheetTitle(series[i], 1);//修改系列标题
            ser.setTitle(series[i], cellReference);
        }

        chart.plot(line);
        chart.setTitleText(chartTitle);//折线图标题
        chart.setTitleOverlay(false);
    }
}
