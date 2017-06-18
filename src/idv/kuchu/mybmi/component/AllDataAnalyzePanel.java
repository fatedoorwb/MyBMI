package idv.kuchu.mybmi.component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import idv.kuchu.mybmi.MainScreen;

public class AllDataAnalyzePanel extends Panel {
	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 432) / 2 - 8;
	int Y = 32;

	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;

	public AllDataAnalyzePanel() {

		JButton BMI = new JButton("BMI");
		BMI.setFont(font);
		BMI.setBounds(BX - 116, Y, 200, 64);

		JButton BodyFat = new JButton("體脂");
		BodyFat.setFont(font);
		BodyFat.setBounds(BX + 116, Y, 200, 64);

		// 圖
		CategoryDataset dataset = setDataset();
		JFreeChart chart = createChart(dataset);

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setBounds(X, Y + 100, 432, 200);

		JButton back = new JButton("返回");
		back.setFont(font);
		back.setBounds(BX, BY, 200, 64);
		back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().disposeF();
			}

		});

		this.add(BMI);
		this.add(BodyFat);

		this.add(back);

		this.add(chartPanel);

	}

	private CategoryDataset setDataset() {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String[] title = new String[] { "推測", "紀錄" };

		int[] v = new int[] { -1, -1, -1, -1, -1, -1, -1 };
		v[0] = 21;
		v[6] = 15;

		for (int i = 0; i < v.length; i++) {
			if (v[i] == -1) {

			}
		}

		dataset.addValue(21, "紀錄", "0531");// <輸入
		dataset.addValue(21, "紀錄", "0601");
		dataset.addValue(20.5, "紀錄", "0602");
		dataset.addValue(20, "紀錄", "0603");
		dataset.addValue(19.5, "紀錄", "0604");
		dataset.addValue(20, "紀錄", "0605");
		dataset.addValue(20.5, "紀錄", "0606");// <輸入

		dataset.addValue(21, "推測", "0531");
		dataset.addValue(21, "推測", "0601");
		dataset.addValue(20.5, "推測", "0602");
		dataset.addValue(20, "推測", "0603");
		dataset.addValue(19.5, "推測", "0604");
		dataset.addValue(20, "推測", "0605");
		dataset.addValue(20.5, "推測", "0606");

		return dataset;
	}

	private int interpolation(int i, int[] v) {
		if (v.length < 1)
			return 0;
		if (v[0] == -1 || v[v.length - 1] == -1)
			return 0;
		if (i <= 0 || i >= v.length - 1)
			return 0;

		int start = v[i - 1];
		int end = 0;
		for (int j = i; j < v.length; j++) {

		}
	}

	private JFreeChart createChart(final CategoryDataset dataset) {
		JFreeChart chart = ChartFactory.createLineChart("BMI統計", "日期", "BMI", dataset, PlotOrientation.VERTICAL, true,
				true, false);

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		numberaxis.setLowerBound(14);

		/*------設置X軸座標上的文字-----------*/
		domainAxis.setTickLabelFont(new Font("微軟正黑體", Font.PLAIN, 14));

		/*------設置X軸的標題文字------------*/
		domainAxis.setLabelFont(new Font("微軟正黑體", Font.BOLD, 16));

		/*------設置Y軸座標上的文字-----------*/
		numberaxis.setTickLabelFont(new Font("微軟正黑體", Font.PLAIN, 14));

		/*------設置Y軸的標題文字------------*/
		numberaxis.setLabelFont(new Font("微軟正黑體", Font.BOLD, 14));
		chart.getLegend().setItemFont(new Font("標楷體", Font.PLAIN, 14));
		chart.getTitle().setFont(new Font("微軟正黑體", Font.BOLD, 16));
		return chart;
	}

}
