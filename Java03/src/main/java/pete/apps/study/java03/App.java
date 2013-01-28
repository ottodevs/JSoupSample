package pete.apps.study.java03;

import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class App 
{
    public static void main( String[] args )
    {
		processData();

    }

	private static void processData() {
		final String URL = "http://stackoverflow.com/questions/tagged/android";
		try {
			Document doc = Jsoup.connect(URL).get();

			Elements listQuestions = doc.select(".summary h3 a");
			System.out.println("Questions: " + listQuestions.size());
			for(Element question: listQuestions) {
				System.out.println("+) " + question.text());
			}

		} catch (Exception ex) {
			System.out.println("Exception: ex = " + ex.toString());
		}
	}
}
