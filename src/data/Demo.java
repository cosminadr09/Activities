package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

public class Demo {
	// sa nu facem cu foruri in stringuri ci cu streamuri neaparat! fara foruri
	// si pot cu mai multe streamuri
	public static void main(String[] args) {

		String fileName = "D://FACULTA//AN2//S2//TP//lab//5//Activities.txt";
		Operation op = new Operation();
		op.populateList(fileName);
//		op.printData();
		op.countDays();
		try {
			op.mapActions();
			op.countActivitiesPerDay();
			op.countDurationPerActivity();
			op.filterActivities();


			System.out.println("Fisier scris cu succes!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
