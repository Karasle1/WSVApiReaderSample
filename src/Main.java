
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Main {




    public static void main(String[] args) {
        /*config */
        String clientId = "xxx";
        String secret = "xxx";
        String loginId = "xxx";
        String comaKey = "xxx";


        Token tok = new Token();
        WSVRequest req = new WSVRequest();
        final String[] token = {tok.getToken(clientId,secret)};
        Timer timer = new Timer();

        /* Time shedule*/
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                /*Check token validity*/
                if (tok.checkExp(token[0])) {

                    String result = req.getUnits(loginId, "Bearer " + token[0], comaKey);

                    System.out.println(token[0]);
                    System.out.println(result);

                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter("result.txt", true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    PrintWriter out = new PrintWriter(fileWriter);

                    out.println(new Date().toString());
                    out.println(token[0]);
                    out.println(result);
                    out.close();

                } else {
                    /*Tone is not valid */
                    token[0] = tok.getToken(clientId,secret);

                    String result = req.getUnits(loginId, "Bearer " + token[0], comaKey);
                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter("result.txt", true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    PrintWriter out = new PrintWriter(fileWriter);
                    System.out.println(token[0]);
                    System.out.println(result);

                    out.println(new Date().toString());
                    out.println(token[0]);
                    out.println(result);
                    out.close();
                }

            }
            };
        timer.schedule(task, 200, 300000);
        }
    }
