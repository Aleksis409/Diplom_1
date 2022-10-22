package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class DatabaseTest {

    private Database database;
    private List<Bun> buns;
    private List<Ingredient> ingredients;

    @Mock
    Bun bun0;
    @Mock
    Bun bun1;
    @Mock
    Ingredient ingredient0;
    @Mock
    Ingredient ingredient1;

    @Before //создание тестовой базы данных
    public void createDatabase() {
        database = new Database();
        buns = new ArrayList<>();
        buns.add(bun0);
        buns.add(bun1);
        ingredients = new ArrayList<>();
        ingredients.add(ingredient0);
        ingredients.add(ingredient1);
    }

    @Test //тест метода availableBuns()
    public void availableBunsTestIsNoReturnBunsList() {
        Database databaseSpy = Mockito.spy(database);
        Mockito.when(databaseSpy.availableBuns()).thenReturn(buns);
        assertEquals("Не возвращается список булочек из базы", buns, databaseSpy.availableBuns());
    }

    @Test //тест метода availableIngredients()
    public void availableIngredientsTestIsNoReturnIngredientsList() {
        Database databaseSpy = Mockito.spy(database);
        Mockito.when(databaseSpy.availableIngredients()).thenReturn(ingredients);
        assertEquals("Не возвращается список ингредиентов из базы", ingredients, databaseSpy.availableIngredients());
    }
}