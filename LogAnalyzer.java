
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

    public int mostNumberVisitsByIP(HashMap<String,Integer> ipCount) {
         int max = 0;
         for (String ipAddr : ipCount.keySet()) {
             if (ipCount.get(ipAddr) > max) {
                 max = ipCount.get(ipAddr);
             }
         }
         return max;
    }

    public ArrayList<String> iPsMostVisits(HashMap<String,Integer> ipCount) {
         ArrayList<String> ips = new ArrayList<>();
         int max = mostNumberVisitsByIP(ipCount);
         for (String ipAddr : ipCount.keySet()) {
             if (ipCount.get(ipAddr).equals(max)) {
                 ips.add(ipAddr);
             }
         }
         return ips;
    }

    private String getDate(LogEntry le) {
        String date = le.getAccessTime().toString();
        String month = date.substring(4, 7);
        String day = date.substring(8, 10).trim();
        return month+' '+day;
    }

    public HashMap<String, ArrayList<String>> iPsForDays() {
         HashMap<String,ArrayList<String>> dateIPs = new HashMap<>();
         for (LogEntry le : records) {
             String date = getDate(le);
             String ip = le.getIpAddress();
             if (! dateIPs.containsKey(date)) {
                 ArrayList<String> ips = new ArrayList<>();
                 ips.add(ip);
                 dateIPs.put(date, ips);
             } else {
                 // if (! dateIPs.get(date).contains(ip)) { add this condition to count visits regardless of IP
                     dateIPs.get(date).add(ip);
                 //}
             }
         }
         return dateIPs;
    }

    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> dateIPs) {
         int max = 0;
         String dayWithMostVisits = "";
         for (String date : dateIPs.keySet()) {
             if (dateIPs.get(date).size() > max) {
                 max = dateIPs.get(date).size();
                 dayWithMostVisits = date;
             }
         }
         return dayWithMostVisits;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> dateIPs, String date) {
        ArrayList<String> ipsMostVisitsOnDay = new ArrayList<>();
         for (String d : dateIPs.keySet()) {
             if (d.equals(date)) {
                 HashMap<String, Integer> ipCount = new HashMap<>();
                 for (String ip : dateIPs.get(d)) {
                     if (! ipCount.containsKey(ip)) {
                         ipCount.put(ip, 1);
                     } else {
                         ipCount.put(ip, ipCount.get(ip) + 1);
                     }
                 }
                 ipsMostVisitsOnDay = iPsMostVisits(ipCount);
             }
         }
         return ipsMostVisitsOnDay;
    }
}
