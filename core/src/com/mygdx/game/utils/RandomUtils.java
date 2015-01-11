package com.mygdx.game.utils;

import java.util.Random;
import com.mygdx.game.enums.EnemyDataType;

public class RandomUtils {

    public static EnemyDataType getRandomEnemyType() {
        RandomEnum<EnemyDataType> randomEnum = new RandomEnum<EnemyDataType>(EnemyDataType.class);
        return randomEnum.random();
    }

    private static class RandomEnum<E extends Enum> {

        private static final Random RND = new Random();
        private final E[] values;

        public RandomEnum(Class<E> token) {
            values = token.getEnumConstants();
        }

        public E random() {
            return values[RND.nextInt(values.length)];
        }
    }

}
