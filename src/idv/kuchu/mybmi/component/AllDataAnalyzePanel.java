package idv.kuchu.mybmi.component;

import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.json.JSONException;
import org.json.JSONObject;

import idv.kuchu.mybmi.MainScreen;
import idv.kuchu.mybmi.data.DataManager;
import idv.kuchu.mybmi.data.DateObject;

public class AllDataAnalyzePanel extends Panel {

	public static float interpolation(float start, float end, float d) {
		return start + (end - start) * d;
	}

	Font font = new Font("微軟正黑體", Font.BOLD, 28);

	int X = ((MainScreen.SCREEN_W - 16) - 432) / 2 - 8;
	int Y = 32;

	int BX = ((MainScreen.SCREEN_W - 16) - 200) / 2 - 8;
	int BY = ((MainScreen.SCREEN_H - 38) - 64) - 50;

	int year;
	int month;
	int day;

	boolean tag = true;

	public AllDataAnalyzePanel(int type) {
		super();
		MainScreen.log("Load AllDataAnalyze Panel...");
		JButton BMI = new JButton("BMI");
		BMI.setFont(font);
		BMI.setBounds(BX - 116, Y, 200, 64);
		BMI.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().disposeF();
				MainScreen.getInstance().addF(new AllDataAnalyzePanel(1));
			}

		});

		JButton BodyFat = new JButton("體脂");
		BodyFat.setFont(font);
		BodyFat.setBounds(BX + 116, Y, 200, 64);
		BodyFat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainScreen.getInstance().disposeF();
				MainScreen.getInstance().addF(new AllDataAnalyzePanel(2));
			}

		});

		Map<String, DateObject> objects = DataManager.instance.getDateObjects();
		year = DateObject.getNowYear();
		month = DateObject.getNowMonth();
		day = DateObject.getNowDay();
		if (!objects.containsKey(getDate(year, month, day, 0))) {
			JOptionPane.showMessageDialog(null, "您今天的資料還沒新增");
			tag = false;
			return;
		}
		// 圖
		if (type > 0) {
			List<Integer> lv = new ArrayList<Integer>();
			try {
				JSONObject user = DataManager.instance.getUserData();
				float[] v = new float[] { -1, -1, -1, -1, -1, -1, -1 };
				{
					String end = "";
					for (int i = 0; i < 7; i++) {
						String date = getDate(year, month, day, i - 6);
						if (objects.containsKey(date)) {
							DateObject object = objects.get(date);
							if (type == 1) {
								v[i] = DataAnalyzePanel.BMI(object.height, object.weight);
							} else {
								v[i] = DataAnalyzePanel.BF(user.getInt("gender"),
										Integer.valueOf(date.substring(0, 4)) - user.getInt("year"), object.height,
										object.weight);
							}
						}
					}
					if (v[0] == -1) {
						lv.add(0);
						Iterator<Entry<String, DateObject>> iterator = objects.entrySet().iterator();
						Entry<String, DateObject> entry;
						String date = getDate(year, month, day, -6);
						while (iterator.hasNext()) {
							entry = iterator.next();
							if (DateObject.DateGap(entry.getKey(), date) > 0) {
								if (end.length() != 8) {
									end = entry.getKey();
								} else if (DateObject.DateGap(entry.getKey(), end) == -1) {
									end = entry.getKey();
								}
							}
						}
						if (end.length() == 8) {
							DateObject object = objects.get(end);
							if (type == 1) {
								v[0] = DataAnalyzePanel.BMI(object.height, object.weight);
							} else if (type == 2) {
								v[0] = DataAnalyzePanel.BF(user.getInt("gender"),
										Integer.valueOf(end.substring(0, 4)) - user.getInt("year"), object.height,
										object.weight);
							}
						}
					}
				}
				CategoryDataset dataset = setDataset(v, lv);
				JFreeChart chart = createChart(dataset, (type == 1 ? "BMI" : (type == 2 ? "體脂" : "???")),
						(type == 2 ? "(%)" : ""), type);
				ChartPanel chartPanel = new ChartPanel(chart);
				chartPanel.setBounds(X, Y + 100, 432, 200);

				this.add(chartPanel);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		// -----

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
		MainScreen.log("Load AllDataAnalyze Panel Finish.");
	}

	private CategoryDataset setDataset(float[] v, List<Integer> lv) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		String[] title = new String[] { "推測", "紀錄" };

		if (lv == null)
			lv = new ArrayList<Integer>();

		String[] s = new String[7];

		for (int i = 0; i < 7; i++) {
			if (v[i] == -1) {
				v[i] = interpolation(i, v);
				lv.add(i);
			}
			s[i] = getDate(year, month, day, i - 6).substring(4, 8);

			dataset.addValue(v[i], title[1], s[i]);
			if (lv.contains(i))
				dataset.addValue(v[i], title[0], s[i]);
		}

		return dataset;
	}

	private String getDate(int year, int month, int day, int d) {
		int[] cd;
		cd = DateObject.getCD(year);
		while (d != 0) {
			if (d < 0) {
				day--;
				d++;
			} else {
				day++;
				d--;
			}
			while (day <= 0) {
				month--;
				while (month <= 0) {
					year--;
					cd = DateObject.getCD(year);
				}
				day = cd[month - 1];
			}
			while (day > cd[month - 1]) {
				month++;
				while (month > 12) {
					year++;
					cd = DateObject.getCD(year);
				}
				day = cd[month - 1];
			}
		}
		return DateObject.getDate(year, month, day);
	}

	private float interpolation(int i, float[] v) {
		if (v.length < 1)
			return 0;
		if (v[0] == -1 || v[v.length - 1] == -1)
			return 0;
		if (i <= 0 || i >= v.length - 1)
			return 0;

		float start = v[i - 1];
		int istart = i - 1;
		float end = 0;
		int iend = 0;
		for (int j = i + 1; j < v.length; j++) {
			if (v[j] != -1) {
				end = v[j];
				iend = j;
				break;
			}
		}
		return ((int) (AllDataAnalyzePanel.interpolation(start, end, 1 / ((float) (iend - istart))) * 100))
				/ 100f;
	}

	private JFreeChart createChart(final CategoryDataset dataset, String Title, String ex, int type) {
		JFreeChart chart = ChartFactory.createLineChart(Title + "統計", "日期", Title + ex, dataset,
				PlotOrientation.VERTICAL, true, true, false);

		CategoryPlot categoryplot = (CategoryPlot) chart.getPlot();
		CategoryAxis domainAxis = categoryplot.getDomainAxis();
		NumberAxis numberaxis = (NumberAxis) categoryplot.getRangeAxis();
		numberaxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		if (type == 1)
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
