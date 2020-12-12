package ua.edu.ucu;

import ua.edu.ucu.stream.AsIntStream;
import ua.edu.ucu.stream.IntStream;

public class StreamApp {

    public static int streamOperations(IntStream intStream) {
        //IntStream intStream = AsIntStream.of(-1, 0, 1, 2, 3); // input values
        System.out.println(intStream.filter(x -> x > 0));
        int res = intStream
                .filter(x -> x > 0) // 1, 2, 3
                .map(x -> x * x) // 1, 4, 9
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1))
                // 0, 1, 2, 3, 4, 5, 8, 9, 10
                .reduce(0, (sum, x) -> sum += x); // 42
        return res;
    }

    public static int[] streamToArray(IntStream intStream) {        
        int[] intArr = intStream.toArray();
        return intArr;
    }

    public static String streamForEach(IntStream intStream) {        
        StringBuilder str = new StringBuilder();
        intStream.forEach(x -> str.append(x));
        return str.toString();
    }

    public static double streamAvar(IntStream intStream) {
        return intStream.average();
    }

    public static int streamMin(IntStream intStream) {
        return intStream.min();
    }

    public static int streamMax(IntStream intStream) {
        return intStream.max();
    }

    public static int streamSum(IntStream intStream) {
        return intStream.sum();
    }

    public static long streamCount(IntStream intStream) {
        return intStream.count();
    }
}
