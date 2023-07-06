package com.HDiSED.BlockchainAnalysis.constans;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressesConstans {
    public static final String binanceFirst = "34xp4vRoCGJym3xR7yCVPFHoCNxv4Twseo";
    public static final String bitfinexFirst = "bc1qgdjqv0av3q56jvd82tkdjpy7gdp9ut8tlqmgrpmv24sq90ecnvqqjwvw97";
    public static final String binanceSecond = "39884E3j6KZj82FK4vcCrkUvWYL5MQaS3v";
    public static final String coincheck = "3LCGsSmfr24demGvriN4e3ft8wEcDuHFqh";
    public static final String okexFirst = "3MgEAFWu1HKSnZ5ZsC8qf61ZW18xrP5pgd";
    public static final String okexSecond = "3FupZp77ySr7jwoLYEJ9mwzJpvoNBXsBnE";
    public static final String poloniex = "3H5JTt42K7RmZtromfTSefcMEFMMe18pMD";
    public static final String bitfinexSecond = "3JZq4atUahhuA9rLhXLMhhTo133J9rF97j";
    public static final String bitmex = "3BMEXqGpG4FxBA1KWhRFufXfSTRgzfDBhJ";
    public static final String bittrexFirst = "385cR5DM96n1HvBDMzLHPYcw89fZAXULJP";
    public static final String bittrexSecond = "1N52wHoVR79PMDishab2XmRHsbekCdGquK";
    public static final String krakenFirst = "3HcEUguUZ4vyyMAPWDPUDjLqz882jXwMfV";
    public static final String krakenSecond = "36xjDRhhbbVUtsxsiekwZ8emf22273xhWm";
    public static final String defichain = "38pZuWUti3vSQuvuFYs8Lwbyje8cmaGhrT";
//14 exchange wallets now
   public static final String allAddresses = Stream.of(
            binanceFirst, bitfinexFirst, binanceSecond, coincheck, okexFirst, okexSecond,
            poloniex, bitfinexSecond, bitmex, bittrexFirst, bittrexSecond, krakenFirst,
            krakenSecond, defichain)
            .collect(Collectors.joining("|")) + "&n=1000";

}
