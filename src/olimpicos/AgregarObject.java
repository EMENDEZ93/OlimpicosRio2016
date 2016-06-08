
package olimpicos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 *
 * @author Edwin Mendez
 */
public class AgregarObject extends ObjectOutputStream{
    

    public AgregarObject(OutputStream out) throws IOException {
        super(out);
    }

    @Override
    protected void writeStreamHeader() throws IOException {
        // do not write a header, but reset:    
        reset();
    }
    
    
    
}
