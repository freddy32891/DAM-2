/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.proven.agenda.model.persist;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author fredd
 */
public class MyObjectOutputStream extends ObjectOutputStream
{
    public MyObjectOutputStream(OutputStream out) throws IOException
    {
        super(out);
    }

    protected MyObjectOutputStream() throws IOException, SecurityException
    {
        super();
    }

    @Override
    protected void writeStreamHeader() throws IOException
    {
    }

}
