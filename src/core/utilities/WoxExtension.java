package core.utilities;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import wox.serial.ObjectReader;
import wox.serial.ObjectWriter;
import wox.serial.SimpleReader;
import wox.serial.SimpleWriter;

import java.io.*;


public class WoxExtension {


    public static String saveString(Object ob, int sizeEstimate) {
        try {
            ObjectWriter writer = new SimpleWriter();
            Element el = writer.write(ob);
            XMLOutputter out = new XMLOutputter();
            out.setFormat(Format.getPrettyFormat());
            ByteArrayOutputStream bout = new ByteArrayOutputStream(sizeEstimate);
            out.output(el, bout);
            return bout.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static Object loadString(String data) {
        try {
            SAXBuilder builder = new SAXBuilder();
            Reader is = new StringReader(data);
            Document doc = builder.build(is);
            Element el = doc.getRootElement();
            ObjectReader reader = new SimpleReader();
            return reader.read(el);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void saveToFile(String data,String name,boolean append)
    {
        try
        {
            FileOutputStream outS=new FileOutputStream(name,append);
            PrintWriter pw=new PrintWriter(outS);

            pw.println(data);
            pw.flush();
            outS.close();

        }
        catch (IOException e)
        {
            System.out.println("Could not save data!");
        }
    }

    public static long loadData(String fileName) throws IOException
    {
        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String input=br.readLine();
        return Long.parseLong(input.trim());
    }
}