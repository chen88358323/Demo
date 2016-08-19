/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/add.js")
 */
//host:port
conn = new Mongo("10.181.163.58:27018");
db = conn.getDB("datasource");
db.auth("pushdb","!QAZ@WSX#EDC$RFV")
print("==============================device_shard_android_ios_addIndex start==================");
//device_android_dropIndex("device_android_101_clean")
addShardIndex();
print("==============================device_shard_android_ios_addIndex start==================");

db = conn.getDB("qudao");
db.auth("pushdb","!QAZ@WSX#EDC$RFV")
//qudao_device_ios_101_addIndex();

function addShardIndex(){
    var androidshard="device_android_101_";
    var iosshard="device_ios_101_";
    var tmp;
    for(var i=0;i<8;i++) {

        tmp=androidshard+""+i;
        print("=============================="+tmp+"  start==================");
        device_android_101_addIndex(tmp);
        print("=============================="+tmp+"  done==================");
        tmp=iosshard+""+i;
        print("=============================="+tmp+"  start==================");
        device_ios_101_addIndex(tmp);
        print("=============================="+tmp+"  done==================");

    }
}

function qudao_device_ios_101_addIndex(){
    var res=db.qudao_device_ios_101.ensureIndex (  { diu:1} ,{ unique:true,background: true })
    print("diu index res:"+res.ok);
    res=db.qudao_device_ios_101.ensureIndex (  { diu2_md5:1} ,{ background: true })
    print("diu2_md5 index res:"+res.ok);
    res=db.qudao_device_ios_101.ensureIndex (  { div:1} ,{ background: true })
    print("div index res:"+res.ok);
}

function device_android_101_addIndex(tbname){
    var res=eval("db."+tbname+".ensureIndex (  { os:1} ,{ background: true })");
    print("os index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { taobao_id:1} ,{ background: true })");
    //print("taobao_id index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { adcode:1} ,{ background: true })");
    //print(" adcode res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { flag:1} ,{ background: true })");
    //print("flag index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { div:1} ,{ background: true })");
    //print("div index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { diu:1} ,{ background: true })");
    //print("diu index res:"+res.ok);

}

function device_ios_101_addIndex(tbname){
    var res=eval("db."+tbname+".ensureIndex (  { os:1} ,{ background: true })");
    print("os index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { adcode:1} ,{ background: true })");
    //print("adcode index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { div:1} ,{ background: true })");
    //print("div index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { flag:1} ,{ background: true })");
    //print("flag index res:"+res.ok);
    //res=eval("db."+tbname+".ensureIndex (  { diu:1} ,{ background: true })");
    //print("diu index res:"+res.ok);
}

function device_android_dropIndex(tbname){
    var res=eval("db."+tbname+".dropIndex (  { token:1} ,{ background: true })");
    print("token index res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { taobao_id:1} ,{ background: true })");
    print("taobao_id index res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { adcode:1} ,{ background: true })");
    print(" adcode res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { flag:1} ,{ background: true })");
    print("flag index res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { diu:1} ,{ background: true })");
    print("diu index res:"+res.ok);
    res=eval("db."+tbname+".dropIndex (  { div:1} ,{ background: true })");
    print("div index res:"+res.ok);

}