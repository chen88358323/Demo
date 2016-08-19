/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/addPushDeviceIndex.js")
 */
//host:port
conn = new Mongo("localhost:30005");
//conn = new Mongo( );


db = conn.getDB("datasource");
print("==============================device_android_101_addIndex start==================");
device_android_101_addIndex();
device_android_101_delIndex();
print("==============================device_android_101_addIndex  done==================");


print("==============================device_ios_101_addIndex start==================");
device_ios_101_addIndex();
device_ios_101_delIndex();
print("==============================device_ios_101_addIndex  done==================");


print("==============================CHANGE DB==================");
db = conn.getDB("qudao");
print("==============================qudao_device_ios_101_addIndex start==================");
qudao_device_ios_101_addIndex();
qudao_device_ios_101_delIndex();
print("==============================qudao_device_ios_101_addIndex  done==================");


function device_android_101_addIndex(){
    var res=db.device_android_101.ensureIndex (  { token:1} ,{ background: true });
    print("token index res:"+res.ok);
    res=db.device_android_101.ensureIndex (  { taobao_id:1} ,{ background: true });
    print("taobao_id index res:"+res.ok);
    res=db.device_android_101.ensureIndex (  { adcode:1} ,{ background: true })
    print(" adcode res:"+res.ok);
    res=db.device_android_101.ensureIndex (  { flag:1} ,{ background: true });
    print("flag index res:"+res.ok);
    res=db.device_android_101.ensureIndex (  { diu:1} ,{ background: true });
    print("diu index res:"+res.ok);
    res=db.device_android_101.ensureIndex (  { div:1} ,{ background: true });
    print("div index res:"+res.ok);

        //os is delete
    //res=db.device_android_102.ensureIndex (  { os:1} ,{ background: true });
    //print("os index res:"+res.ok);
}
function device_android_101_delIndex(){
    db.device_android_102.dropIndex (  { token:1} ,{ background: true });
    db.device_android_102.dropIndex (  { taobao_id:1} ,{ background: true });
    db.device_android_102.dropIndex (  { flag:1} ,{ background: true });
    db.device_android_102.dropIndex (  { diu:1} ,{ background: true });
    db.device_android_102.dropIndex (  { div:1} ,{ background: true });
    db.device_android_102.dropIndex (  { adcode:1} ,{ background: true });
}

function device_ios_101_addIndex(){
    var res=db.device_ios_101.ensureIndex (  { adcode:1} ,{ background: true })
    print("adcode index res:"+res.ok);
    res=db.device_ios_101.ensureIndex (  { token:1} ,{ background: true })
    print("token index res:"+res.ok);
    res=db.device_ios_101.ensureIndex (  { diu:1} ,{ background: true })
    print("diu index res:"+res.ok);
    //res=db.device_ios_101.ensureIndex (  { diu2_md5:1} ,{ background: true })
    //print("diu2_md5 index res:"+res.ok);
    res=db.device_ios_101.ensureIndex (  { div:1} ,{ background: true })
    print("div index res:"+res.ok);
    res=db.device_ios_101.ensureIndex (  { flag:1} ,{ background: true })
    print("flag index res:"+res.ok);
}

function device_ios_101_delIndex(){
    var res=db.device_ios_101.dropIndex (  { adcode:1} ,{ background: true })
    print("adcode index res:"+res.ok);
    res=db.device_ios_101.dropIndex (  { token:1} ,{ background: true })
    print("token index res:"+res.ok);
    //res=db.device_ios_101.dropIndex (  { diu:1} ,{ background: true })
    //print("diu index res:"+res.ok);
    //res=db.device_ios_101.dropIndex (  { diu2_md5:1} ,{ background: true })
    //print("diu2_md5 index res:"+res.ok);
    res=db.device_ios_101.dropIndex (  { div:1} ,{ background: true })
    print("div index res:"+res.ok);
    res=db.device_ios_101.dropIndex (  { flag:1} ,{ background: true })
    print("flag index res:"+res.ok);
}

function qudao_device_ios_101_addIndex(){
    var res=db.qudao_device_ios_101.ensureIndex (  { diu:1} ,{ background: true })
    print("diu index res:"+res.ok);
    res=db.qudao_device_ios_101.ensureIndex (  { diu2_md5:1} ,{ background: true })
    print("diu2_md5 index res:"+res.ok);
    res=db.qudao_device_ios_101.ensureIndex (  { div:1} ,{ background: true })
    print("div index res:"+res.ok);
}


function qudao_device_ios_101_delIndex(){
    var res=db.qudao_device_ios_101.dropIndex (  { diu:1} ,{ background: true })
    print("diu index res:"+res.ok);
    res=db.qudao_device_ios_101.dropIndex (  { diu2_md5:1} ,{ background: true })
    print("diu2_md5 index res:"+res.ok);
    res=db.qudao_device_ios_101.dropIndex (  { div:1} ,{ background: true })
    print("div index res:"+res.ok);
}