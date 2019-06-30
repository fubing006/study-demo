package com.example.demo;

import java.util.ArrayList;
import java.util.List;

public class MyHashMap<K,V> implements MyMap<K,V>{

    public static int defaultLength = 16;
    public static double defaultLoader = 0.75;

    private Entry<K,V>[] table = null;

    private int size = 0;

    public MyHashMap(int length, double loader){
        defaultLength = length;
        defaultLoader = loader;
    }

    public MyHashMap() {
        this(defaultLength, defaultLoader);
    }


    /**
     *  HashMap的put
     * @param k
     * @param v
     * @return
     */
    @Override
    public V put(K k, V v) {
        int index = getIndex(k);

        Entry<K,V> entry = table[index];

        if (size >= defaultLength * defaultLoader) {
            // 扩容
            up2size();
        }

        if (entry == null){
            table[index] = newEntry(k, v, null);
            size++;
        } else {
            // 冲突
            // 同时是否有相同Key的值--TODO

            table[index] = newEntry(k, v, entry);
        }

        return table[index].getValue();
    }

    private Entry<K,V> newEntry(K k, V v, Entry<K,V> next){
        return new Entry<>(k, v, next);
    }

    /**
     * 获取索引位置
     * @param k
     * @return
     */
    private int getIndex (K k) {
        int m = defaultLength;

        int index = k.hashCode() % m;
        return index >= 0 ? index : -index;
   }

    /**
     * 扩容
     */
    private void up2size(){
        Entry<K,V>[] newTable = new Entry[defaultLength * 2];
        // 新建数组后，以前旧数据继续进行再散列
        againHash(newTable);
    }

    /**
     * 重新计算Hash
     * @param newTable
     */
    private void againHash (Entry<K,V>[] newTable) {
        List<Entry<K,V>> list = new ArrayList<>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                continue;
            }
            findEntryByNext(table[i], list);
        }
        if (list.size() > 0) {
            size = 0;
            defaultLength = defaultLength * 2;
            table = newTable;
            for (Entry<K,V> entry : list) {
                if (entry.next != null) {
                    entry.next = null;
                }
                put(entry.getKey(), entry.getValue());
            }
        }
    }

    private void findEntryByNext(Entry<K,V> entry, List<Entry<K,V>> list){
        if (entry != null && entry.next != null) {
            list.add(entry);
            findEntryByNext(entry, list);
        } else {
            list.add(entry);
        }
    }



    @Override
    public V get(K k) {
        int index = getIndex(k);

        return findEntryByEqualKey(k, table[index]);

    }

    private V findEntryByEqualKey (K k, Entry<K,V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        }
        if (entry.next != null) {
            return findEntryByEqualKey(k, entry.next);
        }

        return entry.getValue();
    }

    @Override
    public int size() {
        return 0;
    }

    class Entry<K,V> implements MyMap.Entry<K,V> {
        K k;
        V v;
        Entry<K,V> next;

        public Entry(K k, V v, Entry<K,V> entry) {
            this.k = k;
            this.v = v;
            this.next = entry;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }
    }
}
