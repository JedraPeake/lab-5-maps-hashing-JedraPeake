package ca.uwo.eng.se2205b.lab5;

import java.util.*;

/**
 * Created by PeakeAndSons on 2017-03-09.
 */
public class MyHashMap<K,V> extends AbstractMap<K,V> implements IHashMap<K,V> {
    double loadFactor;
    int N;
    int n;

    private MyEntry<K, V>[] entries;
    int defaultCapcity = 7;

    @SuppressWarnings("unchecked")
    MyHashMap(double lf) {
        this.loadFactor = lf;
        N = defaultCapcity;
        this.entries = (MyEntry<K, V>[]) new MyEntry[defaultCapcity];
    }

    @SuppressWarnings("unchecked")
    MyHashMap() {
        this.loadFactor = 0.3;
        N = defaultCapcity;
        this.entries = (MyEntry<K, V>[]) new MyEntry[defaultCapcity];
    }

    private class MyEntry<K, V> implements Entry<K, V> {
        K key;
        V value;
        int hash;
        boolean isRemoved = false;

//        @Override
//        public int hashCode() {
//            int prime = 13;
//            int mul = 11;
//            if (key != null) {
//                int hashCode = prime * mul + key.hashCode();
//                return hashCode;
//            }
//            return 0;
//        }

        MyEntry(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = key.hashCode();
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V v) {
            V oldVal = this.value;
            this.value = v;
            return oldVal;
        }

        public K setKey(K k) {
            this.key = k;
            return k;
        }

        public void setRemoved(boolean t) {
            this.isRemoved = t;
        }

        @Override
        public String toString() {
            return "MyEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

    }

