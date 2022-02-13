import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GenericsExample {
    public static void main(String[] args) {
//        simpleBoxExample();
//        genericBoxExample();
//        comparingFloats();
//        numberExample();

        var ints1 = List.of(1, 2, 44);
        var ints2 = List.of(231, 2, 44);
        System.out.println((Integer) getFirstObject(ints1) + (Integer) getFirstObject(ints2));
        System.out.println(getFirstObjectGen(ints1) + getFirstObjectGen(ints2));

//        Collections.copy();
        /*
         Есть класс животных и попугаев. попугаи наследуют животных. в животных можно положить кого угодно. в попугаев только попугаев.
         public static <T> void copy(List<? super T> dest, List<? extends T> src) в который, из которого
        Если мы хотим загнать стаю попугаев в стаю животных - так можно. Назначение - животные. Попугаи - животными не являются, но наследуют класс животных.
        А если наоборот, то не сработает, потому что если мы в стаю попугаев загоняем просто каких то животных - попугайчик является попугайчиком, но животные не являются попугайчиком и не наследуют попугайчика.
         */


    }

    private static Object getFirstObject(List list) {
        return list.get(0);
    }

    private static <T> T getFirstObjectGen(List<T> list) {
        return list.get(0);
    }// ОБОБЩЕННЫЙ МЕТОД

    private static void numberExample() {
        var boxI = new BoxWithNumbers<>(2313, 123, 21313, 131331);
        var boxD = new BoxWithNumbers<>(2313.0, 123.0, 21313.0, 131331.0);
//        var boxS1 = new BoxWithNumbers<>("1000.0");// не получится
//        var boxS2 = new BoxWithNumbers<String>(1000.0); // не получится
        System.out.println(boxI.avg());
//        boxI.setNumbers(new Float[] {1f, 2f});// не сработает


//        System.out.println(boxD.equalsByAvg(boxI));
        /*
        Не дает сравнить коробку даблов с коробкой интов. Потому что он ожидает даблы. Но можно поставить вместо N знак вопроса, это значит что ожидается любой тип
         */
        System.out.println(boxD.equalsByAvg2(boxI));
    }// ОГРАНИЧЕНИЕ СВЕРХУ. НАСЛЕДНИКИ

    private static void comparingFloats() {
        var a = 0.7;
        var b = 0.0;
        for (int i = 0; i < 70; i++) {
            b += 0.01;
        }
        System.out.println(a == b);
        System.out.println("A = " + a + " B = " + b);
        /*
        false
        A = 0.7 B = 0.7000000000000004
         */
    }

    private static void genericBoxExample() {
        var boxInt1 = new GenericBox<>(100500);
        var boxInt2 = new GenericBox<>(1);
        var boxString1 = new GenericBox<>("Hello");
        var boxString2 = new GenericBox<>("World");

        GenericBox<Integer> box = new GenericBox<Integer>(21);// В 5 Java надо было писать вот так.
        GenericBox<Integer> box2 = new GenericBox<>(21);// Потом появилось такая запись. Когда мы сказали что коробка уже Integer - дальше можно это не писать
        GenericBox box3 = new GenericBox(21);// Raw use (сырые дженерики). Если мы не используем данные для коробки - это будет работать точно также как простой Box с Object. В коробке не указали тип - считаем что это Object. НЕ ИСПОЛЬЗОВАТЬ.

        // many code strings
//        boxInt1.setObj("hello");// уже не катит, требуется Integer
//        boxString1.setObj(324);
        // many code strings

        var sum = 0;
        sum = boxInt1.getObj() + boxInt2.getObj();

        System.out.println(sum);// 0


        var boxInt3 = new GenericBox<>();// коробка обобщенная, инициализируем ее пустой. И она будет OBJECT.
        boxInt3.setObj("hello");
        boxInt3.setObj(3);
    }

    private static void simpleBoxExample() {
        var boxInt1 = new Box(100500);
        var boxInt2 = new Box(1);
        var boxString1 = new Box("Hello");
        var boxString2 = new Box("World");

        /*
        var sum = (Integer)boxInt1.getObj() + (Integer) boxInt2.getObj();
        System.out.println(sum);
         */


        /*
        // many code strings
        boxInt1.setObj("hello");
        // many code strings

        var sum = (Integer) boxInt1.getObj() + (Integer) boxInt2.getObj();
        System.out.println(sum);
         */


        // many code strings
        boxInt1.setObj("hello");
        // many code strings

        var sum = 0;
        if (boxInt1.getObj() instanceof Integer && boxInt2.getObj() instanceof Integer) {
            sum = (Integer) boxInt1.getObj() + (Integer) boxInt2.getObj();
        }
        System.out.println(sum);// 0
    }


}
