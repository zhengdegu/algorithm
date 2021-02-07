package com.gu.algorithm.converted;

import java.io.IOException;

/**
 * @author gu
 * @create 2021/1/29 上午10:20
 */
public class BuilderDemo {
}

interface Appendable {

    Appendable append(CharSequence csq) throws IOException;

    Appendable append(CharSequence csq, int start, int end) throws IOException;

    Appendable append(char c) throws IOException;
}

abstract class AbstractStringBuilder implements Appendable, CharSequence {
    char[] value;
    int count;

    AbstractStringBuilder() {
    }

    ;

    public AbstractStringBuilder(int capacity) {
        value = new char[capacity];
    }

    @Override
    public int length() {
        return count;
    }

    public int capacity() {
        return value.length;
    }


}