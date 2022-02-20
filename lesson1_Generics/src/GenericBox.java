
// T(ype), E(lement), K(ey), V(alue)
public class GenericBox<X> {
//    public class GenericBox<X, Y, Z> { // при создании такой коробки надо будет указывать все эти типы данных

    /*
    private static X staticField; // НЕЛЬЗЯ объявить статическое поле обобщенного типа. -> Обобщенный тип получаем при создании объекта, а статическое поле общее для всех объектов класса.
     */

    private X obj;

    public GenericBox(X obj) {
        this.obj = obj;
    }

    public X getObj() {
        return obj;
    }

    public void setObj(X obj) {
        this.obj = obj;
    }

    public GenericBox() {


//        obj = new X();// в обобщенном типе НЕЛЬЗЯ создать объект обобщенного типа. Потому что как минимум у объектов разные конструкторы.
//        X[] arr;// МОЖНО создать массив обобщенного типа
//        X[] arr = new X[10]; // НЕЛЬЗЯ задать массив обобщенного типа. Это проистекает из того, что нельзя создать новый объект обобщенного типа.
//
    }

}
