/*
   Обобщенная коробка с числами. Сюда можно положить что угодно. Но можно сделать чтобы в эту коробку можно было положить только числа. Для этого можно воспользоваться ограничением.
   -> Эта коробка должна хранить нечто N. Но это нечто N должно обязательно должно быть типом Number или его наследником.
   Это называется ограничение снизу или сверху (смотря с какой стороны посмотреть).
   Класс Number - абстрактный класс в стандартной библиотеке
    */

public class BoxWithNumbers<N extends Number> {


    private N[] numbers;

    public BoxWithNumbers(N... numbers) { // varargs. Сюда мы можем передавать - ничего, один аргумент, несколько аргументов (которые будут преобразованны в массив), массив ( new Integer{32, 34}).
        this.numbers = numbers;
    }

    public N[] getNumbers() {
        return numbers;
    }

    public void setNumbers(N[] numbers) {
        this.numbers = numbers;
    }

    public double avg() {
        return sum() / numbers.length;
    }

    public double sum() {
        var sum = 0.0;
        for (N number : numbers) {
            sum += number.doubleValue();
        }
        return sum;
    }

    public boolean equalsByAvg(BoxWithNumbers<N> another) {
        return Math.abs(avg() - another.avg()) < 0.00001;// для точного сравнения
    }
    public boolean equalsByAvg1(BoxWithNumbers<?> another) {
        return Math.abs(avg() - another.avg()) < 0.00001;
    }
    public boolean equalsByAvg2(BoxWithNumbers<? extends Number> another) {
        return Math.abs(avg() - another.avg()) < 0.00001;
    }

    /*
    extends number говорил нам о том что это может быть Number или тот кто наследует Number
    а в случае с super Number это может быть Number или то что super у Number-а
     */
    public boolean equalsByAvgSuper(BoxWithNumbers<? super Number> another) {//
        return Math.abs(avg() - another.avg()) < 0.00001;
    }
}


