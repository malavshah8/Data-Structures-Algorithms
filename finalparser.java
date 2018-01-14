import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * This class is used for HTML parsing from URL using Jsoup.
 * 
 * @author Malav Shah
 */
public class finalparser {
	static Map<String, String> hm = new HashMap<String, String>();

	public static void main(String args[]) throws ParseException {
		print("running...");
		String price1 = "";
		Document document;
		try {
			// Get Document object after parsing the html from given url.
			document = Jsoup.connect("https://en.wikipedia.org/wiki/Star_Wars_(film)").get();

			String title = document.title(); // Get title
			print("Title: " + title); // Print title.

			Elements price = document.select("p"); // Get price
			for (int i = 0; i < price.size(); i++) {
				price1 = price.get(i).text().replaceAll("\\[.*?\\]", "");
				String REGEX = "[a-zA-Z]{3,}\\s\\d{1,2},\\s\\d{4}";
				Pattern p = Pattern.compile(REGEX);
				Matcher m = p.matcher(price1);
				while (m.find()) {
					//System.out.println(m.group(0));
					String date = dateFormatter(m.group(0));
					String newInput[] = price1.split("\\.");
					for (String w : newInput) {
						if (w.contains(m.group(0))) {
							// System.out.println(w);
							hm.put(date, w);
						}

					}

				}
			}

			 sortbykey();
			 System.out.println(hm.size());
			/*for (Map.Entry m : hm.entrySet()) {
				System.out.println(m.getKey() + " " + m.getValue());
			}*/

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void print(String string) {
		System.out.println(string);
	}

	public static String dateFormatter(String string) throws ParseException {
		DateFormat fmt = new SimpleDateFormat("MMMM dd, yyyy", Locale.US);
		Date d = fmt.parse(string);

		DateFormat outputFormatter = new SimpleDateFormat("yyyy/MM/dd");
		String output = outputFormatter.format(d);
		// System.out.println(output);
		return output;
	}

	public static void sortbykey() {
		// TreeMap to store values of HashMap
		TreeMap<String, String> sorted = new TreeMap<>();

		// Copy all data from hashMap into TreeMap
		sorted.putAll(hm);

		// Display the TreeMap which is naturally sorted
		for (Map.Entry<String, String> entry : sorted.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
	}

}