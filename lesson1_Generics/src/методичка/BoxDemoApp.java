package методичка;

public class BoxDemoApp {
    public static void main(String[] args) {
        методичка.SimpleBox intBox1 = new методичка.SimpleBox(20);
        методичка.SimpleBox intBox2 = new методичка.SimpleBox(30);

        if (intBox1.getObj() instanceof Integer && intBox2.getObj() instanceof Integer) {
            int sum = (Integer) intBox1.getObj() + (Integer) intBox2.getObj();
            System.out.println(sum);
        } else {
            System.out.println("Содержимое коробок отличиется по типу");
        }

        intBox1.setObj("Java");
        int sum = (Integer) intBox1.getObj() + (Integer) intBox2.getObj();

    }
}
