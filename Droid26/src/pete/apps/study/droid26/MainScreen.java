package pete.apps.study.droid26;

import android.app.Activity;
import android.os.Bundle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;
import android.widget.ListView;
import android.widget.ArrayAdapter;

public class MainScreen extends Activity
{
	ArrayList<String> mData =  new ArrayList<String>();
	ListView mListView;
	ArrayAdapter<String> mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		mListView = (ListView) findViewById(R.id.listView);

		processData();

		mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, mData);
		mListView.setAdapter(mAdapter);
    }

	private void processData() {
		String URL = "http://stackoverflow.com/questions/tagged/android";
		try {
			Document doc = Jsoup.connect(URL).get();
			Elements questions = doc.select(".summary h3 a");
			for(Element question: questions) {
				mData.add(question.text());
			}
			
			if(mData.size() == 0) {
				mData.add("Empty result");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			mData.clear();
			mData.add("Exception: " + ex.toString());
		}
	}
}
