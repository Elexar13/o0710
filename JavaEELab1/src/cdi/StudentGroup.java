package cdi;


import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.swing.*;
import java.util.Map;


interface Group{
    void setStudentsMark(Map<Student, Integer> dictionary);
}

@ApplicationScoped
@GroupInterceptor
public class StudentGroup implements Group {
    @Inject
    int number;

    @Inject
    @GoodStudentAnnotation
    Student goodStudent;

    @Inject
    @GoodStudentAnnotation
    Student badStudent;

    public StudentGroup() {

    }

    public int getNumber() {
        return number;
    }

    public Student getGoodStudent() {
        return goodStudent;
    }

    public Student getBadStudent() {
        return badStudent;
    }

    @Override
    public void setStudentsMark(Map<Student, Integer> dictionary) {
        dictionary.put(goodStudent, 5);
        dictionary.put(badStudent, 3);
    }
    @GroupInterceptor
    public int getMarkOfGoodStudent(Map<Student, Integer> dictionary){
        return dictionary.get(goodStudent);
    }
    @GroupInterceptor
    public int getMarkOfBadStudent(Map<Student, Integer> dictionary){
        return dictionary.get(badStudent);
    }
}

class Producer{
    @Produces
    int number = 303;
}

