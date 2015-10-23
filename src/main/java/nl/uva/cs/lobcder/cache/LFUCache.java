/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.cs.lobcder.cache;

import java.util.Map;
import java.util.Set;

/**
 *
 * @author S. Koulouzis
 */
public class LFUCache extends Cache {

//    private int cacheSize;

    public LFUCache(int cacheSize) {
        super(cacheSize, true);
    }

    @Override
    public void putFile(String key) {
       putWithUsage(key);
    }

    @Override
    public boolean getFile(String key) {
        return getByUsage(key);
    }

    @Override
    protected void removeEntry() {
        removeSmalestValue();
    }
}
