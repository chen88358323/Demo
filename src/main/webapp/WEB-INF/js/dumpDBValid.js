/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/queryvalid.js")
 */
//host:port
conn = new Mongo("10.181.163.58:27018");
print("==============================data valid start==================");
main(5);
print("==============================data valid  done==================");
var res;

function main(mnum){
    if(mnum==1){
        //查询渠道是否为空
        queryDiuInQudaoDevice();
    }else if(mnum==2){
        //查询ios token是否为空
        querytokenInIosDevice();
    }else if(mnum==3){
        //查询线上ios测试设备是否重复存在
        queryIosShard();
    }else if(mnum==4){
        //查询线上android测试设备是否重复存在
        queryAndroidShard();
    }else if(mnum==5){
        //查询线上ios测试设备是否重复存在 全表
        queryAndroidFull();
    }else if(mnum==6){
        //查询线上android测试设备是否重复存在 全表
        queryIosFull();
    }else if(mnum==7){
        //比对PNSManage restore 数据结果
        getRestoreResCount(1);
        print("***************************************************");
        getRestoreResCount(2);
    }   else if(mnum==8){
        //比对datasource restore 数据结果
        getRestoreResCount(3);
        print("***************************************************");
        getRestoreResCount(4);
    }   else{
        print("please input a number in main() " );

        print("查询渠道diu是否为空 修改为 main(1)" );

        print("查询ios token是否为空  修改为 main(2) " );

        print("查询线上ios测试设备是否重复存在  修改为 main(3) " );

        print("查询线上android测试设备是否重复存在 修改为 main(4) " );

        print("查询线上ios测试设备是否重复存在 全表! 修改为 main(5) " );

        print("查询线上android测试设备是否重复存在 全表! 修改为 main(6) " );

        print("比对PNSManage restore 数据结果 修改为 main(7) " );

        print("比对datasource restore 数据结果 修改为 main(8) " );

    }

}
//查询渠道是否为空
function queryDiuInQudaoDevice(){
    db = conn.getDB("qudao");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    res=eval(db.qudao_device_ios_101.find({"diu":""}).count());
    print("diu blank count is "+ res);
    res=eval(db.qudao_device_ios_101.find({"diu":null}).count());
    print("diu null count is "+ res);
}

//查询ios token是否为空
function querytokenInIosDevice(){
    var tmp;
    var tmpa;
    db = conn.getDB("datasource");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    for(var i=0;i<8;i++) {
        tmp="db.device_ios_101_"+i+".find({token:''}).count()";
        tmpa="db.device_ios_101_"+i+".find({token:null}).count()";
        res=eval(tmp);
        print("token blank count is "+ res +" in shard "+i);

        res=eval(tmpa);
        print("token blank count is "+ res +" in shard "+i);
    }

}

