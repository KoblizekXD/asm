package org.objectweb.asm.util;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.ByteOrder;

public class LittleEndianifier {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Usage: java org.objectweb.asm.util.LittleEndianifier <class file> <order> <output file>");
            System.exit(1);
        }

        String classFilePath = args[0];
        String order = args[1];
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(classFilePath));
            FileOutputStream outputWriter = new FileOutputStream(args[2])) {
            ClassWriter classWriter = new ClassWriter(new ClassReader(inputStream), 0);
            outputWriter.write(classWriter.toByteArray(order.equalsIgnoreCase("little") ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN));
            System.out.println("Class file processed successfully with " + order + " endian order.");
        } catch (Exception e) {
            throw new RuntimeException("Error processing class file: " + classFilePath, e);
        }
    }
}
