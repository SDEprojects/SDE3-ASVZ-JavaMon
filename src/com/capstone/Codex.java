package com.capstone;

import java.io.*;

public class Codex {
    private File codex = new File("src/com/capstone/codex.txt");

    public void readCodex(){
        try {
            FileReader fr = new FileReader(codex);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e){
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateCodex(String action){
        try {
            FileWriter fw = new FileWriter(codex, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(action);
            bw.newLine();
//            bw.flush();
            bw.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
