/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.cs.lobcder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import nl.uva.cs.lobcder.cache.Cache;

/**
 *
 * @author S. Koulouzis
 */
class RequestHandler {

    private final Cache cache;
    private final String requestsFile;
    private double hitCount;
    private double requestCount;

    RequestHandler(Cache cache, String requestsFile) {
        this.cache = cache;
        this.requestsFile = requestsFile;
    }

    void start() throws FileNotFoundException, IOException, InterruptedException {
        hitCount = 0;
        requestCount = 0;
//        try (BufferedReader br = new BufferedReader(new FileReader(requestsFile))) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(requestsFile));
            String line;
            while ((line = br.readLine()) != null) {
//                Thread.sleep(5);

                boolean hit = cache.getFile(line);
                if (!hit) {
                    cache.putFile(line);
                } else {
                    hitCount++;
                }
                requestCount++;
//                System.err.println("Line: " + line);
//                System.err.println("Hit: " + hit);
//                System.err.println("cache: " + cache);
//                System.err.println("-------------------");
            }
        } finally {
            br.close();
        }
    }

    double getHitRatio() {

        return (hitCount / requestCount) * 100.0;
    }
}
