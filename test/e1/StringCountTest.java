package e1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCountTest {

    @Test
    public void countWordsTest() {

        //Casos de 0 (null y sin palabras):
        assertEquals(0, StringCount.countWords(""));
        assertEquals(0, StringCount.countWords("   "));
        assertEquals(0, StringCount.countWords(null));

        //Casos de solo una palabra:
        assertEquals(1, StringCount.countWords("Hola"));
        assertEquals(1, StringCount.countWords("Hola    "));

        //Espacios al inicio y/o al final:
        assertEquals(2, StringCount.countWords(" Hola Mundo "));
        assertEquals(2, StringCount.countWords(" Hola Mundo"));
        assertEquals(2, StringCount.countWords("Hola Mundo "));

        //Varios espacios entre palabras/intercalados con cantidades habituales:
        assertEquals(2, StringCount.countWords("Hola              Mundo"));
        assertEquals(4, StringCount.countWords("HolA      Mundo qué   tal"));

        //Escritos normales con signos de puntuación:
        assertEquals(4, StringCount.countWords("Hola Mundo, ¿qué tal?"));
        assertEquals(1, StringCount.countWords("¿?"));
        assertEquals(3, StringCount.countWords("¿Qué tal, Mundo?"));

        //Números como caracteres:
        assertEquals(2, StringCount.countWords("12345 56789"));
        assertEquals(11, StringCount.countWords("Hola Mundo hoy es el día 26 de Septiembre de 2026"));

        //Prueba con caracteres, números y caracteres especiales:
        assertEquals(9, StringCount.countWords("Hola Mundo, hoy, 26/09/2026 es el día 739878 D.C"));
    }

    @Test
    public void countCharTest() {

        //casos de 0 (null y sin palabras):
        assertEquals(0, StringCount.countChar("",  'c'));
        assertEquals(0, StringCount.countChar("   ", 'c'));
        assertEquals(0, StringCount.countChar(null, 'c'));

        //casos de mayúsculas:
        assertEquals(1, StringCount.countChar("Esto es una pruebA", 'a'));
        assertEquals(0, StringCount.countChar("Esto es otrA pruebA", 'a'));
        assertEquals(2, StringCount.countChar("Esto es lA últimA", 'A'));
        assertEquals(1, StringCount.countChar("Ahora sí es la última", 'A'));

        //casos de dígitos:
        assertEquals(1, StringCount.countChar("212", '1'));
        assertEquals(2, StringCount.countChar("212", '2'));
        assertEquals(0, StringCount.countChar("121", '0'));
        assertEquals(2, StringCount.countChar("D1g1tos entre números", '1'));

        //casos de tildes:
        assertEquals(0, StringCount.countChar("á", 'a'));
        assertEquals(1, StringCount.countChar("á", 'á'));
        assertEquals(0, StringCount.countChar("a", 'á'));

        //caso de carácter que no esté:
        assertEquals(0, StringCount.countChar("Casa", 'o'));
    }

    @Test
    public void countCharIgnoringCaseTest(){

        //null y vacio

        assertEquals(0, StringCount.countCharIgnoringCase(null, 'a'));
        assertEquals(0, StringCount.countCharIgnoringCase("", 'a'));

        // ignora mayusculas y minusculas

        assertEquals(2, StringCount.countCharIgnoringCase("Hola HOLA", 'h'));
        assertEquals(4, StringCount.countCharIgnoringCase("AaAA", 'a'));

        //caracter que no aparece

        assertEquals(0, StringCount.countCharIgnoringCase("Hola", 'z'));

        //numeros

        assertEquals(2, StringCount.countCharIgnoringCase("D1g1tos", '1'));

        // á y a son diferentes

        assertEquals(0, StringCount.countCharIgnoringCase("á", 'a'));
        assertEquals(1, StringCount.countCharIgnoringCase("á", 'á'));

        // Á y á sí son iguales ignorando mayúsculas y minúsculas

        assertEquals(1, StringCount.countCharIgnoringCase("Á", 'á'));

    }
    @Test
    public void isPasswordSafeTest() {

        //Casos que prueban longitud:
        assertFalse(StringCount.isPasswordSafe(""));        //caso vacío
        assertFalse(StringCount.isPasswordSafe("Fr@n13"));  //6 caracteres
        assertFalse(StringCount.isPasswordSafe("HyD_t0?")); //7 caracteres

        //Casos que prueban minúsculas:
        assertFalse(StringCount.isPasswordSafe("FR@N_1309"));
        assertFalse(StringCount.isPasswordSafe("HAY_DE_T0D0?"));

        //Casos que prueban mayúsculas:
        assertFalse(StringCount.isPasswordSafe("fr@n_1309"));
        assertFalse(StringCount.isPasswordSafe("hayd_t0d0?"));

        //Casos que prueban dígitos:
        assertFalse(StringCount.isPasswordSafe("Fr@n_TreceCeroNueve"));
        assertFalse(StringCount.isPasswordSafe("Hay_De_Todo?"));

        //Casos que prueban caracteres especiales:
        assertFalse(StringCount.isPasswordSafe("Fran1309"));
        assertFalse(StringCount.isPasswordSafe("¿H4yDeTodo")); //con este comprobamos que el "¿" no funciona
            //Pruebas para cada carácter:
        assertTrue(StringCount.isPasswordSafe("ProbandoCon,00"));
        assertTrue(StringCount.isPasswordSafe("ProbandoCon.01"));
        assertTrue(StringCount.isPasswordSafe("ProbandoCon#02"));
        assertTrue(StringCount.isPasswordSafe("ProbandoCon@03"));
        assertTrue(StringCount.isPasswordSafe("ProbandoCon?04"));
        assertTrue(StringCount.isPasswordSafe("ProbandoCon$05"));

        //Casos completamente correctos:
        assertTrue(StringCount.isPasswordSafe("Fran13?9"));           //probamos si va justo con 8
        assertTrue(StringCount.isPasswordSafe("Fr@n_1309"));          //solo una mayúscula
        assertTrue(StringCount.isPasswordSafe("sÓL0_UN@_MINÚSCULA")); //solo una minúscula
        assertTrue(StringCount.isPasswordSafe("Sí_H@y_De_Tod0"));     //solo un dígito

        //finalmente, comprobamos el "_", pues lo usamos durante varios tests y así libramos que haya contado eso:
        assertFalse(StringCount.isPasswordSafe("Fran_1309"));
    }
}