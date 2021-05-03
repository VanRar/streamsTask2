import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        //пока сократим до 100, что бы можно было глянуть как работает
        for (int i = 0; i < 100; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //глянем что там вышло
        System.out.println(persons);

        int countOfMinors = (int) persons.stream()
                .filter(p -> p.getAge() < 18)
                .count();
        System.out.println(countOfMinors);

        List<String> listOfConscript = persons.stream()
                .filter(p -> p.getSex() == Sex.MAN)
                .filter(p -> p.getAge() > 18 & p.getAge() < 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());
        System.out.println(listOfConscript);

        List<Person> listWorkable = persons.stream()
                .filter(p -> p.getEducation() == Education.HIGHER)
                .filter(p -> (p.getSex()==Sex.WOMEN & p.getAge() > 18 & p.getAge() < 60)|(p.getSex() ==Sex.MAN & p.getAge() > 18 & p.getAge() < 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println();
        System.out.println(listWorkable);
    }
}
