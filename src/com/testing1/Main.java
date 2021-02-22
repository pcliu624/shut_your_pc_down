package com.testing1;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.Math;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;




public class Main {
  private static Date date2 = null;
  private static Date date1 = null;
  private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static long diffInsec = 0;

  public static void main(String[] args) {
    InputForm mainFrame = new InputForm();
    mainFrame.setVisible(true);
    //input datetime

    System.out.println("input date time by following format: yyyy-MM-dd HH:mm:ss");
  }
    public static void dateInput(String dateinput) {

        try {
          date2 = sdf.parse(dateinput);
          System.out.println(date2);
        } catch (ParseException e) {
          System.out.println("input is not valid");
          JOptionPane.showMessageDialog(null, "input is not valid!");
        }

      currentTime();
      if (date2.getTime() > date1.getTime()) {
        caldiff();
        command();

      } else {
        System.out.println("input is not valid");
       JOptionPane.showMessageDialog(null,"input is not valid!");

      }
    }


    public static void caldiff () {
      diffInsec = Math.abs((date2.getTime() - date1.getTime()) / 1000);
      System.out.println(diffInsec);
    }

    public static void currentTime () {
      //current datetime

      date1 = new Date(System.currentTimeMillis());
      System.out.println("Current time is: " + sdf.format(date1));
    }
    public static void cancelShutdown(){
    try{
      Process process = Runtime.getRuntime().exec("cmd /c shutdown -a");
      JOptionPane.showMessageDialog(null,"Cancelled!");

    }catch(IOException e){
      e.printStackTrace();
      }
    }

    public static void command () {
      try {
        Process process = Runtime.getRuntime().exec("cmd /c shutdown -s -t " + diffInsec);
        StringBuilder output = new StringBuilder();

        BufferedReader reader = new BufferedReader(
          new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
          output.append(line + "\n");
        }
        int exitVal = process.waitFor();
        if (exitVal == 0) {
          System.out.println("Success!");
          System.out.println(output);
          System.exit(0);
        } else {
          //abnormal...
        }

      } catch (IOException e) {
        e.printStackTrace();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

    }

}
