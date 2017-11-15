/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionaryex;

/**
 * @author despa
 * @param <K>
 * @param <V>
 */
public class Node<K extends Comparable<? super K>,V> implements Comparable<Node<K, V>>
{
	K key;
	V value;
	int flag;
	
	public Node() {
		key = null;
		value = null;
		flag = 0;
	}

	public Node(K ke, V val)
	{
		this.key = ke;
		this.value = val;
		this.flag = 1;
	
	}
        
        public void setFlagR()
        {
            this.flag = 0;
        }
        
        public void setFlagA()
        {
            this.flag = 1;
        }

        @Override
        public int compareTo(Node<K,V> nod)
        {
            return key.compareTo(nod.key);
        }
}