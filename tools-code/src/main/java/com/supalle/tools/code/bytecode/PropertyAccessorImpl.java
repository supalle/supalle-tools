package com.supalle.tools.code.bytecode;

import com.supalle.tools.code.Student;

public class PropertyAccessorImpl implements PropertyAccessor {

    @Override
    public Object get(Object o) {
        return ((Student) o).getObject();
    }

    @Override
    public void set(Object o, Object v) {
        ((Student) o).setObject(v);
    }
}
