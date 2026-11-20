import java.net.*;
import java.io.*;
import java.sql.SQLOutput;
import java.util.Scanner;

public class HtmlRead {
    public static void main(String[] args) {
        HtmlRead app= new HtmlRead();
    }
    public HtmlRead(){

        try{
            //add scanner
            Scanner scanner = new Scanner(System.in);

            while (true) {
                //ask use for website
                System.out.println();
                System.out.println("Enter your website(enter \"stop\" to quit): ");
                String website = scanner.nextLine();
                System.out.println();
                //if user asks to stop, then break
                if (website.equalsIgnoreCase("stop"))
                {
                    break;
                }
                URL fred = new URL(website);
                //ask user for link filter
                System.out.println("Enter link filter(just click enter if none): ");
                String filterWord = scanner.nextLine();
                System.out.println();

                URLConnection urlc = fred.openConnection();
                urlc.setRequestProperty("User-Agent", "Mozilla 5.0 (Windows; U; " + "Windows NT 5.1; en-US; rv:1.8.0.11) ");
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(urlc.getInputStream()));
                String line;
                //counter for spicy extension counting links
                int counter = 0;
                while ((line = reader.readLine()) != null) {
                    //starting point of a link
                    int start = line.indexOf("href=");
                    //while loop for if link is found
                    while (start != -1) {
                        start += 5;
                        //search for whether its a single quote or double that encloses the link
                        char singleDouble = line.charAt(start);
                        //add one to start after the href=" or href='
                        start++;
                        int end = line.indexOf(singleDouble, start);
                        //if no link is found, break, end the while loop and go to next line
                        if (end == -1) {
                           break;
                        }
                        //link is from the start point, href, to end, ",
                        String theLink = line.substring(start, end);

                        //partial link check/fix
                        //if the link is partial, add the original url, like the base one to it
                        if(!(theLink.contains(":")) || (theLink.startsWith("."))||(theLink.startsWith("/")) || (theLink.startsWith("#")))
                        {
                            theLink = new URL(fred, theLink).toString();
                        }
                        //check if link has the filter word, and if so, add 1 too link counter
                       if (theLink.contains(filterWord)) {
                            System.out.println(theLink);
                            counter++;
                        }
                        /*now search for more link starting from the end, so
                        it can print multiple in the same line */
                        start = line.indexOf("href=", end + 1);
                    }
                }
                reader.close();
                //spicy extension
                //print number of links found
                if(counter>1)
                {
                    System.out.println(counter + " links found");
                }
                //grammar stuff
                if(counter==1)
                {
                    System.out.println(counter + " link found");
                }
                //if no links are found
                if(counter == 0)
                {
                    System.out.println("Try a more common filter word!");
                }
                //spacing
                System.out.println();


            }
        } catch(Exception ex){
            System.out.println(ex);
        }
    }



}