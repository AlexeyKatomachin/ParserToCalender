import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonWriter;
import java.io.*;

public class JSONWork {
    private JsonObject jsonObject;

    public JsonObject toChTime(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", data)
                .add("mailFromSend", mainJsonObject.getString("mailFromSend"))
                .add("mailToSend", mainJsonObject.getString("mailToSend"))
                .add("host", mainJsonObject.getString("host"))
                .add("port", mainJsonObject.getString("port"))
                .add("password", mainJsonObject.getString("password"))
                .add("username", mainJsonObject.getString("username"))
                .add("calendarId", mainJsonObject.getString("calendarId"))
                .build();
        return jsonObject;
    }

    public JsonObject toChMailFrom(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", mainJsonObject.getString("timeOfRequest"))
                .add("mailFromSend", data)
                .add("mailToSend", mainJsonObject.getString("mailToSend"))
                .add("host", mainJsonObject.getString("host"))
                .add("port", mainJsonObject.getString("port"))
                .add("password", mainJsonObject.getString("password"))
                .add("username", mainJsonObject.getString("username"))
                .add("calendarId", mainJsonObject.getString("calendarId"))
                .build();
        return jsonObject;
    }

    public JsonObject toChMailTo(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", mainJsonObject.getString("timeOfRequest"))
                .add("mailFromSend", mainJsonObject.getString("mailFromSend"))
                .add("mailToSend", data)
                .add("host", mainJsonObject.getString("host"))
                .add("port", mainJsonObject.getString("port"))
                .add("password", mainJsonObject.getString("password"))
                .add("username", mainJsonObject.getString("username"))
                .add("calendarId", mainJsonObject.getString("calendarId"))
                .build();
        return jsonObject;
    }

    public JsonObject toChHost(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", mainJsonObject.getString("timeOfRequest"))
                .add("mailFromSend", mainJsonObject.getString("mailFromSend"))
                .add("mailToSend", mainJsonObject.getString("mailToSend"))
                .add("host", data)
                .add("port", mainJsonObject.getString("port"))
                .add("password", mainJsonObject.getString("password"))
                .add("username", mainJsonObject.getString("username"))
                .add("calendarId", mainJsonObject.getString("calendarId"))
                .build();
        return jsonObject;
    }

    public JsonObject toChPassword(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", mainJsonObject.getString("timeOfRequest"))
                .add("mailFromSend", mainJsonObject.getString("mailFromSend"))
                .add("mailToSend", mainJsonObject.getString("mailToSend"))
                .add("host", mainJsonObject.getString("host"))
                .add("port", mainJsonObject.getString("port"))
                .add("password", data)
                .add("username", mainJsonObject.getString("username"))
                .add("calendarId", mainJsonObject.getString("calendarId"))
                .build();
        return jsonObject;
    }

    public JsonObject toChPort(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", mainJsonObject.getString("timeOfRequest"))
                .add("mailFromSend", mainJsonObject.getString("mailFromSend"))
                .add("mailToSend", mainJsonObject.getString("mailToSend"))
                .add("host", mainJsonObject.getString("host"))
                .add("port", data)
                .add("password", mainJsonObject.getString("password"))
                .add("username", mainJsonObject.getString("username"))
                .add("calendarId", mainJsonObject.getString("calendarId"))
                .build();
        return jsonObject;
    }

    public JsonObject toChUsername(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", mainJsonObject.getString("timeOfRequest"))
                .add("mailFromSend", mainJsonObject.getString("mailFromSend"))
                .add("mailToSend", mainJsonObject.getString("mailToSend"))
                .add("host", mainJsonObject.getString("host"))
                .add("port", mainJsonObject.getString("port"))
                .add("password", mainJsonObject.getString("password"))
                .add("username", data)
                .add("calendarId", mainJsonObject.getString("calendarId"))
                .build();
        return jsonObject;
    }

    public JsonObject toChClendarID(String data) throws FileNotFoundException {
        JsonObject mainJsonObject = Json.createReader(new InputStreamReader(new FileInputStream("Settings.json"))).readObject();
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", mainJsonObject.getString("timeOfRequest"))
                .add("mailFromSend", mainJsonObject.getString("mailFromSend"))
                .add("mailToSend", mainJsonObject.getString("mailToSend"))
                .add("host", mainJsonObject.getString("host"))
                .add("port", mainJsonObject.getString("port"))
                .add("password", mainJsonObject.getString("password"))
                .add("username", mainJsonObject.getString("username"))
                .add("calendarId", data)
                .build();
        return jsonObject;
    }

    public void jsonWriteObject(JsonObject jsonObject) throws IOException {
        Writer writer = new FileWriter("Settings.json");
        JsonWriter jsonWriter = Json.createWriter(writer);
        jsonWriter.writeObject(jsonObject);
        writer.close();
    }

    public String jsonReadSettings(String name) throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream("Settings.json"), "UTF-8");// for read
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObject = jsonReader.readObject();
        reader.close();
        return jsonObject.getString(name);
    }

    public String jsonReadEvent(String name) throws IOException {
        Reader reader = new InputStreamReader(new FileInputStream("Game.json"), "UTF-8");// for read
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObject = jsonReader.readObject();
        reader.close();
        return jsonObject.getString(name);
    }


    public void jsonWriteEvent(String dataOfGameForCalender, String players, String place, String description) throws IOException {
        Writer writer = new FileWriter("Game.json");
        JsonWriter jsonWriter = Json.createWriter(writer);
        jsonObject = Json.createObjectBuilder()
                .add("dataOfGameForCalender", dataOfGameForCalender)
                .add("players", players)
                .add("place", place)
                .add("description", description)
                .build();
        jsonWriter.writeObject(jsonObject);
        writer.close();
    }
    /*public JsonObject jsonReadEvent() throws IOException
    {
        Reader reader = new InputStreamReader(new FileInputStream("Event.json"),"UTF-8");// for read
        JsonReader jsonReader = Json.createReader(reader);
        JsonObject jsonObject = jsonReader.readObject();
        reader.close();
        return  jsonObject;
    }*/

    public void resetSettings() throws IOException {
        Writer writer = new FileWriter("Settings.json");
        JsonWriter jsonWriter = Json.createWriter(writer);
        jsonObject = Json.createObjectBuilder()
                .add("timeOfRequest", "13:00")
                .add("mailFromSend", "katsender@yandex.by")
                .add("mailToSend", "katomachin@mail.ru")
                .add("host", "smtp.yandex.by")
                .add("port", "465")
                .add("password", "katsender2016")
                .add("username", "katsender@yandex.by")
                .add("calendarId", "molllall901@gmail.com")
                .build();
        System.out.println(jsonObject);
        jsonWriter.writeObject(jsonObject);
        writer.close();
    }
}


