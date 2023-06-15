package redditbot;

import net.dean.jraw.RedditClient;
import net.dean.jraw.http.NetworkAdapter;
import net.dean.jraw.http.OkHttpNetworkAdapter;
import net.dean.jraw.models.Flair;
import net.dean.jraw.models.Listing;
import net.dean.jraw.models.Submission;
import net.dean.jraw.oauth.Credentials;
import net.dean.jraw.oauth.OAuthHelper;
import net.dean.jraw.http.UserAgent;
import net.dean.jraw.pagination.DefaultPaginator;
import net.dean.jraw.references.SubredditReference;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;


public class redditbotclass {


    private static final String YOUR_USERNAME = "Mysterious_Rewera";
    private static final String YOUR_PASSWORD = "Huf94SPUm5hfGCk";
    private static final String YOUR_APP_ID = "RvHQcMl0P_vyL4tZHJRERg";
    private static final String YOUR_APP_SECRET = "zCKm7Iyo2-peKlGMfsXKLhTiiru-vQ";
    private static final String YOUR_APP_NAME = "Java script for take photos";
    private static final String YOUR_APP_VERSION = "1";
    private static final String YOUR_REDDIT_USERNAME = "Mysterious_Rewera";
    private static final String YOUR_REDDIT_CLIENT_ID = "RvHQcMl0P_vyL4tZHJRERg";

    public static void main(String[] args) {



            // Set up user credentials (replace with your own values)
            Credentials credentials = Credentials.script(
                    YOUR_USERNAME, YOUR_PASSWORD, YOUR_APP_ID, YOUR_APP_SECRET);

            // Create a user agent
            UserAgent userAgent = new UserAgent(YOUR_APP_NAME, YOUR_APP_VERSION,
                    YOUR_REDDIT_USERNAME, YOUR_REDDIT_CLIENT_ID);

            // This is what really sends HTTP requests
            NetworkAdapter adapter = new OkHttpNetworkAdapter(userAgent);

            // Create a RedditClient instance
            RedditClient reddit = OAuthHelper.automatic(adapter, credentials);

            // Specify the subreddit you want to connect to
            // reddit for picks of dicks DickSlips southpark
            String subredditName = "southpark";

            // Create a DefaultPaginator for the subreddit
            DefaultPaginator<Submission> paginator = reddit.subreddit(subredditName).posts().limit(10).build();

            // Fetch and print the top posts from the subreddit
            Listing<Submission> posts = paginator.next();

            for (Submission post : posts) {
                System.out.println(post.getTitle());
                System.out.println(post.getEmbeddedMedia());
                System.out.println(post.isNsfw());
            }

            //path to dir which dir will be clean every time and where all files will be stored
            File dir = new File("D:\\Main\\RedditBot\\RedditBotJava17\\photos");

            //Clear files in directory
            for(File file : Objects.requireNonNull(dir.listFiles())){
                if (file.isFile()){
                    file.delete();
                }
            }


            // Fetch the top posts from the subreddit
            for (Submission post : posts) {

                // Check if the post is an image-based submission
                if ((post.isSelfPost() || !Objects.equals(post.getPostHint(), "image")) & post.isNsfw()) {
                    continue; // Skip non-image posts
                }

                // Retrieve the image URL from the post
                String imageUrl = post.getUrl();

                // Download the image and save it to a file
                try (InputStream in = new URL(imageUrl).openStream()) {
                    String fileName = post.getId() + ".jpg";
                    FileOutputStream out = new FileOutputStream(new File ("D:\\Main\\RedditBot\\RedditBotJava17\\photos", fileName));
                    byte[] buffer = new byte[4096];
                    int bytesRead;
                    while ((bytesRead = in.read(buffer)) != -1) {
                        out.write(buffer, 0, bytesRead);
                    }
                    System.out.println("Downloaded image: " + fileName);
                    out.close();
                } catch (IOException e) {
                    System.err.println("Error downloading image: " + e.getMessage());
                }

            }

    }
}

