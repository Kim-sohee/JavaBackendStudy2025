package com.sinse.storage.graph.view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPosition;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.CategoryLabelWidthType;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.text.TextBlockAnchor;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class HorizentalBarChart extends JPanel{
	
	public HorizentalBarChart(String title) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		dataset.addValue(23.0, "Series 1", "London");
		dataset.addValue(14.0, "Series 1", "New York");
        dataset.addValue(14.0, "Series 1", "Istanbul");
        dataset.addValue(14.0, "Series 1", "Cairo");
        dataset.addValue(13.0, "Series 2", "London");
        dataset.addValue(19.0, "Series 2", "New York");
        dataset.addValue(19.0, "Series 2", "Istanbul");
        dataset.addValue(19.0, "Series 2", "Cairo");
        dataset.addValue(7.0, "Series 3", "London");
        dataset.addValue(9.0, "Series 3", "New York");
        dataset.addValue(9.0, "Series 3", "Istanbul");
        dataset.addValue(9.0, "Series 3", "Cairo");
        
        JFreeChart chart = createChart(dataset);
        
        //add the chart to a panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300,270));
        this.setLayout(new BorderLayout());
        this.add(chartPanel);
	}
	
	private JFreeChart createChart(CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createBarChart(
				"Bar Chart Demo", "Category", "Value", 
				dataset, PlotOrientation.HORIZONTAL, true, true, false);
		
		CategoryPlot plot = chart.getCategoryPlot();
		plot.setForegroundAlpha(1.0f);
		
		CategoryAxis axis = plot.getDomainAxis();
		CategoryLabelPositions p = axis.getCategoryLabelPositions();
		
		CategoryLabelPosition left = new CategoryLabelPosition(
				RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT,
				TextAnchor.CENTER_LEFT, 0.0,
				CategoryLabelWidthType.RANGE, 0.30f);
		axis.setCategoryLabelPositions(CategoryLabelPositions.replaceLeftPosition(p, left));
		
		return chart;
	}
}
