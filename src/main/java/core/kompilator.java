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
import java.io.StringWriter;

public class kompilator {
    static public void main(String[] argv) {
        try {
            ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
            parser p = new parser(new Lexer(new FileReader(argv[0]), symbolFactory), symbolFactory);
            Program result = (Program) p.parse().value;

            StringWriter outputWithLabels = new StringWriter();
            PrintWriter writerToString = new PrintWriter(outputWithLabels);
            Memory memory = new Memory();
            ForVisitor forVisitor = new ForVisitor(memory);
            result.accept(forVisitor);
            MainVisitor mainVisitor = new MainVisitor(writerToString, memory);
            result.accept(mainVisitor);
            writerToString.close();

//            System.out.println(outputWithLabels.toString());

            PostProcessor postProcessor = new PostProcessor();
            String outputWithoutLabels = postProcessor.postProcess(outputWithLabels.toString());

            PrintWriter writerToFile = new PrintWriter(argv[1]);
            writerToFile.print(outputWithoutLabels);
            writerToFile.close();

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
        System.out.println("Usage: java core.kompilator <inputfile> <outputFile>");
    }
}


