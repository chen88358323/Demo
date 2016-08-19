/**
 * Created by chichen.cc on 2015/10/22.
 *
 * under mongo cmd #load("/home/chichen.cc/shardandroid.js")
 */
//host:port
//conn = new Mongo("localhost:30005");
conn = new Mongo( );
db = conn.getDB("datasource");
print("********************* main start*********************");
//device_android_dropIndex("device_android_101_clean")
mainMethod();
print("********************* main done*********************");
function mainMethod(){
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

function device_android_101_addIndex(tbname){
    var res=eval("db."+tbname+".ensureIndex (  { token:1} ,{ background: true })");
    print("token index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { taobao_id:1} ,{ background: true })");
    print("taobao_id index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { adcode:1} ,{ background: true })");
    print(" adcode res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { flag:1} ,{ background: true })");
    print("flag index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { div:1} ,{ background: true })");
    print("div index res:"+res.ok);

}
var res=eval("db.report.ensureIndex (  { originTotal:1} ,{ background: true })");

function device_ios_101_addIndex(tbname){
    var res=eval("db."+tbname+".ensureIndex (  { token:1} ,{unique:true, background: true })");
    print("token index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { adcode:1} ,{ background: true })");
    print("adcode index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { div:1} ,{ background: true })");
    print("div index res:"+res.ok);
    res=eval("db."+tbname+".ensureIndex (  { flag:1} ,{ background: true })");
    print("flag index res:"+res.ok);
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