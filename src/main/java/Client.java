import com.example.Entity.Person;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Client {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:8080/pn/getpb");
        URLConnection connection = url.openConnection();
        InputStream inputStream = connection.getInputStream();
        Scanner scanner = new Scanner(inputStream);
        StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNext()){
            stringBuilder.append(scanner.nextLine());
        }

        System.out.println(stringBuilder);

        Gson gson = new Gson();

        Person[] people = gson.fromJson(stringBuilder.toString(), Person[].class);
        Map<Person, List<String>> persons = new TreeMap<>(new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                return Integer.compare(Integer.parseInt(person.getValue()), Integer.parseInt(t1.getValue()));
            }
        });

        for (Person p: people){
            Person p1 = myContainsKey(persons, p);
            if (p1 != null){
                List<String> list = persons.get(p1);
                list.add(p.getValue());
            } else {
                List<String> list = new ArrayList<>();
                list.add(p.getValue());
                persons.put(p, list);
            }
        }
        System.out.println(persons);
    }

    private static Person myContainsKey(Map<Person, List<String>> p1, Person p2){
        for (Person p: p1.keySet()){
            if (p.getName().equals(p2.getName())){
                return p;
            }
        }
        return null;
    }
}
