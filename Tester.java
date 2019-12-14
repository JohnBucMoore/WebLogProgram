
/**
 * Write a description of class Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        //la.readFile("weblog2-short_log");
        //la.readFile("weblog3-short_log");
        HashMap<String, Integer> ipCount = la.countVisitsPerIP();
        for (String ipAddr : ipCount.keySet()) {
            System.out.println(ipCount.get(ipAddr)+"\t"+ipAddr);
        }
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testLogEntry();
        t.testLogAnalyzer();
        t.testCountVisitsPerIP();
    }
}
