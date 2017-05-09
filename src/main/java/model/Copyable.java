/*
* Requirements mandating inclusion:
*
* This interface is used in other classes and indirectly relates to the following requirements:
* 3.2.1.8.3.1 User can set the dimensions of the grid.
* 3.2.1.8.3.2 User can scale the size of the grid blocks.
* 3.2.1.8.3.3 User can place Pile Objects on the grid.
* 3.2.1.8.3.4 User can show the grid.
* 3.2.1.8.3.5 User can hide the grid.
* */
package model;

import java.io.*;

/**
 * Note: if a class implements Copyable, is must also implement Serializable, and all its fields must
 * be Serializable as well. Copyable relies on serialization to copy the object.
 */
public interface Copyable {

    default Object copy() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
            bos.flush();
            bos.close();

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            Object objCopy = new ObjectInputStream(bis).readObject();
            return objCopy;
        } catch(IOException ioe) {
            System.out.println("IOException while trying to copy object: " + this);
            ioe.printStackTrace();
        } catch(ClassNotFoundException cnfe) {
            System.out.println("ClassNotFoundException while trying to copy object: " + this);
            cnfe.printStackTrace();
        }

        return null;
    }

}
