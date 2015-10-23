/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.cs.lobcder.cache;

/**
 *
 * @author S. Koulouzis
 */
public class MRUCache extends Cache {


    public MRUCache(int cacheSize) {
        super(cacheSize, true);
    }

    @Override
    public void putFile(String key) {
        putWithTime(key);
    }

    @Override
    public boolean getFile(String key) {
        return getByTime(key);
    }

    @Override
    protected void removeEntry() {
        removeLargestValue();
    }
}
