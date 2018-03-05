package data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;

public class Operation {

	private static final String DATA_SEPARATOR = " ";
	private List<MonitoredData> mdList = new ArrayList<MonitoredData>();

	public List<MonitoredData> getMdList() {
		return mdList;
	}

	public void setMdList(List<MonitoredData> mdList) {
		this.mdList = mdList;
	}

	public void populateList(String fileName) {

		List<String> list = new ArrayList<>();

		try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

			// br returns as stream and convert it into a List
			list = br.lines().collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		list.forEach(s -> {
			String[] parts = s.split("		");

			String dateTime1str = parts[0];
			String dateTime2str = parts[1];
			String activityStr = parts[2];
			// System.out.println("Part1 " + dateTime1str);

			// dates1
			// compdates1
			// comptimes1
			String[] dates1 = dateTime1str.split(" ");
			String dateStr1 = dates1[0];
			String timeStr1 = dates1[1];
			//
			// System.out.println("Date1: " + dateStr1);
			// System.out.println("Time1: " + timeStr1);

			String[] compDates1 = dateStr1.split("-");
			String yearStr1 = compDates1[0];
			String monthStr1 = compDates1[1];
			String dayStr1 = compDates1[2];

			// System.out.println("Year1: " + yearStr1);
			// System.out.println("Month1: " + monthStr1);
			// System.out.println("Day1: " + dayStr1);

			int year1 = Integer.parseInt(yearStr1);
			int month1 = Integer.parseInt(monthStr1);
			int day1 = Integer.parseInt(dayStr1);

			String[] compTimes1 = timeStr1.split(":");
			String hourStr1 = compTimes1[0];
			String minuteStr1 = compTimes1[1];
			String secondStr1 = compTimes1[2];

			// System.out.println("Hour1: " + hourStr1);
			// System.out.println("Minute1: " + minuteStr1);
			// System.out.println("Second1: " + secondStr1);

			int hour1 = Integer.parseInt(hourStr1);
			int minute1 = Integer.parseInt(minuteStr1);
			int second1 = Integer.parseInt(secondStr1);

			/**
			 * DateTime(int year, int monthOfYear, int dayOfMonth,
			 * int hourOfDay, int minuteOfHour, int secondOfMinute)           
			 * Constructs an instance from datetime field values using
			 * ISOChronology in the default time zone
			 */

			DateTime startTime = new DateTime(year1, month1, day1, hour1,
					minute1, second1);
			// System.out.println("StartTime: " + startTime);
			//
			// System.out.println("Part2 " + dateTime2str);

			// dates2
			// compdates2
			// comptimes2
			String[] dates2 = dateTime2str.split(" ");
			String dateStr2 = dates2[0];
			String timeStr2 = dates2[1];

			// System.out.println("Date2: " + dateStr2);
			// System.out.println("Time2: " + timeStr2);

			String[] compDates2 = dateStr2.split("-");
			String yearStr2 = compDates2[0];
			String monthStr2 = compDates2[1];
			String dayStr2 = compDates2[2];

			int year2 = Integer.parseInt(yearStr2);
			int month2 = Integer.parseInt(monthStr2);
			int day2 = Integer.parseInt(dayStr2);

			// System.out.println("Year2: " + yearStr2);
			// System.out.println("Month2: " + monthStr2);
			// System.out.println("Day2: " + dayStr2);

			String[] compTimes2 = timeStr2.split(":");
			String hourStr2 = compTimes2[0];
			String minuteStr2 = compTimes2[1];
			String secondStr2 = compTimes2[2];

			// System.out.println("Hour2: " + hourStr2);
			// System.out.println("Minute2: " + minuteStr2);
			// System.out.println("Second2: " + secondStr2);

			int hour2 = Integer.parseInt(hourStr2);
			int minute2 = Integer.parseInt(minuteStr2);
			int second2 = Integer.parseInt(secondStr2);

			DateTime endTime = new DateTime(year2, month2, day2, hour2,
					minute2, second2);
			// System.out.println("EndTime: " + endTime);
			//
			// System.out.println("Part3 " + activityStr);

			MonitoredData md = new MonitoredData(activityStr, startTime,
					endTime);
			mdList.add(md);

		});// voi avea 3 parti

	}

	public void printData() {
		System.out.println("Lista de date:");
		// mdList.stream().forEach(md -> md.toString());
		mdList.stream().forEach(System.out::println);
		System.out.println("Size: " + mdList.size());

	}

	/*
	 * Count different days
	 */
	public void countDays() {

		System.out.println("Task 1:");
		// DateTime firstDate = mdList.stream().findFirst().get().startTime;
		// System.out.println("First date is: " + firstDate.toLocalDate());
		//
		// DateTime lastDate = mdList.stream().reduce((first, second) -> second)
		// .get().endTime;
		//
		// System.out.println("Last date is: " + lastDate.toLocalDate());
		//
		// int noOfDays = Days.daysBetween(firstDate.toLocalDate(),
		// lastDate.toLocalDate()).getDays();
		// System.out.println("No of days between is: " + noOfDays);

		long noOfDays = mdList.stream()
				.mapToInt(md -> md.startTime.getDayOfYear()).distinct().count();
		System.out.println("No of days between is: " + noOfDays);

	}

