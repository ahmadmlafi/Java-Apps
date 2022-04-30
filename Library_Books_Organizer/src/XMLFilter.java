import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class XMLFilter {
    private static final String FILTER_FILED = "Science Fiction";

    public static void main(String[] args) throws Exception {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.parse(new File("src/books.xml"));
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("book");

        Document newDoc = builder.newDocument();
        Element root = newDoc.createElement("catalog");
        newDoc.appendChild(root);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            Book instance = getElementInfo((Element) nNode);
            if (instance.getGenre().equalsIgnoreCase(FILTER_FILED)) root.appendChild(createBookXML(newDoc, instance));
        }

        saveDomToFile(newDoc, "src/new.xml");
    }

    private static Book getElementInfo(Element element) {
        String id = element.getAttribute("id");
        String author = element.getElementsByTagName("author").item(0).getTextContent();
        String title = element.getElementsByTagName("title").item(0).getTextContent();
        String genre = element.getElementsByTagName("genre").item(0).getTextContent();
        String price = element.getElementsByTagName("price").item(0).getTextContent();
        String publish_date = element.getElementsByTagName("publish_date").item(0).getTextContent();
        String description = element.getElementsByTagName("description").item(0).getTextContent();
        Book instance = new Book(id, author, title, genre, price, publish_date, description);
        return instance;
    }

    private static Node createBookXML(Document doc, Book book) {

        Element XMLBook = doc.createElement("book");
        XMLBook.setAttribute("id", book.getId());
        XMLBook.appendChild(createElement(doc, "author", book.getAuthor()));
        XMLBook.appendChild(createElement(doc, "title", book.getTitle()));
        XMLBook.appendChild(createElement(doc, "genre", book.getGenre()));
        XMLBook.appendChild(createElement(doc, "price", book.getPrice()));
        XMLBook.appendChild(createElement(doc, "publish_date", book.getPublish_date()));
        XMLBook.appendChild(createElement(doc, "description", book.getDescription()));
        return XMLBook;
    }

    private static Node createElement(Document doc, String name, String value) {
        Element node = doc.createElement(name);
        node.appendChild(doc.createTextNode(value));
        return node;
    }

    private static void saveDomToFile(Document document, String fileName) throws Exception {

        DOMSource dom = new DOMSource(document);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(dom, result);
    }

}