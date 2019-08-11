package com.twu.biblioteca.view;

import java.util.Scanner;

public class PromptViewWrapper extends PromptView {
    public static boolean isViewInitialized = false;

    public PromptViewWrapper(Scanner scan) { super(scan);}

    @Override
    public void initialize() { isViewInitialized = true; }
}