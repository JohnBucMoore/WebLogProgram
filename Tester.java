
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

    public void getTestFiles(LogAnalyzer la) {
        la.readFile("short-test_log");
        //la.readFile("weblog2-short_log");
        //la.readFile("weblog3-short_log");
    }

    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        getTestFiles(la);
        la.printAll();
    }

    public void testCountVisitsPerIP() {
        LogAnalyzer la = new LogAnalyzer();
        getTestFiles(la);
        HashMap<String, Integer> ipCount = la.countVisitsPerIP();
        for (String ipAddr : ipCount.keySet()) {
            System.out.println(ipCount.get(ipAddr)+"\t"+ipAddr);
        }
    }

    public void testMostNumberVisitsByIP() {
        LogAnalyzer la = new LogAnalyzer();
        getTestFiles(la);
        int num = la.mostNumberVisitsByIP(la.countVisitsPerIP());
        System.out.println(num);
    }

    public void testIPsMostVisits() {
        LogAnalyzer la = new LogAnalyzer();
        getTestFiles(la);
        ArrayList<String> ips = la.iPsMostVisits(la.countVisitsPerIP());
        for (String ip : ips) {
            System.out.println(ip);
        }
    }

    public void testIPsForDays() {
        LogAnalyzer la = new LogAnalyzer();
        getTestFiles(la);
        HashMap<String, ArrayList<String>> dateIPs = la.iPsForDays();
        for (String date : dateIPs.keySet()) {
            System.out.println(date+"\t"+dateIPs.get(date));
        }
    }

    public void testDayWithMostIPVisits() {
        LogAnalyzer la = new LogAnalyzer();
        getTestFiles(la);
        System.out.println(la.dayWithMostIPVisits(la.iPsForDays()));
    }

    public void testIPsWithMostVisitsOnDay() {
        LogAnalyzer la = new LogAnalyzer();
        getTestFiles(la);
        System.out.println(la.iPsWithMostVisitsOnDay(la.iPsForDays(), "Sep 30"));
    }

    public static void main(String[] args) {
        Tester t = new Tester();
        t.testLogEntry();
        t.testLogAnalyzer();
        t.testCountVisitsPerIP();
        t.testMostNumberVisitsByIP();
        t.testIPsMostVisits();
        t.testIPsForDays();
        t.testDayWithMostIPVisits();
        t.testIPsWithMostVisitsOnDay();
    }
}
