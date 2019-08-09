package ColorfulCat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ColorfulCatCrawler {
	public static ArrayList<String> getImgList(String keyword, String color) {
		String url = "https://www.google.com/search";
		HashMap<String, String> inputData = new HashMap<>();
		ArrayList<String> list = new ArrayList<>();
		inputData.put("q", keyword);
		inputData.put("tbm", "isch");
		inputData.put("tbs", "ic:specific,isc:" + color);
		Document doc = null;
		try {
			doc = getDoc(url, inputData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Elements els = doc.getElementsByClass("rg_meta notranslate");
		for (Element el : els) {
			list.add(getImg(el));
		}
		return list;
	}

	public static Document getDoc(String url, Map<String, String> inputData) throws IOException {
		return Jsoup.connect(url).data(inputData).get();
	}
	
	public static String getImg(Element el) {
		String html = el.html();
		Pattern p = Pattern.compile("\"ou\":\"(\\S*)\",\"");
		Matcher m = p.matcher(html);
		if(m.find()) {
			return m.group(1);			
		}
		return null;
	}

	public static void sleep(double second) {
		try {
			Thread.sleep((long) (second * 1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
