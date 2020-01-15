package utils;

import core.Lexer;
import core.Main;
import core.MainVisitor;
import core.Visitor;
import cup11b.parser;
import java_cup.runtime.ComplexSymbolFactory;
import syntax.Program;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUtils {
    public static parser prepareParserTest(String input) {
        ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
        return new parser(new Lexer(new StringReader(input), symbolFactory), symbolFactory);
    }

    public static class WriterWithVisitor {
        private Writer writer;
        private Visitor visitor;

        public WriterWithVisitor(Writer writer, Visitor visitor) {
            this.writer = writer;
            this.visitor = visitor;
        }

        public Writer getWriter() {
            return writer;
        }

        public Visitor getVisitor() {
            return visitor;
        }
    }

    public static WriterWithVisitor prepareVisitorTest() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        MainVisitor visitor = new MainVisitor(printWriter);
        return new WriterWithVisitor(printWriter, visitor);
    }

    public static Program prepareFullUnitTest(String input) throws Exception {
        ComplexSymbolFactory symbolFactory = new ComplexSymbolFactory();
        parser p = new parser(new Lexer(new StringReader(input), symbolFactory), symbolFactory);
        return (Program) p.parse().value;
    }

    public static WriterWithVisitor getVisitorAndWriterInstance() {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        MainVisitor visitor = new MainVisitor(printWriter);
        return new WriterWithVisitor(stringWriter, visitor);
    }

    public static List<File> prepareFullIntegrationTest(String inputFile, String outputFile, String expectedFile)
            throws IOException {
        String[] argv = new String[2];
        argv[0] = inputFile;
        argv[1] = outputFile;

        File actual = new File(outputFile);
        actual.delete();
        FileOutputStream fos = new FileOutputStream(outputFile, true);
        System.setOut(new PrintStream(fos));

        Main.main(argv);

        fos.close();

        // test actual is expected
        File expected = new File(expectedFile);
        List<File> list = new ArrayList<>();
        list.add(expected);
        list.add(actual);
        return list;
    }

    public static void checkFileContent(File expected, File actual) throws IOException {
        assertTrue(expected.isFile());
        assertTrue(actual.isFile());

        BufferedReader actualContent = new BufferedReader(new FileReader(actual));
        BufferedReader expectedContent = new BufferedReader(new FileReader(expected));

        for (int lineNumber = 1; lineNumber != -1; lineNumber++) {
            String expectedLine = expectedContent.readLine();
            String actualLine = actualContent.readLine();
            assertEquals("Line " + lineNumber, expectedLine, actualLine);
            if (expectedLine == null) {
                lineNumber = -2; // EOF
            }
        }
    }

    public static void checkFileContent(List<File> list) throws IOException {
        assertTrue(list.get(0).isFile());
        assertTrue(list.get(1).isFile());

        BufferedReader actualContent = new BufferedReader(new FileReader(list.get(1)));
        BufferedReader expectedContent = new BufferedReader(new FileReader(list.get(0)));

        for (int lineNumber = 1; lineNumber != -1; lineNumber++) {
            String expectedLine = expectedContent.readLine();
            String actualLine = actualContent.readLine();
            assertEquals("Line " + lineNumber, expectedLine, actualLine);
            if (expectedLine == null) {
                lineNumber = -2; // EOF
            }
        }
    }
}
