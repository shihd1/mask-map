import java.io.*;
import java.net.URL;
import java.util.Date;

public class GrabService {

    final static String targetUrl = "https://data.nhi.gov.tw/resource/mask/maskdata.csv";

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(" ================= START ================= " + new Date().toString());
        URL stockURL = new URL(targetUrl);
        BufferedReader in = new BufferedReader(new InputStreamReader(stockURL.openStream()));
//        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("data.csv"))) ;
        // skip first line
        in.readLine();
        StringBuilder sb = new StringBuilder();
        sb.append("var udata='[");
        boolean first = true;
        while (true) {
            String s = in.readLine();
            System.out.println(s);
            if (s == null || s.isEmpty()) {
                break;
            }
            if (s.contains("台中")||s.contains("臺中")) {
                String[] tokens = s.split(",");
                //System.out.println((++i)+"  ==>  "+ Arrays.toString(tokens));
                if (first) {
                    first = false;
                } else {
                    sb.append(",");
                }
                sb.append("[\"").append(tokens[0]).append("\",")
                        .append("\"").append(tokens[4]).append("\",")
                        .append("\"").append(tokens[5]).append("\",")
                        .append("\"").append(tokens[6]).append("\"]");
            }

        }
        sb.append("]';");
        //System.out.println(sb.toString());
        PrintWriter pw = new PrintWriter(new File("qty.js"));
        pw.write(sb.toString());
        pw.close();
        System.out.println(" ================= END ================= " + new Date().toString());


    }
}
