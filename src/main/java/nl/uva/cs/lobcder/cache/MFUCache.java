/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.cs.lobcder.cache;

/**
 *
 * @author S. Koulouzis
 */
public class MFUCache extends Cache {


    public MFUCache(int cacheSize) {
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
        removeLargestValue();
    }


}
