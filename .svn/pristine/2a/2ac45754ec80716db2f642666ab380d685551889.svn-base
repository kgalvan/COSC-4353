package guessinggame;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class SpellCheckerService implements SpellChecker{

    public boolean isSpellingCorrect(String userInput){
        try {
            return Boolean.parseBoolean(getResponseFromURL(userInput));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    public String getResponseFromURL(String userInput) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://agile.cs.uh.edu/spell?check=" + userInput)
                .build();
        Response response = null;

        response = client.newCall(request).execute();

        String websiteResponse = null;

        websiteResponse = response.body().string();

        return websiteResponse;
    }
}
