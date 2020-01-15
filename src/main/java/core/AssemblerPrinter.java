package core;

import java.io.PrintWriter;

public class AssemblerPrinter {
    private static PrintWriter writer;

    public AssemblerPrinter(PrintWriter writer) {
        AssemblerPrinter.writer = writer;
    }

    static void HALT() {
        writer.print("HALT" + "\n");
    }
    static void PUT() {
        writer.print("PUT\n");
    }
    static void GET() {
        writer.print("GET\n");
    }
    static void LOAD(Long idx) {
        writer.print("LOAD " + idx + "\n");
    }
    static void STORE(Long idx) {
        writer.print("STORE " + idx + "\n");
    }



}
