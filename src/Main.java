
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
        String clientId = "78e21e88-5cf0-4ed5-86c8-a4caad853cfe";
        String secret = "4z98Q~AQ-fJjlLVD.OdfqjNnn~XOcKRmVSpH5cSx";


        Token tok = new Token();
        WSVRequest req = new WSVRequest();
        final String[] token = {tok.getToken(clientId,secret)};
        Timer timer = new Timer();


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (tok.checkExp(token[0])) {

                    String result = req.getUnits("Leos.Karasek", "Bearer " + token[0], "8d1768d6d26643ebbd12e6b193f11b1c");

                    System.out.println(token[0]);
                    System.out.println(result);
                    FileWriter fileWriter = null;
                    try {
                        fileWriter = new FileWriter("result.txt", true);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    PrintWriter out = new PrintWriter(fileWriter);
                    try {
                     //   out = new PrintWrite(fileWriter);
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    out.println(new Date().toString());
                    out.println(token[0]);
                    out.println(result);
                    out.close();

                } else {
                    token[0] = tok.getToken(clientId,secret);

                    String result = req.getUnits("Leos.Karasek", "Bearer " + token[0], "8d1768d6d26643ebbd12e6b193f11b1c");

                    System.out.println(token[0]);
                    System.out.println(result);

                    PrintWriter out = null;
                    try {
                        out = new PrintWriter("result.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
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
