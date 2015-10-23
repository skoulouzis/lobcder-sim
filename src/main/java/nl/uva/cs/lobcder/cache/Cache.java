/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.uva.cs.lobcder.cache;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author S. Koulouzis
 */
public abstract class Cache {//implements CacheI{

    protected Map<String, Long> cacheMap;
    protected int cacheSize;

    public Cache(int cacheSize, boolean order) {
        this.cacheMap = new HashMap<String, Long>(cacheSize);
        this.cacheSize = cacheSize;
    }

    public abstract void putFile(String key);

    public abstract boolean getFile(String key);

    protected abstract void removeEntry();

    protected void removeSmalestValue() {
        Set<Map.Entry<String, Long>> set = cacheMap.entrySet();
        String key = null;
        Long min = Long.MAX_VALUE;
        for (Map.Entry<String, Long> e : set) {
            if (e.getValue() < min) {
                min = e.getValue();
                key = e.getKey();
            }
        }
        cacheMap.remove(key);
    }

    protected void removeLargestValue() {
        Set<Map.Entry<String, Long>> set = cacheMap.entrySet();
        String key = null;
        Long max = Long.MIN_VALUE;
        for (Map.Entry<String, Long> e : set) {
            if (e.getValue() > max) {
                max = e.getValue();
                key = e.getKey();
            }
        }
        cacheMap.remove(key);


//         List b = Arrays.asList(ArrayUtils.toObject(a));        
    }

    protected void removeLast() {
        String[] keys = cacheMap.keySet().toArray(new String[cacheMap.keySet().size()]);
        cacheMap.remove(keys[keys.length - 1]);
    }

    @Override
    public String toString() {
        return cacheMap.keySet().toString();
    }

    public boolean getByTime(String key) {
        if (cacheMap.isEmpty()) {
            return false;
        }
        if (this.cacheMap.containsKey(key)) {
            cacheMap.remove(key);
            cacheMap.put(key, Long.valueOf(System.currentTimeMillis()));
            return true;
        }
        return false;
    }

    public boolean getByUsage(String key) {
        if (this.cacheMap.isEmpty()) {
            return false;
        }
        if (this.cacheMap.containsKey(key)) {
            Long usage = cacheMap.get(key);
            cacheMap.remove(key);
            cacheMap.put(key, Long.valueOf(++usage));
            return true;
        }
        return false;
    }

    protected void putWithTime(String key) {
        if (!this.cacheMap.containsKey(key)) {
            if (this.cacheMap.size() >= cacheSize) {
                removeEntry();
            }
            if (cacheSize > 0) {
                cacheMap.put(key, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    protected void putWithUsage(String key) {
        if (!this.cacheMap.containsKey(key)) {
            if (this.cacheMap.size() >= cacheSize) {
                removeEntry();
            }
            if (cacheSize > 0) {
                cacheMap.put(key, Long.valueOf(1));

            }
        }
    }
}
