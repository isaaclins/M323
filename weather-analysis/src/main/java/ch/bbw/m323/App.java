package ch.bbw.m323;

import ch.bbw.m323.model.WeatherEntry;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        try {
            List<WeatherEntry> entries = readWeatherData("sample_weather_data.csv");

            // Create the main frame
            JFrame frame = new JFrame("Weather Data Analysis");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JTabbedPane tabbedPane = new JTabbedPane();

            // 1. Bar Chart: Average temperature by weather condition
            JFreeChart barChart = createBarChart(entries);
            tabbedPane.addTab("Avg Temp", new ChartPanel(barChart));

            // 2. Pie Chart: Weather condition distribution
            JFreeChart pieChart = createPieChart(entries);
            tabbedPane.addTab("Distribution", new ChartPanel(pieChart));

            // 3. Line Chart: Temperature trend over time
            JFreeChart lineChart = createLineChart(entries);
            tabbedPane.addTab("Temp Trend", new ChartPanel(lineChart));

            // 4. Scatter Plot: Temperature vs. Humidity
            JFreeChart scatterPlot = createScatterPlot(entries);
            tabbedPane.addTab("Temp vs. Humidity", new ChartPanel(scatterPlot));

            frame.setContentPane(tabbedPane);
            frame.pack();
            frame.setVisible(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<WeatherEntry> readWeatherData(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            return lines.skip(1) // Skip header
                    .map(App::parseWeatherEntry)
                    .collect(Collectors.toList());
        }
    }

    public static WeatherEntry parseWeatherEntry(String line) {
        String[] parts = line.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(parts[0], formatter);
        String city = parts[1];
        double temperature = Double.parseDouble(parts[2]);
        int humidity = Integer.parseInt(parts[3]);
        double precipitation = Double.parseDouble(parts[4]);
        int windSpeed = Integer.parseInt(parts[5]);
        String weatherCondition = parts[6];
        return new WeatherEntry(date, city, temperature, humidity, precipitation, windSpeed, weatherCondition);
    }

    private static JFreeChart createBarChart(List<WeatherEntry> entries) {
        Map<String, Double> avgTempByCondition = entries.stream()
                .collect(Collectors.groupingBy(
                        WeatherEntry::getWeatherCondition,
                        Collectors.averagingDouble(WeatherEntry::getTemperature)));

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        avgTempByCondition.forEach((condition, avgTemp) -> dataset.addValue(avgTemp, "Average Temperature", condition));

        return ChartFactory.createBarChart(
                "Average Temperature by Weather Condition",
                "Weather Condition",
                "Average Temperature (°C)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    private static JFreeChart createPieChart(List<WeatherEntry> entries) {
        Map<String, Long> conditionCounts = entries.stream()
                .collect(Collectors.groupingBy(
                        WeatherEntry::getWeatherCondition,
                        Collectors.counting()));

        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
        dataset.clear();
        conditionCounts.forEach(dataset::setValue);

        return ChartFactory.createPieChart(
                "Weather Condition Distribution",
                dataset,
                true, true, false);
    }

    private static JFreeChart createLineChart(List<WeatherEntry> entries) {
        TimeSeries series = new TimeSeries("Daily Temperature");
        entries.stream()
                .sorted((e1, e2) -> e1.getDate().compareTo(e2.getDate()))
                .forEach(entry -> {
                    Day day = new Day(
                            java.util.Date.from(entry.getDate().atStartOfDay(ZoneId.systemDefault()).toInstant()));
                    series.add(day, entry.getTemperature());
                });

        TimeSeriesCollection dataset = new TimeSeriesCollection(series);
        return ChartFactory.createTimeSeriesChart(
                "Temperature Trend Over Time",
                "Date",
                "Temperature (°C)",
                dataset,
                true, true, false);
    }

    private static JFreeChart createScatterPlot(List<WeatherEntry> entries) {
        XYSeries series = new XYSeries("Temperature vs. Humidity");
        entries.forEach(entry -> series.add(entry.getHumidity(), entry.getTemperature()));
        XYSeriesCollection dataset = new XYSeriesCollection(series);

        JFreeChart scatterPlot = ChartFactory.createScatterPlot(
                "Temperature vs. Humidity",
                "Humidity (%)",
                "Temperature (°C)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        XYPlot plot = scatterPlot.getXYPlot();
        plot.getRenderer().setSeriesPaint(0, Color.BLUE);
        return scatterPlot;
    }
}
