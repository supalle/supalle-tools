package com.supalle.tools.code;

import java.util.Collection;
import java.util.List;

/**
 * @author Supalle
 */
@SuppressWarnings("all")
public abstract class AbstractLinkedArrayList<E> implements List<E> {
    static final int CACHE_LINE_SIZE = 64;
    static final int OBJECT_HEADER_SIZE = 16;
    static final int ELEMENT_SIZE = (CACHE_LINE_SIZE - OBJECT_HEADER_SIZE) / 4;
    static final int NEXT_ELEMENT_INDEX = ELEMENT_SIZE - 1;
    static final Object EMPTY_ELEMENT = new Object() {
        @Override
        public String toString() {
            return "EMPTY_ELEMENT@" + Integer.toHexString(hashCode());
        }
    };
    static final Object[] ELEMENTS_TEMPLATE = new Object[ELEMENT_SIZE];

    static {
        for (int i = 0; i < ELEMENT_SIZE; i++) {
            ELEMENTS_TEMPLATE[i] = EMPTY_ELEMENT;
        }
    }

    protected final boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    protected final boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    static class ElementIndex {
        private final int index;
        private final Object[] elements;

        private final Object[] preElements;
        private final Object[] nextElements;

        public ElementIndex(int index, Object[] elements, Object[] preElements, Object[] nextElements) {
            this.index = index;
            this.elements = elements;
            this.preElements = preElements;
            this.nextElements = nextElements;
        }

        public int getIndex() {
            return index;
        }

        public Object[] getElements() {
            return elements;
        }

        public Object getElement() {
            return elements[index];
        }

        public Object setElement(Object newElement) {
            final Object old = elements[index];
            elements[index] = newElement;
            return old;
        }

        public Object[] getPreElements() {
            return preElements;
        }

        public Object[] getNextElements() {
            return nextElements;
        }
    }


}
