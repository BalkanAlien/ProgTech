/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maze;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Marija
 */
public class GameRank {
    //time, name
    private static TreeMap<Long, String> rank = new TreeMap<Long, String>();
    
    public static void loadData(String path) throws FileNotFoundException, UnsupportedEncodingException, IOException {
        try (InputStreamReader read = new InputStreamReader(
                new FileInputStream(path), "UTF-8"); BufferedReader bufferedReader = new BufferedReader(read)) {
            String line = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                String[] sourceStrArray = line.split(",");
                Long time = Long.parseLong(sourceStrArray[1]);
                rank.put(time, sourceStrArray[0]);
            }
        }
    }
    
    public static void SaveData(String path) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Map.Entry ent : rank.entrySet()) {
                String keyt=ent.getKey().toString();
                String valuet=ent.getValue().toString();
                String line = valuet + "," + keyt + "\n";
                bw.write(line);
            }
        }
    }
    
    public static void addData(Long time, String name) {
        rank.put(time, name);
        if (rank.size() > 10) {       
            rank.pollLastEntry();
        }
    }
    
    public static String getRank() {
        String data = "";
        for (Map.Entry ent : rank.entrySet()) {
            String keyt=ent.getKey().toString();
            String valuet=ent.getValue().toString();
            String line = valuet + "," + keyt + "\n"; 	
            data += line;
        }
        return data;
    }
    
}
