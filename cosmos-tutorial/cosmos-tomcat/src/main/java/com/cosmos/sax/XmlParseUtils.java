package com.cosmos.sax;

import com.cosmos.servlet.ServletMapping;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Author: Cosmos
 * @program: cosmos-tutorial
 * @Description: TODO（描述此类的用法）
 * @Date: Create in 2018-12-12 13:36
 * @Modified By：
 */
public class XmlParseUtils {


    public List<ServletMapping> parse() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        {
            try {
                String basePath = this.getClass().getResource("/").getPath();
                SAXParser parser = factory.newSAXParser();
                XmlParseHandler handler = new XmlParseHandler();
                parser.parse(basePath + "servlet.xml", handler);
                return handler.getServletMappings();

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        XmlParseUtils xmlParseUtils = new XmlParseUtils();
        xmlParseUtils.parse();
    }


}
