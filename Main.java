package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Main {


    final private static String tablePath = "/home/edtelly/Java/Random Data/";
    final private static String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private static DecimalFormat df = new DecimalFormat("0.00");
    final private static int STRINGLENGTH = 5;

    public static void main(String[] args) throws IOException {
        // write your code here
        String fileName;
        int maxElements;
        int elementCounter = 0;

        String productionCostString;
        String priceAtReleaseString;
        String ratingString;
        double productionCost;
        double priceAtRelease;
        double rating;
        int unitSold;
        int id;
        int idIncrement;



        Scanner scan = new Scanner(System.in);
        Random randomGenerator = new Random();
        ArrayList<String[]> dataTable = new ArrayList<>();




        System.out.print("Number of elements to create");
        maxElements = scan.nextInt();

        System.out.print("Name of file to create");
        fileName = scan.next();

        System.out.print("Id start");
        id = scan.nextInt();

        System.out.print("Id increment");
        idIncrement = scan.nextInt();

        try{
            File outputFile = new File(tablePath + fileName + ".csv");
            if(outputFile.createNewFile()){
                System.out.println("/n/n File Created");
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
        }
        FileWriter fileWriter = new FileWriter(tablePath + fileName + ".csv" );
        BufferedWriter outputWriter = new BufferedWriter(fileWriter);

        int startId = 1000;
        int otherKeyid = 10000;
        int otherKeyIncrement = 34;
        int masterIdIncrement = 3;

       for(int start = 10000; start < 26983; start+= 17) {
            elementCounter = 0;
            while (elementCounter < maxElements) {

                String[] tempArr = new String[STRINGLENGTH];
                tempArr[0] = String.valueOf(startId);
                tempArr[1] = String.valueOf(start);
                tempArr[2] = randomizeString(45, randomGenerator);
                tempArr[3] = randomDate(2020,randomGenerator);
                tempArr[4] = randomizeDouble(1000);


                dataTable.add(tempArr);

                id += idIncrement;
                elementCounter++;
            }
            startId += masterIdIncrement;
        }

    /*  int pID = 10000;int counter = 0;


          for (int i = 501; i < 997; i+= 4) {
              counter++;
              String[] tempArr = new String[STRINGLENGTH];
                tempArr[0] = String.valueOf(i);
                tempArr[1] = String.valueOf(pID);
                tempArr[2] = randomizeDouble(100);
                tempArr[3] = randomDate(2018, randomGenerator);
                tempArr[4] = randomizeString(37, randomGenerator);
                dataTable.add(tempArr);

                if(counter == 7){
                    counter = 0;
                    pID+= 34;
              }

          }
*/
/*        outputWriter.write("productionCost,");
        outputWriter.write("unitsSold,");
        outputWriter.write("genre,");
        outputWriter.write("gameID,");
        outputWriter.write("title,");
        outputWriter.write("priceAtRelease,");
        outputWriter.write("rating,");
        outputWriter.newLine();
*/
            for(int i = 0; i < dataTable.size(); i++) {

                for (int j = 0; j < STRINGLENGTH; j++) {
                    outputWriter.write(dataTable.get(i)[j]);
                    if (j != STRINGLENGTH)
                        outputWriter.write(',');
                }
                outputWriter.newLine();

            }


        outputWriter.flush();
        outputWriter.close();
        fileWriter.close();
    }



    private static String randomizeString(int length,Random randomNumberGenerator){
        StringBuilder randomStringCreator = new StringBuilder(length);
        int randomInt;
        for(int i = 0; i < length; i++){
            randomInt = randomNumberGenerator.nextInt(allowedCharacters.length());
            randomStringCreator.append(allowedCharacters.charAt(randomInt));
        }

        return randomStringCreator.toString();
    }

    private static String randomizeDouble(int increaseRatio){
        double randomDouble = Math.random();
        randomDouble *= increaseRatio;
        return df.format(randomDouble);
    }

    private static String randomizedInt(int bounds, Random randomNumberGenerator){
        int randomInt = randomNumberGenerator.nextInt(bounds);
        return String.valueOf(randomInt + 1);
    }

    private static String randomDate(int earliestYear, Random random){
        String endString;
        int year =  random.nextInt(50);
        year += earliestYear;

        int month = random.nextInt(11);
        month += 1;

        int day;

        if(month == 2){
          day = random.nextInt(27);
          day += 1;
        } else if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 31){
            day = random.nextInt(30);
            day += 1;
        } else {
            day = random.nextInt(29);
            day += 1;
        }

        endString = String.valueOf(year) + '-' + String.valueOf(month) + '-' + String.valueOf(day);
        return endString;
    }

    private static String randomTelephone(Random random){
        int randomNum;
        String finalString ="";

        for(int i = 0; i < 9; i++){
            finalString += String.valueOf(random.nextInt(9) + 1);
        }

        return finalString;
    }


}
