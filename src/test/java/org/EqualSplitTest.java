package org;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class EqualSplitTest {


    EqualSplit ES = new EqualSplit();

    List<Person> getExampleList() {
        Person p1 = new Person("One", 100);
        Person p2 = new Person("Two", 200);
        Person p3 = new Person("Three", 300);
        Person p4 = new Person("Four", 400);
        Person p5 = new Person("Five", 500);
        Person p6 = new Person("Six", 600);
        return List.of(p1, p2, p3, p4, p5, p6);
    }

    @Test
    void getSum() {
        var result = ES.getSum(getExampleList());

        assertThat(result).isEqualTo(2100);
    }

    @Test
    void getPortionSize() {
        List<Person> list = getExampleList();
        var sum = ES.getSum(list);
        var result = ES.getPortionSize(list, sum);

        assertThat(result).isEqualTo(350);
    }

    @Test
    void getPersonOwnedShouldReturnZeroWhenSummerized() {
        var list = getExampleList();
        var sum = ES.getSum(list);
        var portion = ES.getPortionSize(list, sum);
        ES.setMoneyOwned(list, portion);
        double result = 0;
        for (Person p : list) {
            result += p.getMoneyOwnd();
        }
        assertThat(result).isZero();
    }

    @Test
    void getAmountOfsentMoney() {
        var list = getExampleList();
        var sum = ES.getSum(list);
        var portion = ES.getPortionSize(list, sum);
        ES.setMoneyOwned(list, portion);
        ES.setPersonCategoryList(list);
        var transList = ES.getDebtCollect();
        double totalSent = 0;
        for (Transaction sent : transList) {
            totalSent += sent.getMoney();
        }

        assertThat(totalSent).isEqualTo(450);
    }

}
