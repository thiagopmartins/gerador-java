 package br.com.gerador;
 
 import java.util.concurrent.TimeUnit;
 
 public class Time {
   public static void getTime(long seconds) {
     int day = (int)TimeUnit.SECONDS.toDays(seconds);
     long hours = TimeUnit.SECONDS.toHours(seconds) - day * 24;
     long minute = TimeUnit.SECONDS.toMinutes(seconds) - TimeUnit.SECONDS.toHours(seconds) * 60L;
     long second = TimeUnit.SECONDS.toSeconds(seconds) - TimeUnit.SECONDS.toMinutes(seconds) * 60L;
     if (day > 0) {
       System.out.println("Estimativa: " + day + " dias " + hours + " h " + minute + " m " + second + " s");
     } else if (hours > 0L) {
       System.out.println("Estimativa: " + hours + " h " + minute + " m " + second + " s");
     } else if (minute > 0L) {
       System.out.println("Estimativa: " + minute + " m " + second + " s");
     } else {
       System.out.println("Estimativa: " + second + " s");
     }
   }
 }