package core;

/*
  Commented By: Christopher Lopes
  File Name: core.Main.java
  To Create: 
  After the scanner, lcalc.flex, and the parser, ycalc.cup, have been created.
  > javac core.Main.java
  
  To Run: 
  > java core.Main test.txt
  where test.txt is an test input file for the calculator.
*/

import cup11b.parser;
import java_cup.runtime.ComplexSymbolFactory;
import syntax.Program;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Main {
    static public void main(String[] argv) {
        try {
            ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
            parser p = new parser(new Lexer(new FileReader(argv[0]), symbolFactory), symbolFactory);
            Program result = (Program) p.parse().value;

            PrintWriter writer = new PrintWriter(argv[1]);
            MainVisitor visitor = new MainVisitor(writer);
            result.accept(visitor);
            writer.close();
        } catch (ArrayIndexOutOfBoundsException e) {
            printUsage();
        } catch (FileNotFoundException e) { // changed from IOException
            System.out.println("File not found");
        } catch (Exception e) {
            // TODO: Error handling
            e.printStackTrace();
        }
    }

    private static void printUsage() {
        System.out.println("Usage: java core.Main <inputfile> <outputFile>");
    }
}


