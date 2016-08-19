/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/queryvalid.js")
 */
//host:port
conn = new Mongo("10.181.163.58:27018");
print("==============================data valid start==================");
main(1);
print("==============================data valid  done==================");
var res;

function main(mnum){
    if(mnum==1){
        //��ѯ�����Ƿ�Ϊ��
        queryDiuInQudaoDevice();
    }else if(mnum==2){
        //��ѯios token�Ƿ�Ϊ��
        querytokenInIosDevice();
    }else if(mnum==3){
        //��ѯ����ios�����豸�Ƿ��ظ�����
        queryIosShard();
    }else if(mnum==4){
        //��ѯ����android�����豸�Ƿ��ظ�����
        queryAndroidShard();
    }else if(mnum==5){
        //��ѯ����ios�����豸�Ƿ��ظ����� ȫ��
        queryAndroidFull();
    }else if(mnum==6){
        //��ѯ����android�����豸�Ƿ��ظ����� ȫ��
        queryIosFull();
    }else if(mnum==7){
        //�ȶ�restore ���ݽ��
        compareRestoreRes();
    }    else{
        print("please input a number in main() " );

        print("��ѯ����diu�Ƿ�Ϊ�� �޸�Ϊ main(1)" );

        print("��ѯios token�Ƿ�Ϊ��  �޸�Ϊ main(2) " );

        print("��ѯ����ios�����豸�Ƿ��ظ�����  �޸�Ϊ main(3) " );

        print("��ѯ����android�����豸�Ƿ��ظ����� �޸�Ϊ main(4) " );

        print("��ѯ����ios�����豸�Ƿ��ظ����� ȫ��! �޸�Ϊ main(5) " );

        print("��ѯ����android�����豸�Ƿ��ظ����� ȫ��! �޸�Ϊ main(6) " );

        print("�ȶ�restore ���ݽ�� �޸�Ϊ main(7) " );

    }

}
//��ѯ�����Ƿ�Ϊ��
function queryDiuInQudaoDevice(){
    db = conn.getDB("qudao");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    res=eval(db.qudao_device_ios_101.find({"diu":""}).count());
    print("diu blank count is "+ res);
    res=eval(db.qudao_device_ios_101.find({"diu":null}).count());
    print("diu null count is "+ res);
}

//��ѯios token�Ƿ�Ϊ��
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

//��ѯios token
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
            print(tokenArray[i]+" token ������ !!!");
        else if(res==1)
            print("��"+i+"�� token ��֤����  !!!");
        else{
            print(tokenArray[i]+" token �ظ� "+res+"��!!!");
        }

    }

}


//��ѯ����  ������Ա �豸 ���� android tid
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
            print(tokenArray[i]+" tid ������ !!!");
        else if(res==1)
            print("��"+i+"�� tid ��֤����  !!!");
        else{
            print(tokenArray[i]+" tid �ظ� "+res+"��!!!");
        }
    }
}
//��ѯ����tid ��8����Ƭ�� ȫ��ɨ��
function queryAndroidFull(){
    db = conn.getDB("datasource");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    var tokenArray = new Array(
        "VZzXUWCk4aUDADEwZBps2GkD",
        "UwAWUrpnHh4BAEj61rgus5tp",
        "U1daeOSJMG0DAB4GltQy5+tm",
        "VayHO79AIooDAOSg3SsjeSJD",
        "VIMQvfe2x7EDAPN73eYWf7fG"
    );
    var res;
    var tmp;

    for(var i=0;i<tokenArray.length;i++) {
        var sum=0;
        for(var j=0;j<8;j++) {
            tmp="db.device_android_101_"+j+".find({taobao_id:'"+tokenArray[i]+"'}).count()";
            res=eval(tmp);
            res=eval(tmp);
            if(res==0)
                print(tokenArray[i]+" �� "+j+"���� ������ !!!");
            else if(res==1)
                print(tokenArray[i]+"�� "+j+"����  !!!");
            sum+=res;
        }
        if(sum>1)
            print(tokenArray[i]+"���ֶ��  !!!");
    }
}
/***
 * �ȶ�restore���ݿ���������ļ�¼����
 *
 * */
function compareRestoreRes(){
    conn1 = new Mongo("10.181.163.58:27018");
    db1 = conn1.getDB("datasource");
    db1.auth("pushdb","!QAZ@WSX#EDC$RFV");
    var arr1=[];

    conn2 = new Mongo("10.181.163.50:27017");
    db2 = conn2.getDB("datasource");
    var arr2=[];

}
//��ѯ����tid ��8����Ƭ�� ȫ��ɨ��
function queryIosFull(){
    db = conn.getDB("datasource");
    db.auth("pushdb","!QAZ@WSX#EDC$RFV");
    var tokenArray = new Array(
        "4deeba4546d073bf1d2c2f0267b1ccab8cac8d62ccd60eebd90cad7e34b4ca84",
        "414da9fdd8be3f10b593126c7b877f26625c15e5f837f1b2e0a26aafbfe53319",
        "ae070e02770484806a946c6e7eea2c524f4d920cc819a58c82baf7a0a31b3329",
        "bc3c10f2e9998fe171f0c1a1ee2f7c7e1525d9a88666f8dbfe2186c35bb73796"

    );
    var res;
    var tmp;

    for(var i=0;i<tokenArray.length;i++) {
        var sum=0;
        for(var j=0;j<8;j++) {
            tmp="db.device_ios_101_"+j+".find({token:'"+tokenArray[i]+"'}).count()";
            res=eval(tmp);
            res=eval(tmp);
            if(res==0)
                print(tokenArray[i]+" �� "+j+"���� ������ !!!");
            else if(res==1)
                print(tokenArray[i]+"�� "+j+"����  !!!");
            sum+=res;
        }
        if(sum>1)
            print(tokenArray[i]+"���ֶ��  !!!");
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
function isNull(str){
    return str == null || str.value == "";
}