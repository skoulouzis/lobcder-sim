/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.cs.lobcder.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author S. Koulouzis
 */
public class RRCache extends Cache {

    public RRCache(int cacheSize) {
        super(cacheSize, true);
        this.cacheSize = cacheSize;
    }

    @Override
    public void putFile(String key) {
        if (!this.cacheMap.containsKey(key)) {
            if (this.cacheMap.size() >= cacheSize) {
                removeEntry();
            }
            if (cacheSize > 0) {
                cacheMap.put(key, Long.valueOf(1));
            }
        }
    }

    @Override
    public boolean getFile(String key) {
        return getByUsage(key);
    }

    @Override
    protected void removeEntry() {
        if (cacheMap.size() > 0) {
            Random random = new Random();
            List<String> keys = new ArrayList(this.cacheMap.keySet());
            int randIndex = random.nextInt(keys.size());
            String key = keys.get(randIndex);
            this.cacheMap.remove(key);
        }
    }
}
