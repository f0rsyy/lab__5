package company;

public class Main {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Inject injector = new Inject();
        MainTest test = injector.inject(new MainTest());
        test.print();
    }
}
