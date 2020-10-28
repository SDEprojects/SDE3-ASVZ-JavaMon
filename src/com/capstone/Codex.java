package com.capstone;

import java.io.*;

public class Codex {
    private File codex = new File("src/com/capstone/codex.txt");

    public String readCodex(){
        StringBuilder sb = new StringBuilder();
        try {
            FileReader fr = new FileReader(codex);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (FileNotFoundException e){
            System.out.println("The file was not found: " + e.getMessage());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
//        System.out.println(sb.toString());
        return sb.toString();
    }

    public void updateCodex(String action){
        try {
            FileWriter fw = new FileWriter(codex, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(action);
            bw.newLine();
            bw.close();

        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void clear(){
        try {
            PrintWriter pw = new PrintWriter(codex);
            pw.close();
        } catch (FileNotFoundException e){
            System.out.println("The file was not found: " + e.getMessage());
        }
    }
}
