import java.util.Iterator;
/**
 * A dictionary using an array of nodes with key-value pair and makes use of hashing for indexing.
 *
 * @author Daniel J. Norment
 * @author Daniel Esparza
 * @version 1.0
 */
public class DictionaryHashTable<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V>
{
     private Node<K, V>[] dict;
     private int numOfItems;
    
     public DictionaryHashTable()
     {
         this(5); //default to 5
     }
     
     public DictionaryHashTable(int size)
     {
         @SuppressWarnings("unchecked")
         Node<K, V>[] tempDict = (Node<K, V>[])new Node<?,?>[size];
         dict = tempDict;
         for (int i=0; i<dict.length; i++)
         {
             dict[i] = new Node<K, V>(null, null); //fill dict with unused Nodes
         }
         numOfItems = 0;
     }
     
     /** Returns the index at which to place the given key.
      *  @param key  The key at which to retrieve the index. */
     public int index(K key)
     {
         return key.hashCode() % dict.length;
     }
     
     public boolean isPrime(int num)
     {
         for (int i=2; i<Math.sqrt(num); i++)
         {
             if (num % i == 0) //if num divisible by any [2 through ?num]
             {
                 return false;
             }
         }
         return true;
     }
     
     /** Returns the next prime number at least double the size of the current dict size.
      *  @return The next prime at least double current dict size. */
     public int nextPrime()
     {
         int newSize = dict.length * 2 + 1;
         while (!isPrime(newSize))
         {
             newSize++;
         }
         return newSize;
     }
     
     /** Adds a new entry to this dictionary. If the given search key already
       exists in the dictionary, replaces the corresponding value.
       @param key    An object search key of the new entry.
       @param value  An object associated with the search key.
       @return  Either null if the new entry was added to the dictionary
                or the value that was associated with key if that value
                was replaced. */
     public V add(K key, V value)
     {
         if (contains(key)) //if key in dict, replace it
         {
             V tempVal = getValue(key);
             int i = index(key);
             while (!dict[i].getKey().equals(key)) //while Node not same key
             {
                 i = i+1 % dict.length;
             }
             dict[i].setValue(value);
             return tempVal;
         }
         else if (numOfItems == dict.length) //if dict full, resize, then add
         {
             Node<K, V>[] tempDict = dict;
             @SuppressWarnings("unchecked")
             Node<K, V>[] newDict = (Node<K, V>[])new Node<?,?>[nextPrime()];
             for (int i=0; i<newDict.length; i++)
             {
                 newDict[i] = new Node<K, V>(null, null);
             }
             dict = newDict;
             for (int i=0; i<tempDict.length; i++)
             {
                 if (tempDict[i].getKey() != null)
                 {
                     add(tempDict[i].getKey(), tempDict[i].getValue()); //add all old nodes to new, bigger dict
                 }
             }
             add(key, value);
         }
         else //key not inside and dict not full, so add
         {
             int i = index(key);
             while (dict[i].getKey() != null) //while Node not open
             {
                 i = i+1 % dict.length;
             }
             dict[i].setKey(key);
             dict[i].setValue(value);
             numOfItems++;
         }
         return null;
     }
   
     /** Removes a specific entry from this dictionary.
       @param key  An object search key of the entry to be removed.
       @return  Either the value that was associated with the search key
                or null if no such object exists. */
     public V remove(K key)
     {
         if (contains(key))
         {
             V tempVal = getValue(key);
             int i = index(key);
             while (!dict[i].getKey().equals(key) && dict[i].getFlag())
             {
                 i = i+1 % dict.length;
             }
             dict[i].setKey(null);
             dict[i].setValue(null);
             dict[i].setRemoved();
             numOfItems--;
             return tempVal;
         }
         return null;
     }
   
     /** Retrieves from this dictionary the value associated with a given
       search key.
       @param key  An object search key of the entry to be retrieved.
       @return  Either the value that is associated with the search key
                or null if no such object exists. */
     public V getValue(K key)
     {
         if (contains(key))
         {
             int i = index(key);
             while (!dict[i].getKey().equals(key) && dict[i].getFlag()) //while Node not matching and Node has been used, increment index
             {
                 i = i+1 % dict.length;
             }
             return dict[i].getValue();
         }
         return null;
     }
   
     /** Sees whether a specific entry is in this dictionary.
       @param key  An object search key of the desired entry.
       @return  True if key is associated with an entry in the dictionary. */
     public boolean contains(K key)
     {
         if (isEmpty())
         {
             return false;
         }
         int i = index(key);
         while (!dict[i].getKey().equals(key) && dict[i].getFlag() && i+1 != index(key))
         {
             i = i+1 % dict.length;
         }
         //loop exits when Node matches key, Node was unused, or reached original index
         return dict[i].getKey().equals(key);
     }
   
     /** Creates an iterator that traverses all search keys in this dictionary.
       @return  An iterator that provides sequential access to the search
                keys in the dictionary. */
     public Iterator<K> getKeyIterator()
     {
         return null; //todo
     }
   
     /** Creates an iterator that traverses all values in this dictionary.
       @return  An iterator that provides sequential access to the values
                in this dictionary. */
     public Iterator<V> getValueIterator()
     {
         return null; //todo
     }
   
     /** Sees whether this dictionary is empty.
       @return  True if the dictionary is empty. */
     public boolean isEmpty()
     {
         return numOfItems == 0;
     }
   
     /** Gets the size of this dictionary.
       @return  The number of entries (key-value pairs) currently
                in the dictionary. */
     public int getSize()
     {
         return numOfItems;
     }
   
     /** Removes all entries from this dictionary. */
     public void clear()
     {
         for (int i=0; i<dict.length; i++)
         {
             dict[i].setKey(null);
             dict[i].setValue(null);
             dict[i].setUnused();
             numOfItems = 0;
         }
     }
}
