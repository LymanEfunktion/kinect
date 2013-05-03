package test;

abstract class Printer {
	 
    protected PrintingImpl impl;
 
    public Printer(PrintingImpl impl) {
        this.impl = impl;
    }
 
    public abstract void print();
 
    public PrintingImpl getImpl() {
        return impl;
    }
 
    public void setImpl(PrintingImpl impl) {
        this.impl = impl;
    }
 
}
 
class APrinter extends Printer {
 
    public APrinter(PrintingImpl impl) {
        super(impl);
    }
 
    @Override
    public void print() {
        impl.print("A");
    }
 
}
 
class BPrinter extends Printer {
 
    public BPrinter(PrintingImpl impl) {
        super(impl);
    }
 
    @Override
    public void print() {
        impl.print("B");
    }
 
}
 
interface PrintingImpl {
 
    public void print(String what);
 
}
 
class PlainTextPrintingImpl implements PrintingImpl {
 
    @Override
    public void print(String what) {
        System.out.println(what);
    }
 
}
 
class HTMLPrintingImpl implements PrintingImpl {
 
    @Override
    public void print(String what) {
        System.out.println("<p>\n\t<em>" + what + "</em>\n</p>");
    }
 
    public static void main(String[] args) {
        Printer printer;
 
        PrintingImpl plainImpl = new PlainTextPrintingImpl();
        PrintingImpl htmlImpl = new HTMLPrintingImpl();
 
        printer = new APrinter(plainImpl);
        printer.print();
 
        /* Die PrintingImpl kann problemlos zur Laufzeit ausgetauscht
         * werden, da die Implementierung von der Abstraktion
         * entkoppelt ist. */
        printer.setImpl(htmlImpl);
        printer.print();
 
        /* Genauso kann (ähnlich wie beim Strategy-Pattern) der
         * Printer selbst zur Laufzeit geändert werden. */
        printer = new BPrinter(plainImpl);
        printer.print();
 
        printer.setImpl(htmlImpl);
        printer.print();
    }
 
}