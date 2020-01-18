package core;

import java.io.PrintWriter;

public class AssemblerPrinter {
    private static PrintWriter writer;

    public AssemblerPrinter(PrintWriter writer) {
        AssemblerPrinter.writer = writer;
    }


    static void GET() {
        writer.print("GET" + "\n");
    }
    static void PUT() {
        writer.print("PUT" + "\n");
    }
    static void LOAD(Long idx) {
        writer.print("LOAD " + idx + "\n");
    }
    static void LOAD(Memory.Register reg) {
        writer.print("LOAD " + reg.idx + "\n");
    }
    static void STORE(Long idx) {
        writer.print("STORE " + idx + "\n");
    }
    static void STORE(Memory.Register reg) {
        writer.print("STORE " + reg.idx + "\n");
    }
    static void LOADI(Long idx) {
        writer.print("LOADI " + idx + "\n");
    }
    static void LOADI(Memory.Register reg) {
        writer.print("LOADI " + reg.idx + "\n");
    }
    static void STOREI(Long idx) {
        writer.print("STOREI " + idx + "\n");
    }
    static void STOREI(Memory.Register reg) {
        writer.print("STOREI " + reg.idx + "\n");
    }
    static void ADD(Long idx) {
        writer.print("ADD " + idx + "\n");
    }
    static void ADD(Memory.Register reg) {
        writer.print("ADD " + reg.idx + "\n");
    }
    static void SUB(Long idx) {
        writer.print("SUB " + idx + "\n");
    }
    static void SHIFT(Long idx) {
        writer.print("SHIFT " + idx + "\n");
    }
    static void SHIFT(Memory.Register reg) {
        writer.print("SHIFT " + reg.idx + "\n");
    }
    static void INC() {
        writer.print("INC" + "\n");
    }
    static void DEC() {
        writer.print("DEC" + "\n");
    }
    static void JUMP(Long idx) {
        writer.print("JUMP " + idx + "\n");
    }
    static void JPOS(Long idx) {
        writer.print("JPOS " + idx + "\n");
    }
    static void JZERO(Long idx) {
        writer.print("JZERO " + idx + "\n");
    }
    static void JNEG(Long idx) {
        writer.print("JNEG " + idx + "\n");
    }
    static void HALT() {
        writer.print("HALT" + "\n");
    }



}
