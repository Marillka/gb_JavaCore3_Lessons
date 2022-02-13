
public class GenericClass<T> implements GenericInterface<T> {

    @Override // Обобщенный класс, которые реализует обобщенный интерфейс
    public T doSomething(T request) {
        return null;
    }

    /*
    @Override // Работа с конкретным типом
    public String doSomething(String request) {
        return null;
    }
     */

}
