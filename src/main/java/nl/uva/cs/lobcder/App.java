package nl.uva.cs.lobcder;

import nl.uva.cs.lobcder.cache.ACRCache;
import nl.uva.cs.lobcder.cache.RRCache;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import nl.uva.cs.lobcder.cache.MFUCache;
import nl.uva.cs.lobcder.cache.LFUCache;
import nl.uva.cs.lobcder.cache.LRUCache;
import nl.uva.cs.lobcder.cache.MRUCache;

public class App {

    static int maxSize = 4096;

    public static void main(String[] args) {
        try {
            double averageFileSizeInGB = 0;
            String requestsFile;
            maxSize = Integer.valueOf(args[2]);
            int cSize = -1;
            if (args.length > 3 && args[3] != null) {
                cSize = Integer.valueOf(args[3]);
            }
            if (args[0].equals("Krakow")) {
                System.err.println("Krakow");
                run(args[1], System.getProperty("user.home") + "/Downloads/req_krak", 0.005431652024, cSize);
            } else if (args[0].equals("Sheffield")) {
                System.err.println("Sheffield");
                averageFileSizeInGB = 0.012958937164;
                requestsFile = System.getProperty("user.home") + "/Downloads/req_sheffild";
                run(args[1], requestsFile, averageFileSizeInGB, cSize);
            } else if (args[0].equals("Oxford")) {
                System.err.println("Oxford");
                averageFileSizeInGB = 6.99413654e-5;
                requestsFile = System.getProperty("user.home") + "/Downloads/req_oxford";
                run(args[1], requestsFile, averageFileSizeInGB, cSize);
            } else if (args[0].equals("Amsterdam")) {
                System.err.println("Amsterdam");
                averageFileSizeInGB = 0.0102568017228;
                requestsFile = System.getProperty("user.home") + "/Downloads/req_ams";
                run(args[1], requestsFile, averageFileSizeInGB, cSize);
            } else if (args[0].equals("test")) {
                System.err.println("test");
                requestsFile = System.getProperty("user.home") + "/Downloads/req_test";
                run(args[1], requestsFile, averageFileSizeInGB, cSize);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private static void testLFU(String requestsFile, double averageFileSizeInGB, int cSize) throws FileNotFoundException, IOException, InterruptedException {
        LFUCache cache;

        System.err.println("testLFU");
        int cacheSizeINGB;
        cacheSizeINGB = 0;
        double cacheSize;
        if (cSize < 0) {
            cacheSizeINGB = 0;
            cacheSize = cacheSizeINGB / averageFileSizeInGB;
        } else {
            cacheSize = cSize;
        }

        cache = new LFUCache((int) cacheSize);

        RequestHandler r = new RequestHandler(cache, requestsFile);
        r.start();
        System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());

        for (int i = 1; i < maxSize; i = i * 2) {
            if (cSize < 0) {
                cacheSizeINGB = i;
                cacheSize = cacheSizeINGB / averageFileSizeInGB;
            } else {
                cacheSize = cSize;
            }
            cache = new LFUCache((int) cacheSize);
            r = new RequestHandler(cache, requestsFile);
            r.start();
            System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());
            System.gc();
        }
    }

    private static void testMFU(String requestsFile, double averageFileSizeInGB, int cSize) throws FileNotFoundException, IOException, InterruptedException {
        MFUCache cache;

        System.err.println("testMFU");
        int cacheSizeINGB = 0;

        double cacheSize;
        if (cSize < 0) {
            cacheSizeINGB = 0;
            cacheSize = cacheSizeINGB / averageFileSizeInGB;
        } else {
            cacheSize = cSize;
        }

        cache = new MFUCache((int) cacheSize);

        RequestHandler r = new RequestHandler(cache, requestsFile);
        r.start();
        System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());
//        Iterator<String> it = cache.getCache().iterator();
//        while(it.hasNext()){
//            String file = it.next();
//            System.err.println(file);
//        }

                    //        for (int i = 1; i < maxSize; i = i * 2) {
                    //            if (cSize < 0) {
                    //                cacheSizeINGB = i;
                    //                cacheSize = cacheSizeINGB / averageFileSizeInGB;
                    //            } else {
                    //                cacheSize = cSize;
                    //            }
                    //            cache = new MFUCache((int) cacheSize);
                    //            r = new RequestHandler(cache, requestsFile);
                    //            r.start();
                    //            System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());
                    //            System.gc();
                    //        }

    }

