package com.haitaos.util;

import org.apache.shardingsphere.sharding.algorithm.keygen.SnowflakeKeyGenerateAlgorithm;

public class IDUtil {
    private static SnowflakeKeyGenerateAlgorithm shardingKeyGenerator = new SnowflakeKeyGenerateAlgorithm();

    public static Long geneSnowflakeID() {
        return shardingKeyGenerator.generateKey();
    }
}
