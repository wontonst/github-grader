/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wontonst.ghg;

import com.wontonst.ghgformat.parser.FilePatterns;
import java.util.regex.Pattern;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RoyZheng
 */
public class GHGFilePatternsTest {

    public GHGFilePatternsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testHeaderMarker() {
        Pattern p = FilePatterns.header_marker;
        Assert.assertTrue(("---").matches(p.pattern()));
        Assert.assertFalse(("--").matches(p.pattern()));
    }

    @Test
    public void testHeaderTitle() {
    }
}