//查询ios token
function queryIosShard(){
    db = conn.getDB("datasource");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    var tokenArray = new Array(
        "4deeba4546d073bf1d2c2f0267b1ccab8cac8d62ccd60eebd90cad7e34b4ca84",
        "414da9fdd8be3f10b593126c7b877f26625c15e5f837f1b2e0a26aafbfe53319",
        "ea3dfb849aba255fdeb67c77499a06704e8f5db3178131b6acf81be54565b34d",
        "90cf7435c8f288f5985b786922050a4d11ea87a46760d811040e34853bccfc61",
        "ae070e02770484806a946c6e7eea2c524f4d920cc819a58c82baf7a0a31b3329",
        "bc3c10f2e9998fe171f0c1a1ee2f7c7e1525d9a88666f8dbfe2186c35bb73796",
        "4000ca11203ce97b44a74aa78112f3b929dfea04ec48e5509fffa9de8b843d17",
        "90824310828aa77dce04ed9d1a7cbcb7b8b1030577f597dbbf38d01d6d1a0ef7",
        "55f8e0bce24248c3d3863a8b5648043d14cdae1e0bdde1a926d7f4914f966924",
        "91539028b1b609979c136a20f7325f7588f1aa6a09be15d3e090e306913b7bfb",
        "1c41074392e186481268f17b8fe4048b1f43004c187646579fc7811c5e42623d",
        "f26d2bb34bbc9232be395e0572919acaedbe374b00d25512dda0209d9b47bb8b",
        "05363f93bca81e09c8104a7c8b4246d0c57619292c80cdaf2c36d616cb07d9be",
        "412d17647e753973e8249dbd4ee9706b7413ab6d00d4c59b4b5af67520a385bf",
        "63d96ae0c4b3eb993c994cd115e18260ad4d1a1a2ed850a9cdce6ce4e1e4d625"
    );
    var res;
    var tmp;
    var shard;
    for(var i=0;i<tokenArray.length;i++) {
        shard=getShard(tokenArray[i]);
        tmp="db.device_ios_101_"+shard+".find({token:'"+tokenArray[i]+"'}).count()";
        res=eval(tmp);
        if(res==0)
            print(tokenArray[i]+" token 不存在 !!!");
        else if(res==1)
            print("第"+i+"个 token 验证正常  !!!");
        else{
            print(tokenArray[i]+" token 重复 "+res+"次!!!");
        }

    }

}


