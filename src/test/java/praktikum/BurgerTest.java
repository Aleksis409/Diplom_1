package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class BurgerTest {
    private Burger burger;
    private float[] testIngredientsPrice; //массив цен ингредиентов бургера
    private float testBunsPrice; //цена булочки
    private float testResutPrice; //цена бургера = цена булочки * 2 + сумма цен ингредиентов

    public BurgerTest(float testBunsPrice, float[] testIngredientsPrice, float testResutPrice) {
        this.testBunsPrice = testBunsPrice;
        this.testIngredientsPrice = testIngredientsPrice;
        this.testResutPrice = testResutPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные {index} ") // данные для теста метода getPrice()
    public static Object[] getPriceOfBurgerParts() {
        return new Object[][]{
                {0.0f, new float[]{0.0f, 0.0f, 0.0f}, 0.0f},
                {0.15f, new float[]{0.15f, 0.20f, 0.30f}, 0.95f},
                {100.0f, new float[]{50.0f, 60.0f, 70.0f}, 380.0f},
                {500.50f, new float[]{100.30f, 200.70f, 150.50f}, 1452.50f},
        };
    }

    @Mock
    Bun bun;
    @Mock
    Ingredient ingredient0;
    @Mock
    Ingredient ingredient1;
    @Mock
    Ingredient ingredient2;

    @Before //создание бургера: булочка и три ингредиента
    public void createBurger() {
        MockitoAnnotations.initMocks(this);
        burger = new Burger();
        burger.setBuns(bun);
        burger.addIngredient(ingredient0);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
    }

    @Test //тест метода setBuns()
    public void setBunsTestIsOneBunResultOk() {
        assertEquals("Булочка не добавляется к бургеру", bun,burger.bun);
    }

    @Test //тест метода addIngredient()
    public void addIngredientTestIsTwoIngredientResultOk() {
        assertEquals("Список ингредиентов бургера не увеличивается", 3, burger.ingredients.size());
    }

    @Test //тест метода removeIngredient()
    public void removeIngredientTestIsIndexIngredientsResultOk() {
        burger.removeIngredient(1); // удаление ингредиента с индексом 1
        assertFalse("Ингредиент 1 не удален из списка бургера", burger.ingredients.contains(ingredient1));
    }

    @Test //тест метода moveIngredient()
    public void moveIngredientTestIsIndexAndNewIndexIngredientsResultOk() {
        burger.moveIngredient(0, 2); // перемещение ингредиента с индексом 0 на место с индексом 2
        assertEquals("Ингредиент не перемещается в списке бургера", ingredient0, burger.ingredients.get(2));
    }

    @Test //тест метода getPrice()
    public void getPriceTestIsNoRetunPriceOk() {
        Mockito.when(bun.getPrice()).thenReturn(testBunsPrice);
        Mockito.when(ingredient0.getPrice()).thenReturn(testIngredientsPrice[0]);
        Mockito.when(ingredient1.getPrice()).thenReturn(testIngredientsPrice[1]);
        Mockito.when(ingredient2.getPrice()).thenReturn(testIngredientsPrice[2]);
        float price = burger.getPrice();
        assertEquals("Ошибка расчета цены бургера",testResutPrice, price, 0.001);
    }

    @Test //тест метода getReceipt()
    public void getReceiptTestIsNoRetunReceiptOk() {
        //тестовые значения полей "name", "price", "type" для составляющих бургера String[]
        String[] testIngredientsName = {"Test ingredient0", "Test ingredient1", "Test ingredient2"};
        float[] testIngredientsPrice = {0.1f, 0.2f, 0.3f};
        IngredientType[] testIngredientsType = {IngredientType.SAUCE,IngredientType.SAUCE,IngredientType.FILLING};
        String testBunsName = "Test bun";
        float testBunsPrice = 1.0f;
        //формирование актуальных значений полей чека для метода getReceipt()
        Mockito.when(ingredient0.getPrice()).thenReturn(testIngredientsPrice[0]);
        Mockito.when(ingredient0.getName()).thenReturn(testIngredientsName[0]);
        Mockito.when(ingredient0.getType()).thenReturn(testIngredientsType[0]);
        Mockito.when(ingredient1.getPrice()).thenReturn(testIngredientsPrice[1]);
        Mockito.when(ingredient1.getName()).thenReturn(testIngredientsName[1]);
        Mockito.when(ingredient1.getType()).thenReturn(testIngredientsType[1]);
        Mockito.when(ingredient2.getPrice()).thenReturn(testIngredientsPrice[2]);
        Mockito.when(ingredient2.getName()).thenReturn(testIngredientsName[2]);
        Mockito.when(ingredient2.getType()).thenReturn(testIngredientsType[2]);
        Mockito.when(bun.getPrice()).thenReturn(testBunsPrice);
        Mockito.when(bun.getName()).thenReturn(testBunsName);
        float price = testBunsPrice * 2 + testIngredientsPrice[0] + testIngredientsPrice[1] + testIngredientsPrice[2];
        // ожидаемый чек
        String expectedReceipt = String.format("(==== %s ====)%n= %s %s =%n= %s %s =%n= %s %s =%n(==== %s ====)%n%nPrice: %f%n",
                                 testBunsName, testIngredientsType[0].toString().toLowerCase(), testIngredientsName[0],
                                 testIngredientsType[1].toString().toLowerCase(), testIngredientsName[1],
                                 testIngredientsType[2].toString().toLowerCase(), testIngredientsName[2],testBunsName, price);
        assertEquals("Ошибка формирования чека за бургер", expectedReceipt, burger.getReceipt());
    }
}