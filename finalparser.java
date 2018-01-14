import java.io.IOException;
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
	public static void main(String args[]) {
		print("running...");
		String price1 = "";
		Document document;
		try {
			// Get Document object after parsing the html from given url.
			document = Jsoup.connect("https://en.wikipedia.org/wiki/The_Commuter_(film)").get();

			String title = document.title(); // Get title
			print("Title: " + title); // Print title.

			Elements price = document.select("p"); // Get price
			for (int i = 0; i < price.size(); i++) {
				price1 = price.get(i).text().replaceAll("\\[.*?\\]", "");
				String REGEX = "[a-zA-Z]{4,}\\s\\d{1,2},\\s\\d{4}";
				Pattern p = Pattern.compile(REGEX);
				Matcher m = p.matcher(price1);
				while (m.find()) {

					System.out.println("Found " + m.group(0));
					String newInput[] = price1.split("\\.");
					for (String w : newInput) {
						if (w.contains(m.group(0))) {
							System.out.println(w);
						}

					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void print(String string) {
		System.out.println(string);
	}

}