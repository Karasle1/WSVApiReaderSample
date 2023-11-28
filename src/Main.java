import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        /*config */
        String clientId = "78e21e88-5cf0-4ed5-86c8-a4caad853cfe";
        String secret = "4z98Q~AQ-fJjlLVD.OdfqjNnn~XOcKRmVSpH5cSx";
        String loginId = "Leos.Karasek";
        String comaKey = "8d1768d6d26643ebbd12e6b193f11b1c";


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

                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("result.txt", true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    PrintWriter out = new PrintWriter(fileWriter);

                    out.println(new Date());
                    out.println(token[0]);
                    out.println(result);
                    out.close();

                } else {
                    /*Token is not valid , req for the new one */
                    token[0] = tok.getToken(clientId,secret);

                    String result = req.getUnits(loginId, "Bearer " + token[0], comaKey);
                    FileWriter fileWriter;
                    try {
                        fileWriter = new FileWriter("result.txt", true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    PrintWriter out = new PrintWriter(fileWriter);
                    System.out.println(token[0]);
                    System.out.println(result);

                    out.println(new Date());
                    out.println(token[0]);
                    out.println(result);
                    out.close();
                }

            }
            };
        timer.schedule(task, 200, 300000);
        }
    }
