package com.sinse.storage.graph.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xml.DatasetReader;

public class CircleChart extends JPanel{
	
	public CircleChart(String title) {
		PieDataset dataset = null;
		final URL url = getClass().getResource("/data/piedata.xml");
		
		try {
			final InputStream in = url.openStream();
			dataset = DatasetReader.readPieDatasetFromXML(in);
			
			if (dataset == null) {
			    System.out.println("PieDataset을 XML에서 읽어오지 못했습니다.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//create the chart
		final JFreeChart chart = ChartFactory.createPieChart("Pie Chart", dataset, true, true, false);
		chart.setBackgroundPaint(Color.yellow);
		final PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} = {2}", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance()));

		plot.setNoDataMessage("No data available");
		
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(500, 270));
		this.setLayout(new BorderLayout());
		this.add(chartPanel);
		
	}
}
