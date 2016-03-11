package com.instant.service.clickouts;

/**
 * @author sroshchupkin
 */
class MutableInt {
    int value = 1;
    public void increment () { ++value;      }
    public int  get ()       { return value; }
}