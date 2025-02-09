package Framework;

import java.io.FileWriter;
import java.nio.file.Paths;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GameException extends Exception {

    public GameException(String message) {
        super(message);
        logErrorToFile(message, "", "");
    }

    public GameException(String  message, String clase, String metodo) {
        System.out.println("[ERROR EN STARSHIPGAME] UPS...! La nave acaba de explotar...💥" 
                            + clase +"."+ metodo +" : "+ message );
        logErrorToFile(message, clase, metodo);
    }

    private void logErrorToFile(String message, String clase, String metodo) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(Paths.get("logfile.txt").toString(), true))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            
            System.out.println("AppException" +" - "+ clase +"."+ clase +": " + message);
            writer.println(now.format(formatter) +" - "+ clase +"."+ clase +": " + message);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("AppException" +" - "+ clase +"."+ clase +": " + message);
        }
    }

}