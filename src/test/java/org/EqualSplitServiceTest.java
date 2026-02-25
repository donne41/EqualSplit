package org;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class EqualSplitServiceTest {
    EqualSplitService eqService = new EqualSplitService();

    @Test
    void invalidInputShouldThrowException() {
        assertThrows(RuntimeException.class,
                () -> eqService.addPerson(" ", 200));
        assertThrows(RuntimeException.class,
                () -> eqService.editPerson(" hej", -100));
    }

}
