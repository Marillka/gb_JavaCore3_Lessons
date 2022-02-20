package методичка;

public class GenericsDemoApp {
    public static void main(String[] args) {
        методичка.TestGeneric<String> genStr = new методичка.TestGeneric<>("Hello");
        genStr.showType();
        System.out.println("genSrt.getObject(): " + genStr.getObj());

        методичка.TestGeneric<Integer> genInt = new методичка.TestGeneric<>(140);
        genInt.showType();
        System.out.println("genInt.getObject(): " + genInt.getObj());

        int valueFromGenInt = genInt.getObj();
        String valueFromGenString = genStr.getObj();

//        genInt.setObj("Java");// Ошибка компиляции
    }
}
