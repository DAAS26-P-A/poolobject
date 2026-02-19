package ubu.gii.dass.c01;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReusablePoolTest {

    @Test
    @DisplayName("testGetInstance")
    void testGetInstance() {
		// Comprobacion de que el pool no es nulo y que se comporta como un singleton
        ReusablePool pool1 = ReusablePool.getInstance();
        assertNotNull(pool1, "El pool no debería ser nulo tras la inicialización");
		
		// Comprobamos que dos llamadas a getInstance devuelven la misma instancia
        ReusablePool pool2 = ReusablePool.getInstance();
        assertSame(pool1, pool2, "getInstance debe devolver exactamente la misma instancia (Singleton)");
    }

	@Test
    @DisplayName("testAcquireReusableSuccess")
    void testAcquireReusableSuccess() throws Exception {
        // Modificacion del pool para comprobar que se obtienen objetos reutilizables validos
        ReusablePool pool = ReusablePool.getInstance();
        Reusable r = pool.acquireReusable();
        
        try {
            assertNotNull(r, "Debe devolver un objeto Reusable valido");
        } finally {
            // Restauramos el pool a su estado original para los demas tests
            pool.releaseReusable(r);
        }
    }
}