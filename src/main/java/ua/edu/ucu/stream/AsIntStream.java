package ua.edu.ucu.stream;

import ua.edu.ucu.function.IntBinaryOperator;
import ua.edu.ucu.function.IntConsumer;
import ua.edu.ucu.function.IntPredicate;
import ua.edu.ucu.function.IntToIntStreamFunction;
import ua.edu.ucu.function.IntUnaryOperator;

import java.util.ArrayList;
import java.util.Iterator;


public class AsIntStream implements IntStream, Iterable<Integer> {
    private Iterator<Integer> stream;
    private ArrayList<Integer> array;


    private AsIntStream() {
        // To Do
        this.array = new ArrayList<>();
    }

    private AsIntStream(int... values) {
        this.array = new ArrayList<>();
        for (int value: values) {
            this.array.add(value);
        }
    }


    public static IntStream of(int... values) {
        return new AsIntStream(values);
    }

    @Override
    public Double average() {
        double sum = 0;
        int size = 0;
        for (int val : this) {
            sum += val;
            size++;
        }
        return sum/size;
    }

    @Override
    public Integer max() {
        return this.reduce(Integer.MIN_VALUE, Math::max);
    }

    @Override
    public Integer min() {
        return this.reduce(Integer.MAX_VALUE, Math::min);
    }

    @Override
    public long count() {
        return this.reduce(0, (length, a) -> length + 1);
    }

    @Override
    public Integer sum() {
        return this.reduce(0, Integer::sum);
    }


    @Override
    public Iterator<Integer> iterator() {
        if (this.stream == null) {
            return array.iterator();
        }
        return stream;
    }


    @Override
    public IntStream filter(IntPredicate predicate) {
        this.stream = new Iterator<Integer>() {
            private Iterator<Integer> iterat = iterator();

            @Override
            public boolean hasNext() {
                return iterat.hasNext();
            }

            @Override
            public Integer next() {
                Integer next = iterat.next();
                while (!predicate.test(next)) {
                    next = iterat.next();
                }
                return next;
            }
        };

        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        for (int value : this) {
            action.accept(value);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        this.stream = new Iterator<Integer>() {

            private final Iterator<Integer> iterat = iterator();

            @Override
            public boolean hasNext() {
                return iterat.hasNext();
            }
            @Override
            public Integer next() {
                return mapper.apply(iterat.next());
            }

        };
        return this;
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        stream = new Iterator<Integer>() {
            private final Iterator<Integer> iterat = iterator();
            private Iterator<Integer> cur;
            @Override
            public boolean hasNext() {
                while (cur == null
                        || !cur.hasNext()) {
                    if (!iterat.hasNext()) {
                        return false;
                    }
                    cur = ((AsIntStream) func.
                            applyAsIntStream(iterat.next())).iterator();
                }
                return true;
            }
            @Override
            public Integer next() {
                return cur.next();
            }

        };
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        int el = identity;
        int len = 0;

        for (int value : this) {
            el = op.apply(el, value);
            len++;
        }

        return el;
    }

    @Override
    public int[] toArray() {
        int[] arr = new int[(int) count()];
        int i = 0;
        for (int val : this.array) {
            arr[i++] = val;
        }
        return arr;
    }

}