//查询线上  测试人员 设备 数量 android tid
function queryAndroidShard(){
    db = conn.getDB("datasource");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    var tokenArray = new Array(
        "VZzXUWCk4aUDADEwZBps2GkD",
        "UwAWUrpnHh4BAEj61rgus5tp",
        "VHGlkA99wmYDAId5wgwYbfUd",
        "VQXSHk1vEjYDAAii1vNuyrGb",
        "UJMi1kDClx4DACnhWv+5rgWJ",
        "VIgZ6EM55UADAFkYNDFj6RBZ",
        "UqfSZzJedFQBAPGgJRqfFsPG",
        "VLu5e0EYi7MDAG6jslJrF0rQ",
        "UtePzSGaTAUDAC5KoJckeJgw",
        "VXVprIHW8UgDADC1pldlhrv2",
        "U1daeOSJMG0DAB4GltQy5+tm",
        "VayHO79AIooDAOSg3SsjeSJD",
        "VIMQvfe2x7EDAPN73eYWf7fG"
    );
    var res;
    var tmp;
    var shard;
    for(var i=0;i<tokenArray.length;i++) {
        shard=getShard(tokenArray[i]);
        tmp="db.device_android_101_"+shard+".find({taobao_id:'"+tokenArray[i]+"'}).count()";
        res=eval(tmp);
        if(res==0)
            print(tokenArray[i]+" tid 不存在 !!!");
        else if(res==1)
            print("第"+i+"个 tid 验证正常  !!!");
        else{
            print(tokenArray[i]+" tid 重复 "+res+"次!!!");
        }
    }
}
//查询根据tid 在8个分片上 全表扫描
function queryAndroidFull(){
    db = conn.getDB("datasource");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    var tokenArray = new Array(
        "U4bnYgSECqgDAK8KX3dNcZzB",
        "U4ddwcJtJEIDAK+Tjfjx1QPO",
        "U4gSZLoznzIDAFZK/EIILrNt",
        "U4gZb/WkNN0DAHSP1pnJzKnr",
        "U4gmRRPFvKwDANKmq8xLKczE",
        "U4h2VEyOTC0DAHfNVbbSDqqX",
        "U4khkOf3PIADAKZylzwqJk+Y",
        "U4l/YQEaZ5EDAG6MIS3VafJX",
        "U4lbdvURJLgDAI0ub5CVn/IY",
        "U4mh4YIw90sDANAwq3MP+k2j",
        "U4pQDmhyd7IDAELEyW5c/+tg",
        "U4rs5OR1ZEcDABg5GWjgyuoC",
        "U4w1sBDnQyMDAE4fLVYC8i+a",
        "U4x4ED9N8MQDANC1hqNP2POa",
        "U55/nwLNx+8DAEaGqUvbb6tY",
        "U55Yk9hRAmcDAKA2NBbgNd+c",
        "U5Ki+4enoesBANmxHlMino9G",
        "U5PoC2bFMyoDAOTBXrcnjMge",
        "U5Q4NL/mIOMDANYMjV5Slj7s",
        "U5TcRKEFlicDAFog1XgVKrWO",
        "U5UZiI2WxXADABDir4+M5rEJ",
        "U5VIroAQkh0DACG0GWBzf6Q7",
        "U5WQNXA/63QDAL1q1Lqi4O1n",
        "U5ZcD9UF58wBALZNGS0hbfIG",
        "U5etq+ZCKlsDAIqoYYw7nSJJ",
        "U5ez8UiHxFUDADKmC9ZPk9lw",
        "U5g8lZigr7kDANAiBa3qLwgH",
        "U5jBxFUxnZ4DAOwFA1fxKqxY",
        "U5kX7Bm+O/cDAJIpAbtA4Iy9",
        "U5p688NwN2gDAD9y0ld5Sgmo",
        "U5qHsISwUe8DAOUWhCGBc5wB",
        "U5qYB6UoFlcDAIFb/aTZ6zcQ",
        "U5r49UaFtloDAER/G+yo5veX",
        "U5rruNK6vAYDAH7sjnIEYz5c",
        "U5sNOi8h4EgDAE8z0DC70nfq",
        "U5sPYvMNA5UDAK207mmsIIs/",
        "U5wqUXVXOocDAJQ5KsnMuNQh",
        "U6+WwV3ZkWQDAE3zRo1NYn2E",
        "U6/QAO9GdVMDAFfwfdrfKSzK",
        "U61ybU+US0QDAOCrq5onhioI",
        "U64scS5Z6TkDAHX2Z5VEEJyL",
        "U6Ae6nLyiYgDAHIPm46N48jv",
        "U6Ap2A2p7akBAAdmJBblDK3s",
        "U6Dvb/q5OfYDACY3WBRcVKkr",
        "U6GRoj8lqT0DAJfnG9OvcXIB",
        "U6JD6wtJC+oDAO47lHoEGEwR",
        "U6KYckB80dwDADUILmlpHLLF",
        "U6Lf5oH1QIMDAGiI0n2IDwyu",
        "U6PiJYf5cuEDAO+6f0TpnjOC",
        "U6Q59iUpoXgBADQl/q8DnWvT"
    );

    var tmp;
    print( "tokenArray.length "+tokenArray.length);
    for(var i=0;i<tokenArray.length;i++) {
        var res=0;
        var sum=0;
        for(var j=0;j<8;j++) {
            tmp="db.device_android_101_"+j+".find({taobao_id:'"+tokenArray[i]+"'}).count()";
            res=eval(tmp);
            //if(res==0)
            //    print(tokenArray[i]+" 在 "+j+"分区 不存在 !!!");
            if(res==1)
                print(tokenArray[i]+"在 "+j+"分区  !!!");
            sum+=res;
        }
        if(sum>1)
            print(tokenArray[i]+"出现多次  !!!");
    }
}
/***
 * 比对restore数据库后的两个库的记录条数
 * PNSManage
 * */
