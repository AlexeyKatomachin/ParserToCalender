/**
 * Created by User on 09.08.2016.
 */

import jdk.nashorn.internal.runtime.ParserException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class HTMLParser {
    private static String wort = "\"Борисов-Арена\"";

    private String dataOfGameForMail;
    private String text, firstComand, secondComand;
    private String dataAndTime;
    private String day, month, year, hour, minets, hourForCalender;
    public String dataOfGameForCalender, players, place, description;

    public boolean HTMLParser(MailSender mailSender, JSONWork jsonWork) throws IOException {
        Document doc = null;
        Element next_game;
        Elements dats;

        try {
            doc = Jsoup.connect("http://fcbate.by/fan-zone").get();
        } catch (IOException e) {
            System.out.println("Can't connect to HTML page :(");
            mailSender.MessageError("Can't connect to HTML page :(");
        }

        try {
            next_game = doc.select("#next_game").first();
            dats = next_game.getElementsByTag("p");
            for (Element data : dats) {
                text = data.text();

                if (true)//text.indexOf(wort) != -1)
                {

                    firstComand = next_game.getElementsByClass("first-team").text();
                    secondComand = next_game.getElementsByClass("sec-team").text();
                    dataAndTime = next_game.getElementsByTag("p").first().text();
                    place = next_game.getElementsByTag("p").last().text();

                    day = dataAndTime.substring(0, 2);
                    year = dataAndTime.substring(11, 15);
                    hour = dataAndTime.substring(17, 19);
                    hourForCalender = String.valueOf(Byte.valueOf(dataAndTime.substring(17, 19)) - Byte.valueOf("3"));
                    minets = dataAndTime.substring(20, 22);
                    description = next_game.text();
                    month = Month(dataAndTime.substring(3, 6));
                    if (Day(day) != null) {
                        day = Day(day);
                    }

                    players = firstComand.concat(" - " + secondComand);

                    dataOfGameForCalender = year.concat("-" + month + "-" + day +
                            "T" + hourForCalender + ":" + minets + ":" + "00" + "-" +
                            minets + ":" + "00");

                    dataOfGameForMail = players.concat(". " + dataAndTime + ", " + place);

                }
            }
        } catch (ParserException e) {
            System.out.println("Error, can't Parse the Page :(");
            mailSender.MessageError("Error, can't Parse the Page :(");
        }

        if (dataOfGameForCalender.equals(jsonWork.jsonReadEvent("dataOfGameForCalender"))) {
            return false;
        } else {

        }
        jsonWork.jsonWriteEvent(dataOfGameForCalender, players, place, description);
        mailSender.MessageIsSend(dataOfGameForMail, players);
        return true;
    }

    private String Month(String str) {
        switch (str) {
            case ("янв"):
                return "01";
            case ("февь"):
                return "02";
            case ("мар"):
                return "03";
            case ("апр"):
                return "04";
            case ("мая"):
                return "05";
            case ("июн"):
                return "06";
            case ("июл"):
                return "07";
            case ("авг"):
                return "08";
            case ("сен"):
                return "09";
            case ("окт"):
                return "10";
            case ("ноя"):
                return "11";
            case ("дек"):
                return "12";
            default:
                return null;
        }
    }

    private String Day(String str) {
        switch (str) {
            case ("1 "):
                return "01";
            case ("2 "):
                return "02";
            case ("3 "):
                return "03";
            case ("4 "):
                return "04";
            case ("5 "):
                return "05";
            case ("6 "):
                return "06";
            case ("7 "):
                return "07";
            case ("8 "):
                return "08";
            case ("9 "):
                return "09";
            default:
                return null;
        }
    }
}

