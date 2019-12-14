
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         records = new ArrayList<>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()) {
             records.add(WebLogParser.parseEntry(line));
         }
     }

     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }

    public HashMap<String, Integer> countVisitsPerIP() {
        HashMap<String, Integer> ipCount = new HashMap<>();
        for (LogEntry le : records) {
            String ipAddr = le.getIpAddress();
            if (! ipCount.containsKey(ipAddr)) {
                ipCount.put(ipAddr, 1);
            } else {
                ipCount.put(ipAddr, ipCount.get(ipAddr) + 1);
            }
        }
        return ipCount;
    }
}
