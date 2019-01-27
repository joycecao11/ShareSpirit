package ui;

public class WebReader {
    //02a78d263bcf79fb9b46436d429efb360fa5f70be2058a7d72478494bd591d2b
//    private final String URL_PATH = "https://oc2-index.library.ubc.ca";
//    private final String apikey = "&api_key=02a78d263bcf79fb9b46436d429efb360fa5f70be2058a7d72478494bd591d2b";
//    //get this idea from google
//
//    public JSONObject parsingAPI() throws IOException, JSONException {
//        InputStream is = new URL(URL_PATH + "/search/6.2.3?q=" + URLEncoder.encode("java", "UTF-8") + apikey).openStream();
//        try {
//            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
//            String jsonText = readAll(rd);
//            JSONObject json = new JSONObject(jsonText);
//            return json;
//        } finally {
//            is.close();
//        }
//
//    }
//
//    public String getLine() throws IOException ,JSONException{ //can either put parent Exception in the signature or only put specific child exception
//
//        JSONObject json = parsingAPI();
//
//        int numBooks = json.getJSONObject("data").getJSONObject("hits").getJSONArray("hits").length();
//        JSONObject book = json.getJSONObject("data").getJSONObject("hits").getJSONArray("hits").getJSONObject(i).getJSONObject("_source");
//
//        String bookName = json.getString("Title");
//        String author = json.getString("Author");
//        return bookName + author;
//    }
//
//    //get this idea from google
//    private String readAll(Reader rd) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        int cp;
//        while ((cp = rd.read()) != -1) {
//            sb.append((char) cp);
//        }
//        return sb.toString();
//    }
//
//    public static void main(String[] args) throws IOException, JSONException{
//        WebReader wb = new WebReader();
//        wb.getLine();
//
//    }
}
