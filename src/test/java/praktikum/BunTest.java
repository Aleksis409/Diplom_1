package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BunTest {
    private Bun bun;

    @Before //создание булочки
    public void createBun() {
        bun = new Bun("Test bun", 0.01f);
    }

    @Test //тест метода getName()
    public void getNameTestIsNoReturnNameBunString() {
        assertEquals("Должно быть \"Test bun\"", "Test bun", bun.getName());
    }

    @Test //тест метода getPrice()
    public void getPriceTestIsNoReturnPriceBunFloat() {
        assertEquals("Должно быть 0.01", 0.01f, bun.getPrice(), 0.001);
    }
}