	/*
	 * Determine a map of type <String, Integer> that maps to each distinct
	 * action type the number of occurrences in the log. Write the resulting map
	 * into a text file
	 */
	public void mapActions() throws IOException {
		System.out.println("Task 2:");

		Map<String, Long> actions = mdList.stream().collect(
				Collectors.groupingBy(MonitoredData::getActivityLabel,
						Collectors.counting()));
		System.out.println(actions);

		String pathToFile = "D:\\FACULTA\\AN2\\S2\\TP\\lab\\5\\Activities";
		File file = new File("noOfOccurences.txt");
		Path p = file.toPath();

		try (Writer writer = Files.newBufferedWriter(p)) {
			actions.forEach((key, value) -> {
				try {
					writer.write(key + DATA_SEPARATOR + value
							+ System.lineSeparator());
				} catch (IOException ex) {
					throw new UncheckedIOException(ex);
				}
			});
		} catch (UncheckedIOException ex) {
			throw ex.getCause();
		}

	}

	/*
	 * Generates a data structure of type Map<Integer, Map<String, Integer>>
	 * that contains the activity count for each day of the log (task number 2
	 * applied for each day of the log) and writes the result in a text file.
	 */
	public void countActivitiesPerDay() {
		System.out.println("Task 3:");

		Map<Integer, Map<String, Long>> activMap = mdList.stream().collect(
				Collectors.groupingBy(MonitoredData::getDay, Collectors
						.groupingBy(MonitoredData::getActivityLabel,
								Collectors.counting())));
		System.out.println(activMap);
		String pathToFile = "D:\\FACULTA\\AN2\\S2\\TP\\lab\\5\\Activities";
		File file = new File("activitiesByDay.txt");
		Path p = file.toPath();

		try (Writer writer = Files.newBufferedWriter(p)) {
			activMap.forEach((key, value) -> {
				try {
					writer.write(key + DATA_SEPARATOR + value
							+ System.lineSeparator());
				} catch (IOException ex) {
					throw new UncheckedIOException(ex);
				}
			});
		} catch (UncheckedIOException ex) {
			try {
				throw ex.getCause();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * Determine a data structure of the form Map<String, DateTime> that maps
	 * for each activity the total duration computed over the monitoring period.
	 * Filter the activities with total duration larger than 10 hours. Write the
	 * result in a text file.
	 */
	public void countDurationPerActivity() {
		System.out.println("Task 4:");

		Map<String, Integer> duration = mdList
				.stream()
				.collect(
						Collectors
								.groupingBy(
										MonitoredData::getActivityLabel,
										Collectors
												.summingInt(MonitoredData::getDurationHours)))
				.entrySet()
				.stream()
				.filter(md -> md.getValue() > 10)
				.collect(
						Collectors.toMap(md -> md.getKey(), md -> md.getValue()));
		System.out.println(duration);

		String pathToFile = "D:\\FACULTA\\AN2\\S2\\TP\\lab\\5\\Activities";
		File file = new File("countDuration.txt");
		Path p = file.toPath();

		try (Writer writer = Files.newBufferedWriter(p)) {
			duration.forEach((key, value) -> {
				try {
					writer.write(key + DATA_SEPARATOR + value
							+ System.lineSeparator());
				} catch (IOException ex) {
					throw new UncheckedIOException(ex);
				}
			});
		} catch (UncheckedIOException ex) {
			try {
				throw ex.getCause();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * Filter the activities that have 90% of the monitoring samples with
	 * duration less than 5 minutes, collect the results in a List<String>
	 * containing only the distinct activity names and write the result in a
	 * text file.
	 */
	public void filterActivities() {
		System.out.println("Task 5:");

		Map<String, Double> result = mdList
				.stream()
				.collect(
						Collectors
								.groupingBy(
										MonitoredData::getActivityLabel,
										Collectors
												.mapping(
														MonitoredData::getDurationMinutes,
														Collectors
																.averagingDouble(duration -> {
																	return duration < 5 ? 1: 0;
																}))))
				// pt fiecare activitate calculez durata in minute, si pt
				// fiecare durata am folosit functia averageDouble pt a calcula in 
				// procente cat e de adevarata conditia; daca e adevarata va returna procentul 1, altfel <0
				.entrySet()
				.stream()
				.filter(md -> md.getValue() > 0.9)//valoarea repr procentul
				.collect(
						Collectors.toMap(md -> md.getKey(), md -> md.getValue()));
		System.out.println(result);
		//am luat setul de key din result map, si le-am pus intr-o lista
		List<String> filtered = result.keySet().stream()
				.collect(Collectors.toList());
		System.out.println(filtered);
		Path file = Paths.get("filteredActivities.txt");
		try {
			Files.write(file, filtered, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
