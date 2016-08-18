import javax.json.JsonObject;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by User on 09.08.2016.
 */
public class ConsolApp {
    public static String COMMAND = (char) 27 + "[92m";
    public String END = (char) 27 + "[0m";
    private String timeOfRequest;// = "13:00";
    private String mailFromSend;// = "molllall901@gmail.com";
    private String mailToSend;// = "katomachin@mail.ru ";
    private String host;// = "127.0.0.587";
    private String port = "465";
    private String password;// = "allmolll901";
    private String username;// = "molllall901";
    private String calendarId;// = "molllall901@gmail.com";
    private JSONWork jsonWork = new JSONWork();
    private Updating updating = new Updating();
    private JsonObject jsonObjectToSettings;

    public void ConsolApp() throws IOException {
        System.out.println("ConsolApp for working whith .... ");
        System.out.println("Type " + COMMAND + "help" + END + " for list of commands, " +
                COMMAND + "quit" + END + " to quit.");
        inputPreWrite();
    }

    private void inputPreWrite() {
        try (Scanner in = new Scanner(System.in)) {
            do {
                System.out.print("KatSender> ");
            } while (isCommand(in.nextLine(), in));
        } catch (Exception e) {
            System.out.println("Something went wrong :(");
        } finally {
            System.out.println("End of KatSender");
        }
    }

    private boolean isCommand(String input, Scanner in) throws IOException, MessagingException, InterruptedException {
        String[] command = input.split(" ");
        System.out.print("KatSender> ");
        switch (command[0]) {
            case "help":
                helper();
                return true;
            case "settings":
                jsonObjectToSettings = null;
                System.out.println("Type " + COMMAND + "help" + END + " for list of commands, " +
                        COMMAND + "quit" + END + " to quit.");
                do {
                    System.out.print("KatSender> ");
                } while (isCommandForSettings(in.nextLine(), in));
                return true;
            case "start":
                updating.updating(jsonWork);  //it's working
                return true;
            case "mkFile":
                jsonWork.resetSettings();
                return true;
            case "quit":
            case "exit":
                return false;
            default:
                System.out.println("Unknown command, try again ;)");
                return true;
        }
    }

    private boolean isCommandForSettings(String input, Scanner in) throws IOException {
        Settings settings = new Settings();
        String[] command = input.split(" ");
        System.out.print("KatSender> ");
        switch (command[0]) {

            case "help":
                helpForSettings();
                return true;
            case "chHost":
                host = settings.host(in);
                if (!host.equals("")) {
                    jsonObjectToSettings = jsonWork.toChHost(host);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chTime":
                timeOfRequest = settings.time(in);
                if (!timeOfRequest.equals("")) {
                    jsonObjectToSettings = jsonWork.toChTime(timeOfRequest);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chMailTo":
                mailToSend = settings.mailToSend(in);
                if (!mailToSend.equals("")) {
                    jsonObjectToSettings = jsonWork.toChMailTo(mailToSend);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chMailFrom":
                mailFromSend = settings.mailFromSend(in);
                if (!mailFromSend.equals("")) {
                    jsonObjectToSettings = jsonWork.toChMailFrom(mailFromSend);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chPassword":
                password = settings.e_MailPassword(in);
                if (!password.equals("")) {
                    jsonObjectToSettings = jsonWork.toChPassword(password);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chUsername":
                username = settings.e_MailUsername(in);
                if (!username.equals("")) {
                    jsonObjectToSettings = jsonWork.toChUsername(username);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "extend":
                helpExtend();
                return true;
            case "chPort":
                port = settings.port(in);
                if (!port.equals("")) {
                    jsonObjectToSettings = jsonWork.toChPort(port);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "chCalenderID":
                calendarId = settings.calendarID(in);
                if (!calendarId.equals("")) {
                    jsonObjectToSettings = jsonWork.toChClendarID(calendarId);
                    jsonWork.jsonWriteObject(jsonObjectToSettings);
                }
                return true;
            case "reset":
                jsonWork.resetSettings();
                return true;
            case "exit":
                return false;
            default:
                System.out.println("Unknown command, try again ;)");
                return true;
        }
    }

    private void helper() {
        System.out.println("Command " + COMMAND + "start" + END + " for STARTING the program KatSender");
        System.out.println("Command " + COMMAND + "settings" + END + " for chang settings");
        System.out.println("Command " + COMMAND + "quit" + END + " or " + COMMAND + "exit" + END
                + " for ENDING the program KatSender");
        System.out.println("Command " + COMMAND + "mkFile" + END + " build File for settings.IT'S IMPORTANT DO IN THE FIRST TIME OF THE WORK");
    }

    private void helpForSettings() {
        System.out.println("Command " + COMMAND + "chTime" + END + " for chang TIME of updating");
        System.out.println("Command " + COMMAND + "chCalenderID" + END + " for chang CALENDERID of updating");
        System.out.println("Command " + COMMAND + "chMailTo" + END + " for chang address of Mail ON which will sending Mails");
        System.out.println("Command " + COMMAND + "extend" + END + " for view other  settings");
        System.out.println("Command " + COMMAND + "exit" + END + " for EXIT from the settings");
    }

    private void helpExtend() {
        System.out.println("Command " + COMMAND + "chMailFrom" + END + " for chang address of Mail FROM which will sending Mails");
        System.out.println("Command " + COMMAND + "chPassword" + END + " for chang PASSWORD of E-Mail from witch sending mails");
        System.out.println("Command " + COMMAND + "chUsername" + END + " for chang USERNAME of E-Mail from witch sending mails");
        System.out.println("Command " + COMMAND + "chHost" + END + " for chang HOST of E-Mail connection");
        System.out.println("Command " + COMMAND + "chPort" + END + " for chang PORT of E-Mail connection");
        System.out.println("Command " + COMMAND + "reset" + END + " for RESET Settings");
    }

}
