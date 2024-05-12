package cmsc256;

/**
  An interface that describes the operations of a collection of objects.
  There is no ordering defined by this interface and duplicate elements
  are allowed.
 */
public interface SackInterface<T> {

    /** Returns the current number of entries in this sack.
        @return The integer number of entries currently in the sack.
    */
    public int getCurrentSize();


    /** Checks if this sack is empty.
        @return True if the sack is empty, or false if not.
    */
    public boolean isEmpty();

    /** Adds a new entry to this sack.
        @param newEntry The object to be added as a new entry.
        @return True if the addition is successful, or false if not. 
    */
    public boolean add(T newEntry);

    /** Removes one unspecified entry from this sack, if possible.
        @return Either the removed entry, if the removal
        was successful, or null.
    */
    public T remove();

    /** Removes one occurrence of a given entry from this sack,
        if possible.
        @param anEntry The entry to be removed.
        @return True if the removal was successful, or false if not
    */
    public boolean remove(T anEntry);

    /** Removes all entries from this sack.
    */
    public void clear();

    /** Counts the number of times a given entry appears in this sack.
        @param anEntry The entry to be counted.
        @return The number of times anEntry appears in the sack.
    */
    public int getFrequencyOf(T anEntry);

    /** Tests whether this sack contains a given entry.
        @param anEntry The entry to locate.
        @return True if the sack contains anEntry, or false if not.
    */
    public boolean contains(T anEntry);

    /** Retrieves all entries that are in this sack.
        @return A newly allocated array of all the entries in the sack.
        Note: If the sack is empty, the returned array is empty.
    */
    public T[] toArray();

}
