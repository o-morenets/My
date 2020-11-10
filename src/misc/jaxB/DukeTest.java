package misc.jaxB;

import org.junit.Before;
import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.File;

import static org.junit.Assert.*;

public class DukeTest {
    private JAXBContext context;

    @Before
    public void setUp() throws Exception {
        context = JAXBContext.newInstance(Duke.class);
    }

    @Test
    public void serialization() throws JAXBException {
        Marshaller marshaller = this.context.createMarshaller();
        marshaller.marshal(new Duke("Java", 2), new File("duke.xml"));

        Unmarshaller unmarshaller = this.context.createUnmarshaller();
        Object unmarshalled = unmarshaller.unmarshal(new File("duke.xml"));
        System.out.println(unmarshalled);
    }
}