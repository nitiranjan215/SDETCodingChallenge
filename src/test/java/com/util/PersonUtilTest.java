package com.util;

import com.pojo.Person;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonUtilTest {
    //Input File Path
    public static final String jsonInputFile = "src/test/resources/personTest.json";
    public static final String csvInputFile = "src/test/resources/personTest.csv";

    @Test
    public void testConvertToObjectFromJson(){
        Person person1=new Person("Tester1","Bester1",25,"1234 Test Circle",125000);
        Person person2=new Person("Tester2","Bester2",40,"1234 Test Circle",900000);
        List<Person> expectedPersonList = new ArrayList<>();
        expectedPersonList.add(person1);
        expectedPersonList.add(person2);

        List<Person> actualPersonList = PersonUtil.convertToObjectFromJson(jsonInputFile);
        Assert.assertEquals(expectedPersonList,actualPersonList);

    }

    @Test
    public void testConvertToObjectFromCSV() throws IOException {
        Person person1=new Person("Tester2","Bester2",40,"1234 Test Circle",900000);
        Person person2=new Person("Tester3","Bester3",30,"1234 Best Circle",90000);
        List<Person> expectedPersonList = new ArrayList<>();
        expectedPersonList.add(person1);
        expectedPersonList.add(person2);

        List<Person> actualPersonList = PersonUtil.convertToObjectFromCSV(csvInputFile);
        Assert.assertEquals(expectedPersonList,actualPersonList);
    }

}
