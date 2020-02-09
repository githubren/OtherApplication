// BookManager.aidl
package com.example.computer_ren.androidipc_demo;

import com.example.computer_ren.androidipc_demo.Book;

// Declare any non-default types here with import statements

interface BookManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
//    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
//            double aDouble, String aString);

    List<Book> getBooks();

    void addBook(in Book book);
}
