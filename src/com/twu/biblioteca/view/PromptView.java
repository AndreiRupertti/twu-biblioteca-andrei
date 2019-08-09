package com.twu.biblioteca.view;

import com.sun.tools.javac.parser.ScannerFactory;

import java.util.Scanner;
import java.util.function.Function;

public abstract class PromptView implements View {
    private Scanner reader;

    public PromptView(Scanner reader) {
        this.reader = reader;
    }

    public String ask(String question) {
        show(question);
        return reader.nextLine();
    }

    public <T> T askAndParse(String question, Function<String, T> parser) {
        show(question);
        return parser.apply(reader.nextLine());
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void show(Object object) {
        System.out.println(object.toString());
    }

    public void goTo(Class view) {
        try {
            View newView = (View) view.getConstructor(Scanner.class).newInstance(reader);
            newView.initialize();
        } catch (Exception e ) {
            e.printStackTrace();
            close();
        }
    }

    public void close() {
        show("Good Bye!");
        reader.close();
    }

    public abstract void initialize();
    public abstract void refresh();
}
