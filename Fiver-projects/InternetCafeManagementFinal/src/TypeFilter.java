import java.io.File;
import javax.swing.filechooser.FileFilter;
 
public class TypeFilter extends FileFilter {
 
    //file extension 
    private String ext;
    //description
    private String descp;
     //Constructor
    public TypeFilter(String ext, String descp) {
        this.ext = ext;
        this.descp = descp;
    }
     
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        return f.getName().toLowerCase().endsWith(ext);
    }
     //get description method that returns description
    public String getDescription() {
        return descp + String.format(" (*%s)", ext);
    }
}