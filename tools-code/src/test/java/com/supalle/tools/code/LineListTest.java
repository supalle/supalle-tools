package com.supalle.tools.code;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LineListTest {

    @Test
    void add() {
        final LineList<Integer> lineList = LineList.of(1, 2, 3, 4, 5);
        System.out.println(lineList);
    }

}