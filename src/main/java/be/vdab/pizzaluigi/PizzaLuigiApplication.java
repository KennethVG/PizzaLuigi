package be.vdab.pizzaluigi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PizzaLuigiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaLuigiApplication.class, args);

//        List<String> test = Arrays.asList("Apple", "Banana","Orange", "Kiwi");
//        Spliterator<String> s = test.spliterator();
//        Spliterator<String> s1 = s.trySplit();
//
//        s.tryAdvance(System.out::println);
//        //s.forEachRemaining(System.out::println);
//
//        System.out.println("----------------------");
//
//        //s1.forEachRemaining(System.out::println);
//
//        System.out.println(s.hasCharacteristics(Spliterator.SIZED));
//
//        System.out.println("----------------------");
//        System.out.println(s.estimateSize());
//        System.out.println(s1.estimateSize());
//        System.out.println(s.getExactSizeIfKnown());
//        System.out.println(s1.getExactSizeIfKnown());
//
//        Spliterator<String> s2 = Spliterators.spliterator(
//                new String[]{"one", "two"}, 0);
//        s2.forEachRemaining(System.out::println);
    }

}
