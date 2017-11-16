/** An object containing a key, value, and boolean flag.
 * @author despa
 * @author Daniel J. Norment
 * @version 1.1
 */
public class Node<K extends Comparable<? super K>,V> implements Comparable<Node<K, V>>
{
    private K key;
    private V value;
    private boolean flag;
    
    public Node() 
    {
        key = null;
        value = null;
        flag = false;
    }

    public Node(K key, V value)
    {
        this.key = key;
        this.value = value;
        this.flag = false;
    }
    
    /** Set's this node's key.
     *  @param key  The key to which to change this node's key. */
    public void setKey(K key)
    {
        this.key = key;
    }
    
    /** Returns this node's key.
     *  @return This node's key. */
    public K getKey()
    {
        return key;
    }
    
    /** Set's this node's value.
     *  @param value  The value to which to change this node's value. */
    public void setValue(V value)
    {
        this.value = value;
    }
    
    /** Returns this node's value.
     *  @return This node's value. */
    public V getValue()
    {
        return value;
    }
    
    /** Sets the node's flag to indicate data has been removed. */
    public void setUsed()
    {
        flag = true;
    }
        
    /** Sets the node's flag to indicate it has not been used. */
    public void setUnused()
    {
        flag = false;
    }
    
    /** Returns this node's flag.
     *  @return This node's flag. */
    public boolean getFlag()
    {
        return flag;
    }

    /** Compares keys between two nodes.
     *  @param otherNode  The node to which to compare this node.
     *  @return -1, 0, or 1 if the key in this node is less than, equal to, or greater than the key of otherNode. */
    public int compareTo(Node<K,V> otherNode)
    {
        return key.compareTo(otherNode.getKey());
    }
}