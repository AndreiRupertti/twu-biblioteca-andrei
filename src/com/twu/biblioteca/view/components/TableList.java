package com.twu.biblioteca.view.components;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Stream;

public class TableList {
    private String[] headerLabels;
    private List<String[]> rowsValues;

    public TableList(String... headerLabels) {
        this.headerLabels = headerLabels;
        this.rowsValues = new ArrayList<>();
    }

    public void addRow(String... values) { rowsValues.add(values); }

    public String getTable() {
        String splitter = getLineSplitter(getRow(headerLabels).length()-1);
        return splitter + getHeader() + getRows() + splitter;
    }

    public String getRows() {
        return rowsValues.stream()
                .map((rowValues) -> getRow(rowValues))
                .reduce((fullStr, row) -> fullStr + row)
                .orElse("");
    }

    public String getHeader() {
        String header = getRow(headerLabels);
        return header + getLineSplitter(header.length()-1) ;
    }

    private String getLineSplitter(int length) { return  "+"+new String(new char[length-3]).replace("\0", "-")+"+\n"; }

    private String getRow(String[] values) {
        List<String> rowAsList = Arrays.asList(values);
        String formattedValues = rowAsList.stream()
                .reduce("", (fullStr, value) -> fullStr + String.format("%-"+getColumnWidth(rowAsList.indexOf(value)) + "s | ", value));
        return "| " + formattedValues + "\n";
    }

    private int getColumnWidth(int columnIndex) {
        BinaryOperator<String> longerString = (longerStr, value) -> longerStr.length() > value.length() ? longerStr : value;
        Integer biggestRowLength = rowsValues.stream().map(value -> value[columnIndex] ).reduce(longerString).orElse(" ").length();
        Integer biggestHeaderLength = Arrays.asList(headerLabels).stream().reduce(longerString).orElse(" ").length();
        return (biggestHeaderLength > biggestRowLength ? biggestHeaderLength : biggestRowLength) + 2 ;
    }
}
