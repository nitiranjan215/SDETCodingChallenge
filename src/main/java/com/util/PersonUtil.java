package com.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pojo.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersonUtil {

    //Parsing JSON to Java Object
    public static List<Person> convertToObjectFromJson(String jsonInputFile) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> personList = null;
        File file = new File(jsonInputFile);
        try {
            personList = objectMapper.readValue(file, new TypeReference<List<Person>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return personList;
    }

    //Parsing CSV file to Java Object
    public static List<Person> convertToObjectFromCSV(String csvInputFile) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(csvInputFile));
        csvReader.readLine(); //skip row 1 for headers
        String row;
        String[] rowValues;
        Person person;
        List<Person> persons = new ArrayList<>();
        while ((row = csvReader.readLine()) !=null){
            rowValues = row.split(",");
            person = new Person();
            person.setFirstName(rowValues[0]);
            person.setLastName(rowValues[1]);
            person.setAge(Integer.parseInt(rowValues[2]));
            person.setAddress(rowValues[3]);
            person.setSalary(Double.parseDouble(rowValues[4]));
            persons.add(person);
        }
        csvReader.close();
        return persons;
    }

    //Utility function to write data into CSV File
    public static void convertToCSVFromObject(List<Person> personList, String csvOutputFilePath, String csvOutputFile) throws IOException {

        BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvOutputFilePath+csvOutputFile));
        StringBuffer rowData;
        String headerRow = "firstName" +
                "," +
                "lastName" +
                "," +
                "age" +
                "," +
                "address" +
                "," +
                "salary";
        csvWriter.write(headerRow);
        csvWriter.newLine();
        for (Person person : personList){
            rowData = new StringBuffer();
            rowData.append(person.getFirstName());
            rowData.append(",");
            rowData.append(person.getLastName());
            rowData.append(",");
            rowData.append(person.getAge());
            rowData.append(",");
            rowData.append(person.getAddress());
            rowData.append(",");
            rowData.append(person.getSalary());
            csvWriter.write(rowData.toString());
            csvWriter.newLine();
        }
        csvWriter.flush();
        csvWriter.close();
    }
}
