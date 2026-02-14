package org;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class CliRunnerTest {
    CliRunner cRunner;


    @ParameterizedTest
    @CsvSource({
            "1, Bob, 100, 9"
    })
    void addPersonShouldIncreaseGroupSize(int menuSelect, String name, double amount, int exit) {
        String simInput = String.format("%d\n%s\n%.2f\n%d\n", menuSelect, name, amount, exit);

        Scanner fakeScanner = new Scanner(simInput);
        cRunner = new CliRunner(fakeScanner);

        cRunner.main();
        var result = cRunner.list.size();

        assertThat(result).isEqualTo(1);

    }

}
