import java.util.*;

public class LinkShortener {
    private static final String BASE_URL = "http://short.ly/";
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private static final Map<String, String> urlMap = new HashMap<>();
    private static final Map<String, String> reverseMap = new HashMap<>();
    private static final Random random = new Random();

    public static String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder();
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            shortUrl.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return shortUrl.toString();
    }

    public static String shortenUrl(String longUrl) {
        longUrl = longUrl.trim();
        if (reverseMap.containsKey(longUrl)) {
            return BASE_URL + reverseMap.get(longUrl);
        }
        
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (urlMap.containsKey(shortUrl));
        
        urlMap.put(shortUrl, longUrl);
        reverseMap.put(longUrl, shortUrl);
        return BASE_URL + shortUrl;
    }

    public static String expandUrl(String shortUrl) {
        shortUrl = shortUrl.trim();
        if (!shortUrl.startsWith(BASE_URL)) {
            return "Invalid short URL";
        }
        String key = shortUrl.substring(BASE_URL.length());
        return urlMap.getOrDefault(key, "Invalid short URL");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Shorten URL\n2. Expand URL\n3. Exit\nChoose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.println("Enter long URL: ");
                    String longUrl = scanner.nextLine();
                    System.out.println("Shortened URL: " + shortenUrl(longUrl));
                    break;
                case 2:
                    System.out.println("Enter short URL: ");
                    String shortUrl = scanner.nextLine();
                    System.out.println("Original URL: " + expandUrl(shortUrl));
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}
