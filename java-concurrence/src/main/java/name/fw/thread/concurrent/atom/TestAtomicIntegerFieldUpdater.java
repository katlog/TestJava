package name.fw.thread.concurrent.atom;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class TestAtomicIntegerFieldUpdater {

    class Person{
        int age;
    }
    @Test
    public void get(){
        Person person = new Person();

        AtomicIntegerFieldUpdater<Person> age = AtomicIntegerFieldUpdater.newUpdater(Person.class, "age");
        age.addAndGet(person, 12);

    }
}
