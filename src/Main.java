
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Timer;
import java.util.TimerTask;



public class Main {
    public static void main(String[] args) {
        Token tok = new Token();
        WSVRequest req = new WSVRequest();
        final String[] token = {tok.getToken()};
        Timer timer = new Timer();


        // Helper class extends TimerTask
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (tok.checkExp(token[0])) {

                    String result = req.getUnits("Leos.Karasek", "Bearer " + token[0], "8d1768d6d26643ebbd12e6b193f11b1c");

                    System.out.println(token[0]);
                    System.out.println(result);

                    PrintWriter out = null;
                    try {
                        out = new PrintWriter("result.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    out.println(token[0]);
                    out.println(result);
                    out.close();

                } else {
                    token[0] = tok.getToken();

                    String result = req.getUnits("Leos.Karasek", "Bearer " + token[0], "8d1768d6d26643ebbd12e6b193f11b1c");

                    System.out.println(token[0]);
                    System.out.println(result);

                    PrintWriter out = null;
                    try {
                        out = new PrintWriter("result.txt");
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                    out.println(token[0]);
                    out.println(result);
                    out.close();
                }

            }


            };
        timer.schedule(task, 200, 300000);
        }


    }
