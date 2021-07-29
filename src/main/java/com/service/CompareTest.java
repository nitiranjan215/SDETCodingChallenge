package com.service;

import com.pojo.Person;
import com.util.PersonUtil;

import java.io.IOException;
import java.util.*;

public class CompareTest {

    //Input File Path
    public static final String jsonInputFile = "src/main/resources/person.json";
    public static final String csvInputFile = "src/main/resources/person.csv";

    //Output File Path
    public static final String csvOutputFilePath = "src/main/output/";
    public static final String csvOutputFile = "uniquePerson.csv";

    public static void main(String[] args) throws IOException {
        Set<Person> uniquePersons = new HashSet<>();

        //Reading Input JSON File to Java Objects
        List<Person> jsonPersonList = PersonUtil.convertToObjectFromJson(jsonInputFile);
        System.out.println(jsonPersonList);

        //Reading Input CSV File to Java Objects
        List<Person> csvPersonList = PersonUtil.convertToObjectFromCSV(csvInputFile);
        System.out.println(csvPersonList);

        //Adding unique person objects to the HashSet
        uniquePersons.addAll(csvPersonList);
        uniquePersons.addAll(jsonPersonList);
        System.out.println("uniquePersons : "+uniquePersons.size());

        //Sorting the list of unique persons
        List<Person> personList = new ArrayList<>(uniquePersons);
        personList.sort(Comparator.comparing(Person::getSalary));
        System.out.println(personList);

        //Write to CSV File
        PersonUtil.convertToCSVFromObject(personList,csvOutputFilePath,csvOutputFile);

    }
}
