import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Cat {
	public static void main(String[] args) {
		HttpRequest call = HttpRequest.newBuilder().
				uri(URI.create("https://api.thecatapi.com/v1/images/search/")).
				header("x-api-host", "api.thecatapi.com").
				header("x-api-key", "live_z0ruF2YWPQrmkcUrDBVHuaDFqVLPGSU5WbdnkUN5djWYtapdIOsWUyRclJOZdB65").
				GET().build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(call, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		String x = response.body();
		Pattern url = Pattern.compile("(.*)(\"url\":\".*\")(,.*)");
		Matcher m = url.matcher(x);
		m.find();
		String u = m.group(2);
		Pattern extract = Pattern.compile("(\"url\":\")(.*\")");
		Matcher m1 = extract.matcher(u);
		m1.find(); // matcher.group() checks PREVIOUS searches so you should do some
				  // searching before calling this
		String act_url = m1.group(2);
		act_url = act_url.substring(0, act_url.length() - 1);
		System.out.println(act_url);
	}
	

}