    private static void testRR(String requestsFile, double averageFileSizeInGB, int cSize) throws FileNotFoundException, IOException, InterruptedException {
        RRCache cache;

        System.err.println("testRR");
        int cacheSizeINGB;
        cacheSizeINGB = 0;
        double cacheSize;
        if (cSize < 0) {
            cacheSizeINGB = 0;
            cacheSize = cacheSizeINGB / averageFileSizeInGB;
        } else {
            cacheSize = cSize;
        }

        cache = new RRCache((int) cacheSize);

        RequestHandler r = new RequestHandler(cache, requestsFile);
        r.start();
        System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());

        for (int i = 1; i < maxSize; i = i * 2) {
            if (cSize < 0) {
                cacheSizeINGB = i;
                cacheSize = cacheSizeINGB / averageFileSizeInGB;
            } else {
                cacheSize = cSize;
            }
            cache = new RRCache((int) cacheSize);
            r = new RequestHandler(cache, requestsFile);
            r.start();
            System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());
            System.gc();
        }
    }

    private static void testACR(String requestsFile, double averageFileSizeInGB, int cSize) throws FileNotFoundException, IOException, InterruptedException {
        ACRCache cache;

        System.err.println("testACR");
        int cacheSizeINGB;
        cacheSizeINGB = 0;
        double cacheSize;
        if (cSize < 0) {
            cacheSizeINGB = 0;
            cacheSize = cacheSizeINGB / averageFileSizeInGB;
        } else {
            cacheSize = cSize;
        }

        cache = new ACRCache((int) cacheSize);

        RequestHandler r = new RequestHandler(cache, requestsFile);
        r.start();
        System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());

        for (int i = 1; i < maxSize; i = i * 2) {
            if (cSize < 0) {
                cacheSizeINGB = i;
                cacheSize = cacheSizeINGB / averageFileSizeInGB;
            } else {
                cacheSize = cSize;
            }
            cache = new ACRCache((int) cacheSize);
            r = new RequestHandler(cache, requestsFile);
            r.start();
            System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());
            System.gc();
        }
    }

    private static void testLRU(String requestsFile, double averageFileSizeInGB, int cSize) throws FileNotFoundException, IOException, InterruptedException {
        LRUCache cache;

        System.err.println("testLRU");
        int cacheSizeINGB;
        cacheSizeINGB = 0;
        double cacheSize;
        if (cSize < 0) {
            cacheSizeINGB = 0;
            cacheSize = cacheSizeINGB / averageFileSizeInGB;
        } else {
            cacheSize = cSize;
        }

        cache = new LRUCache((int) cacheSize);

        RequestHandler r = new RequestHandler(cache, requestsFile);
        r.start();
        System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());

        for (int i = 1; i < maxSize; i = i * 2) {
            if (cSize < 0) {
                cacheSizeINGB = i;
                cacheSize = cacheSizeINGB / averageFileSizeInGB;
            } else {
                cacheSize = cSize;
            }
            cache = new LRUCache((int) cacheSize);
            r = new RequestHandler(cache, requestsFile);
            r.start();
            System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());
            System.gc();
        }
    }

    private static void testMRU(String requestsFile, double averageFileSizeInGB, int cSize) throws FileNotFoundException, IOException, InterruptedException {
        MRUCache cache;

        System.err.println("testMRU");
        int cacheSizeINGB;
        cacheSizeINGB = 0;
        double cacheSize;
        if (cSize < 0) {
            cacheSizeINGB = 0;
            cacheSize = cacheSizeINGB / averageFileSizeInGB;
        } else {
            cacheSize = cSize;
        }

        cache = new MRUCache((int) cacheSize);

        RequestHandler r = new RequestHandler(cache, requestsFile);
        r.start();
        System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());

        for (int i = 1; i < maxSize; i = i * 2) {
            if (cSize < 0) {
                cacheSizeINGB = i;
                cacheSize = cacheSizeINGB / averageFileSizeInGB;
            } else {
                cacheSize = cSize;
            }
            cache = new MRUCache((int) cacheSize);
            r = new RequestHandler(cache, requestsFile);
            r.start();
            System.err.println((int) cacheSize + ", " + cacheSizeINGB + ", " + r.getHitRatio());
            System.gc();
        }
    }

    private static void run(String alogrithm, String requestsFile, double averageFileSizeInGB, int cSize) throws FileNotFoundException, IOException, InterruptedException {

        if (alogrithm.equals("LFU")) {
            testLFU(requestsFile, averageFileSizeInGB, cSize);
        } else if (alogrithm.equals("MFU")) {
            testMFU(requestsFile, averageFileSizeInGB, cSize);
        } else if (alogrithm.equals("RR")) {
            testRR(requestsFile, averageFileSizeInGB, cSize);
        } else if (alogrithm.equals("LRU")) {
            testLRU(requestsFile, averageFileSizeInGB, cSize);
        } else if (alogrithm.equals("MRU")) {
            testMRU(requestsFile, averageFileSizeInGB, cSize);
        }
    }
}
