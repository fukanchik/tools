package ru.fuxx;

import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Repack {
    private static void usage() {
        System.out.println("Usage:\nRepack --stat input.xml | --trie | --index | --list input.xml output.txt | --hash titles");
        System.out.println("\t--list - save list of page titles");

        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        if (0 == args.length) {
            usage();
        }

        final SAXParserFactory factory = SAXParserFactory.newInstance();
        final SAXParser parser = factory.newSAXParser();

        DefaultHandler handler;
        String input;
        if (args[0].equalsIgnoreCase("--list")) {
            if (args.length != 3) {
                usage();
            }
            input = args[1];
            handler = new TitleListHandler(args[2]);
        } else {
            input = null;
            handler = null;
            usage();
        }
        parser.parse(input, handler);
    }
}