function getRestoreResCount(dbnum){

    if(dbnum==1){
        conn = new Mongo("10.181.163.58:27018");
        db = conn.getDB("PNSManage");
        db.auth("pushdb","!QAZ@WSX#EDC$RFV");
        print("10.181.163.58:27018 PNSManage");
    }else if(dbnum==2){
        print("10.181.163.50:27017 PNSManage");
        conn = new Mongo("10.181.163.50:27017");
        db = conn.getDB("PNSManage");
        eval("rs.slaveOk()");
    }else if(dbnum==3){
        conn = new Mongo("10.181.163.58:27018");
        db = conn.getDB("datasource");
        db.auth("pushdb","!QAZ@WSX#EDC$RFV");
        print("10.181.163.58:27018 datasource");
    }else if(dbnum==4){
        print("10.181.163.50:27017 datasource");
        conn = new Mongo("10.181.163.50:27017");
        db = conn.getDB("datasource");
        eval("rs.slaveOk()");
    }

    if(dbnum<=2){//使用PNSManage表
        var pnsMgrTables=new Array(
            "advanceMessage",
            "agoo_task_list",
            "appschema","message",
            "messageTemplate",
            "report","user","seq"
        );

    }else{//使用datasource表
        var pnsMgrTables=new Array(
            "device_ios_102",
            "device_ios_103",
            "device_android_102","device_android_103",
            "device_linux_103",
            "custom_device","device_distinct_key"
        );
    }


    var pnsMgrRes=[];
    var res;
    for(var i=0;i<pnsMgrTables.length;i++) {
        res=0;
        var tmp="db."+pnsMgrTables[i]+".find().count()";
        //print(tmp);
        res=eval(tmp);
        pnsMgrRes.push(res);
    }
    var l;
    pnsMgrRes.forEach(function(d){
        l=pnsMgrRes.indexOf(d);
        print (  pnsMgrTables[l]+" count is "+d);
    });

}
//查询根据tid 在8个分片上 全表扫描
function queryIosFull(){
    db = conn.getDB("datasource");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    var tokenArray = new Array(
        "01108fad867013507bef30a8e4e38804783ae7c3f4722c54f863e08c41de1c8c",
        "01121b27e0ee6125ee9a8336144145fba3e78102bac1faa255a41c2e26940a51",
        "0112b81a4bb550cf353c9605d179405248ba4403c4b06cb9b6144af40676f1cc",
        "01138f96376ddc65d157e81eaf5d0d2ca46e49d7cbd4bd165640e924b4aaddc2",
        "01142a4c8e23cb699413888aa92f6c31c534f900aaf3a27a88da4a49493e15fc",
        "0114ca77b688a70ea4b6a71875c4465f4cd6d4f5ae0ddff0ee0bf6ca38d7df96",
        "0115ebcc439cd048816f8de78d740963f36ccba1d8d6a5c5ba6d007bb693a009",
        "01183cfbdea0541e1f0bf2f7ebbd459c4b0f492f4cfc8d6bd6b3da644da4ddcd",
        "0118d53c785b585ce3386141d79137dec10e31c8ca23a82bb2ab9aa3ceed5198",
        "01195a281ae02eaef7e7b0ca835bb4c1da16acbb7ae8fd1ead6a6bc2f7ce2b19",
        "011985e535050569f4a72019bb35ec36cf361e71c0211a5b770e5f243d278696",
        "011a0fc754aba05e9a2077629140d5c031a19edab43eef93ecf04de38d19c62e",
        "011c0da9845a9a3bbdfd2821a3ba49dd12c2c981ab517b5255ecb2d76ed6e7f7",
        "011ca03c6999ea15a74dbcb60f37485baba9040726c6ee908f4fd63f90490564",
        "011d10ece5ab57578bfad6a7c2430b85a1cf7a55f8fb17e8d193f08c90913849",
        "011e3922799a6df4a23587638ad2b1463ad79736227ab0d0f546a7e50e42389d",
        "011e789d4f8f75e4083e94e93b173eed87dc0fa72733b3f035565cbdece33ed0",
        "011f29632295055c0804ea21e202d9895e704ecc4442c71cf7c54b6c37b8e52b",
        "01220b057d52b02ec5856e64775b4e7e60be54fd86bb24f9785150e2a8e2ef25",
        "012303e9dc9b038ff6eac35510aad14742b20459e05afa77e960487e51208b8c",
        "012318e2c15c880f0c38dc1933e230439fc8dc6d8f96d0d9518c5b6275b26d8a",
        "01233cafd09f76b2ba04e802ae03a53038be1ba903353d48ca6981a52e6bd689",
        "012523ba238784bf7d35f5ef5720edc3af475d6e9797dc6cbf3b6285dcfd886c",
        "01283e501a30baa37330d0350dd4637e127cabae997d05e0635872d5c17caebb",
        "012898d3ec305d6536ce93d559827bfe03b19b0a32b0a907d27e32a576c0cf0f",
        "0128eed22664a53b14a9f35082fb4d127955ba8f5f811255a03b40536870839b",
        "0129b58d36aa6b4bf4fdcc4e94559aceb22885a76278987f62948992b387d86e",
        "012a53e57c4187da267adddc3f67702ca7cd30d7491494eeb67e627853a9172c",
        "012a5638a8210ea82cc506cb02e6968a77c4e1496371f84ba17165fcca00bac2",
        "012a9c4ea347c512246e0950f3e16c482fbf49800f7ced64a470209570ef84d6",
        "012b8ed2efb72e747ce81e8129480bb6c46cf817252cfd00239d6a8e0bacc377",
        "012b9e5f8021539ec45517312ca1ad5c5637ee9d25d12d5064a76401b95ca490",
        "012baa959a9992354bb04f7224001cb7a497729803abee587b6041ff732cb22c",
        "012bc88ada3cba7c68dcc9f9a1cf5a6e2a68a8079fa9320949384593a00fac9b",
        "012c8c82de9216a336fcb3b2cfe6b1b6cea3d781f7b9a54e5cad8fb542eb2387",
        "012ca42a8e14bc01deab3a3a86bbd16c12ec21d348e1710736c300a4a8f2acd6",
        "012dd3e2a2dc5223cfef49811951d8a01b3253f272b05630209e206283548338",
        "012e63522056732c0e0c2d6ca31946916125fc007ce3d30269a0673018609760",
        "012eb41de33bd499afa041331803815767ba3bee135c31133e2b1341769aa730",
        "012fa4662e2d6f4bb44e2ead6d5bb65fd84806c6122bfd3ec1cf4e6c432b36b9",
        "012fd5fc0238917a282dd2c23af9b2056d2c3201a64a4ed53ef3141c3844045b",
        "0130355432e119f44cae0b5fb1c6d1c66e979724908fd621217500fd1611789b",
        "013247c95956afe41d2b9b20c8dbd07860051276484d18dd2d6994c6063345d1",
        "013413f041a8a66f880a906c121ec5e5072b4774f50e3eb7ca092e5fd7ef2b82",
        "0134f25989a9878f52b8b0e4a3a94784a86ec2d6838acb7df608326b68c85345",
        "01371195cd0df4120cc652f64e257192dc0c72187ebeb4d015aab7bd80fbb74b",
        "01371a91572dc18ceec154896affdb3504813c9360ae1dc5ecc373d7b1573c2d",
        "01387f8c7a7b79729e938d392a8ee38eb08d515ac4ca86005ca200ac73d91f16",
        "0139b15a98684e362767b676376b62ef4fb89ea36eafbe5a0b6723e8859b9e9a",
        "013cb4b1c7a8541e086d14a64e605b8cedf41b53980a4686a7fc77907f5f80de"
    );

    var tmp;
    print( "tokenArray.length "+tokenArray.length);
    for(var i=0;i<tokenArray.length;i++) {
        var sum=0;
        var res=0;
        for(var j=0;j<8;j++) {
            tmp="db.device_ios_101_"+j+".find({token:'"+tokenArray[i]+"'}).count()";
            res=eval(tmp);
            if(res==1)
                print(tokenArray[i]+"在 "+j+"分区  !!!");
            sum+=res;
        }
        if(sum>1)
            print(tokenArray[i]+"出现多次  !!!");
    }
}

/**
 * 得到分片号
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
 * java String hashCode 的实现
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
 * 将js页面的number类型转换为java的int类型
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
function isNull(str){
    return str == null || str.value == "";
}