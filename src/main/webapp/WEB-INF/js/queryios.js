/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/add.js")
 */
//host:port
//conn = new Mongo("localhost:30005");
conn = new Mongo( );


db = conn.getDB("datasource");
print("==============================device_batch_shard_query start==================");
queryshard(1);
print("==============================device_batch_shard_query  done==================");


/**
 * ��Ƭ�Ų�ѯ
 * @param platform
 * 1  android
 * 2  ios
 */
function queryshard(platform){
    if(platform==1)
        platform='android';
    else
        platform='ios';

    var tokenArray = new Array(
        "00002de37006f97f3ce5b087d1c71cc5b9bcb06351576d0f84e8477c59ad824d",
        "000037d0235b5763c50e2db34f42275d8b15f97f1acefee999658577c451f7f6",
        "00017fafbcb6942a85b367608397654c1f128a6684f8031f8b61b0baa9a1888e",
        "0001c77ce7b3ff87352f8daa5359d07c279a83dd2b9ca6a9e2ba3176c96a7c64",
        "00024cfc7f6b81981e4a8c4b34fdb559dc0b7c5375f3bf65118367419ee895fb",
        "0004ea2146b472987bd8fe92b275bd6bbfd7b2cea741b25303b5c001d4b5cc83",
        "00051d02891bb56d3d5b2b9a41218c198975b45d02aabdb2a2410920ce1ca88e",
        "0005c1d6046f7c46c9284bb83808da14946e261362494cc5856b04bc7ffad125",
        "0005ed60019288b8b9b2662f6abb0e87b0b5cb5fad6c69403721361e7f04bfae",
        "00064c8540ce7ac11d95a88b1324545bb8d1919b920b07d853e2d2aeb2da705d",
        "0006fbdb5b0644954966c43916352b2e721f3b83e49354dc3c8714c9b5b975f9",
        "0008d87b5cc0dc981543ae60dc2f64c46bbb8c79ab477479b957b0362275ddcd",
        "0009b32a934d931b03ca4dc31925ec01b209a9dd0a4d6366e039e0fe5b6ec698",
        "0009e5d50fccd0558162baa3042c9ac24e80e27de3fc75b81ec0227bfc393a91",
        "000b7c0566852b7ece17e987261038f0d70099d139bf9f1985c4d9e16ca0aa13",
        "000bebb2b4362f5c942ce20c6b769fdd65189fc4d8f923fd3a1316ead156d509",
        "000bf6adfc9590fecf4209b25c3238cbbb9cab6e62059699f5b55c3aaa258ce9",
        "000cc55648bbc754788cd216595dd48611f50a11f0e63540b2eff151bc46b745",
        "000e4e35bb42a508bb01cee7ed1432434ffeaaba03e332b4cf4d1251ef870e4f",
        "00101fe51f4c9316ec4557c308bc1bb508bb54b245c1f1168f2f14ab28f61d88",
        "0013a6993abff5b384cbf8c510c6da5245c980160a9788d8525a6cd1bd9420a5",
        "00149a1531e900a9bb68556686b03e672c24a8dd627947888368913bc654d80a",
        "0016285888db45bb4da5b1d5f50ef2f39ebc84d898b748f00665a3e654eadbe9",
        "00168533e4f0a1c960dd66ce01008f30702de4f5c60172c516dea6f94c6841ec",
        "00181555e1de7e89dcab99f2be59ea29965fda3d93920d5a6afb91f591a2e1a0",
        "0019c0f17dbce08c0389fc77a213d01744754605956452ec3ae0772a2c551635",
        "001abddab8ca03778e737a8458d1cd9b7f4433519fe69f8b7146e3970c333c00",
        "001cac0f687d8969c5f3301d5ca7dd2dec2bd1a55b66db338a33801af8d73011",
        "002047422d26159b962197753e7b2d5afee7cc81bf3131cc445a60391ed6c78d",
        "0020d3fc4e6b878bab823c7182a067070602b56edeba2ae3448f700fc1c42dc3",
        "002103159a5b4d24af6c4bb433b0a336b67e5a205ea5a0a143741ca581270c34",
        "0023040b030630e947a18893cf2c7ffe49f3dddc2afbeeacf3c934b592a8ebc8",
        "00238965deb67233e6d0912e95a1ef36278f3c3cf21a09e828c8b7e8ea23c2ef",
        "00241765d33e0fe9425c1a822c7e8454b8120bc56a2cc3018971777d1d02ad27",
        "0024a46aa23f897997037e494d7f740468ee80ead9a50537bc73849cb4bcd2dd",
        "0025d8f02143f0c847718eece26ac5e062798be5ae6040421b7b09130f188c09",
        "00277619eaede0433b7fd0385c0dfc1e0933aa788efa9164541c0869ce66df9e",
        "002778df6d1e8aa5c166e9bfc30cf48d1bb5a651028e57f21fb4e6d8c0abc84a",
        "0027e062b490061d223610120292258437a8fc4d5d637e2578a0b31156dd0446",
        "0027eaf699f8b30e7605e179d6ecfc8c10bff0b744f3bf6490418a2281e9bce9",
        "002a31cfb9030f4517f23677c125e40565d13973d877c158f601e28d1d4f6682",
        "002a5e69476926d93f54be58787e0504338ff99e5c2455e61083471c279767dc",
        "002b893f57b4fe55369d85bcbde02a9d1e05e23643922c1e25f19768be077927",
        "002ca74819b2bbfd933a581f1acbe3b882a7227e22726ea25e4c1a02a464cf64",
        "002cb74f295ae15b15968c9486b138c679dc35199cb102b975d5b5a9d82f77df",
        "002ecd96a7d4fdc5d062a56ed0337e5c19324454acc689081ebb154794bddd70",
        "002fe501880a6f9525e277c22f8656fb35665a8596975fd48d55122e5d59dc76",
        "003042d3b0828d206c16ba7d9955d8c50b22a63096cf85da7135ea31af674344",
        "00312069cb5e7e6f6440d4bbaa3ced1dea359838273541ea78b2798a3d02dc0a",
        "003152d6c98da69f426b4bc745f34777bf86737e9dff433f19ec67ea4fefea0a",
        "2209303614a95bed4abdc42ce7885ed2ef5d51753d3f9c96ae168d183f085948",
        "220b912887d247e88bd35f341efb7d57a7b7eb1e849851c02198988d5cbe9dd1",
        "220c196e49a58741e8ed9027f86a1908b27d47b18e506427b37d8e94c4434771",
        "220d163c8a92a34844fe713d386abec2c5e2e021ded9fd9dd77a23ae9f567ded",
        "220f0d0cf789324fe4eb922e228adbe2ab8e5352120bbc24fd68354211dd925e",
        "220f696a4321c520508396f988bddccf6a0c7c86cf27e3b958d761066e3a4550",
        "220ff2993179a725395938d4c9f9cf50578874697cfed5159831c0316d8104a4",
        "2211d2fb1a729f68829feabcfd8e0621db6c5be313c4a2ef358c4a97b13e4b51",
        "2211f4424b0775a2e86f2b6307c4bcbf74727c0071545c93e5d410260b3ea8de",
        "2212a134f7ca8c42532661584d1dd2a4ccfd67fac068fa6f471332d156ef7389",
        "2212c285ba93f5974ea78e7f6fa9dff8664e1fefccdec59463d69d8d8505921a",
        "2213f33c776d64f08617ad9b7c4683722efece7ee6360420e09a1aae4a16cf61",
        "22146271b93cdb60cdef810d5ca6023f3977af2e1a91024883b68b9ee9d22eb4",
        "221542b0ddf03d10195a46f703429e0685c749e88e90a2cd300ab85bac33f9fc",
        "2217223acbaeb7ab53f0875156c9d1722f0911c85c4e4c720e56308612b74b0c",
        "22183390398b11cf849e2342497cb2012a7ca044b7fb97abe4330435e44bba74",
        "22190da733f89e2463880f33a2f0ecc40c15f0cfa4937afeab7f91a0728734e5",
        "2219577a2cc1fe13668724f059d1d2a72f238002b6e7c583becb4b1330a0afda",
        "221a52e828175dff7b77aea324a8412f84da5953d8843658e3549820990a1fce",
        "221a6b39b0c0adfa959c99863432da47c3e80ca8618fa893fa254954d5a17b05",
        "221b7a1be702644b6a1699ee9b369e00b5769e063c251e91f1f305abecc78665",
        "221bd12c3ab0b5f385b1acf4e42b96bc6b075821d307057f63e52b04d21c9d3e",
        "221c7f18a7beb60e043941293e1b196a4477bc32ec1bbbff486c92dbacefa7e5",
        "221ce8d55572550a6e5915f5e40aecad6e3f313c387c8aee47b6838e4b8fa849",
        "221d9426a777c324503f08bdbe4eda3148d48c866ff403a2e9f47a08f2f266c0",
        "221e00a9c69e7eaf03a719cfde75be3f6a9c9dfbea67d717ce09002fe962e9c0",
        "221ee243df3551b81e437a3de9394eb496370da2dd878ea39344bdb9ab8f6122",
        "22211fd68991a9bc746797f82bc73732a496f4aa59478d9931c41db369a94b43",
        "22217c6c5c97b2e4997db5a964c3799dda0011c0c234c6bfd9103eace2b33a51",
        "2221cfbe13bef0e5a49b058e62395a2f54ceafc5e3f74919aed4a02f8a167416",
        "2221e6c2eb07fa60432c1e86db8b42a2c6501e5e96cb8d31df4bda1228e2da9d",
        "222278df7ec780ef30133952222cad382c078ba768f841d646145da3a83e624d",
        "2223520196e5ca495f380755b0813f2fbc813e0babb3e2a446d37f4abf13e5bb",
        "2223fe170cb65389eb9209bfeca2d80f13c73dd577285491705014851a6d9a23",
        "2224f1542b2ff1753c5556539fd0f0339663eac745f0d6985c9af5df6b27c045",
        "222534d490a99a59ee376f3828847304235eb9726555e1ef3598b522260dc9fc",
        "2225ac8b7a418d74a22f2e73f07d86c591bacd16061081d4c38a0438349e82cf",
        "2225e9395d71d1b37d17aa60672f73f0c3b20f6960e8c0733d3834ea76287724",
        "22260abe21462386e976bcf81ac31ad7d0404be63bac79210948f088870486c0",
        "2226fd2939385ba7be636c7883568e3e58b9f8f94387a5f0acd931d5620610e4",
        "2227bf8466956358de7247190610444c2aff7bb1e6966c7f175e4ccb7776535e",
        "222883335bf4b2c97a878657c443dfb222bf3214db2c17ad30e2ea1142c73896",
        "222890e3ca5946111842ffb3eb9e747fb49e2d6100c303505d8348cb241668a8",
        "2228b4d7a7233eba8aa3e14790a9d1af25ed41d714754247d5dad04108385fe7",
        "222a897fa987ab6a8b09175da20b1ddc08b9902bbb02c9b72b6370b39390c815",
        "222ae7da232ced7b21fc6b1d217d27a1353cae99d2aa3e4ae76739f60b3ae929",
        "222c679fc843b8724e2d0160f65356eba10451410703a5a17b56b075fe02fc07",
        "222cd77d10ea9a02dc66798dadf34c41c658a13d24c6998b6b45a66e4f5a1627",
        "222d66fbc67ad7dacc88ac6b6ef8ff1754eaed1e6a2d927311db7a6231057ca2",
        "222eb726ea2f358684f290798ab7f72045eac549ab82bfedaf03ab74dc4187a6"
    );

    var res;
    var tmp;
    var indexNum;
    for(var i=0;i<tokenArray.length;i++) {
        indexNum=getShard(tokenArray[i]);
        tmp="db.device_"+platform+"_101_"+indexNum+".find({token:'"+tokenArray[i]+"'}).count()";

        //tmp="db.device_ios_101.find({token:'"+tokenArray[i]+"'}).count()";
        res=eval(tmp);
        if(res>1)
            print(tokenArray[i]+" query res:"+res);
    }
}


/**
 * �õ���Ƭ��
 * @param strKey
 * @return intValue
 */
function getShard(strKey){
    var shardNum=8;
    var index=hashCode(strKey)%shardNum;
    if(index<0)
        index+=shardNum;
    return index;
}
/**
 * java String hashCode ��ʵ��
 * @param strKey
 * @return intValue
 */
function hashCode(strKey)
{
    var hash = 0;
    if(!isNull(strKey))
    {
        for (var i = 0; i < strKey.length; i++)
        {
            hash = hash * 31 + strKey.charCodeAt(i);
            hash = intValue(hash);
        }
    }
    return hash;
}

/**
 * ��jsҳ���number����ת��Ϊjava��int����
 * @param num
 * @return intValue
 */
function intValue(num)
{
    var MAX_VALUE = 0x7fffffff;
    var MIN_VALUE = -0x80000000;
    if(num > MAX_VALUE || num < MIN_VALUE)
    {
        return num &= 0xFFFFFFFF;
    }
    return num;
}
