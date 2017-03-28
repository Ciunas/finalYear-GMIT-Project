package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by ciunas on 1/27/17.
 */
public class BashCommand {
    public static void main(String[] args) throws IOException {

        String[] cmd = {"/bin/bash","-c","echo \"password\"| sudo -S /home/username/script.sh"};
        Process pb = Runtime.getRuntime().exec(cmd);
        String line;
        BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
        while ((line = input.readLine()) != null) {
            System.out.println(line);
        }
        input.close();
    }
}
