package com.twu.biblioteca.bookitem;

import com.twu.biblioteca.MessageInformation;
import com.twu.biblioteca.elementusage.ElementManagement;
import com.twu.biblioteca.movieitem.Movie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BookManagement implements ElementManagement<Book>  {

    private BookManagement() {}

    private static BookManagement bookManagement = new BookManagement();

    public static BookManagement getBookManagement() {
        return bookManagement;
    }

    public List<Book> initializeBookList() {
        // make a new book list
        List<Book> books = new ArrayList<>(Arrays.asList(
                new Book("A Midsummer Night's Dream", "W. William Shakespeare", 1600),
                new Book("Gone With the Wind", "Margaret Mitchell", 1936),
                new Book("Les Misérables", "Victor Hugo", 1862),
                new Book("The Decameron", "Giovanni Boccaccio", 1353),
                new Book("The Little Prince", "Antoine de Saint-Exupéry", 1942)
        ));

        return books;
    }

    @Override
    public void viewList(List<Book> books) {
        for (int i = 0; i < books.size(); i++) {
            Book book = books.get(i);
            if (!book.getIsChecked()) {
                System.out.println((i + 1) + " | " + book.getName() + " | "
                        + book.getAuthor() + " | " + book.getPublicationYear());
            }
        }
    }

    @Override
    public void checkoutElement(List<Book> books, int elementNumber) {
        if (elementNumber <= books.size() && !books.get(elementNumber - 1).getIsChecked()) {
            books.get(elementNumber - 1).setIsChecked(true);
        } else if (books.get(elementNumber - 1).getIsChecked()) {
            MessageInformation.getMessageInformation().showCheckoutBookUnsuccessfully();
        }
    }

    public void viewBookList(List<Book> books) {
        viewList(books);
    }

    // suppose there's a number sticky on the book
    public void checkoutBook(List<Book> books, int bookNumber) {
        checkoutElement(books, bookNumber);
    }

    public void returnBook(List<Book> books, int bookNumber) {
        if (bookNumber <= books.size() && books.get(bookNumber - 1).getIsChecked()) {
            books.get(bookNumber - 1).setIsChecked(false);
            MessageInformation.getMessageInformation().showReturnBookSuccessfully();
        } else if (!books.get(bookNumber - 1).getIsChecked()) {
            MessageInformation.getMessageInformation().showReturnBookUnsuccessfully();
        }
    }
}
