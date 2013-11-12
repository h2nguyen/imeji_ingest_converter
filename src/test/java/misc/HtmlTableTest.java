package misc;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import module.zusearchive.misc.CreateHtmlTable;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import core.vo.imeji.Items;

public class HtmlTableTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws JAXBException, SAXException {
		CreateHtmlTable chtml = new CreateHtmlTable();
		
		Items items = chtml.getImejiItems("./src/test/resources/xml/Konrad.xml");
		String s = chtml.createTable(items);
	}

}
