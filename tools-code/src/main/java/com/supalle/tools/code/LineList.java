package com.supalle.tools.code;

import java.util.*;

/**
 * @author Supalle
 */
public class LineList<E> extends AbstractLinkedArrayList<E> {

    private Object[] headElements;
    private Object[] tailElements;
    private int size;
    private int lastIndex;

    private final int lineSize;


    public LineList() {
        this(ELEMENT_SIZE - 1);
    }

    public LineList(int lineSize) {
        if (lineSize < 1) {
            throw new IllegalArgumentException("The LineList's line Size must be > 1");
        }
        this.lineSize = lineSize;
        clear();
    }

    public LineList(Collection<? extends E> collection) {
        this();
        addAll(collection);
    }

    public LineList(int lineSize, Collection<? extends E> collection) {
        this(lineSize);
        addAll(collection);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<E> iterator() {
        return new LineListIterator<>(headElements);
    }

    @Override
    public Object[] toArray() {
        final Object[] array = new Object[size];
        int i = 0;
        for (E e : this) {
            array[i++] = e;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        int length;
        if (a == null || (length = a.length) == 0) {
            return (T[]) toArray();
        }
        int i = 0;
        final Object[] array = new Object[size];
        for (E e : this) {
            if (i >= length) {
                break;
            }
            array[i++] = e;
        }
        return (T[]) array;
    }

    @Override
    public boolean add(E e) {
        checkSize();
        size++;
        tailElements[lastIndex++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index;
        while ((index = indexOf(o)) != -1) {
            remove(index);
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (isEmpty(c)) {
            return false;
        }
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkRange(index);
        final int size = this.size;
        if (index == size) {
            for (E e : c) {
                add(e);
            }
        }
        final List<E> suffixList = subList(index, size);

        limitOnIndex(index);

        for (E e : c) {
            add(e);
        }
        for (E e : suffixList) {
            add(e);
        }
        return false;
    }

    private void limitOnIndex(int index) {
        final ElementIndex elementIndex = findElementIndex(headElements, index);
        final Object[] elements = elementIndex.getElements();
        final int elementIndexIndex = elementIndex.getIndex();
        final int length = elements.length;
        for (int i = 0; i < length; i++) {
            if (i > elementIndexIndex) {
                elements[i] = EMPTY_ELEMENT;
            }
        }
        tailElements = elements;
        lastIndex = elementIndexIndex + 1;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (isEmpty(c)) {
            return false;
        }
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (isEmpty(c)) {
            clear();
            return false;
        }
        LineList<E> retainItems = new LineList<>();
        for (Object o : c) {
            if (contains(o)) {
                retainItems.add((E) o);
            }
        }
        clear();
        return addAll(retainItems);
    }

    @Override
    public void clear() {
        headElements = newElements();
        tailElements = headElements;
        size = 0;
        lastIndex = 0;
    }

    // 26                     15                     4
    // 0 1 2 3 4 5 6 7 8 9 10 11
    //                        0 1 2 3 4 5 6 7 8 9 10 11
    //                                               0 1 2 3 4 5 6 7 8 9 10 11
    @Override
    public E get(int index) {
        checkRange(index);
        final ElementIndex elementIndex = findElementIndex(headElements, index);
        return (E) elementIndex.getElement();
    }


    @Override
    public E set(int index, E element) {
        checkRange(index);
        final ElementIndex elementIndex = findElementIndex(headElements, index);
        return (E) elementIndex.setElement(element);
    }

    @Override
    public void add(int index, E element) {
        if (index == size) {
            add(element);
            return;
        }
        checkRange(index);
        final ElementIndex elementIndex = findElementIndex(headElements, index);
        final int elementIndexIndex = elementIndex.getIndex();
        final Object[] elements = elementIndex.getElements();
        final int length = elements.length;

        final Object[] preElements = elementIndex.getPreElements();

        int subLength = length - elementIndexIndex;
        if (length <= lineSize) {
            Object[] newElements = newElements(length);
            if (elementIndexIndex > 0) {
                System.arraycopy(elements, 0, newElements, 0, elementIndexIndex);
            }
            newElements[elementIndexIndex] = element;
            System.arraycopy(elements, elementIndexIndex, newElements, elementIndexIndex + 1, subLength);
            linkElements(preElements, newElements);
            if (elements == tailElements) {
                lastIndex++;
            }
        } else {
            Object[] newElements = newElements(1);
            if (elementIndexIndex == 0) {
                newElements[0] = element;
                newElements[1] = elements;
                linkElements(preElements, newElements);

            } else {

                final int nextElementIndex = getNextElementIndex(elements);
                newElements[0] = elements[nextElementIndex - 1];
                newElements[1] = elements[nextElementIndex];

                System.arraycopy(elements, elementIndexIndex, elements, elementIndexIndex + 1, subLength - 1);
                elements[elementIndexIndex] = element;
                elements[nextElementIndex] = newElements;

                if (elements == tailElements) {
                    lastIndex = 1;
                }
            }
        }
        size++;
    }


    @Override
    public E remove(int index) {
        checkRange(index);
        final ElementIndex elementIndex = findElementIndex(headElements, index);
        final Object[] elements = elementIndex.getElements();
        final Object[] preElements = elementIndex.getPreElements();
        final Object oldVal = elementIndex.getElement();

        if (isSingleElement(elements)) {
            final Object[] nextElements = elementIndex.getNextElements();
            final int nextElementIndex = getNextElementIndex(preElements);
            preElements[nextElementIndex] = nextElements == null ? EMPTY_ELEMENT : nextElements;
            if (elements == tailElements) {
                lastIndex = nextElementIndex;
            }
        } else {

            final int length = elements.length;
            final int elementIndexIndex = elementIndex.getIndex();

            final Object[] newElements = newElements(length - 2);
            if (elementIndexIndex > 0) {
                System.arraycopy(elements, 0, newElements, 0, elementIndexIndex);
            }

            int subSuffixLength = length - elementIndexIndex - 1;

            System.arraycopy(elements, elementIndexIndex + 1, newElements, elementIndexIndex, subSuffixLength);

            preElements[getNextElementIndex(preElements)] = newElements;

            if (elements == tailElements) {
                lastIndex--;
            }
        }
        size--;
        return (E) oldVal;
    }

    @Override
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (E e : this) {
                if (e == null) {
                    return index;
                }
                index++;
            }
            return -1;
        }

        for (E e : this) {
            if (o.equals(e)) {
                return index;
            }
            index++;
        }
        return -1;

    }

    @Override
    public int lastIndexOf(Object o) {
        int index = 0;
        int result = -1;
        if (o == null) {
            for (E e : this) {
                if (e == null) {
                    result = index;
                }
                index++;
            }
            return result;
        }

        for (E e : this) {
            if (o.equals(e)) {
                result = index;
            }
            index++;
        }
        return result;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("listIterator");
    }

    /**
     * 下标要求类似于String.substring()
     */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        final int size = this.size;
        subListRangeCheck(fromIndex, toIndex, size);
        int subLength = toIndex - fromIndex;
        final LineList<E> subList = new LineList<>();
        if (subLength == 0) {
            return subList;
        }

        int index = 0;
        for (E e : this) {
            if (index >= toIndex) {
                break;
            }
            if (index >= fromIndex) {
                subList.add(e);
            }
        }

        return subList;
    }

    static void subListRangeCheck(int fromIndex, int toIndex, int size) {

        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("size:" + size + ",sub list range:0~" + (size) + ",fromIndex:" + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("size:" + size + ",sub list range:0~" + (size) + ",toIndex:" + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
    }


    @Override
    public String toString() {
        final StringJoiner stringJoiner = new StringJoiner(",", "[", "]");
        for (E e : this) {
            stringJoiner.add(String.valueOf(e));
        }
        return stringJoiner.toString();
    }

    private void checkSize() {
        final int nextElementIndex = getNextElementIndex(tailElements);
        if (lastIndex == nextElementIndex) {
            Object[] newElements = newElements();
            tailElements[lastIndex] = newElements;
            tailElements = newElements;
            lastIndex = 0;
        }
    }

    private static int getNextElementIndex(Object[] elements) {
        return elements.length - 1;
    }

    private void linkElements(Object[] preElements, Object[] newElements) {
        if (preElements == null) {
            headElements = newElements;
        } else {
            preElements[getNextElementIndex(preElements)] = newElements;
        }
    }

    private boolean isSingleElement(Object[] elements) {
        return elements.length == 2;
    }

    private Object[] newElements() {
        return newElements(lineSize);
    }

    private static Object[] newElements(final int size) {

        final int elementLineSize = size + 1;

        final Object[] newElements = new Object[elementLineSize];

        for (int i = 0; i < elementLineSize; i++) {
            newElements[i] = EMPTY_ELEMENT;
        }

        return newElements;

    }

    private static Object[] compressElements(Object[] elements) {
        int emptySize = 0;
        final int nextElementIndex = getNextElementIndex(elements);
        for (int i = 0; i < nextElementIndex; i++) {
            if (elements[i] == EMPTY_ELEMENT) {
                emptySize++;
            }
        }
        if (emptySize == 0) {
            return elements;
        }

        Object[] newElements = newElements(nextElementIndex - emptySize);
        for (int i = 0; i < nextElementIndex; i++) {
            if (elements[i] == EMPTY_ELEMENT) {
                emptySize++;
            }
        }

        newElements[getNextElementIndex(newElements)] = elements[nextElementIndex];

        return newElements;
    }

    private ElementIndex findElementIndex(Object[] elements, int index) {
        int nextElementIndex = getNextElementIndex(elements);
        Object[] preElements = null;
        Object nextElements;
        while (index >= nextElementIndex) {
            preElements = elements;
            elements = (Object[]) elements[nextElementIndex];
            index -= nextElementIndex;
            nextElementIndex = getNextElementIndex(elements);
        }
        nextElements = elements[getNextElementIndex(elements)];
        boolean hasNextElements = nextElements != EMPTY_ELEMENT;
        return new ElementIndex(index, elements, preElements, hasNextElements ? (Object[]) nextElements : null);
    }

    private void checkRange(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("size:" + size + ",range:0~" + (size - 1) + ",index:" + index);
        }
    }

    static class LineListIterator<E> implements Iterator<E> {

        private Object[] headElements;
        private int inx = 0;

        LineListIterator(Object[] headElements) {
            this.headElements = headElements;
        }

        @Override
        public boolean hasNext() {
            return headElements[inx] != EMPTY_ELEMENT;
        }

        @Override
        public E next() {
            Object[] elements = this.headElements;
            int inx = this.inx;
            Object element = elements[inx];
            if (element == EMPTY_ELEMENT) {
                return null;
            }

            final int nextElementIndex = getNextElementIndex(elements);
            if (inx >= nextElementIndex) {
                elements = (Object[]) element;
                inx = 0;
                element = elements[inx];
                this.headElements = elements;
            }
            this.inx = ++inx;
            return (E) element;
        }
    }

    /**
     * 泯灭集合的迭代器 —— 每次迭代，都会以最小代价释放集合占用的内存空间
     */
    class VanishLineListIterator<E> implements Iterator<E> {

        private int inx = 0;

        @Override
        public boolean hasNext() {
            return LineList.this.headElements[inx] != EMPTY_ELEMENT;
        }

        @Override
        public E next() {
            Object[] elements = LineList.this.headElements;
            int inx = this.inx;
            Object element = elements[inx];
            if (element == EMPTY_ELEMENT) {
                LineList.this.headElements = null;// Vanish
                LineList.this.tailElements = null;// Vanish
                return null;
            }

            elements[inx] = null;// Vanish

            final int nextElementIndex = getNextElementIndex(elements);
            if (inx >= nextElementIndex) {
                elements = (Object[]) element;
                inx = 0;
                element = elements[inx];
                LineList.this.headElements = elements;

                elements[inx] = null;// Vanish
            }
            this.inx = ++inx;
            return (E) element;
        }
    }

    private Iterator<E> vanishIterator() {
        return new VanishLineListIterator<>();
    }

    public static <E> Iterator<E> vanishIterator(Iterable<E> lineList) {
        if (lineList == null) {
            return Collections.emptyIterator();
        }
        if (lineList instanceof LineList) {
            return ((LineList<E>) lineList).vanishIterator();
        }
        return lineList.iterator();
    }

    public static <E> LineList<E> of(E... e) {
        final LineList<E> lineList = new LineList<>();
        return of(lineList, e);
    }

    public static <E> LineList<E> of(LineList<E> lineList, E... e) {
        if (lineList == null) {
            lineList = new LineList<>();
        }
        if (e != null && e.length > 0) {
            for (E item : e) {
                lineList.add(item);
            }
        }
        return lineList;
    }

    public static <E> LineList<E> of(Collection<? extends E> collection) {
        return new LineList<>(collection);
    }

    public static <E> LineList<E> of(Collection<? extends E> collection, int lineSize) {
        return new LineList<>(lineSize, collection);
    }

    public static <E> LineList<E> vanishOf(Collection<? extends E> collection) {
        final LineList<E> lineList = new LineList<>();
        return vanishAddAll(lineList, collection);
    }

    public static <E> LineList<E> vanishOf(int lineSize, Collection<? extends E> collection) {
        final LineList<E> lineList = new LineList<>(lineSize);
        return vanishAddAll(lineList, collection);
    }

    public static <E> LineList<E> vanishAddAll(LineList<E> lineList, Collection<? extends E> collection) {
        if (lineList == null) {
            lineList = new LineList<>();
        }
        if (lineList == collection) {
            collection = new LineList<>(collection);
        }
        final Iterator<? extends E> vanishIterator = LineList.vanishIterator(collection);
        while (vanishIterator.hasNext()) {
            lineList.add(vanishIterator.next());
        }
        return lineList;
    }

}