    @Override
    public int size() {
        return n;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public V get(Object key) {
        if(key == null){
            return null;
        }
        for (int i = 0; i < N; i++) {
            if (this.entries[i] != null) {
                if (this.entries[i].getKey().equals(key)) {
                    return this.entries[i].getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if (key == "") {
            return null;
        }
        if (key == null) {
            throw new NullPointerException();
        }

        int index = Math.abs(key.hashCode() % entries.length);
        V oldValue = null;
        if (entries[index] != null) {
            oldValue = entries[index].getValue();
        }
        if (this.entries[index] == null) {
            if (loadFactorThreshold() > loadFactor) {
                resize();
            }
            this.entries[index] = new MyEntry<>(key, value);
            n++;
        } else {
            entries[index].setValue(value);
        }

        return oldValue;
    }

    private int index(K key) {
        return Math.abs(key.hashCode() % entries.length);
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        MyEntry<K, V>[] temp = this.entries;
        // always know nextprime wont be prime, prime*2 == even
        int nextPrime = N * 2;
        N = nextPrimeNum(nextPrime);
        this.entries = (MyEntry<K, V>[]) new MyEntry[N];
        for (int i = 0; i < temp.length; i++) {
            MyEntry<K, V> entry = temp[i];
            if (entry != null) {
                this.put(entry.getKey(), entry.getValue());
            }
        }
    }

    private static int nextPrimeNum(int n) {
        boolean isPrime = false;
        int start = 2;
        while (!isPrime) {
            // always know n wont be prime, prime*2 == even
            n += 1;
            // redefine max boundary here
            int m = (int) Math.ceil(Math.sqrt(n));
            isPrime = true;
            for (int i = start; i <= m; i++) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }
        return n;
    }

    @Override
    public V remove(Object key) {
        int index = key.hashCode() % this.entries.length;
        if (entries[index] == null) {
            return null;
        }
//        Iterator<MyEntry<K, V>> iterator = new EntrySet();
//        while (iterator.hasNext()) {
//            MyEntry<K, V> entry = iterator.next();
//            if (entry.key.equals(key)) {
//                key.
//                n--;
//                return entry.value;
//            }
//        }
//        return null;
        MyEntry<K, V> entry = this.entries[index];
        if (entry != null && entry.getKey().equals(key)){
            V oldVal = entry.getValue();
            this.entries[index] = null;
            n--;
            return oldVal;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        if (size() != 0) {
            this.entries = (MyEntry<K, V>[]) new MyEntry[7];
            n = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {return true;}

        if (!(o instanceof HashMap)) {return false;}

        HashMap<?, ?> m = (HashMap<?, ?>) o;
        if (m.size() != size()){return false;}

        Iterator<Entry<K, V>> i = entrySet().iterator();
        while (i.hasNext()) {
            Entry<K, V> e = i.next();
            K key = e.getKey();
            V value = e.getValue();
            if (value == null) {
                if (!(m.get(key) == null && m.containsKey(key))) {
                    return false;
                }
            } else {
                if (!value.equals(m.get(key))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        int out = 0;
        for(int i =0;i<size(); i++ ){
            if(entries[i] != null){
                out += 31* this.entries[i].key.hashCode();
            }
        }
        return out;
    }

    Set<Map.Entry<K,V>> set;

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
//        Set<Map.Entry<K, V>> set = new HashSet();
        set = new HashSet();
        Iterator<MyEntry<K, V>> it = new EntrySet().iterator();
        int cnt = 0;
        while (it.hasNext()) {
            cnt++;
            MyEntry<K, V> curr = it.next();
            set.add(curr);
        }
        return set;
    }

    @Override
    public double loadFactorThreshold() {
        double nextLoadFactor = (n + 1) / N;
        if (nextLoadFactor > this.loadFactor) {
            return nextLoadFactor;
        }
        return this.loadFactor;
    }

    @Override
    public double loadFactor() {
        return n / N;
    }

    @Override
    public int capacity() {
        return N;
    }

//    private class EntrySet implements Iterator<MyEntry<K, V>> {
//        private int nextIndex;
//        private int index =0;
//        private List<Entry<K, V>> list;
//        private int count =0;
//
//        public EntrySet() {
//            list = new LinkedList<>();
//            setNextIndex();
//        }
//
//        @Override
//        public boolean hasNext() {
//            return index < size();
//        }
//
//        private void setNextIndex() {
//            nextIndex = -1;
//            boolean done = false;
//            if(count == 0){
//                if (entries[0] != null) {
//                    nextIndex = 0;
//                    done = true;
//                    count++;
//                }
//            }
//                for (int i = index + 1; i < entries.length && !done; i++) {
//                    if (entries[i] != null) {
//                        nextIndex = i;
//                        done = true;
//                    }
//                }
//        }
//
//        @Override
//        public MyEntry<K, V> next() {
//            if (!hasNext()) {
//                throw new NoSuchElementException();
//            }
//            index = nextIndex;
//            setNextIndex();
//            return entries[index];
//        }
//
//        @Override
//        public void remove() {
//            for(int i =0; i<size()+1;i++){
//                if(entries[i] == null){
//                    if(entrySet().contains(entries[i])) {
//                        MyEntry<K, V> curr = entries[i];
//                        entrySet().remove(entries[i]);
////                        entrySet().remove(curr.getKey(), curr.getValue());
//                    }
//                }
//            }
////            entries[index].setValue(Defu)
//        }
//    }
    private class EntrySet extends AbstractSet<MyEntry<K, V>> {

        @Override
        public Iterator<MyEntry<K, V>> iterator() {
            return new Iter();
        }

        @Override
        public int size() {
            return entries.length;
        }

        @Override
        public void clear() {
            clear();
        }

        public EntrySet() {

        }

        public class Iter implements Iterator<MyEntry<K, V>> {

            private int nextIndex = 0;
            private int index = 0;
            private List<MyEntry<K, V>> list;
            private int count = 0;
            boolean finished = false;

            public Iter() {
                list = new LinkedList<>();
                setNextIndex();
            }

            @Override
            public boolean hasNext() {
                if(finished == true){
                    return false;
                }
                return true;
            }

            private void setNextIndex() {
                nextIndex = -1;
                boolean done = false;
                if (count == 0) {
                    if (entries[0] != null) {
                        nextIndex = 0;
                        done = true;
                        count++;
                    }
                }
                for (int i = index + 1; i < entries.length && !done; i++) {
                    if (entries[i] != null) {
                        nextIndex = i;
                        done = true;
                    }
                    if(entries.length -1 == i){
                        if(entries[i] == null){
                            finished = true;
                            done = true;
                        }
                    }
                }
            }

            @Override
            public MyEntry<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                index = nextIndex;
                setNextIndex();
                return entries[index];
            }

            @Override
        public void remove() {
            for(int i =0; i<size()+1;i++){
                if(entries[i] != null){
                    if(!set.contains(entries[i])) {
//                        MyEntry<K, V> curr = entries[i];
//                        entrySet().remove(entries[i]);
                        set.remove(entries[i]);
                    }
                }
            }
        }
    }
    }
}


