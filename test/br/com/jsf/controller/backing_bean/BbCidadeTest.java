package br.com.jsf.controller.backing_bean;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

public class BbCidadeTest {

    @Test
    public void testGetCidades() {
        System.out.println("getCidades");
        BbCidade instance = new BbCidade();
        List expResult = null;
        List result = instance.getCidades();
        assertEquals(expResult, result);
    }
}
