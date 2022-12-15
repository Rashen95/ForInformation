import java.lang.reflect.Array;

public class App {

    public static void main(String[] args) {
        new Printer().print(
                new Jober(
                        new DataProvider()
                                .getData())
                                        .job());
    }
}

class Jober {
    int[] data;

    public Jober(int[] input) {
        data = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            data[i] = input[i];
        }
    }

    public int[] job() {
        int max = 200;
        int[] result = new int[max];
        for (int datum : data) {
            result[datum]++;
        }
        return result;
    }
}

class Printer {
    public void print(int[] dict) {
        for (int i = 0; i < dict.length; i++) {
            if (dict[i] != 0) {
                System.out.printf("%d встречается %d\n", i, dict[i]);
            }
        }
    }
}

class DataProvider {
    int[] getData() {
        return new int[]{1, 9, 9, 0, 2, 8, 0, 9};
    }
}