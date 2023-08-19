package com.javastudio.tutorial.concurrency;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

class SortTest {
    @Test
    void sort_indices_instead_of_actual_array() throws Exception {
        var list = generateListOfPeople();

        var people = new Person[list.size()];
        list.toArray(people);

        Integer[] indices = IntStream.rangeClosed(1, list.size()).boxed().toArray(Integer[]::new);

        Arrays.sort(indices, Comparator.comparing(index -> people[index - 1],
                Comparator.comparing(Person::lastName).thenComparing(Person::firstName)));

        Arrays.stream(indices).forEach(System.out::println);
    }

    static List<Person> generateListOfPeople() throws ParseException {
        return List.of(
                new Person(1, "Sophia", "Anderson", "1985-10-15"),
                new Person(2, "Jackson", "Martinez", "1993-03-28"),
                new Person(3, "Emily", "Reynolds", "2001-11-09"),
                new Person(4, "Liam", "Patel", "1990-06-07"),
                new Person(5, "Olivia", "Nguyen", "2005-09-22"),
                new Person(6, "Ethan", "Sullivan", "1988-04-03"),
                new Person(7, "Ava", "Khan", "2008-07-17"),
                new Person(8, "Noah", "Thompson", "1999-01-11"),
                new Person(9, "Isabella", "Ramirez", "2003-12-05"),
                new Person(10, "Aiden", "Murphy", "2009-08-20")
        );
    }
}

record Person(Integer id, String firstName, String lastName, Date birthday) {
    private final static String DATE_FORMAT = "yyyy-MM-dd";

    Person(Integer id, String firstName, String lastName, Date birthday) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
    }

    Person(Integer id, String firstName, String lastName, String birthday) throws ParseException {
        this(id, firstName, lastName, new SimpleDateFormat(DATE_FORMAT).parse(birthday));
    }
}
