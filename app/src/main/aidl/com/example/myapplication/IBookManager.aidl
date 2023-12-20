// IBookManager.aidl
package com.example.myapplication;
import com.example.myapplication.Book;

interface IBookManager {
    void addBook(inout Book inBook, inout Book inBook2);
}