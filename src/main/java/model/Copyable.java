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
