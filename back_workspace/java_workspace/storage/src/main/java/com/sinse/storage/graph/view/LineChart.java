package com.sinse.storage.graph.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CombinedDomainXYPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class LineChart extends JPanel{
	
	public LineChart() {
		final JFreeChart chart = createLineChart();
		final ChartPanel panel = new ChartPanel(chart, true, true, true, false, true);
		panel.setPreferredSize(new Dimension(500, 270));
		this.setLayout(new BorderLayout());
		this.add(panel);
	}
	
	private JFreeChart createLineChart() {
		//create subplot 1
		XYDataset data1 = createDataset1();
		XYItemRenderer render1 = new StandardXYItemRenderer();
		NumberAxis rangeAxis1 = new NumberAxis("Range 1");
		XYPlot subplot1 = new XYPlot(data1, null, rangeAxis1, render1);
		
		XYTextAnnotation annotation = new XYTextAnnotation("Hello", 50.0, 10000.0);
		annotation.setFont(new Font("SansSerif", Font.PLAIN, 9));
		annotation.setRotationAngle(Math.PI/4.0);
		subplot1.addAnnotation(annotation);
		
		//create subplot 2
		XYDataset data2 = createDataset2();
		XYItemRenderer render2 = new StandardXYItemRenderer();
		NumberAxis rangeAxis2 = new NumberAxis("Range 2");
		rangeAxis2.setAutoRangeIncludesZero(false);
		XYPlot subplot2 = new XYPlot(data2, null, rangeAxis2, render2);
		subplot2.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
		
		//parent plot
		CombinedDomainXYPlot plot = new CombinedDomainXYPlot(new NumberAxis("Domain"));
		plot.setGap(10.0);
		
		//add the subplots
		plot.add(subplot1, 1);
		plot.add(subplot2, 1);
		plot.setOrientation(PlotOrientation.VERTICAL);
		
		 return new JFreeChart("Line Chart Demo", JFreeChart.DEFAULT_TITLE_FONT, plot, true);
	}
	
	private XYDataset createDataset1() {
		final XYSeries series1 = new XYSeries("Series 1");
        series1.add(10.0, 12353.3);
        series1.add(20.0, 13734.4);
        series1.add(30.0, 14525.3);
        series1.add(40.0, 13984.3);
        series1.add(50.0, 12999.4);
        series1.add(60.0, 14274.3);
        series1.add(70.0, 15943.5);
        series1.add(80.0, 14845.3);
        series1.add(90.0, 14645.4);
        series1.add(100.0, 16234.6);
        series1.add(110.0, 17232.3);
        series1.add(120.0, 14232.2);
        series1.add(130.0, 13102.2);
        series1.add(140.0, 14230.2);
        series1.add(150.0, 11235.2);

        final XYSeries series2 = new XYSeries("Series 2");
        series2.add(10.0, 15000.3);
        series2.add(20.0, 11000.4);
        series2.add(30.0, 17000.3);
        series2.add(40.0, 15000.3);
        series2.add(50.0, 14000.4);
        series2.add(60.0, 12000.3);
        series2.add(70.0, 11000.5);
        series2.add(80.0, 12000.3);
        series2.add(90.0, 13000.4);
        series2.add(100.0, 12000.6);
        series2.add(110.0, 13000.3);
        series2.add(120.0, 17000.2);
        series2.add(130.0, 18000.2);
        series2.add(140.0, 16000.2);
        series2.add(150.0, 17000.2);

        final XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series1);
        collection.addSeries(series2);
        return collection;
	}
	
	private XYDataset createDataset2() {
		final XYSeries series2 = new XYSeries("Series 3");

        series2.add(10.0, 16853.2);
        series2.add(20.0, 19642.3);
        series2.add(30.0, 18253.5);
        series2.add(40.0, 15352.3);
        series2.add(50.0, 13532.0);
        series2.add(100.0, 12635.3);
        series2.add(110.0, 13998.2);
        series2.add(120.0, 11943.2);
        series2.add(130.0, 16943.9);
        series2.add(140.0, 17843.2);
        series2.add(150.0, 16495.3);
        series2.add(160.0, 17943.6);
        series2.add(170.0, 18500.7);
        series2.add(180.0, 19595.9);

        return new XYSeriesCollection(series2);
	}
}
