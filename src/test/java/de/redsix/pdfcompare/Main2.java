/*
 * Copyright 2016 Malte Finsterwalder
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.redsix.pdfcompare;

//import java.io.IOException;
//import java.time.Duration;
//import java.time.Instant;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.ghost4j.analyzer.AnalysisItem;
import org.ghost4j.analyzer.FontAnalyzer;
import org.ghost4j.document.PDFDocument;
import org.ghost4j.renderer.SimpleRenderer;

public class Main2 {

    public static void main(String[] args){
        try {

            // load PDF document
            PDFDocument document = new PDFDocument();
            document.load(new File("C:\\Users\\om76qc\\IdeaProjects\\ForceTestAutomation\\data\\nasa\\PDF\\SL\\OD 201.pdf"));

            // create renderer
            SimpleRenderer renderer = new SimpleRenderer();

            // set resolution (in DPI)
            renderer.setResolution(96);

            // render
            List<Image> images = renderer.render(document);

            // write images to files to disk as PNG
            try {
                for (int i = 0; i < images.size(); i++) {
                    ImageIO.write((RenderedImage) images.get(i), "png", new File((i + 1) + ".png"));
                }
            } catch (IOException e) {
                System.out.println("ERROR: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//
////        String file1 = "expected.pdf";
////        String file2 = "actual.pdf";
////        String file1 = "/home/malte/projects/Testcases/HML/DirektRente/8257926_1/8257926_1_004.pdf";
////        String file2 = "/home/malte/projects/Testcases/HML/DirektRente/8257926_1/x.pdf";
//        String file1 = "C:\\Users\\om76qc\\IdeaProjects\\ForceTestAutomation\\data\\nasa\\PDF\\SL\\OD 201.pdf";
//        String file2 = "C:\\Users\\om76qc\\IdeaProjects\\ForceTestAutomation\\data\\nasa\\PDF\\Html\\OD 201.pdf";
//
////        CompareResult result = null;
//
//        for (int i = 0; i < 1; i++) {
//            Instant start = Instant.now();
////            final CompareResult result = new DiskUsingCompareResult();
//            final CompareResultImpl result = new CompareResultWithPageOverflow();
//            PdfComparator.setDPI(300);
//            new PdfComparator(file1, file2, result)
////                    .withIgnore("ignore.conf")
//                    .compare().writeTo("out");
//            Instant end = Instant.now();
//            System.out.println("Duration: " + Duration.between(start, end).toMillis() + "ms");
//        }
////        printMemory("finished");
////        if (result.isNotEqual()) {
////            System.out.println("Differences found!");
////        }
////        result.writeTo("test_with_ignore");
////
////        start = Instant.now();
////        final CompareResult result2 = new PdfComparator(file1, file2).compare();
////        end = Instant.now();
////        System.out.println("Duration: " + Duration.between(start, end).toMillis() + "ms");
////        if (result2.isNotEqual()) {
////            System.out.println("Differences found!");
////        }
////        result2.writeTo("test");
//    }


    private static long oldTotal;
    private static long oldFree;

    public static void printMemory(final String s) {
        final long totalMemory = Runtime.getRuntime().totalMemory();
        final long freeMemory = Runtime.getRuntime().freeMemory();
        final long consumed = totalMemory - freeMemory;
        System.out.println("==========================================================================");
        System.out.println("Memory " + s);
        System.out.printf("Total Memory: %6dMB  |  %d\n", toMB(totalMemory), toMB(totalMemory - oldTotal));
        System.out.printf("Free Memory:  %6dMB  |  %d\n", toMB(freeMemory), toMB(freeMemory - oldFree));
        System.out.printf("Consumed:     %6dMB  |  %d\n", toMB(consumed), toMB(consumed - (oldTotal - oldFree)));
        System.out.println("==========================================================================");
        oldTotal = totalMemory;
        oldFree = freeMemory;
    }

    private static long toMB(final long memory) {
        return memory / (1024 * 1024);
    }
}
