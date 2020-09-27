package cdi;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public abstract class Student {
    public int id;
    public String name;
    public int age;
    boolean isStudy = true;

    public abstract void toStudy();
}


@Qualifier
@Retention(RUNTIME)
@Target({FIELD, TYPE, METHOD})
@interface GoodStudentAnnotation {}


@GoodStudentAnnotation
class GoodStudent extends Student {

    public GoodStudent() {
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsStudy(boolean isStudy){
        this.isStudy = isStudy;
    }

    @Override
    public void toStudy() {
        System.out.println("study well");
    }
}

@Qualifier
@Retention(RUNTIME)
@Target({FIELD, TYPE, METHOD})
@interface BadStudentAnnotation {}

@BadStudentAnnotation
class BadStudent extends Student{

    public BadStudent() {
    }

    public void setID(int id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setIsStudy(boolean isStudy){
        this.isStudy = isStudy;
    }

    @Override
    public void toStudy() {
        System.out.println("study bad");
    }
}




