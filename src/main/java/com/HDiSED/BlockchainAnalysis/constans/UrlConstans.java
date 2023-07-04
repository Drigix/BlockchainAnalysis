package com.HDiSED.BlockchainAnalysis.constans;

import static com.HDiSED.BlockchainAnalysis.constans.AddressesConstans.allAddresses;

public final class UrlConstans {

    public static final String bitcoinBlockConnectionURL = "https://blockchain.info/rawblock/000000000000000000046c26f11387bc410d7c76264fccf87aa9feb0b5c3cbe3?fbclid=IwAR3mrUpTcEJRWvV6XpHD6fqnSuzrLBJ7be3f4I9FEb_3YBuj-V6Rgrh0K8c";

    public static final String bitcoinTransactionConnectionURL = "https://blockchain.info/rawtx/";

    public static final String bitcoinAddressConnectionURL = "https://blockchain.info/rawaddr/";
//now 5 addresses only 2 transactions
    //public static final String bitcoinAddressesConectionURL = "https://blockchain.info/multiaddr?active=34xp4vRoCGJym3xR7yCVPFHoCNxv4Twseo|bc1qgdjqv0av3q56jvd82tkdjpy7gdp9ut8tlqmgrpmv24sq90ecnvqqjwvw97|3MgEAFWu1HKSnZ5ZsC8qf61ZW18xrP5pgd|3H5JTt42K7RmZtromfTSefcMEFMMe18pMD|385cR5DM96n1HvBDMzLHPYcw89fZAXULJP&n=15";
public static final String bitcoinAddressesConectionURL = "https://blockchain.info/multiaddr?active=" + allAddresses;
//public static final String bitcoinAddressesConectionURL = "https://blockchain.info/multiaddr?active=bc1q2xgy4w6vnvuf0k59l5xswwjee3fqxjwgyh6rse|bc1qxe9ysyejpx03053pdmltvnwk4uc07krlwmuvz7";

}
