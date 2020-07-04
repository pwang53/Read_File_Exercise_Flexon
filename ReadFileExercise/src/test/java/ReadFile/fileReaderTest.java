package ReadFile;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class fileReaderTest
{
    private myFileReader f;
    private String url;

    @Before
    public void getURL() {
        url = "src/main/resources/myText.txt";
    }

    // Positive test
    @Test
    public void testOpenFile() {
        f = new myFileReader(url);
        f.openFile();
        String compareMessage = "Successfully Read the File\n";
        Assert.assertEquals(f.getMessage(), compareMessage);
    }
}
