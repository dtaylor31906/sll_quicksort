//  Linked List class with a private static inner Node class.

import java.util.*;

// public class LinkedList<YourType>
public class LinkedList<YourType> implements Iterable<YourType>
{
   private Node<YourType> head;

   // Constructs an empty list
   public LinkedList()   {  head = null;  }

   // Returns true if the list is empty
   public boolean isEmpty()   {  return head == null;  }

   // Inserts a new node at the beginning of this list.
   public void addFirst(YourType item)   {  
        head = new Node<YourType>(item, head);
    }

   // Returns the first element in the list.
   public YourType getFirst()   {
      if (head == null)
          throw new NoSuchElementException();
      return head.data;
   }

  // Removes the first element in the list.
   public YourType removeFirst()     {
      YourType tmp = getFirst();
      head = head.next;
      return tmp;
   }

   // Inserts a new node to the end of this list.
   public void addLast(YourType item)    {
      if (head == null)
         addFirst(item);
      else   {
         Node<YourType> tmp = head;
         while (tmp.next != null)
             tmp = tmp.next;
         tmp.next = new Node<YourType>(item, null);
      }
   }

   // Returns the last element in the list.
   public YourType getLast()   {
      if (head == null)
          throw new NoSuchElementException();

      Node<YourType> tmp = head;
      while (tmp.next != null)
          tmp = tmp.next;

      return tmp.data;
   }

   // Removes all nodes from the list.
   public void clear()   {   head = null;  }
   
   // Returns true if this list contains the specified element.
   public boolean contains(YourType x)  {
      for (YourType tmp : this)
         if (tmp.equals(x))
             return true;
      return false;
   }

   // Returns the data at the specified position in the list.
   public YourType get(int pos)  {
      if (head == null)
          throw new IndexOutOfBoundsException();

      Node<YourType> tmp = head;
      for (int k = 0; k < pos; k++)
          tmp = tmp.next;

      if (tmp == null)
          throw new IndexOutOfBoundsException();
      return tmp.data;
   }

   // Returns a string representation
   public String toString()  {
      StringBuffer result = new StringBuffer();
      for (Object x : this)
             result.append(x + " ");
      return result.toString();
   }

   //  Inserts a new node after a node containing the key.
   public void insertAfter(YourType key, YourType toInsert)
   {
      Node<YourType> tmp = head;

      while (tmp != null && !tmp.data.equals(key))
          tmp = tmp.next;

      if (tmp != null)
         tmp.next = new Node<YourType>(toInsert, tmp.next);
   }

   //Inserts a new node before a node containing the key.
   public void insertBefore(YourType key, YourType toInsert)  {
      if (head == null)
             return;
      if (head.data.equals(key))  {
         addFirst(toInsert);
         return;
      }

      Node<YourType> prev = null;
      Node<YourType> cur = head;

      while (cur != null && !cur.data.equals(key))   {
         prev = cur;
         cur = cur.next;
      }

      //insert between cur and prev
      if (cur != null)
         prev.next = new Node<YourType>(toInsert, cur);
   }

    // Removes the first occurrence of the specified element in this list.
   public void remove(YourType key) {
      if (head == null)
         throw new RuntimeException("cannot delete");

      if (head.data.equals(key) )  {
         head = head.next;
         return;
      }

      Node<YourType> cur  = head;
      Node<YourType> prev = null;

      while (cur != null && !cur.data.equals(key)) {
         prev = cur;
         cur = cur.next;
      }

      if (cur == null)
         throw new RuntimeException("cannot delete");

      //delete cur node
      prev.next = cur.next;
   }
   
   // Returns a copy of the list with Complexity ???
   public  LinkedList<YourType> copy1()  {
      LinkedList<YourType> twin = new LinkedList<YourType>();
      Node<YourType> tmp = head;
      while (tmp != null)   {
         twin.addLast( tmp.data );
         tmp = tmp.next;
      }
      return twin;
   }

   // Returns a copy  of the list with Complexity ???
   public LinkedList<YourType> copy2()  {
      LinkedList<YourType> twin = new LinkedList<YourType>();
      Node<YourType> tmp = head;
      while (tmp != null)  {
         twin.addFirst( tmp.data );
         tmp = tmp.next;
      }
      return twin.reverse();
   }

   // Reverses the list with Complexity ???
   public LinkedList<YourType> reverse()  {
      LinkedList<YourType> list = new LinkedList<YourType>();
      Node<YourType> tmp = head;
      while (tmp != null)   {
         list.addFirst( tmp.data );
         tmp = tmp.next;
      }
      return list;
   }

   //
   // This is the Node class
   //
   private static class Node<YourType>  {
      private YourType data;
      private Node<YourType> next;

      public Node(YourType data, Node<YourType> next)  {
         this.data = data;
         this.next = next;
      }
   } // end of class Node

    //
    // This is the Iterator class
    //
    public Iterator<YourType> iterator()  {
      return new LinkedListIterator();
   }  // end of class Iterator

   private class LinkedListIterator  implements Iterator<YourType>
   {
      private Node<YourType> nextNode;

      public LinkedListIterator() { nextNode = head; }

      public boolean hasNext()  { return nextNode != null; }

      public YourType next()  {
         if  (!hasNext())
             throw new NoSuchElementException();
         YourType res = nextNode.data;
         nextNode = nextNode.next;
         return res;
      }

      public void remove() { throw new UnsupportedOperationException(); }
   }  // end of class LinkedListIterator

   /*
    * Return the number of elements in this LinkedList. 
    */
   public int size() {
     int count = 0;
     if (head != null) { 
        count = 1; 
        Node<YourType> element = head; 
        while (element.next != null) {
            element = element.next; 
            count++; 
        }
     }
     return count; 
   }
}
