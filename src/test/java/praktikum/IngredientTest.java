package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {
    private Ingredient ingredient;

    @Before //создание ингредиента
    public void createIngredient() {
        ingredient = new Ingredient(IngredientType.SAUCE, "Ингредиент", 0.01f);
    }

    @Test //тест метода getPrice()
    public void getPriceTestIsNoReturnPriceIngredientFloat() {
        float actualResult = ingredient.getPrice();
        float expectedResult = 0.01f;
        assertEquals("Должно быть 0.01", expectedResult, actualResult, 0.001);
    }

    @Test //тест метода getName()
    public void getNameTestIsNoReturnNameIngredientString() {
        String actualResult = ingredient.getName();
        String expectedResult = "Ингредиент";
        assertEquals("Должно быть \"Ингредиент\"", expectedResult, actualResult);
    }

    @Test //тест метода getType()
    public void getTypeTestIsNoReturnTypeIngredientIngredientType() {
        IngredientType actualResult = ingredient.getType();
        IngredientType expectedResult = IngredientType.SAUCE;
        assertEquals("Должно быть \"SAUCE\"", expectedResult, actualResult);
    }
}