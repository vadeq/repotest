package com.zws.util;

import java.math.BigInteger;
import java.net.InetAddress;
/*
* This class is a unique ID generator. To improve databases performance the
* length was reduced to a minimum necessary.
* </p>
* The 16 characters long printable unique ID is a base64 representation of a
* 12 bytes long ID obtained by concatenating:
* </p>
* 1) The 1 less significant byte of the machine IP address. This limits
* the size of the cluster (or subnet) to 256 machines.
* </p>
* 2) The 4 bytes memory address of the generator class instance. This limits
* the machine memory space to 4GB.
* </p>
* 3) The less significant 5 bytes of the current time in milliseconds. This
* limits the lifetime of the ID to 35 years.
* </p>
* 4) A 2 byte synchronized counter to refine possible conflicts within the
* same millisecond. This will refine the time precision at 15 nanoseconds.
* </p>
* The uniqueness of the ID has two dimensions: space and time.
* </p>
* Items 1) and 2) will assures the uniqueness in the network/machine space.
* Since it does not change during the lifetime of the generator it will be
* computed only once at the construction time.
* </p>
* Items 3) and 4) assures the uniqueness in time.
* </p>
* The class can easily be converted to a singleton or stateless session EJB
* @version 1
*/
public class UniqueID {

   // Binary ID
   private byte[] bytes12;
   // Counter
   private short loopCounter = 0;


   /**
    * Creates new UniqueID
    */
   public UniqueID() {
       try {
           // Prepare a buffer to hold the 12 bytes for the unique binary ID
           bytes12 = new byte[12];

           // Get the local IP address as a byte array and copy the less significant byte.
           byte[] bytesIP = InetAddress.getLocalHost().getAddress();
           bytes12[0] = bytesIP[3];

           // Get the memory address for this object and copy the 4 bytes.
           int memAdr = System.identityHashCode(this);
           byte[] bytes4 = new byte[4];
           toFixSizeByteArray (new BigInteger(String.valueOf(memAdr)), bytes4);
           bytes12[1] = bytes4[0];
           bytes12[2] = bytes4[1];
           bytes12[3] = bytes4[2];
           bytes12[4] = bytes4[3];

           // At this point the first 5 bytes are set for the liftime of this object
       }
       catch(Exception ignore) {}
   }

   /**
    * Returns a 16 printable characters unique ID.
    * The ID is base64 encoded
    */
   public String generateBase64ID() {
       // Get the current time and ignore the most 3 significant bytes. Copy the remaining 5 bytes.
       long timeNow = System.currentTimeMillis() & 0xFFFFFFFFFFL;
       byte[] bytes5 = new byte[5];
       toFixSizeByteArray(new BigInteger(String.valueOf(timeNow)), bytes5);
       bytes12[5] = bytes5[0];
       bytes12[6] = bytes5[1];
       bytes12[7] = bytes5[2];
       bytes12[8] = bytes5[3];
       bytes12[9] = bytes5[4];

       // Get the current counter reading and copy the 2 bytes
       short counter = getLoopCounter();
       byte[] bytes2 = new byte[2];
       toFixSizeByteArray(new BigInteger(String.valueOf(counter)), bytes2);
       bytes12[10] = bytes2[0];
       bytes12[11] = bytes2[1];

       // Encode the information in base64 and return the unique ID
       return Base64.encodeBytes(bytes12);
   }

   /**
    * Returns a 16 printable characters unique ID.
    * Takes the base 64 encoding and replace '+' with '$'
    * and '/' with '_' so the resulted string can be used to
    * generate database table names and LDAP names.
    */
   public String generateID() {
       // Return the unique ID
       return generateBase64ID().replace('+', '$').replace('/', '_');
   }

   /**
    * Get the counter value as a signed short
    */
   private synchronized short getLoopCounter() {
       return loopCounter++;
   }


   /**
    * This method transforms Java BigInteger type into a fix size byte array
    * containing the two's-complement representation of the integer.
    * The byte array will be in big-endian byte-order: the most significant
    * byte is in the zeroth element.
    * If the destination array is shorter then the BigInteger.toByteArray(),
    * the the less significant bytes will be copy only.
    * If the destination array is longer then the BigInteger.toByteArray(),
    * destination will be left padded with zeros.
    */
   private static void toFixSizeByteArray (BigInteger bigInt, byte[] destination){
       // Prepare the destination
       for (int i = 0; i < destination.length; i++) {
           destination[i] = 0x00;
       }

       // Convert the BigInt to a byte array
       byte[] source = bigInt.toByteArray();

       // Copy only the fix size length
       if (source.length <= destination.length) {
           for (int i = 0; i < source.length; i++) {
               destination[destination.length - source.length + i] = source[i];
           }
       }
       else {
           for (int i = 0; i < destination.length; i++) {
               destination[i] = source[source.length - destination.length + i];
           }
       }
   }

}
