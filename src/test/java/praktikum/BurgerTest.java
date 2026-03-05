package praktikum;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BurgerTest {

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    private Burger burger;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }

    @Test
    @DisplayName("setBuns сохраняет булочку")
    void setBunsShouldAssignBun() {
        burger.setBuns(bun);
        // Поле private → проверяем через поведение
        when(bun.getPrice()).thenReturn(100f);
        when(bun.getName()).thenReturn("test-bun");

        assertEquals(200f, burger.getPrice(), 0.001f);
    }

    @Test
    @DisplayName("addIngredient добавляет ингредиент в список")
    void addIngredientIncreasesListSize() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(2, burger.ingredients.size());
    }

    @Test
    @DisplayName("removeIngredient удаляет элемент по индексу")
    void removeIngredientByIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(filling, burger.ingredients.get(0));
    }

    @ParameterizedTest
    @CsvSource({
            "0, 1, 'sauce → filling'",
            "1, 0, 'filling → sauce'",
            "0, 0, 'без изменений'"
    })
    @DisplayName("moveIngredient корректно меняет позиции")
    void moveIngredientShouldChangeOrder(int from, int to, String description) {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(from, to);

        if (from == 0 && to == 1) {
            assertEquals(filling, burger.ingredients.get(0));
            assertEquals(sauce, burger.ingredients.get(1));
        } else if (from == 1 && to == 0) {
            assertEquals(filling, burger.ingredients.get(0));
            assertEquals(sauce, burger.ingredients.get(1));
        } else {
            assertEquals(sauce, burger.ingredients.get(0));
            assertEquals(filling, burger.ingredients.get(1));
        }
    }

    @Test
    @DisplayName("getPrice = 2 × цена булки + сумма цен ингредиентов")
    void getPriceShouldCalculateCorrectly() {
        when(bun.getPrice()).thenReturn(120f);
        when(sauce.getPrice()).thenReturn(30f);
        when(filling.getPrice()).thenReturn(180f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float expected = 450f;
        assertEquals(expected, burger.getPrice(), 0.001f);
    }

    @Test
    @DisplayName("getReceipt формирует строку с правильным форматированием")
    void getReceiptShouldReturnFormattedString() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(sauce.getType()).thenReturn(IngredientType.SAUCE);
        when(sauce.getName()).thenReturn("chili sauce");
        when(sauce.getPrice()).thenReturn(100f);

        when(filling.getType()).thenReturn(IngredientType.FILLING);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(180f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce chili sauce ="));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("Price: 480,000000"));

    }

    @Test
    @DisplayName("getPrice без булки бросает NullPointerException")
    void getPriceWithoutBunThrowsNPE() {
        burger.addIngredient(sauce);
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }
}