# lab-5-maps-hashing

##Implement a HashMap

####What would happen if you change the state of a Key stored in a Hash Map? Consider how #hashCode() works.
We must be careful when using objects as keys in hash-based collections to ensure that we don’t allow their state to change when 
they are being used as hash keys. All hash-based collections assume that an object’s hash value does not change while it is in 
use as a key in the collection. If a key’s hash code were to change while it was in a collection, some unpredictable consequences
could follow. This is usually not a problem in practice — it is not common practice to use a mutable 
object like a List as a key in a HashMap.
####Justify why we use a load factor of 0.3 for open hashing based maps? Why is a higher load factor better for a chaining based map?
Higher  load factor values decrease the space overhead but increase the lookup cost (reflected in most of the operations of the 
HashMap class, including get and put). Higher load Factor is probably bettter for chaining as when we rehash we have to change our lists.
####It was not used in this Lab, but what is the benefit of using double hashing over traditional Linear Probing?
double hashing minimizes the clustering that is often associated with linear probing.

See lab document: https://uwoece-se2205b-2017.github.io/labs/05-maps-hash
