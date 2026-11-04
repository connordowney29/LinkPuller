import java.net.*;
import java.io.*;
import java.util.Scanner;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();
    }
    public HtmlRead(){

        try{
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your website: ");
            String website = scanner.nextLine();
            URL fred = new URL(website);
            System.out.println("Enter link filter: ");
            String filter = scanner.nextLine();
            URLConnection urlc = fred.openConnection();
            urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlc.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                int start = line.indexOf("http");

                while (start != -1) {
                    int end = line.indexOf('"', start);

                    if (end == -1) {
                        break;
                    }

                    String theLink = line.substring(start, end);
                    if (theLink.contains(filter)) {
                        System.out.println(theLink);
                    }

                    start = line.indexOf("http", end + 1);
                }
            }
            reader.close();
        } catch(Exception ex){
            System.out.println(ex);
        }
    }



}