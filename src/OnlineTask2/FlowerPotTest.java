package OnlineTask2;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class FlowerPotTest {

    @Test
    public void testFlowerConstructor() {
        Flower f = new Flower("white", "rose", 50, 12);
        assertEquals(f.getAge(), 0, 0.001);
        assertEquals(f.getHeight(), 0, 0.001);
        assertEquals(f.getColour(), "white");
        assertEquals(f.getSpecies(), "rose");
        assertTrue(f.isAlive());

        Flower f2 = new Flower(null, null, 50, 12);
        assertEquals(f2.getAge(), 0, 0.001);
        assertEquals(f2.getHeight(), 0, 0.001);
        assertNull(f2.getColour());
        assertNull(f2.getSpecies());
        assertTrue(f2.isAlive());
    }

    @Test
    public void growTest() {
        Flower f = new Flower("yellow", "tulip", 10, 20.5);
        double lasth = 0;
        double lastage = 0;
        for (int i = 0; i < 10; i++) {
            assertEquals(f.getAge(), i, 0.001);
           // lasth = [redacted];
            assertEquals(f.getHeight(), lasth, 0.001);
            assertTrue(f.grow());
            assertTrue(f.isAlive());
            lasth = f.getHeight();
            lastage = f.getAge();
        }
        assertFalse(f.grow());
        assertFalse(f.grow());
        assertFalse(f.grow());
        assertFalse(f.isAlive());
        assertEquals(f.getAge(), 11, 0.001);
       // assertEquals(f.getHeight(), [redacted], 0.001);
    }



    @Test
    public void growTest2() {
        Flower f = new Flower("yellow", "tulip", 0, 20.5);
        assertFalse(f.grow());
        assertFalse(f.isAlive());
    }

    @Test
    public void aliveTest() {
        Flower f = new Flower("yellow", "tulip", -1, 20.5);
        assertFalse(f.isAlive());
    }

    @Test
    public void growInsideTest() {
        Pot p = new Pot("square", 8);
        Flower f = new Flower("purple", "azalia", 20, 5.765);
        Flower f2 = new Flower("yellow", "tulip", -1, 20.5);
        Flower f3 = new Flower("yellow", "tulip", 3, 20.5);
        Flower f4 = new Flower("yellow", "tulip", 2, 20.5);
        Flower f5 = new Flower("white", "rose", 10, 12);
        Flower f6 = new Flower("red", "rose", 1, 12);
        p.insert(f);
        p.insert(f2);
        p.insert(f3);
        p.insert(f4);
        p.insert(f5);
        p.insert(f6);

        assertTrue(f.growInside(p));
        assertEquals(f.getAge(), 2, 0.001);
        p.rearrange();
        assertTrue(f.growInside(p));
        assertEquals(f.getAge(), 4.5, 0.001);
        //assertEquals(f.getHeight(), [redacted], 0.001);

        assertFalse(f4.growInside(p));
        assertEquals(f4.getAge(), 2.5, 0.001);
        assertFalse(f4.isAlive());
        p.rearrange();
        assertFalse(f6.growInside(p));
        assertEquals(f6.getAge(), 3, 0.001);

        Pot p2 = new Pot("square", 23);
        assertTrue(f.growInside(p2));
        assertEquals(f.getAge(), 8, 0.001);

        assertFalse(f3.growInside(p2));
        assertEquals(f3.getAge(), 3.5, 0.001);
        assertFalse(f3.isAlive());

    }

    @Test
    public void dieTest() {
        Flower f = new Flower("yellow", "tulip", 10, 20.5);
        assertTrue(f.isAlive());
        f.grow();
        assertTrue(f.isAlive());
        f.die();
        assertFalse(f.isAlive());
        assertEquals(f.getAge(), -1, 0.0001);
    }

    @Test
    public void constructorPotTest() {
        Pot p = new Pot("circle", 23);
        assertEquals(p.size(), 23);
        assertEquals(p.getShape(), "circle");
        assertEquals(p.count(), 0);
        assertEquals(p.averageAge(), -1, 0.0001);
        assertEquals(p.averageHeight(), -1, 0.0001);
    }

    @Test
    public void insertTest() {
        Pot p = new Pot("circle", 23);
        assertTrue(p.insert(new Flower("yellow", "dandelion", 12, 23)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 1);
        assertTrue(p.insert(new Flower("white", "rose", 4, 53.4)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 2);

        assertFalse(p.insert(null));
        assertEquals(p.count(), 2);

        assertTrue(p.insert(new Flower("red", "rose", 5, 53.4)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 3);
        assertTrue(p.insert(new Flower("orange", "tulip", 6, 53.4)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 4);
        assertTrue(p.insert(new Flower("white", "orchid", 2, 53.4)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 5);
        for (int i = 0; i < 18; i++) {
            assertTrue(p.insert(new Flower("white", "orchid", 2, 53.4)));
            assertEquals(p.size(), 23);
            assertEquals(p.count(), 6+i);
        }
        assertFalse(p.insert(new Flower("green", "parsely", 2, 53.4)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 23);
        assertFalse(p.insert(new Flower("green", "parsely", 2, 53.4)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 23);
        assertFalse(p.insert(new Flower("green", "parsely", 2, 53.4)));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 23);
    }

    @Test
    public void containsTest() {
        Pot p = new Pot("circle", 23);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f2_2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 5, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f7_2 = new Flower("white", "rose", 4, 53.4);
        Flower f7_3 = new Flower("white", "rose", 4, 53.4);
        assertTrue(p.insert(f));
        assertEquals(p.size(), 23);
        assertEquals(p.count(), 1);
        assertTrue(p.contains(f));

        assertTrue(p.insert(f2));
        f2.grow();
        f2.grow();
        f2.grow();
        f2.grow();

        f2_2.grow();
        assertFalse(p.contains(f2_2));
        f2_2.grow();
        f2_2.grow();
        f2_2.grow();

        assertTrue(p.contains(f2_2));

        assertFalse(p.contains(f3));
        assertFalse(p.contains(f4));
        assertFalse(p.contains(f5));
        assertTrue(p.contains(f6));
        assertFalse(p.contains(f7));


        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);
        assertTrue(p.contains(f3));
        assertTrue(p.contains(f4));
        assertTrue(p.contains(f5));
        assertTrue(p.contains(f6));
        assertTrue(p.contains(f7));

        assertEquals(p.rearrange(),1);
        assertEquals(p.count(), 6);
        assertFalse(p.contains(f2));
        assertTrue(p.contains(f4));
        assertTrue(p.contains(f5));
        assertTrue(p.contains(f6));
        assertTrue(p.contains(f7));
        assertTrue(p.contains(f));
        assertTrue(p.contains(f3));

        f7.grow();
        f7.grow();
        f7_3.grow();
        f7_3.grow();
        assertTrue(p.contains(f7_3));
        assertTrue(p.contains(new Flower("yellow", "dandelion", 1, 23)));

        p.water();
        assertFalse(p.contains(f7_2));
        assertFalse(p.contains(new Flower("yellow", "dandelion", 1, 23)));
        assertTrue(p.contains(null));
        assertFalse((new Pot("rectangle", 0)).contains(null));
        Pot full = new Pot("square", 5);
        full.insert(f);
        full.insert(f5);
        full.insert(f2);
        full.insert(f3);
        full.insert(f4);
        assertFalse(full.contains(null));
        assertFalse(p.contains(new Flower(null, "dandelion", 1, 23)));
        assertFalse(p.contains(new Flower("yellow", null, 1, 23)));

    }

    @Test
    public void containsTypeTest() {
        Pot p = new Pot("circle", 23);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f7_2 = new Flower("white", "rose", 4, 53.4);
        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);

        assertTrue(p.containsType("parsely"));
        assertTrue(p.containsType("oRchid"));
        assertTrue(p.containsType("rose"));
        assertFalse(p.containsType("gdfijgidf"));

        p.water();
        assertEquals(p.rearrange(),2);
        assertFalse(p.containsType("parsely"));
        assertFalse(p.containsType("orchid"));
        p.water();
        p.rearrange();
        assertTrue(p.containsType("rose"));
        f5.die();
        p.rearrange();

        assertFalse(p.containsType("rose"));
        assertFalse(p.containsType(null));
        p.insert(new Flower("white", null, 34, 23));
        assertTrue(p.containsType(null));

    }

    @Test
    public void containsColourTest() {
        Pot p = new Pot("circle", 23);
        Flower f = new Flower("yellow", "daNDElion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("grEEn", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("whITe", "rose", 4, 53.4);
        Flower f7_2 = new Flower("white", "rose", 4, 53.4);
        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);

        assertTrue(p.containsColour("yellow"));
        assertTrue(p.containsColour("white"));
        assertTrue(p.containsColour("orange"));
        assertTrue(p.containsColour("red"));
        assertFalse(p.containsColour("gdfijgidf"));

        p.water();
        assertEquals(p.rearrange(),2);
        assertFalse(p.containsColour("green"));
        assertTrue(p.containsColour("white"));
        p.water();
        p.rearrange();
        assertFalse(p.containsColour("white"));
        f5.die();
        p.rearrange();

        assertFalse(p.containsColour("red"));
        assertFalse(p.containsColour(null));
        p.insert(new Flower(null, null, 34, 23));
        assertTrue(p.containsColour(null));
    }

    @Test
    public void countTypeTest() {
        Pot p = new Pot("circle", 23);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "pARsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f7_2 = new Flower("white", "rose", 4, 53.4);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);

        assertEquals(p.countType("dandelion"), 2);
        assertEquals(p.countType("orchid"), 1);
        assertEquals(p.countType("parsELY"), 1);
        assertEquals(p.countType("ROsE"), 2);

        p.water();
        assertEquals(p.rearrange(),2);
        assertEquals(p.countType("parsely"), 0);
        assertEquals(p.countType("dandelion"), 2);
        assertEquals(p.countType("rOse"), 2);
        p.water();
        p.rearrange();
        assertEquals(p.countType("rose"), 1);
        f5.die();
        p.rearrange();

        assertEquals(p.countType("rose"), 0);
        assertEquals(p.countType(null), 0);
        p.insert(new Flower("red", null, 46, 34));
        assertEquals(p.countType(null), 1);
        p.insert(new Flower(null, null, 46, 34));
        assertEquals(p.countType(null), 2);
    }

    @Test
    public void countColourTest() {
        Pot p = new Pot("circle", 23);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yELlow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f7_2 = new Flower("white", "rose", 4, 53.4);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);

        assertEquals(p.countColour("yellow"), 2);
        assertEquals(p.countColour("white"), 2);
        assertEquals(p.countColour("GrEEn"), 1);
        assertEquals(p.countColour("rose"), 0);

        p.water();
        assertEquals(p.rearrange(),2);
        assertEquals(p.countColour("green"), 0);
        assertEquals(p.countColour("white"), 1);
        assertEquals(p.countColour("red"), 1);
        p.water();
        p.rearrange();
        assertEquals(p.countColour("rED"), 1);
        f5.die();
        p.rearrange();

        assertEquals(p.countColour("red"), 0);
        assertEquals(p.countColour(null), 0);
        p.insert(new Flower(null, "rOSe", 46, 34));
        assertEquals(p.countColour(null), 1);
        p.insert(new Flower(null, "rose", 46, 34));
        assertEquals(p.countColour(null), 2);
    }

    @Test
    public void waterTest() {
        Pot p = new Pot("circle", 13);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f7_2 = new Flower("white", "rose", 4, 53.4);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);
        p.insert(new Flower(null, "rose", 46, 34));
        p.insert(new Flower(null, null, 46, 34));
        p.insert(null);

        p.water();
        assertEquals(f.getAge(), 3, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 3, 0.0001);
        assertEquals(f5.getAge(), 3, 0.0001);
        assertEquals(f6.getAge(), 3, 0.0001);
        assertEquals(f7.getAge(), 3, 0.0001);



        p.water();
        assertEquals(f.getAge(), 6, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 6, 0.0001);
        assertEquals(f5.getAge(), 6, 0.0001);
        assertEquals(f6.getAge(), 6, 0.0001);
        assertEquals(f7.getAge(), 6, 0.0001);
        p.water();
        assertEquals(f.getAge(), 9, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 9, 0.0001);
        assertEquals(f5.getAge(), 9, 0.0001);
        assertEquals(f6.getAge(), 9, 0.0001);
        assertEquals(f7.getAge(), 6, 0.0001);
        p.water();
        assertEquals(f.getAge(), 12, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 9, 0.0001);
        assertEquals(f5.getAge(), 12, 0.0001);
        assertEquals(f6.getAge(), 12, 0.0001);
        assertEquals(f7.getAge(), 6, 0.0001);
        p.water();
        assertEquals(f.getAge(), 15, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 9, 0.0001);
        assertEquals(f5.getAge(), 15, 0.0001);
        assertEquals(f6.getAge(), 15, 0.0001);
        assertEquals(f7.getAge(), 6, 0.0001);
        p.water();
        assertEquals(f.getAge(), 15, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 9, 0.0001);
        assertEquals(f5.getAge(), 18, 0.0001);
        assertEquals(f6.getAge(), 15, 0.0001);
        assertEquals(f7.getAge(), 6, 0.0001);
        p.water();
        assertEquals(p.rearrange(), 7);


    }

    @Test
    public void waterTest2() {
        Pot p = new Pot("circle", 10);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);
        p.water();
        f5.die();
        p.water();
        assertEquals(f5.getAge(), -1, 0.0001);
        assertEquals(f.getAge(), 5, 0.0001);
        p.rearrange();
        p.water();
        assertEquals(f.getAge(), 8.5, 0.0001);
        p.water();
        assertEquals(f4.getAge(), 8.5, 0.0001);
        assertFalse(f4.isAlive());
    }

    @Test
    public void viewTest() {
        Pot p = new Pot("circle", 23);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("grEEn", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 6, 53.4);
        Flower f7_2 = new Flower("white", "rose", 4, 23.4);
        Flower f8 = new Flower("violet", "rose", 6, 53.4);

        assertTrue(p.insert(f)); //yellow
        assertTrue(p.insert(f6)); //yellow
        assertTrue(p.insert(f3)); //green
        assertTrue(p.insert(f7)); //white
        assertTrue(p.insert(f2)); //white
        assertTrue(p.insert(f3)); //green
        assertEquals(p.count(), 6);

        assertEquals(p.view(), "2x green\n2x white\n2x yellow\n");
    }

    @Test
    public void rearrangeTest() {
        Pot p = new Pot("circle", 10);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);
        f2.die();
        f3.die();
        assertEquals(p.rearrange(),2);
        assertEquals(p.count(), 5);
        assertEquals(p.size(), 10);
    }

    @Test
    public void averageAgeTest() {
        Pot p = new Pot("circle", 11);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f8 = new Flower(null, "rose", 20, 34);
        Flower f9 = new Flower(null, null, 20, 34);
        Flower[] x = {f,f2,f3,f4,f5,f6,f7,f8,f9};
        assertEquals(p.averageAge(), -1, 0.0001);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);
        assertEquals(p.averageAge(), 0, 0.0001);

        p.water();
        assertEquals(f.getAge(), 3, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 3, 0.0001);
        assertEquals(f5.getAge(), 3, 0.0001);
        assertEquals(f6.getAge(), 3, 0.0001);
        assertEquals(f7.getAge(), 3, 0.0001);
        assertEquals(p.averageAge(), 3, 0.0001);


        p.insert(f8);
        p.insert(f9);
        //assertEquals(p.averageAge(), averageAge(x), 0.0001);
        p.insert(null);


        p.water();
        //assertEquals(p.averageAge(), averageAge(x), 0.0001);
        p.water();
       // assertEquals(p.averageAge(), averageAge(x), 0.0001);
        p.water();
       // assertEquals(p.averageAge(), averageAge(x), 0.0001);
        p.water();
       // assertEquals(p.averageAge(), averageAge(x), 0.0001);
        p.water();
       // assertEquals(p.averageAge(), averageAge(x), 0.0001);
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        p.water();
        //assertEquals(p.averageAge(), averageAge(x), 0.0001);
        assertEquals(p.rearrange(), 9);

    }

    @Test
    public void averageHeightTest() {
        Pot p = new Pot("circle", 11);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f8 = new Flower(null, "rose", 20, 34);
        Flower f9 = new Flower(null, null, 20, 34);
        Flower[] x = {f,f2,f3,f4,f5,f6,f7,f8,f9};
        assertEquals(p.averageHeight(), -1, 0.0001);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);
        assertEquals(p.averageHeight(), 0, 0.0001);

        p.water();
        assertEquals(f.getAge(), 3, 0.0001);
        assertEquals(f2.getAge(), 3, 0.0001);
        assertEquals(f3.getAge(), 3, 0.0001);
        assertEquals(f4.getAge(), 3, 0.0001);
        assertEquals(f5.getAge(), 3, 0.0001);
        assertEquals(f6.getAge(), 3, 0.0001);
        assertEquals(f7.getAge(), 3, 0.0001);
        //assertEquals(p.averageHeight(), averageHeight(new Flower[]{f,f2,f3,f4,f5,f6,f7}), 0.0001);


        p.insert(f8);
        p.insert(f9);
        //assertEquals(p.averageHeight(), averageHeight(x), 0.0001);
        p.insert(null);


        p.water();
        //assertEquals(p.averageHeight(), averageHeight(x), 0.0001);
        p.water();
       // assertEquals(p.averageHeight(), averageHeight(x), 0.0001);
        p.water();
       // assertEquals(p.averageHeight(), averageHeight(x), 0.0001);
        p.water();
      //  assertEquals(p.averageHeight(), averageHeight(x), 0.0001);
        p.water();
      //  assertEquals(p.averageHeight(), averageHeight(x), 0.0001);
        p.water();p.water();p.water();p.water();p.water();p.water();p.water();
        p.water();p.water();p.water();p.water();p.water();p.water();p.water();
      //  assertEquals(p.averageAge(), averageAge(x), 0.0001);
        assertEquals(p.rearrange(), 9);
    }



    @Test
    public void filterTest() {
        Pot p = new Pot("cIRcle", 13);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f8 = new Flower(null, "rose", 46, 34);
        Flower f9 = new Flower(null, null, 46, 34);
        Flower[] x = {f,f2,f3,f4,f5,f6,f7,f8,f9};
        assertEquals(p.averageAge(), -1, 0.0001);

        assertTrue(p.insert(f));
        assertTrue(p.insert(f2));
        assertTrue(p.insert(f3));
        assertTrue(p.insert(f4));
        assertTrue(p.insert(f5));
        assertTrue(p.insert(f6));
        assertTrue(p.insert(f7));
        assertEquals(p.count(), 7);

        Pot p2 = p.filterColour("yellow");
        assertEquals(p2.count(), 2);
        assertEquals(p2.countColour("yellow"), 2);
        assertEquals(p2.countType("dandelion"), 2);
        assertEquals(p2.getShape(), p.getShape());
        Pot p3 = p2.filterColour("black");
        assertEquals(p3.count(), 0);
        assertEquals(p2.getShape(), p3.getShape());

        assertTrue(p.insert(f8));
        assertTrue(p.insert(f9));
        assertEquals(p.count(), 9);

        Pot p4 = p.filterType("rOse");
        assertEquals(p4.count(), 3);

        Pot p5 = p4.filterColour(null);
        assertEquals(p5.count(), 1);

        Pot p6 = p.filterColour(null).filterType(null);
        assertEquals(p6.count(), 1);
        p6 = p.filterColour("yELlow").filterType("dandelion");
        assertEquals(p6.count(), 2);
        assertEquals(p6.countColour("yellow"), 2);
        assertEquals(p6.countType("dandelion"), 2);

        //check for clone copies
        p6.water();
        p6.water();
        p6.water();
        p6.water();
        assertEquals(p6.rearrange(), 2);
        p6.water();
        assertEquals(p6.rearrange(), 0);
        p6 = p.filterColour("yELlow").filterType("dandelion");
        p6.water();
        p6.water();p6.water();
        p6.water();
        assertEquals(p6.rearrange(), 2);
        assertEquals(p.rearrange(), 0);
        assertEquals(f.getAge(), 0, 0.0001);
        assertEquals(f6.getAge(), 0, 0.0001);

        p.water();
        p.water();
        assertEquals(p4.averageAge(), 0, 0.0001);
        assertEquals(p2.averageAge(), 0, 0.0001);

        f8.grow();
        f9.grow();
        assertEquals(p5.averageAge(), 0, 0.0001);


    }

    @Test
    public void replaceTest() {
        Pot p2 = new Pot("Circle", 12);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f8 = new Flower(null, "rose", 46, 34);
        Flower f9 = new Flower(null, null, 46, 34);
        p2.insert(f);
        p2.insert(f2);
        p2.insert(f3);
        p2.insert(f4);
        p2.insert(f6);
        p2.insert(f7);
        p2.insert(f8);
        p2.insert(f9);
        assertEquals(p2.count(), 8);

        assertEquals(p2.replace(f, f5), 2);
        assertEquals(p2.count(), 8);
        f5.grow();

        assertEquals(p2.averageAge(), 0,0.0001);
        assertEquals(p2.countColour("red"), 2);


    }

    @Test
    public void replaceTypeTest() {
        Pot p2 = new Pot("Circle", 12);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f8 = new Flower(null, "rose", 46, 34);
        Flower f9 = new Flower(null, null, 46, 34);
        p2.insert(f);
        p2.insert(f2);
        p2.insert(f3);
        p2.insert(f4);
        p2.insert(f5);
        p2.insert(f6);
        p2.insert(f7);
        p2.insert(f8);
        p2.insert(f9);
        assertEquals(p2.count(), 9);

        assertEquals(p2.replaceType("rose", null), 3);
        assertEquals(p2.countColour("white"), 1);
        assertEquals(p2.countType("rose"), 0);
        assertEquals(p2.count(), 6);
        Flower f10 = new Flower("green", "parsnip", 1, 2);
        assertEquals(p2.replaceType(null, f10), 1);
        assertEquals(p2.replaceType(null, f5), 0);
        assertEquals(p2.count(), 6);
        f10.grow();
        assertEquals(p2.averageAge(), 0,0.0001);
        assertEquals(p2.countColour("green"), 2);

    }

    @Test
    public void replaceColourTest() {
        Pot p2 = new Pot("Circle", 12);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f8 = new Flower(null, "rose", 46, 34);
        Flower f9 = new Flower(null, null, 46, 34);
        p2.insert(f2);
        p2.insert(f3);
        p2.insert(f4);
        p2.insert(f5);
        p2.insert(f6);
        p2.insert(f7);
        p2.insert(f8);
        p2.insert(f9);
        assertEquals(p2.count(), 8);

        assertEquals(p2.replaceColour("white", f), 2);
        assertEquals(p2.count(), 8);
        f.grow();

        assertEquals(p2.averageAge(), 0,0.0001);
        assertEquals(p2.countColour("yellow"), 3);
        assertEquals(p2.countType("orchid"), 0);
        assertEquals(p2.countType("rose"), 2);

    }

    @Test
    public void combineTest() {
        Pot p1 = new Pot("Square", 12);
        Pot p2 = new Pot("Circle", 12);
        Flower f = new Flower("yellow", "dandelion", 12, 23);
        Flower f2 = new Flower("white", "orchid", 2, 53.4);
        Flower f3 = new Flower("green", "parsely", 2, 53.4);
        Flower f4 = new Flower("orange", "tulip", 6, 53.4);
        Flower f5 = new Flower("red", "rose", 15, 53.4);
        Flower f6 = new Flower("yellow", "dandelion", 12, 23);
        Flower f7 = new Flower("white", "rose", 4, 53.4);
        Flower f8 = new Flower(null, "rose", 46, 34);
        Flower f9 = new Flower(null, null, 46, 34);
        p2.insert(f);
        p2.insert(f2);
        p2.insert(f3);
        p2.insert(f4);
        p2.insert(f5);
        p2.insert(f6);
        p2.insert(f7);
        p2.insert(f8);
        p2.insert(f9);
        for (int i = 0; i < 10; i++) {
            p1.insert( new Flower("yellow", "dandelion", 12, 23));
        }
        Pot np = Pot.combine(p1, p2);
        assertEquals(np.count(), 5);
        assertEquals(np.size(), 5);
        assertFalse(np.contains(null));
        assertEquals(np.getShape(), p1.getShape());
        assertEquals(np.countColour("yellow"), 1);
        assertEquals(np.countColour("white"), 1);
        assertEquals(np.countColour("green"), 1);
        assertEquals(np.countColour("orange"), 1);
        assertEquals(np.countColour("red"), 1);
        assertEquals(np.countColour(null), 0);

        p1.water();
        assertEquals(np.averageAge(), 0, 0.0001);
    }

}
