package cdi;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;


@RequestScoped
public class University {
    @Inject
    Group group;

    Map<Student, Integer> dictionary;

    public University() {
        dictionary = new HashMap<>();
    }

    public void startStudy(){
        group.setStudentsMark(dictionary);
    }
}

@Decorator
class StudentGroupDecorator implements Group{
    @Inject
    @Delegate
    StudentGroup group;

    Map<Student, Integer> dictionary;
    @Override
    public void setStudentsMark(Map<Student, Integer> dictionary) {
        this.dictionary = dictionary;
        group.setStudentsMark(dictionary);

        System.out.println(group.getMarkOfGoodStudent(dictionary));
        System.out.println(group.getMarkOfBadStudent(dictionary));
    }
}
